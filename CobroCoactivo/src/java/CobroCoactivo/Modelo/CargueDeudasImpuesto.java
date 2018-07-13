/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Modelo;

import CobroCoactivo.Persistencia.CivDeudasImpuesto;
import CobroCoactivo.Utility.UtilityCargues;
import java.util.List;

/**
 *
 * @author AMORENO
 */
public class CargueDeudasImpuesto {
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
    private String placa;
    private String servicioVehiculo;
    private String claseVehiculo;
    private String avaluo;
    private String liquidacionOficial;
    private String fechaLiquidacion;
    private String fechamatricula;
    private int archivoPlano;
    private String mensajeCargue;

    public CargueDeudasImpuesto() {
    }

    public CargueDeudasImpuesto(List<UtilityCargues> listCamposDeudasImpuestos) {
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
        this.placa = listCamposDeudasImpuestos.get(18).getValor();
        this.servicioVehiculo = listCamposDeudasImpuestos.get(19).getValor();
        this.claseVehiculo = listCamposDeudasImpuestos.get(20).getValor();
        this.avaluo = listCamposDeudasImpuestos.get(21).getValor();
        this.liquidacionOficial = listCamposDeudasImpuestos.get(22).getValor();
        this.fechaLiquidacion = listCamposDeudasImpuestos.get(23).getValor();
        this.fechamatricula = listCamposDeudasImpuestos.get(24).getValor();
    }
    
    

    public CargueDeudasImpuesto(CivDeudasImpuesto civDeudasImpuesto) {
        this.id = civDeudasImpuesto.getDeuimpId().intValue();
        this.consecutivo = civDeudasImpuesto.getDeuimpConsecutivo();
        this.fecha = civDeudasImpuesto.getDeuimpFecha();
        this.tipo = civDeudasImpuesto.getDeuimpTipo();
        this.valor = civDeudasImpuesto.getDeuimpValor();
        this.motivo = civDeudasImpuesto.getDeuimpMotivo();
        this.referencia = civDeudasImpuesto.getDeuimpReferencia();
        this.nombre1 = civDeudasImpuesto.getDeuimpNombre1();
        this.nombre2 = civDeudasImpuesto.getDeuimpNombre2();
        this.apellido1 = civDeudasImpuesto.getDeuimpApellido1();
        this.apellido2 = civDeudasImpuesto.getDeuimpApellido2();
        this.tipoDocumento = civDeudasImpuesto.getDeuimpTipodocumento();
        this.documento = civDeudasImpuesto.getDeuimpDocumento();
        this.sexo = civDeudasImpuesto.getDeuimpSexo();
        this.direccion1 = civDeudasImpuesto.getDeuimpDireccion1();
        this.direccion2 = civDeudasImpuesto.getDeuimpDireccion2();
        this.celular = civDeudasImpuesto.getDeuimpCelular();
        this.telefono = civDeudasImpuesto.getDeuimpTelefono();
        this.correo = civDeudasImpuesto.getDeuimpCorreoelectronico();
        this.placa = civDeudasImpuesto.getDeuimpPlaca();
        this.servicioVehiculo = civDeudasImpuesto.getDeuimpServiciovehiculo();
        this.claseVehiculo = civDeudasImpuesto.getDeuimpClasevehiculo();
        this.avaluo = civDeudasImpuesto.getDeuimpAvaluo();
        this.liquidacionOficial = civDeudasImpuesto.getDeuimpLiquidacionoficial();
        this.fechaLiquidacion = civDeudasImpuesto.getDeuimpFechaliquidacion();
        this.fechamatricula = civDeudasImpuesto.getDeuimpFechamatricula();
        this.archivoPlano = civDeudasImpuesto.getDeuimpArchivoFk().intValue();
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
    public String getFererencia() {
        return referencia;
    }

    /**
     * @param fererencia the referencia to set
     */
    public void setFererencia(String fererencia) {
        this.referencia = fererencia;
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
     * @return the placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * @return the servicioVehiculo
     */
    public String getServicioVehiculo() {
        return servicioVehiculo;
    }

    /**
     * @param servicioVehiculo the servicioVehiculo to set
     */
    public void setServicioVehiculo(String servicioVehiculo) {
        this.servicioVehiculo = servicioVehiculo;
    }

    /**
     * @return the claseVehiculo
     */
    public String getClaseVehiculo() {
        return claseVehiculo;
    }

    /**
     * @param claseVehiculo the claseVehiculo to set
     */
    public void setClaseVehiculo(String claseVehiculo) {
        this.claseVehiculo = claseVehiculo;
    }

    /**
     * @return the avaluo
     */
    public String getAvaluo() {
        return avaluo;
    }

    /**
     * @param avaluo the avaluo to set
     */
    public void setAvaluo(String avaluo) {
        this.avaluo = avaluo;
    }

    /**
     * @return the liquidacionOficial
     */
    public String getLiquidacionOficial() {
        return liquidacionOficial;
    }

    /**
     * @param liquidacionOficial the liquidacionOficial to set
     */
    public void setLiquidacionOficial(String liquidacionOficial) {
        this.liquidacionOficial = liquidacionOficial;
    }

    /**
     * @return the fechaLiquidacion
     */
    public String getFechaLiquidacion() {
        return fechaLiquidacion;
    }

    /**
     * @param fechaLiquidacion the fechaLiquidacion to set
     */
    public void setFechaLiquidacion(String fechaLiquidacion) {
        this.fechaLiquidacion = fechaLiquidacion;
    }

    /**
     * @return the fechamatricula
     */
    public String getFechamatricula() {
        return fechamatricula;
    }

    /**
     * @param fechamatricula the fechamatricula to set
     */
    public void setFechamatricula(String fechamatricula) {
        this.fechamatricula = fechamatricula;
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

    
}
