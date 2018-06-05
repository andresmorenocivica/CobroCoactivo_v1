package CobroCoactivo.Persistencia;
// Generated 30/05/2018 02:43:14 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;

/**
 * CivUspHistoria generated by hbm2java
 */
public class CivUspHistoria  implements java.io.Serializable {


     private BigDecimal id;
     private CivUsuarios civUsuarios;
     private CivEstadouspHistoria civEstadouspHistoria;
     private String PData;
     private Date fechaProceso;

    public CivUspHistoria() {
    }

	
    public CivUspHistoria(BigDecimal id, CivUsuarios civUsuarios, CivEstadouspHistoria civEstadouspHistoria) {
        this.id = id;
        this.civUsuarios = civUsuarios;
        this.civEstadouspHistoria = civEstadouspHistoria;
    }
    public CivUspHistoria(BigDecimal id, CivUsuarios civUsuarios, CivEstadouspHistoria civEstadouspHistoria, String PData, Date fechaProceso) {
       this.id = id;
       this.civUsuarios = civUsuarios;
       this.civEstadouspHistoria = civEstadouspHistoria;
       this.PData = PData;
       this.fechaProceso = fechaProceso;
    }
   
    public BigDecimal getId() {
        return this.id;
    }
    
    public void setId(BigDecimal id) {
        this.id = id;
    }
    public CivUsuarios getCivUsuarios() {
        return this.civUsuarios;
    }
    
    public void setCivUsuarios(CivUsuarios civUsuarios) {
        this.civUsuarios = civUsuarios;
    }
    public CivEstadouspHistoria getCivEstadouspHistoria() {
        return this.civEstadouspHistoria;
    }
    
    public void setCivEstadouspHistoria(CivEstadouspHistoria civEstadouspHistoria) {
        this.civEstadouspHistoria = civEstadouspHistoria;
    }
    public String getPData() {
        return this.PData;
    }
    
    public void setPData(String PData) {
        this.PData = PData;
    }
    public Date getFechaProceso() {
        return this.fechaProceso;
    }
    
    public void setFechaProceso(Date fechaProceso) {
        this.fechaProceso = fechaProceso;
    }




}


