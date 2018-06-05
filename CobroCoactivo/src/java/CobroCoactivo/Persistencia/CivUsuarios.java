package CobroCoactivo.Persistencia;
// Generated 30/05/2018 02:43:14 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * CivUsuarios generated by hbm2java
 */
public class CivUsuarios  implements java.io.Serializable {


     private BigDecimal usuId;
     private CivPersonas civPersonas;
     private CivEstadoUsuarios civEstadoUsuarios;
     private String usuNombre;
     private String usuPass;
     private Set civConfUsuRecs = new HashSet(0);
     private Set civAttemptses = new HashSet(0);
     private Set civUspHistorias = new HashSet(0);
     private Set logEventoses = new HashSet(0);

    public CivUsuarios() {
    }

	
    public CivUsuarios(BigDecimal usuId, CivPersonas civPersonas, CivEstadoUsuarios civEstadoUsuarios, String usuNombre, String usuPass) {
        this.usuId = usuId;
        this.civPersonas = civPersonas;
        this.civEstadoUsuarios = civEstadoUsuarios;
        this.usuNombre = usuNombre;
        this.usuPass = usuPass;
    }
    public CivUsuarios(BigDecimal usuId, CivPersonas civPersonas, CivEstadoUsuarios civEstadoUsuarios, String usuNombre, String usuPass, Set civConfUsuRecs, Set civAttemptses, Set civUspHistorias, Set logEventoses) {
       this.usuId = usuId;
       this.civPersonas = civPersonas;
       this.civEstadoUsuarios = civEstadoUsuarios;
       this.usuNombre = usuNombre;
       this.usuPass = usuPass;
       this.civConfUsuRecs = civConfUsuRecs;
       this.civAttemptses = civAttemptses;
       this.civUspHistorias = civUspHistorias;
       this.logEventoses = logEventoses;
    }
   
    public BigDecimal getUsuId() {
        return this.usuId;
    }
    
    public void setUsuId(BigDecimal usuId) {
        this.usuId = usuId;
    }
    public CivPersonas getCivPersonas() {
        return this.civPersonas;
    }
    
    public void setCivPersonas(CivPersonas civPersonas) {
        this.civPersonas = civPersonas;
    }
    public CivEstadoUsuarios getCivEstadoUsuarios() {
        return this.civEstadoUsuarios;
    }
    
    public void setCivEstadoUsuarios(CivEstadoUsuarios civEstadoUsuarios) {
        this.civEstadoUsuarios = civEstadoUsuarios;
    }
    public String getUsuNombre() {
        return this.usuNombre;
    }
    
    public void setUsuNombre(String usuNombre) {
        this.usuNombre = usuNombre;
    }
    public String getUsuPass() {
        return this.usuPass;
    }
    
    public void setUsuPass(String usuPass) {
        this.usuPass = usuPass;
    }
    public Set getCivConfUsuRecs() {
        return this.civConfUsuRecs;
    }
    
    public void setCivConfUsuRecs(Set civConfUsuRecs) {
        this.civConfUsuRecs = civConfUsuRecs;
    }
    public Set getCivAttemptses() {
        return this.civAttemptses;
    }
    
    public void setCivAttemptses(Set civAttemptses) {
        this.civAttemptses = civAttemptses;
    }
    public Set getCivUspHistorias() {
        return this.civUspHistorias;
    }
    
    public void setCivUspHistorias(Set civUspHistorias) {
        this.civUspHistorias = civUspHistorias;
    }
    public Set getLogEventoses() {
        return this.logEventoses;
    }
    
    public void setLogEventoses(Set logEventoses) {
        this.logEventoses = logEventoses;
    }




}


