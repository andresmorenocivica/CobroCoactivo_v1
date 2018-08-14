/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanAcuerdosPagos;

/**
 *
 * @author jvergara
 */
public interface GestionAcuerdosPagosBO {

    void getListAcuerdosPagos(BeanAcuerdosPagos beanAcuerdosPagos) throws Exception;

    void getListCarteras(BeanAcuerdosPagos beanAcuerdosPagos) throws Exception;

    void sincronizar(BeanAcuerdosPagos beanAcuerdosPagos) throws Exception;

}
