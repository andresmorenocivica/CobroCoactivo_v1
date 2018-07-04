/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanGestionPlanTrabajo;
import CobroCoactivo.Persistencia.CivEtapasTrabajos;
import java.util.List;

/**
 *
 * @author jvergara
 */
public interface GestionPlanTrabajoBO {
    
    /** 
     * devuelve lista con plan general
     * @see GestionPersonasImpBO
     * @throws Exception
     * @param beanGestionPlanTrabajo bean 
    */
    public void getListPlanGenaral(BeanGestionPlanTrabajo beanGestionPlanTrabajo) throws Exception;

    /**
     * Guarda un plan de trabajo
     * @param beanGestionPlanTrabajo
     * @throws Exception 
     */
    public void guardarPlanTrabajo(BeanGestionPlanTrabajo beanGestionPlanTrabajo) throws Exception;
    
    public void guardarEtapaTrabajo(BeanGestionPlanTrabajo beanGestionPlanTrabajo) throws Exception;
    
    
    /**
     * retorna una lista de civplantrabajo activa
     * @param beanGestionPlanTrabajo
     * @throws Exception 
     */
    public void getListaTrabajo(BeanGestionPlanTrabajo beanGestionPlanTrabajo) throws Exception;
    
    
    /**
     * 
     * @param beanGestionPlanTrabajo
     * @throws Exception 
     */
    public void getListEtapaTrabajo(BeanGestionPlanTrabajo beanGestionPlanTrabajo) throws Exception;
    
    public void getListEtapaGenerales(BeanGestionPlanTrabajo beanGestionPlanTrabajo) throws Exception;
    
    /**
     * 
     * @param beanGestionPlanTrabajo
     * @throws Exception 
     */
    public void getFases(BeanGestionPlanTrabajo beanGestionPlanTrabajo) throws Exception;
    
}
