/*
 * To change this template, choose Tools | Templates
 * Copyright 2016.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Dao.ITPersonas;
import CobroCoactivo.Dao.ITUsuarios;
import CobroCoactivo.Dao.ITAttempts;
import CobroCoactivo.Dao.ITEstadoUsuarios;
import CobroCoactivo.Persistencia.CivAttempts;
import CobroCoactivo.Persistencia.CivEstadoUsuarios;
import CobroCoactivo.Persistencia.CivPersonas;
import CobroCoactivo.Exception.LoginException;
import CobroCoactivo.Dao.DaoLogin;
import CobroCoactivo.Dao.ITLogin;
import CobroCoactivo.Beans.BeanLogin;
import CobroCoactivo.Dao.DaoAttempts;
import CobroCoactivo.Dao.DaoConfUsuRec;
import CobroCoactivo.Dao.DaoEstadoUsuarios;
import CobroCoactivo.Dao.DaoPersonas;
import CobroCoactivo.Dao.DaoUsuarios;
import CobroCoactivo.Utility.DateUtility;
import CobroCoactivo.Utility.Log_Handler;
import CobroCoactivo.Utility.ValidacionPassword;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import CobroCoactivo.Utility.ValidacionDatos;
import java.util.ArrayList;

import CobroCoactivo.Modelo.Modulos;
import CobroCoactivo.Modelo.Recursos;
import CobroCoactivo.Modelo.TipoRecursos;
import CobroCoactivo.Persistencia.CivConfUsuRec;
import CobroCoactivo.Persistencia.CivRecursos;
import CobroCoactivo.Persistencia.CivUsuarios;
import CobroCoactivo.Dao.ITConfUsuRec;
import CobroCoactivo.Utility.HibernateUtil;
import org.hibernate.Session;

public class LoginImplBO implements LoginBO {

    private static final int TIEMPO_RESTABLECER_HORAS = 24;

    private ITLogin loginDAO;
    private ITAttempts attemptsDAO;
    private ITPersonas personasDAO;
    private ITUsuarios usuariosDAO;
    private ITConfUsuRec confUsuRecDAO;
    private ITEstadoUsuarios estadosUsuariosDAO;

    /**
     *
     * @throws Exception
     */
    public LoginImplBO() {
        loginDAO = new DaoLogin();
        attemptsDAO = new DaoAttempts();
        personasDAO = new DaoPersonas();
        usuariosDAO = new DaoUsuarios();
        confUsuRecDAO = new DaoConfUsuRec();
        estadosUsuariosDAO = new DaoEstadoUsuarios();

    }

    @Override
    public void iniciarSesion(BeanLogin obj) throws Exception {
        Date ini = new Date();
        ini.setTime(0);
        Session session = HibernateUtil.getSessionFactory().openSession();
        CivUsuarios login = new CivUsuarios();
        login.setUsuNombre(obj.getUser().trim().toUpperCase(Locale.ROOT));
        login.setUsuPass(obj.getPassword());
        CivUsuarios usu = getLoginDAO().getUsuario(session, login.getUsuNombre()); //Esta variable se llena solamente cuando coincide el usuario. Esto es para el registro de intentos
         if (usu != null) {
            obj.setID_Usuario(usu.getUsuId() + "");
            this.registrarIntento(usu.getUsuId().intValue()); //Registro de intentos
        }
        login = getLoginDAO().validarLogin(session, login); //Carga de datos por medio de password 
        obj.setPassword("");
        if (login != null) {
            if (login.getCivEstadoUsuarios().getEstusuId().intValue() == 2) { //En caso de que el usuario no se encuentre activo 
                throw new LoginException("Este usuario ha sido deshabilitado. Por favor contáctese con el administrador del sistema.");
            }
            if (login.getCivEstadoUsuarios().getEstusuId().intValue() == 4) { //(Revalidación)En caso de que el usuario no se encuentre activo por bloqueo de intentos
                throw new LoginException("Este usuario ha sido bloqueado por superar el número máximo de intentos de usuario. Por favor contáctese con el administrador del sistema.");
            }
            reestablecerIntentosUsuario(login.getUsuId().intValue());
            Date fecha_pass = getUsuariosDAO().consultarFechaUltimoPassword(session, login.getUsuId().intValue());
            if (fecha_pass == null) {
                obj.setUserEstado(3);
                CivEstadoUsuarios civEstadousuarios = getEstadosUsuariosDAO().consultarModuloById(session, 3);
                login.setCivEstadoUsuarios(civEstadousuarios);
                getUsuariosDAO().update(session, login);
                fecha_pass = new Date(); //Fecha para reestablecer automaticamente si no hay historial 
                Log_Handler.registrarEvento("El usuario ID:" + login.getUsuId().intValue() + " necesita restaurar su contraseña ya que no hay registro de una contraseña anterior.", null, Log_Handler.INFO, getClass(), login.getUsuId().intValue());
            }
            Long dias = DateUtility.getDateDiff(fecha_pass, new Date(), TimeUnit.DAYS); //Usando fecha proceso.
            dias++;
            if (dias > 45) {
                //Se cambia el estado del usuario a estado 3 (Restablecer credenciales)
                CivEstadoUsuarios civEstadousuarios = getEstadosUsuariosDAO().consultarModuloById(session, 3);
                login.setCivEstadoUsuarios(civEstadousuarios);
                getUsuariosDAO().update(session, login);
                obj.setUserEstado(3);
                Log_Handler.registrarEvento("El usuario ID:" + login.getUsuId().intValue() + " necesita restaurar su contraseña por superar el límite de días para la restauración de contraseña.", null, Log_Handler.INFO, getClass(), login.getUsuId().intValue());
            }
            if (dias > 35) {
                //CARGA DE MENSAJE: SU CONTRASEÑA ESTÁ POR VENCER
                obj.setDias_vencimiento(46 - dias);
                obj.getNotificationMap().put(login.getUsuId().intValue(), "Su contraseña está por vencer");
            }
            //Se cargan datos básicos de usuario 
            obj.setID_Usuario(login.getUsuId() + "");
            obj.setNombre(login.getUsuNombre());
            obj.setUserEstado(login.getCivEstadoUsuarios().getEstusuId().intValue());
            //obj.setModIdPersonaUsuario(login.getCivPersonas().getPerId().intValue());
        } else {
            session.close();
            throw new LoginException("Usuario y/o contraseña inválidos");
        }
        session.close();
    }

    //Registrar Intento
    /**
     * Registrar intento en base a un ID de usuario. No se puede usar gestión de
     * transacciones ya que este método debe guardar los cambios inmediatamente.
     *
     * @param usuario Cadena de texto con el nombre de Usuario
     * @throws Exception
     */
    private void registrarIntento(int usuario) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CivAttempts aut = getAttemptsDAO().consultarIntentos(session, usuario);

        if (aut == null) {
            aut = new CivAttempts();
            CivUsuarios usu = getUsuariosDAO().consultarIdPer(session, usuario);
            aut.setCivUsuarios(usu);
            aut.setTppUltimoIntento(new Date());
            aut.setTtpIntentos(new BigDecimal(1L));
            aut.setTtpId(BigDecimal.valueOf(
                    getAttemptsDAO().insert(session, aut)));
        } else {
            Long horas = DateUtility.getDateDiff(aut.getTppUltimoIntento(), new Date(), TimeUnit.HOURS);
            CivUsuarios obj_usuario = getUsuariosDAO().consultarUsuarioBy(session, usuario);
            if (horas >= getTIEMPO_RESTABLECER_HORAS()) {
                aut.setTtpIntentos(new BigDecimal(0L));
                if (obj_usuario.getCivEstadoUsuarios().getEstusuId().intValue() == 4) {
                    CivEstadoUsuarios civEstadousuarios = getEstadosUsuariosDAO().consultarModuloById(session, 3);
                    obj_usuario.setCivEstadoUsuarios(civEstadousuarios);
                    getUsuariosDAO().update(session, obj_usuario);
                    Log_Handler.registrarEvento("El usuario ID:" + usuario + " debe reestablecer su contraseña ya que hace mas de " + getTIEMPO_RESTABLECER_HORAS() + " horas se registró un bloqueo por intentos de inicio de sesión.", null, Log_Handler.WARN, getClass(), usuario);
                }
            } else if (aut.getTtpIntentos().intValue() >= 6) {
                CivEstadoUsuarios civEstadousuarios = getEstadosUsuariosDAO().consultarModuloById(session, 4);
                obj_usuario.setCivEstadoUsuarios(civEstadousuarios);
                getUsuariosDAO().update(session, obj_usuario);
                aut.setTtpIntentos(new BigDecimal(aut.getTtpIntentos().intValue() + 1));
                aut.setTppUltimoIntento(new Date());
                getAttemptsDAO().update(session, aut);
                Log_Handler.registrarEvento("El usuario ID:" + usuario + " ha sido bloqueado por superar el número de intentos de inicio de sesión.", null, Log_Handler.WARN, getClass(), usuario);
                throw new LoginException("Ha superado el máximo número de intentos de inicio de sesión. Contáctese con el administrador del sistema.");
            }
            aut.setTtpIntentos(new BigDecimal(aut.getTtpIntentos().intValue() + 1));
            aut.setTppUltimoIntento(new Date());
            getAttemptsDAO().update(session, aut);
        }
        session.close();
    }

    /**
     * Reestablece el numero de intentos registrados con un ID de usuario
     * específico
     *
     * @param usuario Cadena de texto con el nombre del usuario
     * @throws Exception
     */
    public void reestablecerIntentosUsuario(int usuario) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CivAttempts aut = getAttemptsDAO().consultarIntentos(session, usuario);
        if (aut == null) {
            throw new Exception("Usuario requerido no registrado en el historial de intentos");
        } else {
            aut.setTtpIntentos(new BigDecimal(0 + ""));
            getAttemptsDAO().update(session, aut);
        }
        session.close();
    }

    @Override
    public List<Modulos> listarModulos(BeanLogin obj) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<CivRecursos> listR = getLoginDAO().listarRecursos(session, Integer.parseInt(obj.getID_Usuario()));
        List<Modulos> listModulos = new LinkedList<>();
        Modulos mod;

        if (listR == null) {
            throw new LoginException("El usuario no tiene ningún perfil asignado. Por favor contáctese con el administrador del sistema.");
        }

        for (int x = 0; x < listR.size(); x++) {
            CivRecursos r = listR.get(x);
            if (listModulos.isEmpty()) {
                mod = new Modulos();
                mod.setModId(r.getCivModulos().getModId());
                mod.setModIcono(r.getCivModulos().getModIcono());
                mod.setModNombre(r.getCivModulos().getModNombre());
                listModulos.add(mod);
            }
            boolean sw = true;
            for (int i = 0; i < listModulos.size(); i++) {
                Modulos m = listModulos.get(i);
                if (r.getCivModulos().getModId() == m.getModId()) {
                    sw = false;
                    break;
                }
            }
            if (sw) {
                mod = new Modulos();
                mod.setModId(r.getCivModulos().getModId());
                mod.setModIcono(r.getCivModulos().getModIcono());
                mod.setModNombre(r.getCivModulos().getModNombre());
                listModulos.add(mod);
            }
        }

        for (int x = 0; x < listModulos.size(); x++) {
            Modulos m = listModulos.get(x);
            List<Recursos> listrec = new LinkedList<>();
            for (int i = 0; i < listR.size(); i++) {
                CivRecursos r = listR.get(i);
                if (r.getCivModulos().getModId() == m.getModId()) {
                    Recursos rec = new Recursos();
                    rec.setRecId(r.getRecId());
                    rec.setRecNombre(new ValidacionDatos().letraMayuscula(r.getRecNombre()));
                    rec.setRecCarperta(r.getRecCarperta());
                    rec.setRecDescripcion(r.getRecDescripcion());
                    rec.setTipoRecursos(new TipoRecursos(r.getCivTipoRecursos()));
                    listrec.add(rec);
                }
            }
            m.setListRecurso(listrec);
        }
        session.close();
        return listModulos;
    }

    @Override
    public List<Modulos> listarModulos(BeanLogin obj, int tipo) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<CivRecursos> listR = getLoginDAO().listarRecursos(session, Integer.parseInt(obj.getID_Usuario()));

        List<CivRecursos> index = new ArrayList<>();

        for (CivRecursos civRecursoss : listR) {
            if (civRecursoss.getCivTipoRecursos().getTiprecCodigo().intValue() != tipo && civRecursoss.getCivTipoRecursos().getTiprecCodigo().intValue() != 3) {
                index.add(civRecursoss);
            }
        }

        for (CivRecursos integer : index) {
            listR.remove(integer);
        }

        List<Modulos> listModulos = new LinkedList<>();
        Modulos mod;

        if (listR == null) {
            throw new LoginException("El usuario no tiene ningún perfil asignado. Por favor contáctese con el administrador del sistema.");
        }

        for (int x = 0; x < listR.size(); x++) {
            CivRecursos r = listR.get(x);
            if (listModulos.isEmpty()) {
                mod = new Modulos();
                mod.setModId(r.getCivModulos().getModId());
                mod.setModIcono(r.getCivModulos().getModIcono());
                mod.setModNombre(r.getCivModulos().getModNombre());
                listModulos.add(mod);
            }
            boolean sw = true;
            for (int i = 0; i < listModulos.size(); i++) {
                Modulos m = listModulos.get(i);
                if (r.getCivModulos().getModId() == m.getModId()) {
                    sw = false;
                    break;
                }
            }
            if (sw) {
                mod = new Modulos();
                mod.setModId(r.getCivModulos().getModId());
                mod.setModIcono(r.getCivModulos().getModIcono());
                mod.setModNombre(r.getCivModulos().getModNombre());
                listModulos.add(mod);
            }
        }

        for (int x = 0; x < listModulos.size(); x++) {
            Modulos m = listModulos.get(x);
            List<Recursos> listrec = new LinkedList<>();
            for (int i = 0; i < listR.size(); i++) {
                CivRecursos r = listR.get(i);
                if (r.getCivModulos().getModId() == m.getModId()) {
                    Recursos rec = new Recursos();
                    rec.setRecId(r.getRecId());
                    rec.setRecNombre(new ValidacionDatos().letraMayuscula(r.getRecNombre()));
                    rec.setRecCarperta(r.getRecCarperta());
                    rec.setRecDescripcion(r.getRecDescripcion());
                    rec.setTipoRecursos(new TipoRecursos(r.getCivTipoRecursos()));
                    listrec.add(rec);
                }
            }
            m.setListRecurso(listrec);
        }
        session.close();
        return listModulos;
    }

    /**
     *
     * @return @throws Exception
     */
    @Override
    public String generarContrasena() throws Exception {
        ValidacionPassword val = new ValidacionPassword();
        return val.generarPassword();
    }

    @Override
    public void listarPerfilRecursos(BeanLogin obj) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        obj.setListUsuarioRecursos(new ArrayList<>());
        for (CivConfUsuRec pr : getConfUsuRecDAO().listPerfilRecursoByIDUsuario(session, Integer.parseInt(obj.getID_Usuario()))) {
            pr.getCivRecursos().setRecNombre(new ValidacionDatos().letraMayuscula(pr.getCivRecursos().getRecNombre()));
            obj.getListUsuarioRecursos().add(pr);
        }
        session.close();

    }

    @Override
    public List<String> listarRecursos(BeanLogin obj) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<CivRecursos> listR = getLoginDAO().listarRecursos(session, Integer.parseInt(obj.getID_Usuario()));
        List<String> rec = new LinkedList<>();

        if (listR == null) {
            return null;
        }

        for (CivRecursos list : listR) {
            rec.add(list.getRecDescripcion());
        }
        session.close();
        return rec;
    }

    @Override
    public void consultarDatosUsuario(BeanLogin obj) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CivPersonas persona = getPersonasDAO().consultarPersonasById(session, obj.getIdPersonaUsuario());
        if (persona == null) {
            throw new LoginException("No se encontró la persona correspondiente al usuario");
        }
        obj.setNombrePersonaUsuario(persona.getPerNombre1() + " " + persona.getPerApellido1());
        obj.setNombrePersonaUsuario(new ValidacionDatos().letraMayuscula(obj.getNombrePersonaUsuario()));
        obj.setCedulaPersonaUsuario(persona.getPerDocumento());
        obj.setFechaInicioPersonaUsuario(persona.getPerFechaproceso());
        session.close();
    }

    public ITPersonas getPersonasDAO() {
        return personasDAO;
    }

    public void setPersonasDAO(ITPersonas personasDAO) {
        this.personasDAO = personasDAO;
    }

    /**
     * @return the loginDAO
     */
    public ITLogin getLoginDAO() {
        return loginDAO;
    }

    /**
     * @param loginDAO the loginDAO to set
     */
    public void setLoginDAO(DaoLogin loginDAO) {
        this.setLoginDAO(loginDAO);
    }

    /**
     * @return the attemptsDAO
     */
    public ITAttempts getAttemptsDAO() {
        return attemptsDAO;
    }

    /**
     * @param attemptsDAO the attemptsDAO to set
     */
    public void setAttemptsDAO(ITAttempts attemptsDAO) {
        this.attemptsDAO = attemptsDAO;
    }

    /**
     * @return the usuariosDAO
     */
    public ITUsuarios getUsuariosDAO() {
        return usuariosDAO;
    }

    /**
     * @param usuariosDAO the usuariosDAO to set
     */
    public void setUsuariosDAO(ITUsuarios usuariosDAO) {
        this.usuariosDAO = usuariosDAO;
    }

    /**
     * @return the TIEMPO_RESTABLECER_HORAS
     */
    public static int getTIEMPO_RESTABLECER_HORAS() {
        return TIEMPO_RESTABLECER_HORAS;
    }

    /**
     * @param loginDAO the loginDAO to set
     */
    public void setLoginDAO(ITLogin loginDAO) {
        this.loginDAO = loginDAO;
    }

    /**
     * @return the estadosUsuariosDAO
     */
    public ITEstadoUsuarios getEstadosUsuariosDAO() {
        return estadosUsuariosDAO;
    }

    /**
     * @param estadosUsuariosDAO the estadosUsuariosDAO to set
     */
    public void setEstadosUsuariosDAO(ITEstadoUsuarios estadosUsuariosDAO) {
        this.estadosUsuariosDAO = estadosUsuariosDAO;
    }

    /**
     * @return the confUsuRecDAO
     */
    public ITConfUsuRec getConfUsuRecDAO() {
        return confUsuRecDAO;
    }

    /**
     * @param confUsuRecDAO the confUsuRecDAO to set
     */
    public void setConfUsuRecDAO(ITConfUsuRec confUsuRecDAO) {
        this.confUsuRecDAO = confUsuRecDAO;
    }

}
