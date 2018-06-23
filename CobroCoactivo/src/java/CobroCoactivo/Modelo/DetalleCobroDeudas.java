/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Modelo;

import CobroCoactivo.Persistencia.CivCobroDeudas;
import CobroCoactivo.Persistencia.CivDetalleCobroDeudas;
import CobroCoactivo.Persistencia.CivEstadoDetalleCobroDeuda;
import CobroCoactivo.Persistencia.CivValorTipoDetalleCobro;
import java.util.Date;

/**
 *
 * @author emadrid
 */
public class DetalleCobroDeudas {

    private int Id;
    private ValorTipoDetalleCobro valorTipoDetalleCobro;
    private EstadoDetalleCobroDeuda estadoDetalleCobroDeuda;
    private CobroDeudas cobroDeudas;
    private Date Fechaproceso;

    public DetalleCobroDeudas() {
    }

    public DetalleCobroDeudas(CivDetalleCobroDeudas civDetalleCobroDeudas) {
        this.Id = civDetalleCobroDeudas.getDetcobdeuId().intValue();
        this.Fechaproceso = civDetalleCobroDeudas.getDetcobdeuFechaproceso();
    }

    public DetalleCobroDeudas(CivDetalleCobroDeudas civDetalleCobroDeudas, CivEstadoDetalleCobroDeuda civEstadoDetalleCobroDeuda) {
        this.Id = civDetalleCobroDeudas.getDetcobdeuId().intValue();
        this.Fechaproceso = civDetalleCobroDeudas.getDetcobdeuFechaproceso();
        this.estadoDetalleCobroDeuda = new EstadoDetalleCobroDeuda();
    }

    public DetalleCobroDeudas(CivDetalleCobroDeudas civDetalleCobroDeudas, CivEstadoDetalleCobroDeuda civEstadoDetalleCobroDeuda, CivValorTipoDetalleCobro civValorTipoDetalleCobro) {
        this.Id = civDetalleCobroDeudas.getDetcobdeuId().intValue();
        this.Fechaproceso = civDetalleCobroDeudas.getDetcobdeuFechaproceso();
        this.estadoDetalleCobroDeuda = new EstadoDetalleCobroDeuda();
        this.valorTipoDetalleCobro = new ValorTipoDetalleCobro();
    }

    public DetalleCobroDeudas(CivDetalleCobroDeudas civDetalleCobroDeudas, CivEstadoDetalleCobroDeuda civEstadoDetalleCobroDeuda, CivValorTipoDetalleCobro civValorTipoDetalleCobro, CivCobroDeudas civCobroDeudas) {
        this.Id = civDetalleCobroDeudas.getDetcobdeuId().intValue();
        this.Fechaproceso = civDetalleCobroDeudas.getDetcobdeuFechaproceso();
        this.estadoDetalleCobroDeuda = new EstadoDetalleCobroDeuda();
        this.valorTipoDetalleCobro = new ValorTipoDetalleCobro();
        this.cobroDeudas = new CobroDeudas();
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
     * @return the valorTipoDetalleCobro
     */
    public ValorTipoDetalleCobro getValorTipoDetalleCobro() {
        return valorTipoDetalleCobro;
    }

    /**
     * @param valorTipoDetalleCobro the valorTipoDetalleCobro to set
     */
    public void setValorTipoDetalleCobro(ValorTipoDetalleCobro valorTipoDetalleCobro) {
        this.valorTipoDetalleCobro = valorTipoDetalleCobro;
    }

    /**
     * @return the estadoDetalleCobroDeuda
     */
    public EstadoDetalleCobroDeuda getEstadoDetalleCobroDeuda() {
        return estadoDetalleCobroDeuda;
    }

    /**
     * @param estadoDetalleCobroDeuda the estadoDetalleCobroDeuda to set
     */
    public void setEstadoDetalleCobroDeuda(EstadoDetalleCobroDeuda estadoDetalleCobroDeuda) {
        this.estadoDetalleCobroDeuda = estadoDetalleCobroDeuda;
    }

    /**
     * @return the cobroDeudas
     */
    public CobroDeudas getCobroDeudas() {
        return cobroDeudas;
    }

    /**
     * @param cobroDeudas the cobroDeudas to set
     */
    public void setCobroDeudas(CobroDeudas cobroDeudas) {
        this.cobroDeudas = cobroDeudas;
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
