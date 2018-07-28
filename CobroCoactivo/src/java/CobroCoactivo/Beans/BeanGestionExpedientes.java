/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Beans;

import CobroCoactivo.Bo.GestionExpedientesBO;
import CobroCoactivo.Bo.GestionExpedientesImpBO;
import CobroCoactivo.Bo.LoginBO;
import CobroCoactivo.Modelo.ArchivoDetExpedientes;
import CobroCoactivo.Modelo.DetalleExpedientes;
import CobroCoactivo.Modelo.Expedientes;
import CobroCoactivo.Utility.Log_Handler;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author emadrid
 *
 */
@ManagedBean(name = "gestionExpedientesBean")
@ViewScoped
public class BeanGestionExpedientes {

    private int idExpediente;
    private int idDetExpediente;
    private boolean pnlCarpeta = false;
    private boolean pnlSubcarpetas = false;
    private boolean pnlArchivo = false;
    private String referenciaExpediente;
    private List<Expedientes> listExpedientes = new ArrayList<>();
    private List<ArchivoDetExpedientes> listArchivoDetExpedientes = new ArrayList<>();
    private List<DetalleExpedientes> listDetalleExpedientes = new ArrayList<>();
    private GestionExpedientesBO gestionExpedientesBO;
    private LoginBO loginBO;
   

    @PostConstruct
    public void init() {
        try {
            setGestionExpedientesBO(new GestionExpedientesImpBO());
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    public void buscarExpedientes() {
        try {
            getGestionExpedientesBO().buscarExpediente(this);
            if (listExpedientes.size() > 0) {
                setPnlArchivo(false);
                setPnlCarpeta(true);
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    public void abrirCarpeta(int idExpediente) {
        try {
            setIdExpediente(idExpediente);
            getGestionExpedientesBO().abrirExpediente(this);
            if (listDetalleExpedientes.size() > 0) {
                setPnlCarpeta(false);
                setPnlSubcarpetas(true);
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    public void buscarArchivo(int idDetExpediente) {
        try {
            setIdDetExpediente(idDetExpediente);
            getGestionExpedientesBO().buscarArchivo(this);
            if (listArchivoDetExpedientes.size() > 0) {
                setPnlSubcarpetas(false);
                setPnlArchivo(true);
                
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }
    
    
     public void mostrarPdf(String data1) {
        try {
           
           
            File ficheroXLS = new File(data1);
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
     * @return the gestionExpedientesBO
     */
    public GestionExpedientesBO getGestionExpedientesBO() {
        return gestionExpedientesBO;
    }

    /**
     * @param gestionExpedientesBO the gestionExpedientesBO to set
     */
    public void setGestionExpedientesBO(GestionExpedientesBO gestionExpedientesBO) {
        this.gestionExpedientesBO = gestionExpedientesBO;
    }

    /**
     * @return the loginBO
     */
    public LoginBO getLoginBO() {
        return loginBO;
    }

    /**
     * @param loginBO the loginBO to set
     */
    public void setLoginBO(LoginBO loginBO) {
        this.loginBO = loginBO;
    }

    /**
     * @return the listExpedientes
     */
    public List<Expedientes> getListExpedientes() {
        return listExpedientes;
    }

    /**
     * @param listExpedientes the listExpedientes to set
     */
    public void setListExpedientes(List<Expedientes> listExpedientes) {
        this.listExpedientes = listExpedientes;
    }

    /**
     * @return the referenciaExpediente
     */
    public String getReferenciaExpediente() {
        return referenciaExpediente;
    }

    /**
     * @param referenciaExpediente the referenciaExpediente to set
     */
    public void setReferenciaExpediente(String referenciaExpediente) {
        this.referenciaExpediente = referenciaExpediente;
    }

    /**
     * @return the listDetalleExpedientes
     */
    public List<DetalleExpedientes> getListDetalleExpedientes() {
        return listDetalleExpedientes;
    }

    /**
     * @param listDetalleExpedientes the listDetalleExpedientes to set
     */
    public void setListDetalleExpedientes(List<DetalleExpedientes> listDetalleExpedientes) {
        this.listDetalleExpedientes = listDetalleExpedientes;
    }

    /**
     * @return the idExpediente
     */
    public int getIdExpediente() {
        return idExpediente;
    }

    /**
     * @param idExpediente the idExpediente to set
     */
    public void setIdExpediente(int idExpediente) {
        this.idExpediente = idExpediente;
    }

    /**
     * @return the pnlCarpeta
     */
    public boolean isPnlCarpeta() {
        return pnlCarpeta;
    }

    /**
     * @param pnlCarpeta the pnlCarpeta to set
     */
    public void setPnlCarpeta(boolean pnlCarpeta) {
        this.pnlCarpeta = pnlCarpeta;
    }

    /**
     * @return the pnlSubcarpetas
     */
    public boolean isPnlSubcarpetas() {
        return pnlSubcarpetas;
    }

    /**
     * @param pnlSubcarpetas the pnlSubcarpetas to set
     */
    public void setPnlSubcarpetas(boolean pnlSubcarpetas) {
        this.pnlSubcarpetas = pnlSubcarpetas;
    }

    /**
     * @return the idDetExpediente
     */
    public int getIdDetExpediente() {
        return idDetExpediente;
    }

    /**
     * @param idDetExpediente the idDetExpediente to set
     */
    public void setIdDetExpediente(int idDetExpediente) {
        this.idDetExpediente = idDetExpediente;
    }

    /**
     * @return the listArchivoDetExpedientes
     */
    public List<ArchivoDetExpedientes> getListArchivoDetExpedientes() {
        return listArchivoDetExpedientes;
    }

    /**
     * @param listArchivoDetExpedientes the listArchivoDetExpedientes to set
     */
    public void setListArchivoDetExpedientes(List<ArchivoDetExpedientes> listArchivoDetExpedientes) {
        this.listArchivoDetExpedientes = listArchivoDetExpedientes;
    }

    /**
     * @return the pnlArchivo
     */
    public boolean isPnlArchivo() {
        return pnlArchivo;
    }

    /**
     * @param pnlArchivo the pnlArchivo to set
     */
    public void setPnlArchivo(boolean pnlArchivo) {
        this.pnlArchivo = pnlArchivo;
    }

}