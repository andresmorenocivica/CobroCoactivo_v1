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

    void buscarExpediente(BeanGestionExpedientes bean) throws Exception;

    void abrirExpediente(BeanGestionExpedientes bean) throws Exception;

    void buscarArchivo(BeanGestionExpedientes bean) throws Exception;
}
