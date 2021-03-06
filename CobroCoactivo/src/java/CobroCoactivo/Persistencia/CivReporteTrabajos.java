package CobroCoactivo.Persistencia;
// Generated 30/08/2018 09:56:13 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivReporteTrabajos generated by hbm2java
 */
public class CivReporteTrabajos  implements java.io.Serializable {


     private BigDecimal reptraId;
     private CivEstadoReporteTrabajos civEstadoReporteTrabajos;
     private String reptraDescripcion;
     private Date reptraFechaproceso;
     private String reptraArchivo;
     private Set civFasesTrabajoses = new HashSet(0);

    public CivReporteTrabajos() {
    }

	
    public CivReporteTrabajos(BigDecimal reptraId, CivEstadoReporteTrabajos civEstadoReporteTrabajos, String reptraDescripcion) {
        this.reptraId = reptraId;
        this.civEstadoReporteTrabajos = civEstadoReporteTrabajos;
        this.reptraDescripcion = reptraDescripcion;
    }
    public CivReporteTrabajos(BigDecimal reptraId, CivEstadoReporteTrabajos civEstadoReporteTrabajos, String reptraDescripcion, Date reptraFechaproceso, String reptraArchivo, Set civFasesTrabajoses) {
       this.reptraId = reptraId;
       this.civEstadoReporteTrabajos = civEstadoReporteTrabajos;
       this.reptraDescripcion = reptraDescripcion;
       this.reptraFechaproceso = reptraFechaproceso;
       this.reptraArchivo = reptraArchivo;
       this.civFasesTrabajoses = civFasesTrabajoses;
    }
   
    public BigDecimal getReptraId() {
        return this.reptraId;
    }
    
    public void setReptraId(BigDecimal reptraId) {
        this.reptraId = reptraId;
    }
    public CivEstadoReporteTrabajos getCivEstadoReporteTrabajos() {
        return this.civEstadoReporteTrabajos;
    }
    
    public void setCivEstadoReporteTrabajos(CivEstadoReporteTrabajos civEstadoReporteTrabajos) {
        this.civEstadoReporteTrabajos = civEstadoReporteTrabajos;
    }
    public String getReptraDescripcion() {
        return this.reptraDescripcion;
    }
    
    public void setReptraDescripcion(String reptraDescripcion) {
        this.reptraDescripcion = reptraDescripcion;
    }
    public Date getReptraFechaproceso() {
        return this.reptraFechaproceso;
    }
    
    public void setReptraFechaproceso(Date reptraFechaproceso) {
        this.reptraFechaproceso = reptraFechaproceso;
    }
    public String getReptraArchivo() {
        return this.reptraArchivo;
    }
    
    public void setReptraArchivo(String reptraArchivo) {
        this.reptraArchivo = reptraArchivo;
    }
    public Set getCivFasesTrabajoses() {
        return this.civFasesTrabajoses;
    }
    
    public void setCivFasesTrabajoses(Set civFasesTrabajoses) {
        this.civFasesTrabajoses = civFasesTrabajoses;
    }




}


