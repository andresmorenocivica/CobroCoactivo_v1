package CobroCoactivo.Persistencia;
// Generated 2/08/2018 01:52:51 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivEstadoRecursos generated by hbm2java
 */
public class CivEstadoRecursos  implements java.io.Serializable {


     private BigDecimal estrecId;
     private String estrecDescripcion;
     private Date estrecFechainicial;
     private Date estrecFechafinal;
     private Date estrecFechaproceso;
     private Set civRecursoses = new HashSet(0);

    public CivEstadoRecursos() {
    }

	
    public CivEstadoRecursos(BigDecimal estrecId, String estrecDescripcion, Date estrecFechainicial) {
        this.estrecId = estrecId;
        this.estrecDescripcion = estrecDescripcion;
        this.estrecFechainicial = estrecFechainicial;
    }
    public CivEstadoRecursos(BigDecimal estrecId, String estrecDescripcion, Date estrecFechainicial, Date estrecFechafinal, Date estrecFechaproceso, Set civRecursoses) {
       this.estrecId = estrecId;
       this.estrecDescripcion = estrecDescripcion;
       this.estrecFechainicial = estrecFechainicial;
       this.estrecFechafinal = estrecFechafinal;
       this.estrecFechaproceso = estrecFechaproceso;
       this.civRecursoses = civRecursoses;
    }
   
    public BigDecimal getEstrecId() {
        return this.estrecId;
    }
    
    public void setEstrecId(BigDecimal estrecId) {
        this.estrecId = estrecId;
    }
    public String getEstrecDescripcion() {
        return this.estrecDescripcion;
    }
    
    public void setEstrecDescripcion(String estrecDescripcion) {
        this.estrecDescripcion = estrecDescripcion;
    }
    public Date getEstrecFechainicial() {
        return this.estrecFechainicial;
    }
    
    public void setEstrecFechainicial(Date estrecFechainicial) {
        this.estrecFechainicial = estrecFechainicial;
    }
    public Date getEstrecFechafinal() {
        return this.estrecFechafinal;
    }
    
    public void setEstrecFechafinal(Date estrecFechafinal) {
        this.estrecFechafinal = estrecFechafinal;
    }
    public Date getEstrecFechaproceso() {
        return this.estrecFechaproceso;
    }
    
    public void setEstrecFechaproceso(Date estrecFechaproceso) {
        this.estrecFechaproceso = estrecFechaproceso;
    }
    public Set getCivRecursoses() {
        return this.civRecursoses;
    }
    
    public void setCivRecursoses(Set civRecursoses) {
        this.civRecursoses = civRecursoses;
    }




}


