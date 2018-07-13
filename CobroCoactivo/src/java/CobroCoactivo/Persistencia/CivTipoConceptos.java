package CobroCoactivo.Persistencia;
// Generated 13/07/2018 10:45:24 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivTipoConceptos generated by hbm2java
 */
public class CivTipoConceptos  implements java.io.Serializable {


     private BigDecimal tipconId;
     private String tipconDescripcion;
     private String tipconNombrecorto;
     private BigDecimal tipconCodigo;
     private Date tipconFechainicial;
     private Date tipconFechafinal;
     private Set civConceptoses = new HashSet(0);

    public CivTipoConceptos() {
    }

	
    public CivTipoConceptos(BigDecimal tipconId, String tipconDescripcion) {
        this.tipconId = tipconId;
        this.tipconDescripcion = tipconDescripcion;
    }
    public CivTipoConceptos(BigDecimal tipconId, String tipconDescripcion, String tipconNombrecorto, BigDecimal tipconCodigo, Date tipconFechainicial, Date tipconFechafinal, Set civConceptoses) {
       this.tipconId = tipconId;
       this.tipconDescripcion = tipconDescripcion;
       this.tipconNombrecorto = tipconNombrecorto;
       this.tipconCodigo = tipconCodigo;
       this.tipconFechainicial = tipconFechainicial;
       this.tipconFechafinal = tipconFechafinal;
       this.civConceptoses = civConceptoses;
    }
   
    public BigDecimal getTipconId() {
        return this.tipconId;
    }
    
    public void setTipconId(BigDecimal tipconId) {
        this.tipconId = tipconId;
    }
    public String getTipconDescripcion() {
        return this.tipconDescripcion;
    }
    
    public void setTipconDescripcion(String tipconDescripcion) {
        this.tipconDescripcion = tipconDescripcion;
    }
    public String getTipconNombrecorto() {
        return this.tipconNombrecorto;
    }
    
    public void setTipconNombrecorto(String tipconNombrecorto) {
        this.tipconNombrecorto = tipconNombrecorto;
    }
    public BigDecimal getTipconCodigo() {
        return this.tipconCodigo;
    }
    
    public void setTipconCodigo(BigDecimal tipconCodigo) {
        this.tipconCodigo = tipconCodigo;
    }
    public Date getTipconFechainicial() {
        return this.tipconFechainicial;
    }
    
    public void setTipconFechainicial(Date tipconFechainicial) {
        this.tipconFechainicial = tipconFechainicial;
    }
    public Date getTipconFechafinal() {
        return this.tipconFechafinal;
    }
    
    public void setTipconFechafinal(Date tipconFechafinal) {
        this.tipconFechafinal = tipconFechafinal;
    }
    public Set getCivConceptoses() {
        return this.civConceptoses;
    }
    
    public void setCivConceptoses(Set civConceptoses) {
        this.civConceptoses = civConceptoses;
    }




}


