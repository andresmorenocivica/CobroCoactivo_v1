package CobroCoactivo.Persistencia;
// Generated 13/07/2018 10:45:24 AM by Hibernate Tools 4.3.1


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
     private CivPlanTrabajos civPlanTrabajos;
     private CivPersonas civPersonas;
     private CivEstadoDeudas civEstadoDeudas;
     private CivCobroDeudas civCobroDeudas;
     private Date deuFechadeuda;
     private BigDecimal deuValor;
     private BigDecimal deuSaldo;
     private Date deuFechaproceso;
     private String deuRefencia;
     private String deuMotivo;
     private Set civDetalleIngresoDeudases = new HashSet(0);
     private Set civDetalleDeudases = new HashSet(0);
     private Set civMovimientoses = new HashSet(0);
     private Set civExpedienteses = new HashSet(0);

    public CivDeudas() {
    }

	
    public CivDeudas(BigDecimal deuId, CivTipoDeudas civTipoDeudas, CivPersonas civPersonas, CivEstadoDeudas civEstadoDeudas, CivCobroDeudas civCobroDeudas) {
        this.deuId = deuId;
        this.civTipoDeudas = civTipoDeudas;
        this.civPersonas = civPersonas;
        this.civEstadoDeudas = civEstadoDeudas;
        this.civCobroDeudas = civCobroDeudas;
    }
    public CivDeudas(BigDecimal deuId, CivTipoDeudas civTipoDeudas, CivPlanTrabajos civPlanTrabajos, CivPersonas civPersonas, CivEstadoDeudas civEstadoDeudas, CivCobroDeudas civCobroDeudas, Date deuFechadeuda, BigDecimal deuValor, BigDecimal deuSaldo, Date deuFechaproceso, String deuRefencia, String deuMotivo, Set civDetalleIngresoDeudases, Set civDetalleDeudases, Set civMovimientoses, Set civExpedienteses) {
       this.deuId = deuId;
       this.civTipoDeudas = civTipoDeudas;
       this.civPlanTrabajos = civPlanTrabajos;
       this.civPersonas = civPersonas;
       this.civEstadoDeudas = civEstadoDeudas;
       this.civCobroDeudas = civCobroDeudas;
       this.deuFechadeuda = deuFechadeuda;
       this.deuValor = deuValor;
       this.deuSaldo = deuSaldo;
       this.deuFechaproceso = deuFechaproceso;
       this.deuRefencia = deuRefencia;
       this.deuMotivo = deuMotivo;
       this.civDetalleIngresoDeudases = civDetalleIngresoDeudases;
       this.civDetalleDeudases = civDetalleDeudases;
       this.civMovimientoses = civMovimientoses;
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
    public CivPlanTrabajos getCivPlanTrabajos() {
        return this.civPlanTrabajos;
    }
    
    public void setCivPlanTrabajos(CivPlanTrabajos civPlanTrabajos) {
        this.civPlanTrabajos = civPlanTrabajos;
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
    public CivCobroDeudas getCivCobroDeudas() {
        return this.civCobroDeudas;
    }
    
    public void setCivCobroDeudas(CivCobroDeudas civCobroDeudas) {
        this.civCobroDeudas = civCobroDeudas;
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
    public String getDeuMotivo() {
        return this.deuMotivo;
    }
    
    public void setDeuMotivo(String deuMotivo) {
        this.deuMotivo = deuMotivo;
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
    public Set getCivMovimientoses() {
        return this.civMovimientoses;
    }
    
    public void setCivMovimientoses(Set civMovimientoses) {
        this.civMovimientoses = civMovimientoses;
    }
    public Set getCivExpedienteses() {
        return this.civExpedienteses;
    }
    
    public void setCivExpedienteses(Set civExpedienteses) {
        this.civExpedienteses = civExpedienteses;
    }




}


