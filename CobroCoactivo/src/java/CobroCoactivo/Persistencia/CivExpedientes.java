package CobroCoactivo.Persistencia;
// Generated 10/08/2018 01:53:59 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivExpedientes generated by hbm2java
 */
public class CivExpedientes  implements java.io.Serializable {


     private BigDecimal expId;
     private CivTipoExpedientes civTipoExpedientes;
     private CivEstadoExpedientes civEstadoExpedientes;
     private String expReferencia;
     private Date expFechaproceso;
     private String expUbicacion;
     private Set civDetalleExpedienteses = new HashSet(0);

    public CivExpedientes() {
    }

	
    public CivExpedientes(BigDecimal expId, CivTipoExpedientes civTipoExpedientes, CivEstadoExpedientes civEstadoExpedientes, String expReferencia) {
        this.expId = expId;
        this.civTipoExpedientes = civTipoExpedientes;
        this.civEstadoExpedientes = civEstadoExpedientes;
        this.expReferencia = expReferencia;
    }
    public CivExpedientes(BigDecimal expId, CivTipoExpedientes civTipoExpedientes, CivEstadoExpedientes civEstadoExpedientes, String expReferencia, Date expFechaproceso, String expUbicacion, Set civDetalleExpedienteses) {
       this.expId = expId;
       this.civTipoExpedientes = civTipoExpedientes;
       this.civEstadoExpedientes = civEstadoExpedientes;
       this.expReferencia = expReferencia;
       this.expFechaproceso = expFechaproceso;
       this.expUbicacion = expUbicacion;
       this.civDetalleExpedienteses = civDetalleExpedienteses;
    }
   
    public BigDecimal getExpId() {
        return this.expId;
    }
    
    public void setExpId(BigDecimal expId) {
        this.expId = expId;
    }
    public CivTipoExpedientes getCivTipoExpedientes() {
        return this.civTipoExpedientes;
    }
    
    public void setCivTipoExpedientes(CivTipoExpedientes civTipoExpedientes) {
        this.civTipoExpedientes = civTipoExpedientes;
    }
    public CivEstadoExpedientes getCivEstadoExpedientes() {
        return this.civEstadoExpedientes;
    }
    
    public void setCivEstadoExpedientes(CivEstadoExpedientes civEstadoExpedientes) {
        this.civEstadoExpedientes = civEstadoExpedientes;
    }
    public String getExpReferencia() {
        return this.expReferencia;
    }
    
    public void setExpReferencia(String expReferencia) {
        this.expReferencia = expReferencia;
    }
    public Date getExpFechaproceso() {
        return this.expFechaproceso;
    }
    
    public void setExpFechaproceso(Date expFechaproceso) {
        this.expFechaproceso = expFechaproceso;
    }
    public String getExpUbicacion() {
        return this.expUbicacion;
    }
    
    public void setExpUbicacion(String expUbicacion) {
        this.expUbicacion = expUbicacion;
    }
    public Set getCivDetalleExpedienteses() {
        return this.civDetalleExpedienteses;
    }
    
    public void setCivDetalleExpedienteses(Set civDetalleExpedienteses) {
        this.civDetalleExpedienteses = civDetalleExpedienteses;
    }




}


