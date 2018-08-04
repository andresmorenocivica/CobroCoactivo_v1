/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivEstadoDeudas;
import CobroCoactivo.Utility.HibernateUtil;
import java.math.BigDecimal;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author emadrid
 */
public class DaoEstadoDeudas extends ImpGeneryHibernateDao<CivEstadoDeudas, Integer> implements ITEstadoDeudas {

    @Override
    public CivEstadoDeudas getEstadoDeudas(Session session, BigDecimal estadoDeudas) throws Exception {
        String hql = "from CivEstadoDeudas where estdeuId=:codigoEstado";
        Query query = session.createQuery(hql);
        query.setBigDecimal("codigoEstado", estadoDeudas);
        if (query.list().size() > 0) {
            return (CivEstadoDeudas) query.list().get(0);
        }
        return null;
    }

}
