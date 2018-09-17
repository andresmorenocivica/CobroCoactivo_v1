/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Beans;

import CobroCoactivo.Bo.GestionExcepcionesBO;
import CobroCoactivo.Bo.GestionExcepcionesImpBo;
import CobroCoactivo.Exception.ExcepcionesException;
import CobroCoactivo.Modelo.Deudas;
import CobroCoactivo.Modelo.Excepciones;
import CobroCoactivo.Modelo.Movimientos;
import CobroCoactivo.Utility.Log_Handler;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author emadridp
 */
@ManagedBean(name = "gestionExcepcionesBean")
@ViewScoped
public class BeanGestionExcepciones {

    //@Size(min = 1, message = "Ingrese el numero radicado")
    private String numRadicado;
    private String idUser;
    private String documentoPersona;
    private int tipoDocumentoPersona;
    private int idMovimiento;
    private Part file;
    private Date fechaRadicado;
    private boolean pnlListaDeuda = false;
    private boolean pnlFormulario = false;
    private boolean pnlListaExcepciones = false;
    private List<Excepciones> listaExcepciones = new ArrayList<>();
    private List<Deudas> listaDeudas = new ArrayList<>();
    private List<Deudas> listaDeudasSelecionada = new ArrayList<>();
    private List<Movimientos> listaMovimientos = new ArrayList<>();
    private Excepciones excepciones;
    private BeanLogin loginBO;
    private GestionExcepcionesBO gestionExcepcionesBO;

    @PostConstruct
    public void init() {
        try {
            FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            BeanLogin obj = ((BeanLogin) session.getAttribute("loginBean"));
            if (obj.getID_Usuario() != null) {
                setIdUser(obj.getID_Usuario());
            }
            setGestionExcepcionesBO(new GestionExcepcionesImpBo());
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al cargar datos : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getIdUser()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionPlanTrabajo" + "messageGeneral");
        }
    }

    public void buscarExcepcion() {
        try {
            getGestionExcepcionesBO().buscarExcepcion(this);
        } catch (ExcepcionesException ee) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ee.getNivelFacesMessage(), "", ee.getMessage()));
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al buscar la excepcion: ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getIdUser()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
        }
    }

    public void buscarPersona() {
        try {
            getGestionExcepcionesBO().buscarPersona(this);
        } catch (ExcepcionesException ee) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ee.getNivelFacesMessage(), "", ee.getMessage()));
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al buscar persona: ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getIdUser()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
        }
    }

    public void mostrarPnlFormulario(){
        try {
            getGestionExcepcionesBO().llenarListaDeudaSeleccionada(this);
            if (listaDeudasSelecionada.size() > 0) {
                setPnlFormulario(true);
            } else {
                setPnlFormulario(false);
            }
        } catch (ExcepcionesException ee) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ee.getNivelFacesMessage(), "", ee.getMessage()));
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al buscar persona: ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getIdUser()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
        }

    }

    public void guardarExcepcion() {
        try {
            getGestionExcepcionesBO().guardarExcepcion(this);
        } catch (ExcepcionesException ee) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ee.getNivelFacesMessage(), "", ee.getMessage()));
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al guardar la excepcion: ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getIdUser()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionPlanTrabajo" + "messageGeneral");
        }
    }

    /**
     * @return the gestionExcepcionesBO
     */
    public GestionExcepcionesBO getGestionExcepcionesBO() {
        return gestionExcepcionesBO;
    }

    /**
     * @param gestionExcepcionesBO the gestionExcepcionesBO to set
     */
    public void setGestionExcepcionesBO(GestionExcepcionesBO gestionExcepcionesBO) {
        this.gestionExcepcionesBO = gestionExcepcionesBO;
    }

    /**
     * @return the numRadicado
     */
    public String getNumRadicado() {
        return numRadicado;
    }

    /**
     * @param numRadicado the numRadicado to set
     */
    public void setNumRadicado(String numRadicado) {
        this.numRadicado = numRadicado;
    }

    /**
     * @return the listaExcepciones
     */
    public List<Excepciones> getListaExcepciones() {
        return listaExcepciones;
    }

    /**
     * @param listaExcepciones the listaExcepciones to set
     */
    public void setListaExcepciones(List<Excepciones> listaExcepciones) {
        this.listaExcepciones = listaExcepciones;
    }

    /**
     * @return the idUser
     */
    public String getIdUser() {
        return idUser;
    }

    /**
     * @param idUser the idUser to set
     */
    public void setIdUser(String idUser) {
        this.idUser = idUser;
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
     * @return the excepciones
     */
    public Excepciones getExcepciones() {
        return excepciones;
    }

    /**
     * @param excepciones the excepciones to set
     */
    public void setExcepciones(Excepciones excepciones) {
        this.excepciones = excepciones;
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
     * @return the pnlListaDeuda
     */
    public boolean isPnlListaDeuda() {
        return pnlListaDeuda;
    }

    /**
     * @param pnlListaDeuda the pnlListaDeuda to set
     */
    public void setPnlListaDeuda(boolean pnlListaDeuda) {
        this.pnlListaDeuda = pnlListaDeuda;
    }

    /**
     * @return the pnlFormulario
     */
    public boolean isPnlFormulario() {
        return pnlFormulario;
    }

    /**
     * @param pnlFormulario the pnlFormulario to set
     */
    public void setPnlFormulario(boolean pnlFormulario) {
        this.pnlFormulario = pnlFormulario;
    }

    /**
     * @return the fechaRadicado
     */
    public Date getFechaRadicado() {
        return fechaRadicado;
    }

    /**
     * @param fechaRadicado the fechaRadicado to set
     */
    public void setFechaRadicado(Date fechaRadicado) {
        this.fechaRadicado = fechaRadicado;
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
     * @return the listaDeudasSelecionada
     */
    public List<Deudas> getListaDeudasSelecionada() {
        return listaDeudasSelecionada;
    }

    /**
     * @param listaDeudasSelecionada the listaDeudasSelecionada to set
     */
    public void setListaDeudasSelecionada(List<Deudas> listaDeudasSelecionada) {
        this.listaDeudasSelecionada = listaDeudasSelecionada;
    }

    /**
     * @return the pnlListaExcepciones
     */
    public boolean isPnlListaExcepciones() {
        return pnlListaExcepciones;
    }

    /**
     * @param pnlListaExcepciones the pnlListaExcepciones to set
     */
    public void setPnlListaExcepciones(boolean pnlListaExcepciones) {
        this.pnlListaExcepciones = pnlListaExcepciones;
    }

    /**
     * @return the listaMovimientos
     */
    public List<Movimientos> getListaMovimientos() {
        return listaMovimientos;
    }

    /**
     * @param listaMovimientos the listaMovimientos to set
     */
    public void setListaMovimientos(List<Movimientos> listaMovimientos) {
        this.listaMovimientos = listaMovimientos;
    }

    /**
     * @return the idMovimiento
     */
    public int getIdMovimiento() {
        return idMovimiento;
    }

    /**
     * @param idMovimiento the idMovimiento to set
     */
    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

}
