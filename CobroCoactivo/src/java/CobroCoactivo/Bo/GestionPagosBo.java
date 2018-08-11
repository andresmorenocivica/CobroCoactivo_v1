/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanGestionPagos;

/**
 *
 * @author jvergara
 */
public interface GestionPagosBo {
    
    void listarPagos(BeanGestionPagos pagos) throws Exception;
    
    void verCarteras(BeanGestionPagos beanGestionPagos) throws Exception;
    
    void sincronizarPagos(BeanGestionPagos beanGestionPagos) throws Exception;
    
    
    
}
