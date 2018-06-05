package CobroCoactivo.Persistencia;
// Generated 30/05/2018 02:43:14 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivTipoDeudas generated by hbm2java
 */
public class CivTipoDeudas  implements java.io.Serializable {


     private BigDecimal tipdeuId;
     private String tipdeuDescripcion;
     private String tipdeuNombrecorto;
     private BigDecimal tipdeuCodigo;
     private Date tipdeuFechainicial;
     private Date tipdeuFechafinal;
     private Set civDeudases = new HashSet(0);

    public CivTipoDeudas() {
    }

	
    public CivTipoDeudas(BigDecimal tipdeuId, String tipdeuDescripcion) {
        this.tipdeuId = tipdeuId;
        this.tipdeuDescripcion = tipdeuDescripcion;
    }
    public CivTipoDeudas(BigDecimal tipdeuId, String tipdeuDescripcion, String tipdeuNombrecorto, BigDecimal tipdeuCodigo, Date tipdeuFechainicial, Date tipdeuFechafinal, Set civDeudases) {
       this.tipdeuId = tipdeuId;
       this.tipdeuDescripcion = tipdeuDescripcion;
       this.tipdeuNombrecorto = tipdeuNombrecorto;
       this.tipdeuCodigo = tipdeuCodigo;
       this.tipdeuFechainicial = tipdeuFechainicial;
       this.tipdeuFechafinal = tipdeuFechafinal;
       this.civDeudases = civDeudases;
    }
   
    public BigDecimal getTipdeuId() {
        return this.tipdeuId;
    }
    
    public void setTipdeuId(BigDecimal tipdeuId) {
        this.tipdeuId = tipdeuId;
    }
    public String getTipdeuDescripcion() {
        return this.tipdeuDescripcion;
    }
    
    public void setTipdeuDescripcion(String tipdeuDescripcion) {
        this.tipdeuDescripcion = tipdeuDescripcion;
    }
    public String getTipdeuNombrecorto() {
        return this.tipdeuNombrecorto;
    }
    
    public void setTipdeuNombrecorto(String tipdeuNombrecorto) {
        this.tipdeuNombrecorto = tipdeuNombrecorto;
    }
    public BigDecimal getTipdeuCodigo() {
        return this.tipdeuCodigo;
    }
    
    public void setTipdeuCodigo(BigDecimal tipdeuCodigo) {
        this.tipdeuCodigo = tipdeuCodigo;
    }
    public Date getTipdeuFechainicial() {
        return this.tipdeuFechainicial;
    }
    
    public void setTipdeuFechainicial(Date tipdeuFechainicial) {
        this.tipdeuFechainicial = tipdeuFechainicial;
    }
    public Date getTipdeuFechafinal() {
        return this.tipdeuFechafinal;
    }
    
    public void setTipdeuFechafinal(Date tipdeuFechafinal) {
        this.tipdeuFechafinal = tipdeuFechafinal;
    }
    public Set getCivDeudases() {
        return this.civDeudases;
    }
    
    public void setCivDeudases(Set civDeudases) {
        this.civDeudases = civDeudases;
    }




}

