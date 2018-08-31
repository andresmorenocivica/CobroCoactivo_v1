package CobroCoactivo.Persistencia;
// Generated 30/08/2018 09:56:13 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivEstadoArchDetExp generated by hbm2java
 */
public class CivEstadoArchDetExp  implements java.io.Serializable {


     private BigDecimal estarcdetexpId;
     private String estarcdetexpDescripcion;
     private Date estarcdetexpFechainicial;
     private Date estarcdetexpFechafinal;
     private Date estarcdetexpFechaproceso;
     private Set civArchivoDetExpedienteses = new HashSet(0);

    public CivEstadoArchDetExp() {
    }

	
    public CivEstadoArchDetExp(BigDecimal estarcdetexpId, String estarcdetexpDescripcion, Date estarcdetexpFechainicial) {
        this.estarcdetexpId = estarcdetexpId;
        this.estarcdetexpDescripcion = estarcdetexpDescripcion;
        this.estarcdetexpFechainicial = estarcdetexpFechainicial;
    }
    public CivEstadoArchDetExp(BigDecimal estarcdetexpId, String estarcdetexpDescripcion, Date estarcdetexpFechainicial, Date estarcdetexpFechafinal, Date estarcdetexpFechaproceso, Set civArchivoDetExpedienteses) {
       this.estarcdetexpId = estarcdetexpId;
       this.estarcdetexpDescripcion = estarcdetexpDescripcion;
       this.estarcdetexpFechainicial = estarcdetexpFechainicial;
       this.estarcdetexpFechafinal = estarcdetexpFechafinal;
       this.estarcdetexpFechaproceso = estarcdetexpFechaproceso;
       this.civArchivoDetExpedienteses = civArchivoDetExpedienteses;
    }
   
    public BigDecimal getEstarcdetexpId() {
        return this.estarcdetexpId;
    }
    
    public void setEstarcdetexpId(BigDecimal estarcdetexpId) {
        this.estarcdetexpId = estarcdetexpId;
    }
    public String getEstarcdetexpDescripcion() {
        return this.estarcdetexpDescripcion;
    }
    
    public void setEstarcdetexpDescripcion(String estarcdetexpDescripcion) {
        this.estarcdetexpDescripcion = estarcdetexpDescripcion;
    }
    public Date getEstarcdetexpFechainicial() {
        return this.estarcdetexpFechainicial;
    }
    
    public void setEstarcdetexpFechainicial(Date estarcdetexpFechainicial) {
        this.estarcdetexpFechainicial = estarcdetexpFechainicial;
    }
    public Date getEstarcdetexpFechafinal() {
        return this.estarcdetexpFechafinal;
    }
    
    public void setEstarcdetexpFechafinal(Date estarcdetexpFechafinal) {
        this.estarcdetexpFechafinal = estarcdetexpFechafinal;
    }
    public Date getEstarcdetexpFechaproceso() {
        return this.estarcdetexpFechaproceso;
    }
    
    public void setEstarcdetexpFechaproceso(Date estarcdetexpFechaproceso) {
        this.estarcdetexpFechaproceso = estarcdetexpFechaproceso;
    }
    public Set getCivArchivoDetExpedienteses() {
        return this.civArchivoDetExpedienteses;
    }
    
    public void setCivArchivoDetExpedienteses(Set civArchivoDetExpedienteses) {
        this.civArchivoDetExpedienteses = civArchivoDetExpedienteses;
    }




}


