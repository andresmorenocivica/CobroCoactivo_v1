package CobroCoactivo.Persistencia;
// Generated 27/07/2018 01:43:20 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * CivErrores generated by hbm2java
 */
public class CivErrores  implements java.io.Serializable {


     private BigDecimal errId;
     private String errException;
     private String errCausa;
     private String errSolucion;

    public CivErrores() {
    }

	
    public CivErrores(BigDecimal errId) {
        this.errId = errId;
    }
    public CivErrores(BigDecimal errId, String errException, String errCausa, String errSolucion) {
       this.errId = errId;
       this.errException = errException;
       this.errCausa = errCausa;
       this.errSolucion = errSolucion;
    }
   
    public BigDecimal getErrId() {
        return this.errId;
    }
    
    public void setErrId(BigDecimal errId) {
        this.errId = errId;
    }
    public String getErrException() {
        return this.errException;
    }
    
    public void setErrException(String errException) {
        this.errException = errException;
    }
    public String getErrCausa() {
        return this.errCausa;
    }
    
    public void setErrCausa(String errCausa) {
        this.errCausa = errCausa;
    }
    public String getErrSolucion() {
        return this.errSolucion;
    }
    
    public void setErrSolucion(String errSolucion) {
        this.errSolucion = errSolucion;
    }




}


