/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanActosAdministractivo;

/**
 *
 * @author jvergara
 */
public interface GestionActosAdministractivosBO {
    
    
    void getTodosActosAdministractivos(BeanActosAdministractivo beanActosAdministractivo) throws Exception;
    
}
