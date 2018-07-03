/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivEtapasGenerales;
import CobroCoactivo.Utility.HibernateUtil;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author jvergara
 */
public class DaoEstapaGeneral extends ImpGeneryHibernateDao<CivEtapasGenerales, Integer> implements ITEstapaGeneral {

    @Override
    public List<CivEtapasGenerales> findAllEtapaByIdPlanGeneral(Session session ,int id) throws Exception {
     
        String hql = "from CivEtapasGenerales where civPlanGenerales.plagenId=:id order by etagenPrioridad";
        Query query = session.createQuery(hql);
        query.setParameter("id", new BigDecimal(id));
        if (query.list().size() > 0) {
            return query.list();
        }
        return null;

    }
    
    @Override
    public CivEtapasGenerales getCivEtapaGeneral(int idEtapaGeneral) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "SELECT * FROM CIV_ETAPAS_GENERALES WHERE ETAGEN_ID = :idEtapaGeneral";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(CivEtapasGenerales.class);
        query.setInteger("idEtapaGeneral", idEtapaGeneral);
        if (query.list().size() > 0) {
            return (CivEtapasGenerales) query.list().get(0);
        }   
        session.close();
        return null;
    }

}
