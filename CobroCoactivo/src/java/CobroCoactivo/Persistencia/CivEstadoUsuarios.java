package CobroCoactivo.Persistencia;
// Generated 30/08/2018 09:56:13 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivEstadoUsuarios generated by hbm2java
 */
public class CivEstadoUsuarios  implements java.io.Serializable {


     private BigDecimal estusuId;
     private String estusuDescripcion;
     private Date estusuFechainicial;
     private Date estusuFechafinal;
     private Date estusuFechaproceso;
     private Set civUsuarioses = new HashSet(0);

    public CivEstadoUsuarios() {
    }

	
    public CivEstadoUsuarios(BigDecimal estusuId, String estusuDescripcion, Date estusuFechainicial) {
        this.estusuId = estusuId;
        this.estusuDescripcion = estusuDescripcion;
        this.estusuFechainicial = estusuFechainicial;
    }
    public CivEstadoUsuarios(BigDecimal estusuId, String estusuDescripcion, Date estusuFechainicial, Date estusuFechafinal, Date estusuFechaproceso, Set civUsuarioses) {
       this.estusuId = estusuId;
       this.estusuDescripcion = estusuDescripcion;
       this.estusuFechainicial = estusuFechainicial;
       this.estusuFechafinal = estusuFechafinal;
       this.estusuFechaproceso = estusuFechaproceso;
       this.civUsuarioses = civUsuarioses;
    }
   
    public BigDecimal getEstusuId() {
        return this.estusuId;
    }
    
    public void setEstusuId(BigDecimal estusuId) {
        this.estusuId = estusuId;
    }
    public String getEstusuDescripcion() {
        return this.estusuDescripcion;
    }
    
    public void setEstusuDescripcion(String estusuDescripcion) {
        this.estusuDescripcion = estusuDescripcion;
    }
    public Date getEstusuFechainicial() {
        return this.estusuFechainicial;
    }
    
    public void setEstusuFechainicial(Date estusuFechainicial) {
        this.estusuFechainicial = estusuFechainicial;
    }
    public Date getEstusuFechafinal() {
        return this.estusuFechafinal;
    }
    
    public void setEstusuFechafinal(Date estusuFechafinal) {
        this.estusuFechafinal = estusuFechafinal;
    }
    public Date getEstusuFechaproceso() {
        return this.estusuFechaproceso;
    }
    
    public void setEstusuFechaproceso(Date estusuFechaproceso) {
        this.estusuFechaproceso = estusuFechaproceso;
    }
    public Set getCivUsuarioses() {
        return this.civUsuarioses;
    }
    
    public void setCivUsuarioses(Set civUsuarioses) {
        this.civUsuarioses = civUsuarioses;
    }




}


