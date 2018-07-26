/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanPlanGeneral;
import org.hibernate.Session;

/**
 *
 * @author jvergara
 */
public interface PlanGeneralBO {

    void guardarPlanGeneral(BeanPlanGeneral obj) throws Exception;

    void actualizarPlanGeneral(BeanPlanGeneral bean) throws Exception;

    void listarPlanesGenerales(BeanPlanGeneral bean) throws Exception;

    void listarEstadoGenerales(BeanPlanGeneral bean) throws Exception;

    void listarEtapaGeneralesPorIdPlanGeneral(BeanPlanGeneral bean) throws Exception;

    void listarEstadoEtapasGenerales(BeanPlanGeneral bean) throws Exception;

    void guardarEtapaGeneral(BeanPlanGeneral bean) throws Exception;

    void actualizarEtapaGeneral(BeanPlanGeneral bean) throws Exception;

    void guardarFasesGeneral(BeanPlanGeneral bean) throws Exception;

    void listarEstadoFasesGenerales(BeanPlanGeneral bean) throws Exception;

    void listarFasesGeneralesPorEtapa(BeanPlanGeneral bean) throws Exception;

    void actualizarFase(BeanPlanGeneral bean) throws Exception;
}
