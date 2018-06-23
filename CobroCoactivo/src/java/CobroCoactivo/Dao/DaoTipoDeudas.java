/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.Persistencia.CivTipoDeudas;
import CobroCoactivo.Utility.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author emadrid
 */
public class DaoTipoDeudas implements ITTipoDeudas {

    @Override
    public List<CivTipoDeudas> listAll() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from CivTipoDeudas";
        Query query = session.createQuery(hql);
        if (query.list().size() > 0) {
            return query.list();
        }
        session.close();
        return null;
    }

}
