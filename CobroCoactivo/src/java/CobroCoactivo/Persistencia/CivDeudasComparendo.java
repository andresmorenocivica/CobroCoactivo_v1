package CobroCoactivo.Persistencia;
// Generated 10/08/2018 01:53:59 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * CivDeudasComparendo generated by hbm2java
 */
public class CivDeudasComparendo  implements java.io.Serializable {


     private BigDecimal deucomId;
     private String deucomConsecutivo;
     private String deucomFecha;
     private String deucomTipo;
     private String deucomValor;
     private String deucomMotivo;
     private String deucomReferencia;
     private String deucomNombre1;
     private String deucomNombre2;
     private String deucomApellido1;
     private String deucomApellido2;
     private String deucomTipodocumento;
     private String deucomDocumento;
     private String deucomSexo;
     private String deucomDireccion1;
     private String deucomDireccion2;
     private String deucomCelular;
     private String deucomTelefono;
     private String deucomCorreoelectronico;
     private String deucomNumerocomparendo;
     private String deucomInfraccion;
     private String deucomDescripcioninfraccion;
     private String deucomFechaComparendo;
     private String deucomNumerosancion;
     private String deucomFechasancion;
     private String deucomValorsancion;
     private BigDecimal deucomArchivoFk;

    public CivDeudasComparendo() {
    }

	
    public CivDeudasComparendo(BigDecimal deucomId) {
        this.deucomId = deucomId;
    }
    public CivDeudasComparendo(BigDecimal deucomId, String deucomConsecutivo, String deucomFecha, String deucomTipo, String deucomValor, String deucomMotivo, String deucomReferencia, String deucomNombre1, String deucomNombre2, String deucomApellido1, String deucomApellido2, String deucomTipodocumento, String deucomDocumento, String deucomSexo, String deucomDireccion1, String deucomDireccion2, String deucomCelular, String deucomTelefono, String deucomCorreoelectronico, String deucomNumerocomparendo, String deucomInfraccion, String deucomDescripcioninfraccion, String deucomFechaComparendo, String deucomNumerosancion, String deucomFechasancion, String deucomValorsancion, BigDecimal deucomArchivoFk) {
       this.deucomId = deucomId;
       this.deucomConsecutivo = deucomConsecutivo;
       this.deucomFecha = deucomFecha;
       this.deucomTipo = deucomTipo;
       this.deucomValor = deucomValor;
       this.deucomMotivo = deucomMotivo;
       this.deucomReferencia = deucomReferencia;
       this.deucomNombre1 = deucomNombre1;
       this.deucomNombre2 = deucomNombre2;
       this.deucomApellido1 = deucomApellido1;
       this.deucomApellido2 = deucomApellido2;
       this.deucomTipodocumento = deucomTipodocumento;
       this.deucomDocumento = deucomDocumento;
       this.deucomSexo = deucomSexo;
       this.deucomDireccion1 = deucomDireccion1;
       this.deucomDireccion2 = deucomDireccion2;
       this.deucomCelular = deucomCelular;
       this.deucomTelefono = deucomTelefono;
       this.deucomCorreoelectronico = deucomCorreoelectronico;
       this.deucomNumerocomparendo = deucomNumerocomparendo;
       this.deucomInfraccion = deucomInfraccion;
       this.deucomDescripcioninfraccion = deucomDescripcioninfraccion;
       this.deucomFechaComparendo = deucomFechaComparendo;
       this.deucomNumerosancion = deucomNumerosancion;
       this.deucomFechasancion = deucomFechasancion;
       this.deucomValorsancion = deucomValorsancion;
       this.deucomArchivoFk = deucomArchivoFk;
    }
   
    public BigDecimal getDeucomId() {
        return this.deucomId;
    }
    
    public void setDeucomId(BigDecimal deucomId) {
        this.deucomId = deucomId;
    }
    public String getDeucomConsecutivo() {
        return this.deucomConsecutivo;
    }
    
    public void setDeucomConsecutivo(String deucomConsecutivo) {
        this.deucomConsecutivo = deucomConsecutivo;
    }
    public String getDeucomFecha() {
        return this.deucomFecha;
    }
    
    public void setDeucomFecha(String deucomFecha) {
        this.deucomFecha = deucomFecha;
    }
    public String getDeucomTipo() {
        return this.deucomTipo;
    }
    
    public void setDeucomTipo(String deucomTipo) {
        this.deucomTipo = deucomTipo;
    }
    public String getDeucomValor() {
        return this.deucomValor;
    }
    
    public void setDeucomValor(String deucomValor) {
        this.deucomValor = deucomValor;
    }
    public String getDeucomMotivo() {
        return this.deucomMotivo;
    }
    
    public void setDeucomMotivo(String deucomMotivo) {
        this.deucomMotivo = deucomMotivo;
    }
    public String getDeucomReferencia() {
        return this.deucomReferencia;
    }
    
    public void setDeucomReferencia(String deucomReferencia) {
        this.deucomReferencia = deucomReferencia;
    }
    public String getDeucomNombre1() {
        return this.deucomNombre1;
    }
    
    public void setDeucomNombre1(String deucomNombre1) {
        this.deucomNombre1 = deucomNombre1;
    }
    public String getDeucomNombre2() {
        return this.deucomNombre2;
    }
    
    public void setDeucomNombre2(String deucomNombre2) {
        this.deucomNombre2 = deucomNombre2;
    }
    public String getDeucomApellido1() {
        return this.deucomApellido1;
    }
    
    public void setDeucomApellido1(String deucomApellido1) {
        this.deucomApellido1 = deucomApellido1;
    }
    public String getDeucomApellido2() {
        return this.deucomApellido2;
    }
    
    public void setDeucomApellido2(String deucomApellido2) {
        this.deucomApellido2 = deucomApellido2;
    }
    public String getDeucomTipodocumento() {
        return this.deucomTipodocumento;
    }
    
    public void setDeucomTipodocumento(String deucomTipodocumento) {
        this.deucomTipodocumento = deucomTipodocumento;
    }
    public String getDeucomDocumento() {
        return this.deucomDocumento;
    }
    
    public void setDeucomDocumento(String deucomDocumento) {
        this.deucomDocumento = deucomDocumento;
    }
    public String getDeucomSexo() {
        return this.deucomSexo;
    }
    
    public void setDeucomSexo(String deucomSexo) {
        this.deucomSexo = deucomSexo;
    }
    public String getDeucomDireccion1() {
        return this.deucomDireccion1;
    }
    
    public void setDeucomDireccion1(String deucomDireccion1) {
        this.deucomDireccion1 = deucomDireccion1;
    }
    public String getDeucomDireccion2() {
        return this.deucomDireccion2;
    }
    
    public void setDeucomDireccion2(String deucomDireccion2) {
        this.deucomDireccion2 = deucomDireccion2;
    }
    public String getDeucomCelular() {
        return this.deucomCelular;
    }
    
    public void setDeucomCelular(String deucomCelular) {
        this.deucomCelular = deucomCelular;
    }
    public String getDeucomTelefono() {
        return this.deucomTelefono;
    }
    
    public void setDeucomTelefono(String deucomTelefono) {
        this.deucomTelefono = deucomTelefono;
    }
    public String getDeucomCorreoelectronico() {
        return this.deucomCorreoelectronico;
    }
    
    public void setDeucomCorreoelectronico(String deucomCorreoelectronico) {
        this.deucomCorreoelectronico = deucomCorreoelectronico;
    }
    public String getDeucomNumerocomparendo() {
        return this.deucomNumerocomparendo;
    }
    
    public void setDeucomNumerocomparendo(String deucomNumerocomparendo) {
        this.deucomNumerocomparendo = deucomNumerocomparendo;
    }
    public String getDeucomInfraccion() {
        return this.deucomInfraccion;
    }
    
    public void setDeucomInfraccion(String deucomInfraccion) {
        this.deucomInfraccion = deucomInfraccion;
    }
    public String getDeucomDescripcioninfraccion() {
        return this.deucomDescripcioninfraccion;
    }
    
    public void setDeucomDescripcioninfraccion(String deucomDescripcioninfraccion) {
        this.deucomDescripcioninfraccion = deucomDescripcioninfraccion;
    }
    public String getDeucomFechaComparendo() {
        return this.deucomFechaComparendo;
    }
    
    public void setDeucomFechaComparendo(String deucomFechaComparendo) {
        this.deucomFechaComparendo = deucomFechaComparendo;
    }
    public String getDeucomNumerosancion() {
        return this.deucomNumerosancion;
    }
    
    public void setDeucomNumerosancion(String deucomNumerosancion) {
        this.deucomNumerosancion = deucomNumerosancion;
    }
    public String getDeucomFechasancion() {
        return this.deucomFechasancion;
    }
    
    public void setDeucomFechasancion(String deucomFechasancion) {
        this.deucomFechasancion = deucomFechasancion;
    }
    public String getDeucomValorsancion() {
        return this.deucomValorsancion;
    }
    
    public void setDeucomValorsancion(String deucomValorsancion) {
        this.deucomValorsancion = deucomValorsancion;
    }
    public BigDecimal getDeucomArchivoFk() {
        return this.deucomArchivoFk;
    }
    
    public void setDeucomArchivoFk(BigDecimal deucomArchivoFk) {
        this.deucomArchivoFk = deucomArchivoFk;
    }




}


