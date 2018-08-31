/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivEstadoDeudas;
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
        String hql = "from CivEstadoDeudas where estdeuId=:idEstado";
        Query query = session.createQuery(hql);
        query.setBigDecimal("idEstado", estadoDeudas);
        if (query.list().size() > 0) {
            return (CivEstadoDeudas) query.list().get(0);
        }
        return null;
    }

}
