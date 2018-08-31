/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivExcepciones;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author emadridp
 */
public class DaoExcepciones extends ImpGeneryHibernateDao<CivExcepciones, Integer> implements ITExcepciones {

    @Override
    public CivExcepciones getExcepciones(Session session, String numRadicado) throws Exception {
        String hql = "from CivExcepciones where excNumeroRadicado =:numRadicado";
        Query query = session.createQuery(hql);
        query.setParameter("numRadicado", numRadicado);
        if (query.list().size() > 0) {
            return (CivExcepciones) query.list().get(0);
        }
        return null;
    }

}
