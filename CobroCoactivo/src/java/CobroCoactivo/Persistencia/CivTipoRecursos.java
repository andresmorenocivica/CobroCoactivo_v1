package CobroCoactivo.Persistencia;
// Generated 7/06/2018 08:54:17 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivTipoRecursos generated by hbm2java
 */
public class CivTipoRecursos  implements java.io.Serializable {


     private BigDecimal tiprecId;
     private String tiprecDescripcion;
     private String tiprecNombrecorto;
     private Date tiprecFechainicial;
     private Date tiprecFechafinal;
     private BigDecimal tiprecCodigo;
     private Set civRecursoses = new HashSet(0);

    public CivTipoRecursos() {
    }

	
    public CivTipoRecursos(BigDecimal tiprecId, String tiprecDescripcion, Date tiprecFechainicial) {
        this.tiprecId = tiprecId;
        this.tiprecDescripcion = tiprecDescripcion;
        this.tiprecFechainicial = tiprecFechainicial;
    }
    public CivTipoRecursos(BigDecimal tiprecId, String tiprecDescripcion, String tiprecNombrecorto, Date tiprecFechainicial, Date tiprecFechafinal, BigDecimal tiprecCodigo, Set civRecursoses) {
       this.tiprecId = tiprecId;
       this.tiprecDescripcion = tiprecDescripcion;
       this.tiprecNombrecorto = tiprecNombrecorto;
       this.tiprecFechainicial = tiprecFechainicial;
       this.tiprecFechafinal = tiprecFechafinal;
       this.tiprecCodigo = tiprecCodigo;
       this.civRecursoses = civRecursoses;
    }
   
    public BigDecimal getTiprecId() {
        return this.tiprecId;
    }
    
    public void setTiprecId(BigDecimal tiprecId) {
        this.tiprecId = tiprecId;
    }
    public String getTiprecDescripcion() {
        return this.tiprecDescripcion;
    }
    
    public void setTiprecDescripcion(String tiprecDescripcion) {
        this.tiprecDescripcion = tiprecDescripcion;
    }
    public String getTiprecNombrecorto() {
        return this.tiprecNombrecorto;
    }
    
    public void setTiprecNombrecorto(String tiprecNombrecorto) {
        this.tiprecNombrecorto = tiprecNombrecorto;
    }
    public Date getTiprecFechainicial() {
        return this.tiprecFechainicial;
    }
    
    public void setTiprecFechainicial(Date tiprecFechainicial) {
        this.tiprecFechainicial = tiprecFechainicial;
    }
    public Date getTiprecFechafinal() {
        return this.tiprecFechafinal;
    }
    
    public void setTiprecFechafinal(Date tiprecFechafinal) {
        this.tiprecFechafinal = tiprecFechafinal;
    }
    public BigDecimal getTiprecCodigo() {
        return this.tiprecCodigo;
    }
    
    public void setTiprecCodigo(BigDecimal tiprecCodigo) {
        this.tiprecCodigo = tiprecCodigo;
    }
    public Set getCivRecursoses() {
        return this.civRecursoses;
    }
    
    public void setCivRecursoses(Set civRecursoses) {
        this.civRecursoses = civRecursoses;
    }




}


