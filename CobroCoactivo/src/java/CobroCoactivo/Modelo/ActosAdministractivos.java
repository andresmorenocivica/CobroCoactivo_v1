/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Modelo;

import java.util.Date;


/**
 *
 * @author jvergara
 */
public class ActosAdministractivos {
    
    
     private Long actId;
     private Usuarios civUsuarios;
     private String actNumero;
     private Date actFecha;

    public ActosAdministractivos(Long actId, Usuarios civUsuarios, String actNumero, Date actFecha) {
        this.actId = actId;
        this.civUsuarios = civUsuarios;
        this.actNumero = actNumero;
        this.actFecha = actFecha;
    }

    public ActosAdministractivos() {
    }

    /**
     * @return the actId
     */
    public Long getActId() {
        return actId;
    }

    /**
     * @param actId the actId to set
     */
    public void setActId(Long actId) {
        this.actId = actId;
    }

    /**
     * @return the civUsuarios
     */
    public Usuarios getCivUsuarios() {
        return civUsuarios;
    }

    /**
     * @param civUsuarios the civUsuarios to set
     */
    public void setCivUsuarios(Usuarios civUsuarios) {
        this.civUsuarios = civUsuarios;
    }

    /**
     * @return the actNumero
     */
    public String getActNumero() {
        return actNumero;
    }

    /**
     * @param actNumero the actNumero to set
     */
    public void setActNumero(String actNumero) {
        this.actNumero = actNumero;
    }

    /**
     * @return the actFecha
     */
    public Date getActFecha() {
        return actFecha;
    }

    /**
     * @param actFecha the actFecha to set
     */
    public void setActFecha(Date actFecha) {
        this.actFecha = actFecha;
    }
     
     
     
    
}
