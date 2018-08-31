/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivValorTipoDetalleCobro;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author emadrid
 */
public class DaoValorTipoDetalleCobro extends ImpGeneryHibernateDao<CivValorTipoDetalleCobro, Integer> implements ITValorTipoDetalleCobro {

    @Override
    public CivValorTipoDetalleCobro cargarValorDetalle(Session session, int ValorTipo) throws Exception {
        String hql="from CivValorTipoDetalleCobro where valtipdetcobId =:ValorTipo";
        Query query = session.createQuery(hql);
        query.setInteger("ValorTipo", ValorTipo);
        if (query.list().size() > 0) {
            return (CivValorTipoDetalleCobro) query.list().get(0);
        }
        return null;
    }

}
