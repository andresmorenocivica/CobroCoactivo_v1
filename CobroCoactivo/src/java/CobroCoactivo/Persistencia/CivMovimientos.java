package CobroCoactivo.Persistencia;
// Generated 2/08/2018 01:52:51 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;

/**
 * CivMovimientos generated by hbm2java
 */
public class CivMovimientos  implements java.io.Serializable {


     private BigDecimal movId;
     private CivUsuarios civUsuarios;
     private CivPersonas civPersonas;
     private CivFasesTrabajos civFasesTrabajos;
     private CivEstadoMovimientos civEstadoMovimientos;
     private CivDeudas civDeudas;
     private Date movFechaproceso;

    public CivMovimientos() {
    }

	
    public CivMovimientos(BigDecimal movId, CivUsuarios civUsuarios, CivPersonas civPersonas, CivFasesTrabajos civFasesTrabajos, CivEstadoMovimientos civEstadoMovimientos, CivDeudas civDeudas) {
        this.movId = movId;
        this.civUsuarios = civUsuarios;
        this.civPersonas = civPersonas;
        this.civFasesTrabajos = civFasesTrabajos;
        this.civEstadoMovimientos = civEstadoMovimientos;
        this.civDeudas = civDeudas;
    }
    public CivMovimientos(BigDecimal movId, CivUsuarios civUsuarios, CivPersonas civPersonas, CivFasesTrabajos civFasesTrabajos, CivEstadoMovimientos civEstadoMovimientos, CivDeudas civDeudas, Date movFechaproceso) {
       this.movId = movId;
       this.civUsuarios = civUsuarios;
       this.civPersonas = civPersonas;
       this.civFasesTrabajos = civFasesTrabajos;
       this.civEstadoMovimientos = civEstadoMovimientos;
       this.civDeudas = civDeudas;
       this.movFechaproceso = movFechaproceso;
    }
   
    public BigDecimal getMovId() {
        return this.movId;
    }
    
    public void setMovId(BigDecimal movId) {
        this.movId = movId;
    }
    public CivUsuarios getCivUsuarios() {
        return this.civUsuarios;
    }
    
    public void setCivUsuarios(CivUsuarios civUsuarios) {
        this.civUsuarios = civUsuarios;
    }
    public CivPersonas getCivPersonas() {
        return this.civPersonas;
    }
    
    public void setCivPersonas(CivPersonas civPersonas) {
        this.civPersonas = civPersonas;
    }
    public CivFasesTrabajos getCivFasesTrabajos() {
        return this.civFasesTrabajos;
    }
    
    public void setCivFasesTrabajos(CivFasesTrabajos civFasesTrabajos) {
        this.civFasesTrabajos = civFasesTrabajos;
    }
    public CivEstadoMovimientos getCivEstadoMovimientos() {
        return this.civEstadoMovimientos;
    }
    
    public void setCivEstadoMovimientos(CivEstadoMovimientos civEstadoMovimientos) {
        this.civEstadoMovimientos = civEstadoMovimientos;
    }
    public CivDeudas getCivDeudas() {
        return this.civDeudas;
    }
    
    public void setCivDeudas(CivDeudas civDeudas) {
        this.civDeudas = civDeudas;
    }
    public Date getMovFechaproceso() {
        return this.movFechaproceso;
    }
    
    public void setMovFechaproceso(Date movFechaproceso) {
        this.movFechaproceso = movFechaproceso;
    }




}


