package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanGestionPersonas;
import java.io.Serializable;
import CobroCoactivo.Dao.*;
import CobroCoactivo.Modelo.DatosPersonas;
import CobroCoactivo.Modelo.Deudas;
import CobroCoactivo.Modelo.EstadoPersonas;
import CobroCoactivo.Modelo.Personas;
import CobroCoactivo.Modelo.TipoDocumentos;
import CobroCoactivo.Persistencia.CivDatosPersonas;
import CobroCoactivo.Persistencia.CivDeudas;
import CobroCoactivo.Persistencia.CivEstadoPersonas;
import CobroCoactivo.Persistencia.CivPersonas;
import CobroCoactivo.Persistencia.CivTipoDocumentos;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author jvergara
 */
public class GestionPersonasImpBO implements GestionPersonasBO, Serializable {

    private ITTipoDocumento tipoDocumentoDAO;
    private ITPersonas personasDAO;
    private ITDatosPersonas datosPersonasDAO;
    private ITDeudas deudasDAO;
    private ITEstadoPersonas estadoPersonasDAO;

    public GestionPersonasImpBO() {
        tipoDocumentoDAO = new DaoTipoDocumento();
        personasDAO = new DaoPersonas();
        datosPersonasDAO = new DaoDatosPersonas();
        estadoPersonasDAO = new DaoEstadoPersonas();
        deudasDAO = new DaoDeudas();
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
    public void cargarDatosPersonas(BeanGestionPersonas beanGestionPersonas) throws Exception {
        if (beanGestionPersonas != null) {
            if (beanGestionPersonas.getDetallePersona() != null) {
                if (beanGestionPersonas.getDetallePersona().getId() != 0) {
                    List<CivDatosPersonas> listCivDatosPersonas = getDatosPersonasDAO().listarDatosPersonas(beanGestionPersonas.getDetallePersona().getId());
                    for (CivDatosPersonas CivDatosPersona : listCivDatosPersonas) {
                        DatosPersonas datosPersonas = new DatosPersonas(CivDatosPersona, CivDatosPersona.getCivTipoDatosPersonas(), CivDatosPersona.getCivEstadoDatosPersonas());
                        beanGestionPersonas.getDetallePersona().getListDatosPersonas().add(datosPersonas);
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
                    for (CivDeudas civDeudas : listCivDeudas) {
                        Deudas deudas = new Deudas(civDeudas, civDeudas.getCivEstadoDeudas(), civDeudas.getCivTipoDeudas(), civDeudas.getCivPersonas());
                        bean.getDetallePersona().getListdeuda().add(deudas);
                    }
                }
            }
        }
    }

    @Override
    public void consultarByDocumentoByTipoDocumento(BeanGestionPersonas bean) throws Exception {
        bean.setListPersonas(new ArrayList<>());
        CivPersonas civPersonas = getPersonasDAO().consultarPersonasByDocumento(bean.getTipoDocumentoPersona(), bean.getDocumentoPersona());
        if (civPersonas == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "no se encontro la persona en el sistema", null));
        }
        Personas persona = new Personas(civPersonas, civPersonas.getCivEstadoPersonas(), civPersonas.getCivTipoDocumentos());
        bean.getListPersonas().add(persona);
    }

    @Override
    public void guardarPersona(BeanGestionPersonas bean) throws Exception {
        CivPersonas civPersonas = new CivPersonas();

        CivTipoDocumentos civTipoDocumentos = getTipoDocumentoDAO().getTipoDocumento(BigDecimal.valueOf(bean.getTipoDocumentoPersona()));
        civTipoDocumentos.setTipdocId(civTipoDocumentos.getTipdocId());
        civTipoDocumentos.setTipdocDescripcion(civTipoDocumentos.getTipdocDescripcion());
        civTipoDocumentos.setTipdocNombrecorto(civTipoDocumentos.getTipdocNombrecorto());
        civTipoDocumentos.setTipdocCodigo(civTipoDocumentos.getTipdocCodigo());
        civTipoDocumentos.setTipdocFechainicial(civTipoDocumentos.getTipdocFechainicial());
        civTipoDocumentos.setTipdocFechafinal(civTipoDocumentos.getTipdocFechafinal());

        CivEstadoPersonas civEstadoPersonas = getEstadoPersonasDAO().getEstadoPersona(BigDecimal.valueOf(bean.getEstadoPersonas()));
        civEstadoPersonas.setEstperId(civEstadoPersonas.getEstperId());
        civEstadoPersonas.setEstperDescripcion(civEstadoPersonas.getEstperDescripcion());
        civEstadoPersonas.setEstperFechainicial(civEstadoPersonas.getEstperFechainicial());
        civEstadoPersonas.setEstperFechafinal(civEstadoPersonas.getEstperFechafinal());
        civEstadoPersonas.setEstperFechaproceso(civEstadoPersonas.getEstperFechaproceso());

        civPersonas.setPerId(new BigDecimal(bean.getDetallePersona().getId()));
       
        civPersonas.setPerNombre1(bean.getDetallePersona().getNombre1());
        civPersonas.setPerNombre2(bean.getDetallePersona().getNombre2());
        civPersonas.setPerSexo(bean.getDetallePersona().getSexo());
        civPersonas.setCivTipoDocumentos(civTipoDocumentos);
        civPersonas.setPerApellido1(bean.getDetallePersona().getApellido1());
        civPersonas.setPerApellido2(bean.getDetallePersona().getApellido2());
        civPersonas.setPerDocumento(bean.getDetallePersona().getDocumento());
        civPersonas.setCivEstadoPersonas(civEstadoPersonas);
        getPersonasDAO().create(civPersonas);
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

}
