/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Modelo;

import CobroCoactivo.Persistencia.CivCobroDeudas;
import CobroCoactivo.Persistencia.CivEstadoCobroDeuda;
import java.util.Date;

/**
 *
 * @author emadrid
 */
public class CobroDeudas {

    private int Id;
    private EstadoCobroDeudas estadoCobroDeudas;
    private String Descripcion;
    private Date Fechaproceso;
    private String colorDificultad;

    public CobroDeudas() {
    }

    public CobroDeudas(CivCobroDeudas civCobroDeudas) {
        this.Id = civCobroDeudas.getCobdeuId().intValue();
        this.Descripcion = civCobroDeudas.getCobdeuDescripcion();
        this.Fechaproceso = civCobroDeudas.getCobdeuFechaproceso();
    }

    public CobroDeudas(CivCobroDeudas civCobroDeudas, CivEstadoCobroDeuda civEstadoCobroDeuda) {
        this.Id = civCobroDeudas.getCobdeuId().intValue();
        this.Descripcion = civCobroDeudas.getCobdeuDescripcion();
        this.Fechaproceso = civCobroDeudas.getCobdeuFechaproceso();
        this.estadoCobroDeudas = new EstadoCobroDeudas();
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
     * @return the Descripcion
     */
    public String getDescripcion() {
        return Descripcion;
    }

    /**
     * @param Descripcion the Descripcion to set
     */
    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
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
     * @return the estadoCobroDeudas
     */
    public EstadoCobroDeudas getEstadoCobroDeudas() {
        return estadoCobroDeudas;
    }

    /**
     * @param estadoCobroDeudas the estadoCobroDeudas to set
     */
    public void setEstadoCobroDeudas(EstadoCobroDeudas estadoCobroDeudas) {
        this.estadoCobroDeudas = estadoCobroDeudas;
    }

    /**
     * @return the colorDificultad
     */
    public String getColorDificultad() {
        return colorDificultad;
    }

    /**
     * @param colorDificultad the colorDificultad to set
     */
    public void setColorDificultad(String colorDificultad) {
        this.colorDificultad = colorDificultad;
    }

}
