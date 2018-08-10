/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Modelo;

import CobroCoactivo.Persistencia.CivDetalleExpedientes;
import CobroCoactivo.Persistencia.CivPrestamoExpHistorial;
import CobroCoactivo.Persistencia.CivUsuarios;
import java.util.Date;

/**
 *
 * @author emadridp
 */
public class PrestamoExpHistorial implements java.io.Serializable {

    private int id;
    private Usuarios usuarios;
    private DetalleExpedientes detalleExpedientes;
    private Date fechaproceso;

    public PrestamoExpHistorial() {
    }

    public PrestamoExpHistorial(CivPrestamoExpHistorial civPrestamoExpHistorial) {
        this.id = civPrestamoExpHistorial.getPreexphisId().intValue();
        this.fechaproceso = civPrestamoExpHistorial.getPreexphisFechaproceso();
    }

    public PrestamoExpHistorial(CivPrestamoExpHistorial civPrestamoExpHistorial, CivUsuarios civUsuarios) {
        this.id = civPrestamoExpHistorial.getPreexphisId().intValue();
        this.fechaproceso = civPrestamoExpHistorial.getPreexphisFechaproceso();
        this.usuarios = new Usuarios(civUsuarios);
    }

    public PrestamoExpHistorial(CivPrestamoExpHistorial civPrestamoExpHistorial, CivUsuarios civUsuarios, CivDetalleExpedientes civDetalleExpedientes) {
        this.id = civPrestamoExpHistorial.getPreexphisId().intValue();
        this.fechaproceso = civPrestamoExpHistorial.getPreexphisFechaproceso();
        this.usuarios = new Usuarios(civUsuarios);
        this.detalleExpedientes = new DetalleExpedientes(civDetalleExpedientes);
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the usuarios
     */
    public Usuarios getUsuarios() {
        return usuarios;
    }

    /**
     * @param usuarios the usuarios to set
     */
    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    /**
     * @return the detalleExpedientes
     */
    public DetalleExpedientes getDetalleExpedientes() {
        return detalleExpedientes;
    }

    /**
     * @param detalleExpedientes the detalleExpedientes to set
     */
    public void setDetalleExpedientes(DetalleExpedientes detalleExpedientes) {
        this.detalleExpedientes = detalleExpedientes;
    }

    /**
     * @return the fechaproceso
     */
    public Date getFechaproceso() {
        return fechaproceso;
    }

    /**
     * @param fechaproceso the fechaproceso to set
     */
    public void setFechaproceso(Date fechaproceso) {
        this.fechaproceso = fechaproceso;
    }

}
