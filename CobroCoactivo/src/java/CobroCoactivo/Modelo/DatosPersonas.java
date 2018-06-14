/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Modelo;

import CobroCoactivo.Persistencia.CivDatosPersonas;
import CobroCoactivo.Persistencia.CivEstadoDatosPersonas;
import CobroCoactivo.Persistencia.CivPersonas;
import CobroCoactivo.Persistencia.CivTipoDatosPersonas;
import java.util.Date;

/**
 *
 * @author emadrid
 */
public class DatosPersonas implements java.io.Serializable {

    private int Id;
    private String Descripcion;
    private TipoDatosPersonas tipoDatosPersonas;
    private EstadoDatosPersonas estadoDatosPersonas;
    private Date FechaProceso;

    public DatosPersonas() {
    }

    public DatosPersonas(CivDatosPersonas civDatosPersonas) {
        this.Id = civDatosPersonas.getDatperId().intValue();
        this.Descripcion = civDatosPersonas.getDatperDescripcion();
        this.FechaProceso = civDatosPersonas.getDatperFechaproceso();

    }

    public DatosPersonas(CivDatosPersonas civDatosPersonas, CivTipoDatosPersonas civTipoDatosPersonas) {
        this.Id = civDatosPersonas.getDatperId().intValue();
        this.Descripcion = civDatosPersonas.getDatperDescripcion();
        this.FechaProceso = civDatosPersonas.getDatperFechaproceso();
        this.tipoDatosPersonas = new TipoDatosPersonas(civTipoDatosPersonas);

    }

    public DatosPersonas(CivDatosPersonas civDatosPersonas, CivTipoDatosPersonas civTipoDatosPersonas,  CivEstadoDatosPersonas civEstadoDatosPersonas) {
        this.Id = civDatosPersonas.getDatperId().intValue();
        this.Descripcion = civDatosPersonas.getDatperDescripcion();
        this.FechaProceso = civDatosPersonas.getDatperFechaproceso();
        this.tipoDatosPersonas = new TipoDatosPersonas(civTipoDatosPersonas);
        this.estadoDatosPersonas = new EstadoDatosPersonas(civEstadoDatosPersonas);
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
     * @return the tipoDatosPersonas
     */
    public TipoDatosPersonas getTipoDatosPersonas() {
        return tipoDatosPersonas;
    }

    /**
     * @param tipoDatosPersonas the tipoDatosPersonas to set
     */
    public void setTipoDatosPersonas(TipoDatosPersonas tipoDatosPersonas) {
        this.tipoDatosPersonas = tipoDatosPersonas;
    }

    /**
     * @return the estadoDatosPersonas
     */
    public EstadoDatosPersonas getEstadoDatosPersonas() {
        return estadoDatosPersonas;
    }

    /**
     * @param estadoDatosPersonas the estadoDatosPersonas to set
     */
    public void setEstadoDatosPersonas(EstadoDatosPersonas estadoDatosPersonas) {
        this.estadoDatosPersonas = estadoDatosPersonas;
    }

    /**
     * @return the FechaProceso
     */
    public Date getFechaProceso() {
        return FechaProceso;
    }

    /**
     * @param FechaProceso the FechaProceso to set
     */
    public void setFechaProceso(Date FechaProceso) {
        this.FechaProceso = FechaProceso;
    }
}
