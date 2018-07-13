package CobroCoactivo.Persistencia;
// Generated 13/07/2018 10:45:24 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivEstadoDeudas generated by hbm2java
 */
public class CivEstadoDeudas  implements java.io.Serializable {


     private BigDecimal estdeuId;
     private String estdeuDescripcion;
     private Date estdeuFechainicial;
     private Date estdeuFechafinal;
     private Date estdeuFechaproceso;
     private Set civDeudases = new HashSet(0);

    public CivEstadoDeudas() {
    }

	
    public CivEstadoDeudas(BigDecimal estdeuId, String estdeuDescripcion, Date estdeuFechainicial) {
        this.estdeuId = estdeuId;
        this.estdeuDescripcion = estdeuDescripcion;
        this.estdeuFechainicial = estdeuFechainicial;
    }
    public CivEstadoDeudas(BigDecimal estdeuId, String estdeuDescripcion, Date estdeuFechainicial, Date estdeuFechafinal, Date estdeuFechaproceso, Set civDeudases) {
       this.estdeuId = estdeuId;
       this.estdeuDescripcion = estdeuDescripcion;
       this.estdeuFechainicial = estdeuFechainicial;
       this.estdeuFechafinal = estdeuFechafinal;
       this.estdeuFechaproceso = estdeuFechaproceso;
       this.civDeudases = civDeudases;
    }
   
    public BigDecimal getEstdeuId() {
        return this.estdeuId;
    }
    
    public void setEstdeuId(BigDecimal estdeuId) {
        this.estdeuId = estdeuId;
    }
    public String getEstdeuDescripcion() {
        return this.estdeuDescripcion;
    }
    
    public void setEstdeuDescripcion(String estdeuDescripcion) {
        this.estdeuDescripcion = estdeuDescripcion;
    }
    public Date getEstdeuFechainicial() {
        return this.estdeuFechainicial;
    }
    
    public void setEstdeuFechainicial(Date estdeuFechainicial) {
        this.estdeuFechainicial = estdeuFechainicial;
    }
    public Date getEstdeuFechafinal() {
        return this.estdeuFechafinal;
    }
    
    public void setEstdeuFechafinal(Date estdeuFechafinal) {
        this.estdeuFechafinal = estdeuFechafinal;
    }
    public Date getEstdeuFechaproceso() {
        return this.estdeuFechaproceso;
    }
    
    public void setEstdeuFechaproceso(Date estdeuFechaproceso) {
        this.estdeuFechaproceso = estdeuFechaproceso;
    }
    public Set getCivDeudases() {
        return this.civDeudases;
    }
    
    public void setCivDeudases(Set civDeudases) {
        this.civDeudases = civDeudases;
    }




}


