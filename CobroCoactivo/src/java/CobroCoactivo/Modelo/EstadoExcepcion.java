/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Modelo;

import CobroCoactivo.Persistencia.CivEstadoExcepcion;
import java.util.Date;

/**
 *
 * @author emadridp
 */
public class EstadoExcepcion {

    private int id;
    private String nombre;
    private Date fechaProceso;
    private Date fechaInicial;
    private Date fechaFinal;

    public EstadoExcepcion() {
    }

    public EstadoExcepcion(CivEstadoExcepcion civEstadoExcepcion) {
        this.id = civEstadoExcepcion.getEstexcId().intValue();
        this.nombre = civEstadoExcepcion.getEstexcNombre();
        this.fechaProceso = civEstadoExcepcion.getEstexcFechaproceso();
        this.fechaInicial = civEstadoExcepcion.getEstexcFechainicial();
        this.fechaFinal = civEstadoExcepcion.getEstexcFechafinal();
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
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
     * @return the fechaInicial
     */
    public Date getFechaInicial() {
        return fechaInicial;
    }

    /**
     * @param fechaInicial the fechaInicial to set
     */
    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    /**
     * @return the fechaFinal
     */
    public Date getFechaFinal() {
        return fechaFinal;
    }

    /**
     * @param fechaFinal the fechaFinal to set
     */
    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

}
