package CobroCoactivo.Persistencia;
// Generated 2/08/2018 01:52:51 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivEstadoDetalleSolicitudes generated by hbm2java
 */
public class CivEstadoDetalleSolicitudes  implements java.io.Serializable {


     private BigDecimal estdetsolId;
     private String estdetsolNombre;
     private Date estdetsolFechaproceso;
     private Date estdetsolFechainicial;
     private Date estdetsolFechafinal;
     private Set civDetalleSolicitudeses = new HashSet(0);

    public CivEstadoDetalleSolicitudes() {
    }

	
    public CivEstadoDetalleSolicitudes(BigDecimal estdetsolId) {
        this.estdetsolId = estdetsolId;
    }
    public CivEstadoDetalleSolicitudes(BigDecimal estdetsolId, String estdetsolNombre, Date estdetsolFechaproceso, Date estdetsolFechainicial, Date estdetsolFechafinal, Set civDetalleSolicitudeses) {
       this.estdetsolId = estdetsolId;
       this.estdetsolNombre = estdetsolNombre;
       this.estdetsolFechaproceso = estdetsolFechaproceso;
       this.estdetsolFechainicial = estdetsolFechainicial;
       this.estdetsolFechafinal = estdetsolFechafinal;
       this.civDetalleSolicitudeses = civDetalleSolicitudeses;
    }
   
    public BigDecimal getEstdetsolId() {
        return this.estdetsolId;
    }
    
    public void setEstdetsolId(BigDecimal estdetsolId) {
        this.estdetsolId = estdetsolId;
    }
    public String getEstdetsolNombre() {
        return this.estdetsolNombre;
    }
    
    public void setEstdetsolNombre(String estdetsolNombre) {
        this.estdetsolNombre = estdetsolNombre;
    }
    public Date getEstdetsolFechaproceso() {
        return this.estdetsolFechaproceso;
    }
    
    public void setEstdetsolFechaproceso(Date estdetsolFechaproceso) {
        this.estdetsolFechaproceso = estdetsolFechaproceso;
    }
    public Date getEstdetsolFechainicial() {
        return this.estdetsolFechainicial;
    }
    
    public void setEstdetsolFechainicial(Date estdetsolFechainicial) {
        this.estdetsolFechainicial = estdetsolFechainicial;
    }
    public Date getEstdetsolFechafinal() {
        return this.estdetsolFechafinal;
    }
    
    public void setEstdetsolFechafinal(Date estdetsolFechafinal) {
        this.estdetsolFechafinal = estdetsolFechafinal;
    }
    public Set getCivDetalleSolicitudeses() {
        return this.civDetalleSolicitudeses;
    }
    
    public void setCivDetalleSolicitudeses(Set civDetalleSolicitudeses) {
        this.civDetalleSolicitudeses = civDetalleSolicitudeses;
    }




}


