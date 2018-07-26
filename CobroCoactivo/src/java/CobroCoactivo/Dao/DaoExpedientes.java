/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivExpedientes;
import CobroCoactivo.Utility.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author emadrid
 */
public class DaoExpedientes extends ImpGeneryHibernateDao<CivExpedientes, Integer> implements ITExpedientes {

    @Override
    public CivExpedientes getCivExpedientes(String referencia) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from CivExpedientes where expReferencia=:referencia";
        Query query = session.createQuery(hql);
        query.setParameter("referencia", referencia);
        if (query.list().size() > 0) {
            return (CivExpedientes) query.list().get(0);
        }
        return null;
    }

}
