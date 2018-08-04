/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivEtapasTrabajos;
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
public class DaoEtapasTrabajo extends ImpGeneryHibernateDao<CivEtapasTrabajos, Integer> implements ITEtapasTrabajo {

    @Override
    public List<CivEtapasTrabajos> listarEtapasTrabajoByPlantrabajo(Session session, int idPlanTrabajo) throws Exception {
        String sql = "FROM CivEtapasTrabajos WHERE civPlanTrabajos.platraId =:platraId and civEstadoEtapaTrabajos.estetatraId=1 ORDER BY etatraObligatorio";
        Query query = session.createQuery(sql);
        query.setParameter("platraId", new BigDecimal(idPlanTrabajo));
        if (query.list().size() > 0) {
            return query.list();
        }
        return null;
    }

    @Override
    public CivEtapasTrabajos find(Session session, int id) throws Exception {

        String sql = "FROM CivEtapasTrabajos WHERE etatraId = :id";
        Query query = session.createQuery(sql);
        query.setBigDecimal("id", BigDecimal.valueOf(id));
        if (query.list().size() > 0) {
            CivEtapasTrabajos civEtapasTrabajos = (CivEtapasTrabajos) query.list().get(0);
            System.out.println(civEtapasTrabajos.getCivFasesTrabajoses());
            return civEtapasTrabajos;
        }

        return null;
    }

}
