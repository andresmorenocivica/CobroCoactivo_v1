package CobroCoactivo.Persistencia;
// Generated 7/06/2018 08:54:17 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivTipoExpedientes generated by hbm2java
 */
public class CivTipoExpedientes  implements java.io.Serializable {


     private BigDecimal tipexpId;
     private String tipexpDescripcion;
     private String tipexpNombrecorto;
     private BigDecimal tipexpCodigo;
     private Date tipexpFechainicial;
     private Date tipexpFechafinal;
     private Set civExpedienteses = new HashSet(0);

    public CivTipoExpedientes() {
    }

	
    public CivTipoExpedientes(BigDecimal tipexpId, String tipexpDescripcion) {
        this.tipexpId = tipexpId;
        this.tipexpDescripcion = tipexpDescripcion;
    }
    public CivTipoExpedientes(BigDecimal tipexpId, String tipexpDescripcion, String tipexpNombrecorto, BigDecimal tipexpCodigo, Date tipexpFechainicial, Date tipexpFechafinal, Set civExpedienteses) {
       this.tipexpId = tipexpId;
       this.tipexpDescripcion = tipexpDescripcion;
       this.tipexpNombrecorto = tipexpNombrecorto;
       this.tipexpCodigo = tipexpCodigo;
       this.tipexpFechainicial = tipexpFechainicial;
       this.tipexpFechafinal = tipexpFechafinal;
       this.civExpedienteses = civExpedienteses;
    }
   
    public BigDecimal getTipexpId() {
        return this.tipexpId;
    }
    
    public void setTipexpId(BigDecimal tipexpId) {
        this.tipexpId = tipexpId;
    }
    public String getTipexpDescripcion() {
        return this.tipexpDescripcion;
    }
    
    public void setTipexpDescripcion(String tipexpDescripcion) {
        this.tipexpDescripcion = tipexpDescripcion;
    }
    public String getTipexpNombrecorto() {
        return this.tipexpNombrecorto;
    }
    
    public void setTipexpNombrecorto(String tipexpNombrecorto) {
        this.tipexpNombrecorto = tipexpNombrecorto;
    }
    public BigDecimal getTipexpCodigo() {
        return this.tipexpCodigo;
    }
    
    public void setTipexpCodigo(BigDecimal tipexpCodigo) {
        this.tipexpCodigo = tipexpCodigo;
    }
    public Date getTipexpFechainicial() {
        return this.tipexpFechainicial;
    }
    
    public void setTipexpFechainicial(Date tipexpFechainicial) {
        this.tipexpFechainicial = tipexpFechainicial;
    }
    public Date getTipexpFechafinal() {
        return this.tipexpFechafinal;
    }
    
    public void setTipexpFechafinal(Date tipexpFechafinal) {
        this.tipexpFechafinal = tipexpFechafinal;
    }
    public Set getCivExpedienteses() {
        return this.civExpedienteses;
    }
    
    public void setCivExpedienteses(Set civExpedienteses) {
        this.civExpedienteses = civExpedienteses;
    }




}


