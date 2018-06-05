package CobroCoactivo.Modelo;
// Generated 30/05/2018 02:16:05 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Modulos generated by hbm2java
 */
public class Modulos  implements java.io.Serializable {


     private BigDecimal modId;
     private EstadoModulos estadoModulos;
     private String modNombre;
     private String modIcono;
     private Date modFechaproceso;
     private List listRecurso = new ArrayList<>();

    public Modulos() {
    }


   
    public BigDecimal getModId() {
        return this.modId;
    }
    
    public void setModId(BigDecimal modId) {
        this.modId = modId;
    }
    public EstadoModulos getEstadoModulos() {
        return this.estadoModulos;
    }
    
    public void setEstadoModulos(EstadoModulos estadoModulos) {
        this.estadoModulos = estadoModulos;
    }
    public String getModNombre() {
        return this.modNombre;
    }
    
    public void setModNombre(String modNombre) {
        this.modNombre = modNombre;
    }
    public String getModIcono() {
        return this.modIcono;
    }
    
    public void setModIcono(String modIcono) {
        this.modIcono = modIcono;
    }
    public Date getModFechaproceso() {
        return this.modFechaproceso;
    }
    


    /**
     * @return the listRecurso
     */
    public List getListRecurso() {
        return listRecurso;
    }

    /**
     * @param listRecurso the listRecurso to set
     */
    public void setListRecurso(List listRecurso) {
        this.listRecurso = listRecurso;
    }




}

