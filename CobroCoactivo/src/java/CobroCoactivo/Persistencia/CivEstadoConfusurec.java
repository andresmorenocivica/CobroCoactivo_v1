package CobroCoactivo.Persistencia;
// Generated 30/08/2018 09:56:13 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivEstadoConfusurec generated by hbm2java
 */
public class CivEstadoConfusurec  implements java.io.Serializable {


     private BigDecimal estconfusurecId;
     private String estconfusurecDescripcion;
     private Date estconfusurecFechainicial;
     private Date estconfusurecFechafinal;
     private Date estconfusurecFechaproceso;
     private Set civConfUsuRecs = new HashSet(0);

    public CivEstadoConfusurec() {
    }

	
    public CivEstadoConfusurec(BigDecimal estconfusurecId) {
        this.estconfusurecId = estconfusurecId;
    }
    public CivEstadoConfusurec(BigDecimal estconfusurecId, String estconfusurecDescripcion, Date estconfusurecFechainicial, Date estconfusurecFechafinal, Date estconfusurecFechaproceso, Set civConfUsuRecs) {
       this.estconfusurecId = estconfusurecId;
       this.estconfusurecDescripcion = estconfusurecDescripcion;
       this.estconfusurecFechainicial = estconfusurecFechainicial;
       this.estconfusurecFechafinal = estconfusurecFechafinal;
       this.estconfusurecFechaproceso = estconfusurecFechaproceso;
       this.civConfUsuRecs = civConfUsuRecs;
    }
   
    public BigDecimal getEstconfusurecId() {
        return this.estconfusurecId;
    }
    
    public void setEstconfusurecId(BigDecimal estconfusurecId) {
        this.estconfusurecId = estconfusurecId;
    }
    public String getEstconfusurecDescripcion() {
        return this.estconfusurecDescripcion;
    }
    
    public void setEstconfusurecDescripcion(String estconfusurecDescripcion) {
        this.estconfusurecDescripcion = estconfusurecDescripcion;
    }
    public Date getEstconfusurecFechainicial() {
        return this.estconfusurecFechainicial;
    }
    
    public void setEstconfusurecFechainicial(Date estconfusurecFechainicial) {
        this.estconfusurecFechainicial = estconfusurecFechainicial;
    }
    public Date getEstconfusurecFechafinal() {
        return this.estconfusurecFechafinal;
    }
    
    public void setEstconfusurecFechafinal(Date estconfusurecFechafinal) {
        this.estconfusurecFechafinal = estconfusurecFechafinal;
    }
    public Date getEstconfusurecFechaproceso() {
        return this.estconfusurecFechaproceso;
    }
    
    public void setEstconfusurecFechaproceso(Date estconfusurecFechaproceso) {
        this.estconfusurecFechaproceso = estconfusurecFechaproceso;
    }
    public Set getCivConfUsuRecs() {
        return this.civConfUsuRecs;
    }
    
    public void setCivConfUsuRecs(Set civConfUsuRecs) {
        this.civConfUsuRecs = civConfUsuRecs;
    }




}


