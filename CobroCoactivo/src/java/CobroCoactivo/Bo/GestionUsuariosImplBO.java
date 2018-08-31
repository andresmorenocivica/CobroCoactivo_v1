/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanGestionUsuarios;
import CobroCoactivo.Crypto.DigestHandler;
import CobroCoactivo.Dao.DaoConfUsuRec;
import CobroCoactivo.Dao.DaoDatosPersonas;
import CobroCoactivo.Dao.DaoEstadoConfUsuRec;
import CobroCoactivo.Dao.DaoEstadoUspHistoria;
import CobroCoactivo.Dao.DaoEstadoUsuarios;
import CobroCoactivo.Dao.DaoModulos;
import CobroCoactivo.Dao.DaoMovimientos;
import CobroCoactivo.Dao.DaoPersonas;
import CobroCoactivo.Dao.DaoPrestamoExpHistorial;
import CobroCoactivo.Dao.DaoRecursos;
import CobroCoactivo.Dao.DaoTipoDocumento;
import CobroCoactivo.Dao.DaoUspHistoria;
import CobroCoactivo.Dao.DaoUsuarios;
import CobroCoactivo.Dao.ITConfUsuRec;
import CobroCoactivo.Dao.ITDatosPersonas;
import CobroCoactivo.Dao.ITEstadoConfUsuRec;
import CobroCoactivo.Dao.ITEstadoUspHistoria;
import CobroCoactivo.Dao.ITEstadoUsuarios;
import CobroCoactivo.Dao.ITModulos;
import CobroCoactivo.Dao.ITMovimientos;
import CobroCoactivo.Dao.ITPersonas;
import CobroCoactivo.Dao.ITPrestamoExpHistorial;
import CobroCoactivo.Dao.ITRecursos;
import CobroCoactivo.Dao.ITTipoDocumento;
import CobroCoactivo.Dao.ITUspHistoria;
import CobroCoactivo.Dao.ITUsuarios;
import CobroCoactivo.Exception.UsuariosException;
import CobroCoactivo.Modelo.ConfUsuRec;
import CobroCoactivo.Modelo.DatosPersonas;
import CobroCoactivo.Modelo.EstadoUsuarios;
import CobroCoactivo.Modelo.Modulos;
import CobroCoactivo.Modelo.Movimientos;
import CobroCoactivo.Modelo.Personas;
import CobroCoactivo.Modelo.PrestamoExpHistorial;
import CobroCoactivo.Modelo.Recursos;
import CobroCoactivo.Modelo.TipoDocumentos;
import CobroCoactivo.Persistencia.CivUsuarios;
import java.util.ArrayList;
import java.util.List;
import CobroCoactivo.Modelo.Usuarios;
import CobroCoactivo.Persistencia.CivConfUsuRec;
import CobroCoactivo.Persistencia.CivDatosPersonas;
import CobroCoactivo.Persistencia.CivEstadoConfusurec;
import CobroCoactivo.Persistencia.CivEstadoUsuarios;
import CobroCoactivo.Persistencia.CivEstadouspHistoria;
import CobroCoactivo.Persistencia.CivModulos;
import CobroCoactivo.Persistencia.CivMovimientos;
import CobroCoactivo.Persistencia.CivPersonas;
import CobroCoactivo.Persistencia.CivPrestamoExpHistorial;
import CobroCoactivo.Persistencia.CivRecursos;
import CobroCoactivo.Persistencia.CivTipoDocumentos;
import CobroCoactivo.Persistencia.CivUspHistoria;
import CobroCoactivo.Utility.HibernateUtil;
import static CobroCoactivo.Utility.HibernateUtil.getSessionFactory;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;

/**
 *
 * @author emadrid
 */
public class GestionUsuariosImplBO implements GestionUsuariosBO, Serializable {

    private ITUsuarios usuariosDAO;
    private ITPersonas personasDAO;
    private ITDatosPersonas datosPersonasDAO;
    private ITTipoDocumento tipoDocumentoDAO;
    private ITModulos modulosDAO;
    private ITRecursos recursosDAO;
    private ITConfUsuRec confUsuRecDAO;
    private ITEstadoConfUsuRec estadoConfUsuRecDAO;
    private ITEstadoUsuarios estadoUsuariosDAO;
    private ITEstadoUspHistoria estadoUspHistoriaDAO;
    private ITUspHistoria uspHistoriaDAO;
    private ITMovimientos movimientosDAO;
    private ITPrestamoExpHistorial prestamoExpHistorialDAO;

    public GestionUsuariosImplBO() {
        usuariosDAO = new DaoUsuarios();
        personasDAO = new DaoPersonas();
        datosPersonasDAO = new DaoDatosPersonas();
        tipoDocumentoDAO = new DaoTipoDocumento();
        modulosDAO = new DaoModulos();
        recursosDAO = new DaoRecursos();
        confUsuRecDAO = new DaoConfUsuRec();
        estadoConfUsuRecDAO = new DaoEstadoConfUsuRec();
        estadoUsuariosDAO = new DaoEstadoUsuarios();
        estadoUspHistoriaDAO = new DaoEstadoUspHistoria();
        uspHistoriaDAO = new DaoUspHistoria();
        movimientosDAO = new DaoMovimientos();
        prestamoExpHistorialDAO = new DaoPrestamoExpHistorial();
    }

    @Override
    public void consultarUsuario(BeanGestionUsuarios bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            List<CivUsuarios> listaCivUsuario = new ArrayList<>();
            switch (bean.getTipoBusqueda()) {
                case 1:
                    listaCivUsuario = getUsuariosDAO().listarUsuarios(session, bean.getNombreUsuario().toUpperCase());
                    if (listaCivUsuario == null) {
                        throw new UsuariosException("No se encontro el usuario.", 3);
                    }
                    break;
            }
            if (listaCivUsuario != null && listaCivUsuario.size() > 0) {
                for (CivUsuarios civUsuarios : listaCivUsuario) {
                    Usuarios usuarios = new Usuarios(civUsuarios, civUsuarios.getCivEstadoUsuarios(), civUsuarios.getCivPersonas());
                    bean.getListadoUsuarios().add(usuarios);
                }
            }
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void actualizarContraseña(BeanGestionUsuarios bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            List list = getUsuariosDAO().consultar_HPAS(session, bean.getDetalleUsuario().getId());
            String passCifrada = DigestHandler.encryptSHA2(bean.getContraseñaConfirmacion());
            if (list.contains(passCifrada)) {
                throw new UsuariosException("Por seguridad debe usar una contraseña no registrada con anteriodad en el sistemas.", 3);
            }
            CivUsuarios civUsuarios = getUsuariosDAO().find(session, new BigDecimal(bean.getDetalleUsuario().getId()));
            civUsuarios.setUsuPass(DigestHandler.encryptSHA2(bean.getContraseñaConfirmacion()));
            getUsuariosDAO().update(session, civUsuarios);
            RequestContext requestContexts = RequestContext.getCurrentInstance();
            requestContexts.execute("$('#modalRestablecerContraseña').modal('hide')");
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void cargarDatosPersonas(BeanGestionUsuarios bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            if (bean != null) {
                if (bean.getDetallePersonaModal() != null) {
                    if (bean.getDetallePersonaModal().getId() != 0) {
                        List<CivDatosPersonas> listCivDatosPersonas = getDatosPersonasDAO().listarDatosPersonas(session, bean.getDetallePersonaModal().getId());
                        if (listCivDatosPersonas != null) {
                            for (CivDatosPersonas CivDatosPersona : listCivDatosPersonas) {
                                DatosPersonas datosPersonas = new DatosPersonas(CivDatosPersona, CivDatosPersona.getCivTipoDatosPersonas(), CivDatosPersona.getCivEstadoDatosPersonas());
                                bean.getDetallePersonaModal().getListDatosPersonas().add(datosPersonas);
                            }
                        }
                    }
                }
            }
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void cargarTipoDocumento(BeanGestionUsuarios bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            List<CivTipoDocumentos> listCivTipoDocumento = getTipoDocumentoDAO().listAll(session);
            for (CivTipoDocumentos civTipoDocumentos : listCivTipoDocumento) {
                TipoDocumentos tipoDocumentos = new TipoDocumentos(civTipoDocumentos);
                bean.getListTipoDocumento().add(tipoDocumentos);
            }
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void cargarEstadoUsuario(BeanGestionUsuarios bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            List<CivEstadoUsuarios> listCivEstadoUsuarios = getEstadoUsuariosDAO().findAll(session);
            if (listCivEstadoUsuarios != null) {
                for (CivEstadoUsuarios civEstadoUsuario : listCivEstadoUsuarios) {
                    EstadoUsuarios estadoUsuarios = new EstadoUsuarios(civEstadoUsuario);
                    bean.getListEstadoUsuarios().add(estadoUsuarios);
                }
            }
        } finally {
            session.flush();
            session.close();
        }

    }

    @Override
    public void cargarModulosByUsuario(BeanGestionUsuarios bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            List<CivModulos> listCivModulos = getModulosDAO().getModulosByUsuario(session, bean.getDetalleUsuario().getId());
            if (listCivModulos != null) {
                for (CivModulos civModulo : listCivModulos) {
                    List<CivRecursos> listCivRecursos = getRecursosDAO().getRecursos(session, civModulo.getModId().intValue(), bean.getDetalleUsuario().getId());
                    Modulos modulos = new Modulos(civModulo, civModulo.getCivEstadoModulos(), listCivRecursos);
                    bean.getListModulos().add(modulos);
                }
            }
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void cargarConfUsuRec(BeanGestionUsuarios bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            bean.setListConfUsuRec(new ArrayList<>());
            List<CivRecursos> listRecursos = getRecursosDAO().getRecursos(session, bean.getIdModuloSelecionado(), bean.getDetalleUsuario().getId());
            if (listRecursos != null) {
                for (CivRecursos civRecurso : listRecursos) {
                    List<CivConfUsuRec> listCivConfUsuRec = getConfUsuRecDAO().getConfUsuRec(session, civRecurso.getRecId().intValue(), bean.getDetalleUsuario().getId());
                    for (CivConfUsuRec civConfUsuRec : listCivConfUsuRec) {
                        ConfUsuRec confUsuRec = new ConfUsuRec(civConfUsuRec, civConfUsuRec.getCivEstadoConfusurec(), civConfUsuRec.getCivUsuarios(), civConfUsuRec.getCivRecursos());
                        bean.getListConfUsuRec().add(confUsuRec);
                    }
                }
                bean.setPnlBtnAceptar(true);
            } else {
                bean.setPnlBtnAceptar(false);
            }
        } finally {
            session.flush();
            session.close();
        }

    }

    @Override
    public void cargarHistorialConfUsuRec(BeanGestionUsuarios bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            List<CivConfUsuRec> listCivConfUsuRec = getConfUsuRecDAO().getConfUsuRecByUser(session, bean.getDetalleUsuario().getId());
            if (listCivConfUsuRec != null) {
                for (CivConfUsuRec civConfUsuRec : listCivConfUsuRec) {
                    ConfUsuRec confUsuRec = new ConfUsuRec(civConfUsuRec, civConfUsuRec.getCivEstadoConfusurec(), civConfUsuRec.getCivUsuarios(), civConfUsuRec.getCivRecursos());
                    bean.getListConfUsuRecByUser().add(confUsuRec);
                }
            }
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void cargarMovimientoByUser(BeanGestionUsuarios bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            List<CivMovimientos> listCivMovimientos = getMovimientosDAO().getMovimientoByUser(session, bean.getDetalleUsuario().getId());
            if (listCivMovimientos != null) {
                for (CivMovimientos civMovimiento : listCivMovimientos) {
                    Movimientos movimientos = new Movimientos(civMovimiento, civMovimiento.getCivEstadoMovimientos(), civMovimiento.getCivDeudas(), civMovimiento.getCivFasesTrabajos(), civMovimiento.getCivPersonas(), civMovimiento.getCivUsuarios());
                    bean.getListMovimientosByUser().add(movimientos);
                }
            }
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void cargarHistorialExpPrestado(BeanGestionUsuarios bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            List<CivPrestamoExpHistorial> listCivPrestamoExpHistorial = getPrestamoExpHistorialDAO().getCivPrestamoExpHistorial(session, bean.getDetalleUsuario().getId());
            if (listCivPrestamoExpHistorial != null) {
                for (CivPrestamoExpHistorial civPrestamoExpHistorial : listCivPrestamoExpHistorial) {
                    PrestamoExpHistorial prestamoExpHistorial = new PrestamoExpHistorial(civPrestamoExpHistorial, civPrestamoExpHistorial.getCivUsuarios(), civPrestamoExpHistorial.getCivDetalleExpedientes());
                    bean.getListPrestamoExpHistorial().add(prestamoExpHistorial);
                }
            }
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void cargarTodosModulos(BeanGestionUsuarios bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            bean.setListTodosModulos(new ArrayList<>());
            List<CivModulos> listCivModulos = getModulosDAO().findAll(session);
            if (listCivModulos != null) {
                for (CivModulos civModulo : listCivModulos) {
                    Modulos modulos = new Modulos(civModulo, civModulo.getCivEstadoModulos());
                    List<CivRecursos> listCivRecursos = getRecursosDAO().getRecursos(session, civModulo.getModId().intValue(), bean.getDetalleUsuario().getId());
                    if (listCivRecursos != null) {
                        for (CivRecursos CivRecurso : listCivRecursos) {
                            List<CivConfUsuRec> civConfUsuRec = getConfUsuRecDAO().getConfUsuRec(session, CivRecurso.getRecId().intValue());
                            Recursos recurso = new Recursos(CivRecurso);
                            recurso.setSelecionado(civConfUsuRec != null ? true : false);
                            modulos.getListRecurso().add(recurso);
                        }
                    }
                    bean.getListTodosModulos().add(modulos);
                }
            }
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void cargarRecursoByModulo(BeanGestionUsuarios bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            bean.setListRecursos(new ArrayList<>());
            List<CivRecursos> listRecursos = getRecursosDAO().getListCivRecursos(session, bean.getIdModuloSelecionado(), bean.getDetalleUsuario().getId());
            if (listRecursos != null) {
                for (CivRecursos civRecurso : listRecursos) {
                    Recursos recursos = new Recursos(civRecurso);
                    bean.getListRecursos().add(recursos);
                }
                bean.setPnlBtnAceptar(true);
            } else {
                bean.setPnlBtnAceptar(false);
            }
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void updateRecursoUsuario(BeanGestionUsuarios bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            int increment = -1;
            for (int i = 0; i < bean.getListConfUsuRec().size(); i++) {
                increment++;
                ConfUsuRec temporal = bean.getListConfUsuRec().get(i);
                if (temporal.isSelecionado() == true) {
                    if (increment == i) {
                        CivConfUsuRec civConfUsuRec = getConfUsuRecDAO().find(session, BigDecimal.valueOf(temporal.getConfusurecId()));

                        CivEstadoConfusurec civEstadoConfusurec = getEstadoConfUsuRecDAO().find(session, BigDecimal.valueOf(2));

                        civConfUsuRec.setCivEstadoConfusurec(civEstadoConfusurec);
                        getConfUsuRecDAO().update(session, civConfUsuRec);
                    }
                }
            }
            cargarTodosModulos(bean);
            transaction.commit();
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("$('#modalQuitarRecursoModulo').modal('hide')");
            throw new UsuariosException("Su accion fue realizada con exito.", 1);
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void guardarRecursoUsuario(BeanGestionUsuarios bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            int increment = -1;
            for (int i = 0; i < bean.getListRecursos().size(); i++) {
                increment++;
                if (bean.getListRecursos().get(i).isSelecionado() == true) {
                    if (increment == i) {
                        CivConfUsuRec civConfUsuRec = new CivConfUsuRec();
                        CivEstadoConfusurec civEstadoConfusurec = getEstadoConfUsuRecDAO().find(session, BigDecimal.valueOf(1));
                        CivRecursos civRecursos = getRecursosDAO().getRecursos(session, bean.getListRecursos().get(i).getRecId().intValue());
                        CivUsuarios civUsuarios = getUsuariosDAO().find(session, BigDecimal.valueOf(bean.getDetalleUsuario().getId()));

                        civConfUsuRec.setCivUsuarios(civUsuarios);
                        civConfUsuRec.setConfusurecFechaproceso(new Date());
                        civConfUsuRec.setCivEstadoConfusurec(civEstadoConfusurec);
                        civConfUsuRec.setCivRecursos(civRecursos);
                        getConfUsuRecDAO().create(session, civConfUsuRec);
                    }
                }
            }
            RequestContext requestContexts = RequestContext.getCurrentInstance();
            requestContexts.execute("$('#modalRecursoModulos').modal('hide')");
            transaction.commit();
            cargarTodosModulos(bean);
            throw new UsuariosException("Se agrego el recurso exitosamente, el usuario debe iniciar sesion pa ver los cambios.", 1);
        } finally {
            session.flush();
            session.close();
        }
    }

    public String generarNombreUsuario(String nombre, String apellido) {
        String userName = "";
        String ap = "";
        Random random = new Random();
        int x = random.nextInt(99);
        String apellido1 = apellido.toUpperCase();
        for (int i = 0; i < apellido1.length(); i++) {
            if (apellido1.charAt(i) != ' ') {
                ap += apellido1.charAt(i);
            }
        }
        String nombre1 = nombre.toUpperCase();
        userName = nombre1.charAt(0) + ap + x;
        return userName;
    }

    @Override
    public void consultarPersona(BeanGestionUsuarios bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            bean.setListPersonas(new ArrayList<>());
            CivPersonas civPersonas = getPersonasDAO().consultarPersonasByDocumento(session, bean.getTipoDocumentoPersona(), bean.getDocumentoPersona());
            if (civPersonas != null) {
                Personas persona = new Personas(civPersonas, civPersonas.getCivEstadoPersonas(), civPersonas.getCivTipoDocumentos());
                bean.getListPersonas().add(persona);
                bean.setNombreUsuarioNuevo(generarNombreUsuario(civPersonas.getPerNombre1(), civPersonas.getPerApellido1()));
            }
            if (civPersonas == null) {
                RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("$('#modalAgregarPersona').modal('show')");
                RequestContext requestContexts = RequestContext.getCurrentInstance();
                requestContexts.execute("$('#modalRegistrarUser').modal('hide')");
                throw new UsuariosException("No se encontro la persona en el sistema.", 2);
            }
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void registrarUsuario(BeanGestionUsuarios bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            CivPersonas civPersonas = getPersonasDAO().consultarPersonasByDocumento(session, bean.getTipoDocumentoPersona(), bean.getDocumentoPersona());
            CivUsuarios user = getUsuariosDAO().consultarUsuarioByNombre(session, bean.getNombreUsuarioNuevo().toUpperCase());
            if (user != null) {
                throw new UsuariosException("Ya existe un usuario con este nombre.", 2);
            }
            int idPersona = civPersonas.getPerId().intValue();
            CivUsuarios cu = getUsuariosDAO().getCivUsuario(session, idPersona);
            if (cu != null) {
                throw new UsuariosException("Ya existe un usuario registrado a esta persona.", 2);
            } else {
                CivUsuarios civUsuarios = new CivUsuarios();

                CivEstadoUsuarios civEstadoUsuarios = getEstadoUsuariosDAO().consultarModuloById(session, 3);
                civUsuarios.setUsuNombre(bean.getNombreUsuarioNuevo().toUpperCase());
                civUsuarios.setUsuPass(DigestHandler.encryptSHA2(bean.getDocumentoPersona()));
                civUsuarios.setUsuFechaproceso(new Date());
                civUsuarios.setCivEstadoUsuarios(civEstadoUsuarios);
                civUsuarios.setCivPersonas(civPersonas);
                getUsuariosDAO().create(session, civUsuarios);

                CivUspHistoria civUspHistoria = new CivUspHistoria();
                CivEstadouspHistoria civEstadouspHistoria = getEstadoUspHistoriaDAO().find(session, BigDecimal.valueOf(1));

                civUspHistoria.setPData(DigestHandler.encryptSHA2(bean.getDocumentoPersona()));
                civUspHistoria.setFechaProceso(new Date());
                civUspHistoria.setCivUsuarios(civUsuarios);
                civUspHistoria.setCivEstadouspHistoria(civEstadouspHistoria);
                getUspHistoriaDAO().create(session, civUspHistoria);
                transaction.commit();
                throw new UsuariosException("La contraseña por defecto es el numero del documento.", 1);
            }
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void actualizarUser(BeanGestionUsuarios bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            CivUsuarios civUsuarios = getUsuariosDAO().find(session, new BigDecimal(bean.getDetalleUsuario().getId()));
            CivEstadoUsuarios civEstadoUsuarios = getEstadoUsuariosDAO().find(session, new BigDecimal(bean.getDetalleUsuario().getEstadoUsuarios().getId()));
            civUsuarios.setCivEstadoUsuarios(civEstadoUsuarios);
            getUsuariosDAO().update(session, civUsuarios);
            transaction.commit();
            throw new UsuariosException("Se ha actualizado el estado del usuario.", 1);
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void generarContrasena(BeanGestionUsuarios bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            int numero;
            numero = (int) (Math.random() * 900) + 100;
            String contrasenia = "CobroCoactivo" + numero;
            bean.setContrasenaAleatoria(contrasenia);
            CivUsuarios civUsuarios = getUsuariosDAO().find(session, new BigDecimal(bean.getDetalleUsuario().getId()));
            CivEstadoUsuarios civEstadoUsuarios = new CivEstadoUsuarios();
            civEstadoUsuarios.setEstusuId(BigDecimal.valueOf(3));
            civUsuarios.setUsuPass(DigestHandler.encryptSHA2(contrasenia));
            civUsuarios.setCivEstadoUsuarios(civEstadoUsuarios);
            getUsuariosDAO().update(session, civUsuarios);
            CivEstadouspHistoria civEstadouspHistoria = getEstadoUspHistoriaDAO().find(session, BigDecimal.ONE);
            CivUspHistoria civUspHistoria = new CivUspHistoria(null, civUsuarios, civEstadouspHistoria, DigestHandler.encryptSHA2(contrasenia), new Date());
            // SE ACTULIZAN TODAS LAS CONTRASEÑA QUE A TENIDO EL USUARIO A ESTADO 2 (INACTIVO)
            List<CivUspHistoria> listCivUspHistoria = getUsuariosDAO().consultarEstado_HPAS(session, bean.getDetalleUsuario().getId());
            if (listCivUspHistoria != null) {
                List<CivUspHistoria> listCivUspHistorias = getUsuariosDAO().consultarEstado_HPAS(session, bean.getDetalleUsuario().getId());
                for (CivUspHistoria cuh : listCivUspHistorias) {
                    if (cuh.getCivEstadouspHistoria().getEstuspId().intValue() == 1) {
                        CivEstadouspHistoria civEstadoHistoria = getEstadoUspHistoriaDAO().find(session, new BigDecimal(2));
                        cuh.setCivEstadouspHistoria(civEstadoHistoria);
                        getUsuariosDAO().updateHisPass(session, cuh);
                    }
                }
            }
            //////////////////
            getUspHistoriaDAO().create(session, civUspHistoria);
            transaction.commit();
            throw new UsuariosException("Se ha restablecido la contraseña.", 1);
        } finally {
            session.flush();
            session.close();
        }

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
     * @return the personasDAO
     */
    public ITPersonas getPersonasDAO() {
        return personasDAO;
    }

    /**
     * @param personasDAO the personasDAO to set
     */
    public void setPersonasDAO(ITPersonas personasDAO) {
        this.personasDAO = personasDAO;
    }

    /**
     * @return the datosPersonasDAO
     */
    public ITDatosPersonas getDatosPersonasDAO() {
        return datosPersonasDAO;
    }

    /**
     * @param datosPersonasDAO the datosPersonasDAO to set
     */
    public void setDatosPersonasDAO(ITDatosPersonas datosPersonasDAO) {
        this.datosPersonasDAO = datosPersonasDAO;
    }

    /**
     * @return the tipoDocumentoDAO
     */
    public ITTipoDocumento getTipoDocumentoDAO() {
        return tipoDocumentoDAO;
    }

    /**
     * @param tipoDocumentoDAO the tipoDocumentoDAO to set
     */
    public void setTipoDocumentoDAO(ITTipoDocumento tipoDocumentoDAO) {
        this.tipoDocumentoDAO = tipoDocumentoDAO;
    }

    /**
     * @return the modulosDAO
     */
    public ITModulos getModulosDAO() {
        return modulosDAO;
    }

    /**
     * @param modulosDAO the modulosDAO to set
     */
    public void setModulosDAO(ITModulos modulosDAO) {
        this.modulosDAO = modulosDAO;
    }

    /**
     * @return the recursosDAO
     */
    public ITRecursos getRecursosDAO() {
        return recursosDAO;
    }

    /**
     * @param recursosDAO the recursosDAO to set
     */
    public void setRecursosDAO(ITRecursos recursosDAO) {
        this.recursosDAO = recursosDAO;
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

    /**
     * @return the estadoConfUsuRecDAO
     */
    public ITEstadoConfUsuRec getEstadoConfUsuRecDAO() {
        return estadoConfUsuRecDAO;
    }

    /**
     * @param estadoConfUsuRecDAO the estadoConfUsuRecDAO to set
     */
    public void setEstadoConfUsuRecDAO(ITEstadoConfUsuRec estadoConfUsuRecDAO) {
        this.estadoConfUsuRecDAO = estadoConfUsuRecDAO;
    }

    /**
     * @return the estadoUsuariosDAO
     */
    public ITEstadoUsuarios getEstadoUsuariosDAO() {
        return estadoUsuariosDAO;
    }

    /**
     * @param estadoUsuariosDAO the estadoUsuariosDAO to set
     */
    public void setEstadoUsuariosDAO(ITEstadoUsuarios estadoUsuariosDAO) {
        this.estadoUsuariosDAO = estadoUsuariosDAO;
    }

    /**
     * @return the uspHistoriaDAO
     */
    public ITUspHistoria getUspHistoriaDAO() {
        return uspHistoriaDAO;
    }

    /**
     * @param uspHistoriaDAO the uspHistoriaDAO to set
     */
    public void setUspHistoriaDAO(ITUspHistoria uspHistoriaDAO) {
        this.uspHistoriaDAO = uspHistoriaDAO;
    }

    /**
     * @return the estadoUspHistoriaDAO
     */
    public ITEstadoUspHistoria getEstadoUspHistoriaDAO() {
        return estadoUspHistoriaDAO;
    }

    /**
     * @param estadoUspHistoriaDAO the estadoUspHistoriaDAO to set
     */
    public void setEstadoUspHistoriaDAO(ITEstadoUspHistoria estadoUspHistoriaDAO) {
        this.estadoUspHistoriaDAO = estadoUspHistoriaDAO;
    }

    /**
     * @return the movimientosDAO
     */
    public ITMovimientos getMovimientosDAO() {
        return movimientosDAO;
    }

    /**
     * @param movimientosDAO the movimientosDAO to set
     */
    public void setMovimientosDAO(ITMovimientos movimientosDAO) {
        this.movimientosDAO = movimientosDAO;
    }

    /**
     * @return the prestamoExpHistorialDAO
     */
    public ITPrestamoExpHistorial getPrestamoExpHistorialDAO() {
        return prestamoExpHistorialDAO;
    }

    /**
     * @param prestamoExpHistorialDAO the prestamoExpHistorialDAO to set
     */
    public void setPrestamoExpHistorialDAO(ITPrestamoExpHistorial prestamoExpHistorialDAO) {
        this.prestamoExpHistorialDAO = prestamoExpHistorialDAO;
    }

}
