package CobroCoactivo.Persistencia;
// Generated 25/07/2018 05:27:39 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;

/**
 * CivDetalleIngresoDeudas generated by hbm2java
 */
public class CivDetalleIngresoDeudas  implements java.io.Serializable {


     private BigDecimal detingdeuId;
     private CivIngresoDeudas civIngresoDeudas;
     private CivEstadoDetalleIngresodeu civEstadoDetalleIngresodeu;
     private CivDeudas civDeudas;
     private BigDecimal detingdeuEstdetingdeuFk;
     private Date detingdeuFechaproceso;

    public CivDetalleIngresoDeudas() {
    }

	
    public CivDetalleIngresoDeudas(CivIngresoDeudas civIngresoDeudas, CivEstadoDetalleIngresodeu civEstadoDetalleIngresodeu, CivDeudas civDeudas, BigDecimal detingdeuEstdetingdeuFk) {
        this.civIngresoDeudas = civIngresoDeudas;
        this.civEstadoDetalleIngresodeu = civEstadoDetalleIngresodeu;
        this.civDeudas = civDeudas;
        this.detingdeuEstdetingdeuFk = detingdeuEstdetingdeuFk;
    }
    public CivDetalleIngresoDeudas(CivIngresoDeudas civIngresoDeudas, CivEstadoDetalleIngresodeu civEstadoDetalleIngresodeu, CivDeudas civDeudas, BigDecimal detingdeuEstdetingdeuFk, Date detingdeuFechaproceso) {
       this.civIngresoDeudas = civIngresoDeudas;
       this.civEstadoDetalleIngresodeu = civEstadoDetalleIngresodeu;
       this.civDeudas = civDeudas;
       this.detingdeuEstdetingdeuFk = detingdeuEstdetingdeuFk;
       this.detingdeuFechaproceso = detingdeuFechaproceso;
    }
   
    public BigDecimal getDetingdeuId() {
        return this.detingdeuId;
    }
    
    public void setDetingdeuId(BigDecimal detingdeuId) {
        this.detingdeuId = detingdeuId;
    }
    public CivIngresoDeudas getCivIngresoDeudas() {
        return this.civIngresoDeudas;
    }
    
    public void setCivIngresoDeudas(CivIngresoDeudas civIngresoDeudas) {
        this.civIngresoDeudas = civIngresoDeudas;
    }
    public CivEstadoDetalleIngresodeu getCivEstadoDetalleIngresodeu() {
        return this.civEstadoDetalleIngresodeu;
    }
    
    public void setCivEstadoDetalleIngresodeu(CivEstadoDetalleIngresodeu civEstadoDetalleIngresodeu) {
        this.civEstadoDetalleIngresodeu = civEstadoDetalleIngresodeu;
    }
    public CivDeudas getCivDeudas() {
        return this.civDeudas;
    }
    
    public void setCivDeudas(CivDeudas civDeudas) {
        this.civDeudas = civDeudas;
    }
    public BigDecimal getDetingdeuEstdetingdeuFk() {
        return this.detingdeuEstdetingdeuFk;
    }
    
    public void setDetingdeuEstdetingdeuFk(BigDecimal detingdeuEstdetingdeuFk) {
        this.detingdeuEstdetingdeuFk = detingdeuEstdetingdeuFk;
    }
    public Date getDetingdeuFechaproceso() {
        return this.detingdeuFechaproceso;
    }
    
    public void setDetingdeuFechaproceso(Date detingdeuFechaproceso) {
        this.detingdeuFechaproceso = detingdeuFechaproceso;
    }




}


