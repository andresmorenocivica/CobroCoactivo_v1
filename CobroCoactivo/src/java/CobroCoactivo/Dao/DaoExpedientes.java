/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivExpedientes;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author emadrid
 */
public class DaoExpedientes extends ImpGeneryHibernateDao<CivExpedientes, Integer> implements ITExpedientes {

    @Override
    public CivExpedientes getCivExpedientes(Session session, String expPersona) throws Exception {
        String hql = "from CivExpedientes where expReferencia=:referencia";
        Query query = session.createQuery(hql);
        query.setParameter("referencia", expPersona);
        if (query.list().size() > 0) {
            return (CivExpedientes) query.list().get(0);
        }
        return null;
    }

    @Override
    public CivExpedientes getCivExpedientesByUri(Session session, String ubicacion) throws Exception {
        String hql = "from CivExpedientes where expUbicacion=:ubicacion";
        Query query = session.createQuery(hql);
        query.setParameter("ubicacion", ubicacion);
        if (query.list().size() > 0) {
            return (CivExpedientes) query.list().get(0);
        }
        return null;
    }

}
