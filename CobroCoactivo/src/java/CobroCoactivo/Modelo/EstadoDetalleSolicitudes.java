/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Modelo;

import CobroCoactivo.Persistencia.CivEstadoDetalleSolicitudes;
import java.util.Date;

/**
 *
 * @author emadridp
 */
public class EstadoDetalleSolicitudes {

    private int id;
    private String nombre;
    private Date fechaProceso;
    private Date fechaInicial;
    private Date fechaFinal;

    public EstadoDetalleSolicitudes() {
    }

    public EstadoDetalleSolicitudes(CivEstadoDetalleSolicitudes civEstadoDetalleSolicitudes) {
        this.id = civEstadoDetalleSolicitudes.getEstdetsolId().intValue();
        this.nombre = civEstadoDetalleSolicitudes.getEstdetsolNombre();
        this.fechaInicial = civEstadoDetalleSolicitudes.getEstdetsolFechainicial();
        this.fechaFinal = civEstadoDetalleSolicitudes.getEstdetsolFechafinal();
        this.fechaProceso = civEstadoDetalleSolicitudes.getEstdetsolFechaproceso();
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
     * @return the fechaiInicial
     */
    public Date getFechaInicial() {
        return fechaInicial;
    }

    /**
     * @param fechaInicial the fechaiInicial to set
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
