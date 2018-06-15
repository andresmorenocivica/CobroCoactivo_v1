package CobroCoactivo.Persistencia;
// Generated 15/06/2018 09:05:14 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivEntidades generated by hbm2java
 */
public class CivEntidades  implements java.io.Serializable {


     private BigDecimal entId;
     private CivPersonas civPersonas;
     private CivEstadoEntidades civEstadoEntidades;
     private Date entFechaproceso;
     private Set civAbogadoses = new HashSet(0);

    public CivEntidades() {
    }

	
    public CivEntidades(BigDecimal entId, CivPersonas civPersonas, CivEstadoEntidades civEstadoEntidades) {
        this.entId = entId;
        this.civPersonas = civPersonas;
        this.civEstadoEntidades = civEstadoEntidades;
    }
    public CivEntidades(BigDecimal entId, CivPersonas civPersonas, CivEstadoEntidades civEstadoEntidades, Date entFechaproceso, Set civAbogadoses) {
       this.entId = entId;
       this.civPersonas = civPersonas;
       this.civEstadoEntidades = civEstadoEntidades;
       this.entFechaproceso = entFechaproceso;
       this.civAbogadoses = civAbogadoses;
    }
   
    public BigDecimal getEntId() {
        return this.entId;
    }
    
    public void setEntId(BigDecimal entId) {
        this.entId = entId;
    }
    public CivPersonas getCivPersonas() {
        return this.civPersonas;
    }
    
    public void setCivPersonas(CivPersonas civPersonas) {
        this.civPersonas = civPersonas;
    }
    public CivEstadoEntidades getCivEstadoEntidades() {
        return this.civEstadoEntidades;
    }
    
    public void setCivEstadoEntidades(CivEstadoEntidades civEstadoEntidades) {
        this.civEstadoEntidades = civEstadoEntidades;
    }
    public Date getEntFechaproceso() {
        return this.entFechaproceso;
    }
    
    public void setEntFechaproceso(Date entFechaproceso) {
        this.entFechaproceso = entFechaproceso;
    }
    public Set getCivAbogadoses() {
        return this.civAbogadoses;
    }
    
    public void setCivAbogadoses(Set civAbogadoses) {
        this.civAbogadoses = civAbogadoses;
    }




}


