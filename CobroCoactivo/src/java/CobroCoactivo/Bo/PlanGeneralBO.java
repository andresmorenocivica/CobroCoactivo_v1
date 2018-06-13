/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanPlanGeneral;

/**
 *
 * @author jvergara
 */
public interface PlanGeneralBO {

    void GuardarPlanGeneral(BeanPlanGeneral obj) throws Exception;

    void ActualizarPlanGeneral(BeanPlanGeneral bean) throws Exception;

    void ListarPlanesGenerales(BeanPlanGeneral bean) throws Exception;

    void ListarEstadoGenerales(BeanPlanGeneral bean) throws Exception;

    void listarEtadoGeneralesPorIdPlanGeneral(BeanPlanGeneral bean) throws Exception;

    void ListarEstadoEtapasGenerales(BeanPlanGeneral bean) throws Exception;
    
    void GuardarEtapaGeneral(BeanPlanGeneral bean) throws Exception;
    
     void ActualizarEtapaGeneral(BeanPlanGeneral bean) throws Exception;
    
}
