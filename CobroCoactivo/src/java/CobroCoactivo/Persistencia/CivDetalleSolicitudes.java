package CobroCoactivo.Persistencia;
// Generated 2/08/2018 01:52:51 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;

/**
 * CivDetalleSolicitudes generated by hbm2java
 */
public class CivDetalleSolicitudes  implements java.io.Serializable {


     private BigDecimal detsolId;
     private CivSolicitudes civSolicitudes;
     private CivEstadoDetalleSolicitudes civEstadoDetalleSolicitudes;
     private String detsolDescripcion;
     private Date detsolFechaproceso;

    public CivDetalleSolicitudes() {
    }

	
    public CivDetalleSolicitudes(BigDecimal detsolId, CivSolicitudes civSolicitudes, CivEstadoDetalleSolicitudes civEstadoDetalleSolicitudes) {
        this.detsolId = detsolId;
        this.civSolicitudes = civSolicitudes;
        this.civEstadoDetalleSolicitudes = civEstadoDetalleSolicitudes;
    }
    public CivDetalleSolicitudes(BigDecimal detsolId, CivSolicitudes civSolicitudes, CivEstadoDetalleSolicitudes civEstadoDetalleSolicitudes, String detsolDescripcion, Date detsolFechaproceso) {
       this.detsolId = detsolId;
       this.civSolicitudes = civSolicitudes;
       this.civEstadoDetalleSolicitudes = civEstadoDetalleSolicitudes;
       this.detsolDescripcion = detsolDescripcion;
       this.detsolFechaproceso = detsolFechaproceso;
    }
   
    public BigDecimal getDetsolId() {
        return this.detsolId;
    }
    
    public void setDetsolId(BigDecimal detsolId) {
        this.detsolId = detsolId;
    }
    public CivSolicitudes getCivSolicitudes() {
        return this.civSolicitudes;
    }
    
    public void setCivSolicitudes(CivSolicitudes civSolicitudes) {
        this.civSolicitudes = civSolicitudes;
    }
    public CivEstadoDetalleSolicitudes getCivEstadoDetalleSolicitudes() {
        return this.civEstadoDetalleSolicitudes;
    }
    
    public void setCivEstadoDetalleSolicitudes(CivEstadoDetalleSolicitudes civEstadoDetalleSolicitudes) {
        this.civEstadoDetalleSolicitudes = civEstadoDetalleSolicitudes;
    }
    public String getDetsolDescripcion() {
        return this.detsolDescripcion;
    }
    
    public void setDetsolDescripcion(String detsolDescripcion) {
        this.detsolDescripcion = detsolDescripcion;
    }
    public Date getDetsolFechaproceso() {
        return this.detsolFechaproceso;
    }
    
    public void setDetsolFechaproceso(Date detsolFechaproceso) {
        this.detsolFechaproceso = detsolFechaproceso;
    }




}


