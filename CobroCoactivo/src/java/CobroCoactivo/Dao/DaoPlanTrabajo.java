/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivPlanTrabajos;
import CobroCoactivo.Utility.HibernateUtil;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author AMORENO
 */
public class DaoPlanTrabajo extends ImpGeneryHibernateDao<CivPlanTrabajos, Integer> implements ITPlanTrabajo {

    @Override
    public CivPlanTrabajos getPlanTrabajo(Session session, int idPlanTrabajo) throws Exception {
        String sql = "FROM CivPlanTrabajos WHERE platraId =:idPlanTrabajo";
        Query query = session.createQuery(sql);
        query.setParameter("idPlanTrabajo", new BigDecimal(idPlanTrabajo));
        if (query.list().size() > 0) {
            CivPlanTrabajos object = (CivPlanTrabajos) query.list().get(0);
            return object;
        }

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

    @Override
    public CivPlanTrabajos find(Session session, int id) throws Exception {

        String sql = "FROM CivPlanTrabajos WHERE platraId = :id";
        Query query = session.createQuery(sql);
        query.setBigDecimal("id", BigDecimal.valueOf(id));
        if (query.list().size() > 0) {
            CivPlanTrabajos civEtapasTrabajos = (CivPlanTrabajos) query.list().get(0);

            return civEtapasTrabajos;
        }

        return null;

    }

    @Override
    public CivPlanTrabajos getPlanTrabajo(String nombrePlanTrabajo) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String sql = "SELECT * FROM CIV_PLAN_TRABAJOS WHERE PLATRA_DESCRIPCION =:nombrePlanTrabajo";
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(CivPlanTrabajos.class);
            query.setString("nombrePlanTrabajo", nombrePlanTrabajo);
            if (query.list().size() > 0) {
                CivPlanTrabajos object = (CivPlanTrabajos) query.list().get(0);
                return object;
            }
            return null;
        } finally {
            session.close();
        }
    }

}
