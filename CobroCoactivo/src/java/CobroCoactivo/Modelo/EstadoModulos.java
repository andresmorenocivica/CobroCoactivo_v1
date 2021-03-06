package CobroCoactivo.Modelo;
// Generated 30/05/2018 02:16:05 PM by Hibernate Tools 4.3.1



import CobroCoactivo.Persistencia.CivEstadoModulos;
import java.util.Date;

/**
 * EstadoModulos generated by hbm2java
 */
public class EstadoModulos  implements java.io.Serializable {


     private int Id;
     private String Descripcion;
     private Date Fechainicial;
     private Date Fechafinal;
     private Date Fechaproceso;

    public EstadoModulos() {
    }

	
    
    public EstadoModulos(CivEstadoModulos civEstadoModulos) {
       this.Id = civEstadoModulos.getEstmodId().intValue();
       this.Descripcion = civEstadoModulos.getEstmodDescripcion();
       this.Fechainicial = civEstadoModulos.getEstmodFechainicial();
       this.Fechafinal = civEstadoModulos.getEstmodFechafinal();
       this.Fechaproceso = civEstadoModulos.getEstmodFechaproceso();
    }

    /**
     * @return the Id
     */
    public int getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(int Id) {
        this.Id = Id;
    }

    /**
     * @return the Descripcion
     */
    public String getDescripcion() {
        return Descripcion;
    }

    /**
     * @param Descripcion the Descripcion to set
     */
    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    /**
     * @return the Fechainicial
     */
    public Date getFechainicial() {
        return Fechainicial;
    }

    /**
     * @param Fechainicial the Fechainicial to set
     */
    public void setFechainicial(Date Fechainicial) {
        this.Fechainicial = Fechainicial;
    }

    /**
     * @return the Fechafinal
     */
    public Date getFechafinal() {
        return Fechafinal;
    }

    /**
     * @param Fechafinal the Fechafinal to set
     */
    public void setFechafinal(Date Fechafinal) {
        this.Fechafinal = Fechafinal;
    }

    /**
     * @return the Fechaproceso
     */
    public Date getFechaproceso() {
        return Fechaproceso;
    }

    /**
     * @param Fechaproceso the Fechaproceso to set
     */
    public void setFechaproceso(Date Fechaproceso) {
        this.Fechaproceso = Fechaproceso;
    }
   


}


