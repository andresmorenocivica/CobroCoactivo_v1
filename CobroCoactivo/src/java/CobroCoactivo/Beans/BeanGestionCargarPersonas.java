/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Beans;

import CobroCoactivo.Bo.GestionCargarPersonaBO;
import CobroCoactivo.Bo.GestionCargarPersonaImpBo;
import CobroCoactivo.Modelo.Deudas;
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
@ManagedBean(name = "gestionCargarPersonasBean")
@ViewScoped
public class BeanGestionCargarPersonas {

    private Client client;
    private int tipo;
    private String numero;
    private BeanLogin loginBO = new BeanLogin();
    private GestionCargarPersonaBO cargarPersonaBO = new GestionCargarPersonaImpBo();
    private List<Personas> listPersonas = new ArrayList<>();
    private List<Deudas> listDeudas = new ArrayList<>();
    private Personas personas = new Personas();

    /**
     * Creates a new instance of BeanGestionCargarPersonas
     */
    public BeanGestionCargarPersonas() {
    }

    @PostConstruct
    public void init() {
        setClient(ClientBuilder.newClient());
        buscarPersona();
    }

    public void buscarPersona() {
        try {

            getCargarPersonaBO().buscarPersona(this);

        } catch (Exception e) {
            e.printStackTrace();

            Log_Handler.registrarEvento("Error al cargar datos : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBO().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionPlanTrabajo" + "messageGeneral");
        }

    }

    public void verDeudas(Personas personas) {
        try {
            setPersonas(personas);
            getCargarPersonaBO().verDeuda(this);
        } catch (Exception e) {
            e.printStackTrace();
            Log_Handler.registrarEvento("Error al cargar datos : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBO().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionPlanTrabajo" + "messageGeneral");
        }

    }

    public void sincronizarDeuda() {
        try {
            
            getCargarPersonaBO().sincronizarDeuda(this);
            
                
        } catch (Exception e) {
            e.printStackTrace();
            Log_Handler.registrarEvento("Error al cargar datos : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBO().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionPlanTrabajo" + "messageGeneral");
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
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
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
     * @return the cargarPersonaBO
     */
    public GestionCargarPersonaBO getCargarPersonaBO() {
        return cargarPersonaBO;
    }

    /**
     * @param cargarPersonaBO the cargarPersonaBO to set
     */
    public void setCargarPersonaBO(GestionCargarPersonaBO cargarPersonaBO) {
        this.cargarPersonaBO = cargarPersonaBO;
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
     * @return the personas
     */
    public Personas getPersonas() {
        return personas;
    }

    /**
     * @param personas the personas to set
     */
    public void setPersonas(Personas personas) {
        this.personas = personas;
    }

}
