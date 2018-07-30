package CobroCoactivo.Persistencia;
// Generated 30/07/2018 08:55:07 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;

/**
 * CivAttempts generated by hbm2java
 */
public class CivAttempts  implements java.io.Serializable {


     private BigDecimal ttpId;
     private CivUsuarios civUsuarios;
     private BigDecimal ttpIntentos;
     private Date tppUltimoIntento;

    public CivAttempts() {
    }

	
    public CivAttempts(BigDecimal ttpId, CivUsuarios civUsuarios) {
        this.ttpId = ttpId;
        this.civUsuarios = civUsuarios;
    }
    public CivAttempts(BigDecimal ttpId, CivUsuarios civUsuarios, BigDecimal ttpIntentos, Date tppUltimoIntento) {
       this.ttpId = ttpId;
       this.civUsuarios = civUsuarios;
       this.ttpIntentos = ttpIntentos;
       this.tppUltimoIntento = tppUltimoIntento;
    }
   
    public BigDecimal getTtpId() {
        return this.ttpId;
    }
    
    public void setTtpId(BigDecimal ttpId) {
        this.ttpId = ttpId;
    }
    public CivUsuarios getCivUsuarios() {
        return this.civUsuarios;
    }
    
    public void setCivUsuarios(CivUsuarios civUsuarios) {
        this.civUsuarios = civUsuarios;
    }
    public BigDecimal getTtpIntentos() {
        return this.ttpIntentos;
    }
    
    public void setTtpIntentos(BigDecimal ttpIntentos) {
        this.ttpIntentos = ttpIntentos;
    }
    public Date getTppUltimoIntento() {
        return this.tppUltimoIntento;
    }
    
    public void setTppUltimoIntento(Date tppUltimoIntento) {
        this.tppUltimoIntento = tppUltimoIntento;
    }




}


