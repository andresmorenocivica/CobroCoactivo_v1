package CobroCoactivo.Persistencia;
// Generated 9/08/2018 03:19:23 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;

/**
 * CivDetallePagos generated by hbm2java
 */
public class CivDetallePagos  implements java.io.Serializable {


     private BigDecimal detpagId;
     private CivPagos civPagos;
     private CivEstadoDetallePago civEstadoDetallePago;
     private CivDeudas civDeudas;
     private BigDecimal detpagValor;
     private Date detpagFechaproceso;

    public CivDetallePagos() {
    }

	
    public CivDetallePagos(BigDecimal detpagId, CivPagos civPagos, CivEstadoDetallePago civEstadoDetallePago, CivDeudas civDeudas) {
        this.detpagId = detpagId;
        this.civPagos = civPagos;
        this.civEstadoDetallePago = civEstadoDetallePago;
        this.civDeudas = civDeudas;
    }
    public CivDetallePagos(BigDecimal detpagId, CivPagos civPagos, CivEstadoDetallePago civEstadoDetallePago, CivDeudas civDeudas, BigDecimal detpagValor, Date detpagFechaproceso) {
       this.detpagId = detpagId;
       this.civPagos = civPagos;
       this.civEstadoDetallePago = civEstadoDetallePago;
       this.civDeudas = civDeudas;
       this.detpagValor = detpagValor;
       this.detpagFechaproceso = detpagFechaproceso;
    }
   
    public BigDecimal getDetpagId() {
        return this.detpagId;
    }
    
    public void setDetpagId(BigDecimal detpagId) {
        this.detpagId = detpagId;
    }
    public CivPagos getCivPagos() {
        return this.civPagos;
    }
    
    public void setCivPagos(CivPagos civPagos) {
        this.civPagos = civPagos;
    }
    public CivEstadoDetallePago getCivEstadoDetallePago() {
        return this.civEstadoDetallePago;
    }
    
    public void setCivEstadoDetallePago(CivEstadoDetallePago civEstadoDetallePago) {
        this.civEstadoDetallePago = civEstadoDetallePago;
    }
    public CivDeudas getCivDeudas() {
        return this.civDeudas;
    }
    
    public void setCivDeudas(CivDeudas civDeudas) {
        this.civDeudas = civDeudas;
    }
    public BigDecimal getDetpagValor() {
        return this.detpagValor;
    }
    
    public void setDetpagValor(BigDecimal detpagValor) {
        this.detpagValor = detpagValor;
    }
    public Date getDetpagFechaproceso() {
        return this.detpagFechaproceso;
    }
    
    public void setDetpagFechaproceso(Date detpagFechaproceso) {
        this.detpagFechaproceso = detpagFechaproceso;
    }




}


