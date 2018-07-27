/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanGestionCargarPersonas;

/**
 *
 * @author jvergara
 */
public interface GestionCargarPersonaBO {
    
    
    void buscarPersona(BeanGestionCargarPersonas beanGestionCargarPersonas) throws Exception;
    
    void verDeuda(BeanGestionCargarPersonas beanGestionCargarPersonas) throws Exception;
    
    void sincronizarDeuda(BeanGestionCargarPersonas beanGestionCargarPersonas) throws Exception;
    
    
}
