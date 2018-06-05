package CobroCoactivo.Persistencia;
// Generated 30/05/2018 02:43:14 PM by Hibernate Tools 4.3.1


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
     private CivDeudas civDeudas;
     private String expRefencia;
     private Date expFechaproceso;
     private Set civDetalleExpedienteses = new HashSet(0);

    public CivExpedientes() {
    }

	
    public CivExpedientes(BigDecimal expId, CivTipoExpedientes civTipoExpedientes, CivEstadoExpedientes civEstadoExpedientes, CivDeudas civDeudas, String expRefencia) {
        this.expId = expId;
        this.civTipoExpedientes = civTipoExpedientes;
        this.civEstadoExpedientes = civEstadoExpedientes;
        this.civDeudas = civDeudas;
        this.expRefencia = expRefencia;
    }
    public CivExpedientes(BigDecimal expId, CivTipoExpedientes civTipoExpedientes, CivEstadoExpedientes civEstadoExpedientes, CivDeudas civDeudas, String expRefencia, Date expFechaproceso, Set civDetalleExpedienteses) {
       this.expId = expId;
       this.civTipoExpedientes = civTipoExpedientes;
       this.civEstadoExpedientes = civEstadoExpedientes;
       this.civDeudas = civDeudas;
       this.expRefencia = expRefencia;
       this.expFechaproceso = expFechaproceso;
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
    public CivDeudas getCivDeudas() {
        return this.civDeudas;
    }
    
    public void setCivDeudas(CivDeudas civDeudas) {
        this.civDeudas = civDeudas;
    }
    public String getExpRefencia() {
        return this.expRefencia;
    }
    
    public void setExpRefencia(String expRefencia) {
        this.expRefencia = expRefencia;
    }
    public Date getExpFechaproceso() {
        return this.expFechaproceso;
    }
    
    public void setExpFechaproceso(Date expFechaproceso) {
        this.expFechaproceso = expFechaproceso;
    }
    public Set getCivDetalleExpedienteses() {
        return this.civDetalleExpedienteses;
    }
    
    public void setCivDetalleExpedienteses(Set civDetalleExpedienteses) {
        this.civDetalleExpedienteses = civDetalleExpedienteses;
    }




}

