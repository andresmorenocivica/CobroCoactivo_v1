/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivEstadoPersonas;
import CobroCoactivo.Utility.HibernateUtil;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author emadrid
 */
public class DaoEstadoPersonas extends ImpGeneryHibernateDao<CivEstadoPersonas, Integer> implements ITEstadoPersonas {

    @Override
    public List<CivEstadoPersonas> listAll() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from CivEstadoPersonas";
        Query query = session.createQuery(hql);
        if (query.list().size() > 0) {
            return query.list();
        }
        session.close();
        return null;
    }

    @Override
    public CivEstadoPersonas getEstadoPersona(BigDecimal tipoDocumento) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from CivEstadoPersonas where estperId=:codigoEstado";
        Query query = session.createQuery(hql);
        query.setParameter("codigoEstado", tipoDocumento);
        if (query.list().size() > 0) {
            return (CivEstadoPersonas) query.list().get(0);
        }
        session.close();
        return null;
    }

}
