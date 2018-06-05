package CobroCoactivo.Persistencia;
// Generated 30/05/2018 02:43:14 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivRecursos generated by hbm2java
 */
public class CivRecursos  implements java.io.Serializable {


     private BigDecimal recId;
     private CivTipoRecursos civTipoRecursos;
     private CivPerfiles civPerfiles;
     private CivModulos civModulos;
     private CivEstadoRecursos civEstadoRecursos;
     private String recNombre;
     private String recDescripcion;
     private Date recFechaproceso;
     private String recCarperta;
     private Set civConfUsuRecs = new HashSet(0);

    public CivRecursos() {
    }

	
    public CivRecursos(BigDecimal recId, CivTipoRecursos civTipoRecursos, CivPerfiles civPerfiles, CivModulos civModulos, CivEstadoRecursos civEstadoRecursos, String recNombre) {
        this.recId = recId;
        this.civTipoRecursos = civTipoRecursos;
        this.civPerfiles = civPerfiles;
        this.civModulos = civModulos;
        this.civEstadoRecursos = civEstadoRecursos;
        this.recNombre = recNombre;
    }
    public CivRecursos(BigDecimal recId, CivTipoRecursos civTipoRecursos, CivPerfiles civPerfiles, CivModulos civModulos, CivEstadoRecursos civEstadoRecursos, String recNombre, String recDescripcion, Date recFechaproceso, String recCarperta, Set civConfUsuRecs) {
       this.recId = recId;
       this.civTipoRecursos = civTipoRecursos;
       this.civPerfiles = civPerfiles;
       this.civModulos = civModulos;
       this.civEstadoRecursos = civEstadoRecursos;
       this.recNombre = recNombre;
       this.recDescripcion = recDescripcion;
       this.recFechaproceso = recFechaproceso;
       this.recCarperta = recCarperta;
       this.civConfUsuRecs = civConfUsuRecs;
    }
   
    public BigDecimal getRecId() {
        return this.recId;
    }
    
    public void setRecId(BigDecimal recId) {
        this.recId = recId;
    }
    public CivTipoRecursos getCivTipoRecursos() {
        return this.civTipoRecursos;
    }
    
    public void setCivTipoRecursos(CivTipoRecursos civTipoRecursos) {
        this.civTipoRecursos = civTipoRecursos;
    }
    public CivPerfiles getCivPerfiles() {
        return this.civPerfiles;
    }
    
    public void setCivPerfiles(CivPerfiles civPerfiles) {
        this.civPerfiles = civPerfiles;
    }
    public CivModulos getCivModulos() {
        return this.civModulos;
    }
    
    public void setCivModulos(CivModulos civModulos) {
        this.civModulos = civModulos;
    }
    public CivEstadoRecursos getCivEstadoRecursos() {
        return this.civEstadoRecursos;
    }
    
    public void setCivEstadoRecursos(CivEstadoRecursos civEstadoRecursos) {
        this.civEstadoRecursos = civEstadoRecursos;
    }
    public String getRecNombre() {
        return this.recNombre;
    }
    
    public void setRecNombre(String recNombre) {
        this.recNombre = recNombre;
    }
    public String getRecDescripcion() {
        return this.recDescripcion;
    }
    
    public void setRecDescripcion(String recDescripcion) {
        this.recDescripcion = recDescripcion;
    }
    public Date getRecFechaproceso() {
        return this.recFechaproceso;
    }
    
    public void setRecFechaproceso(Date recFechaproceso) {
        this.recFechaproceso = recFechaproceso;
    }
    public String getRecCarperta() {
        return this.recCarperta;
    }
    
    public void setRecCarperta(String recCarperta) {
        this.recCarperta = recCarperta;
    }
    public Set getCivConfUsuRecs() {
        return this.civConfUsuRecs;
    }
    
    public void setCivConfUsuRecs(Set civConfUsuRecs) {
        this.civConfUsuRecs = civConfUsuRecs;
    }




}


