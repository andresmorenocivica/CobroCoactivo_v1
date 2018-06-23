package CobroCoactivo.Persistencia;
// Generated 23/06/2018 10:46:28 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivEstadoPerfiles generated by hbm2java
 */
public class CivEstadoPerfiles  implements java.io.Serializable {


     private BigDecimal estperfId;
     private String estperfDescripcion;
     private Date estperfFechainicial;
     private Date estperfFechafinal;
     private Date estperfFechaproceso;
     private Set civPerfileses = new HashSet(0);

    public CivEstadoPerfiles() {
    }

	
    public CivEstadoPerfiles(BigDecimal estperfId, String estperfDescripcion, Date estperfFechainicial) {
        this.estperfId = estperfId;
        this.estperfDescripcion = estperfDescripcion;
        this.estperfFechainicial = estperfFechainicial;
    }
    public CivEstadoPerfiles(BigDecimal estperfId, String estperfDescripcion, Date estperfFechainicial, Date estperfFechafinal, Date estperfFechaproceso, Set civPerfileses) {
       this.estperfId = estperfId;
       this.estperfDescripcion = estperfDescripcion;
       this.estperfFechainicial = estperfFechainicial;
       this.estperfFechafinal = estperfFechafinal;
       this.estperfFechaproceso = estperfFechaproceso;
       this.civPerfileses = civPerfileses;
    }
   
    public BigDecimal getEstperfId() {
        return this.estperfId;
    }
    
    public void setEstperfId(BigDecimal estperfId) {
        this.estperfId = estperfId;
    }
    public String getEstperfDescripcion() {
        return this.estperfDescripcion;
    }
    
    public void setEstperfDescripcion(String estperfDescripcion) {
        this.estperfDescripcion = estperfDescripcion;
    }
    public Date getEstperfFechainicial() {
        return this.estperfFechainicial;
    }
    
    public void setEstperfFechainicial(Date estperfFechainicial) {
        this.estperfFechainicial = estperfFechainicial;
    }
    public Date getEstperfFechafinal() {
        return this.estperfFechafinal;
    }
    
    public void setEstperfFechafinal(Date estperfFechafinal) {
        this.estperfFechafinal = estperfFechafinal;
    }
    public Date getEstperfFechaproceso() {
        return this.estperfFechaproceso;
    }
    
    public void setEstperfFechaproceso(Date estperfFechaproceso) {
        this.estperfFechaproceso = estperfFechaproceso;
    }
    public Set getCivPerfileses() {
        return this.civPerfileses;
    }
    
    public void setCivPerfileses(Set civPerfileses) {
        this.civPerfileses = civPerfileses;
    }




}


