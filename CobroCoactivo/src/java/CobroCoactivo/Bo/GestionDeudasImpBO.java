/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanGestionDeudas;
import CobroCoactivo.Dao.DaoCobroDeudas;
import CobroCoactivo.Dao.DaoDetalleCobroDeudas;
import CobroCoactivo.Dao.DaoDetalleExpedientes;
import CobroCoactivo.Dao.DaoDeudas;
import CobroCoactivo.Dao.DaoEstadoDeudas;
import CobroCoactivo.Dao.DaoExpedientes;
import CobroCoactivo.Dao.DaoPersonas;
import CobroCoactivo.Dao.DaoPlanTrabajo;
import CobroCoactivo.Dao.DaoTipoDetalleCobro;
import CobroCoactivo.Dao.DaoTipoDeudas;
import CobroCoactivo.Dao.DaoTipoDocumento;
import CobroCoactivo.Dao.DaoValorTipoDetalleCobro;
import CobroCoactivo.Dao.ITCobroDeudas;
import CobroCoactivo.Dao.ITDetalleCobroDeudas;
import CobroCoactivo.Dao.ITDetalleExpedientes;
import CobroCoactivo.Dao.ITDeudas;
import CobroCoactivo.Dao.ITEstadoDeudas;
import CobroCoactivo.Dao.ITExpedientes;
import CobroCoactivo.Dao.ITPersonas;
import CobroCoactivo.Dao.ITPlanTrabajo;
import CobroCoactivo.Dao.ITTipoDetalleCobro;
import CobroCoactivo.Dao.ITTipoDeudas;
import CobroCoactivo.Dao.ITTipoDocumento;
import CobroCoactivo.Dao.ITValorTipoDetalleCobro;
import CobroCoactivo.Exception.DeudasException;
import CobroCoactivo.Modelo.CobroDeudas;
import CobroCoactivo.Persistencia.CivDeudas;
import CobroCoactivo.Modelo.Deudas;
import CobroCoactivo.Modelo.Expedientes;
import CobroCoactivo.Modelo.TipoDeudas;
import CobroCoactivo.Persistencia.CivCobroDeudas;
import CobroCoactivo.Persistencia.CivDetalleCobroDeudas;
import CobroCoactivo.Persistencia.CivDetalleExpedientes;
import CobroCoactivo.Persistencia.CivEstadoDetalleExpedientes;
import CobroCoactivo.Persistencia.CivEstadoDeudas;
import CobroCoactivo.Persistencia.CivExpedientes;
import CobroCoactivo.Persistencia.CivPersonas;
import CobroCoactivo.Persistencia.CivPlanTrabajos;
import CobroCoactivo.Persistencia.CivTipoDetalleCobro;
import CobroCoactivo.Persistencia.CivTipoDetalleExpedientes;
import CobroCoactivo.Persistencia.CivTipoDeudas;
import CobroCoactivo.Persistencia.CivTipoDocumentos;
import CobroCoactivo.Persistencia.CivValorTipoDetalleCobro;
import CobroCoactivo.Utility.HibernateUtil;
import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author emadrid
 */
public class GestionDeudasImpBO implements GestionDeudasBO, Serializable {

    private ITDeudas deudasDAO;
    private ITPersonas personasDAO;
    private ITTipoDeudas tipoDeudasDAO;
    private ITCobroDeudas cobroDeudasDAO;
    private ITDetalleCobroDeudas detalleCobroDeudasDAO;
    private ITTipoDetalleCobro tipoDetalleCobroDAO;
    private ITValorTipoDetalleCobro valorTipoDetalleCobroDAO;
    private ITEstadoDeudas estadoDeudasDAO;
    private ITPlanTrabajo planTrabajoDAO;
    private ITTipoDocumento tipoDocumentoDAO;
    private ITExpedientes expedientesDAO;
    private ITDetalleExpedientes detalleExpedientesDAO;

    public GestionDeudasImpBO() {
        deudasDAO = new DaoDeudas();
        personasDAO = new DaoPersonas();
        tipoDeudasDAO = new DaoTipoDeudas();
        cobroDeudasDAO = new DaoCobroDeudas();
        tipoDetalleCobroDAO = new DaoTipoDetalleCobro();
        detalleCobroDeudasDAO = new DaoDetalleCobroDeudas();
        valorTipoDetalleCobroDAO = new DaoValorTipoDetalleCobro();
        estadoDeudasDAO = new DaoEstadoDeudas();
        planTrabajoDAO = new DaoPlanTrabajo();
        tipoDocumentoDAO = new DaoTipoDocumento();
        detalleExpedientesDAO = new DaoDetalleExpedientes();
        expedientesDAO = new DaoExpedientes();
    }

    @Override
    public void cargarListaTipoDeudas(BeanGestionDeudas bean) throws Exception {
        List<CivTipoDeudas> listCivTipoDeudas = getTipoDeudasDAO().listAll();
        for (CivTipoDeudas CivTipoDeuda : listCivTipoDeudas) {
            TipoDeudas tipoDeudas = new TipoDeudas(CivTipoDeuda);
            bean.getListTipoDeudas().add(tipoDeudas);
        }
    }

    @Override
    public void cargarListaCobroDeudas(BeanGestionDeudas bean) throws Exception {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            List<CivCobroDeudas> listCivCobroDeudas = getCobroDeudasDAO().findAll(session);
            for (CivCobroDeudas CivCobroDeuda : listCivCobroDeudas) {
                CobroDeudas cobroDeudas = new CobroDeudas(CivCobroDeuda);
                switch (cobroDeudas.getDescripcion()) {
                    case "DIFICIL":
                        cobroDeudas.setColorDificultad("btn btn-danger");
                        break;
                    case "MEDIANO":
                        cobroDeudas.setColorDificultad("btn btn-warning");
                        break;
                    case "FACIL":
                        cobroDeudas.setColorDificultad("btn btn-success");
                        break;
                }
                bean.getListCobroDeudas().add(cobroDeudas);
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.flush();
                session.close();
            }
        }
    }

    @Override
    public void clasificacionDeudas(BeanGestionDeudas bean) throws Exception {

        bean.setListDeudas(new ArrayList<>());
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            List<CivDeudas> listCivDeudas = getDeudasDAO().findAll(session);
            if (listCivDeudas != null) {
                if (listCivDeudas.size() > 0) {
                    int i = 0;
                    for (CivDeudas civDeuda : listCivDeudas) {
                        CivPersonas civPersonas = getPersonasDAO().consultarPersonasById(session, civDeuda.getCivPersonas().getPerId().intValue());
                        CivPlanTrabajos civPlanTrabajos = getPlanTrabajoDAO().getPlanTrabajo(session, civDeuda.getCivPlanTrabajos().getPlatraId().intValue());
                        CivEstadoDeudas civEstadoDeudas = getEstadoDeudasDAO().getEstadoDeudas(session, civDeuda.getCivEstadoDeudas().getEstdeuId());
                        Deudas deudas = new Deudas(civDeuda, civEstadoDeudas, civPlanTrabajos, civDeuda.getCivTipoDeudas(), civPersonas);
                        bean.getListDeudas().add(deudas);
                        i++;
                    }//Next
                }//End If
            }//End If

        } finally {
            if (session != null && session.isOpen()) {
                session.flush();
                session.close();
            }
        }//Try -> Finally
    }

    @Override
    public void buscarDeudas(BeanGestionDeudas bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            List<CivDeudas> listCivDeudas = new ArrayList<>();
            bean.setListDeudas(new ArrayList<>());
            switch (bean.getTipoBusqueda()) {
                case 1:
                    listCivDeudas = getDeudasDAO().listarDeudasByRefencia(session, bean.getReferenciaDeuda().toUpperCase());
                    break;
                case 2:
                    listCivDeudas = getDeudasDAO().listarDeudasByFechaAdquisicion(session, bean.getFechaCargueInicial(), bean.getFechaCargueFinal());
                    break;

                case 3:
                    listCivDeudas = getDeudasDAO().listarDeudasByFechaDeuda(session, bean.getFechaDeudaInicial(), bean.getFechaDeudaFinal());
                    break;
                case 4:
                    listCivDeudas = getDeudasDAO().listarDeudasByTipo(session, bean.getTipoDeudas());
                    break;
            }
            if (listCivDeudas == null) {
                throw new DeudasException("No se encontro niguna informaciòn.", 2);
            }
            if (listCivDeudas != null) {
                if (listCivDeudas.size() > 0) {
                    int i = 0;
                    for (CivDeudas civDeuda : listCivDeudas) {
                        CivDetalleExpedientes civDetalleExpedientes = getDetalleExpedientesDAO().getCivDetalleExpedientes(session, civDeuda.getDeuRefencia());
                        if (civDetalleExpedientes != null) {
                            bean.setBtnCrearExp(false);
                        }
                        CivPersonas civPersonas = getPersonasDAO().consultarPersonasById(session, civDeuda.getCivPersonas().getPerId().intValue());
                        CivTipoDocumentos civTipoDocumentos = getTipoDocumentoDAO().getTipoDocumento(session, civPersonas.getCivTipoDocumentos().getTipdocId());
                        civPersonas.setCivTipoDocumentos(civTipoDocumentos);
                        CivPlanTrabajos civPlanTrabajos = getPlanTrabajoDAO().getPlanTrabajo(session, civDeuda.getCivPlanTrabajos().getPlatraId().intValue());
                        CivEstadoDeudas civEstadoDeudas = getEstadoDeudasDAO().getEstadoDeudas(session, civDeuda.getCivEstadoDeudas().getEstdeuId());
                        CivCobroDeudas civCobroDeudas = null;
                        if (civDeuda.getCivCobroDeudas() != null) {
                            civCobroDeudas = getCobroDeudasDAO().getCobroDeudas(session, civDeuda.getCivCobroDeudas().getCobdeuId().intValue());
                        }
                        Deudas deudas = new Deudas(civDeuda, civEstadoDeudas, civPlanTrabajos, civDeuda.getCivTipoDeudas(), civPersonas, civCobroDeudas);
                        if (civCobroDeudas != null) {
                            switch (deudas.getCobroDeudas().getDescripcion()) {
                                case "FACIL":
                                    deudas.getCobroDeudas().setColorDificultad("label label-primary");
                                    break;
                                case "MEDIANO":
                                    deudas.getCobroDeudas().setColorDificultad("label label-warning");
                                    break;
                                case "DIFICIL":
                                    deudas.getCobroDeudas().setColorDificultad("label label-danger");
                                    break;
                            }
                        }
                        bean.getListDeudas().add(deudas);
                        i++;
                    }
                }
            }
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void actualizarDeudaCargada(BeanGestionDeudas bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            CivDeudas civDeudas = getDeudasDAO().find(session, new BigDecimal(bean.getDeudaSeleccionada().getId()));
            CivCobroDeudas civCobroDeudas = getCobroDeudasDAO().getCobroDeudas(session, bean.getCobroDeudasSeleccionado().getId());
            CivTipoDeudas civTipoDeudas = getTipoDeudasDAO().find(session, new BigDecimal(bean.getDeudaSeleccionada().getTipoDeudas().getId()));
            CivEstadoDeudas civEstadoDeudas = getEstadoDeudasDAO().getEstadoDeudas(session, new BigDecimal(bean.getDeudaSeleccionada().getEstadoDeudas().getId()));
            CivPlanTrabajos civPlanTrabajos = getPlanTrabajoDAO().getPlanTrabajo(session, bean.getDeudaSeleccionada().getPlanTrabajoDeuda().getId());
            CivPersonas civPersonas = getPersonasDAO().consultarPersonasById(session, bean.getDeudaSeleccionada().getPersonas().getId());
            civDeudas.setCivPlanTrabajos(civPlanTrabajos);
            civDeudas.setCivPersonas(civPersonas);
            civDeudas.setCivEstadoDeudas(civEstadoDeudas);
            civDeudas.setCivTipoDeudas(civTipoDeudas);
            civDeudas.setCivCobroDeudas(civCobroDeudas);
            getDeudasDAO().update(session, civDeudas);
            CobroDeudas cobroDeudas = new CobroDeudas(civCobroDeudas);
            bean.getDeudaSeleccionada().setCobroDeudas(cobroDeudas);
            switch (civCobroDeudas.getCobdeuDescripcion()) {
                case "FACIL":
                    bean.getDeudaSeleccionada().getCobroDeudas().setColorDificultad("label label-primary");
                    break;
                case "MEDIANO":
                    bean.getDeudaSeleccionada().getCobroDeudas().setColorDificultad("label label-warning");
                    break;
                case "DIFICIL":
                    bean.getDeudaSeleccionada().getCobroDeudas().setColorDificultad("label label-danger");
                    break;
            }
            transaction.commit();
        } finally {
            session.flush();
            session.close();

        }
    }

    @Override
    public Deudas cargarProcentajeCobro(Deudas deudas) throws Exception {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            List<CivCobroDeudas> listaCivCobroDeudas = getCobroDeudasDAO().findAll(session);
            for (CivCobroDeudas civCobroDeudas : listaCivCobroDeudas) {
                List<CivDetalleCobroDeudas> listCivDetalleCobroDeudas = getDetalleCobroDeudasDAO().listarDetalleCobroDeudas(civCobroDeudas.getCobdeuId().intValue());
                for (CivDetalleCobroDeudas civDetalleCobroDeudas : listCivDetalleCobroDeudas) {
                    CivValorTipoDetalleCobro civValorTipoDetalleCobro = getValorTipoDetalleCobroDAO().cargarValorDetalle(civDetalleCobroDeudas.getCivValorTipoDetalleCobro().getValtipdetcobId().intValue());
                    CivTipoDetalleCobro civTipoDetalleCobro = getTipoDetalleCobroDAO().cargarTipoDetalleCobro(civValorTipoDetalleCobro.getCivTipoDetalleCobro().getTipdetcobId().intValue());

                }
            }
            return deudas;
        } finally {
            if (session != null && session.isOpen()) {
                session.flush();
                session.close();
            }
        }
    }

    @Override
    public void crearExpedienteDeuda(BeanGestionDeudas bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            CivExpedientes civExpedientes = getExpedientesDAO().getCivExpedientes(session, bean.getDeudas().getPersonas().getDocumento());
            if (civExpedientes != null) {
                Expedientes expedientes = new Expedientes(civExpedientes);
                String folderExpedienteDeuda = "";
                folderExpedienteDeuda = expedientes.getUbicacion() + "/" + bean.getDeudas().getReferencia();
                File folderDeuda = new File(folderExpedienteDeuda);
                if (!folderDeuda.exists()) {
                    folderDeuda.mkdirs();
                    CivDeudas civDeudas = getDeudasDAO().find(session, new BigDecimal(bean.getDeudas().getId()));
                    CivDetalleExpedientes civDetalleExpedientes = new CivDetalleExpedientes();
                    civDetalleExpedientes.setDetexpFechaproceso(new Date());
                    civDetalleExpedientes.setCivDeudas(civDeudas);
                    civDetalleExpedientes.setDetexpDescripcion(bean.getDeudas().getReferencia());
                    CivEstadoDetalleExpedientes civEstadoDetalleExpedientes = new CivEstadoDetalleExpedientes();
                    civEstadoDetalleExpedientes.setEstdetexpId(BigDecimal.ONE);
                    civDetalleExpedientes.setCivEstadoDetalleExpedientes(civEstadoDetalleExpedientes);
                    CivTipoDetalleExpedientes civTipoDetalleExpedientes = new CivTipoDetalleExpedientes();
                    civTipoDetalleExpedientes.setTipdetexpId(BigDecimal.ONE);
                    civDetalleExpedientes.setCivTipoDetalleExpedientes(civTipoDetalleExpedientes);
                    civDetalleExpedientes.setCivExpedientes(civExpedientes);
                    civDetalleExpedientes.setDetexpUbicacion(folderExpedienteDeuda);
                    getDetalleExpedientesDAO().create(session, civDetalleExpedientes);
                    throw new DeudasException("Se ha creado el expediente a la deuda.", 1);
                }
            } else {
                throw new DeudasException("La persona que esta asociada esta deuda no tiene expediente.", 3);
            }
            transaction.commit();
        } finally {
            session.flush();
            session.close();
        }
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
     * @return the tipoDeudasDAO
     */
    public ITTipoDeudas getTipoDeudasDAO() {
        return tipoDeudasDAO;
    }

    /**
     * @param tipoDeudasDAO the tipoDeudasDAO to set
     */
    public void setTipoDeudasDAO(ITTipoDeudas tipoDeudasDAO) {
        this.tipoDeudasDAO = tipoDeudasDAO;
    }

    /**
     * @return the cobroDeudasDAO
     */
    public ITCobroDeudas getCobroDeudasDAO() {
        return cobroDeudasDAO;
    }

    /**
     * @param cobroDeudasDAO the cobroDeudasDAO to set
     */
    public void setCobroDeudasDAO(ITCobroDeudas cobroDeudasDAO) {
        this.cobroDeudasDAO = cobroDeudasDAO;
    }

    /**
     * @return the detalleCobroDeudasDAO
     */
    public ITDetalleCobroDeudas getDetalleCobroDeudasDAO() {
        return detalleCobroDeudasDAO;
    }

    /**
     * @param detalleCobroDeudasDAO the detalleCobroDeudasDAO to set
     */
    public void setDetalleCobroDeudasDAO(ITDetalleCobroDeudas detalleCobroDeudasDAO) {
        this.detalleCobroDeudasDAO = detalleCobroDeudasDAO;
    }

    /**
     * @return the tipoDetalleCobroDAO
     */
    public ITTipoDetalleCobro getTipoDetalleCobroDAO() {
        return tipoDetalleCobroDAO;
    }

    /**
     * @param tipoDetalleCobroDAO the tipoDetalleCobroDAO to set
     */
    public void setTipoDetalleCobroDAO(ITTipoDetalleCobro tipoDetalleCobroDAO) {
        this.tipoDetalleCobroDAO = tipoDetalleCobroDAO;
    }

    /**
     * @return the valorTipoDetalleCobroDAO
     */
    public ITValorTipoDetalleCobro getValorTipoDetalleCobroDAO() {
        return valorTipoDetalleCobroDAO;
    }

    /**
     * @param valorTipoDetalleCobroDAO the valorTipoDetalleCobroDAO to set
     */
    public void setValorTipoDetalleCobroDAO(ITValorTipoDetalleCobro valorTipoDetalleCobroDAO) {
        this.valorTipoDetalleCobroDAO = valorTipoDetalleCobroDAO;
    }

    /**
     * @return the estadoDeudasDAO
     */
    public ITEstadoDeudas getEstadoDeudasDAO() {
        return estadoDeudasDAO;
    }

    /**
     * @param estadoDeudasDAO the estadoDeudasDAO to set
     */
    public void setEstadoDeudasDAO(ITEstadoDeudas estadoDeudasDAO) {
        this.estadoDeudasDAO = estadoDeudasDAO;
    }

    /**
     * @return the planTrabajoDAO
     */
    public ITPlanTrabajo getPlanTrabajoDAO() {
        return planTrabajoDAO;
    }

    /**
     * @param planTrabajoDAO the planTrabajoDAO to set
     */
    public void setPlanTrabajoDAO(ITPlanTrabajo planTrabajoDAO) {
        this.planTrabajoDAO = planTrabajoDAO;
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

}
