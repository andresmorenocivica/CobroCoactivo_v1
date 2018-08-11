/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Beans;

import CobroCoactivo.Bo.GestionPagosBo;
import CobroCoactivo.Bo.GestionPagosImpBO;
import CobroCoactivo.Modelo.Deudas;
import CobroCoactivo.Modelo.Pagos;
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
@ManagedBean(name = "gestionPagosbean")
@ViewScoped
public class BeanGestionPagos {

    private Client client;
    private String numeroFactura;
    private String idUser;

    private BeanLogin loginBO;
    private GestionPagosBo pagosBo = new GestionPagosImpBO();
    private List<Pagos> listPagos = new ArrayList<>();
    private List<Deudas> listDeudas = new ArrayList<>();
    private Pagos pagos = new Pagos();

    public void listaPagos() {
        try {
            getPagosBo().listarPagos(this);

        } catch (Exception e) {
            e.printStackTrace();
            Log_Handler.registrarEvento("Error al cargar datos : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getLoginBO().getID_Usuario()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionPlanTrabajo" + "messageGeneral");
        }

    }

    public void verCartera(Pagos pagos) {
        try {
            this.setPagos(pagos);
            setNumeroFactura(pagos.getNumero());
            getPagosBo().verCarteras(this);
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
        listaPagos();
        FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        BeanLogin obj_ = ((BeanLogin) session.getAttribute("loginBean"));
        if (obj_.getID_Usuario() != null) {
            setIdUser(obj_.getID_Usuario());
        }
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
     * @return the numero
     */
    public String getNumeroFactura() {
        return numeroFactura;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumeroFactura(String numero) {
        this.numeroFactura = numero;
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
     * @return the pagos
     */
    public Pagos getPagos() {
        return pagos;
    }

    /**
     * @param pagos the pagos to set
     */
    public void setPagos(Pagos pagos) {
        this.pagos = pagos;
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
