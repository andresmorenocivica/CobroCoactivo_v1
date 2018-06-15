/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Modelo;

import CobroCoactivo.Persistencia.CivEstadoValorTipoDetcobro;
import java.util.Date;

/**
 *
 * @author emadrid
 */
public class EstadoValorTipoDeuda {

    private int Id;
    private String Descripcion;
    private Date Fechainicial;
    private Date Fechafinal;
    private Date Fechaproceso;

    public EstadoValorTipoDeuda() {
    }
    
    public EstadoValorTipoDeuda(CivEstadoValorTipoDetcobro civEstadoValorTipoDetcobro){
        this.Id = civEstadoValorTipoDetcobro.getEstvaltipdetcobId().intValue();
        this.Descripcion = civEstadoValorTipoDetcobro.getEstvaltipdetcobDescripcion();
        this.Fechainicial = civEstadoValorTipoDetcobro.getEstvaltipdetcobFechainicial();
        this.Fechafinal = civEstadoValorTipoDetcobro.getEstvaltipdetcobFechafinal();
        this.Fechaproceso = civEstadoValorTipoDetcobro.getEstvaltipdetcobFechaproceso();
    
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
