/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivDetalleSolicitudes;
import CobroCoactivo.Utility.HibernateUtil;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author emadridp
 */
public class DaoDetalleSolicitudes extends ImpGeneryHibernateDao<CivDetalleSolicitudes, Integer> implements ITDetalleSolicitudes {

    @Override
    public List<CivDetalleSolicitudes> getCivDetalleSolicitudes(Session session, int idSolicitud) throws Exception {
        String hql = "from CivDetalleSolicitudes where civSolicitudes.solId =:idSolicitud";
        Query query = session.createQuery(hql);
        query.setParameter("idSolicitud", new BigDecimal(idSolicitud));
        if (query.list().size() > 0) {
            return query.list();
        }
        return null;
    }

    @Override
    public List<CivDetalleSolicitudes> getCivDetalleSolicitudes(Session session) throws Exception {
        String hql = "from CivDetalleSolicitudes where civEstadoDetalleSolicitudes.estdetsolId =4";
        Query query = session.createQuery(hql);
        if (query.list().size() > 0) {
            return query.list();
        }
        return null;
    }

}
