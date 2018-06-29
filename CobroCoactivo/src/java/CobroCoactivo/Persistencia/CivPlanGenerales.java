package CobroCoactivo.Persistencia;
// Generated 28/06/2018 06:29:52 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivPlanGenerales generated by hbm2java
 */
public class CivPlanGenerales  implements java.io.Serializable {


     private BigDecimal plagenId;
     private CivEstadoPlanGenerales civEstadoPlanGenerales;
     private String plagenDescripcion;
     private Date plagenFechaproceso;
     private Set civEtapasGeneraleses = new HashSet(0);

    public CivPlanGenerales() {
    }

	
    public CivPlanGenerales(BigDecimal plagenId, CivEstadoPlanGenerales civEstadoPlanGenerales, String plagenDescripcion) {
        this.plagenId = plagenId;
        this.civEstadoPlanGenerales = civEstadoPlanGenerales;
        this.plagenDescripcion = plagenDescripcion;
    }
    public CivPlanGenerales(BigDecimal plagenId, CivEstadoPlanGenerales civEstadoPlanGenerales, String plagenDescripcion, Date plagenFechaproceso, Set civEtapasGeneraleses) {
       this.plagenId = plagenId;
       this.civEstadoPlanGenerales = civEstadoPlanGenerales;
       this.plagenDescripcion = plagenDescripcion;
       this.plagenFechaproceso = plagenFechaproceso;
       this.civEtapasGeneraleses = civEtapasGeneraleses;
    }
   
    public BigDecimal getPlagenId() {
        return this.plagenId;
    }
    
    public void setPlagenId(BigDecimal plagenId) {
        this.plagenId = plagenId;
    }
    public CivEstadoPlanGenerales getCivEstadoPlanGenerales() {
        return this.civEstadoPlanGenerales;
    }
    
    public void setCivEstadoPlanGenerales(CivEstadoPlanGenerales civEstadoPlanGenerales) {
        this.civEstadoPlanGenerales = civEstadoPlanGenerales;
    }
    public String getPlagenDescripcion() {
        return this.plagenDescripcion;
    }
    
    public void setPlagenDescripcion(String plagenDescripcion) {
        this.plagenDescripcion = plagenDescripcion;
    }
    public Date getPlagenFechaproceso() {
        return this.plagenFechaproceso;
    }
    
    public void setPlagenFechaproceso(Date plagenFechaproceso) {
        this.plagenFechaproceso = plagenFechaproceso;
    }
    public Set getCivEtapasGeneraleses() {
        return this.civEtapasGeneraleses;
    }
    
    public void setCivEtapasGeneraleses(Set civEtapasGeneraleses) {
        this.civEtapasGeneraleses = civEtapasGeneraleses;
    }




}


