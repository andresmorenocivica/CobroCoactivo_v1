package CobroCoactivo.Persistencia;
// Generated 30/08/2018 09:56:13 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivSolicitudes generated by hbm2java
 */
public class CivSolicitudes  implements java.io.Serializable {


     private BigDecimal solId;
     private CivUsuarios civUsuarios;
     private CivEstadoSolicitudes civEstadoSolicitudes;
     private Date solFechaproceso;
     private String solDescripcion;
     private Set civDetalleSolicitudeses = new HashSet(0);

    public CivSolicitudes() {
    }

	
    public CivSolicitudes(BigDecimal solId, CivUsuarios civUsuarios, CivEstadoSolicitudes civEstadoSolicitudes) {
        this.solId = solId;
        this.civUsuarios = civUsuarios;
        this.civEstadoSolicitudes = civEstadoSolicitudes;
    }
    public CivSolicitudes(BigDecimal solId, CivUsuarios civUsuarios, CivEstadoSolicitudes civEstadoSolicitudes, Date solFechaproceso, String solDescripcion, Set civDetalleSolicitudeses) {
       this.solId = solId;
       this.civUsuarios = civUsuarios;
       this.civEstadoSolicitudes = civEstadoSolicitudes;
       this.solFechaproceso = solFechaproceso;
       this.solDescripcion = solDescripcion;
       this.civDetalleSolicitudeses = civDetalleSolicitudeses;
    }
   
    public BigDecimal getSolId() {
        return this.solId;
    }
    
    public void setSolId(BigDecimal solId) {
        this.solId = solId;
    }
    public CivUsuarios getCivUsuarios() {
        return this.civUsuarios;
    }
    
    public void setCivUsuarios(CivUsuarios civUsuarios) {
        this.civUsuarios = civUsuarios;
    }
    public CivEstadoSolicitudes getCivEstadoSolicitudes() {
        return this.civEstadoSolicitudes;
    }
    
    public void setCivEstadoSolicitudes(CivEstadoSolicitudes civEstadoSolicitudes) {
        this.civEstadoSolicitudes = civEstadoSolicitudes;
    }
    public Date getSolFechaproceso() {
        return this.solFechaproceso;
    }
    
    public void setSolFechaproceso(Date solFechaproceso) {
        this.solFechaproceso = solFechaproceso;
    }
    public String getSolDescripcion() {
        return this.solDescripcion;
    }
    
    public void setSolDescripcion(String solDescripcion) {
        this.solDescripcion = solDescripcion;
    }
    public Set getCivDetalleSolicitudeses() {
        return this.civDetalleSolicitudeses;
    }
    
    public void setCivDetalleSolicitudeses(Set civDetalleSolicitudeses) {
        this.civDetalleSolicitudeses = civDetalleSolicitudeses;
    }




}


