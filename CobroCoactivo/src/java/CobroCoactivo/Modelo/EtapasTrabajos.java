package CobroCoactivo.Modelo;
// Generated 30/05/2018 02:16:05 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * EtapasTrabajos generated by hbm2java
 */
public class EtapasTrabajos  implements java.io.Serializable {


     private BigDecimal etatraId;
     private PlanTrabajos planTrabajos;
     private EstadoEtapaTrabajos estadoEtapaTrabajos;
     private String etatraDescricion;
     private Date etatraFechaproceso;
     private Set fasesTrabajoses = new HashSet(0);

    public EtapasTrabajos() {
    }

	
    public EtapasTrabajos(BigDecimal etatraId, PlanTrabajos planTrabajos, EstadoEtapaTrabajos estadoEtapaTrabajos, String etatraDescricion) {
        this.etatraId = etatraId;
        this.planTrabajos = planTrabajos;
        this.estadoEtapaTrabajos = estadoEtapaTrabajos;
        this.etatraDescricion = etatraDescricion;
    }
    public EtapasTrabajos(BigDecimal etatraId, PlanTrabajos planTrabajos, EstadoEtapaTrabajos estadoEtapaTrabajos, String etatraDescricion, Date etatraFechaproceso, Set fasesTrabajoses) {
       this.etatraId = etatraId;
       this.planTrabajos = planTrabajos;
       this.estadoEtapaTrabajos = estadoEtapaTrabajos;
       this.etatraDescricion = etatraDescricion;
       this.etatraFechaproceso = etatraFechaproceso;
       this.fasesTrabajoses = fasesTrabajoses;
    }
   
    public BigDecimal getEtatraId() {
        return this.etatraId;
    }
    
    public void setEtatraId(BigDecimal etatraId) {
        this.etatraId = etatraId;
    }
    public PlanTrabajos getPlanTrabajos() {
        return this.planTrabajos;
    }
    
    public void setPlanTrabajos(PlanTrabajos planTrabajos) {
        this.planTrabajos = planTrabajos;
    }
    public EstadoEtapaTrabajos getEstadoEtapaTrabajos() {
        return this.estadoEtapaTrabajos;
    }
    
    public void setEstadoEtapaTrabajos(EstadoEtapaTrabajos estadoEtapaTrabajos) {
        this.estadoEtapaTrabajos = estadoEtapaTrabajos;
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
    public Set getFasesTrabajoses() {
        return this.fasesTrabajoses;
    }
    
    public void setFasesTrabajoses(Set fasesTrabajoses) {
        this.fasesTrabajoses = fasesTrabajoses;
    }




}

