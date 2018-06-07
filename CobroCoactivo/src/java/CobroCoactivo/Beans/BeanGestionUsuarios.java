/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Beans;

import CobroCoactivo.Bo.GestionUsuariosBO;
import CobroCoactivo.Bo.GestionUsuariosImplBO;
import CobroCoactivo.Modelo.Usuarios;
import CobroCoactivo.Utility.Log_Handler;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author emadrid
 */
@ManagedBean(name = "gestionUsuariosBean")
@SessionScoped
public class BeanGestionUsuarios {

    private GestionUsuariosBO gestionUsuariosBO;
    private String NombreUsuario;
    private List<Usuarios> listadoUsuarios ;
    private int tipoBusqueda;

    @PostConstruct
    public void init() {
        try {
            setGestionUsuariosBO(new GestionUsuariosImplBO());
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    public void consultarUsuarios(int tipo) {
        try {
            setTipoBusqueda(tipo);
            getGestionUsuariosBO().consultarUsuario(this);
            getListadoUsuarios().size();
        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
           FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    /**
     * @return the gestionUsuariosBO
     */
    public GestionUsuariosBO getGestionUsuariosBO() {
        return gestionUsuariosBO;
    }

    /**
     * @param gestionUsuariosBO the gestionUsuariosBO to set
     */
    public void setGestionUsuariosBO(GestionUsuariosBO gestionUsuariosBO) {
        this.gestionUsuariosBO = gestionUsuariosBO;
    }

    /**
     * @return the NombreUsuario
     */
    public String getNombreUsuario() {
        return NombreUsuario;
    }

    /**
     * @param NombreUsuario the NombreUsuario to set
     */
    public void setNombreUsuario(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }

    /**
     * @return the listadoUsuarios
     */
    public List<Usuarios> getListadoUsuarios() {
        return listadoUsuarios;
    }

    /**
     * @param listadoUsuarios the listadoUsuarios to set
     */
    public void setListadoUsuarios(List<Usuarios> listadoUsuarios) {
        this.listadoUsuarios = listadoUsuarios;
    }

    /**
     * @return the tipoBusqueda
     */
    public int getTipoBusqueda() {
        return tipoBusqueda;
    }

    /**
     * @param tipoBusqueda the tipoBusqueda to set
     */
    public void setTipoBusqueda(int tipoBusqueda) {
        this.tipoBusqueda = tipoBusqueda;
    }
}
