/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivPlanGenerales;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author jvergara
 */
public interface ITPlanGeneral extends ITGeneryHibernateDao<CivPlanGenerales,Integer>{
    
    /**
     * 
     * @param session 
     * @param id
     * @return
     * @throws Exception 
     */
       
    List<CivPlanGenerales> findCivPlanGeneral(Session session ,int id) throws Exception;
    CivPlanGenerales getCivPlanGeneral(int idPlangeneral) throws Exception ;
    
    List<CivPlanGenerales> getListPlanGenerales(Session session) throws Exception;
    
}
