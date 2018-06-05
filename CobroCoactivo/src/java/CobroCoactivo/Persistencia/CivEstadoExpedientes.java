package CobroCoactivo.Persistencia;
// Generated 30/05/2018 02:43:14 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivEstadoExpedientes generated by hbm2java
 */
public class CivEstadoExpedientes  implements java.io.Serializable {


     private BigDecimal estexpId;
     private String estexpDescripcion;
     private Date estexpFechainicial;
     private Date estexpFechafinal;
     private Date estexpFechaproceso;
     private Set civExpedienteses = new HashSet(0);

    public CivEstadoExpedientes() {
    }

	
    public CivEstadoExpedientes(BigDecimal estexpId, String estexpDescripcion) {
        this.estexpId = estexpId;
        this.estexpDescripcion = estexpDescripcion;
    }
    public CivEstadoExpedientes(BigDecimal estexpId, String estexpDescripcion, Date estexpFechainicial, Date estexpFechafinal, Date estexpFechaproceso, Set civExpedienteses) {
       this.estexpId = estexpId;
       this.estexpDescripcion = estexpDescripcion;
       this.estexpFechainicial = estexpFechainicial;
       this.estexpFechafinal = estexpFechafinal;
       this.estexpFechaproceso = estexpFechaproceso;
       this.civExpedienteses = civExpedienteses;
    }
   
    public BigDecimal getEstexpId() {
        return this.estexpId;
    }
    
    public void setEstexpId(BigDecimal estexpId) {
        this.estexpId = estexpId;
    }
    public String getEstexpDescripcion() {
        return this.estexpDescripcion;
    }
    
    public void setEstexpDescripcion(String estexpDescripcion) {
        this.estexpDescripcion = estexpDescripcion;
    }
    public Date getEstexpFechainicial() {
        return this.estexpFechainicial;
    }
    
    public void setEstexpFechainicial(Date estexpFechainicial) {
        this.estexpFechainicial = estexpFechainicial;
    }
    public Date getEstexpFechafinal() {
        return this.estexpFechafinal;
    }
    
    public void setEstexpFechafinal(Date estexpFechafinal) {
        this.estexpFechafinal = estexpFechafinal;
    }
    public Date getEstexpFechaproceso() {
        return this.estexpFechaproceso;
    }
    
    public void setEstexpFechaproceso(Date estexpFechaproceso) {
        this.estexpFechaproceso = estexpFechaproceso;
    }
    public Set getCivExpedienteses() {
        return this.civExpedienteses;
    }
    
    public void setCivExpedienteses(Set civExpedienteses) {
        this.civExpedienteses = civExpedienteses;
    }




}


