/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivDetalleExpedientes;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author emadrid
 */
public class DaoDetalleExpedientes extends ImpGeneryHibernateDao<CivDetalleExpedientes, Integer> implements ITDetalleExpedientes {

    @Override
    public List<CivDetalleExpedientes> getListCivDetalleExpediente(int idExpediente) throws Exception {
        Session session = getSessionFactory().openSession();
        String hql = "from CivDetalleExpedientes where civExpedientes.expId =:expId";
        Query query = session.createQuery(hql);
        query.setParameter("expId", new BigDecimal(idExpediente));
        if (query.list().size() > 0) {
            return query.list();
        }
        return null;
    }

}
