/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivFasesGenerales;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author jvergara
 */
public class DaoFasesGenerales extends ImpGeneryHibernateDao<CivFasesGenerales, Integer> implements ITFasesGenerales{

    @Override
    public List<CivFasesGenerales> AllListByEtapaGeneral(int id) throws Exception {
        Session session = getSessionFactory().openSession();
        String hql = "from CivFasesGenerales where civEtapasGenerales.etagenId=:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", new BigDecimal(id));
        return query.list();
    }
    
}
