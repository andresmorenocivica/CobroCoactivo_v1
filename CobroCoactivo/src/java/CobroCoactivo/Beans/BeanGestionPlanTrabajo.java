/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Beans;

import CobroCoactivo.Bo.GestionPlanTrabajoBO;
import CobroCoactivo.Bo.GestionPlanTrabajoImpBO;
import CobroCoactivo.Modelo.EtapasGenerales;
import CobroCoactivo.Modelo.EtapasTrabajos;
import CobroCoactivo.Modelo.FasesGenerales;
import CobroCoactivo.Modelo.FasesTrabajos;
import CobroCoactivo.Modelo.PlanGenerales;
import CobroCoactivo.Modelo.PlanTrabajos;
import CobroCoactivo.Utility.Log_Handler;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * el bean encargado de administrar todo lo referente a la vista plan trabajo
 *
 * @author jesison
 * @version 1.0. 27/06/2018
 */
@ManagedBean(name = "gestionPlanTrabajoBean")
@ViewScoped
public class BeanGestionPlanTrabajo {
    
    private List<PlanGenerales> listPlanGenerales = new ArrayList<>();
    private List<PlanTrabajos> listPlanTrabajos = new ArrayList<>();
    private GestionPlanTrabajoBO gestionPlanTrabajoBO = new GestionPlanTrabajoImpBO();
    private PlanTrabajos planTrabajos = new PlanTrabajos();
    private List<EtapasTrabajos> listEtapaTrabajos = new ArrayList<>();
    private List<EtapasGenerales> listEtapaGenerales = new ArrayList<>();
    private boolean renderEtapaTrabajo = false;
    private EtapasTrabajos etapasTrabajos = new EtapasTrabajos();
    private List<FasesTrabajos> listFasesTrabajoses = new ArrayList<>();
    private List<FasesGenerales> listFasesGeneral = new ArrayList<>();
    private boolean renderFaseTrabajo;
    private BeanLogin loginBO;

    /**
     * metodo que se ejecuta despues de iniciar el bean
     */
    @PostConstruct
    public void init() {
        try {
            getGestionPlanTrabajoBO().getListaTrabajo(this);
            getGestionPlanTrabajoBO().getListPlanGenaral(this);
            
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al cargar datos : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBO().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionPlanTrabajo" + "messageGeneral");
        }
        
    }

    /**
     * guardar un plan de trabajo
     */
    public void guardarPlanTrabajo() {
        try {
            getGestionPlanTrabajoBO().guardarPlanTrabajo(this);
            
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al cargar datos : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBO().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionPlanTrabajo" + "messageGeneral");
        }
    }
    
    public void guardarFasesTrabajo() {
        try {
            getGestionPlanTrabajoBO().guardarFaseTrabajo(this);
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al cargar datos : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBO().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionPlanTrabajo" + "messageGeneral");
        }
    }
    
    public void guardarEtapaTrabajo() {
        try {
            getGestionPlanTrabajoBO().guardarEtapaTrabajo(this);
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al cargar datos : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBO().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionPlanTrabajo" + "messageGeneral");
        }
    }
    
    public void listaEtapaTrabajo(PlanTrabajos planTrabajos) {
        try {
            setPlanTrabajos(planTrabajos);
            setRenderEtapaTrabajo(true);
            setRenderFaseTrabajo(false);
            getGestionPlanTrabajoBO().getListEtapaTrabajo(this);
            getGestionPlanTrabajoBO().getListEtapaGenerales(this);
            getListEtapaGenerales();
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al cargar datos : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBO().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionPlanTrabajo" + "messageGeneral");
        }
        
    }
    
    public void listarFasestrabajo(EtapasTrabajos etapasTrabajos) {
        
        try {
            setEtapasTrabajos(etapasTrabajos);
            setRenderFaseTrabajo(true);
            getGestionPlanTrabajoBO().getFases(this);
            getGestionPlanTrabajoBO().getfasesGenerales(this);
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al cargar datos : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBO().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionPlanTrabajo" + "messageGeneral");
        }
    }
    
    public void mostrarPdf(String archivo) {
        try {
            File ficheroXLS = new File(archivo);
            FacesContext ctx = FacesContext.getCurrentInstance();
            FileInputStream fis = new FileInputStream(ficheroXLS);
            byte[] bytes = new byte[1000];
            int read = 0;
            if (!ctx.getResponseComplete()) {
                String fileName = ficheroXLS.getName();
                String contentType = "application/pdf";
                HttpServletResponse response
                        = (HttpServletResponse) ctx.getExternalContext().getResponse();
                response.setContentType(contentType);
                response.setHeader("Content-Disposition",
                        "attachment;filename=\"" + fileName + "\"");
                ServletOutputStream out = response.getOutputStream();
                while ((read = fis.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                out.flush();
                out.close();
                ctx.responseComplete();
            }
            
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al cargar datos : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBO().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionPlanTrabajo" + "messageGeneral");
        }
    }
    
    public boolean renderBottonAdicionar() {
        if (getListPlanGenerales().size() == 0 || getListPlanGenerales() == null) {
            return false;
        }
        
        return true;
    }
    
    public boolean renderBottonAdicionarEtapas() {
        if (getListEtapaGenerales().size() == 0 || getListEtapaGenerales() == null) {
            return false;
        }
        
        return true;
    }
    
    public boolean renderBotonAdicionatFases() {
        if (getListFasesGeneral().size() == 0 || getListFasesGeneral() == null) {
            return false;
        }
        
        return true;
        
    }

    /**
     * Contructor por defecto
     */
    public BeanGestionPlanTrabajo() {
    }

    /**
     * el que controla el acesso a la base de datos es el controlador
     *
     * @return the gestionPlanTrabajoBO
     */
    public GestionPlanTrabajoBO getGestionPlanTrabajoBO() {
        return gestionPlanTrabajoBO;
    }

    /**
     * @param gestionPlanTrabajoBO the gestionPlanTrabajoBO to set
     */
    public void setGestionPlanTrabajoBO(GestionPlanTrabajoBO gestionPlanTrabajoBO) {
        this.gestionPlanTrabajoBO = gestionPlanTrabajoBO;
    }

    /**
     * @return the listPlanTrabajos
     */
    public List<PlanTrabajos> getListPlanTrabajos() {
        return listPlanTrabajos;
    }

    /**
     * @param listPlanTrabajos the listPlanTrabajos to set
     */
    public void setListPlanTrabajos(List<PlanTrabajos> listPlanTrabajos) {
        this.listPlanTrabajos = listPlanTrabajos;
    }

    /**
     * @return the listPlanGenerales
     */
    public List<PlanGenerales> getListPlanGenerales() {
        return listPlanGenerales;
    }

    /**
     * @param listPlanGenerales the listPlanGenerales to set
     */
    public void setListPlanGenerales(List<PlanGenerales> listPlanGenerales) {
        this.listPlanGenerales = listPlanGenerales;
    }

    /**
     * @return the listEtapaTrabajos
     */
    public List<EtapasTrabajos> getListEtapaTrabajos() {
        return listEtapaTrabajos;
    }

    /**
     * @param listEtapaTrabajos the listEtapaTrabajos to set
     */
    public void setListEtapaTrabajos(List<EtapasTrabajos> listEtapaTrabajos) {
        this.listEtapaTrabajos = listEtapaTrabajos;
    }

    /**
     * @return the renderEtapaTrabajo
     */
    public boolean isRenderEtapaTrabajo() {
        return renderEtapaTrabajo;
    }

    /**
     * @param renderEtapaTrabajo the renderEtapaTrabajo to set
     */
    public void setRenderEtapaTrabajo(boolean renderEtapaTrabajo) {
        this.renderEtapaTrabajo = renderEtapaTrabajo;
    }

    /**
     * @return the planTrabajos
     */
    public PlanTrabajos getPlanTrabajos() {
        return planTrabajos;
    }

    /**
     * @param planTrabajos the planTrabajos to set
     */
    public void setPlanTrabajos(PlanTrabajos planTrabajos) {
        this.planTrabajos = planTrabajos;
    }

    /**
     * @return the renderFaseTrabajo
     */
    public boolean isRenderFaseTrabajo() {
        return renderFaseTrabajo;
    }

    /**
     * @param renderFaseTrabajo the renderFaseTrabajo to set
     */
    public void setRenderFaseTrabajo(boolean renderFaseTrabajo) {
        this.renderFaseTrabajo = renderFaseTrabajo;
    }

    /**
     * @return the etapasTrabajos
     */
    public EtapasTrabajos getEtapasTrabajos() {
        return etapasTrabajos;
    }

    /**
     * @param etapasTrabajos the etapasTrabajos to set
     */
    public void setEtapasTrabajos(EtapasTrabajos etapasTrabajos) {
        this.etapasTrabajos = etapasTrabajos;
    }

    /**
     * @return the listFasesTrabajoses
     */
    public List<FasesTrabajos> getListFasesTrabajoses() {
        return listFasesTrabajoses;
    }

    /**
     * @param listFasesTrabajoses the listFasesTrabajoses to set
     */
    public void setListFasesTrabajoses(List<FasesTrabajos> listFasesTrabajoses) {
        this.listFasesTrabajoses = listFasesTrabajoses;
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
     * @return the listEtapaGenerales
     */
    public List<EtapasGenerales> getListEtapaGenerales() {
        return listEtapaGenerales;
    }

    /**
     * @param listEtapaGenerales the listEtapaGenerales to set
     */
    public void setListEtapaGenerales(List<EtapasGenerales> listEtapaGenerales) {
        this.listEtapaGenerales = listEtapaGenerales;
    }

    /**
     * @return the listFasesGeneral
     */
    public List<FasesGenerales> getListFasesGeneral() {
        return listFasesGeneral;
    }

    /**
     * @param listFasesGeneral the listFasesGeneral to set
     */
    public void setListFasesGeneral(List<FasesGenerales> listFasesGeneral) {
        this.listFasesGeneral = listFasesGeneral;
    }
    
}
