/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Modelo;

import CobroCoactivo.Persistencia.CivArchivosPlanos;
import java.util.Date;

/**
 *
 * @author AMORENO
 */
public class ArchivosPlanos {

      private long id;
    private String nombre;
    private Date fecha;
    private int estado;
    private Usuarios usuario;

    public ArchivosPlanos(CivArchivosPlanos civArchivosPlanos) {
        this.id = civArchivosPlanos.getArcId().intValue();
        this.nombre = civArchivosPlanos.getArcNombre();
        this.fecha = civArchivosPlanos.getArcFecha();
        this.estado = civArchivosPlanos.getArcEstadoFk().intValue();
        this.usuario = new Usuarios(civArchivosPlanos.getCivUsuarios());
    }
    
    
    
    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
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
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(int estado) {
        this.estado = estado;
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
 
  
}
