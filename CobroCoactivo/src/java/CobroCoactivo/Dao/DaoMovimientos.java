/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivMovimientos;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

/**
 *
 * @author emadrid
 */
public class DaoMovimientos extends ImpGeneryHibernateDao<CivMovimientos, Integer> implements ITMovimientos {

    @Override
    public CivMovimientos getMovimientoByDeudaByFaseTrabajo(Session session, int deuId, int faseTrabajo) throws Exception {
        String hql = "FROM CivMovimientos WHERE civDeudas.deuId = :deuId AND civFasesTrabajos.fastraId = :faseTrabajo";
        Query query = session.createQuery(hql);
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
    public List<CivMovimientos> getMovimientoByUser(Session session, int idUsuario) throws Exception {
        String hql = "from CivMovimientos where civUsuarios.usuId =:idUsuario";
        Query query = session.createQuery(hql);
        query.setParameter("idUsuario", new BigDecimal(idUsuario));
        if (query.list().size() > 0) {
            return query.list();
        }
        return null;
    }

    @Override
    public List<HashMap> getMovimientosByDeuda(Session session, int idDeuda) throws Exception {
        String sql = "SELECT * FROM CIV_MOVIMIENTOS M\n"
                + "INNER JOIN CIV_FASES_TRABAJOS FT ON FT.FASTRA_ID = M.MOV_FASTRA_FK WHERE MOV_DEUDA_FK  =:idDeuda";
        SQLQuery query = session.createSQLQuery(sql);
        query.setParameter("idDeuda", new BigDecimal(idDeuda));
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        if (query.list().size() > 0) {
            return query.list();
        }
        return null;
    }
}
