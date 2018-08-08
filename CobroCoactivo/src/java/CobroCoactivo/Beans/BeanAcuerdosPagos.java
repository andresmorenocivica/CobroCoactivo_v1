/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Beans;

import CobroCoactivo.Bo.GestionAcuerdosPagosBO;
import CobroCoactivo.Bo.GestionAcuerdosPagosImpBO;
import CobroCoactivo.Modelo.Personas;
import CobroCoactivo.Utility.Log_Handler;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
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
    private List<Personas> listPersonas = new ArrayList<>();
    private BeanLogin loginBO = new BeanLogin();
    private GestionAcuerdosPagosBO acuerdosPagosBO = new GestionAcuerdosPagosImpBO();
    

    /**
     * Creates a new instance of BeanAcuerdosPagos
     */
    public BeanAcuerdosPagos() {
    }

    public void getListaPersonas() {
        try {
            getAcuerdosPagosBO().getListPersonasAcuerdosPagos(this);

        } catch (Exception e) {
            e.printStackTrace();
            Log_Handler.registrarEvento("Error al cargar datos : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBO().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionPlanTrabajo" + "messageGeneral");
        }
    }
    
    @PostConstruct
    public void init(){
        setClient(ClientBuilder.newClient());
        getListPersonas();
        
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
     * @return the listPersonas
     */
    public List<Personas> getListPersonas() {
        return listPersonas;
    }

    /**
     * @param listPersonas the listPersonas to set
     */
    public void setListPersonas(List<Personas> listPersonas) {
        this.listPersonas = listPersonas;
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

}
