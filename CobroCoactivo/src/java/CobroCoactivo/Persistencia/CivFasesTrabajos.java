package CobroCoactivo.Persistencia;
// Generated 9/08/2018 03:19:23 PM by Hibernate Tools 4.3.1


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
     private CivEstadoFasesTrabajos civEstadoFasesTrabajos;
     private String fastraDescripcion;
     private Date fastraFechaproceso;
     private BigDecimal fastraDianim;
     private BigDecimal fastraDiamax;
     private String fastraObligatorio;
     private Set civMovimientoses = new HashSet(0);

    public CivFasesTrabajos() {
    }

	
    public CivFasesTrabajos(BigDecimal fastraId, CivEtapasTrabajos civEtapasTrabajos, CivEstadoFasesTrabajos civEstadoFasesTrabajos, String fastraDescripcion, Date fastraFechaproceso, BigDecimal fastraDianim, BigDecimal fastraDiamax, String fastraObligatorio) {
        this.fastraId = fastraId;
        this.civEtapasTrabajos = civEtapasTrabajos;
        this.civEstadoFasesTrabajos = civEstadoFasesTrabajos;
        this.fastraDescripcion = fastraDescripcion;
        this.fastraFechaproceso = fastraFechaproceso;
        this.fastraDianim = fastraDianim;
        this.fastraDiamax = fastraDiamax;
        this.fastraObligatorio = fastraObligatorio;
    }
    public CivFasesTrabajos(BigDecimal fastraId, CivReporteTrabajos civReporteTrabajos, CivEtapasTrabajos civEtapasTrabajos, CivEstadoFasesTrabajos civEstadoFasesTrabajos, String fastraDescripcion, Date fastraFechaproceso, BigDecimal fastraDianim, BigDecimal fastraDiamax, String fastraObligatorio, Set civMovimientoses) {
       this.fastraId = fastraId;
       this.civReporteTrabajos = civReporteTrabajos;
       this.civEtapasTrabajos = civEtapasTrabajos;
       this.civEstadoFasesTrabajos = civEstadoFasesTrabajos;
       this.fastraDescripcion = fastraDescripcion;
       this.fastraFechaproceso = fastraFechaproceso;
       this.fastraDianim = fastraDianim;
       this.fastraDiamax = fastraDiamax;
       this.fastraObligatorio = fastraObligatorio;
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
    public CivEstadoFasesTrabajos getCivEstadoFasesTrabajos() {
        return this.civEstadoFasesTrabajos;
    }
    
    public void setCivEstadoFasesTrabajos(CivEstadoFasesTrabajos civEstadoFasesTrabajos) {
        this.civEstadoFasesTrabajos = civEstadoFasesTrabajos;
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
    public String getFastraObligatorio() {
        return this.fastraObligatorio;
    }
    
    public void setFastraObligatorio(String fastraObligatorio) {
        this.fastraObligatorio = fastraObligatorio;
    }
    public Set getCivMovimientoses() {
        return this.civMovimientoses;
    }
    
    public void setCivMovimientoses(Set civMovimientoses) {
        this.civMovimientoses = civMovimientoses;
    }




}


