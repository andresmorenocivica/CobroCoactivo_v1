package CobroCoactivo.Persistencia;
// Generated 30/07/2018 04:15:15 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivIngresoDeudas generated by hbm2java
 */
public class CivIngresoDeudas  implements java.io.Serializable {


     private BigDecimal ingdeuId;
     private CivTipoIngresoDeudas civTipoIngresoDeudas;
     private CivEstadoIngresoDeudas civEstadoIngresoDeudas;
     private String ingdeuReferencia;
     private Date ingdeuFechaproceso;
     private Set civDetalleIngresoDeudases = new HashSet(0);

    public CivIngresoDeudas() {
    }

	
    public CivIngresoDeudas(BigDecimal ingdeuId, CivTipoIngresoDeudas civTipoIngresoDeudas, CivEstadoIngresoDeudas civEstadoIngresoDeudas) {
        this.ingdeuId = ingdeuId;
        this.civTipoIngresoDeudas = civTipoIngresoDeudas;
        this.civEstadoIngresoDeudas = civEstadoIngresoDeudas;
    }
    public CivIngresoDeudas(BigDecimal ingdeuId, CivTipoIngresoDeudas civTipoIngresoDeudas, CivEstadoIngresoDeudas civEstadoIngresoDeudas, String ingdeuReferencia, Date ingdeuFechaproceso, Set civDetalleIngresoDeudases) {
       this.ingdeuId = ingdeuId;
       this.civTipoIngresoDeudas = civTipoIngresoDeudas;
       this.civEstadoIngresoDeudas = civEstadoIngresoDeudas;
       this.ingdeuReferencia = ingdeuReferencia;
       this.ingdeuFechaproceso = ingdeuFechaproceso;
       this.civDetalleIngresoDeudases = civDetalleIngresoDeudases;
    }
   
    public BigDecimal getIngdeuId() {
        return this.ingdeuId;
    }
    
    public void setIngdeuId(BigDecimal ingdeuId) {
        this.ingdeuId = ingdeuId;
    }
    public CivTipoIngresoDeudas getCivTipoIngresoDeudas() {
        return this.civTipoIngresoDeudas;
    }
    
    public void setCivTipoIngresoDeudas(CivTipoIngresoDeudas civTipoIngresoDeudas) {
        this.civTipoIngresoDeudas = civTipoIngresoDeudas;
    }
    public CivEstadoIngresoDeudas getCivEstadoIngresoDeudas() {
        return this.civEstadoIngresoDeudas;
    }
    
    public void setCivEstadoIngresoDeudas(CivEstadoIngresoDeudas civEstadoIngresoDeudas) {
        this.civEstadoIngresoDeudas = civEstadoIngresoDeudas;
    }
    public String getIngdeuReferencia() {
        return this.ingdeuReferencia;
    }
    
    public void setIngdeuReferencia(String ingdeuReferencia) {
        this.ingdeuReferencia = ingdeuReferencia;
    }
    public Date getIngdeuFechaproceso() {
        return this.ingdeuFechaproceso;
    }
    
    public void setIngdeuFechaproceso(Date ingdeuFechaproceso) {
        this.ingdeuFechaproceso = ingdeuFechaproceso;
    }
    public Set getCivDetalleIngresoDeudases() {
        return this.civDetalleIngresoDeudases;
    }
    
    public void setCivDetalleIngresoDeudases(Set civDetalleIngresoDeudases) {
        this.civDetalleIngresoDeudases = civDetalleIngresoDeudases;
    }




}


