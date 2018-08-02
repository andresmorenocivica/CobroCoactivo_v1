/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Modelo;

import CobroCoactivo.Persistencia.CivEstadoSolicitudes;
import CobroCoactivo.Persistencia.CivSolicitudes;
import CobroCoactivo.Persistencia.CivUsuarios;
import java.util.Date;

/**
 *
 * @author emadridp
 */
public class Solicitudes {

    private int id;
    private Date fechaProceso;
    private String descripcion;
    private Usuarios usuarios;
    private EstadoSolicitudes estadoSolicitudes;

    public Solicitudes() {
    }

    public Solicitudes(CivSolicitudes civSolicitudes) {
        this.id = civSolicitudes.getSolId().intValue();
        this.descripcion = civSolicitudes.getSolDescripcion();
        this.fechaProceso = civSolicitudes.getSolFechaproceso();
    }

    public Solicitudes(CivSolicitudes civSolicitudes, CivEstadoSolicitudes civEstadoSolicitudes) {
        this.id = civSolicitudes.getSolId().intValue();
        this.descripcion = civSolicitudes.getSolDescripcion();
        this.fechaProceso = civSolicitudes.getSolFechaproceso();
        this.estadoSolicitudes = new EstadoSolicitudes(civEstadoSolicitudes);
    }

    public Solicitudes(CivSolicitudes civSolicitudes, CivEstadoSolicitudes civEstadoSolicitudes, CivUsuarios civUsuarios) {
        this.id = civSolicitudes.getSolId().intValue();
        this.descripcion = civSolicitudes.getSolDescripcion();
        this.fechaProceso = civSolicitudes.getSolFechaproceso();
        this.estadoSolicitudes = new EstadoSolicitudes(civEstadoSolicitudes);
        this.usuarios = new Usuarios(civUsuarios);
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
     * @return the estadoSolicitudes
     */
    public EstadoSolicitudes getEstadoSolicitudes() {
        return estadoSolicitudes;
    }

    /**
     * @param estadoSolicitudes the estadoSolicitudes to set
     */
    public void setEstadoSolicitudes(EstadoSolicitudes estadoSolicitudes) {
        this.estadoSolicitudes = estadoSolicitudes;
    }

    /**
     * @return the fechaProceso
     */
    public Date getFechaProceso() {
        return fechaProceso;
    }

    /**
     * @param fechaProceso the fechaProceso to set
     */
    public void setFechaProceso(Date fechaProceso) {
        this.fechaProceso = fechaProceso;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

}
