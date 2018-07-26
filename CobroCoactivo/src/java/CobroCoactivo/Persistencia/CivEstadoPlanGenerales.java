package CobroCoactivo.Persistencia;
// Generated 25/07/2018 05:27:39 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivEstadoPlanGenerales generated by hbm2java
 */
public class CivEstadoPlanGenerales  implements java.io.Serializable {


     private BigDecimal estplagenId;
     private String estplagenDescripcion;
     private Date estplagenFechainicial;
     private Date estplagenFechafinal;
     private Date estplagenFechaproceso;
     private Set civPlanGeneraleses = new HashSet(0);

    public CivEstadoPlanGenerales() {
    }

	
    public CivEstadoPlanGenerales(BigDecimal estplagenId, String estplagenDescripcion, Date estplagenFechainicial) {
        this.estplagenId = estplagenId;
        this.estplagenDescripcion = estplagenDescripcion;
        this.estplagenFechainicial = estplagenFechainicial;
    }
    public CivEstadoPlanGenerales(BigDecimal estplagenId, String estplagenDescripcion, Date estplagenFechainicial, Date estplagenFechafinal, Date estplagenFechaproceso, Set civPlanGeneraleses) {
       this.estplagenId = estplagenId;
       this.estplagenDescripcion = estplagenDescripcion;
       this.estplagenFechainicial = estplagenFechainicial;
       this.estplagenFechafinal = estplagenFechafinal;
       this.estplagenFechaproceso = estplagenFechaproceso;
       this.civPlanGeneraleses = civPlanGeneraleses;
    }
   
    public BigDecimal getEstplagenId() {
        return this.estplagenId;
    }
    
    public void setEstplagenId(BigDecimal estplagenId) {
        this.estplagenId = estplagenId;
    }
    public String getEstplagenDescripcion() {
        return this.estplagenDescripcion;
    }
    
    public void setEstplagenDescripcion(String estplagenDescripcion) {
        this.estplagenDescripcion = estplagenDescripcion;
    }
    public Date getEstplagenFechainicial() {
        return this.estplagenFechainicial;
    }
    
    public void setEstplagenFechainicial(Date estplagenFechainicial) {
        this.estplagenFechainicial = estplagenFechainicial;
    }
    public Date getEstplagenFechafinal() {
        return this.estplagenFechafinal;
    }
    
    public void setEstplagenFechafinal(Date estplagenFechafinal) {
        this.estplagenFechafinal = estplagenFechafinal;
    }
    public Date getEstplagenFechaproceso() {
        return this.estplagenFechaproceso;
    }
    
    public void setEstplagenFechaproceso(Date estplagenFechaproceso) {
        this.estplagenFechaproceso = estplagenFechaproceso;
    }
    public Set getCivPlanGeneraleses() {
        return this.civPlanGeneraleses;
    }
    
    public void setCivPlanGeneraleses(Set civPlanGeneraleses) {
        this.civPlanGeneraleses = civPlanGeneraleses;
    }




}


