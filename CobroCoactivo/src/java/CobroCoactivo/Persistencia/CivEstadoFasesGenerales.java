package CobroCoactivo.Persistencia;
// Generated 2/08/2018 01:52:51 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivEstadoFasesGenerales generated by hbm2java
 */
public class CivEstadoFasesGenerales  implements java.io.Serializable {


     private BigDecimal estfasgenId;
     private String estfasgenDescripcion;
     private Date estfasgenFechainicial;
     private Date estfasgenFechafinal;
     private Date estfasgenFechaproceso;
     private Set civFasesGeneraleses = new HashSet(0);

    public CivEstadoFasesGenerales() {
    }

	
    public CivEstadoFasesGenerales(BigDecimal estfasgenId, String estfasgenDescripcion, Date estfasgenFechainicial) {
        this.estfasgenId = estfasgenId;
        this.estfasgenDescripcion = estfasgenDescripcion;
        this.estfasgenFechainicial = estfasgenFechainicial;
    }
    public CivEstadoFasesGenerales(BigDecimal estfasgenId, String estfasgenDescripcion, Date estfasgenFechainicial, Date estfasgenFechafinal, Date estfasgenFechaproceso, Set civFasesGeneraleses) {
       this.estfasgenId = estfasgenId;
       this.estfasgenDescripcion = estfasgenDescripcion;
       this.estfasgenFechainicial = estfasgenFechainicial;
       this.estfasgenFechafinal = estfasgenFechafinal;
       this.estfasgenFechaproceso = estfasgenFechaproceso;
       this.civFasesGeneraleses = civFasesGeneraleses;
    }
   
    public BigDecimal getEstfasgenId() {
        return this.estfasgenId;
    }
    
    public void setEstfasgenId(BigDecimal estfasgenId) {
        this.estfasgenId = estfasgenId;
    }
    public String getEstfasgenDescripcion() {
        return this.estfasgenDescripcion;
    }
    
    public void setEstfasgenDescripcion(String estfasgenDescripcion) {
        this.estfasgenDescripcion = estfasgenDescripcion;
    }
    public Date getEstfasgenFechainicial() {
        return this.estfasgenFechainicial;
    }
    
    public void setEstfasgenFechainicial(Date estfasgenFechainicial) {
        this.estfasgenFechainicial = estfasgenFechainicial;
    }
    public Date getEstfasgenFechafinal() {
        return this.estfasgenFechafinal;
    }
    
    public void setEstfasgenFechafinal(Date estfasgenFechafinal) {
        this.estfasgenFechafinal = estfasgenFechafinal;
    }
    public Date getEstfasgenFechaproceso() {
        return this.estfasgenFechaproceso;
    }
    
    public void setEstfasgenFechaproceso(Date estfasgenFechaproceso) {
        this.estfasgenFechaproceso = estfasgenFechaproceso;
    }
    public Set getCivFasesGeneraleses() {
        return this.civFasesGeneraleses;
    }
    
    public void setCivFasesGeneraleses(Set civFasesGeneraleses) {
        this.civFasesGeneraleses = civFasesGeneraleses;
    }




}


