/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivTipoDetalleCobro;
import CobroCoactivo.Utility.HibernateUtil;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author emadrid
 */
public class DaoTipoDetalleCobro extends ImpGeneryHibernateDao<CivTipoDetalleCobro, Integer> implements ITTipoDetalleCobro {

    @Override
    public CivTipoDetalleCobro cargarTipoDetalleCobro(int tipoDetalle) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "SELECT * FROM CIV_TIPO_DETALLE_COBRO WHERE TIPDETCOB_ID =:tipoDetalle";
        SQLQuery query = session.createSQLQuery(hql);
        query.addEntity(CivTipoDetalleCobro.class);
        query.setInteger("tipoDetalle", tipoDetalle);
        if (query.list().size() > 0) {
            session.close();
            return (CivTipoDetalleCobro) query.list().get(0);
        }
        session.close();
        return null;
    }

}
