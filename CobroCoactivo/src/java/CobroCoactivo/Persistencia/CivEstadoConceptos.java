package CobroCoactivo.Persistencia;
// Generated 30/05/2018 02:43:14 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivEstadoConceptos generated by hbm2java
 */
public class CivEstadoConceptos  implements java.io.Serializable {


     private BigDecimal estconId;
     private String estconDescripcion;
     private Date estconFechainicial;
     private Date estconFechafinal;
     private Date estconFechaproceso;
     private Set civConceptoses = new HashSet(0);

    public CivEstadoConceptos() {
    }

	
    public CivEstadoConceptos(BigDecimal estconId, String estconDescripcion) {
        this.estconId = estconId;
        this.estconDescripcion = estconDescripcion;
    }
    public CivEstadoConceptos(BigDecimal estconId, String estconDescripcion, Date estconFechainicial, Date estconFechafinal, Date estconFechaproceso, Set civConceptoses) {
       this.estconId = estconId;
       this.estconDescripcion = estconDescripcion;
       this.estconFechainicial = estconFechainicial;
       this.estconFechafinal = estconFechafinal;
       this.estconFechaproceso = estconFechaproceso;
       this.civConceptoses = civConceptoses;
    }
   
    public BigDecimal getEstconId() {
        return this.estconId;
    }
    
    public void setEstconId(BigDecimal estconId) {
        this.estconId = estconId;
    }
    public String getEstconDescripcion() {
        return this.estconDescripcion;
    }
    
    public void setEstconDescripcion(String estconDescripcion) {
        this.estconDescripcion = estconDescripcion;
    }
    public Date getEstconFechainicial() {
        return this.estconFechainicial;
    }
    
    public void setEstconFechainicial(Date estconFechainicial) {
        this.estconFechainicial = estconFechainicial;
    }
    public Date getEstconFechafinal() {
        return this.estconFechafinal;
    }
    
    public void setEstconFechafinal(Date estconFechafinal) {
        this.estconFechafinal = estconFechafinal;
    }
    public Date getEstconFechaproceso() {
        return this.estconFechaproceso;
    }
    
    public void setEstconFechaproceso(Date estconFechaproceso) {
        this.estconFechaproceso = estconFechaproceso;
    }
    public Set getCivConceptoses() {
        return this.civConceptoses;
    }
    
    public void setCivConceptoses(Set civConceptoses) {
        this.civConceptoses = civConceptoses;
    }




}


