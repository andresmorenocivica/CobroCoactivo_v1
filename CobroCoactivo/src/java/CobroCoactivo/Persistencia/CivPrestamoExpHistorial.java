package CobroCoactivo.Persistencia;
// Generated 30/08/2018 09:56:13 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;

/**
 * CivPrestamoExpHistorial generated by hbm2java
 */
public class CivPrestamoExpHistorial  implements java.io.Serializable {


     private BigDecimal preexphisId;
     private CivUsuarios civUsuarios;
     private CivDetalleExpedientes civDetalleExpedientes;
     private Date preexphisFechaproceso;

    public CivPrestamoExpHistorial() {
    }

	
    public CivPrestamoExpHistorial(BigDecimal preexphisId, CivUsuarios civUsuarios) {
        this.preexphisId = preexphisId;
        this.civUsuarios = civUsuarios;
    }
    public CivPrestamoExpHistorial(BigDecimal preexphisId, CivUsuarios civUsuarios, CivDetalleExpedientes civDetalleExpedientes, Date preexphisFechaproceso) {
       this.preexphisId = preexphisId;
       this.civUsuarios = civUsuarios;
       this.civDetalleExpedientes = civDetalleExpedientes;
       this.preexphisFechaproceso = preexphisFechaproceso;
    }
   
    public BigDecimal getPreexphisId() {
        return this.preexphisId;
    }
    
    public void setPreexphisId(BigDecimal preexphisId) {
        this.preexphisId = preexphisId;
    }
    public CivUsuarios getCivUsuarios() {
        return this.civUsuarios;
    }
    
    public void setCivUsuarios(CivUsuarios civUsuarios) {
        this.civUsuarios = civUsuarios;
    }
    public CivDetalleExpedientes getCivDetalleExpedientes() {
        return this.civDetalleExpedientes;
    }
    
    public void setCivDetalleExpedientes(CivDetalleExpedientes civDetalleExpedientes) {
        this.civDetalleExpedientes = civDetalleExpedientes;
    }
    public Date getPreexphisFechaproceso() {
        return this.preexphisFechaproceso;
    }
    
    public void setPreexphisFechaproceso(Date preexphisFechaproceso) {
        this.preexphisFechaproceso = preexphisFechaproceso;
    }




}


