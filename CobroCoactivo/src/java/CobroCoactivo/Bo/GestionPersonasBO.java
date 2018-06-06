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

     void consultarByDocumentoByTipoDocumento(BeanGestionPersonas bean) throws Exception;
}
