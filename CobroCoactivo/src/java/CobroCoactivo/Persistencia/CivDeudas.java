package CobroCoactivo.Persistencia;
// Generated 10/08/2018 01:53:59 PM by Hibernate Tools 4.3.1


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
     private BigDecimal deuRefUnica;
     private Set civDetalleIngresoDeudases = new HashSet(0);
     private Set civDetalleDeudases = new HashSet(0);
     private Set civDetallePagoses = new HashSet(0);
     private Set civMovimientoses = new HashSet(0);
     private Set civDetalleExpedienteses = new HashSet(0);

    public CivDeudas() {
    }

	
    public CivDeudas(BigDecimal deuId, CivTipoDeudas civTipoDeudas, CivPersonas civPersonas, CivEstadoDeudas civEstadoDeudas) {
        this.deuId = deuId;
        this.civTipoDeudas = civTipoDeudas;
        this.civPersonas = civPersonas;
        this.civEstadoDeudas = civEstadoDeudas;
    }
    public CivDeudas(BigDecimal deuId, CivTipoDeudas civTipoDeudas, CivPlanTrabajos civPlanTrabajos, CivPersonas civPersonas, CivEstadoDeudas civEstadoDeudas, CivCobroDeudas civCobroDeudas, Date deuFechadeuda, BigDecimal deuValor, BigDecimal deuSaldo, Date deuFechaproceso, String deuRefencia, String deuMotivo, BigDecimal deuRefUnica, Set civDetalleIngresoDeudases, Set civDetalleDeudases, Set civDetallePagoses, Set civMovimientoses, Set civDetalleExpedienteses) {
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
       this.deuRefUnica = deuRefUnica;
       this.civDetalleIngresoDeudases = civDetalleIngresoDeudases;
       this.civDetalleDeudases = civDetalleDeudases;
       this.civDetallePagoses = civDetallePagoses;
       this.civMovimientoses = civMovimientoses;
       this.civDetalleExpedienteses = civDetalleExpedienteses;
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
    public BigDecimal getDeuRefUnica() {
        return this.deuRefUnica;
    }
    
    public void setDeuRefUnica(BigDecimal deuRefUnica) {
        this.deuRefUnica = deuRefUnica;
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
    public Set getCivDetallePagoses() {
        return this.civDetallePagoses;
    }
    
    public void setCivDetallePagoses(Set civDetallePagoses) {
        this.civDetallePagoses = civDetallePagoses;
    }
    public Set getCivMovimientoses() {
        return this.civMovimientoses;
    }
    
    public void setCivMovimientoses(Set civMovimientoses) {
        this.civMovimientoses = civMovimientoses;
    }
    public Set getCivDetalleExpedienteses() {
        return this.civDetalleExpedienteses;
    }
    
    public void setCivDetalleExpedienteses(Set civDetalleExpedienteses) {
        this.civDetalleExpedienteses = civDetalleExpedienteses;
    }




}


