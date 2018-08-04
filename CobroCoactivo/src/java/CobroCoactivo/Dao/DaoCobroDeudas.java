/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivCobroDeudas;
import CobroCoactivo.Utility.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author emadrid
 */
public class DaoCobroDeudas extends ImpGeneryHibernateDao<CivCobroDeudas, Integer> implements ITCobroDeudas {

    @Override
    public CivCobroDeudas getCobroDeudas(Session session, int idCobroDeuda) throws Exception {
        String hql = "from CivCobroDeudas where cobdeuId=:cobroDeuda";
        Query query = session.createQuery(hql);
        query.setInteger("cobroDeuda", idCobroDeuda);
        if (query.list().size() > 0) {
            return (CivCobroDeudas) query.list().get(0);
        }
        return null;

    }

}
