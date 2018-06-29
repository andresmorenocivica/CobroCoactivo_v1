/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivMovimientos;
import CobroCoactivo.Utility.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author emadrid
 */
public class DaoMovimientos extends ImpGeneryHibernateDao<CivMovimientos, Integer> implements ITMovimientos {

    @Override
    public List<CivMovimientos> listMovimientos(int idDeudaMovimientos) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from CivMovimientos where civDeudas.deuId=:idDeudaMovimientos";
        Query query = session.createQuery(hql);
        query.setInteger("idDeudaMovimientos", idDeudaMovimientos);
        if (query.list().size() > 0) {
            return query.list();
        }
        session.close();
        return null;

    }
}
