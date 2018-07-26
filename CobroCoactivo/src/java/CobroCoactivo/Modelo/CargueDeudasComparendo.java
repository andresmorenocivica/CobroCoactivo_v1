/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Modelo;

import CobroCoactivo.Persistencia.CivDeudasComparendo;
import CobroCoactivo.Utility.UtilityCargues;
import java.util.List;

/**
 *
 * @author AMORENO
 */
public class CargueDeudasComparendo {

    private int id;
    private String consecutivo;
    private String fecha;
    private String tipo;
    private String valor;
    private String motivo;
    private String referencia;
    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    private String tipoDocumento;
    private String documento;
    private String sexo;
    private String direccion1;
    private String direccion2;
    private String celular;
    private String telefono;
    private String correo;

    private String numeroComparendo;
    private String infraccion;
    private String descripcionInfraccion;
    private String fechaComparendo;
    private String numeroSancion;
    private String fechaSancion;
    private String valorSancion;
    private int archivoPlano;
    private String mensajeCargue;

    public CargueDeudasComparendo(CivDeudasComparendo civDeudasComparendo) {
        this.id = civDeudasComparendo.getDeucomId().intValue();
        this.consecutivo = civDeudasComparendo.getDeucomConsecutivo();
        this.fecha = civDeudasComparendo.getDeucomFecha();
        this.tipo = civDeudasComparendo.getDeucomTipo();
        this.valor = civDeudasComparendo.getDeucomValor();
        this.motivo = civDeudasComparendo.getDeucomMotivo();
        this.referencia = civDeudasComparendo.getDeucomReferencia();
        this.nombre1 = civDeudasComparendo.getDeucomNombre1();
        this.nombre2 = civDeudasComparendo.getDeucomNombre2();
        this.apellido1 = civDeudasComparendo.getDeucomApellido1();
        this.apellido2 = civDeudasComparendo.getDeucomApellido2();
        this.tipoDocumento = civDeudasComparendo.getDeucomTipodocumento();
        this.documento = civDeudasComparendo.getDeucomDocumento();
        this.sexo = civDeudasComparendo.getDeucomSexo();
        this.direccion1 = civDeudasComparendo.getDeucomDireccion1();
        this.direccion2 = civDeudasComparendo.getDeucomDireccion2();
        this.celular = civDeudasComparendo.getDeucomCelular();
        this.telefono = civDeudasComparendo.getDeucomTelefono();
        this.correo = civDeudasComparendo.getDeucomCorreoelectronico();
        this.numeroComparendo = civDeudasComparendo.getDeucomNumerocomparendo();
        this.infraccion = civDeudasComparendo.getDeucomInfraccion();
        this.descripcionInfraccion = civDeudasComparendo.getDeucomDescripcioninfraccion();
        this.fechaComparendo = civDeudasComparendo.getDeucomFechaComparendo();
        this.numeroSancion = civDeudasComparendo.getDeucomNumerosancion();
        this.fechaSancion = civDeudasComparendo.getDeucomFechasancion();
        this.valorSancion = civDeudasComparendo.getDeucomValorsancion();
        this.archivoPlano = civDeudasComparendo.getDeucomArchivoFk().intValue();
    }

    public CargueDeudasComparendo(List<UtilityCargues> listCamposDeudasImpuestos) {
        this.consecutivo = listCamposDeudasImpuestos.get(0).getValor();
        this.fecha = listCamposDeudasImpuestos.get(1).getValor();
        this.tipo = listCamposDeudasImpuestos.get(2).getValor();
        this.valor = listCamposDeudasImpuestos.get(3).getValor();
        this.motivo = listCamposDeudasImpuestos.get(4).getValor();
        this.referencia = listCamposDeudasImpuestos.get(5).getValor();
        this.nombre1 = listCamposDeudasImpuestos.get(6).getValor();
        this.nombre2 = listCamposDeudasImpuestos.get(7).getValor();
        this.apellido1 = listCamposDeudasImpuestos.get(8).getValor();
        this.apellido2 = listCamposDeudasImpuestos.get(9).getValor();
        this.tipoDocumento = listCamposDeudasImpuestos.get(10).getValor();
        this.documento = listCamposDeudasImpuestos.get(11).getValor();
        this.sexo = listCamposDeudasImpuestos.get(12).getValor();
        this.direccion1 = listCamposDeudasImpuestos.get(13).getValor();
        this.direccion2 = listCamposDeudasImpuestos.get(14).getValor();
        this.celular = listCamposDeudasImpuestos.get(15).getValor();
        this.telefono = listCamposDeudasImpuestos.get(16).getValor();
        this.correo = listCamposDeudasImpuestos.get(17).getValor();
        this.numeroComparendo = listCamposDeudasImpuestos.get(18).getValor();
        this.infraccion = listCamposDeudasImpuestos.get(19).getValor();
        this.descripcionInfraccion = listCamposDeudasImpuestos.get(20).getValor();
        this.fechaComparendo = listCamposDeudasImpuestos.get(21).getValor();
        this.numeroSancion = listCamposDeudasImpuestos.get(22).getValor();
        this.fechaSancion = listCamposDeudasImpuestos.get(23).getValor();
        this.valorSancion = listCamposDeudasImpuestos.get(24).getValor();
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the consecutivo
     */
    public String getConsecutivo() {
        return consecutivo;
    }

    /**
     * @param consecutivo the consecutivo to set
     */
    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
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
     * @return the motivo
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * @param motivo the motivo to set
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    /**
     * @return the referencia
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * @param referencia the referencia to set
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    /**
     * @return the nombre1
     */
    public String getNombre1() {
        return nombre1;
    }

    /**
     * @param nombre1 the nombre1 to set
     */
    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    /**
     * @return the nombre2
     */
    public String getNombre2() {
        return nombre2;
    }

    /**
     * @param nombre2 the nombre2 to set
     */
    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    /**
     * @return the apellido1
     */
    public String getApellido1() {
        return apellido1;
    }

    /**
     * @param apellido1 the apellido1 to set
     */
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    /**
     * @return the apellido2
     */
    public String getApellido2() {
        return apellido2;
    }

    /**
     * @param apellido2 the apellido2 to set
     */
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    /**
     * @return the tipoDocumento
     */
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * @param tipoDocumento the tipoDocumento to set
     */
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    /**
     * @return the documento
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * @param documento the documento to set
     */
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    /**
     * @return the sexo
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * @return the direccion1
     */
    public String getDireccion1() {
        return direccion1;
    }

    /**
     * @param direccion1 the direccion1 to set
     */
    public void setDireccion1(String direccion1) {
        this.direccion1 = direccion1;
    }

    /**
     * @return the direccion2
     */
    public String getDireccion2() {
        return direccion2;
    }

    /**
     * @param direccion2 the direccion2 to set
     */
    public void setDireccion2(String direccion2) {
        this.direccion2 = direccion2;
    }

    /**
     * @return the celular
     */
    public String getCelular() {
        return celular;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the numeroComparendo
     */
    public String getNumeroComparendo() {
        return numeroComparendo;
    }

    /**
     * @param numeroComparendo the numeroComparendo to set
     */
    public void setNumeroComparendo(String numeroComparendo) {
        this.numeroComparendo = numeroComparendo;
    }

    /**
     * @return the infraccion
     */
    public String getInfraccion() {
        return infraccion;
    }

    /**
     * @param infraccion the infraccion to set
     */
    public void setInfraccion(String infraccion) {
        this.infraccion = infraccion;
    }

    /**
     * @return the descripcionInfraccion
     */
    public String getDescripcionInfraccion() {
        return descripcionInfraccion;
    }

    /**
     * @param descripcionInfraccion the descripcionInfraccion to set
     */
    public void setDescripcionInfraccion(String descripcionInfraccion) {
        this.descripcionInfraccion = descripcionInfraccion;
    }

    /**
     * @return the fechaComparendo
     */
    public String getFechaComparendo() {
        return fechaComparendo;
    }

    /**
     * @param fechaComparendo the fechaComparendo to set
     */
    public void setFechaComparendo(String fechaComparendo) {
        this.fechaComparendo = fechaComparendo;
    }

    /**
     * @return the numeroSancion
     */
    public String getNumeroSancion() {
        return numeroSancion;
    }

    /**
     * @param numeroSancion the numeroSancion to set
     */
    public void setNumeroSancion(String numeroSancion) {
        this.numeroSancion = numeroSancion;
    }

    /**
     * @return the fechaSancion
     */
    public String getFechaSancion() {
        return fechaSancion;
    }

    /**
     * @param fechaSancion the fechaSancion to set
     */
    public void setFechaSancion(String fechaSancion) {
        this.fechaSancion = fechaSancion;
    }

    /**
     * @return the valorSancion
     */
    public String getValorSancion() {
        return valorSancion;
    }

    /**
     * @param valorSancion the valorSancion to set
     */
    public void setValorSancion(String valorSancion) {
        this.valorSancion = valorSancion;
    }

    /**
     * @return the archivoPlano
     */
    public int getArchivoPlano() {
        return archivoPlano;
    }

    /**
     * @param archivoPlano the archivoPlano to set
     */
    public void setArchivoPlano(int archivoPlano) {
        this.archivoPlano = archivoPlano;
    }

    /**
     * @return the mensajeCargue
     */
    public String getMensajeCargue() {
        return mensajeCargue;
    }

    /**
     * @param mensajeCargue the mensajeCargue to set
     */
    public void setMensajeCargue(String mensajeCargue) {
        this.mensajeCargue = mensajeCargue;
    }

}
