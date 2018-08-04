/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivEtapasGenerales;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author jvergara
 */
public interface ITEstapaGeneral extends ITGeneryHibernateDao<CivEtapasGenerales, Integer>{
    
    
        /**
         *  trae una lista de la etapa general
         * @param session 
         * @param id del civ plan general
         * @return list CivEtapasGenerales
         * @throws Exception 
         */
        List<CivEtapasGenerales> findAllEtapaByIdPlanGeneral(Session session, int id) throws Exception ;
        
         CivEtapasGenerales getCivEtapaGeneral(Session session , int idPlangeneral) throws Exception;
    
}
