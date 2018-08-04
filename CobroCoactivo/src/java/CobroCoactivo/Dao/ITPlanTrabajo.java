/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivPlanTrabajos;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author AMORENO
 */
public interface ITPlanTrabajo extends ITGeneryHibernateDao<CivPlanTrabajos, Integer> {
    
     public CivPlanTrabajos find(Session session ,int id) throws Exception;

    public CivPlanTrabajos getPlanTrabajo(Session session,int idPlanTrabajo) throws Exception;
    public CivPlanTrabajos getPlanTrabajo(String nombrePlanTrabajo) throws Exception;
    
    public List<CivPlanTrabajos> getAllPlanTrabajo()throws Exception;
    
    
}
