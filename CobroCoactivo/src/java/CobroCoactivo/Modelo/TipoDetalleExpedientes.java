package CobroCoactivo.Modelo;
// Generated 30/05/2018 02:16:05 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TipoDetalleExpedientes generated by hbm2java
 */
public class TipoDetalleExpedientes  implements java.io.Serializable {


     private BigDecimal tipdetexpId;
     private String tidetpexpDescripcion;
     private String tipdetexpNombrecorto;
     private BigDecimal tipdetexpCodigo;
     private Date tipdetexpFechainicial;
     private Date tipdetexpFechafinal;
     private Set detalleExpedienteses = new HashSet(0);

    public TipoDetalleExpedientes() {
    }

	
    public TipoDetalleExpedientes(BigDecimal tipdetexpId, String tidetpexpDescripcion) {
        this.tipdetexpId = tipdetexpId;
        this.tidetpexpDescripcion = tidetpexpDescripcion;
    }
    public TipoDetalleExpedientes(BigDecimal tipdetexpId, String tidetpexpDescripcion, String tipdetexpNombrecorto, BigDecimal tipdetexpCodigo, Date tipdetexpFechainicial, Date tipdetexpFechafinal, Set detalleExpedienteses) {
       this.tipdetexpId = tipdetexpId;
       this.tidetpexpDescripcion = tidetpexpDescripcion;
       this.tipdetexpNombrecorto = tipdetexpNombrecorto;
       this.tipdetexpCodigo = tipdetexpCodigo;
       this.tipdetexpFechainicial = tipdetexpFechainicial;
       this.tipdetexpFechafinal = tipdetexpFechafinal;
       this.detalleExpedienteses = detalleExpedienteses;
    }
   
    public BigDecimal getTipdetexpId() {
        return this.tipdetexpId;
    }
    
    public void setTipdetexpId(BigDecimal tipdetexpId) {
        this.tipdetexpId = tipdetexpId;
    }
    public String getTidetpexpDescripcion() {
        return this.tidetpexpDescripcion;
    }
    
    public void setTidetpexpDescripcion(String tidetpexpDescripcion) {
        this.tidetpexpDescripcion = tidetpexpDescripcion;
    }
    public String getTipdetexpNombrecorto() {
        return this.tipdetexpNombrecorto;
    }
    
    public void setTipdetexpNombrecorto(String tipdetexpNombrecorto) {
        this.tipdetexpNombrecorto = tipdetexpNombrecorto;
    }
    public BigDecimal getTipdetexpCodigo() {
        return this.tipdetexpCodigo;
    }
    
    public void setTipdetexpCodigo(BigDecimal tipdetexpCodigo) {
        this.tipdetexpCodigo = tipdetexpCodigo;
    }
    public Date getTipdetexpFechainicial() {
        return this.tipdetexpFechainicial;
    }
    
    public void setTipdetexpFechainicial(Date tipdetexpFechainicial) {
        this.tipdetexpFechainicial = tipdetexpFechainicial;
    }
    public Date getTipdetexpFechafinal() {
        return this.tipdetexpFechafinal;
    }
    
    public void setTipdetexpFechafinal(Date tipdetexpFechafinal) {
        this.tipdetexpFechafinal = tipdetexpFechafinal;
    }
    public Set getDetalleExpedienteses() {
        return this.detalleExpedienteses;
    }
    
    public void setDetalleExpedienteses(Set detalleExpedienteses) {
        this.detalleExpedienteses = detalleExpedienteses;
    }




}

