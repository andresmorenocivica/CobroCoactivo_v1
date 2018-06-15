package CobroCoactivo.Persistencia;
// Generated 15/06/2018 09:05:14 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivEstadoIngresoDeudas generated by hbm2java
 */
public class CivEstadoIngresoDeudas  implements java.io.Serializable {


     private BigDecimal estingdeuId;
     private String estingdeuDescripcion;
     private Date estingdeuFechainicial;
     private Date estingdeuFechafinal;
     private Date estingdeuFechaproceso;
     private Set civIngresoDeudases = new HashSet(0);

    public CivEstadoIngresoDeudas() {
    }

	
    public CivEstadoIngresoDeudas(BigDecimal estingdeuId, String estingdeuDescripcion, Date estingdeuFechainicial) {
        this.estingdeuId = estingdeuId;
        this.estingdeuDescripcion = estingdeuDescripcion;
        this.estingdeuFechainicial = estingdeuFechainicial;
    }
    public CivEstadoIngresoDeudas(BigDecimal estingdeuId, String estingdeuDescripcion, Date estingdeuFechainicial, Date estingdeuFechafinal, Date estingdeuFechaproceso, Set civIngresoDeudases) {
       this.estingdeuId = estingdeuId;
       this.estingdeuDescripcion = estingdeuDescripcion;
       this.estingdeuFechainicial = estingdeuFechainicial;
       this.estingdeuFechafinal = estingdeuFechafinal;
       this.estingdeuFechaproceso = estingdeuFechaproceso;
       this.civIngresoDeudases = civIngresoDeudases;
    }
   
    public BigDecimal getEstingdeuId() {
        return this.estingdeuId;
    }
    
    public void setEstingdeuId(BigDecimal estingdeuId) {
        this.estingdeuId = estingdeuId;
    }
    public String getEstingdeuDescripcion() {
        return this.estingdeuDescripcion;
    }
    
    public void setEstingdeuDescripcion(String estingdeuDescripcion) {
        this.estingdeuDescripcion = estingdeuDescripcion;
    }
    public Date getEstingdeuFechainicial() {
        return this.estingdeuFechainicial;
    }
    
    public void setEstingdeuFechainicial(Date estingdeuFechainicial) {
        this.estingdeuFechainicial = estingdeuFechainicial;
    }
    public Date getEstingdeuFechafinal() {
        return this.estingdeuFechafinal;
    }
    
    public void setEstingdeuFechafinal(Date estingdeuFechafinal) {
        this.estingdeuFechafinal = estingdeuFechafinal;
    }
    public Date getEstingdeuFechaproceso() {
        return this.estingdeuFechaproceso;
    }
    
    public void setEstingdeuFechaproceso(Date estingdeuFechaproceso) {
        this.estingdeuFechaproceso = estingdeuFechaproceso;
    }
    public Set getCivIngresoDeudases() {
        return this.civIngresoDeudases;
    }
    
    public void setCivIngresoDeudases(Set civIngresoDeudases) {
        this.civIngresoDeudases = civIngresoDeudases;
    }




}


