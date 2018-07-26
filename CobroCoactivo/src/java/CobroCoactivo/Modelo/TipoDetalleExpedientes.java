package CobroCoactivo.Modelo;
// Generated 30/05/2018 02:16:05 PM by Hibernate Tools 4.3.1

import CobroCoactivo.Persistencia.CivTipoDetalleExpedientes;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TipoDetalleExpedientes generated by hbm2java
 */
public class TipoDetalleExpedientes implements java.io.Serializable {

    private int Id;
    private String tidetpexpDescripcion;
    private String Nombrecorto;
    private int Codigo;
    private Date Fechainicial;
    private Date Fechafinal;
    private Set detalleExpedienteses = new HashSet(0);

    public TipoDetalleExpedientes() {
    }

    public TipoDetalleExpedientes(CivTipoDetalleExpedientes civTipoDetalleExpedientes) {
        this.Id = civTipoDetalleExpedientes.getTipdetexpId().intValue();
        this.tidetpexpDescripcion = civTipoDetalleExpedientes.getTidetpexpDescripcion();
        this.Nombrecorto = civTipoDetalleExpedientes.getTipdetexpNombrecorto();
        this.Codigo = civTipoDetalleExpedientes.getTipdetexpCodigo().intValue();
        this.Fechainicial = civTipoDetalleExpedientes.getTipdetexpFechainicial();
        this.Fechafinal = civTipoDetalleExpedientes.getTipdetexpFechafinal();
    }

    public int getTipdetexpId() {
        return this.Id;
    }

    public void setTipdetexpId(int Id) {
        this.Id = Id;
    }

    public String getTidetpexpDescripcion() {
        return this.tidetpexpDescripcion;
    }

    public void setTidetpexpDescripcion(String tidetpexpDescripcion) {
        this.tidetpexpDescripcion = tidetpexpDescripcion;
    }

    public String getTipdetexpNombrecorto() {
        return this.Nombrecorto;
    }

    public void setTipdetexpNombrecorto(String Nombrecorto) {
        this.Nombrecorto = Nombrecorto;
    }

    public int getTipdetexpCodigo() {
        return this.Codigo;
    }

    public void setTipdetexpCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public Date getTipdetexpFechainicial() {
        return this.Fechainicial;
    }

    public void setTipdetexpFechainicial(Date Fechainicial) {
        this.Fechainicial = Fechainicial;
    }

    public Date getTipdetexpFechafinal() {
        return this.Fechafinal;
    }

    public void setTipdetexpFechafinal(Date Fechafinal) {
        this.Fechafinal = Fechafinal;
    }

    public Set getDetalleExpedienteses() {
        return this.detalleExpedienteses;
    }

    public void setDetalleExpedienteses(Set detalleExpedienteses) {
        this.detalleExpedienteses = detalleExpedienteses;
    }

}
