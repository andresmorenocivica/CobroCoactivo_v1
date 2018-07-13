/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Utility;

import CobroCoactivo.Dao.DaoEstructuraPlanos;
import CobroCoactivo.Dao.ITEstructuraPlanos;
import CobroCoactivo.Modelo.CargueDeudasImpuesto;
import CobroCoactivo.Persistencia.CivDeudasImpuesto;
import CobroCoactivo.Persistencia.CivEstructuraPlanos;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author AMORENO
 */
public class UtilityCargues {

    private String msg = "";
    private String valor = "";
    private String campo = "";
    private boolean error;
    private CargueDeudasImpuesto cargueDeudasImpuesto = new CargueDeudasImpuesto();

    public List<UtilityCargues> validarlineaImpuesto(List listaCampos, ITEstructuraPlanos estructuraPlanos) throws SQLException, Exception {
        int numeroCampo = 0;
        String nombreCampo = "";
        String registro = " Registro " + (listaCampos.get(0).toString() != null ? listaCampos.get(0).toString() : "") + " - " + (listaCampos.get(5).toString() != null ? listaCampos.get(5).toString() : " Sin Referencia");
        int cantidadCampos = listaCampos.size();

        List<UtilityCargues> listaValidados = new ArrayList();

        if (listaCampos.size() < 25) {
            setMsg(registro + " linea no cumple con los campos minimos. valor Minimo(24) valor Obtenido (" + listaCampos.size() + ")");
            setError(false);
            return null;
        }

        if (listaCampos.size() > 25) {
            setMsg(registro + " linea no cumple con los campos minimos. valor Maximo(24) valor Obtenido (" + listaCampos.size() + ")");
            setError(false);
            return null;
        }

        for (int i = 0; i < cantidadCampos; i++) {
            numeroCampo = i + 1;
            UtilityCargues utilityCargues = new UtilityCargues();
            setError(true);
            CivEstructuraPlanos civEstructuraPlanos = estructuraPlanos.getEstructuraPlano(1, numeroCampo);
            nombreCampo = civEstructuraPlanos.getEstplaNombreCampo();
            utilityCargues.setCampo(civEstructuraPlanos.getEstplaNombreCampo());
            valor = listaCampos.get(i).toString();
            if (valor.trim().equals("") || valor.isEmpty()) {
                if (civEstructuraPlanos.getEstplaObligatorio().equals("S")) {
                    setMsg(registro + " - error en (" + civEstructuraPlanos.getEstplaNombreCampo() + ") el campo es obligatorio - en el campo: (" + (i + 1) + ")");
                    setError(false);
                    break;
                }
            } else {
                if (valor.length() < civEstructuraPlanos.getEstplaLongitudMin().intValue()) {
                    setMsg(registro + " - longitud no permitida en (" + civEstructuraPlanos.getEstplaNombreCampo() + ") min( :" + civEstructuraPlanos.getEstplaLongitudMin().intValue() + ") real(" + valor.length() + ") valor:" + valor + "- en el campo: (" + (i + 1) + ")");
                    setError(false);
                    break;
                }
                if (valor.length() > civEstructuraPlanos.getEstplaLongitudMax().intValue()) {
                    setMsg(registro + " - longitud no permitida en (" + civEstructuraPlanos.getEstplaNombreCampo() + ")  max( :" + civEstructuraPlanos.getEstplaLongitudMax().intValue() + ") real(" + valor.length() + ") valor:" + valor + "- en el campo: (" + (i + 1) + ")");
                    setError(false);
                    break;
                }
                if (civEstructuraPlanos.getEstplaTipoDato().equals("numerico")) {
                    try {
                        long valorEntero = Long.parseLong(valor);
                    } catch (NumberFormatException e) {
                        setMsg(registro + " - error en (" + civEstructuraPlanos.getEstplaNombreCampo() + ") al convertir valor a entero :(" + valor + ") causa :" + e.getMessage() + "- en el campo: (" + (i + 1) + ")");
                        setError(false);
                        break;
                    }

                }
                if (civEstructuraPlanos.getEstplaTipoDato().equals("fecha")) {
                    try {
                        Date valorFecha = new Date(new java.text.SimpleDateFormat("dd/mm/yyyy").parse(valor).getTime());
                    } catch (ParseException ex) {
                        setMsg(registro + " - error en(" + civEstructuraPlanos.getEstplaNombreCampo() + ")  al convertir valor a fecha:(" + valor + ") requerido 'dd/mm/yyyy' causa :" + ex.getMessage() + "- en el campo: (" + (i + 1) + ")");
                        setError(false);
                        break;
                    }
                }
            }
            utilityCargues.setValor(valor);
            listaValidados.add(utilityCargues);
        }

        return listaValidados;
    }

    /**
     * @return the error
     */
    public boolean isError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(boolean error) {
        this.error = error;
    }

    /**
     * @return the cargueDeudasImpuesto
     */
    public CargueDeudasImpuesto getCargueDeudasImpuesto() {
        return cargueDeudasImpuesto;
    }

    /**
     * @param cargueDeudasImpuesto the cargueDeudasImpuesto to set
     */
    public void setCargueDeudasImpuesto(CargueDeudasImpuesto cargueDeudasImpuesto) {
        this.cargueDeudasImpuesto = cargueDeudasImpuesto;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * @return the campo
     */
    public String getCampo() {
        return campo;
    }

    /**
     * @param campo the campo to set
     */
    public void setCampo(String campo) {
        this.campo = campo;
    }
}
