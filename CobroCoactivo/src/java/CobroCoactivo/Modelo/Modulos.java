package CobroCoactivo.Modelo;
// Generated 30/05/2018 02:16:05 PM by Hibernate Tools 4.3.1

import CobroCoactivo.Persistencia.CivEstadoModulos;
import CobroCoactivo.Persistencia.CivModulos;
import CobroCoactivo.Persistencia.CivRecursos;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Modulos generated by hbm2java
 */
public class Modulos implements java.io.Serializable {

    private BigDecimal modId;
    private EstadoModulos estadoModulos;
    private String modNombre;
    private String modIcono;
    private Date modFechaproceso;
    private List<Recursos> listRecurso = new ArrayList<>();
    private boolean selecionado;

    public Modulos() {
    }

    public Modulos(CivModulos civModulos) {
        this.modId = civModulos.getModId();
        this.modNombre = civModulos.getModNombre();
        this.modIcono = civModulos.getModIcono();
        this.modFechaproceso = civModulos.getModFechaproceso();
    }

    public Modulos(CivModulos civModulos, CivEstadoModulos civEstadoModulos) {
        this.modId = civModulos.getModId();
        this.modNombre = civModulos.getModNombre();
        this.modIcono = civModulos.getModIcono();
        this.modFechaproceso = civModulos.getModFechaproceso();
        this.estadoModulos = new EstadoModulos(civEstadoModulos);
    }

    public Modulos(CivModulos civModulos, CivEstadoModulos civEstadoModulos, List<CivRecursos> listCivRecursos) {
        this.modId = civModulos.getModId();
        this.modNombre = civModulos.getModNombre();
        this.modIcono = civModulos.getModIcono();
        this.modFechaproceso = civModulos.getModFechaproceso();
        this.estadoModulos = new EstadoModulos(civEstadoModulos);
        for (CivRecursos civRecurso : listCivRecursos) {
            Recursos recursos = new Recursos(civRecurso);
            this.listRecurso.add(recursos);
        }
        
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
    public List<Recursos> getListRecurso() {
        return listRecurso;
    }

    /**
     * @param listRecurso the listRecurso to set
     */
    public void setListRecurso(List<Recursos> listRecurso) {
        this.listRecurso = listRecurso;
    }

    /**
     * @return the selecionado
     */
    public boolean isSelecionado() {
        return selecionado;
    }

    /**
     * @param selecionado the selecionado to set
     */
    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }


}
