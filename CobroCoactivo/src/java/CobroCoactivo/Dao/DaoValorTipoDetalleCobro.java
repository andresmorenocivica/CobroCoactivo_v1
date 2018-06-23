/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivValorTipoDetalleCobro;
import CobroCoactivo.Utility.HibernateUtil;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author emadrid
 */
public class DaoValorTipoDetalleCobro extends ImpGeneryHibernateDao<CivValorTipoDetalleCobro, Integer> implements ITValorTipoDetalleCobro {

    @Override
    public CivValorTipoDetalleCobro cargarValorDetalle(int ValorTipo) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "SELECT * FROM CIV_VALOR_TIPO_DETALLE_COBRO where VALTIPDETCOB_ID =:ValorTipo";
        SQLQuery query = session.createSQLQuery(hql);
        query.addEntity(CivValorTipoDetalleCobro.class);
        query.setInteger("ValorTipo", ValorTipo);
        if (query.list().size() > 0) {
            session.close();
            return (CivValorTipoDetalleCobro) query.list().get(0);
        }
        session.close();
        return null;
    }

}
