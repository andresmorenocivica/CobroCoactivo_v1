package CobroCoactivo.Persistencia;
// Generated 10/08/2018 01:53:59 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivEstadoModulos generated by hbm2java
 */
public class CivEstadoModulos  implements java.io.Serializable {


     private BigDecimal estmodId;
     private String estmodDescripcion;
     private Date estmodFechainicial;
     private Date estmodFechafinal;
     private Date estmodFechaproceso;
     private Set civModuloses = new HashSet(0);

    public CivEstadoModulos() {
    }

	
    public CivEstadoModulos(BigDecimal estmodId, String estmodDescripcion) {
        this.estmodId = estmodId;
        this.estmodDescripcion = estmodDescripcion;
    }
    public CivEstadoModulos(BigDecimal estmodId, String estmodDescripcion, Date estmodFechainicial, Date estmodFechafinal, Date estmodFechaproceso, Set civModuloses) {
       this.estmodId = estmodId;
       this.estmodDescripcion = estmodDescripcion;
       this.estmodFechainicial = estmodFechainicial;
       this.estmodFechafinal = estmodFechafinal;
       this.estmodFechaproceso = estmodFechaproceso;
       this.civModuloses = civModuloses;
    }
   
    public BigDecimal getEstmodId() {
        return this.estmodId;
    }
    
    public void setEstmodId(BigDecimal estmodId) {
        this.estmodId = estmodId;
    }
    public String getEstmodDescripcion() {
        return this.estmodDescripcion;
    }
    
    public void setEstmodDescripcion(String estmodDescripcion) {
        this.estmodDescripcion = estmodDescripcion;
    }
    public Date getEstmodFechainicial() {
        return this.estmodFechainicial;
    }
    
    public void setEstmodFechainicial(Date estmodFechainicial) {
        this.estmodFechainicial = estmodFechainicial;
    }
    public Date getEstmodFechafinal() {
        return this.estmodFechafinal;
    }
    
    public void setEstmodFechafinal(Date estmodFechafinal) {
        this.estmodFechafinal = estmodFechafinal;
    }
    public Date getEstmodFechaproceso() {
        return this.estmodFechaproceso;
    }
    
    public void setEstmodFechaproceso(Date estmodFechaproceso) {
        this.estmodFechaproceso = estmodFechaproceso;
    }
    public Set getCivModuloses() {
        return this.civModuloses;
    }
    
    public void setCivModuloses(Set civModuloses) {
        this.civModuloses = civModuloses;
    }




}


