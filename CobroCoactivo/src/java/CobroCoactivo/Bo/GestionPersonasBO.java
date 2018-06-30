/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanGestionPersonas;

/**
 *
 * @author jvergara
 */
public interface GestionPersonasBO {

    // metodo para cargar los tipos de documento
    void cargarTipoDocumento(BeanGestionPersonas beanGestionPersonas) throws Exception;

    //Metodo para cargar los estados de las personas 
    void cargarEstadoPersonas(BeanGestionPersonas bean) throws Exception;

    void consultarByDocumentoByTipoDocumento(BeanGestionPersonas bean) throws Exception;

    // Metodo para cargar los datos adicionales de la persona que se consulta.
    void cargarDatosPersonas(BeanGestionPersonas bean) throws Exception;

    // Metodo para cargar las deudas de la persona que se consulto.
    void cargarDeudas(BeanGestionPersonas bean) throws Exception;

    // Metodo para actualizar informacion de una persona
    void updatePersona(BeanGestionPersonas bean) throws Exception;

    //Metodo que sirve para guardar una persona
    void guardarPersona(BeanGestionPersonas bean) throws Exception;

    void cargarMovimientosDeudas(BeanGestionPersonas bean) throws Exception;

}
