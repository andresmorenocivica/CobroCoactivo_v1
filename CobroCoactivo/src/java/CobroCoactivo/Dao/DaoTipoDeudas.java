/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivTipoDeudas;
import java.math.BigDecimal;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author emadrid
 */
public class DaoTipoDeudas extends ImpGeneryHibernateDao<CivTipoDeudas, Integer> implements ITTipoDeudas {

    @Override
    public CivTipoDeudas getTipoDeuda(Session session, BigDecimal tipdeuId) throws Exception {
        String hql = "from CivTipoDeudas where estperId=:tipdeuId";
        Query query = session.createQuery(hql);
        query.setParameter("tipdeuId", tipdeuId);
        if (query.list().size() > 0) {
            return (CivTipoDeudas) query.list().get(0);
        }
        return null;
    }

}
