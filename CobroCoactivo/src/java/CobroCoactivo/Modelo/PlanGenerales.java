package CobroCoactivo.Modelo;
// Generated 30/05/2018 02:16:05 PM by Hibernate Tools 4.3.1

import CobroCoactivo.Persistencia.CivEstadoPlanGenerales;
import CobroCoactivo.Persistencia.CivPlanGenerales;
import java.util.Date;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;


/**
 * PlanGenerales generated by hbm2java
 */
public class PlanGenerales {

    private int Id;
    private EstadoPlanGenerales estadoPlanGenerales = new EstadoPlanGenerales();

   
    @Size(min = 2, max = 30, message = "Minimo dos caracteres maximo 30 caracteres")
    private String descripcion;

    private Date Fechaproceso;

    public PlanGenerales() {
    }

    public PlanGenerales(CivPlanGenerales civPlanGenerales) {
        this.Id = civPlanGenerales.getPlagenId().intValue();
        this.descripcion = civPlanGenerales.getPlagenDescripcion();
        this.Fechaproceso = civPlanGenerales.getPlagenFechaproceso();
    }

    public PlanGenerales(CivPlanGenerales civPlanGenerales, CivEstadoPlanGenerales civEstadoPlanGenerales) {
        this.Id = civPlanGenerales.getPlagenId().intValue();
        this.descripcion = civPlanGenerales.getPlagenDescripcion();
        this.Fechaproceso = civPlanGenerales.getPlagenFechaproceso();
        this.estadoPlanGenerales = new EstadoPlanGenerales(civEstadoPlanGenerales);
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
     * @return the estadoPlanGenerales
     */
    public EstadoPlanGenerales getEstadoPlanGenerales() {
        return estadoPlanGenerales;
    }

    /**
     * @param estadoPlanGenerales the estadoPlanGenerales to set
     */
    public void setEstadoPlanGenerales(EstadoPlanGenerales estadoPlanGenerales) {
        this.estadoPlanGenerales = estadoPlanGenerales;
    }

    /**
     * @return the Descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param Descripcion the Descripcion to set
     */
    public void setDescripcion(String Descripcion) {
        this.descripcion = Descripcion;
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
