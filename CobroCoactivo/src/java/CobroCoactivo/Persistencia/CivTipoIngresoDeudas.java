package CobroCoactivo.Persistencia;
// Generated 30/05/2018 02:43:14 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivTipoIngresoDeudas generated by hbm2java
 */
public class CivTipoIngresoDeudas  implements java.io.Serializable {


     private BigDecimal tipingId;
     private String tipingDescripcion;
     private String tipingNombrecorto;
     private Date tipingFechainicial;
     private Date tipingFechafinal;
     private BigDecimal tipingCodigo;
     private Set civIngresoDeudases = new HashSet(0);

    public CivTipoIngresoDeudas() {
    }

	
    public CivTipoIngresoDeudas(BigDecimal tipingId, String tipingDescripcion) {
        this.tipingId = tipingId;
        this.tipingDescripcion = tipingDescripcion;
    }
    public CivTipoIngresoDeudas(BigDecimal tipingId, String tipingDescripcion, String tipingNombrecorto, Date tipingFechainicial, Date tipingFechafinal, BigDecimal tipingCodigo, Set civIngresoDeudases) {
       this.tipingId = tipingId;
       this.tipingDescripcion = tipingDescripcion;
       this.tipingNombrecorto = tipingNombrecorto;
       this.tipingFechainicial = tipingFechainicial;
       this.tipingFechafinal = tipingFechafinal;
       this.tipingCodigo = tipingCodigo;
       this.civIngresoDeudases = civIngresoDeudases;
    }
   
    public BigDecimal getTipingId() {
        return this.tipingId;
    }
    
    public void setTipingId(BigDecimal tipingId) {
        this.tipingId = tipingId;
    }
    public String getTipingDescripcion() {
        return this.tipingDescripcion;
    }
    
    public void setTipingDescripcion(String tipingDescripcion) {
        this.tipingDescripcion = tipingDescripcion;
    }
    public String getTipingNombrecorto() {
        return this.tipingNombrecorto;
    }
    
    public void setTipingNombrecorto(String tipingNombrecorto) {
        this.tipingNombrecorto = tipingNombrecorto;
    }
    public Date getTipingFechainicial() {
        return this.tipingFechainicial;
    }
    
    public void setTipingFechainicial(Date tipingFechainicial) {
        this.tipingFechainicial = tipingFechainicial;
    }
    public Date getTipingFechafinal() {
        return this.tipingFechafinal;
    }
    
    public void setTipingFechafinal(Date tipingFechafinal) {
        this.tipingFechafinal = tipingFechafinal;
    }
    public BigDecimal getTipingCodigo() {
        return this.tipingCodigo;
    }
    
    public void setTipingCodigo(BigDecimal tipingCodigo) {
        this.tipingCodigo = tipingCodigo;
    }
    public Set getCivIngresoDeudases() {
        return this.civIngresoDeudases;
    }
    
    public void setCivIngresoDeudases(Set civIngresoDeudases) {
        this.civIngresoDeudases = civIngresoDeudases;
    }




}


