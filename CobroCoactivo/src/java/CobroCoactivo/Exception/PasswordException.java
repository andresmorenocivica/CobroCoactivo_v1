/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package CobroCoactivo.Exception;

import javax.faces.application.FacesMessage;

/**
 * Clase que define una Excepcion en el módulo de Password
 *
 * @author Roymer Camacho
 */
public class PasswordException extends Exception {

    private int nivel;

    /**
     * Constructor donde se establece el mensaje de error
     *
     * @param message Mensaje de error
     */
    public PasswordException(String message, int nivel) {
        super(message);
        this.nivel = nivel;
    }

    /**
     * Returna nivel de Severidad de mensajes de Faces
     *
     * @return Nivel del mensaje Severity
     */
    public FacesMessage.Severity getNivelFacesMessage() {
        FacesMessage.Severity sev;
        switch (this.getNivel()) {
            case 1:
                sev = FacesMessage.SEVERITY_INFO;
                break;
            case 2:
                sev = FacesMessage.SEVERITY_WARN;
                break;
            case 3:
                sev = FacesMessage.SEVERITY_ERROR;
                break;
            default:
                sev = FacesMessage.SEVERITY_FATAL;
                break;
        }
        return sev;
    }

    @Override
    public String getMessage() {
        switch (this.getNivel()) {
            case 1:
                return super.getMessage();
            case 2:
                return super.getMessage();
            case 3:
                return super.getMessage();
            default:
                return "Se ha registrado un error. Por favor intente de nuevo. " + super.getMessage(); //To change body of generated methods, choose Tools | Templates.

        }
    }

    /**
     * @return the nivel
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * @param nivel the nivel to set
     */
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

}
