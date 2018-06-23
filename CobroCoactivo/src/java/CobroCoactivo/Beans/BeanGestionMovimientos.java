/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Beans;

import CobroCoactivo.Bo.GestionMovimientosBO;
import CobroCoactivo.Bo.GestionMovimientosImpBO;
import CobroCoactivo.Bo.LoginImplBO;
import CobroCoactivo.Modelo.Deudas;
import CobroCoactivo.Modelo.EtapasTrabajos;
import CobroCoactivo.Modelo.PlanTrabajos;
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

/**
 *
 * @author AMORENO
 */
@ManagedBean(name = "gestionMovimientoBean", eager = true)
@ViewScoped
public class BeanGestionMovimientos implements Serializable {

    private GestionMovimientosBO gestionMovimientosBO;
    private BeanLogin loginBO;

    // filtro de busquedas
    private String fechaAdquisicionInicial;
    // filtro de busquedas
    private String fechaAdquisicionFinal;
    // filtro de busquedas
    private Date fechaDeudaInicial;
    // filtro de busquedas
    private Date fechaDeudaFInal;
    private boolean visibleBusqueda;
    private int tipoBusqueda;

    private List<PlanTrabajos> listaPlanTrabajo = new ArrayList<>();
    private PlanTrabajos planTrabajoSeleccionado;
    private EtapasTrabajos etapaTrabajoSeleccionada;
    private List<Deudas> listaDeudasTabla = new ArrayList<>();
    private List<Deudas> listaDeudas = new ArrayList<>();

    @PostConstruct
    public void init() {
        setGestionMovimientosBO(new GestionMovimientosImpBO());
        setLoginBO(new BeanLogin());
        setVisibleBusqueda(true);
    }

    public void buscar(int tipo) {
        try {
            setTipoBusqueda(tipo);
            setListaPlanTrabajo(new ArrayList<>());
            getGestionMovimientosBO().cargarListadoPlanesTrabajo(this);
            getGestionMovimientosBO().buscarDeudasByTipoBusqueda(this);
            if (getListaDeudas().size() > 0) {
                setVisibleBusqueda(false);
            }
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al cargar datos : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBO().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    public void buscarEtapasByPlanTrabajo(PlanTrabajos planTrabajos) {
        try {
            if (getPlanTrabajoSeleccionado() != planTrabajos) {
                if (getEtapaTrabajoSeleccionada() != null) {
                    getEtapaTrabajoSeleccionada().setDescricion("");
                    getEtapaTrabajoSeleccionada().setListaFasesTrabajo(new ArrayList<>());
                }
            }
            setPlanTrabajoSeleccionado(planTrabajos);
            getPlanTrabajoSeleccionado().setListaEtapasTrabajo(new ArrayList<>());
            getGestionMovimientosBO().cargarEtapasPlanTrabajo(this);
            getGestionMovimientosBO().filtarListaDeudaTabla(this);
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al cargar datos : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBO().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    public void buscarFasesByEtapa(EtapasTrabajos etapasTrabajos) {
        try {
            setEtapaTrabajoSeleccionada(etapasTrabajos);
            getEtapaTrabajoSeleccionada().setListaFasesTrabajo(new ArrayList<>());
            getGestionMovimientosBO().cargarFasesTrabajo(this);
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al cargar datos : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBO().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    /**
     * @return the gestionMovimientosBO
     */
    public GestionMovimientosBO getGestionMovimientosBO() {
        return gestionMovimientosBO;
    }

    /**
     * @param gestionMovimientosBO the gestionMovimientosBO to set
     */
    public void setGestionMovimientosBO(GestionMovimientosBO gestionMovimientosBO) {
        this.gestionMovimientosBO = gestionMovimientosBO;
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
     * @return the fechaDeudaFInal
     */
    public Date getFechaDeudaFInal() {
        return fechaDeudaFInal;
    }

    /**
     * @param fechaDeudaFInal the fechaDeudaFInal to set
     */
    public void setFechaDeudaFInal(Date fechaDeudaFInal) {
        this.fechaDeudaFInal = fechaDeudaFInal;
    }

    /**
     * @return the listaPlanTrabajo
     */
    public List<PlanTrabajos> getListaPlanTrabajo() {
        return listaPlanTrabajo;
    }

    /**
     * @param listaPlanTrabajo the listaPlanTrabajo to set
     */
    public void setListaPlanTrabajo(List<PlanTrabajos> listaPlanTrabajo) {
        this.listaPlanTrabajo = listaPlanTrabajo;
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
     * @return the visibleBusqueda
     */
    public boolean isVisibleBusqueda() {
        return visibleBusqueda;
    }

    /**
     * @param visibleBusqueda the visibleBusqueda to set
     */
    public void setVisibleBusqueda(boolean visibleBusqueda) {
        this.visibleBusqueda = visibleBusqueda;
    }

    /**
     * @return the listaDeudas
     */
    public List<Deudas> getListaDeudas() {
        return listaDeudas;
    }

    /**
     * @param listaDeudas the listaDeudas to set
     */
    public void setListaDeudas(List<Deudas> listaDeudas) {
        this.listaDeudas = listaDeudas;
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
     * @return the fechaAdquisicionInicial
     */
    public String getFechaAdquisicionInicial() {
        return fechaAdquisicionInicial;
    }

    /**
     * @param fechaAdquisicionInicial the fechaAdquisicionInicial to set
     */
    public void setFechaAdquisicionInicial(String fechaAdquisicionInicial) {
        this.fechaAdquisicionInicial = fechaAdquisicionInicial;
    }

    /**
     * @return the fechaAdquisicionFinal
     */
    public String getFechaAdquisicionFinal() {
        return fechaAdquisicionFinal;
    }

    /**
     * @param fechaAdquisicionFinal the fechaAdquisicionFinal to set
     */
    public void setFechaAdquisicionFinal(String fechaAdquisicionFinal) {
        this.fechaAdquisicionFinal = fechaAdquisicionFinal;
    }

    /**
     * @return the planTrabajoSeleccionado
     */
    public PlanTrabajos getPlanTrabajoSeleccionado() {
        return planTrabajoSeleccionado;
    }

    /**
     * @param planTrabajoSeleccionado the planTrabajoSeleccionado to set
     */
    public void setPlanTrabajoSeleccionado(PlanTrabajos planTrabajoSeleccionado) {
        this.planTrabajoSeleccionado = planTrabajoSeleccionado;
    }

    /**
     * @return the etapaTrabajoSeleccionada
     */
    public EtapasTrabajos getEtapaTrabajoSeleccionada() {
        return etapaTrabajoSeleccionada;
    }

    /**
     * @param etapaTrabajoSeleccionada the etapaTrabajoSeleccionada to set
     */
    public void setEtapaTrabajoSeleccionada(EtapasTrabajos etapaTrabajoSeleccionada) {
        this.etapaTrabajoSeleccionada = etapaTrabajoSeleccionada;
    }

    /**
     * @return the listaDeudasTabla
     */
    public List<Deudas> getListaDeudasTabla() {
        return listaDeudasTabla;
    }

    /**
     * @param listaDeudasTabla the listaDeudasTabla to set
     */
    public void setListaDeudasTabla(List<Deudas> listaDeudasTabla) {
        this.listaDeudasTabla = listaDeudasTabla;
    }

}
