/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivEtapasGenerales;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author jvergara
 */
public class DaoEstapaGeneral extends ImpGeneryHibernateDao<CivEtapasGenerales, Integer> implements ITEstapaGeneral {

    @Override
    public List<CivEtapasGenerales> findAllEtapaByIdPlanGeneral(int id) throws Exception {
        Session session = getSessionFactory().openSession();
        String hql = "from CivEtapasGenerales where civPlanGenerales.plagenId=:id order by prioridad";
        Query query = session.createQuery(hql);
        query.setParameter("id", new BigDecimal(id));
        if (query.list().size() > 0) {
            return query.list();
        }
        return null;

    }

}
