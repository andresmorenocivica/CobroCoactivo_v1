package CobroCoactivo.Persistencia;
// Generated 10/08/2018 01:53:59 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivModulos generated by hbm2java
 */
public class CivModulos  implements java.io.Serializable {


     private BigDecimal modId;
     private CivEstadoModulos civEstadoModulos;
     private String modNombre;
     private String modIcono;
     private Date modFechaproceso;
     private Set civRecursoses = new HashSet(0);

    public CivModulos() {
    }

	
    public CivModulos(BigDecimal modId, CivEstadoModulos civEstadoModulos, String modNombre) {
        this.modId = modId;
        this.civEstadoModulos = civEstadoModulos;
        this.modNombre = modNombre;
    }
    public CivModulos(BigDecimal modId, CivEstadoModulos civEstadoModulos, String modNombre, String modIcono, Date modFechaproceso, Set civRecursoses) {
       this.modId = modId;
       this.civEstadoModulos = civEstadoModulos;
       this.modNombre = modNombre;
       this.modIcono = modIcono;
       this.modFechaproceso = modFechaproceso;
       this.civRecursoses = civRecursoses;
    }
   
    public BigDecimal getModId() {
        return this.modId;
    }
    
    public void setModId(BigDecimal modId) {
        this.modId = modId;
    }
    public CivEstadoModulos getCivEstadoModulos() {
        return this.civEstadoModulos;
    }
    
    public void setCivEstadoModulos(CivEstadoModulos civEstadoModulos) {
        this.civEstadoModulos = civEstadoModulos;
    }
    public String getModNombre() {
        return this.modNombre;
    }
    
    public void setModNombre(String modNombre) {
        this.modNombre = modNombre;
    }
    public String getModIcono() {
        return this.modIcono;
    }
    
    public void setModIcono(String modIcono) {
        this.modIcono = modIcono;
    }
    public Date getModFechaproceso() {
        return this.modFechaproceso;
    }
    
    public void setModFechaproceso(Date modFechaproceso) {
        this.modFechaproceso = modFechaproceso;
    }
    public Set getCivRecursoses() {
        return this.civRecursoses;
    }
    
    public void setCivRecursoses(Set civRecursoses) {
        this.civRecursoses = civRecursoses;
    }




}


