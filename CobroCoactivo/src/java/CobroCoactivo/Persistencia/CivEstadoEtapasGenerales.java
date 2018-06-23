package CobroCoactivo.Persistencia;
// Generated 23/06/2018 10:46:28 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivEstadoEtapasGenerales generated by hbm2java
 */
public class CivEstadoEtapasGenerales  implements java.io.Serializable {


     private BigDecimal estetagenId;
     private String estetagenDescripcion;
     private Date estetagenFechainicial;
     private Date estetagenFechafinal;
     private Date estetagenFechaproceso;
     private Set civEtapasGeneraleses = new HashSet(0);

    public CivEstadoEtapasGenerales() {
    }

	
    public CivEstadoEtapasGenerales(BigDecimal estetagenId, String estetagenDescripcion, Date estetagenFechainicial) {
        this.estetagenId = estetagenId;
        this.estetagenDescripcion = estetagenDescripcion;
        this.estetagenFechainicial = estetagenFechainicial;
    }
    public CivEstadoEtapasGenerales(BigDecimal estetagenId, String estetagenDescripcion, Date estetagenFechainicial, Date estetagenFechafinal, Date estetagenFechaproceso, Set civEtapasGeneraleses) {
       this.estetagenId = estetagenId;
       this.estetagenDescripcion = estetagenDescripcion;
       this.estetagenFechainicial = estetagenFechainicial;
       this.estetagenFechafinal = estetagenFechafinal;
       this.estetagenFechaproceso = estetagenFechaproceso;
       this.civEtapasGeneraleses = civEtapasGeneraleses;
    }
   
    public BigDecimal getEstetagenId() {
        return this.estetagenId;
    }
    
    public void setEstetagenId(BigDecimal estetagenId) {
        this.estetagenId = estetagenId;
    }
    public String getEstetagenDescripcion() {
        return this.estetagenDescripcion;
    }
    
    public void setEstetagenDescripcion(String estetagenDescripcion) {
        this.estetagenDescripcion = estetagenDescripcion;
    }
    public Date getEstetagenFechainicial() {
        return this.estetagenFechainicial;
    }
    
    public void setEstetagenFechainicial(Date estetagenFechainicial) {
        this.estetagenFechainicial = estetagenFechainicial;
    }
    public Date getEstetagenFechafinal() {
        return this.estetagenFechafinal;
    }
    
    public void setEstetagenFechafinal(Date estetagenFechafinal) {
        this.estetagenFechafinal = estetagenFechafinal;
    }
    public Date getEstetagenFechaproceso() {
        return this.estetagenFechaproceso;
    }
    
    public void setEstetagenFechaproceso(Date estetagenFechaproceso) {
        this.estetagenFechaproceso = estetagenFechaproceso;
    }
    public Set getCivEtapasGeneraleses() {
        return this.civEtapasGeneraleses;
    }
    
    public void setCivEtapasGeneraleses(Set civEtapasGeneraleses) {
        this.civEtapasGeneraleses = civEtapasGeneraleses;
    }




}


