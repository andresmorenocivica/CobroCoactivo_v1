/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanGestionExpedientes;

/**
 *
 * @author emadrid
 */
public interface GestionExpedientesBO {

    void crearExpediente(BeanGestionExpedientes bean) throws Exception;

    void cargarSolicitudes(BeanGestionExpedientes bean) throws Exception;

    void buscarDetSolicitudes(BeanGestionExpedientes bean) throws Exception;

    void aceptarSolicitudExpediente(BeanGestionExpedientes bean) throws Exception;

    void updateArchivo(BeanGestionExpedientes bean) throws Exception;

    void abrirExpediente(BeanGestionExpedientes bean) throws Exception;

    void buscarPersona(BeanGestionExpedientes bean) throws Exception;

    void buscarArchivo(BeanGestionExpedientes bean) throws Exception;

    void buscarExpediente(BeanGestionExpedientes bean) throws Exception;

    void guardarArchivo(BeanGestionExpedientes bean) throws Exception;

    void mostrarExpedienteSelect(BeanGestionExpedientes bean) throws Exception;

    void enviarSolicitud(BeanGestionExpedientes bean) throws Exception;
}
