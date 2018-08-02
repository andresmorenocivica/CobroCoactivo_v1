package CobroCoactivo.Persistencia;
// Generated 2/08/2018 01:52:51 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;

/**
 * CivEstadoDetalleIngresodeu generated by hbm2java
 */
public class CivEstadoDetalleIngresodeu  implements java.io.Serializable {


     private BigDecimal estdetingdeuId;
     private String estdetingdeuDescripcion;
     private Date estdetingdeuFechainicial;
     private Date estdetingdeuFechafinal;
     private Date estdetingdeuFechaproceso;
     private CivDetalleIngresoDeudas civDetalleIngresoDeudas;

    public CivEstadoDetalleIngresodeu() {
    }

	
    public CivEstadoDetalleIngresodeu(BigDecimal estdetingdeuId, String estdetingdeuDescripcion, Date estdetingdeuFechainicial) {
        this.estdetingdeuId = estdetingdeuId;
        this.estdetingdeuDescripcion = estdetingdeuDescripcion;
        this.estdetingdeuFechainicial = estdetingdeuFechainicial;
    }
    public CivEstadoDetalleIngresodeu(BigDecimal estdetingdeuId, String estdetingdeuDescripcion, Date estdetingdeuFechainicial, Date estdetingdeuFechafinal, Date estdetingdeuFechaproceso, CivDetalleIngresoDeudas civDetalleIngresoDeudas) {
       this.estdetingdeuId = estdetingdeuId;
       this.estdetingdeuDescripcion = estdetingdeuDescripcion;
       this.estdetingdeuFechainicial = estdetingdeuFechainicial;
       this.estdetingdeuFechafinal = estdetingdeuFechafinal;
       this.estdetingdeuFechaproceso = estdetingdeuFechaproceso;
       this.civDetalleIngresoDeudas = civDetalleIngresoDeudas;
    }
   
    public BigDecimal getEstdetingdeuId() {
        return this.estdetingdeuId;
    }
    
    public void setEstdetingdeuId(BigDecimal estdetingdeuId) {
        this.estdetingdeuId = estdetingdeuId;
    }
    public String getEstdetingdeuDescripcion() {
        return this.estdetingdeuDescripcion;
    }
    
    public void setEstdetingdeuDescripcion(String estdetingdeuDescripcion) {
        this.estdetingdeuDescripcion = estdetingdeuDescripcion;
    }
    public Date getEstdetingdeuFechainicial() {
        return this.estdetingdeuFechainicial;
    }
    
    public void setEstdetingdeuFechainicial(Date estdetingdeuFechainicial) {
        this.estdetingdeuFechainicial = estdetingdeuFechainicial;
    }
    public Date getEstdetingdeuFechafinal() {
        return this.estdetingdeuFechafinal;
    }
    
    public void setEstdetingdeuFechafinal(Date estdetingdeuFechafinal) {
        this.estdetingdeuFechafinal = estdetingdeuFechafinal;
    }
    public Date getEstdetingdeuFechaproceso() {
        return this.estdetingdeuFechaproceso;
    }
    
    public void setEstdetingdeuFechaproceso(Date estdetingdeuFechaproceso) {
        this.estdetingdeuFechaproceso = estdetingdeuFechaproceso;
    }
    public CivDetalleIngresoDeudas getCivDetalleIngresoDeudas() {
        return this.civDetalleIngresoDeudas;
    }
    
    public void setCivDetalleIngresoDeudas(CivDetalleIngresoDeudas civDetalleIngresoDeudas) {
        this.civDetalleIngresoDeudas = civDetalleIngresoDeudas;
    }




}


