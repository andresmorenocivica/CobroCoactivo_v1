package CobroCoactivo.Persistencia;
// Generated 23/06/2018 10:46:28 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;

/**
 * CivFasesGenerales generated by hbm2java
 */
public class CivFasesGenerales  implements java.io.Serializable {


     private BigDecimal fasgenId;
     private CivEtapasGenerales civEtapasGenerales;
     private CivEstadoFasesGenerales civEstadoFasesGenerales;
     private CivDocumenGenerales civDocumenGenerales;
     private String fasgenDescripcion;
     private Date fasgenFechaproceso;
     private BigDecimal fasgenDianim;
     private BigDecimal fasgenDiamax;
     private String fasgenObligatorio;

    public CivFasesGenerales() {
    }

	
    public CivFasesGenerales(BigDecimal fasgenId, CivEtapasGenerales civEtapasGenerales, CivEstadoFasesGenerales civEstadoFasesGenerales, CivDocumenGenerales civDocumenGenerales, String fasgenDescripcion, String fasgenObligatorio) {
        this.fasgenId = fasgenId;
        this.civEtapasGenerales = civEtapasGenerales;
        this.civEstadoFasesGenerales = civEstadoFasesGenerales;
        this.civDocumenGenerales = civDocumenGenerales;
        this.fasgenDescripcion = fasgenDescripcion;
        this.fasgenObligatorio = fasgenObligatorio;
    }
    public CivFasesGenerales(BigDecimal fasgenId, CivEtapasGenerales civEtapasGenerales, CivEstadoFasesGenerales civEstadoFasesGenerales, CivDocumenGenerales civDocumenGenerales, String fasgenDescripcion, Date fasgenFechaproceso, BigDecimal fasgenDianim, BigDecimal fasgenDiamax, String fasgenObligatorio) {
       this.fasgenId = fasgenId;
       this.civEtapasGenerales = civEtapasGenerales;
       this.civEstadoFasesGenerales = civEstadoFasesGenerales;
       this.civDocumenGenerales = civDocumenGenerales;
       this.fasgenDescripcion = fasgenDescripcion;
       this.fasgenFechaproceso = fasgenFechaproceso;
       this.fasgenDianim = fasgenDianim;
       this.fasgenDiamax = fasgenDiamax;
       this.fasgenObligatorio = fasgenObligatorio;
    }
   
    public BigDecimal getFasgenId() {
        return this.fasgenId;
    }
    
    public void setFasgenId(BigDecimal fasgenId) {
        this.fasgenId = fasgenId;
    }
    public CivEtapasGenerales getCivEtapasGenerales() {
        return this.civEtapasGenerales;
    }
    
    public void setCivEtapasGenerales(CivEtapasGenerales civEtapasGenerales) {
        this.civEtapasGenerales = civEtapasGenerales;
    }
    public CivEstadoFasesGenerales getCivEstadoFasesGenerales() {
        return this.civEstadoFasesGenerales;
    }
    
    public void setCivEstadoFasesGenerales(CivEstadoFasesGenerales civEstadoFasesGenerales) {
        this.civEstadoFasesGenerales = civEstadoFasesGenerales;
    }
    public CivDocumenGenerales getCivDocumenGenerales() {
        return this.civDocumenGenerales;
    }
    
    public void setCivDocumenGenerales(CivDocumenGenerales civDocumenGenerales) {
        this.civDocumenGenerales = civDocumenGenerales;
    }
    public String getFasgenDescripcion() {
        return this.fasgenDescripcion;
    }
    
    public void setFasgenDescripcion(String fasgenDescripcion) {
        this.fasgenDescripcion = fasgenDescripcion;
    }
    public Date getFasgenFechaproceso() {
        return this.fasgenFechaproceso;
    }
    
    public void setFasgenFechaproceso(Date fasgenFechaproceso) {
        this.fasgenFechaproceso = fasgenFechaproceso;
    }
    public BigDecimal getFasgenDianim() {
        return this.fasgenDianim;
    }
    
    public void setFasgenDianim(BigDecimal fasgenDianim) {
        this.fasgenDianim = fasgenDianim;
    }
    public BigDecimal getFasgenDiamax() {
        return this.fasgenDiamax;
    }
    
    public void setFasgenDiamax(BigDecimal fasgenDiamax) {
        this.fasgenDiamax = fasgenDiamax;
    }
    public String getFasgenObligatorio() {
        return this.fasgenObligatorio;
    }
    
    public void setFasgenObligatorio(String fasgenObligatorio) {
        this.fasgenObligatorio = fasgenObligatorio;
    }




}


