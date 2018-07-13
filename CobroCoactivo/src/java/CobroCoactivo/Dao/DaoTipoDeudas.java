/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivDetalleDeudas;
import CobroCoactivo.Persistencia.CivTipoDeudas;
import CobroCoactivo.Utility.HibernateUtil;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author emadrid
 */

public class DaoTipoDeudas extends ImpGeneryHibernateDao<CivTipoDeudas, Integer> implements ITTipoDeudas {

    @Override
    public List<CivTipoDeudas> listAll() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from CivTipoDeudas";
        Query query = session.createQuery(hql);
        if (query.list().size() > 0) {
            return query.list();
        }
        session.close();
        return null;
    }
    
    @Override
    public CivTipoDeudas getTipoDeuda(BigDecimal tipdeuId) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from CivTipoDeudas where estperId=:tipdeuId";
        Query query = session.createQuery(hql);
        query.setParameter("tipdeuId", tipdeuId);
        if (query.list().size() > 0) {
            return (CivTipoDeudas) query.list().get(0);
        }
        session.close();
        return null;
    }

}
