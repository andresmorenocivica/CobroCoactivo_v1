/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivDetalleDeudas;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author AMORENO
 */
public class DaoDetalleDeudas extends ImpGeneryHibernateDao<CivDetalleDeudas, Integer> implements ITDetalleDeudas{

    @Override
    public List<CivDetalleDeudas> getListDetallesDeudasByidDeuda(Session session, long id) throws Exception {
      
        String hql = "from CivDetalleDeudas where civDeudas.deuId = :id";
        Query query =  session.createQuery(hql);
        query.setParameter("id", BigDecimal.valueOf(id));
        if (query.list().size() > 0) {
            
            return query.list();
        }
        
        return null;

    }
    
}
