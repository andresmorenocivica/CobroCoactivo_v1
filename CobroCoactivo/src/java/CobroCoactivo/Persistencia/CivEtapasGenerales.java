package CobroCoactivo.Persistencia;
// Generated 27/07/2018 01:43:20 PM by Hibernate Tools 4.3.1


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
     private BigDecimal etagenPrioridad;
     private Set civFasesGeneraleses = new HashSet(0);

    public CivEtapasGenerales() {
    }

	
    public CivEtapasGenerales(BigDecimal etagenId, CivPlanGenerales civPlanGenerales, CivEstadoEtapasGenerales civEstadoEtapasGenerales, String etagenDescripcion, String etagenObligatorio, BigDecimal etagenPrioridad) {
        this.etagenId = etagenId;
        this.civPlanGenerales = civPlanGenerales;
        this.civEstadoEtapasGenerales = civEstadoEtapasGenerales;
        this.etagenDescripcion = etagenDescripcion;
        this.etagenObligatorio = etagenObligatorio;
        this.etagenPrioridad = etagenPrioridad;
    }
    public CivEtapasGenerales(BigDecimal etagenId, CivPlanGenerales civPlanGenerales, CivEstadoEtapasGenerales civEstadoEtapasGenerales, String etagenDescripcion, Date etagenFechaproceso, String etagenObligatorio, BigDecimal etagenPrioridad, Set civFasesGeneraleses) {
       this.etagenId = etagenId;
       this.civPlanGenerales = civPlanGenerales;
       this.civEstadoEtapasGenerales = civEstadoEtapasGenerales;
       this.etagenDescripcion = etagenDescripcion;
       this.etagenFechaproceso = etagenFechaproceso;
       this.etagenObligatorio = etagenObligatorio;
       this.etagenPrioridad = etagenPrioridad;
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
    public BigDecimal getEtagenPrioridad() {
        return this.etagenPrioridad;
    }
    
    public void setEtagenPrioridad(BigDecimal etagenPrioridad) {
        this.etagenPrioridad = etagenPrioridad;
    }
    public Set getCivFasesGeneraleses() {
        return this.civFasesGeneraleses;
    }
    
    public void setCivFasesGeneraleses(Set civFasesGeneraleses) {
        this.civFasesGeneraleses = civFasesGeneraleses;
    }




}


