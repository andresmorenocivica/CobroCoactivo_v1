/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanGestionExcepciones;
import CobroCoactivo.Dao.DaoArchivoDetExpedientes;
import CobroCoactivo.Dao.DaoDetalleExpedientes;
import CobroCoactivo.Dao.DaoDeudas;
import CobroCoactivo.Dao.DaoExcepciones;
import CobroCoactivo.Dao.DaoMovimientos;
import CobroCoactivo.Dao.DaoPersonas;
import CobroCoactivo.Dao.DaoUsuarios;
import CobroCoactivo.Dao.ITArchivoDetExpedientes;
import CobroCoactivo.Dao.ITDetalleExpedientes;
import CobroCoactivo.Dao.ITDeudas;
import CobroCoactivo.Dao.ITExcepciones;
import CobroCoactivo.Dao.ITMovimientos;
import CobroCoactivo.Dao.ITPersonas;
import CobroCoactivo.Dao.ITUsuarios;
import CobroCoactivo.Exception.ExcepcionesException;
import CobroCoactivo.Modelo.Deudas;
import CobroCoactivo.Modelo.Excepciones;
import CobroCoactivo.Modelo.FasesTrabajos;
import CobroCoactivo.Modelo.Movimientos;
import CobroCoactivo.Persistencia.CivArchivoDetExpedientes;
import CobroCoactivo.Persistencia.CivDetalleExpedientes;
import CobroCoactivo.Persistencia.CivDeudas;
import CobroCoactivo.Persistencia.CivEstadoArchDetExp;
import CobroCoactivo.Persistencia.CivEstadoExcepcion;
import CobroCoactivo.Persistencia.CivExcepciones;
import CobroCoactivo.Persistencia.CivMovimientos;
import CobroCoactivo.Persistencia.CivPersonas;
import CobroCoactivo.Persistencia.CivTipoExcepcion;
import CobroCoactivo.Persistencia.CivUsuarios;
import CobroCoactivo.Utility.HibernateUtil;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author emadridp
 */
public class GestionExcepcionesImpBo implements GestionExcepcionesBO {

    private ITExcepciones excepcionesDAO;
    private ITDeudas deudasDAO;
    private ITPersonas personasDAO;
    private ITUsuarios usuariosDAO;
    private ITMovimientos movimientosDAO;
    private ITDetalleExpedientes detalleExpedientesDAO;
    private ITArchivoDetExpedientes archivoDetExpedientesDAO;

    public GestionExcepcionesImpBo() {
        excepcionesDAO = new DaoExcepciones();
        deudasDAO = new DaoDeudas();
        personasDAO = new DaoPersonas();
        usuariosDAO = new DaoUsuarios();
        movimientosDAO = new DaoMovimientos();
        detalleExpedientesDAO = new DaoDetalleExpedientes();
        archivoDetExpedientesDAO = new DaoArchivoDetExpedientes();
    }

    @Override
    public void buscarExcepcion(BeanGestionExcepciones bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            bean.setListaExcepciones(new ArrayList<>());
            CivExcepciones civExcepciones = getExcepcionesDAO().getExcepciones(session, bean.getNumRadicado());
            if (civExcepciones != null) {
                Excepciones excepciones = new Excepciones(civExcepciones, civExcepciones.getCivUsuarios(), civExcepciones.getCivTipoExcepcion(), civExcepciones.getCivMovimientos(), civExcepciones.getCivEstadoExcepcion(), civExcepciones.getCivArchivoDetExpedientes());
                bean.getListaExcepciones().add(excepciones);
                bean.setPnlListaExcepciones(true);
            } else {
                throw new ExcepcionesException("No se encontro ninguna informacion.", 2);
            }
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void buscarPersona(BeanGestionExcepciones bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            bean.setListaDeudas(new ArrayList<>());
            if (bean.getTipoDocumentoPersona() != 0) {
                CivPersonas civPersonas = getPersonasDAO().consultarPersonasByDocumento(session, bean.getTipoDocumentoPersona(), bean.getDocumentoPersona());
                if (bean.isPnlFormulario() == true) {
                    bean.setPnlFormulario(false);
                }
                if (civPersonas != null) {
                    List<CivDeudas> listaCivDeudas = getDeudasDAO().listarDeudas(session, civPersonas.getPerId().intValue());
                    if (listaCivDeudas != null) {
                        for (CivDeudas civDeuda : listaCivDeudas) {
                            Deudas deudas = new Deudas(civDeuda);
                            bean.getListaDeudas().add(deudas);
                        }
                        bean.setPnlListaDeuda(true);
                    } else {
                        throw new ExcepcionesException("La persona no tiene deuda en el sistema.", 2);
                    }
                } else {
                    throw new ExcepcionesException("No existe la persona en nuestro sistema.", 2);
                }
            } else {
                throw new ExcepcionesException("Debe seleccionar un tipo de documento.", 2);
            }
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void guardarExcepcion(BeanGestionExcepciones bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            if (Paths.get(bean.getFile().getSubmittedFileName()).getFileName().toString().endsWith(".pdf")) {
                List<Deudas> list = bean.getListaDeudasSelecionada();
                CivDetalleExpedientes civDetalleExpedientes = new CivDetalleExpedientes();
                CivExcepciones civExcepciones = new CivExcepciones();
                for (int i = 0; i < list.size(); i++) {
                    civDetalleExpedientes = getDetalleExpedientesDAO().getCivDetalleExpedientes(session, list.get(i).getReferencia());
                    if (civDetalleExpedientes != null) {
                        CivArchivoDetExpedientes civArchivoDetExpedientes = new CivArchivoDetExpedientes();
                        CivEstadoArchDetExp estadoArchDetExp = new CivEstadoArchDetExp();
                        estadoArchDetExp.setEstarcdetexpId(BigDecimal.ONE);
                        civArchivoDetExpedientes.setArcdetexpNombre(Paths.get(bean.getFile().getSubmittedFileName()).getFileName().toString());
                        civArchivoDetExpedientes.setArcdetexpFechaproceso(new Date());
                        civArchivoDetExpedientes.setCivDetalleExpedientes(civDetalleExpedientes);
                        civArchivoDetExpedientes.setCivEstadoArchDetExp(estadoArchDetExp);
                        civArchivoDetExpedientes.setArcdetexpUbicacion(civDetalleExpedientes.getDetexpUbicacion() + "/" + Paths.get(bean.getFile().getSubmittedFileName()).getFileName().toString());
                        File folderDeudaSelecionada = new File(civDetalleExpedientes.getDetexpUbicacion());
                        if (!folderDeudaSelecionada.exists()) {
                            folderDeudaSelecionada.mkdirs();
                        }
                        InputStream stream = bean.getFile().getInputStream();
                        Files.copy(stream, new File(civDetalleExpedientes.getDetexpUbicacion() + "/" + Paths.get(bean.getFile().getSubmittedFileName()).getFileName().toString()).toPath(), StandardCopyOption.REPLACE_EXISTING);
                        getArchivoDetExpedientesDAO().create(session, civArchivoDetExpedientes);

                        CivEstadoExcepcion civEstadoExcepcion = new CivEstadoExcepcion();
                        civEstadoExcepcion.setEstexcId(BigDecimal.valueOf(3));
                        CivTipoExcepcion civTipoExcepcion = new CivTipoExcepcion();
                        civTipoExcepcion.setTipexcId(BigDecimal.ONE);
                        CivUsuarios civUsuarios = getUsuariosDAO().consultarUsuarioBy(session, new Integer(bean.getIdUser()));
                        CivMovimientos civMovimientos = getMovimientosDAO().find(session, new BigDecimal(bean.getIdMovimiento()));
                        civExcepciones.setExcFechaproceso(new Date());
                        civExcepciones.setExcNumeroRadicado(bean.getNumRadicado());
                        civExcepciones.setExcFechaRadicado(bean.getFechaRadicado());
                        civExcepciones.setCivEstadoExcepcion(civEstadoExcepcion);
                        civExcepciones.setCivTipoExcepcion(civTipoExcepcion);
                        civExcepciones.setCivUsuarios(civUsuarios);
                        civExcepciones.setCivMovimientos(civMovimientos);
                        civExcepciones.setCivArchivoDetExpedientes(civArchivoDetExpedientes);
                        getExcepcionesDAO().create(session, civExcepciones);
                        transaction.commit();
                        bean.init();
                    }
                }
                if (civExcepciones != null) {
                    throw new ExcepcionesException("Se ha guardado la excepcion en el sistema.", 1);
                }
            } else {
                throw new ExcepcionesException("El archivo tiene que ser .PDF", 2);
            }
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void llenarListaDeudaSeleccionada(BeanGestionExcepciones bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            bean.setListaDeudasSelecionada(new ArrayList<>());
            bean.setListaMovimientos(new ArrayList<>());
            int increment = -1;
            for (int i = 0; i < bean.getListaDeudas().size(); i++) {
                increment++;
                if (bean.getListaDeudas().get(i).isSeleccionado() == true) {
                    CivDeudas civDeudas = getDeudasDAO().find(session, new BigDecimal(bean.getListaDeudas().get(i).getId()));
                    Deudas deudas = new Deudas(civDeudas);
                    bean.getListaDeudasSelecionada().add(deudas);
                    List<HashMap> listaCivMovimientos = getMovimientosDAO().getMovimientosByDeuda(session, bean.getListaDeudas().get(i).getId());
                    if (listaCivMovimientos != null) {
                        for (HashMap hash : listaCivMovimientos) {
                            Movimientos movimientos = new Movimientos();
                            movimientos.setId(new Integer(hash.get("MOV_ID").toString()));
                            FasesTrabajos fasesTrabajos = new FasesTrabajos();
                            fasesTrabajos.setDescripcion(hash.get("FASTRA_DESCRIPCION").toString());
                            fasesTrabajos.setId(new Integer(hash.get("FASTRA_ID").toString()));
                            movimientos.setFasesTrabajos(fasesTrabajos);
                            bean.getListaMovimientos().add(movimientos);
                        }
                    }
                }
            }
        } finally {
            session.flush();
            session.close();
        }
    }

    /**
     * @return the excepcionesDAO
     */
    public ITExcepciones getExcepcionesDAO() {
        return excepcionesDAO;
    }

    /**
     * @param excepcionesDAO the excepcionesDAO to set
     */
    public void setExcepcionesDAO(ITExcepciones excepcionesDAO) {
        this.excepcionesDAO = excepcionesDAO;
    }

    /**
     * @return the deudasDAO
     */
    public ITDeudas getDeudasDAO() {
        return deudasDAO;
    }

    /**
     * @param deudasDAO the deudasDAO to set
     */
    public void setDeudasDAO(ITDeudas deudasDAO) {
        this.deudasDAO = deudasDAO;
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
     * @return the detalleExpedientesDAO
     */
    public ITDetalleExpedientes getDetalleExpedientesDAO() {
        return detalleExpedientesDAO;
    }

    /**
     * @param detalleExpedientesDAO the detalleExpedientesDAO to set
     */
    public void setDetalleExpedientesDAO(ITDetalleExpedientes detalleExpedientesDAO) {
        this.detalleExpedientesDAO = detalleExpedientesDAO;
    }

    /**
     * @return the archivoDetExpedientesDAO
     */
    public ITArchivoDetExpedientes getArchivoDetExpedientesDAO() {
        return archivoDetExpedientesDAO;
    }

    /**
     * @param archivoDetExpedientesDAO the archivoDetExpedientesDAO to set
     */
    public void setArchivoDetExpedientesDAO(ITArchivoDetExpedientes archivoDetExpedientesDAO) {
        this.archivoDetExpedientesDAO = archivoDetExpedientesDAO;
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

}
