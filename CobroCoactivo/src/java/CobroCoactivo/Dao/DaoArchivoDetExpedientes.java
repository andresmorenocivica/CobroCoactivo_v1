/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivArchivoDetExpedientes;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author emadrid
 */
public class DaoArchivoDetExpedientes extends ImpGeneryHibernateDao<CivArchivoDetExpedientes, Integer> implements ITArchivoDetExpedientes {

    @Override
    public List<CivArchivoDetExpedientes> getCivArchivoDetExpedientes(Session session, int idDetExpediente) throws Exception {
        String hql = "from CivArchivoDetExpedientes where civDetalleExpedientes.detexpId =:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", new BigDecimal(idDetExpediente));
        if (query.list().size() > 0) {
            return query.list();
        }
        return null;
    }

}
