package CobroCoactivo.Persistencia;
// Generated 26/07/2018 02:21:37 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivEstadoDetalleDeudas generated by hbm2java
 */
public class CivEstadoDetalleDeudas  implements java.io.Serializable {


     private BigDecimal estdetdeuId;
     private String estdetdeuDescripcion;
     private Date estdetdeuFechainicial;
     private Date estdetdeuFechafinal;
     private Date estdetdeuFechaproceso;
     private Set civDetalleDeudases = new HashSet(0);

    public CivEstadoDetalleDeudas() {
    }

	
    public CivEstadoDetalleDeudas(BigDecimal estdetdeuId, String estdetdeuDescripcion, Date estdetdeuFechainicial) {
        this.estdetdeuId = estdetdeuId;
        this.estdetdeuDescripcion = estdetdeuDescripcion;
        this.estdetdeuFechainicial = estdetdeuFechainicial;
    }
    public CivEstadoDetalleDeudas(BigDecimal estdetdeuId, String estdetdeuDescripcion, Date estdetdeuFechainicial, Date estdetdeuFechafinal, Date estdetdeuFechaproceso, Set civDetalleDeudases) {
       this.estdetdeuId = estdetdeuId;
       this.estdetdeuDescripcion = estdetdeuDescripcion;
       this.estdetdeuFechainicial = estdetdeuFechainicial;
       this.estdetdeuFechafinal = estdetdeuFechafinal;
       this.estdetdeuFechaproceso = estdetdeuFechaproceso;
       this.civDetalleDeudases = civDetalleDeudases;
    }
   
    public BigDecimal getEstdetdeuId() {
        return this.estdetdeuId;
    }
    
    public void setEstdetdeuId(BigDecimal estdetdeuId) {
        this.estdetdeuId = estdetdeuId;
    }
    public String getEstdetdeuDescripcion() {
        return this.estdetdeuDescripcion;
    }
    
    public void setEstdetdeuDescripcion(String estdetdeuDescripcion) {
        this.estdetdeuDescripcion = estdetdeuDescripcion;
    }
    public Date getEstdetdeuFechainicial() {
        return this.estdetdeuFechainicial;
    }
    
    public void setEstdetdeuFechainicial(Date estdetdeuFechainicial) {
        this.estdetdeuFechainicial = estdetdeuFechainicial;
    }
    public Date getEstdetdeuFechafinal() {
        return this.estdetdeuFechafinal;
    }
    
    public void setEstdetdeuFechafinal(Date estdetdeuFechafinal) {
        this.estdetdeuFechafinal = estdetdeuFechafinal;
    }
    public Date getEstdetdeuFechaproceso() {
        return this.estdetdeuFechaproceso;
    }
    
    public void setEstdetdeuFechaproceso(Date estdetdeuFechaproceso) {
        this.estdetdeuFechaproceso = estdetdeuFechaproceso;
    }
    public Set getCivDetalleDeudases() {
        return this.civDetalleDeudases;
    }
    
    public void setCivDetalleDeudases(Set civDetalleDeudases) {
        this.civDetalleDeudases = civDetalleDeudases;
    }




}


