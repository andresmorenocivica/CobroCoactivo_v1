package CobroCoactivo.Persistencia;
// Generated 7/06/2018 03:45:05 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivEstadoDatosPersonas generated by hbm2java
 */
public class CivEstadoDatosPersonas  implements java.io.Serializable {


     private BigDecimal estdatperId;
     private String estdatperDescripcion;
     private Date estdatperFechainicial;
     private Date estdatperFechafinal;
     private Date estdatperFechaproceso;
     private Set civDatosPersonases = new HashSet(0);

    public CivEstadoDatosPersonas() {
    }

	
    public CivEstadoDatosPersonas(BigDecimal estdatperId, String estdatperDescripcion, Date estdatperFechainicial) {
        this.estdatperId = estdatperId;
        this.estdatperDescripcion = estdatperDescripcion;
        this.estdatperFechainicial = estdatperFechainicial;
    }
    public CivEstadoDatosPersonas(BigDecimal estdatperId, String estdatperDescripcion, Date estdatperFechainicial, Date estdatperFechafinal, Date estdatperFechaproceso, Set civDatosPersonases) {
       this.estdatperId = estdatperId;
       this.estdatperDescripcion = estdatperDescripcion;
       this.estdatperFechainicial = estdatperFechainicial;
       this.estdatperFechafinal = estdatperFechafinal;
       this.estdatperFechaproceso = estdatperFechaproceso;
       this.civDatosPersonases = civDatosPersonases;
    }
   
    public BigDecimal getEstdatperId() {
        return this.estdatperId;
    }
    
    public void setEstdatperId(BigDecimal estdatperId) {
        this.estdatperId = estdatperId;
    }
    public String getEstdatperDescripcion() {
        return this.estdatperDescripcion;
    }
    
    public void setEstdatperDescripcion(String estdatperDescripcion) {
        this.estdatperDescripcion = estdatperDescripcion;
    }
    public Date getEstdatperFechainicial() {
        return this.estdatperFechainicial;
    }
    
    public void setEstdatperFechainicial(Date estdatperFechainicial) {
        this.estdatperFechainicial = estdatperFechainicial;
    }
    public Date getEstdatperFechafinal() {
        return this.estdatperFechafinal;
    }
    
    public void setEstdatperFechafinal(Date estdatperFechafinal) {
        this.estdatperFechafinal = estdatperFechafinal;
    }
    public Date getEstdatperFechaproceso() {
        return this.estdatperFechaproceso;
    }
    
    public void setEstdatperFechaproceso(Date estdatperFechaproceso) {
        this.estdatperFechaproceso = estdatperFechaproceso;
    }
    public Set getCivDatosPersonases() {
        return this.civDatosPersonases;
    }
    
    public void setCivDatosPersonases(Set civDatosPersonases) {
        this.civDatosPersonases = civDatosPersonases;
    }




}

