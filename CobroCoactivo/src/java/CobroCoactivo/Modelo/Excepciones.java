/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Modelo;

import CobroCoactivo.Persistencia.CivArchivoDetExpedientes;
import CobroCoactivo.Persistencia.CivEstadoExcepcion;
import CobroCoactivo.Persistencia.CivExcepciones;
import CobroCoactivo.Persistencia.CivMovimientos;
import CobroCoactivo.Persistencia.CivTipoExcepcion;
import CobroCoactivo.Persistencia.CivUsuarios;
import java.util.Date;

/**
 *
 * @author emadridp
 */
public class Excepciones {

    private int id;
    private Date fechaProceso;
    private Date fechaRadicado;
    private String numeroRadicado;
    private Usuarios usuarios;
    private TipoExcepcion tipoExcepcion;
    private Movimientos movimientos;
    private EstadoExcepcion estadoExcepcion;
    private ArchivoDetExpedientes archivoDetExpedientes;

    public Excepciones() {
    }

    public Excepciones(CivExcepciones civExcepciones) {
        this.id = civExcepciones.getExcId().intValue();
        this.numeroRadicado = civExcepciones.getExcNumeroRadicado();
        this.fechaProceso = civExcepciones.getExcFechaproceso();
        this.fechaRadicado = civExcepciones.getExcFechaRadicado();
    }

    public Excepciones(CivExcepciones civExcepciones, CivUsuarios civUsuarios) {
        this.id = civExcepciones.getExcId().intValue();
        this.numeroRadicado = civExcepciones.getExcNumeroRadicado();
        this.fechaProceso = civExcepciones.getExcFechaproceso();
        this.fechaRadicado = civExcepciones.getExcFechaRadicado();
        this.usuarios = new Usuarios(civUsuarios);
    }

    public Excepciones(CivExcepciones civExcepciones, CivUsuarios civUsuarios, CivTipoExcepcion civTipoExcepcion) {
        this.id = civExcepciones.getExcId().intValue();
        this.numeroRadicado = civExcepciones.getExcNumeroRadicado();
        this.fechaProceso = civExcepciones.getExcFechaproceso();
        this.fechaRadicado = civExcepciones.getExcFechaRadicado();
        this.usuarios = new Usuarios(civUsuarios);
        this.tipoExcepcion = new TipoExcepcion(civTipoExcepcion);
    }

    public Excepciones(CivExcepciones civExcepciones, CivUsuarios civUsuarios, CivTipoExcepcion civTipoExcepcion, CivMovimientos civMovimientos) {
        this.id = civExcepciones.getExcId().intValue();
        this.numeroRadicado = civExcepciones.getExcNumeroRadicado();
        this.fechaProceso = civExcepciones.getExcFechaproceso();
        this.fechaRadicado = civExcepciones.getExcFechaRadicado();
        this.usuarios = new Usuarios(civUsuarios);
        this.tipoExcepcion = new TipoExcepcion(civTipoExcepcion);
        this.movimientos = new Movimientos(civMovimientos);
    }

    public Excepciones(CivExcepciones civExcepciones, CivUsuarios civUsuarios, CivTipoExcepcion civTipoExcepcion, CivMovimientos civMovimientos, CivEstadoExcepcion civEstadoExcepcion) {
        this.id = civExcepciones.getExcId().intValue();
        this.numeroRadicado = civExcepciones.getExcNumeroRadicado();
        this.fechaProceso = civExcepciones.getExcFechaproceso();
        this.fechaRadicado = civExcepciones.getExcFechaRadicado();
        this.usuarios = new Usuarios(civUsuarios);
        this.tipoExcepcion = new TipoExcepcion(civTipoExcepcion);
        this.estadoExcepcion = new EstadoExcepcion(civEstadoExcepcion);
        if (civMovimientos != null) {
            this.movimientos = new Movimientos(civMovimientos);
        }
    }

    public Excepciones(CivExcepciones civExcepciones, CivUsuarios civUsuarios, CivTipoExcepcion civTipoExcepcion, CivMovimientos civMovimientos, CivEstadoExcepcion civEstadoExcepcion, CivArchivoDetExpedientes civArchivoDetExpedientes) {
        this.id = civExcepciones.getExcId().intValue();
        this.numeroRadicado = civExcepciones.getExcNumeroRadicado();
        this.fechaProceso = civExcepciones.getExcFechaproceso();
        this.fechaRadicado = civExcepciones.getExcFechaRadicado();
        this.usuarios = new Usuarios(civUsuarios);
        this.tipoExcepcion = new TipoExcepcion(civTipoExcepcion);
        this.estadoExcepcion = new EstadoExcepcion(civEstadoExcepcion);
        this.archivoDetExpedientes = new ArchivoDetExpedientes(civArchivoDetExpedientes);
        if (civMovimientos != null) {
            this.movimientos = new Movimientos(civMovimientos);
        }
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
     * @return the tipoExcepcion
     */
    public TipoExcepcion getTipoExcepcion() {
        return tipoExcepcion;
    }

    /**
     * @param tipoExcepcion the tipoExcepcion to set
     */
    public void setTipoExcepcion(TipoExcepcion tipoExcepcion) {
        this.tipoExcepcion = tipoExcepcion;
    }

    /**
     * @return the movimientos
     */
    public Movimientos getMovimientos() {
        return movimientos;
    }

    /**
     * @param movimientos the movimientos to set
     */
    public void setMovimientos(Movimientos movimientos) {
        this.movimientos = movimientos;
    }

    /**
     * @return the estadoExcepcion
     */
    public EstadoExcepcion getEstadoExcepcion() {
        return estadoExcepcion;
    }

    /**
     * @param estadoExcepcion the estadoExcepcion to set
     */
    public void setEstadoExcepcion(EstadoExcepcion estadoExcepcion) {
        this.estadoExcepcion = estadoExcepcion;
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
     * @return the fechaRadicado
     */
    public Date getFechaRadicado() {
        return fechaRadicado;
    }

    /**
     * @param fechaRadicado the fechaRadicado to set
     */
    public void setFechaRadicado(Date fechaRadicado) {
        this.fechaRadicado = fechaRadicado;
    }

    /**
     * @return the numeroRadicado
     */
    public String getNumeroRadicado() {
        return numeroRadicado;
    }

    /**
     * @param numeroRadicado the numeroRadicado to set
     */
    public void setNumeroRadicado(String numeroRadicado) {
        this.numeroRadicado = numeroRadicado;
    }

    /**
     * @return the archivoDetExpedientes
     */
    public ArchivoDetExpedientes getArchivoDetExpedientes() {
        return archivoDetExpedientes;
    }

    /**
     * @param archivoDetExpedientes the archivoDetExpedientes to set
     */
    public void setArchivoDetExpedientes(ArchivoDetExpedientes archivoDetExpedientes) {
        this.archivoDetExpedientes = archivoDetExpedientes;
    }

}
