package CobroCoactivo.Persistencia;
// Generated 9/08/2018 03:19:23 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivEstadoPagos generated by hbm2java
 */
public class CivEstadoPagos  implements java.io.Serializable {


     private BigDecimal estpagId;
     private String estpagDescripcion;
     private Date estpagFechainicial;
     private Date estpagFechafinal;
     private Date estpagFechaproceso;
     private Set civPagoses = new HashSet(0);

    public CivEstadoPagos() {
    }

	
    public CivEstadoPagos(BigDecimal estpagId, String estpagDescripcion) {
        this.estpagId = estpagId;
        this.estpagDescripcion = estpagDescripcion;
    }
    public CivEstadoPagos(BigDecimal estpagId, String estpagDescripcion, Date estpagFechainicial, Date estpagFechafinal, Date estpagFechaproceso, Set civPagoses) {
       this.estpagId = estpagId;
       this.estpagDescripcion = estpagDescripcion;
       this.estpagFechainicial = estpagFechainicial;
       this.estpagFechafinal = estpagFechafinal;
       this.estpagFechaproceso = estpagFechaproceso;
       this.civPagoses = civPagoses;
    }
   
    public BigDecimal getEstpagId() {
        return this.estpagId;
    }
    
    public void setEstpagId(BigDecimal estpagId) {
        this.estpagId = estpagId;
    }
    public String getEstpagDescripcion() {
        return this.estpagDescripcion;
    }
    
    public void setEstpagDescripcion(String estpagDescripcion) {
        this.estpagDescripcion = estpagDescripcion;
    }
    public Date getEstpagFechainicial() {
        return this.estpagFechainicial;
    }
    
    public void setEstpagFechainicial(Date estpagFechainicial) {
        this.estpagFechainicial = estpagFechainicial;
    }
    public Date getEstpagFechafinal() {
        return this.estpagFechafinal;
    }
    
    public void setEstpagFechafinal(Date estpagFechafinal) {
        this.estpagFechafinal = estpagFechafinal;
    }
    public Date getEstpagFechaproceso() {
        return this.estpagFechaproceso;
    }
    
    public void setEstpagFechaproceso(Date estpagFechaproceso) {
        this.estpagFechaproceso = estpagFechaproceso;
    }
    public Set getCivPagoses() {
        return this.civPagoses;
    }
    
    public void setCivPagoses(Set civPagoses) {
        this.civPagoses = civPagoses;
    }




}


