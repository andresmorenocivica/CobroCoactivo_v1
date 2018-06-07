package CobroCoactivo.Persistencia;
// Generated 7/06/2018 03:45:05 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivTipoDatosPersonas generated by hbm2java
 */
public class CivTipoDatosPersonas  implements java.io.Serializable {


     private BigDecimal tipdatperId;
     private CivEstadoTipoDatosPersonas civEstadoTipoDatosPersonas;
     private String tipdatperDescripcion;
     private Date tipdatperFechainicial;
     private Date tipdatperFechafinal;
     private String tipdatperNombrecorto;
     private BigDecimal tipdatperCodigo;
     private Set civDatosPersonases = new HashSet(0);

    public CivTipoDatosPersonas() {
    }

	
    public CivTipoDatosPersonas(BigDecimal tipdatperId, CivEstadoTipoDatosPersonas civEstadoTipoDatosPersonas, String tipdatperDescripcion, Date tipdatperFechainicial) {
        this.tipdatperId = tipdatperId;
        this.civEstadoTipoDatosPersonas = civEstadoTipoDatosPersonas;
        this.tipdatperDescripcion = tipdatperDescripcion;
        this.tipdatperFechainicial = tipdatperFechainicial;
    }
    public CivTipoDatosPersonas(BigDecimal tipdatperId, CivEstadoTipoDatosPersonas civEstadoTipoDatosPersonas, String tipdatperDescripcion, Date tipdatperFechainicial, Date tipdatperFechafinal, String tipdatperNombrecorto, BigDecimal tipdatperCodigo, Set civDatosPersonases) {
       this.tipdatperId = tipdatperId;
       this.civEstadoTipoDatosPersonas = civEstadoTipoDatosPersonas;
       this.tipdatperDescripcion = tipdatperDescripcion;
       this.tipdatperFechainicial = tipdatperFechainicial;
       this.tipdatperFechafinal = tipdatperFechafinal;
       this.tipdatperNombrecorto = tipdatperNombrecorto;
       this.tipdatperCodigo = tipdatperCodigo;
       this.civDatosPersonases = civDatosPersonases;
    }
   
    public BigDecimal getTipdatperId() {
        return this.tipdatperId;
    }
    
    public void setTipdatperId(BigDecimal tipdatperId) {
        this.tipdatperId = tipdatperId;
    }
    public CivEstadoTipoDatosPersonas getCivEstadoTipoDatosPersonas() {
        return this.civEstadoTipoDatosPersonas;
    }
    
    public void setCivEstadoTipoDatosPersonas(CivEstadoTipoDatosPersonas civEstadoTipoDatosPersonas) {
        this.civEstadoTipoDatosPersonas = civEstadoTipoDatosPersonas;
    }
    public String getTipdatperDescripcion() {
        return this.tipdatperDescripcion;
    }
    
    public void setTipdatperDescripcion(String tipdatperDescripcion) {
        this.tipdatperDescripcion = tipdatperDescripcion;
    }
    public Date getTipdatperFechainicial() {
        return this.tipdatperFechainicial;
    }
    
    public void setTipdatperFechainicial(Date tipdatperFechainicial) {
        this.tipdatperFechainicial = tipdatperFechainicial;
    }
    public Date getTipdatperFechafinal() {
        return this.tipdatperFechafinal;
    }
    
    public void setTipdatperFechafinal(Date tipdatperFechafinal) {
        this.tipdatperFechafinal = tipdatperFechafinal;
    }
    public String getTipdatperNombrecorto() {
        return this.tipdatperNombrecorto;
    }
    
    public void setTipdatperNombrecorto(String tipdatperNombrecorto) {
        this.tipdatperNombrecorto = tipdatperNombrecorto;
    }
    public BigDecimal getTipdatperCodigo() {
        return this.tipdatperCodigo;
    }
    
    public void setTipdatperCodigo(BigDecimal tipdatperCodigo) {
        this.tipdatperCodigo = tipdatperCodigo;
    }
    public Set getCivDatosPersonases() {
        return this.civDatosPersonases;
    }
    
    public void setCivDatosPersonases(Set civDatosPersonases) {
        this.civDatosPersonases = civDatosPersonases;
    }




}

