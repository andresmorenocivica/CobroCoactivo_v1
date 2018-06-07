package CobroCoactivo.Persistencia;
// Generated 7/06/2018 03:45:05 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivDeudas generated by hbm2java
 */
public class CivDeudas  implements java.io.Serializable {


     private BigDecimal deuId;
     private CivTipoDeudas civTipoDeudas;
     private CivPersonas civPersonas;
     private CivEstadoDeudas civEstadoDeudas;
     private Date deuFechadeuda;
     private BigDecimal deuValor;
     private BigDecimal deuSaldo;
     private Date deuFechaproceso;
     private String deuRefencia;
     private Set civDetalleIngresoDeudases = new HashSet(0);
     private Set civDetalleDeudases = new HashSet(0);
     private Set civExpedienteses = new HashSet(0);

    public CivDeudas() {
    }

	
    public CivDeudas(BigDecimal deuId, CivTipoDeudas civTipoDeudas, CivPersonas civPersonas, CivEstadoDeudas civEstadoDeudas) {
        this.deuId = deuId;
        this.civTipoDeudas = civTipoDeudas;
        this.civPersonas = civPersonas;
        this.civEstadoDeudas = civEstadoDeudas;
    }
    public CivDeudas(BigDecimal deuId, CivTipoDeudas civTipoDeudas, CivPersonas civPersonas, CivEstadoDeudas civEstadoDeudas, Date deuFechadeuda, BigDecimal deuValor, BigDecimal deuSaldo, Date deuFechaproceso, String deuRefencia, Set civDetalleIngresoDeudases, Set civDetalleDeudases, Set civExpedienteses) {
       this.deuId = deuId;
       this.civTipoDeudas = civTipoDeudas;
       this.civPersonas = civPersonas;
       this.civEstadoDeudas = civEstadoDeudas;
       this.deuFechadeuda = deuFechadeuda;
       this.deuValor = deuValor;
       this.deuSaldo = deuSaldo;
       this.deuFechaproceso = deuFechaproceso;
       this.deuRefencia = deuRefencia;
       this.civDetalleIngresoDeudases = civDetalleIngresoDeudases;
       this.civDetalleDeudases = civDetalleDeudases;
       this.civExpedienteses = civExpedienteses;
    }
   
    public BigDecimal getDeuId() {
        return this.deuId;
    }
    
    public void setDeuId(BigDecimal deuId) {
        this.deuId = deuId;
    }
    public CivTipoDeudas getCivTipoDeudas() {
        return this.civTipoDeudas;
    }
    
    public void setCivTipoDeudas(CivTipoDeudas civTipoDeudas) {
        this.civTipoDeudas = civTipoDeudas;
    }
    public CivPersonas getCivPersonas() {
        return this.civPersonas;
    }
    
    public void setCivPersonas(CivPersonas civPersonas) {
        this.civPersonas = civPersonas;
    }
    public CivEstadoDeudas getCivEstadoDeudas() {
        return this.civEstadoDeudas;
    }
    
    public void setCivEstadoDeudas(CivEstadoDeudas civEstadoDeudas) {
        this.civEstadoDeudas = civEstadoDeudas;
    }
    public Date getDeuFechadeuda() {
        return this.deuFechadeuda;
    }
    
    public void setDeuFechadeuda(Date deuFechadeuda) {
        this.deuFechadeuda = deuFechadeuda;
    }
    public BigDecimal getDeuValor() {
        return this.deuValor;
    }
    
    public void setDeuValor(BigDecimal deuValor) {
        this.deuValor = deuValor;
    }
    public BigDecimal getDeuSaldo() {
        return this.deuSaldo;
    }
    
    public void setDeuSaldo(BigDecimal deuSaldo) {
        this.deuSaldo = deuSaldo;
    }
    public Date getDeuFechaproceso() {
        return this.deuFechaproceso;
    }
    
    public void setDeuFechaproceso(Date deuFechaproceso) {
        this.deuFechaproceso = deuFechaproceso;
    }
    public String getDeuRefencia() {
        return this.deuRefencia;
    }
    
    public void setDeuRefencia(String deuRefencia) {
        this.deuRefencia = deuRefencia;
    }
    public Set getCivDetalleIngresoDeudases() {
        return this.civDetalleIngresoDeudases;
    }
    
    public void setCivDetalleIngresoDeudases(Set civDetalleIngresoDeudases) {
        this.civDetalleIngresoDeudases = civDetalleIngresoDeudases;
    }
    public Set getCivDetalleDeudases() {
        return this.civDetalleDeudases;
    }
    
    public void setCivDetalleDeudases(Set civDetalleDeudases) {
        this.civDetalleDeudases = civDetalleDeudases;
    }
    public Set getCivExpedienteses() {
        return this.civExpedienteses;
    }
    
    public void setCivExpedienteses(Set civExpedienteses) {
        this.civExpedienteses = civExpedienteses;
    }




}


