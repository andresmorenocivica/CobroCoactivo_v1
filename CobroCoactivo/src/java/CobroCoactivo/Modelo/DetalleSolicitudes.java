/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Modelo;

import CobroCoactivo.Persistencia.CivDetalleSolicitudes;
import CobroCoactivo.Persistencia.CivEstadoDetalleSolicitudes;
import CobroCoactivo.Persistencia.CivSolicitudes;
import java.util.Date;

/**
 *
 * @author emadridp
 */
public class DetalleSolicitudes {

    private int id;
    private Solicitudes solicitudes;
    private EstadoDetalleSolicitudes estadoDetalleSolicitudes;
    private String descripcion;
    private Date fechaProceso;
    private boolean selecionado;

    public DetalleSolicitudes() {
    }

    public DetalleSolicitudes(CivDetalleSolicitudes civDetalleSolicitudes) {
        this.id = civDetalleSolicitudes.getDetsolId().intValue();
        this.descripcion = civDetalleSolicitudes.getDetsolDescripcion();
        this.fechaProceso = civDetalleSolicitudes.getDetsolFechaproceso();
    }

    public DetalleSolicitudes(CivDetalleSolicitudes civDetalleSolicitudes, CivEstadoDetalleSolicitudes civEstadoDetalleSolicitudes) {
        this.id = civDetalleSolicitudes.getDetsolId().intValue();
        this.descripcion = civDetalleSolicitudes.getDetsolDescripcion();
        this.fechaProceso = civDetalleSolicitudes.getDetsolFechaproceso();
        this.estadoDetalleSolicitudes = new EstadoDetalleSolicitudes(civEstadoDetalleSolicitudes);
    }

    public DetalleSolicitudes(CivDetalleSolicitudes civDetalleSolicitudes, CivEstadoDetalleSolicitudes civEstadoDetalleSolicitudes, CivSolicitudes civSolicitudes) {
        this.id = civDetalleSolicitudes.getDetsolId().intValue();
        this.descripcion = civDetalleSolicitudes.getDetsolDescripcion();
        this.fechaProceso = civDetalleSolicitudes.getDetsolFechaproceso();
        this.estadoDetalleSolicitudes = new EstadoDetalleSolicitudes(civEstadoDetalleSolicitudes);
        this.solicitudes = new Solicitudes(civSolicitudes);
    }

    /**
     * @return the solicitudes
     */
    public Solicitudes getSolicitudes() {
        return solicitudes;
    }

    /**
     * @param solicitudes the solicitudes to set
     */
    public void setSolicitudes(Solicitudes solicitudes) {
        this.solicitudes = solicitudes;
    }

    /**
     * @return the estadoDetalleSolicitudes
     */
    public EstadoDetalleSolicitudes getEstadoDetalleSolicitudes() {
        return estadoDetalleSolicitudes;
    }

    /**
     * @param estadoDetalleSolicitudes the estadoDetalleSolicitudes to set
     */
    public void setEstadoDetalleSolicitudes(EstadoDetalleSolicitudes estadoDetalleSolicitudes) {
        this.estadoDetalleSolicitudes = estadoDetalleSolicitudes;
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
     * @return the selecionado
     */
    public boolean isSelecionado() {
        return selecionado;
    }

    /**
     * @param selecionado the selecionado to set
     */
    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }

}
