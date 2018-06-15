/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Modelo;

import CobroCoactivo.Persistencia.CivEstadoTipoDetalleCobro;
import CobroCoactivo.Persistencia.CivTipoDetalleCobro;
import java.util.Date;

/**
 *
 * @author emadrid
 */
public class TipoDetalleCobro {

    private int Id;
    private EstadoTipoDetalleCobro estadoTipoDetalleCobro = new EstadoTipoDetalleCobro();
    private String Descripcion;
    private Date Fechaproceso;

    public TipoDetalleCobro() {
    }

    public TipoDetalleCobro(CivTipoDetalleCobro civTipoDetalleCobro) {
        this.Id = civTipoDetalleCobro.getTipdetcobId().intValue();
        this.Descripcion = civTipoDetalleCobro.getTipdetcobDescripcion();
        this.Fechaproceso = civTipoDetalleCobro.getTipdetcobFechaproceso();
    }

    public TipoDetalleCobro(CivTipoDetalleCobro civTipoDetalleCobro, CivEstadoTipoDetalleCobro civEstadoTipoDetalleCobro) {
        this.Id = civTipoDetalleCobro.getTipdetcobId().intValue();
        this.Descripcion = civTipoDetalleCobro.getTipdetcobDescripcion();
        this.Fechaproceso = civTipoDetalleCobro.getTipdetcobFechaproceso();
        this.estadoTipoDetalleCobro =  new EstadoTipoDetalleCobro(civEstadoTipoDetalleCobro);

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
     * @return the estadoTipoDetalleCobro
     */
    public EstadoTipoDetalleCobro getEstadoTipoDetalleCobro() {
        return estadoTipoDetalleCobro;
    }

    /**
     * @param estadoTipoDetalleCobro the estadoTipoDetalleCobro to set
     */
    public void setEstadoTipoDetalleCobro(EstadoTipoDetalleCobro estadoTipoDetalleCobro) {
        this.estadoTipoDetalleCobro = estadoTipoDetalleCobro;
    }

}
