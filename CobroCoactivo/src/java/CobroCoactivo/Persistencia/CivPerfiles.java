package CobroCoactivo.Persistencia;
// Generated 27/07/2018 01:43:20 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivPerfiles generated by hbm2java
 */
public class CivPerfiles  implements java.io.Serializable {


     private BigDecimal perfId;
     private CivEstadoPerfiles civEstadoPerfiles;
     private String perfNombre;
     private Date perfFechaproceso;
     private Set civRecursoses = new HashSet(0);

    public CivPerfiles() {
    }

	
    public CivPerfiles(BigDecimal perfId, CivEstadoPerfiles civEstadoPerfiles, String perfNombre) {
        this.perfId = perfId;
        this.civEstadoPerfiles = civEstadoPerfiles;
        this.perfNombre = perfNombre;
    }
    public CivPerfiles(BigDecimal perfId, CivEstadoPerfiles civEstadoPerfiles, String perfNombre, Date perfFechaproceso, Set civRecursoses) {
       this.perfId = perfId;
       this.civEstadoPerfiles = civEstadoPerfiles;
       this.perfNombre = perfNombre;
       this.perfFechaproceso = perfFechaproceso;
       this.civRecursoses = civRecursoses;
    }
   
    public BigDecimal getPerfId() {
        return this.perfId;
    }
    
    public void setPerfId(BigDecimal perfId) {
        this.perfId = perfId;
    }
    public CivEstadoPerfiles getCivEstadoPerfiles() {
        return this.civEstadoPerfiles;
    }
    
    public void setCivEstadoPerfiles(CivEstadoPerfiles civEstadoPerfiles) {
        this.civEstadoPerfiles = civEstadoPerfiles;
    }
    public String getPerfNombre() {
        return this.perfNombre;
    }
    
    public void setPerfNombre(String perfNombre) {
        this.perfNombre = perfNombre;
    }
    public Date getPerfFechaproceso() {
        return this.perfFechaproceso;
    }
    
    public void setPerfFechaproceso(Date perfFechaproceso) {
        this.perfFechaproceso = perfFechaproceso;
    }
    public Set getCivRecursoses() {
        return this.civRecursoses;
    }
    
    public void setCivRecursoses(Set civRecursoses) {
        this.civRecursoses = civRecursoses;
    }




}


