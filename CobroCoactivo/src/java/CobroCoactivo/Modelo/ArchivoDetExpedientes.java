/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Modelo;

import CobroCoactivo.Persistencia.CivArchivoDetExpedientes;
import CobroCoactivo.Persistencia.CivDetalleExpedientes;
import CobroCoactivo.Persistencia.CivEstadoArchDetExp;
import java.util.Date;

/**
 *
 * @author emadrid
 */
public class ArchivoDetExpedientes implements java.io.Serializable {

    private int Id;
    private EstadoArchDetExp estadoArchDetExp;
    private DetalleExpedientes detalleExpedientes;
    private String Nombre;
    private Date Fechaproceso;
    private String Ubicacion;

    public ArchivoDetExpedientes() {
    }

    public ArchivoDetExpedientes(CivArchivoDetExpedientes civArchivoDetExpedientes) {
        this.Id = civArchivoDetExpedientes.getArcdetexpId().intValue();
        this.Nombre = civArchivoDetExpedientes.getArcdetexpNombre();
        this.Fechaproceso = civArchivoDetExpedientes.getArcdetexpFechaproceso();
        this.Ubicacion = civArchivoDetExpedientes.getArcdetexpUbicacion();
    }

    public ArchivoDetExpedientes(CivArchivoDetExpedientes civArchivoDetExpedientes, CivEstadoArchDetExp civEstadoArchDetExp) {
        this.Id = civArchivoDetExpedientes.getArcdetexpId().intValue();
        this.Nombre = civArchivoDetExpedientes.getArcdetexpNombre();
        this.Fechaproceso = civArchivoDetExpedientes.getArcdetexpFechaproceso();
        this.Ubicacion = civArchivoDetExpedientes.getArcdetexpUbicacion();
        this.estadoArchDetExp = new EstadoArchDetExp(civEstadoArchDetExp);
    }

    public ArchivoDetExpedientes(CivArchivoDetExpedientes civArchivoDetExpedientes, CivEstadoArchDetExp civEstadoArchDetExp, CivDetalleExpedientes civDetalleExpedientes) {
        this.Id = civArchivoDetExpedientes.getArcdetexpId().intValue();
        this.Nombre = civArchivoDetExpedientes.getArcdetexpNombre();
        this.Fechaproceso = civArchivoDetExpedientes.getArcdetexpFechaproceso();
        this.Ubicacion = civArchivoDetExpedientes.getArcdetexpUbicacion();
        this.estadoArchDetExp = new EstadoArchDetExp(civEstadoArchDetExp);
        this.detalleExpedientes = new DetalleExpedientes(civDetalleExpedientes,civDetalleExpedientes.getCivExpedientes());
    }

    /**
     * @return the Id
     */
    public int getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(int Id) {
        this.Id = Id;
    }

    /**
     * @return the estadoArchDetExp
     */
    public EstadoArchDetExp getEstadoArchDetExp() {
        return estadoArchDetExp;
    }

    /**
     * @param estadoArchDetExp the estadoArchDetExp to set
     */
    public void setEstadoArchDetExp(EstadoArchDetExp estadoArchDetExp) {
        this.estadoArchDetExp = estadoArchDetExp;
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
     * @return the Nombre
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * @param Nombre the Nombre to set
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    /**
     * @return the Fechaproceso
     */
    public Date getFechaproceso() {
        return Fechaproceso;
    }

    /**
     * @param Fechaproceso the Fechaproceso to set
     */
    public void setFechaproceso(Date Fechaproceso) {
        this.Fechaproceso = Fechaproceso;
    }

    /**
     * @return the Ubicacion
     */
    public String getUbicacion() {
        return Ubicacion;
    }

    /**
     * @param Ubicacion the Ubicacion to set
     */
    public void setUbicacion(String Ubicacion) {
        this.Ubicacion = Ubicacion;
    }

}
