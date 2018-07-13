/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivEstadoConfusurec;
import CobroCoactivo.Utility.HibernateUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author emadrid
 */
public class DaoEstadoConfUsuRec extends ImpGeneryHibernateDao<CivEstadoConfusurec, Integer> implements ITEstadoConfUsuRec {

    @Override
    public CivEstadoConfusurec getEstadoConfUsuRec(BigDecimal idEstado) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from CivEstadoConfusurec where estconfusurecId=:idEstado";
        Query query = session.createQuery(hql);
        query.setParameter("idEstado", idEstado);
        if (query.list().size() > 0) {
            return (CivEstadoConfusurec) query.list().get(0);
        }
        session.close();
        return null;
    }

}
