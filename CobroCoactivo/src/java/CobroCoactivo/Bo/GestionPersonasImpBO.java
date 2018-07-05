package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanGestionPersonas;
import java.io.Serializable;
import CobroCoactivo.Dao.*;
import CobroCoactivo.Modelo.DatosPersonas;
import CobroCoactivo.Modelo.Deudas;
import CobroCoactivo.Modelo.EstadoPersonas;
import CobroCoactivo.Modelo.Movimientos;
import CobroCoactivo.Modelo.Personas;
import CobroCoactivo.Modelo.TipoDocumentos;
import CobroCoactivo.Persistencia.CivDatosPersonas;
import CobroCoactivo.Persistencia.CivDeudas;
import CobroCoactivo.Persistencia.CivEstadoPersonas;
import CobroCoactivo.Persistencia.CivFasesTrabajos;
import CobroCoactivo.Persistencia.CivMovimientos;
import CobroCoactivo.Persistencia.CivPersonas;
import CobroCoactivo.Persistencia.CivTipoDocumentos;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author jvergara
 */
public class GestionPersonasImpBO implements GestionPersonasBO, Serializable {

    private ITDeudas deudasDAO;
    private ITPersonas personasDAO;
    private ITMovimientos movimientosDAO;
    private ITDatosPersonas datosPersonasDAO;
    private ITTipoDocumento tipoDocumentoDAO;
    private ITEstadoPersonas estadoPersonasDAO;
    private ITFasesTrabajo fasesTrabajoDAO;
    private ITPlanTrabajo planTrabajoDAO;

    public GestionPersonasImpBO() {
        deudasDAO = new DaoDeudas();
        personasDAO = new DaoPersonas();
        movimientosDAO = new DaoMovimientos();
        fasesTrabajoDAO = new DaoFasesTrabajo();
        tipoDocumentoDAO = new DaoTipoDocumento();
        datosPersonasDAO = new DaoDatosPersonas();
        estadoPersonasDAO = new DaoEstadoPersonas();
        planTrabajoDAO = new DaoPlanTrabajo();

    }

    @Override
    public void cargarTipoDocumento(BeanGestionPersonas beanGestionPersonas) throws Exception {
        List<CivTipoDocumentos> listCivTipoDocumento = getTipoDocumentoDAO().listAll();
        for (CivTipoDocumentos civTipoDocumentos : listCivTipoDocumento) {
            TipoDocumentos tipoDocumentos = new TipoDocumentos(civTipoDocumentos);
            beanGestionPersonas.getListTipoDocumento().add(tipoDocumentos);
        }
    }

    @Override
    public void cargarEstadoPersonas(BeanGestionPersonas bean) throws Exception {
        List<CivEstadoPersonas> listCivEstadoPersonas = getEstadoPersonasDAO().listAll();
        for (CivEstadoPersonas civEstadoPersona : listCivEstadoPersonas) {
            EstadoPersonas estadoPersonas = new EstadoPersonas(civEstadoPersona);
            bean.getListEstadoPersonas().add(estadoPersonas);
        }
    }

    @Override
    public void cargarMovimientosDeudas(BeanGestionPersonas bean) throws Exception {
        List<CivMovimientos> listCivMovimientos = getMovimientosDAO().listMovimientos(bean.getDeudaSelecionada().getId());
        if (listCivMovimientos == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No se encontro movimiento en el sistema de esta deuda", null));
        }
        if (listCivMovimientos != null) {
            for (CivMovimientos civMovimiento : listCivMovimientos) {
                CivFasesTrabajos civFasesTrabajos = getFasesTrabajoDAO().getFasesTrabajos(civMovimiento.getCivFasesTrabajos().getFastraId().intValue());
                Movimientos movimientos = new Movimientos(civMovimiento, civMovimiento.getCivEstadoMovimientos(), civMovimiento.getCivDeudas(), civFasesTrabajos, civMovimiento.getCivPersonas(), civMovimiento.getCivUsuarios());
                bean.getDeudaSelecionada().getListMovimientos().add(movimientos);
            }
        }
    }

    @Override
    public void cargarDatosPersonas(BeanGestionPersonas beanGestionPersonas) throws Exception {
        if (beanGestionPersonas != null) {
            if (beanGestionPersonas.getDetallePersona() != null) {
                if (beanGestionPersonas.getDetallePersona().getId() != 0) {
                    List<CivDatosPersonas> listCivDatosPersonas = getDatosPersonasDAO().listarDatosPersonas(beanGestionPersonas.getDetallePersona().getId());
                    if (listCivDatosPersonas != null) {
                        for (CivDatosPersonas CivDatosPersona : listCivDatosPersonas) {
                            DatosPersonas datosPersonas = new DatosPersonas(CivDatosPersona, CivDatosPersona.getCivTipoDatosPersonas(), CivDatosPersona.getCivEstadoDatosPersonas());
                            beanGestionPersonas.getDetallePersona().getListDatosPersonas().add(datosPersonas);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void updatePersona(BeanGestionPersonas bean) throws Exception {
        CivPersonas civPersonas = new CivPersonas();
        CivTipoDocumentos civTipoDocumentos = getTipoDocumentoDAO().getTipoDocumento(new BigDecimal(bean.getDetallePersona().getTipoDocumentos().getId()));
        civTipoDocumentos.setTipdocId(new BigDecimal(bean.getDetallePersona().getTipoDocumentos().getId()));
        civTipoDocumentos.setTipdocDescripcion(civTipoDocumentos.getTipdocDescripcion());
        civTipoDocumentos.setTipdocNombrecorto(civTipoDocumentos.getTipdocNombrecorto());
        civTipoDocumentos.setTipdocCodigo(civTipoDocumentos.getTipdocCodigo());
        civTipoDocumentos.setTipdocFechainicial(civTipoDocumentos.getTipdocFechainicial());
        civTipoDocumentos.setTipdocFechafinal(civTipoDocumentos.getTipdocFechafinal());

        CivEstadoPersonas civEstadoPersonas = new CivEstadoPersonas();
        civEstadoPersonas.setEstperId(new BigDecimal(bean.getDetallePersona().getEstadoPersonas().getId()));
        civEstadoPersonas.setEstperDescripcion(bean.getDetallePersona().getEstadoPersonas().getDescripcion());
        civEstadoPersonas.setEstperFechainicial(bean.getDetallePersona().getEstadoPersonas().getFechainicial());
        civEstadoPersonas.setEstperFechafinal(bean.getDetallePersona().getEstadoPersonas().getFechafinal());
        civEstadoPersonas.setEstperFechaproceso(bean.getDetallePersona().getEstadoPersonas().getFechaproceso());

        civPersonas.setPerId(new BigDecimal(bean.getDetallePersona().getId()));
        civPersonas.setPerDocumento(bean.getDetallePersona().getDocumento());
        civPersonas.setPerSexo(bean.getDetallePersona().getSexo());
        civPersonas.setCivTipoDocumentos(civTipoDocumentos);
        civPersonas.setPerNombre1(bean.getDetallePersona().getNombre1());
        civPersonas.setPerNombre2(bean.getDetallePersona().getNombre2());
        civPersonas.setPerApellido1(bean.getDetallePersona().getApellido1());
        civPersonas.setPerApellido2(bean.getDetallePersona().getApellido2());
        civPersonas.setCivEstadoPersonas(civEstadoPersonas);

        getPersonasDAO().update(civPersonas);
    }

    @Override
    public void cargarDeudas(BeanGestionPersonas bean) throws Exception {
        if (bean != null) {
            if (bean.getDetallePersona() != null) {
                if (bean.getDetallePersona().getId() != 0) {
                    List<CivDeudas> listCivDeudas = getDeudasDAO().listarDeudas(bean.getDetallePersona().getId());
                    if (listCivDeudas != null) {
                        for (CivDeudas civDeudas : listCivDeudas) {
                            Deudas deudas = new Deudas(civDeudas, civDeudas.getCivEstadoDeudas(), civDeudas.getCivPlanTrabajos(), civDeudas.getCivTipoDeudas(), civDeudas.getCivPersonas());
                            bean.getDetallePersona().getListdeuda().add(deudas);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void consultarByDocumentoByTipoDocumento(BeanGestionPersonas bean) throws Exception {
        bean.setListPersonas(new ArrayList<>());
        CivPersonas civPersonas = getPersonasDAO().consultarPersonasByDocumento(bean.getTipoDocumentoPersona(), bean.getDocumentoPersona());
        if (civPersonas != null) {
            Personas persona = new Personas(civPersonas, civPersonas.getCivEstadoPersonas(), civPersonas.getCivTipoDocumentos());
            bean.getListPersonas().add(persona);
        }
        if (civPersonas == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No se encontro la persona en el sistema", null));
        }
    }

    @Override
    public void guardarPersona(BeanGestionPersonas bean) throws Exception {
        CivPersonas civPersonas = new CivPersonas();
        CivTipoDocumentos civTipoDocumentos = getTipoDocumentoDAO().getTipoDocumento(BigDecimal.valueOf(bean.getTipoDocumentoPersona()));
        CivEstadoPersonas civEstadoPersonas = getEstadoPersonasDAO().getEstadoPersona(BigDecimal.valueOf(1));
        civPersonas.setPerNombre1(bean.getDetallePersona().getNombre1().toUpperCase());
        civPersonas.setPerNombre2(bean.getDetallePersona().getNombre2().toUpperCase());
        civPersonas.setPerSexo(bean.getDetallePersona().getSexo().toUpperCase());
        civPersonas.setCivTipoDocumentos(civTipoDocumentos);
        civPersonas.setPerApellido1(bean.getDetallePersona().getApellido1().toUpperCase());
        civPersonas.setPerApellido2(bean.getDetallePersona().getApellido2().toUpperCase());
        civPersonas.setPerDocumento(bean.getDetallePersona().getDocumento());
        civPersonas.setPerFechaproceso(new Date());
        civPersonas.setCivEstadoPersonas(civEstadoPersonas);
        getPersonasDAO().create(civPersonas);
        if (civPersonas != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se guardo la persona exitosamente", null));
        }
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
     * @return the estadoPersonasDAO
     */
    public ITEstadoPersonas getEstadoPersonasDAO() {
        return estadoPersonasDAO;
    }

    /**
     * @param estadoPersonasDAO the estadoPersonasDAO to set
     */
    public void setEstadoPersonasDAO(ITEstadoPersonas estadoPersonasDAO) {
        this.estadoPersonasDAO = estadoPersonasDAO;
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
     * @return the fasesTrabajoDAO
     */
    public ITFasesTrabajo getFasesTrabajoDAO() {
        return fasesTrabajoDAO;
    }

    /**
     * @param fasesTrabajoDAO the fasesTrabajoDAO to set
     */
    public void setFasesTrabajoDAO(ITFasesTrabajo fasesTrabajoDAO) {
        this.fasesTrabajoDAO = fasesTrabajoDAO;
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

}
