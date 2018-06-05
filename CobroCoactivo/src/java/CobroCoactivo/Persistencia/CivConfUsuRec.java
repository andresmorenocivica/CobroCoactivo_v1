package CobroCoactivo.Persistencia;
// Generated 30/05/2018 02:43:14 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;

/**
 * CivConfUsuRec generated by hbm2java
 */
public class CivConfUsuRec  implements java.io.Serializable {


     private BigDecimal confusurecId;
     private CivUsuarios civUsuarios;
     private CivRecursos civRecursos;
     private CivEstadoConfusurec civEstadoConfusurec;
     private Date confusurecFechaproceso;

    public CivConfUsuRec() {
    }

	
    public CivConfUsuRec(BigDecimal confusurecId, CivUsuarios civUsuarios, CivRecursos civRecursos, CivEstadoConfusurec civEstadoConfusurec) {
        this.confusurecId = confusurecId;
        this.civUsuarios = civUsuarios;
        this.civRecursos = civRecursos;
        this.civEstadoConfusurec = civEstadoConfusurec;
    }
    public CivConfUsuRec(BigDecimal confusurecId, CivUsuarios civUsuarios, CivRecursos civRecursos, CivEstadoConfusurec civEstadoConfusurec, Date confusurecFechaproceso) {
       this.confusurecId = confusurecId;
       this.civUsuarios = civUsuarios;
       this.civRecursos = civRecursos;
       this.civEstadoConfusurec = civEstadoConfusurec;
       this.confusurecFechaproceso = confusurecFechaproceso;
    }
   
    public BigDecimal getConfusurecId() {
        return this.confusurecId;
    }
    
    public void setConfusurecId(BigDecimal confusurecId) {
        this.confusurecId = confusurecId;
    }
    public CivUsuarios getCivUsuarios() {
        return this.civUsuarios;
    }
    
    public void setCivUsuarios(CivUsuarios civUsuarios) {
        this.civUsuarios = civUsuarios;
    }
    public CivRecursos getCivRecursos() {
        return this.civRecursos;
    }
    
    public void setCivRecursos(CivRecursos civRecursos) {
        this.civRecursos = civRecursos;
    }
    public CivEstadoConfusurec getCivEstadoConfusurec() {
        return this.civEstadoConfusurec;
    }
    
    public void setCivEstadoConfusurec(CivEstadoConfusurec civEstadoConfusurec) {
        this.civEstadoConfusurec = civEstadoConfusurec;
    }
    public Date getConfusurecFechaproceso() {
        return this.confusurecFechaproceso;
    }
    
    public void setConfusurecFechaproceso(Date confusurecFechaproceso) {
        this.confusurecFechaproceso = confusurecFechaproceso;
    }




}

