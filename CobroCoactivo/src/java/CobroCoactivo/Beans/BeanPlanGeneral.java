/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Beans;

import CobroCoactivo.Bo.PlanGeneralBO;
import CobroCoactivo.Bo.PlanGeneralImpBO;
import CobroCoactivo.Modelo.EstadoEtapasGenerales;
import CobroCoactivo.Modelo.EstadoPlanGenerales;
import CobroCoactivo.Modelo.EtapasGenerales;
import CobroCoactivo.Modelo.PlanGenerales;
import CobroCoactivo.Utility.Log_Handler;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author jvergara
 */
@ManagedBean(name = "PlanGeneralbean")
@ViewScoped
public class BeanPlanGeneral implements Serializable {

    private PlanGenerales planGenerales = new PlanGenerales();
    private EtapasGenerales etapasGenerales = new EtapasGenerales();
    private List<EstadoEtapasGenerales> estadoEtapasGenerales = new ArrayList<>();

    private PlanGeneralBO planGeneralBO;

    private List<PlanGenerales> listadoPlanGeneraleses = new ArrayList<>();
    private List<EtapasGenerales> listadoEtapasGeneraleses = new ArrayList<>();
    private List<EstadoPlanGenerales> ListadoEStadoPlanesGenerales = new ArrayList<>();

    private int idEstadoGeneral;
    private int idEstapaGeneral;

    private Boolean estadoButton = true;
    private Boolean renderEtapaGeneral = false;
    private String obligatorio;
    private String nombreModalTitulo;

    public BeanPlanGeneral() {

    }

    @PostConstruct
    public void init() {
        try {
            setPlanGeneralBO(new PlanGeneralImpBO());
            getPlanGeneralBO().ListarPlanesGenerales(this);
            if (getListadoEStadoPlanesGenerales().size() == 0) {
                getPlanGeneralBO().ListarEstadoGenerales(this);
            }

            if (getEstadoEtapasGenerales().size() == 0) {
                getPlanGeneralBO().ListarEstadoEtapasGenerales(this);
            }

        } catch (Exception ex) {
            Logger.getLogger(BeanPlanGeneral.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void guardarPlanGeneral() {

        try {
            getPlanGeneralBO().GuardarPlanGeneral(this);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Plan General Creado exitoxamente", null));
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("$('#planGeneral').modal('hide')");

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    public void actualizarPlanGeneral() {
        try {
            getPlanGeneralBO().ActualizarPlanGeneral(this);
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("$('#planGeneral').modal('hide')");

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }

    }

    public void guardarEtapaGeneral() {
        try {
            getPlanGeneralBO().GuardarEtapaGeneral(this);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Etapa general Creada exitoxamente", null));
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("$('#etapaGeneral').modal('hide')");

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");

        }
    }

    public void actualizarEtapaGeneral() {
        try {
            getPlanGeneralBO().ActualizarEtapaGeneral(this);
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("$('#etapaGeneral').modal('hide')");

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    public void eventoActualizar(PlanGenerales planGenerales) {
        try {
            setNombreModalTitulo("Actualizar");
            setEstadoButton(false);
            setPlanGenerales(planGenerales);
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }

    }

    public void eventoActualizarEtapa(EtapasGenerales etapasGenerales) {
        try {
            setNombreModalTitulo("Actualizar");
            setEstadoButton(false);
            setEtapasGenerales(etapasGenerales);

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }

    }

    public void eventoGuardar(int number) {
        setNombreModalTitulo("Guardar");
        setEstadoButton(true);
        if (number == 1) {
            setPlanGenerales(new PlanGenerales());
        } else {
            setEtapasGenerales(new EtapasGenerales());
        }

    }

    public void ListarEtadoGeneralesPorIdPlanGeneral(PlanGenerales planGenerales) {
        try {
            setRenderEtapaGeneral(true);
            setPlanGenerales(planGenerales);
            getPlanGeneralBO().listarEtadoGeneralesPorIdPlanGeneral(this);

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }

    }

    /**
     * @return the planGenerales
     */
    public PlanGenerales getPlanGenerales() {
        return planGenerales;
    }

    /**
     * @param planGenerales the planGenerales to set
     */
    public void setPlanGenerales(PlanGenerales planGenerales) {
        this.planGenerales = planGenerales;
    }

    /**
     * @return the planGeneralBO
     */
    public PlanGeneralBO getPlanGeneralBO() {
        return planGeneralBO;
    }

    /**
     * @param planGeneralBO the planGeneralBO to set
     */
    public void setPlanGeneralBO(PlanGeneralBO planGeneralBO) {
        this.planGeneralBO = planGeneralBO;
    }

    /**
     * @return the listadoPlanGeneraleses
     */
    public List<PlanGenerales> getListadoPlanGeneraleses() {
        return listadoPlanGeneraleses;
    }

    /**
     * @param listadoPlanGeneraleses the listadoPlanGeneraleses to set
     */
    public void setListadoPlanGeneraleses(List<PlanGenerales> listadoPlanGeneraleses) {
        this.listadoPlanGeneraleses = listadoPlanGeneraleses;
    }

    /**
     * @return the ListadoEStadoPlanesGenerales
     */
    public List<EstadoPlanGenerales> getListadoEStadoPlanesGenerales() {
        return ListadoEStadoPlanesGenerales;
    }

    /**
     * @param ListadoEStadoPlanesGenerales the ListadoEStadoPlanesGenerales to
     * set
     */
    public void setListadoEStadoPlanesGenerales(List<EstadoPlanGenerales> ListadoEStadoPlanesGenerales) {
        this.ListadoEStadoPlanesGenerales = ListadoEStadoPlanesGenerales;
    }

    /**
     * @return the idEstadoGeneral
     */
    public int getIdEstadoGeneral() {
        return idEstadoGeneral;
    }

    /**
     * @param idEstadoGeneral the idEstadoGeneral to set
     */
    public void setIdEstadoGeneral(int idEstadoGeneral) {
        this.idEstadoGeneral = idEstadoGeneral;
    }

    /**
     * @return the estadoButton
     */
    public Boolean getEstadoButton() {
        return estadoButton;
    }

    /**
     * @param estadoButton the estadoButton to set
     */
    public void setEstadoButton(Boolean estadoButton) {
        this.estadoButton = estadoButton;
    }

    /**
     * @return the nombreModalTitulo
     */
    public String getNombreModalTitulo() {
        return nombreModalTitulo;
    }

    /**
     * @param nombreModalTitulo the nombreModalTitulo to set
     */
    public void setNombreModalTitulo(String nombreModalTitulo) {
        this.nombreModalTitulo = nombreModalTitulo;
    }

    /**
     * @return the renderPlanGEneral
     */
    public Boolean getRenderEtapaGeneral() {
        return renderEtapaGeneral;
    }

    /**
     * @param renderPlanGEneral the renderPlanGEneral to set
     */
    public void setRenderEtapaGeneral(Boolean renderPlanGEneral) {
        this.renderEtapaGeneral = renderPlanGEneral;
    }

    /**
     * @return the listadoEtapasGeneraleses
     */
    public List<EtapasGenerales> getListadoEtapasGeneraleses() {
        return listadoEtapasGeneraleses;
    }

    /**
     * @param listadoEtapasGeneraleses the listadoEtapasGeneraleses to set
     */
    public void setListadoEtapasGeneraleses(List<EtapasGenerales> listadoEtapasGeneraleses) {
        this.listadoEtapasGeneraleses = listadoEtapasGeneraleses;
    }

    /**
     * @return the esEtapasGenerales
     */
    public EtapasGenerales getEtapasGenerales() {
        return etapasGenerales;
    }

    /**
     * @param esEtapasGenerales the esEtapasGenerales to set
     */
    public void setEtapasGenerales(EtapasGenerales etapasGenerales) {
        this.etapasGenerales = etapasGenerales;
    }

    /**
     * @return the estadoEtapasGenerales
     */
    public List<EstadoEtapasGenerales> getEstadoEtapasGenerales() {
        return estadoEtapasGenerales;
    }

    /**
     * @param estadoEtapasGenerales the estadoEtapasGenerales to set
     */
    public void setEstadoEtapasGenerales(List<EstadoEtapasGenerales> estadoEtapasGenerales) {
        this.estadoEtapasGenerales = estadoEtapasGenerales;
    }

    /**
     * @return the obligatorio
     */
    public String getObligatorio() {
        return obligatorio;
    }

    /**
     * @param obligatorio the obligatorio to set
     */
    public void setObligatorio(String obligatorio) {
        this.obligatorio = obligatorio;
    }

    /**
     * @return the idEstapaGeneral
     */
    public int getIdEstapaGeneral() {
        return idEstapaGeneral;
    }

    /**
     * @param idEstapaGeneral the idEstapaGeneral to set
     */
    public void setIdEstapaGeneral(int idEstapaGeneral) {
        this.idEstapaGeneral = idEstapaGeneral;
    }

}
