package CobroCoactivo.Persistencia;
// Generated 30/07/2018 08:55:07 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivPagos generated by hbm2java
 */
public class CivPagos  implements java.io.Serializable {


     private BigDecimal pagId;
     private CivUsuarios civUsuarios;
     private CivEstadoPagos civEstadoPagos;
     private String pagNumero;
     private BigDecimal pagValor;
     private Date pagFecha;
     private String pagBanco;
     private String pagOservacion;
     private Set civDetallePagoses = new HashSet(0);

    public CivPagos() {
    }

	
    public CivPagos(BigDecimal pagId, CivUsuarios civUsuarios) {
        this.pagId = pagId;
        this.civUsuarios = civUsuarios;
    }
    public CivPagos(BigDecimal pagId, CivUsuarios civUsuarios, CivEstadoPagos civEstadoPagos, String pagNumero, BigDecimal pagValor, Date pagFecha, String pagBanco, String pagOservacion, Set civDetallePagoses) {
       this.pagId = pagId;
       this.civUsuarios = civUsuarios;
       this.civEstadoPagos = civEstadoPagos;
       this.pagNumero = pagNumero;
       this.pagValor = pagValor;
       this.pagFecha = pagFecha;
       this.pagBanco = pagBanco;
       this.pagOservacion = pagOservacion;
       this.civDetallePagoses = civDetallePagoses;
    }
   
    public BigDecimal getPagId() {
        return this.pagId;
    }
    
    public void setPagId(BigDecimal pagId) {
        this.pagId = pagId;
    }
    public CivUsuarios getCivUsuarios() {
        return this.civUsuarios;
    }
    
    public void setCivUsuarios(CivUsuarios civUsuarios) {
        this.civUsuarios = civUsuarios;
    }
    public CivEstadoPagos getCivEstadoPagos() {
        return this.civEstadoPagos;
    }
    
    public void setCivEstadoPagos(CivEstadoPagos civEstadoPagos) {
        this.civEstadoPagos = civEstadoPagos;
    }
    public String getPagNumero() {
        return this.pagNumero;
    }
    
    public void setPagNumero(String pagNumero) {
        this.pagNumero = pagNumero;
    }
    public BigDecimal getPagValor() {
        return this.pagValor;
    }
    
    public void setPagValor(BigDecimal pagValor) {
        this.pagValor = pagValor;
    }
    public Date getPagFecha() {
        return this.pagFecha;
    }
    
    public void setPagFecha(Date pagFecha) {
        this.pagFecha = pagFecha;
    }
    public String getPagBanco() {
        return this.pagBanco;
    }
    
    public void setPagBanco(String pagBanco) {
        this.pagBanco = pagBanco;
    }
    public String getPagOservacion() {
        return this.pagOservacion;
    }
    
    public void setPagOservacion(String pagOservacion) {
        this.pagOservacion = pagOservacion;
    }
    public Set getCivDetallePagoses() {
        return this.civDetallePagoses;
    }
    
    public void setCivDetallePagoses(Set civDetallePagoses) {
        this.civDetallePagoses = civDetallePagoses;
    }




}


