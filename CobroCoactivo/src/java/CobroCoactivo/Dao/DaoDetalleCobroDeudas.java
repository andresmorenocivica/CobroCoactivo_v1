/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivDetalleCobroDeudas;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author emadrid
 */
public class DaoDetalleCobroDeudas extends ImpGeneryHibernateDao<CivDetalleCobroDeudas, Integer> implements ITDetalleCobroDeudas {

    @Override
    public List<CivDetalleCobroDeudas> listarDetalleCobroDeudas(Session session, int idCobro) throws Exception {
        String hql = "from CivDetalleCobroDeudas where civCobroDeudas.cobdeuId =:idCobro";
        Query query = session.createQuery(hql);
        query.setInteger("idCobro", idCobro);
        if (query.list().size() > 0) {
            return query.list();
        }
        return null;
    }

}
