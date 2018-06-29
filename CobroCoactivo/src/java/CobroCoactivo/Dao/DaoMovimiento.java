/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivMovimientos;
import CobroCoactivo.Utility.HibernateUtil;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author AMORENO
 */
public class DaoMovimiento extends ImpGeneryHibernateDao<CivMovimientos, Integer> implements ITMovimiento{

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
    
}
