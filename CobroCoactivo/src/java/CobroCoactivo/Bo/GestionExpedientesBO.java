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

    void abrirExpediente(BeanGestionExpedientes bean) throws Exception;

    void buscarArchivo(BeanGestionExpedientes bean) throws Exception;

    void buscarExpediente(BeanGestionExpedientes bean) throws Exception;

    void guardarArchivo(BeanGestionExpedientes bean) throws Exception;

    void mostrarExpedienteSelect(BeanGestionExpedientes bean) throws Exception;

    void enviarSolicitud(BeanGestionExpedientes bean) throws Exception;
}
