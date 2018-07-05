package CobroCoactivo.Modelo;
// Generated 30/05/2018 02:16:05 PM by Hibernate Tools 4.3.1

import CobroCoactivo.Persistencia.CivEstadoEtapasGenerales;
import CobroCoactivo.Persistencia.CivEtapasGenerales;
import CobroCoactivo.Persistencia.CivPlanGenerales;
import java.util.Date;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * EtapasGenerales generated by hbm2java
 */
public class EtapasGenerales implements java.io.Serializable {

    private int Id;
    @Size(min = 2, max = 30, message = "Minimo dos caracteres, maximo 30")
    private String descripcion;
    private Date fechaproceso;
    private String obligatorio;
    @Min(value = 1, message = "Minimo valor 1")
    @Digits(integer = 1, fraction = 0, message = "debe ser un numero")
    private int prioridad;
    private boolean seleccionado;
    private PlanGenerales planGenerales;
    private EstadoEtapasGenerales estadoEtapasGenerales;

    public EtapasGenerales() {
    }

    public EtapasGenerales(CivEtapasGenerales civEtapasGenerales) {
        this.Id = civEtapasGenerales.getEtagenId().intValue();
        this.descripcion = civEtapasGenerales.getEtagenDescripcion();
        this.fechaproceso = civEtapasGenerales.getEtagenFechaproceso();
        this.obligatorio = civEtapasGenerales.getEtagenObligatorio();
        this.prioridad = civEtapasGenerales.getEtagenPrioridad().intValue();
    }
    
     public EtapasGenerales(CivEtapasGenerales civEtapasGenerales,CivEstadoEtapasGenerales civEstadoEtapasGenerales) {
        this.Id = civEtapasGenerales.getEtagenId().intValue();
        this.descripcion = civEtapasGenerales.getEtagenDescripcion();
        this.fechaproceso = civEtapasGenerales.getEtagenFechaproceso();
        this.obligatorio = civEtapasGenerales.getEtagenObligatorio();
        this.prioridad = civEtapasGenerales.getEtagenPrioridad().intValue();
        this.estadoEtapasGenerales = new EstadoEtapasGenerales(civEstadoEtapasGenerales);
    }
     public EtapasGenerales(CivEtapasGenerales civEtapasGenerales,CivEstadoEtapasGenerales civEstadoEtapasGenerales,CivPlanGenerales civPlanGenerales) {
        this.Id = civEtapasGenerales.getEtagenId().intValue();
        this.descripcion = civEtapasGenerales.getEtagenDescripcion();
        this.fechaproceso = civEtapasGenerales.getEtagenFechaproceso();
        this.obligatorio = civEtapasGenerales.getEtagenObligatorio();
        this.prioridad = civEtapasGenerales.getEtagenPrioridad().intValue();
        this.estadoEtapasGenerales = new EstadoEtapasGenerales(civEstadoEtapasGenerales);
        this.planGenerales = new PlanGenerales(civPlanGenerales);
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
        return fechaproceso;
    }

    /**
     * @param Fechaproceso the Fechaproceso to set
     */
    public void setFechaproceso(Date Fechaproceso) {
        this.fechaproceso = Fechaproceso;
    }

    /**
     * @return the Obligatorio
     */
    public String getObligatorio() {
        return obligatorio;
    }

    /**
     * @param Obligatorio the Obligatorio to set
     */
    public void setObligatorio(String Obligatorio) {
        this.obligatorio = Obligatorio;
    }

    /**
     * @return the prioridad
     */
    public int getPrioridad() {
        return prioridad;
    }

    /**
     * @param prioridad the prioridad to set
     */
    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    /**
     * @return the seleccionado
     */
    public boolean isSeleccionado() {
        return seleccionado;
    }

    /**
     * @param seleccionado the seleccionado to set
     */
    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    /**
     * @return the planGenerales
     */
    public PlanGenerales getPlanGenerales() {
        return planGenerales;
    }

    /**
     * @param planGenerales the planGenerales to set
     */
    public void setPlanGenerales(PlanGenerales planGenerales) {
        this.planGenerales = planGenerales;
    }

    /**
     * @return the estadoEtapasGenerales
     */
    public EstadoEtapasGenerales getEstadoEtapasGenerales() {
        return estadoEtapasGenerales;
    }

    /**
     * @param estadoEtapasGenerales the estadoEtapasGenerales to set
     */
    public void setEstadoEtapasGenerales(EstadoEtapasGenerales estadoEtapasGenerales) {
        this.estadoEtapasGenerales = estadoEtapasGenerales;
    }

   
}
