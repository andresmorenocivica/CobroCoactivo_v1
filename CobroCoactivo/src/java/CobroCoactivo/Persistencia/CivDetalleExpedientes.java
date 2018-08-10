package CobroCoactivo.Persistencia;
// Generated 10/08/2018 01:53:59 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivDetalleExpedientes generated by hbm2java
 */
public class CivDetalleExpedientes  implements java.io.Serializable {


     private BigDecimal detexpId;
     private CivTipoDetalleExpedientes civTipoDetalleExpedientes;
     private CivExpedientes civExpedientes;
     private CivEstadoDetalleExpedientes civEstadoDetalleExpedientes;
     private CivDeudas civDeudas;
     private String detexpDescripcion;
     private Date detexpFechaproceso;
     private String detexpUbicacion;
     private Set civArchivoDetExpedienteses = new HashSet(0);
     private Set civPrestamoExpHistorials = new HashSet(0);

    public CivDetalleExpedientes() {
    }

	
    public CivDetalleExpedientes(BigDecimal detexpId, CivTipoDetalleExpedientes civTipoDetalleExpedientes, CivExpedientes civExpedientes, CivEstadoDetalleExpedientes civEstadoDetalleExpedientes, CivDeudas civDeudas, String detexpDescripcion) {
        this.detexpId = detexpId;
        this.civTipoDetalleExpedientes = civTipoDetalleExpedientes;
        this.civExpedientes = civExpedientes;
        this.civEstadoDetalleExpedientes = civEstadoDetalleExpedientes;
        this.civDeudas = civDeudas;
        this.detexpDescripcion = detexpDescripcion;
    }
    public CivDetalleExpedientes(BigDecimal detexpId, CivTipoDetalleExpedientes civTipoDetalleExpedientes, CivExpedientes civExpedientes, CivEstadoDetalleExpedientes civEstadoDetalleExpedientes, CivDeudas civDeudas, String detexpDescripcion, Date detexpFechaproceso, String detexpUbicacion, Set civArchivoDetExpedienteses, Set civPrestamoExpHistorials) {
       this.detexpId = detexpId;
       this.civTipoDetalleExpedientes = civTipoDetalleExpedientes;
       this.civExpedientes = civExpedientes;
       this.civEstadoDetalleExpedientes = civEstadoDetalleExpedientes;
       this.civDeudas = civDeudas;
       this.detexpDescripcion = detexpDescripcion;
       this.detexpFechaproceso = detexpFechaproceso;
       this.detexpUbicacion = detexpUbicacion;
       this.civArchivoDetExpedienteses = civArchivoDetExpedienteses;
       this.civPrestamoExpHistorials = civPrestamoExpHistorials;
    }
   
    public BigDecimal getDetexpId() {
        return this.detexpId;
    }
    
    public void setDetexpId(BigDecimal detexpId) {
        this.detexpId = detexpId;
    }
    public CivTipoDetalleExpedientes getCivTipoDetalleExpedientes() {
        return this.civTipoDetalleExpedientes;
    }
    
    public void setCivTipoDetalleExpedientes(CivTipoDetalleExpedientes civTipoDetalleExpedientes) {
        this.civTipoDetalleExpedientes = civTipoDetalleExpedientes;
    }
    public CivExpedientes getCivExpedientes() {
        return this.civExpedientes;
    }
    
    public void setCivExpedientes(CivExpedientes civExpedientes) {
        this.civExpedientes = civExpedientes;
    }
    public CivEstadoDetalleExpedientes getCivEstadoDetalleExpedientes() {
        return this.civEstadoDetalleExpedientes;
    }
    
    public void setCivEstadoDetalleExpedientes(CivEstadoDetalleExpedientes civEstadoDetalleExpedientes) {
        this.civEstadoDetalleExpedientes = civEstadoDetalleExpedientes;
    }
    public CivDeudas getCivDeudas() {
        return this.civDeudas;
    }
    
    public void setCivDeudas(CivDeudas civDeudas) {
        this.civDeudas = civDeudas;
    }
    public String getDetexpDescripcion() {
        return this.detexpDescripcion;
    }
    
    public void setDetexpDescripcion(String detexpDescripcion) {
        this.detexpDescripcion = detexpDescripcion;
    }
    public Date getDetexpFechaproceso() {
        return this.detexpFechaproceso;
    }
    
    public void setDetexpFechaproceso(Date detexpFechaproceso) {
        this.detexpFechaproceso = detexpFechaproceso;
    }
    public String getDetexpUbicacion() {
        return this.detexpUbicacion;
    }
    
    public void setDetexpUbicacion(String detexpUbicacion) {
        this.detexpUbicacion = detexpUbicacion;
    }
    public Set getCivArchivoDetExpedienteses() {
        return this.civArchivoDetExpedienteses;
    }
    
    public void setCivArchivoDetExpedienteses(Set civArchivoDetExpedienteses) {
        this.civArchivoDetExpedienteses = civArchivoDetExpedienteses;
    }
    public Set getCivPrestamoExpHistorials() {
        return this.civPrestamoExpHistorials;
    }
    
    public void setCivPrestamoExpHistorials(Set civPrestamoExpHistorials) {
        this.civPrestamoExpHistorials = civPrestamoExpHistorials;
    }




}


