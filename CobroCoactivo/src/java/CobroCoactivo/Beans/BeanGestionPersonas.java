/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Beans;

import CobroCoactivo.Bo.GestionPersonasBO;
import CobroCoactivo.Bo.GestionPersonasImpBO;
import CobroCoactivo.Modelo.Deudas;
import CobroCoactivo.Modelo.EstadoPersonas;
import CobroCoactivo.Modelo.Movimientos;
import CobroCoactivo.Modelo.Personas;
import CobroCoactivo.Modelo.TipoDocumentos;
import CobroCoactivo.Utility.Log_Handler;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author amoreno
 */
@ManagedBean(name = "gestionPersonaBean", eager = true)
@ViewScoped
public class BeanGestionPersonas implements Serializable {
    // filtros de Busqueda 

    private String documentoPersona;
    private int tipoDocumentoPersona;
    private String nombrePersona;
    private Date FechaInicial, FechaFinal;
    //--- filtros de busqueda
    // lista que se utilizara para cargar el combo de tipos de documento
    private List<TipoDocumentos> listTipoDocumento = new ArrayList<>();
    // lista que se utilizara para cargar la tabla de resultado de la busqueda
    private List<Personas> listPersonas = new ArrayList<>();

    // Objeto que se utiliza para captura la deuda que seleccione para mostrar sus movimientos
    private Deudas deudaSelecionada;
    private Movimientos detalleMovimientos = new Movimientos();

    private List<EstadoPersonas> listEstadoPersonas = new ArrayList<>();
    private int estadoPersonas;

    // objeto que se utilizara para mostrar el detalle de la persona
    private Personas detallePersona = new Personas();
    private String encabezadoDetallePersona;

    private BeanLogin loginBO;
    private BeanRequest requestBean = new BeanRequest();
    private GestionPersonasBO gestionPersonasBO;

    @PostConstruct
    public void init() {
        try {
            setGestionPersonasBO(new GestionPersonasImpBO());
            FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            BeanRequest obj_ = (BeanRequest) session.getAttribute("requestBean");
            if (obj_ != null) {
                if (obj_.getPersonas() != null) {
                    setDetallePersona(obj_.getPersonas());
                    setEncabezadoDetallePersona(obj_.getRuta());
                    getGestionPersonasBO().cargarDatosPersonas(this);
                    getGestionPersonasBO().cargarDeudas(this);
                    obj_.setPersonas(new Personas());
                }
            }
            setLoginBO(new BeanLogin());
            if (getListTipoDocumento() != null && getListTipoDocumento().size() == 0) {
                getGestionPersonasBO().cargarTipoDocumento(this);
                getGestionPersonasBO().cargarEstadoPersonas(this);
            }
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al cargar datos : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBO().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    public void modalAgregarPersona() {
        try {
            setDetallePersona(new Personas());
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("$('#modalAgregarPersona').modal('show')");
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al cargar datos : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBO().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    public void buscarPersona(int tipo) {
        try {
            // casos para los tipos de busqueda
            switch (tipo) {
                // busqueda por tipo documento y documento
                case 1:
                    getGestionPersonasBO().consultarByDocumentoByTipoDocumento(this);
                    break;
                case 2:
                    break;
            }
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al cargar datos : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBO().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    public void cargarMovimientosDeuda(Deudas deudas) {
        try {
            setDeudaSelecionada(deudas);
            getGestionPersonasBO().cargarMovimientosDeudas(this);
            RequestContext requestContext = RequestContext.getCurrentInstance();
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frmDetallePersona:tablaMovimientos");
            requestContext.execute("$('#modalMovimiento').modal('show')");
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al cargar datos : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBO().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }

    }

    public void editarPersona() {
        try {
            getDetallePersona().setEditable(false);
            getDetallePersona();
            getGestionPersonasBO().updatePersona(this);
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al cargar datos : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBO().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    public void savePersona() {
        try {
            getGestionPersonasBO().guardarPersona(this);
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("$('#modalAgregarPersona').modal('hide')");
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al cargar datos : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBO().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    /**
     * @return the documentoPersona
     */
    public String getDocumentoPersona() {
        return documentoPersona;
    }

    /**
     * @param documentoPersona the documentoPersona to set
     */
    public void setDocumentoPersona(String documentoPersona) {
        this.documentoPersona = documentoPersona;
    }

    /**
     * @return the tipoDocumentoPersona
     */
    public int getTipoDocumentoPersona() {
        return tipoDocumentoPersona;
    }

    /**
     * @param tipoDocumentoPersona the tipoDocumentoPersona to set
     */
    public void setTipoDocumentoPersona(int tipoDocumentoPersona) {
        this.tipoDocumentoPersona = tipoDocumentoPersona;
    }

    /**
     * @return the nombrePersona
     */
    public String getNombrePersona() {
        return nombrePersona;
    }

    /**
     * @param nombrePersona the nombrePersona to set
     */
    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    /**
     * @return the FechaInicial
     */
    public Date getFechaInicial() {
        return FechaInicial;
    }

    /**
     * @param FechaInicial the FechaInicial to set
     */
    public void setFechaInicial(Date FechaInicial) {
        this.FechaInicial = FechaInicial;
    }

    /**
     * @return the FechaFinal
     */
    public Date getFechaFinal() {
        return FechaFinal;
    }

    /**
     * @param FechaFinal the FechaFinal to set
     */
    public void setFechaFinal(Date FechaFinal) {
        this.FechaFinal = FechaFinal;
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
     * @return the listPersonas
     */
    public List<Personas> getListPersonas() {
        return listPersonas;
    }

    /**
     * @param listPersonas the listPersonas to set
     */
    public void setListPersonas(List<Personas> listPersonas) {
        this.listPersonas = listPersonas;
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
     * @return the gestionPersonasBO
     */
    public GestionPersonasBO getGestionPersonasBO() {
        return gestionPersonasBO;
    }

    /**
     * @param gestionPersonasBO the gestionPersonasBO to set
     */
    public void setGestionPersonasBO(GestionPersonasBO gestionPersonasBO) {
        this.gestionPersonasBO = gestionPersonasBO;
    }

    /**
     * @return the detallePersona
     */
    public Personas getDetallePersona() {
        return detallePersona;
    }

    /**
     * @param detallePersona the detallePersona to set
     */
    public void setDetallePersona(Personas detallePersona) {
        this.detallePersona = detallePersona;
    }

    /**
     * @return the encabezadoDetallePersona
     */
    public String getEncabezadoDetallePersona() {
        return encabezadoDetallePersona;
    }

    /**
     * @param encabezadoDetallePersona the encabezadoDetallePersona to set
     */
    public void setEncabezadoDetallePersona(String encabezadoDetallePersona) {
        this.encabezadoDetallePersona = encabezadoDetallePersona;
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
     * @return the deudaSelecionada
     */
    public Deudas getDeudaSelecionada() {
        return deudaSelecionada;
    }

    /**
     * @param deudaSelecionada the deudaSelecionada to set
     */
    public void setDeudaSelecionada(Deudas deudaSelecionada) {
        this.deudaSelecionada = deudaSelecionada;
    }

    /**
     * @return the detalleMovimientos
     */
    public Movimientos getDetalleMovimientos() {
        return detalleMovimientos;
    }

    /**
     * @param detalleMovimientos the detalleMovimientos to set
     */
    public void setDetalleMovimientos(Movimientos detalleMovimientos) {
        this.detalleMovimientos = detalleMovimientos;
    }

}
