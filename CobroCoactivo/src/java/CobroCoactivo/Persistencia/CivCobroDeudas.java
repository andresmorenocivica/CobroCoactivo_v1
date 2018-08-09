package CobroCoactivo.Persistencia;
// Generated 9/08/2018 03:19:23 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivCobroDeudas generated by hbm2java
 */
public class CivCobroDeudas  implements java.io.Serializable {


     private BigDecimal cobdeuId;
     private CivEstadoCobroDeuda civEstadoCobroDeuda;
     private String cobdeuDescripcion;
     private Date cobdeuFechaproceso;
     private Set civDeudases = new HashSet(0);
     private Set civDetalleCobroDeudases = new HashSet(0);

    public CivCobroDeudas() {
    }

	
    public CivCobroDeudas(BigDecimal cobdeuId) {
        this.cobdeuId = cobdeuId;
    }
    public CivCobroDeudas(BigDecimal cobdeuId, CivEstadoCobroDeuda civEstadoCobroDeuda, String cobdeuDescripcion, Date cobdeuFechaproceso, Set civDeudases, Set civDetalleCobroDeudases) {
       this.cobdeuId = cobdeuId;
       this.civEstadoCobroDeuda = civEstadoCobroDeuda;
       this.cobdeuDescripcion = cobdeuDescripcion;
       this.cobdeuFechaproceso = cobdeuFechaproceso;
       this.civDeudases = civDeudases;
       this.civDetalleCobroDeudases = civDetalleCobroDeudases;
    }
   
    public BigDecimal getCobdeuId() {
        return this.cobdeuId;
    }
    
    public void setCobdeuId(BigDecimal cobdeuId) {
        this.cobdeuId = cobdeuId;
    }
    public CivEstadoCobroDeuda getCivEstadoCobroDeuda() {
        return this.civEstadoCobroDeuda;
    }
    
    public void setCivEstadoCobroDeuda(CivEstadoCobroDeuda civEstadoCobroDeuda) {
        this.civEstadoCobroDeuda = civEstadoCobroDeuda;
    }
    public String getCobdeuDescripcion() {
        return this.cobdeuDescripcion;
    }
    
    public void setCobdeuDescripcion(String cobdeuDescripcion) {
        this.cobdeuDescripcion = cobdeuDescripcion;
    }
    public Date getCobdeuFechaproceso() {
        return this.cobdeuFechaproceso;
    }
    
    public void setCobdeuFechaproceso(Date cobdeuFechaproceso) {
        this.cobdeuFechaproceso = cobdeuFechaproceso;
    }
    public Set getCivDeudases() {
        return this.civDeudases;
    }
    
    public void setCivDeudases(Set civDeudases) {
        this.civDeudases = civDeudases;
    }
    public Set getCivDetalleCobroDeudases() {
        return this.civDetalleCobroDeudases;
    }
    
    public void setCivDetalleCobroDeudases(Set civDetalleCobroDeudases) {
        this.civDetalleCobroDeudases = civDetalleCobroDeudases;
    }




}


