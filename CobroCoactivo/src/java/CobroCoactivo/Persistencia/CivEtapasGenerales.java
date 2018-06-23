package CobroCoactivo.Persistencia;
// Generated 19/06/2018 11:34:10 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivEtapasGenerales generated by hbm2java
 */
public class CivEtapasGenerales  implements java.io.Serializable {


     private BigDecimal etagenId;
     private CivPlanGenerales civPlanGenerales;
     private CivEstadoEtapasGenerales civEstadoEtapasGenerales;
     private String etagenDescripcion;
     private Date etagenFechaproceso;
     private String etagenObligatorio;
     private Set civFasesGeneraleses = new HashSet(0);
     private Integer prioridad;

    public CivEtapasGenerales() {
    }

	
    public CivEtapasGenerales(BigDecimal etagenId, CivPlanGenerales civPlanGenerales, CivEstadoEtapasGenerales civEstadoEtapasGenerales, String etagenDescripcion, String etagenObligatorio) {
        this.etagenId = etagenId;
        this.civPlanGenerales = civPlanGenerales;
        this.civEstadoEtapasGenerales = civEstadoEtapasGenerales;
        this.etagenDescripcion = etagenDescripcion;
        this.etagenObligatorio = etagenObligatorio;
    }
    public CivEtapasGenerales(BigDecimal etagenId, CivPlanGenerales civPlanGenerales, CivEstadoEtapasGenerales civEstadoEtapasGenerales, String etagenDescripcion, Date etagenFechaproceso, String etagenObligatorio, Set civFasesGeneraleses) {
       this.etagenId = etagenId;
       this.civPlanGenerales = civPlanGenerales;
       this.civEstadoEtapasGenerales = civEstadoEtapasGenerales;
       this.etagenDescripcion = etagenDescripcion;
       this.etagenFechaproceso = etagenFechaproceso;
       this.etagenObligatorio = etagenObligatorio;
       this.civFasesGeneraleses = civFasesGeneraleses;
    }
   
    public BigDecimal getEtagenId() {
        return this.etagenId;
    }
    
    public void setEtagenId(BigDecimal etagenId) {
        this.etagenId = etagenId;
    }
    public CivPlanGenerales getCivPlanGenerales() {
        return this.civPlanGenerales;
    }
    
    public void setCivPlanGenerales(CivPlanGenerales civPlanGenerales) {
        this.civPlanGenerales = civPlanGenerales;
    }
    public CivEstadoEtapasGenerales getCivEstadoEtapasGenerales() {
        return this.civEstadoEtapasGenerales;
    }
    
    public void setCivEstadoEtapasGenerales(CivEstadoEtapasGenerales civEstadoEtapasGenerales) {
        this.civEstadoEtapasGenerales = civEstadoEtapasGenerales;
    }
    public String getEtagenDescripcion() {
        return this.etagenDescripcion;
    }
    
    public void setEtagenDescripcion(String etagenDescripcion) {
        this.etagenDescripcion = etagenDescripcion;
    }
    public Date getEtagenFechaproceso() {
        return this.etagenFechaproceso;
    }
    
    public void setEtagenFechaproceso(Date etagenFechaproceso) {
        this.etagenFechaproceso = etagenFechaproceso;
    }
    public String getEtagenObligatorio() {
        return this.etagenObligatorio;
    }
    
    public void setEtagenObligatorio(String etagenObligatorio) {
        this.etagenObligatorio = etagenObligatorio;
    }
    public Set getCivFasesGeneraleses() {
        return this.civFasesGeneraleses;
    }
    
    public void setCivFasesGeneraleses(Set civFasesGeneraleses) {
        this.civFasesGeneraleses = civFasesGeneraleses;
    }

    /**
     * @return the prioridad
     */
    public Integer getPrioridad() {
        return prioridad;
    }

    /**
     * @param prioridad the prioridad to set
     */
    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }




}


