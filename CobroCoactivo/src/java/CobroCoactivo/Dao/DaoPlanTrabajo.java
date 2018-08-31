/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivPlanTrabajos;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author AMORENO
 */
public class DaoPlanTrabajo extends ImpGeneryHibernateDao<CivPlanTrabajos, Integer> implements ITPlanTrabajo {

    @Override
    public CivPlanTrabajos getPlanTrabajo(Session session, int idPlanTrabajo) throws Exception {
        String hql = "FROM CivPlanTrabajos WHERE platraId =:idPlanTrabajo";
        Query query = session.createQuery(hql);
        query.setParameter("idPlanTrabajo", new BigDecimal(idPlanTrabajo));
        if (query.list().size() > 0) {
            CivPlanTrabajos object = (CivPlanTrabajos) query.list().get(0);
            return object;
        }
        return null;

    }

    @Override
    public List<CivPlanTrabajos> getAllPlanTrabajo(Session session) throws Exception {
        String hql = "from CivPlanTrabajos where civEstadoPlanTrabajos.estplatraId=:1";
        Query query = session.createQuery(hql);
        if (query.list().size() > 0) {
            return query.list();
        }
        return null;
    }

    @Override
    public CivPlanTrabajos getPlanTrabajo(Session session, String nombrePlanTrabajo) throws Exception {
        String hql = "from CivPlanTrabajos where platraDescripcion =:nombrePlanTrabajo";
        Query query = session.createQuery(hql);
        query.setString("nombrePlanTrabajo", nombrePlanTrabajo);
        if (query.list().size() > 0) {
            CivPlanTrabajos object = (CivPlanTrabajos) query.list().get(0);
            return object;
        }
        return null;
    }

}
