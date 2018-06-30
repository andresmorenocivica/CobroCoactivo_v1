/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivPlanTrabajos;
import CobroCoactivo.Utility.HibernateUtil;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author AMORENO
 */
public class DaoPlanTrabajo extends ImpGeneryHibernateDao<CivPlanTrabajos, Integer> implements ITPlanTrabajo {

    @Override
    public CivPlanTrabajos getPlanTrabajo(int idPlanTrabajo) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "SELECT * FROM CIV_PLAN_TRABAJOS WHERE PLATRA_ID =:idPlanTrabajo";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(CivPlanTrabajos.class);
        query.setInteger("idPlanTrabajo", idPlanTrabajo);
        if (query.list().size() > 0) {
            return (CivPlanTrabajos) query.list().get(0);
        }
        session.close();
        return null;
    }

    @Override
    public List<CivPlanTrabajos> getAllPlanTrabajo() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "SELECT * FROM CIV_PLAN_TRABAJOS WHERE PLATRA_ESTPLATRA_FK =1";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(CivPlanTrabajos.class);
        if (query.list().size() > 0) {
            return query.list();
        }
        session.close();
        return null;
    }

}
