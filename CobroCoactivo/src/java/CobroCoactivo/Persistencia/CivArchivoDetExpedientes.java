package CobroCoactivo.Persistencia;
// Generated 30/07/2018 04:15:15 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;

/**
 * CivArchivoDetExpedientes generated by hbm2java
 */
public class CivArchivoDetExpedientes  implements java.io.Serializable {


     private BigDecimal arcdetexpId;
     private CivEstadoArchDetExp civEstadoArchDetExp;
     private CivDetalleExpedientes civDetalleExpedientes;
     private String arcdetexpNombre;
     private Date arcdetexpFechaproceso;
     private String arcdetexpUbicacion;

    public CivArchivoDetExpedientes() {
    }

	
    public CivArchivoDetExpedientes(BigDecimal arcdetexpId, CivEstadoArchDetExp civEstadoArchDetExp, CivDetalleExpedientes civDetalleExpedientes, String arcdetexpNombre) {
        this.arcdetexpId = arcdetexpId;
        this.civEstadoArchDetExp = civEstadoArchDetExp;
        this.civDetalleExpedientes = civDetalleExpedientes;
        this.arcdetexpNombre = arcdetexpNombre;
    }
    public CivArchivoDetExpedientes(BigDecimal arcdetexpId, CivEstadoArchDetExp civEstadoArchDetExp, CivDetalleExpedientes civDetalleExpedientes, String arcdetexpNombre, Date arcdetexpFechaproceso, String arcdetexpUbicacion) {
       this.arcdetexpId = arcdetexpId;
       this.civEstadoArchDetExp = civEstadoArchDetExp;
       this.civDetalleExpedientes = civDetalleExpedientes;
       this.arcdetexpNombre = arcdetexpNombre;
       this.arcdetexpFechaproceso = arcdetexpFechaproceso;
       this.arcdetexpUbicacion = arcdetexpUbicacion;
    }
   
    public BigDecimal getArcdetexpId() {
        return this.arcdetexpId;
    }
    
    public void setArcdetexpId(BigDecimal arcdetexpId) {
        this.arcdetexpId = arcdetexpId;
    }
    public CivEstadoArchDetExp getCivEstadoArchDetExp() {
        return this.civEstadoArchDetExp;
    }
    
    public void setCivEstadoArchDetExp(CivEstadoArchDetExp civEstadoArchDetExp) {
        this.civEstadoArchDetExp = civEstadoArchDetExp;
    }
    public CivDetalleExpedientes getCivDetalleExpedientes() {
        return this.civDetalleExpedientes;
    }
    
    public void setCivDetalleExpedientes(CivDetalleExpedientes civDetalleExpedientes) {
        this.civDetalleExpedientes = civDetalleExpedientes;
    }
    public String getArcdetexpNombre() {
        return this.arcdetexpNombre;
    }
    
    public void setArcdetexpNombre(String arcdetexpNombre) {
        this.arcdetexpNombre = arcdetexpNombre;
    }
    public Date getArcdetexpFechaproceso() {
        return this.arcdetexpFechaproceso;
    }
    
    public void setArcdetexpFechaproceso(Date arcdetexpFechaproceso) {
        this.arcdetexpFechaproceso = arcdetexpFechaproceso;
    }
    public String getArcdetexpUbicacion() {
        return this.arcdetexpUbicacion;
    }
    
    public void setArcdetexpUbicacion(String arcdetexpUbicacion) {
        this.arcdetexpUbicacion = arcdetexpUbicacion;
    }




}


