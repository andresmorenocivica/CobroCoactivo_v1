package CobroCoactivo.Persistencia;
// Generated 30/05/2018 02:43:14 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivEtapasTrabajos generated by hbm2java
 */
public class CivEtapasTrabajos  implements java.io.Serializable {


     private BigDecimal etatraId;
     private CivPlanTrabajos civPlanTrabajos;
     private CivEstadoEtapaTrabajos civEstadoEtapaTrabajos;
     private String etatraDescricion;
     private Date etatraFechaproceso;
     private Set civFasesTrabajoses = new HashSet(0);

    public CivEtapasTrabajos() {
    }

	
    public CivEtapasTrabajos(BigDecimal etatraId, CivPlanTrabajos civPlanTrabajos, CivEstadoEtapaTrabajos civEstadoEtapaTrabajos, String etatraDescricion) {
        this.etatraId = etatraId;
        this.civPlanTrabajos = civPlanTrabajos;
        this.civEstadoEtapaTrabajos = civEstadoEtapaTrabajos;
        this.etatraDescricion = etatraDescricion;
    }
    public CivEtapasTrabajos(BigDecimal etatraId, CivPlanTrabajos civPlanTrabajos, CivEstadoEtapaTrabajos civEstadoEtapaTrabajos, String etatraDescricion, Date etatraFechaproceso, Set civFasesTrabajoses) {
       this.etatraId = etatraId;
       this.civPlanTrabajos = civPlanTrabajos;
       this.civEstadoEtapaTrabajos = civEstadoEtapaTrabajos;
       this.etatraDescricion = etatraDescricion;
       this.etatraFechaproceso = etatraFechaproceso;
       this.civFasesTrabajoses = civFasesTrabajoses;
    }
   
    public BigDecimal getEtatraId() {
        return this.etatraId;
    }
    
    public void setEtatraId(BigDecimal etatraId) {
        this.etatraId = etatraId;
    }
    public CivPlanTrabajos getCivPlanTrabajos() {
        return this.civPlanTrabajos;
    }
    
    public void setCivPlanTrabajos(CivPlanTrabajos civPlanTrabajos) {
        this.civPlanTrabajos = civPlanTrabajos;
    }
    public CivEstadoEtapaTrabajos getCivEstadoEtapaTrabajos() {
        return this.civEstadoEtapaTrabajos;
    }
    
    public void setCivEstadoEtapaTrabajos(CivEstadoEtapaTrabajos civEstadoEtapaTrabajos) {
        this.civEstadoEtapaTrabajos = civEstadoEtapaTrabajos;
    }
    public String getEtatraDescricion() {
        return this.etatraDescricion;
    }
    
    public void setEtatraDescricion(String etatraDescricion) {
        this.etatraDescricion = etatraDescricion;
    }
    public Date getEtatraFechaproceso() {
        return this.etatraFechaproceso;
    }
    
    public void setEtatraFechaproceso(Date etatraFechaproceso) {
        this.etatraFechaproceso = etatraFechaproceso;
    }
    public Set getCivFasesTrabajoses() {
        return this.civFasesTrabajoses;
    }
    
    public void setCivFasesTrabajoses(Set civFasesTrabajoses) {
        this.civFasesTrabajoses = civFasesTrabajoses;
    }




}

