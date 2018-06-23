package CobroCoactivo.Persistencia;
// Generated 23/06/2018 10:46:28 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivFasesTrabajos generated by hbm2java
 */
public class CivFasesTrabajos  implements java.io.Serializable {


     private BigDecimal fastraId;
     private CivReporteTrabajos civReporteTrabajos;
     private CivEtapasTrabajos civEtapasTrabajos;
     private CivEstadoFasesTrabajos civEstadoFasesTrabajosByEstfastraId;
     private CivEstadoFasesTrabajos civEstadoFasesTrabajosByFastraEstfastraFk;
     private String fastraDescripcion;
     private Date fastraFechaproceso;
     private BigDecimal fastraDianim;
     private BigDecimal fastraDiamax;
     private Set civMovimientoses = new HashSet(0);

    public CivFasesTrabajos() {
    }

	
    public CivFasesTrabajos(CivEtapasTrabajos civEtapasTrabajos, CivEstadoFasesTrabajos civEstadoFasesTrabajosByEstfastraId, CivEstadoFasesTrabajos civEstadoFasesTrabajosByFastraEstfastraFk, String fastraDescripcion) {
        this.civEtapasTrabajos = civEtapasTrabajos;
        this.civEstadoFasesTrabajosByEstfastraId = civEstadoFasesTrabajosByEstfastraId;
        this.civEstadoFasesTrabajosByFastraEstfastraFk = civEstadoFasesTrabajosByFastraEstfastraFk;
        this.fastraDescripcion = fastraDescripcion;
    }
    public CivFasesTrabajos(CivReporteTrabajos civReporteTrabajos, CivEtapasTrabajos civEtapasTrabajos, CivEstadoFasesTrabajos civEstadoFasesTrabajosByEstfastraId, CivEstadoFasesTrabajos civEstadoFasesTrabajosByFastraEstfastraFk, String fastraDescripcion, Date fastraFechaproceso, BigDecimal fastraDianim, BigDecimal fastraDiamax, Set civMovimientoses) {
       this.civReporteTrabajos = civReporteTrabajos;
       this.civEtapasTrabajos = civEtapasTrabajos;
       this.civEstadoFasesTrabajosByEstfastraId = civEstadoFasesTrabajosByEstfastraId;
       this.civEstadoFasesTrabajosByFastraEstfastraFk = civEstadoFasesTrabajosByFastraEstfastraFk;
       this.fastraDescripcion = fastraDescripcion;
       this.fastraFechaproceso = fastraFechaproceso;
       this.fastraDianim = fastraDianim;
       this.fastraDiamax = fastraDiamax;
       this.civMovimientoses = civMovimientoses;
    }
   
    public BigDecimal getFastraId() {
        return this.fastraId;
    }
    
    public void setFastraId(BigDecimal fastraId) {
        this.fastraId = fastraId;
    }
    public CivReporteTrabajos getCivReporteTrabajos() {
        return this.civReporteTrabajos;
    }
    
    public void setCivReporteTrabajos(CivReporteTrabajos civReporteTrabajos) {
        this.civReporteTrabajos = civReporteTrabajos;
    }
    public CivEtapasTrabajos getCivEtapasTrabajos() {
        return this.civEtapasTrabajos;
    }
    
    public void setCivEtapasTrabajos(CivEtapasTrabajos civEtapasTrabajos) {
        this.civEtapasTrabajos = civEtapasTrabajos;
    }
    public CivEstadoFasesTrabajos getCivEstadoFasesTrabajosByEstfastraId() {
        return this.civEstadoFasesTrabajosByEstfastraId;
    }
    
    public void setCivEstadoFasesTrabajosByEstfastraId(CivEstadoFasesTrabajos civEstadoFasesTrabajosByEstfastraId) {
        this.civEstadoFasesTrabajosByEstfastraId = civEstadoFasesTrabajosByEstfastraId;
    }
    public CivEstadoFasesTrabajos getCivEstadoFasesTrabajosByFastraEstfastraFk() {
        return this.civEstadoFasesTrabajosByFastraEstfastraFk;
    }
    
    public void setCivEstadoFasesTrabajosByFastraEstfastraFk(CivEstadoFasesTrabajos civEstadoFasesTrabajosByFastraEstfastraFk) {
        this.civEstadoFasesTrabajosByFastraEstfastraFk = civEstadoFasesTrabajosByFastraEstfastraFk;
    }
    public String getFastraDescripcion() {
        return this.fastraDescripcion;
    }
    
    public void setFastraDescripcion(String fastraDescripcion) {
        this.fastraDescripcion = fastraDescripcion;
    }
    public Date getFastraFechaproceso() {
        return this.fastraFechaproceso;
    }
    
    public void setFastraFechaproceso(Date fastraFechaproceso) {
        this.fastraFechaproceso = fastraFechaproceso;
    }
    public BigDecimal getFastraDianim() {
        return this.fastraDianim;
    }
    
    public void setFastraDianim(BigDecimal fastraDianim) {
        this.fastraDianim = fastraDianim;
    }
    public BigDecimal getFastraDiamax() {
        return this.fastraDiamax;
    }
    
    public void setFastraDiamax(BigDecimal fastraDiamax) {
        this.fastraDiamax = fastraDiamax;
    }
    public Set getCivMovimientoses() {
        return this.civMovimientoses;
    }
    
    public void setCivMovimientoses(Set civMovimientoses) {
        this.civMovimientoses = civMovimientoses;
    }




}


