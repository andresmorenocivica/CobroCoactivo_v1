package CobroCoactivo.Modelo;
// Generated 30/05/2018 02:16:05 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * EtapasGenerales generated by hbm2java
 */
public class EtapasGenerales  implements java.io.Serializable {


     private BigDecimal etagenId;
     private PlanGenerales planGenerales;
     private EstadoEtapasGenerales estadoEtapasGenerales;
     private String etagenDescripcion;
     private Date etagenFechaproceso;
     private String etagenObligatorio;
     private Set fasesGeneraleses = new HashSet(0);

    public EtapasGenerales() {
    }

	
    public EtapasGenerales(BigDecimal etagenId, PlanGenerales planGenerales, EstadoEtapasGenerales estadoEtapasGenerales, String etagenDescripcion, String etagenObligatorio) {
        this.etagenId = etagenId;
        this.planGenerales = planGenerales;
        this.estadoEtapasGenerales = estadoEtapasGenerales;
        this.etagenDescripcion = etagenDescripcion;
        this.etagenObligatorio = etagenObligatorio;
    }
    public EtapasGenerales(BigDecimal etagenId, PlanGenerales planGenerales, EstadoEtapasGenerales estadoEtapasGenerales, String etagenDescripcion, Date etagenFechaproceso, String etagenObligatorio, Set fasesGeneraleses) {
       this.etagenId = etagenId;
       this.planGenerales = planGenerales;
       this.estadoEtapasGenerales = estadoEtapasGenerales;
       this.etagenDescripcion = etagenDescripcion;
       this.etagenFechaproceso = etagenFechaproceso;
       this.etagenObligatorio = etagenObligatorio;
       this.fasesGeneraleses = fasesGeneraleses;
    }
   
    public BigDecimal getEtagenId() {
        return this.etagenId;
    }
    
    public void setEtagenId(BigDecimal etagenId) {
        this.etagenId = etagenId;
    }
    public PlanGenerales getPlanGenerales() {
        return this.planGenerales;
    }
    
    public void setPlanGenerales(PlanGenerales planGenerales) {
        this.planGenerales = planGenerales;
    }
    public EstadoEtapasGenerales getEstadoEtapasGenerales() {
        return this.estadoEtapasGenerales;
    }
    
    public void setEstadoEtapasGenerales(EstadoEtapasGenerales estadoEtapasGenerales) {
        this.estadoEtapasGenerales = estadoEtapasGenerales;
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
    public Set getFasesGeneraleses() {
        return this.fasesGeneraleses;
    }
    
    public void setFasesGeneraleses(Set fasesGeneraleses) {
        this.fasesGeneraleses = fasesGeneraleses;
    }




}


