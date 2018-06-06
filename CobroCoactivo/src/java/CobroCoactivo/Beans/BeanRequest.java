/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Beans;

import CobroCoactivo.Modelo.Personas;
import CobroCoactivo.Modelo.Usuarios;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author amoreno
 */
@ManagedBean(name = "requestBean",eager = true)
@SessionScoped
public class BeanRequest  implements Serializable{
    private static final long serialVersionUID = -1612020613604473554L;
    // ESTA VARIABLE SE VA A UTILIZAR PARA CARGAR LA RUTA DEL FORMULARIO PARA PODER NAVEGAR
    private String ruta;
    
    
    
    /// OBJETO PARA INTERACTUAR CON EL DETALLE USUARIO
    private Usuarios usuario;
    
    //se declara un objeto para ver eñ detalle persona
    private Personas personas;
    

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @return the ruta
     */
    public String getRuta() {
        return ruta;
    }

    /**
     * @param ruta the ruta to set
     */
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    /**
     * @return the usuario
     */
    public Usuarios getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Personas getPersonas() {
        return personas;
    }

    public void setPersonas(Personas personas) {
        this.personas = personas;
    }

  
    
}
