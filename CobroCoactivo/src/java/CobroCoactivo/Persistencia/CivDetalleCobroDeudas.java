package CobroCoactivo.Persistencia;
// Generated 30/08/2018 09:56:13 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;

/**
 * CivDetalleCobroDeudas generated by hbm2java
 */
public class CivDetalleCobroDeudas  implements java.io.Serializable {


     private BigDecimal detcobdeuId;
     private CivValorTipoDetalleCobro civValorTipoDetalleCobro;
     private CivEstadoDetalleCobroDeuda civEstadoDetalleCobroDeuda;
     private CivCobroDeudas civCobroDeudas;
     private Date detcobdeuFechaproceso;

    public CivDetalleCobroDeudas() {
    }

	
    public CivDetalleCobroDeudas(BigDecimal detcobdeuId, CivValorTipoDetalleCobro civValorTipoDetalleCobro, CivEstadoDetalleCobroDeuda civEstadoDetalleCobroDeuda, CivCobroDeudas civCobroDeudas) {
        this.detcobdeuId = detcobdeuId;
        this.civValorTipoDetalleCobro = civValorTipoDetalleCobro;
        this.civEstadoDetalleCobroDeuda = civEstadoDetalleCobroDeuda;
        this.civCobroDeudas = civCobroDeudas;
    }
    public CivDetalleCobroDeudas(BigDecimal detcobdeuId, CivValorTipoDetalleCobro civValorTipoDetalleCobro, CivEstadoDetalleCobroDeuda civEstadoDetalleCobroDeuda, CivCobroDeudas civCobroDeudas, Date detcobdeuFechaproceso) {
       this.detcobdeuId = detcobdeuId;
       this.civValorTipoDetalleCobro = civValorTipoDetalleCobro;
       this.civEstadoDetalleCobroDeuda = civEstadoDetalleCobroDeuda;
       this.civCobroDeudas = civCobroDeudas;
       this.detcobdeuFechaproceso = detcobdeuFechaproceso;
    }
   
    public BigDecimal getDetcobdeuId() {
        return this.detcobdeuId;
    }
    
    public void setDetcobdeuId(BigDecimal detcobdeuId) {
        this.detcobdeuId = detcobdeuId;
    }
    public CivValorTipoDetalleCobro getCivValorTipoDetalleCobro() {
        return this.civValorTipoDetalleCobro;
    }
    
    public void setCivValorTipoDetalleCobro(CivValorTipoDetalleCobro civValorTipoDetalleCobro) {
        this.civValorTipoDetalleCobro = civValorTipoDetalleCobro;
    }
    public CivEstadoDetalleCobroDeuda getCivEstadoDetalleCobroDeuda() {
        return this.civEstadoDetalleCobroDeuda;
    }
    
    public void setCivEstadoDetalleCobroDeuda(CivEstadoDetalleCobroDeuda civEstadoDetalleCobroDeuda) {
        this.civEstadoDetalleCobroDeuda = civEstadoDetalleCobroDeuda;
    }
    public CivCobroDeudas getCivCobroDeudas() {
        return this.civCobroDeudas;
    }
    
    public void setCivCobroDeudas(CivCobroDeudas civCobroDeudas) {
        this.civCobroDeudas = civCobroDeudas;
    }
    public Date getDetcobdeuFechaproceso() {
        return this.detcobdeuFechaproceso;
    }
    
    public void setDetcobdeuFechaproceso(Date detcobdeuFechaproceso) {
        this.detcobdeuFechaproceso = detcobdeuFechaproceso;
    }




}


