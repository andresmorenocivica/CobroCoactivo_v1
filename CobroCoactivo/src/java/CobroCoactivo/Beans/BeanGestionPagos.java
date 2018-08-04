/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Beans;

import CobroCoactivo.Bo.GestionPagosBo;
import CobroCoactivo.Bo.GestionPagosImpBO;
import CobroCoactivo.Modelo.Pagos;
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
@ManagedBean(name = "gestionPagosbean")
@ViewScoped
public class BeanGestionPagos {

    private Client client;
    private List<Personas> listPersonas = new ArrayList<>();
    private Personas personas = new Personas();
    private int tipo;
    private String numero;
    private BeanLogin loginBO = new BeanLogin();
    private GestionPagosBo pagosBo = new GestionPagosImpBO();
    private List<Pagos> listPagos = new ArrayList<>();

    public void listaPersonas() {
        try {
            getPagosBo().listarPersonas(this);

        } catch (Exception e) {
            e.printStackTrace();
            Log_Handler.registrarEvento("Error al cargar datos : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBO().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionPlanTrabajo" + "messageGeneral");
        }

    }

    public void verPagos(Personas personas) {
        try {
            setPersonas(personas);
            getPagosBo().verPagos(this);
        } catch (Exception e) {
            e.printStackTrace();
            Log_Handler.registrarEvento("Error al cargar datos : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBO().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionPlanTrabajo" + "messageGeneral");
        }
    }

    public void sincronizarPagos() {
        try {
            
            getPagosBo().sincronizarPagos(this);

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
        listaPersonas();
    }

    /**
     * Creates a new instance of BeanGestionPagos
     */
    public BeanGestionPagos() {

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
     * @return the pagosBo
     */
    public GestionPagosBo getPagosBo() {
        return pagosBo;
    }

    /**
     * @param pagosBo the pagosBo to set
     */
    public void setPagosBo(GestionPagosBo pagosBo) {
        this.pagosBo = pagosBo;
    }

    /**
     * @return the listPagos
     */
    public List<Pagos> getListPagos() {
        return listPagos;
    }

    /**
     * @param listPagos the listPagos to set
     */
    public void setListPagos(List<Pagos> listPagos) {
        this.listPagos = listPagos;
    }

}
