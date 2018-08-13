/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Beans;

import CobroCoactivo.Bo.GestionActosAdministractivosBO;
import CobroCoactivo.Bo.GestionActosAdministractivosImpBO;
import CobroCoactivo.Modelo.ActosAdministractivos;
import CobroCoactivo.Modelo.Deudas;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
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
@ManagedBean(name = "actosAdministractivobean")
@ViewScoped
public class BeanActosAdministractivo {

    private List<ActosAdministractivos> listActosAdministractivo = new ArrayList<>();
    private Client client;
    private GestionActosAdministractivosBO gestionActosAdministractivosBO = new GestionActosAdministractivosImpBO();
    private ActosAdministractivos actosAdministractivos = new ActosAdministractivos();
    private List<Deudas> listDeudas = new ArrayList<>();
    private BeanLogin loginBO;
    private String idUser;

    /**
     * Creates a new instance of BeanActosAdministractivo
     */
    public BeanActosAdministractivo() {
    }

    public void getTodosActosAdministractivos() {
        try {
            getGestionActosAdministractivosBO().getTodosActosAdministractivos(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void verCartera(ActosAdministractivos actosAdministractivos) {
        try {
            setActosAdministractivos(actosAdministractivos);
            getGestionActosAdministractivosBO().getCarteras(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sincronizarActosAdministractivo(){
        try {
            getGestionActosAdministractivosBO().sincronizarActosAdministractivo(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void init() {
        setClient(ClientBuilder.newClient());
        getTodosActosAdministractivos();
        FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        BeanLogin obj_ = ((BeanLogin) session.getAttribute("loginBean"));
        if (obj_.getID_Usuario() != null) {
            setIdUser(obj_.getID_Usuario());
        }

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
     * @param gestionActosAdministractivosBO the gestionActosAdministractivosBO
     * to set
     */
    public void setGestionActosAdministractivosBO(GestionActosAdministractivosBO gestionActosAdministractivosBO) {
        this.gestionActosAdministractivosBO = gestionActosAdministractivosBO;
    }

    /**
     * @return the actosAdministractivos
     */
    public ActosAdministractivos getActosAdministractivos() {
        return actosAdministractivos;
    }

    /**
     * @param actosAdministractivos the actosAdministractivos to set
     */
    public void setActosAdministractivos(ActosAdministractivos actosAdministractivos) {
        this.actosAdministractivos = actosAdministractivos;
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
