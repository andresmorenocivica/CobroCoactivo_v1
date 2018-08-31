/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivTipoDetalleCobro;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author emadrid
 */
public class DaoTipoDetalleCobro extends ImpGeneryHibernateDao<CivTipoDetalleCobro, Integer> implements ITTipoDetalleCobro {

    @Override
    public CivTipoDetalleCobro cargarTipoDetalleCobro(Session session, int tipoDetalle) throws Exception {
        String hql="from CivTipoDetalleCobro where tipdetcobId=:tipoDetalle";
        Query query = session.createQuery(hql);
        query.setInteger("tipoDetalle", tipoDetalle);
        if (query.list().size() > 0) {
            return (CivTipoDetalleCobro) query.list().get(0);
        }
        return null;
    }

}
