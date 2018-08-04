/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivMovimientos;
import CobroCoactivo.Utility.HibernateUtil;
import java.math.BigDecimal;
import java.math.BigInteger;
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
    public CivMovimientos getMovimientoByDeudaByFaseTrabajo(Session session, int deuId, int faseTrabajo) throws Exception {
        String sql = "FROM CivMovimientos WHERE civDeudas.deuId = :deuId AND civFasesTrabajos.fastraId = :faseTrabajo";
        Query query = session.createQuery(sql);
        query.setParameter("deuId", new BigDecimal(deuId));
        query.setParameter("faseTrabajo", new BigDecimal(faseTrabajo));
        if (query.list().size() > 0) {
            return (CivMovimientos) query.list().get(0);
        }
        return null;
    }

    @Override
    public List<CivMovimientos> listMovimientos(Session session, int idDeudaMovimientos) throws Exception {
        String hql = "from CivMovimientos where civDeudas.deuId=:idDeudaMovimientos";
        Query query = session.createQuery(hql);
        query.setInteger("idDeudaMovimientos", idDeudaMovimientos);
        if (query.list().size() > 0) {
            return query.list();
        }
        return null;

    }

    @Override
    public List<CivMovimientos> listMovimiento(Session session, int fase) throws Exception {
        String hql = "from CivMovimientos where civFasesTrabajos.fastraId=:fase";
        Query query = session.createQuery(hql);
        query.setInteger("fase", fase);
        if (query.list().size() > 0) {
            return query.list();
        }

        return null;
    }

    @Override
    public List<CivMovimientos> getMovimientoByUser(int idUsuario) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from CivMovimientos where civUsuarios.usuId =:idUsuario";
        Query query = session.createQuery(hql);
        query.setParameter("idUsuario", new BigDecimal(idUsuario));
        if (query.list().size() > 0) {
            return query.list();
        }
        session.close();
        return null;
    }

}
