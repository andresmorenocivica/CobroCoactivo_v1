/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanGestionExpedientes;
import CobroCoactivo.Dao.DaoArchivoDetExpedientes;
import CobroCoactivo.Dao.DaoDetalleExpedientes;
import CobroCoactivo.Dao.DaoDetalleSolicitudes;
import CobroCoactivo.Dao.DaoEstadoDetalleSolicitudes;
import CobroCoactivo.Dao.DaoExpedientes;
import CobroCoactivo.Dao.DaoPersonas;
import CobroCoactivo.Dao.DaoPrestamoExpHistorial;
import CobroCoactivo.Dao.DaoSolicitudes;
import CobroCoactivo.Dao.DaoUsuarios;
import CobroCoactivo.Dao.ITArchivoDetExpedientes;
import CobroCoactivo.Dao.ITDetalleExpedientes;
import CobroCoactivo.Dao.ITDetalleSolicitudes;
import CobroCoactivo.Dao.ITEstadoDetalleSolicitudes;
import CobroCoactivo.Dao.ITExpedientes;
import CobroCoactivo.Dao.ITPersonas;
import CobroCoactivo.Dao.ITPrestamoExpHistorial;
import CobroCoactivo.Dao.ITSolicitudes;
import CobroCoactivo.Dao.ITUsuarios;
import CobroCoactivo.Exception.ExpedientesException;
import CobroCoactivo.Modelo.ArchivoDetExpedientes;
import CobroCoactivo.Modelo.DetalleExpedientes;
import CobroCoactivo.Modelo.DetalleSolicitudes;
import CobroCoactivo.Modelo.Expedientes;
import CobroCoactivo.Modelo.Solicitudes;
import CobroCoactivo.Persistencia.CivArchivoDetExpedientes;
import CobroCoactivo.Persistencia.CivDetalleExpedientes;
import CobroCoactivo.Persistencia.CivDetalleSolicitudes;
import CobroCoactivo.Persistencia.CivEstadoArchDetExp;
import CobroCoactivo.Persistencia.CivEstadoDetalleSolicitudes;
import CobroCoactivo.Persistencia.CivEstadoSolicitudes;
import CobroCoactivo.Persistencia.CivExpedientes;
import CobroCoactivo.Persistencia.CivPersonas;
import CobroCoactivo.Persistencia.CivPrestamoExpHistorial;
import CobroCoactivo.Persistencia.CivSolicitudes;
import CobroCoactivo.Persistencia.CivUsuarios;
import CobroCoactivo.Utility.HibernateUtil;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;

/**
 *
 * @author emadrid
 */
public class GestionExpedientesImpBO implements GestionExpedientesBO, Serializable {

    private ITExpedientes expedientesDAO;
    private ITDetalleExpedientes detalleExpedientesDAO;
    private ITArchivoDetExpedientes archivoDetExpedientesDAO;
    private ITUsuarios usuariosDAO;
    private ITSolicitudes solicitudesDAO;
    private ITDetalleSolicitudes detalleSolicitudesDAO;
    private ITPersonas personasDAO;
    private ITPrestamoExpHistorial prestamoExpHistorialDAO;
    private ITEstadoDetalleSolicitudes estadoDetalleSolicitudesDAO;

    public GestionExpedientesImpBO() {
        expedientesDAO = new DaoExpedientes();
        detalleExpedientesDAO = new DaoDetalleExpedientes();
        archivoDetExpedientesDAO = new DaoArchivoDetExpedientes();
        usuariosDAO = new DaoUsuarios();
        solicitudesDAO = new DaoSolicitudes();
        detalleSolicitudesDAO = new DaoDetalleSolicitudes();
        personasDAO = new DaoPersonas();
        prestamoExpHistorialDAO = new DaoPrestamoExpHistorial();
        estadoDetalleSolicitudesDAO = new DaoEstadoDetalleSolicitudes();
    }

    @Override
    public void crearExpediente(BeanGestionExpedientes bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            CivPersonas civPersonas = getPersonasDAO().consultarPersonasByDocumento(session, bean.getTipoDocumentoPersona(), bean.getDocumentoPersona());
            if (civPersonas != null) {
                Expedientes expedientes = new Expedientes();
                String nombreExpedientePersona = "";
                nombreExpedientePersona = expedientes.crearExpediente(civPersonas, getExpedientesDAO());
                throw new ExpedientesException("Se creo el expediente correctamente, la referencia del expediente es el N° de cedula.", 1);
            }
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void buscarExpediente(BeanGestionExpedientes bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            CivExpedientes civExpedientes = new CivExpedientes();
            bean.setListExpedientes(new ArrayList<>());
            switch (bean.getTipoBusqueda()) {
                case 1:
                    civExpedientes = getExpedientesDAO().getCivExpedientes(session, bean.getReferenciaExpediente());
                    break;
            }
            if (civExpedientes != null) {
                Expedientes expedientes = new Expedientes(civExpedientes, civExpedientes.getCivTipoExpedientes(), civExpedientes.getCivEstadoExpedientes());
                bean.getListExpedientes().add(expedientes);
            }
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void abrirExpediente(BeanGestionExpedientes bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            bean.setListDetalleExpedientes(new ArrayList<>());
            List<CivDetalleExpedientes> listCivDetalleExpedientes = getDetalleExpedientesDAO().getListCivDetalleExpediente(session, bean.getIdExpediente());
            if (listCivDetalleExpedientes != null) {
                for (CivDetalleExpedientes civDetalleExpediente : listCivDetalleExpedientes) {
                    DetalleExpedientes detalleExpedientes = new DetalleExpedientes(civDetalleExpediente, civDetalleExpediente.getCivExpedientes(), civDetalleExpediente.getCivEstadoDetalleExpedientes(), civDetalleExpediente.getCivTipoDetalleExpedientes());
                    bean.getListDetalleExpedientes().add(detalleExpedientes);
                }
            }
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void buscarArchivo(BeanGestionExpedientes bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            bean.setListArchivoDetExpedientes(new ArrayList<>());
            List<CivArchivoDetExpedientes> listCivArchivoDetExpedientes = getArchivoDetExpedientesDAO().getCivArchivoDetExpedientes(session, bean.getIdDetExpediente());
            if (listCivArchivoDetExpedientes != null) {
                for (CivArchivoDetExpedientes civArchivoDetExpediente : listCivArchivoDetExpedientes) {
                    ArchivoDetExpedientes archivoDetExpedientes = new ArchivoDetExpedientes(civArchivoDetExpediente, civArchivoDetExpediente.getCivEstadoArchDetExp(), civArchivoDetExpediente.getCivDetalleExpedientes());
                    bean.getListArchivoDetExpedientes().add(archivoDetExpedientes);
                }
            } else {
                throw new ExpedientesException("No hay archivo en esta carpeta, agrege archivo por favor.", 2);
            }
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void guardarArchivo(BeanGestionExpedientes bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            if (Paths.get(bean.getFile().getSubmittedFileName()).getFileName().toString().endsWith(".pdf")) {
                CivArchivoDetExpedientes civArchivoDetExpedientes = new CivArchivoDetExpedientes();
                CivEstadoArchDetExp civEstadoArchDetExp = new CivEstadoArchDetExp();
                civEstadoArchDetExp.setEstarcdetexpId(BigDecimal.ONE);
                CivDetalleExpedientes civDetalleExpedientes = getDetalleExpedientesDAO().find(session, new BigDecimal(bean.getIdDetExpediente()));
                civArchivoDetExpedientes.setArcdetexpNombre(Paths.get(bean.getFile().getSubmittedFileName()).getFileName().toString());
                civArchivoDetExpedientes.setArcdetexpFechaproceso(new Date());
                civArchivoDetExpedientes.setCivDetalleExpedientes(civDetalleExpedientes);
                civArchivoDetExpedientes.setArcdetexpUbicacion(civDetalleExpedientes.getDetexpUbicacion() + "/" + Paths.get(bean.getFile().getSubmittedFileName()).getFileName().toString());
                civArchivoDetExpedientes.setCivEstadoArchDetExp(civEstadoArchDetExp);
                InputStream stream = bean.getFile().getInputStream();
                Files.copy(stream, new File(civDetalleExpedientes.getDetexpUbicacion() + "/" + Paths.get(bean.getFile().getSubmittedFileName()).getFileName().toString()).toPath(), StandardCopyOption.REPLACE_EXISTING);
                getArchivoDetExpedientesDAO().create(session, civArchivoDetExpedientes);
                transaction.commit();
                RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("$('#addArchivo').modal('hide')");
                throw new ExpedientesException("Se agregado el archivo correctamente.", 1);
            } else {
                throw new ExpedientesException("Solo se puede cargar archivo PDF.", 2);
            }
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void updateArchivo(BeanGestionExpedientes bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            if (Paths.get(bean.getFile().getSubmittedFileName()).getFileName().toString().endsWith(".pdf")) {
                Transaction transaction = session.beginTransaction();
                CivArchivoDetExpedientes civArchivoDetExpedientes = getArchivoDetExpedientesDAO().find(session, new BigDecimal(bean.getArchivoDetExpedientes().getId()));
                civArchivoDetExpedientes.setArcdetexpFechaproceso(new Date());
                CivEstadoArchDetExp civEstadoArchDetExp = new CivEstadoArchDetExp();
                civEstadoArchDetExp.setEstarcdetexpId(BigDecimal.ONE);
                civArchivoDetExpedientes.setCivEstadoArchDetExp(civEstadoArchDetExp);
                civArchivoDetExpedientes.setArcdetexpNombre(Paths.get(bean.getFile().getSubmittedFileName()).getFileName().toString());
                civArchivoDetExpedientes.setArcdetexpUbicacion(civArchivoDetExpedientes.getCivDetalleExpedientes().getDetexpUbicacion() + "/" + Paths.get(bean.getFile().getSubmittedFileName()).getFileName().toString());
                InputStream stream = bean.getFile().getInputStream();
                Files.copy(stream, new File(civArchivoDetExpedientes.getCivDetalleExpedientes().getDetexpUbicacion() + "/" + Paths.get(bean.getFile().getSubmittedFileName()).getFileName().toString()).toPath(), StandardCopyOption.REPLACE_EXISTING);
                getArchivoDetExpedientesDAO().update(session, civArchivoDetExpedientes);
                transaction.commit();
                RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("$('#addArchivo').modal('hide')");
                throw new ExpedientesException("Se actualizo el archivo correctamente.", 1);
            }
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void mostrarExpedienteSelect(BeanGestionExpedientes bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            bean.setListDetalleExpdientesSelect(new ArrayList<>());
            int increment = -1;
            for (int i = 0; i < bean.getListDetalleExpedientes().size(); i++) {
                increment++;
                DetalleExpedientes detExp = bean.getListDetalleExpedientes().get(i);
                if (detExp.isSelecionado() == true) {
                    if (increment == i) {
                        CivDetalleExpedientes civDetalleExpedientes = getDetalleExpedientesDAO().find(session, BigDecimal.valueOf(detExp.getDetexpId()));
                        DetalleExpedientes detalleExpedientes = new DetalleExpedientes(civDetalleExpedientes);
                        bean.getListDetalleExpdientesSelect().add(detalleExpedientes);
                    }
                }
            }
            if (bean.getListDetalleExpdientesSelect().size() > 0) {
                bean.setPnlExpSelect(true);
            } else {
                bean.setPnlExpSelect(false);
            }
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void enviarSolicitud(BeanGestionExpedientes bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            CivSolicitudes civSolicitudes = new CivSolicitudes();
            CivEstadoSolicitudes civEstadoSolicitudes = new CivEstadoSolicitudes();
            civEstadoSolicitudes.setEstsolId(BigDecimal.valueOf(3));
            CivUsuarios civUsuarios = getUsuariosDAO().find(session, new BigDecimal(bean.getIdUser()));
            civSolicitudes.setSolFechaproceso(new Date());
            civSolicitudes.setSolDescripcion(generarReferencia());
            civSolicitudes.setCivUsuarios(civUsuarios);
            civSolicitudes.setCivEstadoSolicitudes(civEstadoSolicitudes);
            getSolicitudesDAO().create(session, civSolicitudes);

            CivDetalleSolicitudes civDetalleSolicitudes = new CivDetalleSolicitudes();
            for (int i = 0; i < bean.getListDetalleExpdientesSelect().size(); i++) {
                civDetalleSolicitudes = new CivDetalleSolicitudes();
                CivEstadoDetalleSolicitudes civEstadoDetalleSolicitudes = new CivEstadoDetalleSolicitudes();
                civEstadoDetalleSolicitudes.setEstdetsolId(BigDecimal.ONE);
                civDetalleSolicitudes.setDetsolFechaproceso(new Date());
                civDetalleSolicitudes.setCivSolicitudes(civSolicitudes);
                civDetalleSolicitudes.setCivEstadoDetalleSolicitudes(civEstadoDetalleSolicitudes);
                civDetalleSolicitudes.setDetsolDescripcion(bean.getListDetalleExpdientesSelect().get(i).getDetexpDescripcion());
                getDetalleSolicitudesDAO().create(session, civDetalleSolicitudes);
            }
            transaction.commit();
            if (civDetalleSolicitudes != null) {
                bean.setPnlSubcarpetas(false);
                bean.setPnlExpSelect(false);
                throw new ExpedientesException("Se ha enviado la solicitud.", 1);
            }
        } finally {
            session.flush();
            session.close();
        }
    }

    public String generarReferencia() {
        String referencia = "";
        int a;
        for (int i = 0; i < 6; i++) {
            if (i < 3) {    // 0,1,2 posiciones de numeros
                referencia = (int) (Math.random() * 9) + "" + referencia;
            } else {       // 3,4,5 posiciones de letras
                do {
                    a = (int) (Math.random() * 26 + 65);///
                } while (a == 65 || a == 69 || a == 73 || a == 79 || a == 85);
                char letra = (char) a;
                if (i == 4) {
                    referencia = referencia + letra;
                } else {
                    referencia = referencia + letra;
                }
            }
        }
        return referencia;
    }

    @Override
    public void cargarSolicitudes(BeanGestionExpedientes bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            bean.setListSolicitudes(new ArrayList<>());
            List<CivSolicitudes> listCivSolicitudes = getSolicitudesDAO().getCivSolicitudesPendientes(session);
            if (listCivSolicitudes != null) {
                for (CivSolicitudes civSolicitudes : listCivSolicitudes) {
                    Solicitudes solicitudes = new Solicitudes(civSolicitudes, civSolicitudes.getCivEstadoSolicitudes(), civSolicitudes.getCivUsuarios());
                    bean.getListSolicitudes().add(solicitudes);
                }
            }
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void buscarDetSolicitudes(BeanGestionExpedientes bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            bean.setListDetalleSolicitudes(new ArrayList<>());
            List<CivDetalleSolicitudes> listCivDetalleSolicitudes = getDetalleSolicitudesDAO().getCivDetalleSolicitudes(session, bean.getIdSolicitud());
            for (CivDetalleSolicitudes civDetalleSolicitudes : listCivDetalleSolicitudes) {
                DetalleSolicitudes detalleSolicitudes = new DetalleSolicitudes(civDetalleSolicitudes, civDetalleSolicitudes.getCivEstadoDetalleSolicitudes(), civDetalleSolicitudes.getCivSolicitudes());
                bean.getListDetalleSolicitudes().add(detalleSolicitudes);
            }
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void cargarDetSolicitudes(BeanGestionExpedientes bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            bean.setListDetalleSolicitudes(new ArrayList<>());
            List<CivDetalleSolicitudes> listCivDetalleSolicitudes = getDetalleSolicitudesDAO().getCivDetalleSolicitudes(session);
            if (listCivDetalleSolicitudes != null) {
                for (CivDetalleSolicitudes civDetalleSolicitudes : listCivDetalleSolicitudes) {
                    DetalleSolicitudes detalleSolicitudes = new DetalleSolicitudes(civDetalleSolicitudes, civDetalleSolicitudes.getCivEstadoDetalleSolicitudes(), civDetalleSolicitudes.getCivSolicitudes());
                    bean.getListDetalleSolicitudes().add(detalleSolicitudes);
                }
            }
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void aceptarSolicitudExpediente(BeanGestionExpedientes bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            int increment = -1;
            for (int i = 0; i < bean.getListDetalleSolicitudes().size(); i++) {
                increment++;
                if (bean.getListDetalleSolicitudes().get(i).isSelecionado() == true) {
                    if (increment == i) {
                        CivDetalleSolicitudes civDetalleSolicitudes = getDetalleSolicitudesDAO().find(session, new BigDecimal(bean.getListDetalleSolicitudes().get(i).getId()));
                        CivPrestamoExpHistorial civPrestamoExpHistorial = new CivPrestamoExpHistorial();
                        CivDetalleExpedientes civDetalleExpedientes = getDetalleExpedientesDAO().getCivDetalleExpedientes(session, bean.getListDetalleSolicitudes().get(i).getDescripcion());
                        CivUsuarios civUsuarios = getUsuariosDAO().consultarUsuarioBy(session, civDetalleSolicitudes.getCivSolicitudes().getCivUsuarios().getUsuId().intValue());
                        civPrestamoExpHistorial.setPreexphisFechaproceso(new Date());
                        civPrestamoExpHistorial.setCivDetalleExpedientes(civDetalleExpedientes);
                        civPrestamoExpHistorial.setCivUsuarios(civUsuarios);
                        getPrestamoExpHistorialDAO().create(session, civPrestamoExpHistorial);

                        CivEstadoDetalleSolicitudes civEstadoDetalleSolicitudes = getEstadoDetalleSolicitudesDAO().find(session, new BigDecimal(4));
                        civDetalleSolicitudes.setCivEstadoDetalleSolicitudes(civEstadoDetalleSolicitudes);
                        getDetalleSolicitudesDAO().update(session, civDetalleSolicitudes);

                        CivSolicitudes civSolicitudes = getSolicitudesDAO().find(session, new BigDecimal(bean.getIdSolicitud()));
                        CivEstadoSolicitudes civEstadoSolicitudes = new CivEstadoSolicitudes();
                        civEstadoSolicitudes.setEstsolId(new BigDecimal(4));
                        civSolicitudes.setCivEstadoSolicitudes(civEstadoSolicitudes);
                        getSolicitudesDAO().update(session, civSolicitudes);
                    }
                }
            }
            transaction.commit();
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("$('#modalSolicitudExpediente').modal('hide')");
            throw new ExpedientesException("Su accion se genero correctamente.", 1);
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void buscarPersona(BeanGestionExpedientes bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            CivPersonas civPersonas = getPersonasDAO().consultarPersonasByDocumento(session, bean.getTipoDocumentoPersona(), bean.getDocumentoPersona());
            if (civPersonas != null) {
                bean.setNombrePersona(civPersonas.getPerNombre1() + " " + civPersonas.getPerApellido1());
                bean.setPnlPersonaEncontrada(true);
            }
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void recibirExp(BeanGestionExpedientes bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            CivDetalleSolicitudes civDetalleSolicitudes = getDetalleSolicitudesDAO().find(session, new BigDecimal(bean.getDetalleSolicitudes().getId()));
            CivEstadoDetalleSolicitudes civEstadoDetalleSolicitudes = getEstadoDetalleSolicitudesDAO().find(session, BigDecimal.ONE);
            civDetalleSolicitudes.setCivEstadoDetalleSolicitudes(civEstadoDetalleSolicitudes);
            getDetalleSolicitudesDAO().update(session, civDetalleSolicitudes);
            transaction.commit();
            cargarDetSolicitudes(bean);
        } finally {
            session.flush();
            session.close();
        }
    }

    /**
     * @return the expedientesDAO
     */
    public ITExpedientes getExpedientesDAO() {
        return expedientesDAO;
    }

    /**
     * @param expedientesDAO the expedientesDAO to set
     */
    public void setExpedientesDAO(ITExpedientes expedientesDAO) {
        this.expedientesDAO = expedientesDAO;
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
     * @return the solicitudesDAO
     */
    public ITSolicitudes getSolicitudesDAO() {
        return solicitudesDAO;
    }

    /**
     * @param solicitudesDAO the solicitudesDAO to set
     */
    public void setSolicitudesDAO(ITSolicitudes solicitudesDAO) {
        this.solicitudesDAO = solicitudesDAO;
    }

    /**
     * @return the detalleSolicitudesDAO
     */
    public ITDetalleSolicitudes getDetalleSolicitudesDAO() {
        return detalleSolicitudesDAO;
    }

    /**
     * @param detalleSolicitudesDAO the detalleSolicitudesDAO to set
     */
    public void setDetalleSolicitudesDAO(ITDetalleSolicitudes detalleSolicitudesDAO) {
        this.detalleSolicitudesDAO = detalleSolicitudesDAO;
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

    /**
     * @return the estadoDetalleSolicitudesDAO
     */
    public ITEstadoDetalleSolicitudes getEstadoDetalleSolicitudesDAO() {
        return estadoDetalleSolicitudesDAO;
    }

    /**
     * @param estadoDetalleSolicitudesDAO the estadoDetalleSolicitudesDAO to set
     */
    public void setEstadoDetalleSolicitudesDAO(ITEstadoDetalleSolicitudes estadoDetalleSolicitudesDAO) {
        this.estadoDetalleSolicitudesDAO = estadoDetalleSolicitudesDAO;
    }

}
