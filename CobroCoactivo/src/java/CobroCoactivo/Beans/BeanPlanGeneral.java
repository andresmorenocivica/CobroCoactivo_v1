/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Beans;

import CobroCoactivo.Bo.PlanGeneralBO;
import CobroCoactivo.Bo.PlanGeneralImpBO;
import CobroCoactivo.Modelo.EstadoPlanGenerales;
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

/**
 *
 * @author jvergara
 */
@ManagedBean(name = "PlanGeneralbean")
@ViewScoped
public class BeanPlanGeneral implements Serializable{

    private PlanGenerales planGenerales = new PlanGenerales();
    private PlanGeneralBO planGeneralBO;
    private List<PlanGenerales> listadoPlanGeneraleses = new ArrayList<>();
    private List<EstadoPlanGenerales>ListadoEStadoPlanesGenerales = new ArrayList<>();
    private int idEstadoGeneral;
            
    

    public BeanPlanGeneral() {

    }

    @PostConstruct
    public void init() {
        try {
            setPlanGeneralBO(new PlanGeneralImpBO());
            getPlanGeneralBO().ListarPlanesGenerales(this);
            getPlanGeneralBO().ListarEstadoGenerales(this);
        } catch (Exception ex) {
            Logger.getLogger(BeanPlanGeneral.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void guardarPlanGeneral() {

        try {
            getPlanGeneralBO().GuardarPlanGeneral(this);
           
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
     * @param ListadoEStadoPlanesGenerales the ListadoEStadoPlanesGenerales to set
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


}
