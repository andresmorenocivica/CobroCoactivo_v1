/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivPlanGenerales;
import CobroCoactivo.Utility.HibernateUtil;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author jvergara
 */
public class DaoPlanGeneral extends ImpGeneryHibernateDao<CivPlanGenerales,Integer> implements ITPlanGeneral{

   
    public List<CivPlanGenerales> findCivPlanGeneral(Session session,int id) throws Exception {
       
        String sql = "SELECT * FROM CivPlanGenerales WHERE plagenId =:id";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(CivPlanGenerales.class);
        query.setInteger("id", id);
        return query.list();
    }
    
}
