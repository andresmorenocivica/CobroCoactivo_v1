package CobroCoactivo.Persistencia;
// Generated 30/08/2018 09:56:13 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivPersonas generated by hbm2java
 */
public class CivPersonas  implements java.io.Serializable {


     private BigDecimal perId;
     private CivTipoDocumentos civTipoDocumentos;
     private CivEstadoPersonas civEstadoPersonas;
     private String perDocumento;
     private String perNombre1;
     private String perNombre2;
     private String perApellido1;
     private String perApellido2;
     private String perSexo;
     private Date perFechaproceso;
     private Set civDatosPersonases = new HashSet(0);
     private Set civEntidadeses = new HashSet(0);
     private Set civMovimientoses = new HashSet(0);
     private Set civDeudases = new HashSet(0);
     private Set civUsuarioses = new HashSet(0);
     private Set civAbogadoses = new HashSet(0);

    public CivPersonas() {
    }

	
    public CivPersonas(BigDecimal perId, CivTipoDocumentos civTipoDocumentos, CivEstadoPersonas civEstadoPersonas, String perDocumento, String perNombre1, String perApellido1) {
        this.perId = perId;
        this.civTipoDocumentos = civTipoDocumentos;
        this.civEstadoPersonas = civEstadoPersonas;
        this.perDocumento = perDocumento;
        this.perNombre1 = perNombre1;
        this.perApellido1 = perApellido1;
    }
    public CivPersonas(BigDecimal perId, CivTipoDocumentos civTipoDocumentos, CivEstadoPersonas civEstadoPersonas, String perDocumento, String perNombre1, String perNombre2, String perApellido1, String perApellido2, String perSexo, Date perFechaproceso, Set civDatosPersonases, Set civEntidadeses, Set civMovimientoses, Set civDeudases, Set civUsuarioses, Set civAbogadoses) {
       this.perId = perId;
       this.civTipoDocumentos = civTipoDocumentos;
       this.civEstadoPersonas = civEstadoPersonas;
       this.perDocumento = perDocumento;
       this.perNombre1 = perNombre1;
       this.perNombre2 = perNombre2;
       this.perApellido1 = perApellido1;
       this.perApellido2 = perApellido2;
       this.perSexo = perSexo;
       this.perFechaproceso = perFechaproceso;
       this.civDatosPersonases = civDatosPersonases;
       this.civEntidadeses = civEntidadeses;
       this.civMovimientoses = civMovimientoses;
       this.civDeudases = civDeudases;
       this.civUsuarioses = civUsuarioses;
       this.civAbogadoses = civAbogadoses;
    }
   
    public BigDecimal getPerId() {
        return this.perId;
    }
    
    public void setPerId(BigDecimal perId) {
        this.perId = perId;
    }
    public CivTipoDocumentos getCivTipoDocumentos() {
        return this.civTipoDocumentos;
    }
    
    public void setCivTipoDocumentos(CivTipoDocumentos civTipoDocumentos) {
        this.civTipoDocumentos = civTipoDocumentos;
    }
    public CivEstadoPersonas getCivEstadoPersonas() {
        return this.civEstadoPersonas;
    }
    
    public void setCivEstadoPersonas(CivEstadoPersonas civEstadoPersonas) {
        this.civEstadoPersonas = civEstadoPersonas;
    }
    public String getPerDocumento() {
        return this.perDocumento;
    }
    
    public void setPerDocumento(String perDocumento) {
        this.perDocumento = perDocumento;
    }
    public String getPerNombre1() {
        return this.perNombre1;
    }
    
    public void setPerNombre1(String perNombre1) {
        this.perNombre1 = perNombre1;
    }
    public String getPerNombre2() {
        return this.perNombre2;
    }
    
    public void setPerNombre2(String perNombre2) {
        this.perNombre2 = perNombre2;
    }
    public String getPerApellido1() {
        return this.perApellido1;
    }
    
    public void setPerApellido1(String perApellido1) {
        this.perApellido1 = perApellido1;
    }
    public String getPerApellido2() {
        return this.perApellido2;
    }
    
    public void setPerApellido2(String perApellido2) {
        this.perApellido2 = perApellido2;
    }
    public String getPerSexo() {
        return this.perSexo;
    }
    
    public void setPerSexo(String perSexo) {
        this.perSexo = perSexo;
    }
    public Date getPerFechaproceso() {
        return this.perFechaproceso;
    }
    
    public void setPerFechaproceso(Date perFechaproceso) {
        this.perFechaproceso = perFechaproceso;
    }
    public Set getCivDatosPersonases() {
        return this.civDatosPersonases;
    }
    
    public void setCivDatosPersonases(Set civDatosPersonases) {
        this.civDatosPersonases = civDatosPersonases;
    }
    public Set getCivEntidadeses() {
        return this.civEntidadeses;
    }
    
    public void setCivEntidadeses(Set civEntidadeses) {
        this.civEntidadeses = civEntidadeses;
    }
    public Set getCivMovimientoses() {
        return this.civMovimientoses;
    }
    
    public void setCivMovimientoses(Set civMovimientoses) {
        this.civMovimientoses = civMovimientoses;
    }
    public Set getCivDeudases() {
        return this.civDeudases;
    }
    
    public void setCivDeudases(Set civDeudases) {
        this.civDeudases = civDeudases;
    }
    public Set getCivUsuarioses() {
        return this.civUsuarioses;
    }
    
    public void setCivUsuarioses(Set civUsuarioses) {
        this.civUsuarioses = civUsuarioses;
    }
    public Set getCivAbogadoses() {
        return this.civAbogadoses;
    }
    
    public void setCivAbogadoses(Set civAbogadoses) {
        this.civAbogadoses = civAbogadoses;
    }




}


