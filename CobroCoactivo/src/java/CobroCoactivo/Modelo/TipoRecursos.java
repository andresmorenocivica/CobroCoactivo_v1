package CobroCoactivo.Modelo;
// Generated 30/05/2018 02:16:05 PM by Hibernate Tools 4.3.1


import CobroCoactivo.Persistencia.CivTipoRecursos;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TipoRecursos generated by hbm2java
 */
public class TipoRecursos  implements java.io.Serializable {


     private int Id;
     private String Descripcion;
     private String Nombrecorto;
     private Date Fechainicial;
     private Date Fechafinal;
     private int Codigo;
     private Set recursoses = new HashSet(0);

    public TipoRecursos() {
    }

    public TipoRecursos(CivTipoRecursos civTipoRecursos) {
        this.Id = civTipoRecursos.getTiprecId().intValue();
        this.Descripcion = civTipoRecursos.getTiprecDescripcion();
        this.Nombrecorto = civTipoRecursos.getTiprecNombrecorto();
        this.Fechainicial = civTipoRecursos.getTiprecFechainicial();
        this.Fechafinal = civTipoRecursos.getTiprecFechafinal();
        this.Codigo = civTipoRecursos.getTiprecCodigo().intValue();
    }


   
    public int getTiprecId() {
        return this.Id;
    }
    
    public void setTiprecId(int Id) {
        this.Id = Id;
    }
    public String getTiprecDescripcion() {
        return this.Descripcion;
    }
    
    public void setTiprecDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    public String getTiprecNombrecorto() {
        return this.Nombrecorto;
    }
    
    public void setTiprecNombrecorto(String Nombrecorto) {
        this.Nombrecorto = Nombrecorto;
    }
    public Date getTiprecFechainicial() {
        return this.Fechainicial;
    }
    
    public void setTiprecFechainicial(Date Fechainicial) {
        this.Fechainicial = Fechainicial;
    }
    public Date getTiprecFechafinal() {
        return this.Fechafinal;
    }
    
    public void setTiprecFechafinal(Date Fechafinal) {
        this.Fechafinal = Fechafinal;
    }
    public int getTiprecCodigo() {
        return this.Codigo;
    }
    
    public void setTiprecCodigo(int Codigo) {
        this.Codigo = Codigo;
    }
    public Set getRecursoses() {
        return this.recursoses;
    }
    
    public void setRecursoses(Set recursoses) {
        this.recursoses = recursoses;
    }




}


