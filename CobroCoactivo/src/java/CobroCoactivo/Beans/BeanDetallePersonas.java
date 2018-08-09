/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Beans;

import CobroCoactivo.Bo.DetallePersonaBO;
import CobroCoactivo.Bo.DetallePersonaImpBO;
import CobroCoactivo.Modelo.EstadoPersonas;
import CobroCoactivo.Modelo.Personas;
import CobroCoactivo.Modelo.TipoDocumentos;
import CobroCoactivo.Utility.Log_Handler;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author emadrid
 */
@ManagedBean(name = "detallePersonasBean", eager = true)
@ViewScoped
public class BeanDetallePersonas {

    //Variable utilizada para el detalle de la persona en modal
    private Personas detallePersonasModal = new Personas();
    private List<EstadoPersonas> listEstadoPersonas = new ArrayList<>();
    private int estadoPersonas;
    private List<TipoDocumentos> listTipoDocumento = new ArrayList<>();

    /////
    private BeanLogin loginBO;
    private BeanRequest requestBean = new BeanRequest();
    private DetallePersonaBO detallePersonaBO;

    public void init() {
        try { 
            setDetallePersonaBO(new DetallePersonaImpBO());
            FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            BeanRequest obj_ = (BeanRequest) session.getAttribute("requestBean");
            if (obj_ != null) {
                if (obj_.getPersonas() != null) {
                    setDetallePersonasModal(obj_.getPersonas());
                    getDetallePersonaBO().cargarDatosPersonas(this);
                    getDetallePersonaBO().cargarTipoDocumento(this);
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("pnlModalDetallePersona");
                    requestContext.execute("$('#modalDetallePersona').modal('show')");
                }
            }
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al cargar datos : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBO().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Prueba", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    /**
     * @return the loginBO
     */
    public BeanLogin getLoginBO() {
        return loginBO;
    }

    /**
     * @param loginBO the loginBO to set
     */
    public void setLoginBO(BeanLogin loginBO) {
        this.loginBO = loginBO;
    }

    /**
     * @return the requestBean
     */
    public BeanRequest getRequestBean() {
        return requestBean;
    }

    /**
     * @param requestBean the requestBean to set
     */
    public void setRequestBean(BeanRequest requestBean) {
        this.requestBean = requestBean;
    }

    /**
     * @return the detallePersonasModal
     */
    public Personas getDetallePersonasModal() {
        return detallePersonasModal;
    }

    /**
     * @param detallePersonasModal the detallePersonasModal to set
     */
    public void setDetallePersonasModal(Personas detallePersonasModal) {
        this.detallePersonasModal = detallePersonasModal;
    }

    /**
     * @return the estadoPersonas
     */
    public int getEstadoPersonas() {
        return estadoPersonas;
    }

    /**
     * @param estadoPersonas the estadoPersonas to set
     */
    public void setEstadoPersonas(int estadoPersonas) {
        this.estadoPersonas = estadoPersonas;
    }

    /**
     * @return the listEstadoPersonas
     */
    public List<EstadoPersonas> getListEstadoPersonas() {
        return listEstadoPersonas;
    }

    /**
     * @param listEstadoPersonas the listEstadoPersonas to set
     */
    public void setListEstadoPersonas(List<EstadoPersonas> listEstadoPersonas) {
        this.listEstadoPersonas = listEstadoPersonas;
    }

    /**
     * @return the listTipoDocumento
     */
    public List<TipoDocumentos> getListTipoDocumento() {
        return listTipoDocumento;
    }

    /**
     * @param listTipoDocumento the listTipoDocumento to set
     */
    public void setListTipoDocumento(List<TipoDocumentos> listTipoDocumento) {
        this.listTipoDocumento = listTipoDocumento;
    }

    /**
     * @return the detallePersonaBO
     */
    public DetallePersonaBO getDetallePersonaBO() {
        return detallePersonaBO;
    }

    /**
     * @param detallePersonaBO the detallePersonaBO to set
     */
    public void setDetallePersonaBO(DetallePersonaBO detallePersonaBO) {
        this.detallePersonaBO = detallePersonaBO;
    }
}
