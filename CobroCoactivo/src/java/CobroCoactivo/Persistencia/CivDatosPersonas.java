package CobroCoactivo.Persistencia;
// Generated 30/07/2018 04:15:15 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;

/**
 * CivDatosPersonas generated by hbm2java
 */
public class CivDatosPersonas  implements java.io.Serializable {


     private BigDecimal datperId;
     private CivTipoDatosPersonas civTipoDatosPersonas;
     private CivPersonas civPersonas;
     private CivEstadoDatosPersonas civEstadoDatosPersonas;
     private String datperDescripcion;
     private Date datperFechaproceso;

    public CivDatosPersonas() {
    }

	
    public CivDatosPersonas(BigDecimal datperId, CivTipoDatosPersonas civTipoDatosPersonas, CivPersonas civPersonas, CivEstadoDatosPersonas civEstadoDatosPersonas, String datperDescripcion) {
        this.datperId = datperId;
        this.civTipoDatosPersonas = civTipoDatosPersonas;
        this.civPersonas = civPersonas;
        this.civEstadoDatosPersonas = civEstadoDatosPersonas;
        this.datperDescripcion = datperDescripcion;
    }
    public CivDatosPersonas(BigDecimal datperId, CivTipoDatosPersonas civTipoDatosPersonas, CivPersonas civPersonas, CivEstadoDatosPersonas civEstadoDatosPersonas, String datperDescripcion, Date datperFechaproceso) {
       this.datperId = datperId;
       this.civTipoDatosPersonas = civTipoDatosPersonas;
       this.civPersonas = civPersonas;
       this.civEstadoDatosPersonas = civEstadoDatosPersonas;
       this.datperDescripcion = datperDescripcion;
       this.datperFechaproceso = datperFechaproceso;
    }
   
    public BigDecimal getDatperId() {
        return this.datperId;
    }
    
    public void setDatperId(BigDecimal datperId) {
        this.datperId = datperId;
    }
    public CivTipoDatosPersonas getCivTipoDatosPersonas() {
        return this.civTipoDatosPersonas;
    }
    
    public void setCivTipoDatosPersonas(CivTipoDatosPersonas civTipoDatosPersonas) {
        this.civTipoDatosPersonas = civTipoDatosPersonas;
    }
    public CivPersonas getCivPersonas() {
        return this.civPersonas;
    }
    
    public void setCivPersonas(CivPersonas civPersonas) {
        this.civPersonas = civPersonas;
    }
    public CivEstadoDatosPersonas getCivEstadoDatosPersonas() {
        return this.civEstadoDatosPersonas;
    }
    
    public void setCivEstadoDatosPersonas(CivEstadoDatosPersonas civEstadoDatosPersonas) {
        this.civEstadoDatosPersonas = civEstadoDatosPersonas;
    }
    public String getDatperDescripcion() {
        return this.datperDescripcion;
    }
    
    public void setDatperDescripcion(String datperDescripcion) {
        this.datperDescripcion = datperDescripcion;
    }
    public Date getDatperFechaproceso() {
        return this.datperFechaproceso;
    }
    
    public void setDatperFechaproceso(Date datperFechaproceso) {
        this.datperFechaproceso = datperFechaproceso;
    }




}


