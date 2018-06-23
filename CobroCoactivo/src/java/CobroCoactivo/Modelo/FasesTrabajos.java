package CobroCoactivo.Modelo;
// Generated 30/05/2018 02:16:05 PM by Hibernate Tools 4.3.1

import CobroCoactivo.Persistencia.CivEstadoFasesTrabajos;
import CobroCoactivo.Persistencia.CivFasesTrabajos;
import java.util.Date;

/**
 * FasesTrabajos generated by hbm2java
 */
public class FasesTrabajos implements java.io.Serializable {

    private int Id;
    private ReporteTrabajos reporteTrabajos;
    private EstadoFasesTrabajos estadoFasesTrabajos;
    private String Descripcion;
    private Date Fechaproceso;
    private int Dianim;
    private int Diamax;

    public FasesTrabajos() {
    }

    public FasesTrabajos(CivFasesTrabajos civFasesTrabajos,CivEstadoFasesTrabajos civEstadoFasesTrabajos) {
        this.Id = civFasesTrabajos.getFastraId().intValue();
        this.Descripcion = civFasesTrabajos.getFastraDescripcion();
        this.Fechaproceso = civFasesTrabajos.getFastraFechaproceso();
        this.Dianim = civFasesTrabajos.getFastraDianim().intValue();
        this.Diamax = civFasesTrabajos.getFastraDiamax().intValue();
        this.estadoFasesTrabajos = new EstadoFasesTrabajos(civEstadoFasesTrabajos);
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
     * @return the reporteTrabajos
     */
    public ReporteTrabajos getReporteTrabajos() {
        return reporteTrabajos;
    }

    /**
     * @param reporteTrabajos the reporteTrabajos to set
     */
    public void setReporteTrabajos(ReporteTrabajos reporteTrabajos) {
        this.reporteTrabajos = reporteTrabajos;
    }

    /**
     * @return the estadoFasesTrabajos
     */
    public EstadoFasesTrabajos getEstadoFasesTrabajos() {
        return estadoFasesTrabajos;
    }

    /**
     * @param estadoFasesTrabajos the estadoFasesTrabajos to set
     */
    public void setEstadoFasesTrabajos(EstadoFasesTrabajos estadoFasesTrabajos) {
        this.estadoFasesTrabajos = estadoFasesTrabajos;
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

    /**
     * @return the Dianim
     */
    public int getDianim() {
        return Dianim;
    }

    /**
     * @param Dianim the Dianim to set
     */
    public void setDianim(int Dianim) {
        this.Dianim = Dianim;
    }

    /**
     * @return the Diamax
     */
    public int getDiamax() {
        return Diamax;
    }

    /**
     * @param Diamax the Diamax to set
     */
    public void setDiamax(int Diamax) {
        this.Diamax = Diamax;
    }

}
