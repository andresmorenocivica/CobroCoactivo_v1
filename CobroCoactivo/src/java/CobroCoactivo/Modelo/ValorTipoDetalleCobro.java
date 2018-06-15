/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Modelo;

import CobroCoactivo.Persistencia.CivEstadoValorTipoDetcobro;
import CobroCoactivo.Persistencia.CivValorTipoDetalleCobro;
import java.util.Date;

/**
 *
 * @author emadrid
 */
public class ValorTipoDetalleCobro {

    private int Id;
    private EstadoValorTipoDeuda estadoValorTipoDeuda = new EstadoValorTipoDeuda();
    private String Descripcion;
    private Date Fechaproceso;

    public ValorTipoDetalleCobro() {
    }

    public ValorTipoDetalleCobro(CivValorTipoDetalleCobro civValorTipoDetalleCobro) {
        this.Id = civValorTipoDetalleCobro.getValtipdetcobId().intValue();
        this.Descripcion = civValorTipoDetalleCobro.getValtipdetcobDescripcion();
        this.Fechaproceso = civValorTipoDetalleCobro.getValtipdetcobFechaproceso();
    }

    public ValorTipoDetalleCobro(CivValorTipoDetalleCobro civValorTipoDetalleCobro, CivEstadoValorTipoDetcobro civEstadoValorTipoDetcobro) {
        this.Id = civValorTipoDetalleCobro.getValtipdetcobId().intValue();
        this.Descripcion = civValorTipoDetalleCobro.getValtipdetcobDescripcion();
        this.Fechaproceso = civValorTipoDetalleCobro.getValtipdetcobFechaproceso();
        this.estadoValorTipoDeuda = new EstadoValorTipoDeuda(civEstadoValorTipoDetcobro);
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
     * @return the estadoValorTipoDeuda
     */
    public EstadoValorTipoDeuda getEstadoValorTipoDeuda() {
        return estadoValorTipoDeuda;
    }

    /**
     * @param estadoValorTipoDeuda the estadoValorTipoDeuda to set
     */
    public void setEstadoValorTipoDeuda(EstadoValorTipoDeuda estadoValorTipoDeuda) {
        this.estadoValorTipoDeuda = estadoValorTipoDeuda;
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
}
