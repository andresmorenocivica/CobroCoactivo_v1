/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Beans;

import CobroCoactivo.Bo.GestionMovimientosBO;
import CobroCoactivo.Bo.GestionMovimientosImpBO;
import CobroCoactivo.Exception.MovimientosException;
import CobroCoactivo.Modelo.Deudas;
import CobroCoactivo.Modelo.EtapasTrabajos;
import CobroCoactivo.Modelo.FasesTrabajos;
import CobroCoactivo.Modelo.PlanTrabajos;
import CobroCoactivo.Utility.HibernateUtil;
import CobroCoactivo.Utility.Log_Handler;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author AMORENO
 */
@ManagedBean(name = "gestionMovimientoBean", eager = true)
@ViewScoped
public class BeanGestionMovimientos implements Serializable {

    private GestionMovimientosBO gestionMovimientosBO;
    private BeanLogin loginBO;
    private BeanRequest loginBean;
    private List<PlanTrabajos> listaPlanTrabajo = new ArrayList<>();
    private PlanTrabajos planTrabajoSeleccionado;
    private EtapasTrabajos etapaTrabajoSeleccionada;
    private FasesTrabajos faseTrabajoSeleccionada;

    private List<Deudas> listaDeudasTabla = new ArrayList<>();
    private List<Deudas> listaDeudas = new ArrayList<>();

    @PostConstruct
    public void init() {
        try {
            FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            setLoginBO((BeanLogin) session.getAttribute("loginBean"));
            setGestionMovimientosBO(new GestionMovimientosImpBO());
            //getGestionMovimientosBO().cargarListadoDeudas(this);
            getGestionMovimientosBO().cargarListadoPlanesTrabajo(this);
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al cargar datos : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBO().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        } finally {

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
            setListaDeudasTabla(new ArrayList<>());
            setListaDeudasTabla(planTrabajos.getListaDeudas());
        } catch (MovimientosException me) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(me.getNivelFacesMessage(), "", me.getMessage()));
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al buscar etapas: ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBO().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    public void buscarFasesByEtapa(EtapasTrabajos etapasTrabajos) {
        try {
            setEtapaTrabajoSeleccionada(etapasTrabajos);
            getEtapaTrabajoSeleccionada().setListaFasesTrabajo(new ArrayList<>());
            getGestionMovimientosBO().cargarFasesTrabajo(this);
            setListaDeudasTabla(new ArrayList<>());
            //setListaDeudasTabla(etapasTrabajos.getListDeudas());
        } catch (MovimientosException me) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(me.getNivelFacesMessage(), "", me.getMessage()));
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al buscar fases : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBO().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    public void mostarDeudasFases(FasesTrabajos fasesTrabajos) {
        try {
            setListaDeudasTabla(new ArrayList<>());
            setListaDeudasTabla(fasesTrabajos.getListaDeudas());
            setFaseTrabajoSeleccionada(fasesTrabajos);
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al mostrar deudas : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBO().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }

    }

    public void guardarMovimiento() {
        try {
            getGestionMovimientosBO().generarMovimiento(this);
            setListaPlanTrabajo(new ArrayList<>());
            getGestionMovimientosBO().cargarListadoPlanesTrabajo(this);
            getGestionMovimientosBO().cargarEtapasPlanTrabajo(this);
            getGestionMovimientosBO().cargarFasesTrabajo(this);
//            setListaDeudasTabla(new ArrayList<>());
        } catch (MovimientosException me) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(me.getNivelFacesMessage(), "", me.getMessage()));
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al guardar movimientos : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBO().getID_Usuario()));
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
     * @return the faseTrabajoSeleccionada
     */
    public FasesTrabajos getFaseTrabajoSeleccionada() {
        return faseTrabajoSeleccionada;
    }

    /**
     * @param faseTrabajoSeleccionada the faseTrabajoSeleccionada to set
     */
    public void setFaseTrabajoSeleccionada(FasesTrabajos faseTrabajoSeleccionada) {
        this.faseTrabajoSeleccionada = faseTrabajoSeleccionada;
    }

    /**
     * @return the loginBean
     */
    public BeanRequest getLoginBean() {
        return loginBean;
    }

    /**
     * @param loginBean the loginBean to set
     */
    public void setLoginBean(BeanRequest loginBean) {
        this.loginBean = loginBean;
    }

}
