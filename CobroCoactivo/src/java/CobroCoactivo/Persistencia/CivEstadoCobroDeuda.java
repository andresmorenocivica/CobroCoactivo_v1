package CobroCoactivo.Persistencia;
// Generated 15/06/2018 09:05:14 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivEstadoCobroDeuda generated by hbm2java
 */
public class CivEstadoCobroDeuda  implements java.io.Serializable {


     private BigDecimal estcobdeuId;
     private String estcobdeuDescripcion;
     private Date estcobdeuFechainicial;
     private Date estcobdeuFechafinal;
     private Date estcobdeuFechaproceso;
     private Set civCobroDeudases = new HashSet(0);

    public CivEstadoCobroDeuda() {
    }

	
    public CivEstadoCobroDeuda(BigDecimal estcobdeuId, String estcobdeuDescripcion) {
        this.estcobdeuId = estcobdeuId;
        this.estcobdeuDescripcion = estcobdeuDescripcion;
    }
    public CivEstadoCobroDeuda(BigDecimal estcobdeuId, String estcobdeuDescripcion, Date estcobdeuFechainicial, Date estcobdeuFechafinal, Date estcobdeuFechaproceso, Set civCobroDeudases) {
       this.estcobdeuId = estcobdeuId;
       this.estcobdeuDescripcion = estcobdeuDescripcion;
       this.estcobdeuFechainicial = estcobdeuFechainicial;
       this.estcobdeuFechafinal = estcobdeuFechafinal;
       this.estcobdeuFechaproceso = estcobdeuFechaproceso;
       this.civCobroDeudases = civCobroDeudases;
    }
   
    public BigDecimal getEstcobdeuId() {
        return this.estcobdeuId;
    }
    
    public void setEstcobdeuId(BigDecimal estcobdeuId) {
        this.estcobdeuId = estcobdeuId;
    }
    public String getEstcobdeuDescripcion() {
        return this.estcobdeuDescripcion;
    }
    
    public void setEstcobdeuDescripcion(String estcobdeuDescripcion) {
        this.estcobdeuDescripcion = estcobdeuDescripcion;
    }
    public Date getEstcobdeuFechainicial() {
        return this.estcobdeuFechainicial;
    }
    
    public void setEstcobdeuFechainicial(Date estcobdeuFechainicial) {
        this.estcobdeuFechainicial = estcobdeuFechainicial;
    }
    public Date getEstcobdeuFechafinal() {
        return this.estcobdeuFechafinal;
    }
    
    public void setEstcobdeuFechafinal(Date estcobdeuFechafinal) {
        this.estcobdeuFechafinal = estcobdeuFechafinal;
    }
    public Date getEstcobdeuFechaproceso() {
        return this.estcobdeuFechaproceso;
    }
    
    public void setEstcobdeuFechaproceso(Date estcobdeuFechaproceso) {
        this.estcobdeuFechaproceso = estcobdeuFechaproceso;
    }
    public Set getCivCobroDeudases() {
        return this.civCobroDeudases;
    }
    
    public void setCivCobroDeudases(Set civCobroDeudases) {
        this.civCobroDeudases = civCobroDeudases;
    }




}

