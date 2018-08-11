/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Beans;

import CobroCoactivo.Bo.GestionActosAdministractivosBO;
import CobroCoactivo.Bo.GestionActosAdministractivosImpBO;
import CobroCoactivo.Modelo.ActosAdministractivos;
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
@ManagedBean(name = "actosAdministractivobean")
@ViewScoped
public class BeanActosAdministractivo {
    
    
   private List<ActosAdministractivos> listActosAdministractivo =  new ArrayList<>();
   private Client client;
   private GestionActosAdministractivosBO gestionActosAdministractivosBO =  new GestionActosAdministractivosImpBO();
    
    

    /**
     * Creates a new instance of BeanActosAdministractivo
     */
    public BeanActosAdministractivo() {
    }
    
    public void getTodosActosAdministractivos(){
        try {
            getGestionActosAdministractivosBO().getTodosActosAdministractivos(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    @PostConstruct
    public void init(){
        setClient(ClientBuilder.newClient());
        getTodosActosAdministractivos();
        
    }

    /**
     * @return the listActosAdministractivo
     */
    public List<ActosAdministractivos> getListActosAdministractivo() {
        return listActosAdministractivo;
    }

    /**
     * @param listActosAdministractivo the listActosAdministractivo to set
     */
    public void setListActosAdministractivo(List<ActosAdministractivos> listActosAdministractivo) {
        this.listActosAdministractivo = listActosAdministractivo;
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
     * @return the gestionActosAdministractivosBO
     */
    public GestionActosAdministractivosBO getGestionActosAdministractivosBO() {
        return gestionActosAdministractivosBO;
    }

    /**
     * @param gestionActosAdministractivosBO the gestionActosAdministractivosBO to set
     */
    public void setGestionActosAdministractivosBO(GestionActosAdministractivosBO gestionActosAdministractivosBO) {
        this.gestionActosAdministractivosBO = gestionActosAdministractivosBO;
    }
    
}
