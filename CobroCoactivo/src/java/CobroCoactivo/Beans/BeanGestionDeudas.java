/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Beans;

import CobroCoactivo.Bo.GestionDeudasBO;
import CobroCoactivo.Bo.GestionDeudasImpBO;
import CobroCoactivo.Modelo.CobroDeudas;
import CobroCoactivo.Modelo.Deudas;
import CobroCoactivo.Modelo.Personas;
import CobroCoactivo.Modelo.TipoDeudas;
import CobroCoactivo.Utility.Log_Handler;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author emadrid
 */
@ManagedBean(name = "gestionDeudasBean", eager = true)
@ViewScoped
public class BeanGestionDeudas {

    private GestionDeudasBO gestionDeudasBO;
    private BeanLogin loginBO;
    private int tipoBusqueda;
    private int tipoDeudas;
    private int estadoDeudas;
    private String referenciaDeuda;
    private Date fechaCargueInicial;
    private Date fechaCargueFinal;
    private Date fechaDeudaInicial;
    private Date fechaDeudaFinal;
    private boolean prueba = false;

    private List<TipoDeudas> listTipoDeudas = new ArrayList<>();
    private List<Deudas> listDeudas = new ArrayList<>();
    private Personas personas = new Personas();
    private Deudas deudas = new Deudas();
    private boolean busquedaVisible = true;
    private boolean btnFiltroBusqueda = false;
    private boolean pnlTablaClasificacionDeudas = true;
    private List<CobroDeudas> listCobroDeudas = new ArrayList<>();
    private CobroDeudas cobroDeudasSeleccionado;
    private Deudas deudaSeleccionada;

    @PostConstruct
    public void init() {
        try {
            setGestionDeudasBO(new GestionDeudasImpBO());
            setLoginBO(new BeanLogin());
            getGestionDeudasBO().cargarListaTipoDeudas(this);
            getGestionDeudasBO().cargarListaCobroDeudas(this);
            //getGestionDeudasBO().clasificacionDeudas(this);
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al cargar datos : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBO().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error fatal", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }

    }

    public void buscarDeudas(int tipo) {
        try {
            setTipoBusqueda(tipo);
            getGestionDeudasBO().buscarDeudas(this);
            if (getListDeudas().size() > 0) {
                setBusquedaVisible(false);
            } else {

            }
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al cargar datos : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBO().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error fatal", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    public void dificultadDeudas(CobroDeudas cobroDeudas, Deudas deudas) {
        try {
            setCobroDeudasSeleccionado(cobroDeudas);
            setDeudaSeleccionada(deudas);
            getGestionDeudasBO().actualizarDeudaCargada(this);
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al cargar datos : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBO().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error fatal", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    public void crearExpedienteDeuda(Deudas deudas) {
        try {
            setDeudas(deudas);
            getGestionDeudasBO().crearExpedienteDeuda(this);
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al cargar datos : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBO().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error fatal", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    /**
     * @return the personas
     */
    public Personas getPersonas() {
        return personas;
    }

    /**
     * @param personas the personas to set
     */
    public void setPersonas(Personas personas) {
        this.personas = personas;
    }

    /**
     * @return the gestionDeudasBO
     */
    public GestionDeudasBO getGestionDeudasBO() {
        return gestionDeudasBO;
    }

    /**
     * @param gestionDeudasBO the gestionDeudasBO to set
     */
    public void setGestionDeudasBO(GestionDeudasBO gestionDeudasBO) {
        this.gestionDeudasBO = gestionDeudasBO;
    }

    /**
     * @return the listDeudas
     */
    public List<Deudas> getListDeudas() {
        return listDeudas;
    }

    /**
     * @param listDeudas the listDeudas to set
     */
    public void setListDeudas(List<Deudas> listDeudas) {
        this.listDeudas = listDeudas;
    }

    /**
     * @return the tipoBusqueda
     */
    public int getTipoBusqueda() {
        return tipoBusqueda;
    }

    /**
     * @param tipoBusqueda the tipoBusqueda to set
     */
    public void setTipoBusqueda(int tipoBusqueda) {
        this.tipoBusqueda = tipoBusqueda;
    }

    /**
     * @return the referenciaDeuda
     */
    public String getReferenciaDeuda() {
        return referenciaDeuda;
    }

    /**
     * @param referenciaDeuda the referenciaDeuda to set
     */
    public void setReferenciaDeuda(String referenciaDeuda) {
        this.referenciaDeuda = referenciaDeuda;
    }

    /**
     * @return the deudas
     */
    public Deudas getDeudas() {
        return deudas;
    }

    /**
     * @param deudas the deudas to set
     */
    public void setDeudas(Deudas deudas) {
        this.deudas = deudas;
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
     * @return the busquedaVisible
     */
    public boolean isBusquedaVisible() {
        return busquedaVisible;
    }

    /**
     * @param busquedaVisible the busquedaVisible to set
     */
    public void setBusquedaVisible(boolean busquedaVisible) {
        this.busquedaVisible = busquedaVisible;
    }

    /**
     * @return the btnFiltroBusqueda
     */
    public boolean isBtnFiltroBusqueda() {
        return btnFiltroBusqueda;
    }

    /**
     * @param btnFiltroBusqueda the btnFiltroBusqueda to set
     */
    public void setBtnFiltroBusqueda(boolean btnFiltroBusqueda) {
        this.btnFiltroBusqueda = btnFiltroBusqueda;
    }

    /**
     * @return the tipoDeudas
     */
    public int getTipoDeudas() {
        return tipoDeudas;
    }

    /**
     * @param tipoDeudas the tipoDeudas to set
     */
    public void setTipoDeudas(int tipoDeudas) {
        this.tipoDeudas = tipoDeudas;
    }

    /**
     * @return the listTipoDeudas
     */
    public List<TipoDeudas> getListTipoDeudas() {
        return listTipoDeudas;
    }

    /**
     * @param listTipoDeudas the listTipoDeudas to set
     */
    public void setListTipoDeudas(List<TipoDeudas> listTipoDeudas) {
        this.listTipoDeudas = listTipoDeudas;
    }

    /**
     * @return the listCobroDeudas
     */
    public List<CobroDeudas> getListCobroDeudas() {
        return listCobroDeudas;
    }

    /**
     * @param listCobroDeudas the listCobroDeudas to set
     */
    public void setListCobroDeudas(List<CobroDeudas> listCobroDeudas) {
        this.listCobroDeudas = listCobroDeudas;
    }

    /**
     * @return the cobroDeudasSeleccionado
     */
    public CobroDeudas getCobroDeudasSeleccionado() {
        return cobroDeudasSeleccionado;
    }

    /**
     * @param cobroDeudasSeleccionado the cobroDeudasSeleccionado to set
     */
    public void setCobroDeudasSeleccionado(CobroDeudas cobroDeudasSeleccionado) {
        this.cobroDeudasSeleccionado = cobroDeudasSeleccionado;
    }

    /**
     * @return the deudaSeleccionada
     */
    public Deudas getDeudaSeleccionada() {
        return deudaSeleccionada;
    }

    /**
     * @param deudaSeleccionada the deudaSeleccionada to set
     */
    public void setDeudaSeleccionada(Deudas deudaSeleccionada) {
        this.deudaSeleccionada = deudaSeleccionada;
    }

    /**
     * @return the estadoDeudas
     */
    public int getEstadoDeudas() {
        return estadoDeudas;
    }

    /**
     * @param estadoDeudas the estadoDeudas to set
     */
    public void setEstadoDeudas(int estadoDeudas) {
        this.estadoDeudas = estadoDeudas;
    }

    /**
     * @return the pnlTablaClasificacionDeudas
     */
    public boolean isPnlTablaClasificacionDeudas() {
        return pnlTablaClasificacionDeudas;
    }

    /**
     * @param pnlTablaClasificacionDeudas the pnlTablaClasificacionDeudas to set
     */
    public void setPnlTablaClasificacionDeudas(boolean pnlTablaClasificacionDeudas) {
        this.pnlTablaClasificacionDeudas = pnlTablaClasificacionDeudas;
    }

    /**
     * @return the fechaCargueInicial
     */
    public Date getFechaCargueInicial() {
        return fechaCargueInicial;
    }

    /**
     * @param fechaCargueInicial the fechaCargueInicial to set
     */
    public void setFechaCargueInicial(Date fechaCargueInicial) {
        this.fechaCargueInicial = fechaCargueInicial;
    }

    /**
     * @return the fechaCargueFinal
     */
    public Date getFechaCargueFinal() {
        return fechaCargueFinal;
    }

    /**
     * @param fechaCargueFinal the fechaCargueFinal to set
     */
    public void setFechaCargueFinal(Date fechaCargueFinal) {
        this.fechaCargueFinal = fechaCargueFinal;
    }

    /**
     * @return the fechaDeudaInicial
     */
    public Date getFechaDeudaInicial() {
        return fechaDeudaInicial;
    }

    /**
     * @param fechaDeudaInicial the fechaDeudaInicial to set
     */
    public void setFechaDeudaInicial(Date fechaDeudaInicial) {
        this.fechaDeudaInicial = fechaDeudaInicial;
    }

    /**
     * @return the fechaDeudaFinal
     */
    public Date getFechaDeudaFinal() {
        return fechaDeudaFinal;
    }

    /**
     * @param fechaDeudaFinal the fechaDeudaFinal to set
     */
    public void setFechaDeudaFinal(Date fechaDeudaFinal) {
        this.fechaDeudaFinal = fechaDeudaFinal;
    }

    /**
     * @return the prueba
     */
    public boolean isPrueba() {
        return prueba;
    }

    /**
     * @param prueba the prueba to set
     */
    public void setPrueba(boolean prueba) {
        this.prueba = prueba;
    }

}
