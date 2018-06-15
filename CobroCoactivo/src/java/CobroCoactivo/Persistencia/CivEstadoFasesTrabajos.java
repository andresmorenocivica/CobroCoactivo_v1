package CobroCoactivo.Persistencia;
// Generated 15/06/2018 09:05:14 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivEstadoFasesTrabajos generated by hbm2java
 */
public class CivEstadoFasesTrabajos  implements java.io.Serializable {


     private BigDecimal estfastraId;
     private String estfastraDescripcion;
     private Date estfastraFechainicial;
     private Date estfastraFechafinal;
     private Date estfastraFechaproceso;
     private CivFasesTrabajos civFasesTrabajosByEstfastraId;
     private Set civFasesTrabajosesForFastraEstfastraFk = new HashSet(0);

    public CivEstadoFasesTrabajos() {
    }

	
    public CivEstadoFasesTrabajos(BigDecimal estfastraId, String estfastraDescripcion, Date estfastraFechainicial) {
        this.estfastraId = estfastraId;
        this.estfastraDescripcion = estfastraDescripcion;
        this.estfastraFechainicial = estfastraFechainicial;
    }
    public CivEstadoFasesTrabajos(BigDecimal estfastraId, String estfastraDescripcion, Date estfastraFechainicial, Date estfastraFechafinal, Date estfastraFechaproceso, CivFasesTrabajos civFasesTrabajosByEstfastraId, Set civFasesTrabajosesForFastraEstfastraFk) {
       this.estfastraId = estfastraId;
       this.estfastraDescripcion = estfastraDescripcion;
       this.estfastraFechainicial = estfastraFechainicial;
       this.estfastraFechafinal = estfastraFechafinal;
       this.estfastraFechaproceso = estfastraFechaproceso;
       this.civFasesTrabajosByEstfastraId = civFasesTrabajosByEstfastraId;
       this.civFasesTrabajosesForFastraEstfastraFk = civFasesTrabajosesForFastraEstfastraFk;
    }
   
    public BigDecimal getEstfastraId() {
        return this.estfastraId;
    }
    
    public void setEstfastraId(BigDecimal estfastraId) {
        this.estfastraId = estfastraId;
    }
    public String getEstfastraDescripcion() {
        return this.estfastraDescripcion;
    }
    
    public void setEstfastraDescripcion(String estfastraDescripcion) {
        this.estfastraDescripcion = estfastraDescripcion;
    }
    public Date getEstfastraFechainicial() {
        return this.estfastraFechainicial;
    }
    
    public void setEstfastraFechainicial(Date estfastraFechainicial) {
        this.estfastraFechainicial = estfastraFechainicial;
    }
    public Date getEstfastraFechafinal() {
        return this.estfastraFechafinal;
    }
    
    public void setEstfastraFechafinal(Date estfastraFechafinal) {
        this.estfastraFechafinal = estfastraFechafinal;
    }
    public Date getEstfastraFechaproceso() {
        return this.estfastraFechaproceso;
    }
    
    public void setEstfastraFechaproceso(Date estfastraFechaproceso) {
        this.estfastraFechaproceso = estfastraFechaproceso;
    }
    public CivFasesTrabajos getCivFasesTrabajosByEstfastraId() {
        return this.civFasesTrabajosByEstfastraId;
    }
    
    public void setCivFasesTrabajosByEstfastraId(CivFasesTrabajos civFasesTrabajosByEstfastraId) {
        this.civFasesTrabajosByEstfastraId = civFasesTrabajosByEstfastraId;
    }
    public Set getCivFasesTrabajosesForFastraEstfastraFk() {
        return this.civFasesTrabajosesForFastraEstfastraFk;
    }
    
    public void setCivFasesTrabajosesForFastraEstfastraFk(Set civFasesTrabajosesForFastraEstfastraFk) {
        this.civFasesTrabajosesForFastraEstfastraFk = civFasesTrabajosesForFastraEstfastraFk;
    }




}


