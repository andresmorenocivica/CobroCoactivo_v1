package CobroCoactivo.Persistencia;
// Generated 25/07/2018 05:27:39 PM by Hibernate Tools 4.3.1


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
     private BigDecimal arcdetexpDetExpFk;
     private String arcdetexpUbicacion;
     private BigDecimal arcdetexpEstArcdetexpFk;

    public CivArchivoDetExpedientes() {
    }

	
    public CivArchivoDetExpedientes(CivEstadoArchDetExp civEstadoArchDetExp, CivDetalleExpedientes civDetalleExpedientes, String arcdetexpNombre, BigDecimal arcdetexpDetExpFk, BigDecimal arcdetexpEstArcdetexpFk) {
        this.civEstadoArchDetExp = civEstadoArchDetExp;
        this.civDetalleExpedientes = civDetalleExpedientes;
        this.arcdetexpNombre = arcdetexpNombre;
        this.arcdetexpDetExpFk = arcdetexpDetExpFk;
        this.arcdetexpEstArcdetexpFk = arcdetexpEstArcdetexpFk;
    }
    public CivArchivoDetExpedientes(CivEstadoArchDetExp civEstadoArchDetExp, CivDetalleExpedientes civDetalleExpedientes, String arcdetexpNombre, Date arcdetexpFechaproceso, BigDecimal arcdetexpDetExpFk, String arcdetexpUbicacion, BigDecimal arcdetexpEstArcdetexpFk) {
       this.civEstadoArchDetExp = civEstadoArchDetExp;
       this.civDetalleExpedientes = civDetalleExpedientes;
       this.arcdetexpNombre = arcdetexpNombre;
       this.arcdetexpFechaproceso = arcdetexpFechaproceso;
       this.arcdetexpDetExpFk = arcdetexpDetExpFk;
       this.arcdetexpUbicacion = arcdetexpUbicacion;
       this.arcdetexpEstArcdetexpFk = arcdetexpEstArcdetexpFk;
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
    public BigDecimal getArcdetexpDetExpFk() {
        return this.arcdetexpDetExpFk;
    }
    
    public void setArcdetexpDetExpFk(BigDecimal arcdetexpDetExpFk) {
        this.arcdetexpDetExpFk = arcdetexpDetExpFk;
    }
    public String getArcdetexpUbicacion() {
        return this.arcdetexpUbicacion;
    }
    
    public void setArcdetexpUbicacion(String arcdetexpUbicacion) {
        this.arcdetexpUbicacion = arcdetexpUbicacion;
    }
    public BigDecimal getArcdetexpEstArcdetexpFk() {
        return this.arcdetexpEstArcdetexpFk;
    }
    
    public void setArcdetexpEstArcdetexpFk(BigDecimal arcdetexpEstArcdetexpFk) {
        this.arcdetexpEstArcdetexpFk = arcdetexpEstArcdetexpFk;
    }




}


