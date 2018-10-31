/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Beans;

import CobroCoactivo.Utility.Log_Handler;
import java.nio.file.Path;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author emadrid
 */
@ManagedBean(name = "cargueExcelCarteraBean")
@ViewScoped
public class BeanCargueExcelCartera {

    private String idUser;
    private Path archivo;
    private BeanLogin loginBO;

    @PostConstruct
    public void init() {
        try {
            FacesContext contexts = javax.faces.context.FacesContext.getCurrentInstance();
            HttpSession session1 = (HttpSession) contexts.getExternalContext().getSession(false);
            BeanLogin obj = ((BeanLogin) session1.getAttribute("loginBean"));
            if (obj.getID_Usuario() != null) {
                setIdUser(obj.getID_Usuario());
            }
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al cargar datos : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getIdUser()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
        }
    }

    public void procesarExcel() {
        try {

        } catch (Exception e) {
            Log_Handler.registrarEvento("Error al cargar datos : ", e, Log_Handler.ERROR, getClass(), Integer.parseInt(getIdUser()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
        }
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

    /**
     * @return the archivo
     */
    public Path getArchivo() {
        return archivo;
    }

    /**
     * @param archivo the archivo to set
     */
    public void setArchivo(Path archivo) {
        this.archivo = archivo;
    }

}
