/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Modelo;

import CobroCoactivo.Persistencia.CivDeudas;
import CobroCoactivo.Persistencia.CivEstadoMovimientos;
import CobroCoactivo.Persistencia.CivFasesTrabajos;
import CobroCoactivo.Persistencia.CivMovimientos;
import CobroCoactivo.Persistencia.CivPersonas;
import CobroCoactivo.Persistencia.CivUsuarios;
import java.util.Date;

/**
 *
 * @author emadrid
 */
public class Movimientos {

    private int Id;
    private Date Fechaproceso;
    private Usuarios Usuarios;
    private Personas Personas;
    private FasesTrabajos FasesTrabajos;
    private EstadoMovimientos EstadoMovimientos;
    private Deudas Deudas;

    public Movimientos() {
    }

    public Movimientos(CivMovimientos civMovimientos) {
        this.Id = civMovimientos.getMovId().intValue();
        this.Fechaproceso = civMovimientos.getMovFechaproceso();
    }

    public Movimientos(CivMovimientos civMovimientos, CivEstadoMovimientos civEstadoMovimientos) {
        this.Id = civMovimientos.getMovId().intValue();
        this.Fechaproceso = civMovimientos.getMovFechaproceso();
        this.EstadoMovimientos = new EstadoMovimientos(civEstadoMovimientos);
    }

    public Movimientos(CivMovimientos civMovimientos, CivEstadoMovimientos civEstadoMovimientos, CivDeudas civDeudas) {
        this.Id = civMovimientos.getMovId().intValue();
        this.Fechaproceso = civMovimientos.getMovFechaproceso();
        this.EstadoMovimientos = new EstadoMovimientos(civEstadoMovimientos);
        this.Deudas = new Deudas(civDeudas);

    }

    public Movimientos(CivMovimientos civMovimientos, CivEstadoMovimientos civEstadoMovimientos, CivDeudas civDeudas, CivFasesTrabajos civFasesTrabajos) {
        this.Id = civMovimientos.getMovId().intValue();
        this.Fechaproceso = civMovimientos.getMovFechaproceso();
        this.EstadoMovimientos = new EstadoMovimientos(civEstadoMovimientos);
        this.Deudas = new Deudas(civDeudas);
        this.FasesTrabajos = new FasesTrabajos(civFasesTrabajos);

    }

    public Movimientos(CivMovimientos civMovimientos, CivEstadoMovimientos civEstadoMovimientos, CivDeudas civDeudas, CivFasesTrabajos civFasesTrabajos, CivPersonas civPersonas) {
        this.Id = civMovimientos.getMovId().intValue();
        this.Fechaproceso = civMovimientos.getMovFechaproceso();
        this.EstadoMovimientos = new EstadoMovimientos(civEstadoMovimientos);
        this.Deudas = new Deudas(civDeudas);
        this.FasesTrabajos = new FasesTrabajos(civFasesTrabajos, civFasesTrabajos.getCivEstadoFasesTrabajos());
        this.Personas = new Personas(civPersonas);
    }

    public Movimientos(CivMovimientos civMovimientos, CivEstadoMovimientos civEstadoMovimientos, CivDeudas civDeudas, CivFasesTrabajos civFasesTrabajos, CivPersonas civPersonas, CivUsuarios civUsuarios) {
        this.Id = civMovimientos.getMovId().intValue();
        this.Fechaproceso = civMovimientos.getMovFechaproceso();
        this.EstadoMovimientos = new EstadoMovimientos(civEstadoMovimientos);
        this.Deudas = new Deudas(civDeudas, civDeudas.getCivEstadoDeudas(), civDeudas.getCivPlanTrabajos());
        this.FasesTrabajos = new FasesTrabajos(civFasesTrabajos, civFasesTrabajos.getCivEstadoFasesTrabajos(), civFasesTrabajos.getCivReporteTrabajos(), civFasesTrabajos.getCivEtapasTrabajos());
        this.Personas = new Personas(civPersonas);
        this.Usuarios = new Usuarios(civUsuarios);
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
     * @return the Usuarios
     */
    public Usuarios getUsuarios() {
        return Usuarios;
    }

    /**
     * @param Usuarios the Usuarios to set
     */
    public void setUsuarios(Usuarios Usuarios) {
        this.Usuarios = Usuarios;
    }

    /**
     * @return the Personas
     */
    public Personas getPersonas() {
        return Personas;
    }

    /**
     * @param Personas the Personas to set
     */
    public void setPersonas(Personas Personas) {
        this.Personas = Personas;
    }

    /**
     * @return the FasesTrabajos
     */
    public FasesTrabajos getFasesTrabajos() {
        return FasesTrabajos;
    }

    /**
     * @param FasesTrabajos the FasesTrabajos to set
     */
    public void setFasesTrabajos(FasesTrabajos FasesTrabajos) {
        this.FasesTrabajos = FasesTrabajos;
    }

    /**
     * @return the EstadoMovimientos
     */
    public EstadoMovimientos getEstadoMovimientos() {
        return EstadoMovimientos;
    }

    /**
     * @param EstadoMovimientos the EstadoMovimientos to set
     */
    public void setEstadoMovimientos(EstadoMovimientos EstadoMovimientos) {
        this.EstadoMovimientos = EstadoMovimientos;
    }

    /**
     * @return the Deudas
     */
    public Deudas getDeudas() {
        return Deudas;
    }

    /**
     * @param Deudas the Deudas to set
     */
    public void setDeudas(Deudas Deudas) {
        this.Deudas = Deudas;
    }

}
