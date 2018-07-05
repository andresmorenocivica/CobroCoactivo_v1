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
import CobroCoactivo.Modelo.FasesGenerales;
import CobroCoactivo.Modelo.PlanGenerales;
import CobroCoactivo.Persistencia.CivEstadoFasesGenerales;
import CobroCoactivo.Utility.Log_Handler;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.primefaces.context.RequestContext;

/**
 *
 * @author jvergara
 */
@ManagedBean(name = "PlanGeneralbean")
@ViewScoped
public class BeanPlanGeneral {

    //objecto utilidos en el beanPlanGeneral
    private PlanGeneralBO planGeneralBO = new PlanGeneralImpBO();
    private PlanGenerales planGenerales = new PlanGenerales();
    private EtapasGenerales etapasGenerales = new EtapasGenerales();
    private FasesGenerales fasesGenerales = new FasesGenerales();
    private Part file;
    private BeanLogin loginBO;
    //termina objectos utilizados en el bean plan general

    // Lista de Objectos beanPlangeneral 
    private List<EstadoEtapasGenerales> estadoEtapasGenerales = new ArrayList<>();
    private List<PlanGenerales> listadoPlanGeneraleses = new ArrayList<>();
    private List<EtapasGenerales> listadoEtapasGeneraleses = new ArrayList<>();
    private List<EstadoPlanGenerales> ListadoEStadoPlanesGenerales = new ArrayList<>();
    private List<CivEstadoFasesGenerales> listCivEstadoFasesGeneraleses = new ArrayList<>();
    private List<FasesGenerales> listFasesGenerales = new ArrayList<>();
    //termina  lista de objectos beanplangeneral

    // variables primitivas utiliza en el beanPlangenearal
    private int idEstadoGeneral;
    private int idEstapaGeneral;
    private int idEStadoFasesGeneral;

    private Boolean estadoButton = true;
    private Boolean renderEtapaGeneral = false;
    private Boolean renderFaseGeneral = false;
    private boolean renderDivCambiarArchivo = false;

    private String obligatorio;
    private String nombreModalTitulo;
    //termina las variables primitivas utilizadas en el beanPlangeneral

    public BeanPlanGeneral() {

    }

    @PostConstruct
    public void init() {
        try {

            setLoginBO(new BeanLogin());
            setRenderFaseGeneral(false);
            setRenderEtapaGeneral(false);
            FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            setLoginBO((BeanLogin) session.getAttribute("loginBean"));

            setPlanGeneralBO(new PlanGeneralImpBO());
            getPlanGeneralBO().ListarPlanesGenerales(this);
            if (getListadoEStadoPlanesGenerales().size() == 0) {
                getPlanGeneralBO().ListarEstadoGenerales(this);
            }

            if (getEstadoEtapasGenerales().size() == 0) {
                getPlanGeneralBO().ListarEstadoEtapasGenerales(this);
            }
            if (getListCivEstadoFasesGeneraleses().size() == 0) {
                getPlanGeneralBO().listarEstadoFasesGenerales(this);
            }

        } catch (Exception ex) {
            Logger.getLogger(BeanPlanGeneral.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void actualizarFase() {
        try {

            getPlanGeneralBO().actualizarFase(this);
            getPlanGeneralBO().listarFasesGeneralesPorEtapa(this);
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage("planMensajeGeneral", new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Plan general creado exitosamente", "Plan General Creado exitosamente"));
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("$('#planGeneral').modal('hide')");

        }

    }

    public void guardarPlanGeneral() {

        try {
            getPlanGeneralBO().GuardarPlanGeneral(this);
            FacesContext.getCurrentInstance().addMessage("planMensajeGeneral", new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Plan general creado exitosamente", "Plan General Creado exitosamente"));
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("$('#planGeneral').modal('hide')");

        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al cargar datos : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBO().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", ""));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("Plan" + "messagePlanGeneral");
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
                    "Etapa general creada exitosamente", null));
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
        } else if (number == 2) {
            setEtapasGenerales(new EtapasGenerales());
        } else if (number == 3) {
            setFasesGenerales(new FasesGenerales());
            setRenderDivCambiarArchivo(false);
        }

    }

    public void cambiarArchivo() {
        try {
            setRenderDivCambiarArchivo(false);
        } catch (Exception e) {
        }
    }

    public void modalActualizarFase(FasesGenerales fasesGenerales) {
        try {
            setFile(null);
            setRenderDivCambiarArchivo(true);
            setEstadoButton(false);
            setFasesGenerales(fasesGenerales);

        } catch (Exception e) {
        }
    }

    public void ListarEtapaGeneralesPorIdPlanGeneral(PlanGenerales planGenerales) {
        try {
            setRenderEtapaGeneral(true);
            setRenderFaseGeneral(false);
            setPlanGenerales(planGenerales);
            getPlanGeneralBO().listarEtapaGeneralesPorIdPlanGeneral(this);

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }

    }

    public void listarFasesGeneralesPorFases(EtapasGenerales etapasGenerales) {
        try {
            setRenderFaseGeneral(true);
            setEtapasGenerales(etapasGenerales);
            getPlanGeneralBO().listarFasesGeneralesPorEtapa(this);

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }

    }

    public void guardarFasesGenerales() {
        try {
            getPlanGeneralBO().guardarFasesGeneral(this);

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
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

    /**
     * @return the renderFaseGeneral
     */
    public Boolean getRenderFaseGeneral() {
        return renderFaseGeneral;
    }

    /**
     * @param renderFaseGeneral the renderFaseGeneral to set
     */
    public void setRenderFaseGeneral(Boolean renderFaseGeneral) {
        this.renderFaseGeneral = renderFaseGeneral;
    }

    /**
     * @return the fasesGenerales
     */
    public FasesGenerales getFasesGenerales() {
        return fasesGenerales;
    }

    /**
     * @param fasesGenerales the fasesGenerales to set
     */
    public void setFasesGenerales(FasesGenerales fasesGenerales) {
        this.fasesGenerales = fasesGenerales;
    }

    /**
     * @return the file
     */
    public Part getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(Part file) {
        this.file = file;
    }

    /**
     * @return the listCivEstadoFasesGeneraleses
     */
    public List<CivEstadoFasesGenerales> getListCivEstadoFasesGeneraleses() {
        return listCivEstadoFasesGeneraleses;
    }

    /**
     * @param listCivEstadoFasesGeneraleses the listCivEstadoFasesGeneraleses to
     * set
     */
    public void setListCivEstadoFasesGeneraleses(List<CivEstadoFasesGenerales> listCivEstadoFasesGeneraleses) {
        this.listCivEstadoFasesGeneraleses = listCivEstadoFasesGeneraleses;
    }

    /**
     * @return the idEStadoFasesGeneral
     */
    public int getIdEStadoFasesGeneral() {
        return idEStadoFasesGeneral;
    }

    /**
     * @param idEStadoFasesGeneral the idEStadoFasesGeneral to set
     */
    public void setIdEStadoFasesGeneral(int idEStadoFasesGeneral) {
        this.idEStadoFasesGeneral = idEStadoFasesGeneral;
    }

    /**
     * @return the listFasesGenerales
     */
    public List<FasesGenerales> getListFasesGenerales() {
        return listFasesGenerales;
    }

    /**
     * @param listFasesGenerales the listFasesGenerales to set
     */
    public void setListFasesGenerales(List<FasesGenerales> listFasesGenerales) {
        this.listFasesGenerales = listFasesGenerales;
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
     * @return the renderDivCambiarArchivo
     */
    public boolean isRenderDivCambiarArchivo() {
        return renderDivCambiarArchivo;
    }

    /**
     * @param renderDivCambiarArchivo the renderDivCambiarArchivo to set
     */
    public void setRenderDivCambiarArchivo(boolean renderDivCambiarArchivo) {
        this.renderDivCambiarArchivo = renderDivCambiarArchivo;
    }

}
