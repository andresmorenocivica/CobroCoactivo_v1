/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Beans;

import CobroCoactivo.Bo.CargueArchivoDeudasBO;
import CobroCoactivo.Bo.CargueArchivoDeudasImpBO;
import CobroCoactivo.Modelo.CargueDeudasImpuesto;
import CobroCoactivo.Utility.Log_Handler;
import CobroCoactivo.Utility.UtilityCargues;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author AMORENO
 */
@ManagedBean(name = "cargueArchivoDeudasBean")
@ViewScoped
public class BeanCargueArchivoDeudas {

    private CargueArchivoDeudasBO cargueArchivoDeudasBO = new CargueArchivoDeudasImpBO();
    private BeanLogin loginBO;
    private Part archivoCague;
    private int cargados,sinCargar;
    private List<CargueDeudasImpuesto>listaDeudasImpuestoCorrectas = new ArrayList<>();
    private List<UtilityCargues> listaDeudasImpuestoIncorrectas =  new ArrayList<>();
    private boolean visibleTablaDeudasCorrectas = false;

    @PostConstruct
    public void init() {
        try {
        FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        setLoginBO((BeanLogin) session.getAttribute("loginBean"));
        
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    public void cargarArchivoDeudas() {
        try {
            setListaDeudasImpuestoCorrectas(new ArrayList<>());
            setListaDeudasImpuestoIncorrectas(new ArrayList<>());
            getCargueArchivoDeudasBO().procesarCargueArchivoDeudas(this);
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }
    public void mostarTabla(boolean sw){
        try {
            setVisibleTablaDeudasCorrectas(sw);
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    /**
     * @return the archivoCague
     */
    public Part getArchivoCague() {
        return archivoCague;
    }

    /**
     * @param archivoCague the archivoCague to set
     */
    public void setArchivoCague(Part archivoCague) {
        this.archivoCague = archivoCague;
    }

    /**
     * @return the cargueArchivoDeudasBO
     */
    public CargueArchivoDeudasBO getCargueArchivoDeudasBO() {
        return cargueArchivoDeudasBO;
    }

    /**
     * @param cargueArchivoDeudasBO the cargueArchivoDeudasBO to set
     */
    public void setCargueArchivoDeudasBO(CargueArchivoDeudasBO cargueArchivoDeudasBO) {
        this.cargueArchivoDeudasBO = cargueArchivoDeudasBO;
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
     * @return the cargados
     */
    public int getCargados() {
        return cargados;
    }

    /**
     * @param cargados the cargados to set
     */
    public void setCargados(int cargados) {
        this.cargados = cargados;
    }

    /**
     * @return the sinCargar
     */
    public int getSinCargar() {
        return sinCargar;
    }

    /**
     * @param sinCargar the sinCargar to set
     */
    public void setSinCargar(int sinCargar) {
        this.sinCargar = sinCargar;
    }

    /**
     * @return the listaDeudasImpuestoCorrectas
     */
    public List<CargueDeudasImpuesto> getListaDeudasImpuestoCorrectas() {
        return listaDeudasImpuestoCorrectas;
    }

    /**
     * @param listaDeudasImpuestoCorrectas the listaDeudasImpuestoCorrectas to set
     */
    public void setListaDeudasImpuestoCorrectas(List<CargueDeudasImpuesto> listaDeudasImpuestoCorrectas) {
        this.listaDeudasImpuestoCorrectas = listaDeudasImpuestoCorrectas;
    }

    /**
     * @return the listaDeudasImpuestoIncorrectas
     */
    public List<UtilityCargues> getListaDeudasImpuestoIncorrectas() {
        return listaDeudasImpuestoIncorrectas;
    }

    /**
     * @param listaDeudasImpuestoIncorrectas the listaDeudasImpuestoIncorrectas to set
     */
    public void setListaDeudasImpuestoIncorrectas(List<UtilityCargues> listaDeudasImpuestoIncorrectas) {
        this.listaDeudasImpuestoIncorrectas = listaDeudasImpuestoIncorrectas;
    }

    /**
     * @return the visibleTablaDeudasCorrectas
     */
    public boolean isVisibleTablaDeudasCorrectas() {
        return visibleTablaDeudasCorrectas;
    }

    /**
     * @param visibleTablaDeudasCorrectas the visibleTablaDeudasCorrectas to set
     */
    public void setVisibleTablaDeudasCorrectas(boolean visibleTablaDeudasCorrectas) {
        this.visibleTablaDeudasCorrectas = visibleTablaDeudasCorrectas;
    }

 

    

}
