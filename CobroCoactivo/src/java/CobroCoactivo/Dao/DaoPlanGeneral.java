/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivPlanGenerales;
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
public class DaoPlanGeneral extends ImpGeneryHibernateDao<CivPlanGenerales, Integer> implements ITPlanGeneral {

    public List<CivPlanGenerales> findCivPlanGeneral(Session session, int id) throws Exception {
        String sql = "SELECT * FROM CivPlanGenerales WHERE plagenId =:id";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(CivPlanGenerales.class);
        query.setInteger("id", id);
        return query.list();
    }

    @Override
    public CivPlanGenerales getCivPlanGeneral(int idPlangeneral) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "SELECT * FROM CIV_PLAN_GENERALES WHERE PLAGEN_ID = :idPlangeneral";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(CivPlanGenerales.class);
        query.setInteger("idPlangeneral", idPlangeneral);
        if (query.list().size() > 0) {
            return (CivPlanGenerales) query.list().get(0);
        }
        session.close();
        return null;
    }

    @Override
    public List<CivPlanGenerales> getListPlanGenerales(Session session) throws Exception {
        String hql = "from CivPlanGenerales where civEstadoPlanGenerales.estplagenId=1";
        Query query = session.createQuery(hql);
        return query.list();
    }

    @Override
    public CivPlanGenerales getCivPlanGeneralByColor(String color) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "from CivPlanGenerales where plagenColor=:color";
        Query query =  session.createQuery(sql);
        query.setString("color", color);
        if (query.list().size() > 0) {
            return (CivPlanGenerales) query.list().get(0);
        }
        session.close();
        return null;
    }

    @Override
    public CivPlanGenerales getCivPlanGeneralByDescripcion(String descripcion) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "FROM CivPlanGenerales WHERE plagenDescripcion = :descripcion";
        Query query = session.createQuery(sql);
        query.setString("descripcion", descripcion);
        if (query.list().size() > 0) {
            return (CivPlanGenerales) query.list().get(0);
        }
        session.close();
        return null;
    }
}
