/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Modelo;

import CobroCoactivo.Persistencia.CivEstadoPagos;
import java.util.Date;

/**
 *
 * @author jvergara
 */
public class Pagos {
    
     private long id;
     private  Usuarios usuarios;
     private EstadoPagos estadoPagos;
     private String numero;
     private double valor;
     private Date fecha;
     private String banco;
     private String oservacion;
     private long idCartera;

    public Pagos() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public EstadoPagos getEstadoPagos() {
        return estadoPagos;
    }

    public void setEstadoPagos(EstadoPagos estadoPagos) {
        this.estadoPagos = estadoPagos;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getOservacion() {
        return oservacion;
    }

    public void setOservacion(String oservacion) {
        this.oservacion = oservacion;
    }

    /**
     * @return the idCartera
     */
    public long getIdCartera() {
        return idCartera;
    }

    /**
     * @param idCartera the idCartera to set
     */
    public void setIdCartera(long idCartera) {
        this.idCartera = idCartera;
    }
     
     
}
