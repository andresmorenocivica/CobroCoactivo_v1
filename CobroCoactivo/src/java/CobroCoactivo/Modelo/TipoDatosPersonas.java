/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Modelo;

import CobroCoactivo.Persistencia.CivEstadoTipoDatosPersonas;
import CobroCoactivo.Persistencia.CivTipoDatosPersonas;
import java.util.Date;

/**
 *
 * @author emadrid
 */
public class TipoDatosPersonas implements java.io.Serializable {

    private int Id;
    private String Descripcion;
    private EstadoTipoDatosPersonas estadoTipoDatosPersonas;
    private Date Fechainicial;
    private Date Fechafinal;
    private String Nombrecorto;
    private int Codigo;

    public TipoDatosPersonas() {
    }

    public TipoDatosPersonas(CivTipoDatosPersonas civTipoDatosPersonas) {
        this.Id = civTipoDatosPersonas.getTipdatperId().intValue();
        this.Descripcion = civTipoDatosPersonas.getTipdatperDescripcion();
        this.Fechainicial = civTipoDatosPersonas.getTipdatperFechainicial();
        this.Fechafinal = civTipoDatosPersonas.getTipdatperFechafinal();
        this.Nombrecorto = civTipoDatosPersonas.getTipdatperNombrecorto();
        this.Codigo = civTipoDatosPersonas.getTipdatperCodigo().intValue();
    }
 public TipoDatosPersonas (CivTipoDatosPersonas civTipoDatosPersonas, CivEstadoTipoDatosPersonas civEstadoTipoDatosPersonas){
 
        this.Id = civTipoDatosPersonas.getTipdatperId().intValue();
        this.Descripcion = civTipoDatosPersonas.getTipdatperDescripcion();
        this.Fechainicial = civTipoDatosPersonas.getTipdatperFechainicial();
        this.Fechafinal = civTipoDatosPersonas.getTipdatperFechafinal();
        this.Nombrecorto = civTipoDatosPersonas.getTipdatperNombrecorto();
        this.Codigo = civTipoDatosPersonas.getTipdatperCodigo().intValue();
        this.estadoTipoDatosPersonas = new EstadoTipoDatosPersonas();
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
     * @return the estadoTipoDatosPersonas
     */
    public EstadoTipoDatosPersonas getEstadoTipoDatosPersonas() {
        return estadoTipoDatosPersonas;
    }

    /**
     * @param estadoTipoDatosPersonas the estadoTipoDatosPersonas to set
     */
    public void setEstadoTipoDatosPersonas(EstadoTipoDatosPersonas estadoTipoDatosPersonas) {
        this.estadoTipoDatosPersonas = estadoTipoDatosPersonas;
    }

    /**
     * @return the Fechainicial
     */
    public Date getFechainicial() {
        return Fechainicial;
    }

    /**
     * @param Fechainicial the Fechainicial to set
     */
    public void setFechainicial(Date Fechainicial) {
        this.Fechainicial = Fechainicial;
    }

    /**
     * @return the Fechafinal
     */
    public Date getFechafinal() {
        return Fechafinal;
    }

    /**
     * @param Fechafinal the Fechafinal to set
     */
    public void setFechafinal(Date Fechafinal) {
        this.Fechafinal = Fechafinal;
    }

    /**
     * @return the Nombrecorto
     */
    public String getNombrecorto() {
        return Nombrecorto;
    }

    /**
     * @param Nombrecorto the Nombrecorto to set
     */
    public void setNombrecorto(String Nombrecorto) {
        this.Nombrecorto = Nombrecorto;
    }

    /**
     * @return the Codigo
     */
    public int getCodigo() {
        return Codigo;
    }

    /**
     * @param Codigo the Codigo to set
     */
    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

}
