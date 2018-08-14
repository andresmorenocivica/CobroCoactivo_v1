/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Beans;

import CobroCoactivo.Bo.GestionAcuerdosPagosBO;
import CobroCoactivo.Bo.GestionAcuerdosPagosImpBO;
import CobroCoactivo.Modelo.Deudas;
import CobroCoactivo.Modelo.Financiacion;
import CobroCoactivo.Utility.Log_Handler;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

/**
 *
 * @author jvergara
 */
@ManagedBean(name = "acuerdosPagosbean")
@ViewScoped
public class BeanAcuerdosPagos {

    private Client client;
    private List<Financiacion> listFinanciacion = new ArrayList<>();
    private BeanLogin loginBO = new BeanLogin();
    private GestionAcuerdosPagosBO acuerdosPagosBO = new GestionAcuerdosPagosImpBO();
    private Financiacion financiacion = new Financiacion();
    private List<Deudas> listDeudas = new ArrayList<>();
    private String idUser;

    /**
     * Creates a new instance of BeanAcuerdosPagos
     */
    public BeanAcuerdosPagos() {
    }

    public void getListaAcuerdosPagos() {
        try {
            getAcuerdosPagosBO().getListAcuerdosPagos(this);

        } catch (Exception e) {
            e.printStackTrace();
            Log_Handler.registrarEvento("Error al cargar datos : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBO().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionPlanTrabajo" + "messageGeneral");
        }
    }

    public void verCarteras(Financiacion financiacion) {
        try {
            setFinanciacion(financiacion);
            getAcuerdosPagosBO().getListCarteras(this);

        } catch (Exception e) {
            e.printStackTrace();
            Log_Handler.registrarEvento("Error al cargar datos : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBO().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionPlanTrabajo" + "messageGeneral");
        }
    }

    public void sincronizar() {
        try {
            getAcuerdosPagosBO().sincronizar(this);

        } catch (Exception e) {
            e.printStackTrace();
            Log_Handler.registrarEvento("Error al cargar datos : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBO().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionPlanTrabajo" + "messageGeneral");
        }
    }

    @PostConstruct
    public void init() {
        setClient(ClientBuilder.newClient());
        getListaAcuerdosPagos();
        FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        BeanLogin obj_ = ((BeanLogin) session.getAttribute("loginBean"));
        if (obj_.getID_Usuario() != null) {
            setIdUser(obj_.getID_Usuario());
        }

    }

    /**
     * @return the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(Client client) {
        this.client = client;
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
     * @return the acuerdosPagosBO
     */
    public GestionAcuerdosPagosBO getAcuerdosPagosBO() {
        return acuerdosPagosBO;
    }

    /**
     * @param acuerdosPagosBO the acuerdosPagosBO to set
     */
    public void setAcuerdosPagosBO(GestionAcuerdosPagosBO acuerdosPagosBO) {
        this.acuerdosPagosBO = acuerdosPagosBO;
    }

    /**
     * @return the listFinanciacion
     */
    public List<Financiacion> getListFinanciacion() {
        return listFinanciacion;
    }

    /**
     * @param listFinanciacion the listFinanciacion to set
     */
    public void setListFinanciacion(List<Financiacion> listFinanciacion) {
        this.listFinanciacion = listFinanciacion;
    }

    /**
     * @return the financiacion
     */
    public Financiacion getFinanciacion() {
        return financiacion;
    }

    /**
     * @param financiacion the financiacion to set
     */
    public void setFinanciacion(Financiacion financiacion) {
        this.financiacion = financiacion;
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

}
