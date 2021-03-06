package CobroCoactivo.Modelo;
// Generated 30/05/2018 02:16:05 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;

/**
 * UspHistoria generated by hbm2java
 */
public class UspHistoria  implements java.io.Serializable {


     private BigDecimal id;
     private Usuarios usuarios;
     private EstadouspHistoria estadouspHistoria;
     private String PData;
     private Date fechaProceso;

    public UspHistoria() {
    }

	
    public UspHistoria(BigDecimal id, Usuarios usuarios, EstadouspHistoria estadouspHistoria) {
        this.id = id;
        this.usuarios = usuarios;
        this.estadouspHistoria = estadouspHistoria;
    }
    public UspHistoria(BigDecimal id, Usuarios usuarios, EstadouspHistoria estadouspHistoria, String PData, Date fechaProceso) {
       this.id = id;
       this.usuarios = usuarios;
       this.estadouspHistoria = estadouspHistoria;
       this.PData = PData;
       this.fechaProceso = fechaProceso;
    }
   
    public BigDecimal getId() {
        return this.id;
    }
    
    public void setId(BigDecimal id) {
        this.id = id;
    }
    public Usuarios getUsuarios() {
        return this.usuarios;
    }
    
    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }
    public EstadouspHistoria getEstadouspHistoria() {
        return this.estadouspHistoria;
    }
    
    public void setEstadouspHistoria(EstadouspHistoria estadouspHistoria) {
        this.estadouspHistoria = estadouspHistoria;
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


