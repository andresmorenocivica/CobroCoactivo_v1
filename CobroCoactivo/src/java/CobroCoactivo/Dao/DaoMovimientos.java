/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivMovimientos;
import CobroCoactivo.Utility.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author emadrid
 */
public class DaoMovimientos extends ImpGeneryHibernateDao<CivMovimientos, Integer> implements ITMovimientos {

    @Override
    public CivMovimientos getMovimientoByDeudaByFaseTrabajo(int deuId, int faseTrabajo) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "SELECT * FROM CIV_MOVIMIENTOS WHERE MOV_DEUDA_FK = :deuId AND MOV_FASTRA_FK = :faseTrabajo";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(CivMovimientos.class);
        query.setInteger("deuId", deuId);
        query.setInteger("faseTrabajo", faseTrabajo);
        if (query.list().size() > 0) {
            return (CivMovimientos) query.list().get(0);
        }
        session.close();
        return null;
    }

    @Override
    public List<CivMovimientos> listMovimientos(int idDeudaMovimientos) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from CivMovimientos where civDeudas.deuId=:idDeudaMovimientos";
        Query query = session.createQuery(hql);
        query.setInteger("idDeudaMovimientos", idDeudaMovimientos);
        if (query.list().size() > 0) {
            return query.list();
        }
        session.close();
        return null;

    }

    @Override
    public List<CivMovimientos> listMovimiento(int fase) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "from CivMovimientos where civFasesTrabajos.fastraId=:fase";
            Query query = session.createQuery(hql);
            query.setInteger("fase", fase);
            if (query.list().size() > 0)
                return query.list();
            
            return null;

        } finally {
            session.close();
        }

    }

}
