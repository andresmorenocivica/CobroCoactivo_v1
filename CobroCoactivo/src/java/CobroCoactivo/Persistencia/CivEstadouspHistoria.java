package CobroCoactivo.Persistencia;
// Generated 25/07/2018 05:27:39 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivEstadouspHistoria generated by hbm2java
 */
public class CivEstadouspHistoria  implements java.io.Serializable {


     private BigDecimal estuspId;
     private String estuspDescripcion;
     private Date estuspFechainicial;
     private Date estuspFechafinal;
     private Date estuspFechaproceso;
     private Date estuspFechafianl;
     private Set civUspHistorias = new HashSet(0);

    public CivEstadouspHistoria() {
    }

	
    public CivEstadouspHistoria(BigDecimal estuspId, String estuspDescripcion, Date estuspFechainicial) {
        this.estuspId = estuspId;
        this.estuspDescripcion = estuspDescripcion;
        this.estuspFechainicial = estuspFechainicial;
    }
    public CivEstadouspHistoria(BigDecimal estuspId, String estuspDescripcion, Date estuspFechainicial, Date estuspFechafinal, Date estuspFechaproceso, Date estuspFechafianl, Set civUspHistorias) {
       this.estuspId = estuspId;
       this.estuspDescripcion = estuspDescripcion;
       this.estuspFechainicial = estuspFechainicial;
       this.estuspFechafinal = estuspFechafinal;
       this.estuspFechaproceso = estuspFechaproceso;
       this.estuspFechafianl = estuspFechafianl;
       this.civUspHistorias = civUspHistorias;
    }
   
    public BigDecimal getEstuspId() {
        return this.estuspId;
    }
    
    public void setEstuspId(BigDecimal estuspId) {
        this.estuspId = estuspId;
    }
    public String getEstuspDescripcion() {
        return this.estuspDescripcion;
    }
    
    public void setEstuspDescripcion(String estuspDescripcion) {
        this.estuspDescripcion = estuspDescripcion;
    }
    public Date getEstuspFechainicial() {
        return this.estuspFechainicial;
    }
    
    public void setEstuspFechainicial(Date estuspFechainicial) {
        this.estuspFechainicial = estuspFechainicial;
    }
    public Date getEstuspFechafinal() {
        return this.estuspFechafinal;
    }
    
    public void setEstuspFechafinal(Date estuspFechafinal) {
        this.estuspFechafinal = estuspFechafinal;
    }
    public Date getEstuspFechaproceso() {
        return this.estuspFechaproceso;
    }
    
    public void setEstuspFechaproceso(Date estuspFechaproceso) {
        this.estuspFechaproceso = estuspFechaproceso;
    }
    public Date getEstuspFechafianl() {
        return this.estuspFechafianl;
    }
    
    public void setEstuspFechafianl(Date estuspFechafianl) {
        this.estuspFechafianl = estuspFechafianl;
    }
    public Set getCivUspHistorias() {
        return this.civUspHistorias;
    }
    
    public void setCivUspHistorias(Set civUspHistorias) {
        this.civUspHistorias = civUspHistorias;
    }




}


