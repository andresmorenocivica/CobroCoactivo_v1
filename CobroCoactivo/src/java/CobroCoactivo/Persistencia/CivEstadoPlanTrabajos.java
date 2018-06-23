package CobroCoactivo.Persistencia;
// Generated 23/06/2018 10:46:28 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivEstadoPlanTrabajos generated by hbm2java
 */
public class CivEstadoPlanTrabajos  implements java.io.Serializable {


     private BigDecimal estplatraId;
     private String estplatraDescripcion;
     private Date estplatraFechainicial;
     private Date estplatraFechafinal;
     private Date estplatraFechaproceso;
     private Set civPlanTrabajoses = new HashSet(0);

    public CivEstadoPlanTrabajos() {
    }

	
    public CivEstadoPlanTrabajos(BigDecimal estplatraId, String estplatraDescripcion, Date estplatraFechainicial) {
        this.estplatraId = estplatraId;
        this.estplatraDescripcion = estplatraDescripcion;
        this.estplatraFechainicial = estplatraFechainicial;
    }
    public CivEstadoPlanTrabajos(BigDecimal estplatraId, String estplatraDescripcion, Date estplatraFechainicial, Date estplatraFechafinal, Date estplatraFechaproceso, Set civPlanTrabajoses) {
       this.estplatraId = estplatraId;
       this.estplatraDescripcion = estplatraDescripcion;
       this.estplatraFechainicial = estplatraFechainicial;
       this.estplatraFechafinal = estplatraFechafinal;
       this.estplatraFechaproceso = estplatraFechaproceso;
       this.civPlanTrabajoses = civPlanTrabajoses;
    }
   
    public BigDecimal getEstplatraId() {
        return this.estplatraId;
    }
    
    public void setEstplatraId(BigDecimal estplatraId) {
        this.estplatraId = estplatraId;
    }
    public String getEstplatraDescripcion() {
        return this.estplatraDescripcion;
    }
    
    public void setEstplatraDescripcion(String estplatraDescripcion) {
        this.estplatraDescripcion = estplatraDescripcion;
    }
    public Date getEstplatraFechainicial() {
        return this.estplatraFechainicial;
    }
    
    public void setEstplatraFechainicial(Date estplatraFechainicial) {
        this.estplatraFechainicial = estplatraFechainicial;
    }
    public Date getEstplatraFechafinal() {
        return this.estplatraFechafinal;
    }
    
    public void setEstplatraFechafinal(Date estplatraFechafinal) {
        this.estplatraFechafinal = estplatraFechafinal;
    }
    public Date getEstplatraFechaproceso() {
        return this.estplatraFechaproceso;
    }
    
    public void setEstplatraFechaproceso(Date estplatraFechaproceso) {
        this.estplatraFechaproceso = estplatraFechaproceso;
    }
    public Set getCivPlanTrabajoses() {
        return this.civPlanTrabajoses;
    }
    
    public void setCivPlanTrabajoses(Set civPlanTrabajoses) {
        this.civPlanTrabajoses = civPlanTrabajoses;
    }




}


