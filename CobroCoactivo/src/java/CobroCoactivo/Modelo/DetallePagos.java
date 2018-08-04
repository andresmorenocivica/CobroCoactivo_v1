/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Modelo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author jvergara
 */
public class DetallePagos implements Serializable{

    private long id;
    private Pagos Pagos;
    private EstadoDetallePago estadoDetallePago;
    private Deudas deudas;
    private double valor;
    private Date fechaproceso;

    public DetallePagos() {
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the Pagos
     */
    public Pagos getPagos() {
        return Pagos;
    }

    /**
     * @param Pagos the Pagos to set
     */
    public void setPagos(Pagos Pagos) {
        this.Pagos = Pagos;
    }

    /**
     * @return the civEstadoDetallePago
     */
    public EstadoDetallePago getCivEstadoDetallePago() {
        return estadoDetallePago;
    }

    /**
     * @param civEstadoDetallePago the civEstadoDetallePago to set
     */
    public void setCivEstadoDetallePago(EstadoDetallePago estadoDetallePago) {
        this.estadoDetallePago = estadoDetallePago;
    }

    /**
     * @return the civDeudas
     */
    public Deudas getDeudas() {
        return deudas;
    }

    /**
     * @param civDeudas the civDeudas to set
     */
    public void setDeudas(Deudas deudas) {
        this.deudas = deudas;
    }

    /**
     * @return the valor
     */
    public double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * @return the fechaproceso
     */
    public Date getFechaproceso() {
        return fechaproceso;
    }

    /**
     * @param fechaproceso the fechaproceso to set
     */
    public void setFechaproceso(Date fechaproceso) {
        this.fechaproceso = fechaproceso;
    }
    
    

}
