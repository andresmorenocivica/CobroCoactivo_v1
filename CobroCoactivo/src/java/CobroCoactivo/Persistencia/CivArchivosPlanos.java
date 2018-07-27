package CobroCoactivo.Persistencia;
// Generated 27/07/2018 01:43:20 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;

/**
 * CivArchivosPlanos generated by hbm2java
 */
public class CivArchivosPlanos  implements java.io.Serializable {


     private BigDecimal arcId;
     private CivUsuarios civUsuarios;
     private String arcNombre;
     private Date arcFecha;
     private BigDecimal arcEstadoFk;

    public CivArchivosPlanos() {
    }

    public CivArchivosPlanos(BigDecimal arcId, CivUsuarios civUsuarios, String arcNombre, Date arcFecha, BigDecimal arcEstadoFk) {
       this.arcId = arcId;
       this.civUsuarios = civUsuarios;
       this.arcNombre = arcNombre;
       this.arcFecha = arcFecha;
       this.arcEstadoFk = arcEstadoFk;
    }
   
    public BigDecimal getArcId() {
        return this.arcId;
    }
    
    public void setArcId(BigDecimal arcId) {
        this.arcId = arcId;
    }
    public CivUsuarios getCivUsuarios() {
        return this.civUsuarios;
    }
    
    public void setCivUsuarios(CivUsuarios civUsuarios) {
        this.civUsuarios = civUsuarios;
    }
    public String getArcNombre() {
        return this.arcNombre;
    }
    
    public void setArcNombre(String arcNombre) {
        this.arcNombre = arcNombre;
    }
    public Date getArcFecha() {
        return this.arcFecha;
    }
    
    public void setArcFecha(Date arcFecha) {
        this.arcFecha = arcFecha;
    }
    public BigDecimal getArcEstadoFk() {
        return this.arcEstadoFk;
    }
    
    public void setArcEstadoFk(BigDecimal arcEstadoFk) {
        this.arcEstadoFk = arcEstadoFk;
    }




}


