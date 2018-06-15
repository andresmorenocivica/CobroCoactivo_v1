package CobroCoactivo.Persistencia;
// Generated 15/06/2018 09:05:14 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivValorTipoDetalleCobro generated by hbm2java
 */
public class CivValorTipoDetalleCobro  implements java.io.Serializable {


     private BigDecimal valtipdetcobId;
     private CivEstadoValorTipoDetcobro civEstadoValorTipoDetcobro;
     private String valtipdetcobDescripcion;
     private Date valtipdetcobFechaproceso;
     private Set civDetalleCobroDeudases = new HashSet(0);

    public CivValorTipoDetalleCobro() {
    }

	
    public CivValorTipoDetalleCobro(BigDecimal valtipdetcobId, CivEstadoValorTipoDetcobro civEstadoValorTipoDetcobro, String valtipdetcobDescripcion) {
        this.valtipdetcobId = valtipdetcobId;
        this.civEstadoValorTipoDetcobro = civEstadoValorTipoDetcobro;
        this.valtipdetcobDescripcion = valtipdetcobDescripcion;
    }
    public CivValorTipoDetalleCobro(BigDecimal valtipdetcobId, CivEstadoValorTipoDetcobro civEstadoValorTipoDetcobro, String valtipdetcobDescripcion, Date valtipdetcobFechaproceso, Set civDetalleCobroDeudases) {
       this.valtipdetcobId = valtipdetcobId;
       this.civEstadoValorTipoDetcobro = civEstadoValorTipoDetcobro;
       this.valtipdetcobDescripcion = valtipdetcobDescripcion;
       this.valtipdetcobFechaproceso = valtipdetcobFechaproceso;
       this.civDetalleCobroDeudases = civDetalleCobroDeudases;
    }
   
    public BigDecimal getValtipdetcobId() {
        return this.valtipdetcobId;
    }
    
    public void setValtipdetcobId(BigDecimal valtipdetcobId) {
        this.valtipdetcobId = valtipdetcobId;
    }
    public CivEstadoValorTipoDetcobro getCivEstadoValorTipoDetcobro() {
        return this.civEstadoValorTipoDetcobro;
    }
    
    public void setCivEstadoValorTipoDetcobro(CivEstadoValorTipoDetcobro civEstadoValorTipoDetcobro) {
        this.civEstadoValorTipoDetcobro = civEstadoValorTipoDetcobro;
    }
    public String getValtipdetcobDescripcion() {
        return this.valtipdetcobDescripcion;
    }
    
    public void setValtipdetcobDescripcion(String valtipdetcobDescripcion) {
        this.valtipdetcobDescripcion = valtipdetcobDescripcion;
    }
    public Date getValtipdetcobFechaproceso() {
        return this.valtipdetcobFechaproceso;
    }
    
    public void setValtipdetcobFechaproceso(Date valtipdetcobFechaproceso) {
        this.valtipdetcobFechaproceso = valtipdetcobFechaproceso;
    }
    public Set getCivDetalleCobroDeudases() {
        return this.civDetalleCobroDeudases;
    }
    
    public void setCivDetalleCobroDeudases(Set civDetalleCobroDeudases) {
        this.civDetalleCobroDeudases = civDetalleCobroDeudases;
    }




}

