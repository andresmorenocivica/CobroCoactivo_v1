package CobroCoactivo.Persistencia;
// Generated 13/07/2018 10:45:24 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivEstadoPersonas generated by hbm2java
 */
public class CivEstadoPersonas  implements java.io.Serializable {


     private BigDecimal estperId;
     private String estperDescripcion;
     private Date estperFechainicial;
     private Date estperFechafinal;
     private Date estperFechaproceso;
     private Set civPersonases = new HashSet(0);

    public CivEstadoPersonas() {
    }

	
    public CivEstadoPersonas(BigDecimal estperId, String estperDescripcion) {
        this.estperId = estperId;
        this.estperDescripcion = estperDescripcion;
    }
    public CivEstadoPersonas(BigDecimal estperId, String estperDescripcion, Date estperFechainicial, Date estperFechafinal, Date estperFechaproceso, Set civPersonases) {
       this.estperId = estperId;
       this.estperDescripcion = estperDescripcion;
       this.estperFechainicial = estperFechainicial;
       this.estperFechafinal = estperFechafinal;
       this.estperFechaproceso = estperFechaproceso;
       this.civPersonases = civPersonases;
    }
   
    public BigDecimal getEstperId() {
        return this.estperId;
    }
    
    public void setEstperId(BigDecimal estperId) {
        this.estperId = estperId;
    }
    public String getEstperDescripcion() {
        return this.estperDescripcion;
    }
    
    public void setEstperDescripcion(String estperDescripcion) {
        this.estperDescripcion = estperDescripcion;
    }
    public Date getEstperFechainicial() {
        return this.estperFechainicial;
    }
    
    public void setEstperFechainicial(Date estperFechainicial) {
        this.estperFechainicial = estperFechainicial;
    }
    public Date getEstperFechafinal() {
        return this.estperFechafinal;
    }
    
    public void setEstperFechafinal(Date estperFechafinal) {
        this.estperFechafinal = estperFechafinal;
    }
    public Date getEstperFechaproceso() {
        return this.estperFechaproceso;
    }
    
    public void setEstperFechaproceso(Date estperFechaproceso) {
        this.estperFechaproceso = estperFechaproceso;
    }
    public Set getCivPersonases() {
        return this.civPersonases;
    }
    
    public void setCivPersonases(Set civPersonases) {
        this.civPersonases = civPersonases;
    }




}


