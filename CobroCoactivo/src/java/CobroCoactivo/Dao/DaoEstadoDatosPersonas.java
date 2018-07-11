/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivEstadoDatosPersonas;
import CobroCoactivo.Utility.HibernateUtil;
import java.math.BigDecimal;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author emadrid
 */
public class DaoEstadoDatosPersonas extends ImpGeneryHibernateDao<CivEstadoDatosPersonas, Integer> implements ITEstadoDatosPersonas {

    @Override
    public CivEstadoDatosPersonas getEstadoDatosPersonas(BigDecimal idEstado) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from CivEstadoDatosPersonas where estdatperId=:idEstado";
        Query query = session.createQuery(hql);
        query.setParameter("idEstado", idEstado);
        if (query.list().size() > 0) {
            return (CivEstadoDatosPersonas) query.list().get(0);
        }
        session.close();
        return null;
    }
}
