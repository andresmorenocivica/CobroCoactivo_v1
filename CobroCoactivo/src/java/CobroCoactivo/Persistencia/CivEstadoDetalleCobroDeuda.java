package CobroCoactivo.Persistencia;
// Generated 2/08/2018 01:52:51 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivEstadoDetalleCobroDeuda generated by hbm2java
 */
public class CivEstadoDetalleCobroDeuda  implements java.io.Serializable {


     private BigDecimal estdetcobdeuId;
     private String estdetcobdeuDescripcion;
     private Date estdetcobdeuFechainicial;
     private Date estdetcobdeuFechafinal;
     private Date estdetcobdeuFechaproceso;
     private Set civDetalleCobroDeudases = new HashSet(0);

    public CivEstadoDetalleCobroDeuda() {
    }

	
    public CivEstadoDetalleCobroDeuda(BigDecimal estdetcobdeuId, String estdetcobdeuDescripcion) {
        this.estdetcobdeuId = estdetcobdeuId;
        this.estdetcobdeuDescripcion = estdetcobdeuDescripcion;
    }
    public CivEstadoDetalleCobroDeuda(BigDecimal estdetcobdeuId, String estdetcobdeuDescripcion, Date estdetcobdeuFechainicial, Date estdetcobdeuFechafinal, Date estdetcobdeuFechaproceso, Set civDetalleCobroDeudases) {
       this.estdetcobdeuId = estdetcobdeuId;
       this.estdetcobdeuDescripcion = estdetcobdeuDescripcion;
       this.estdetcobdeuFechainicial = estdetcobdeuFechainicial;
       this.estdetcobdeuFechafinal = estdetcobdeuFechafinal;
       this.estdetcobdeuFechaproceso = estdetcobdeuFechaproceso;
       this.civDetalleCobroDeudases = civDetalleCobroDeudases;
    }
   
    public BigDecimal getEstdetcobdeuId() {
        return this.estdetcobdeuId;
    }
    
    public void setEstdetcobdeuId(BigDecimal estdetcobdeuId) {
        this.estdetcobdeuId = estdetcobdeuId;
    }
    public String getEstdetcobdeuDescripcion() {
        return this.estdetcobdeuDescripcion;
    }
    
    public void setEstdetcobdeuDescripcion(String estdetcobdeuDescripcion) {
        this.estdetcobdeuDescripcion = estdetcobdeuDescripcion;
    }
    public Date getEstdetcobdeuFechainicial() {
        return this.estdetcobdeuFechainicial;
    }
    
    public void setEstdetcobdeuFechainicial(Date estdetcobdeuFechainicial) {
        this.estdetcobdeuFechainicial = estdetcobdeuFechainicial;
    }
    public Date getEstdetcobdeuFechafinal() {
        return this.estdetcobdeuFechafinal;
    }
    
    public void setEstdetcobdeuFechafinal(Date estdetcobdeuFechafinal) {
        this.estdetcobdeuFechafinal = estdetcobdeuFechafinal;
    }
    public Date getEstdetcobdeuFechaproceso() {
        return this.estdetcobdeuFechaproceso;
    }
    
    public void setEstdetcobdeuFechaproceso(Date estdetcobdeuFechaproceso) {
        this.estdetcobdeuFechaproceso = estdetcobdeuFechaproceso;
    }
    public Set getCivDetalleCobroDeudases() {
        return this.civDetalleCobroDeudases;
    }
    
    public void setCivDetalleCobroDeudases(Set civDetalleCobroDeudases) {
        this.civDetalleCobroDeudases = civDetalleCobroDeudases;
    }




}


