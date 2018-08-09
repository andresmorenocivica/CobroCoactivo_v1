package CobroCoactivo.Persistencia;
// Generated 9/08/2018 03:19:23 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivEstadoTipoDatosPersonas generated by hbm2java
 */
public class CivEstadoTipoDatosPersonas  implements java.io.Serializable {


     private BigDecimal esttipdatId;
     private String esttipdatDescripcion;
     private Date esttipdatFechainicial;
     private Date esttipdatFechafinal;
     private Date esttipdatFechaproceso;
     private Set civTipoDatosPersonases = new HashSet(0);

    public CivEstadoTipoDatosPersonas() {
    }

	
    public CivEstadoTipoDatosPersonas(BigDecimal esttipdatId, String esttipdatDescripcion, Date esttipdatFechainicial) {
        this.esttipdatId = esttipdatId;
        this.esttipdatDescripcion = esttipdatDescripcion;
        this.esttipdatFechainicial = esttipdatFechainicial;
    }
    public CivEstadoTipoDatosPersonas(BigDecimal esttipdatId, String esttipdatDescripcion, Date esttipdatFechainicial, Date esttipdatFechafinal, Date esttipdatFechaproceso, Set civTipoDatosPersonases) {
       this.esttipdatId = esttipdatId;
       this.esttipdatDescripcion = esttipdatDescripcion;
       this.esttipdatFechainicial = esttipdatFechainicial;
       this.esttipdatFechafinal = esttipdatFechafinal;
       this.esttipdatFechaproceso = esttipdatFechaproceso;
       this.civTipoDatosPersonases = civTipoDatosPersonases;
    }
   
    public BigDecimal getEsttipdatId() {
        return this.esttipdatId;
    }
    
    public void setEsttipdatId(BigDecimal esttipdatId) {
        this.esttipdatId = esttipdatId;
    }
    public String getEsttipdatDescripcion() {
        return this.esttipdatDescripcion;
    }
    
    public void setEsttipdatDescripcion(String esttipdatDescripcion) {
        this.esttipdatDescripcion = esttipdatDescripcion;
    }
    public Date getEsttipdatFechainicial() {
        return this.esttipdatFechainicial;
    }
    
    public void setEsttipdatFechainicial(Date esttipdatFechainicial) {
        this.esttipdatFechainicial = esttipdatFechainicial;
    }
    public Date getEsttipdatFechafinal() {
        return this.esttipdatFechafinal;
    }
    
    public void setEsttipdatFechafinal(Date esttipdatFechafinal) {
        this.esttipdatFechafinal = esttipdatFechafinal;
    }
    public Date getEsttipdatFechaproceso() {
        return this.esttipdatFechaproceso;
    }
    
    public void setEsttipdatFechaproceso(Date esttipdatFechaproceso) {
        this.esttipdatFechaproceso = esttipdatFechaproceso;
    }
    public Set getCivTipoDatosPersonases() {
        return this.civTipoDatosPersonases;
    }
    
    public void setCivTipoDatosPersonases(Set civTipoDatosPersonases) {
        this.civTipoDatosPersonases = civTipoDatosPersonases;
    }




}


