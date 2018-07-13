package CobroCoactivo.Persistencia;
// Generated 13/07/2018 10:45:24 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivEstadoEntidades generated by hbm2java
 */
public class CivEstadoEntidades  implements java.io.Serializable {


     private BigDecimal estentId;
     private String estentDescripcion;
     private Date estentFechainicial;
     private Date estentFechafinal;
     private Date estentFechaproceso;
     private Set civEntidadeses = new HashSet(0);

    public CivEstadoEntidades() {
    }

	
    public CivEstadoEntidades(BigDecimal estentId, String estentDescripcion, Date estentFechainicial) {
        this.estentId = estentId;
        this.estentDescripcion = estentDescripcion;
        this.estentFechainicial = estentFechainicial;
    }
    public CivEstadoEntidades(BigDecimal estentId, String estentDescripcion, Date estentFechainicial, Date estentFechafinal, Date estentFechaproceso, Set civEntidadeses) {
       this.estentId = estentId;
       this.estentDescripcion = estentDescripcion;
       this.estentFechainicial = estentFechainicial;
       this.estentFechafinal = estentFechafinal;
       this.estentFechaproceso = estentFechaproceso;
       this.civEntidadeses = civEntidadeses;
    }
   
    public BigDecimal getEstentId() {
        return this.estentId;
    }
    
    public void setEstentId(BigDecimal estentId) {
        this.estentId = estentId;
    }
    public String getEstentDescripcion() {
        return this.estentDescripcion;
    }
    
    public void setEstentDescripcion(String estentDescripcion) {
        this.estentDescripcion = estentDescripcion;
    }
    public Date getEstentFechainicial() {
        return this.estentFechainicial;
    }
    
    public void setEstentFechainicial(Date estentFechainicial) {
        this.estentFechainicial = estentFechainicial;
    }
    public Date getEstentFechafinal() {
        return this.estentFechafinal;
    }
    
    public void setEstentFechafinal(Date estentFechafinal) {
        this.estentFechafinal = estentFechafinal;
    }
    public Date getEstentFechaproceso() {
        return this.estentFechaproceso;
    }
    
    public void setEstentFechaproceso(Date estentFechaproceso) {
        this.estentFechaproceso = estentFechaproceso;
    }
    public Set getCivEntidadeses() {
        return this.civEntidadeses;
    }
    
    public void setCivEntidadeses(Set civEntidadeses) {
        this.civEntidadeses = civEntidadeses;
    }




}


