/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivPrestamoExpHistorial;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author emadridp
 */
public class DaoPrestamoExpHistorial extends ImpGeneryHibernateDao<CivPrestamoExpHistorial, Integer> implements ITPrestamoExpHistorial {

    @Override
    public List<CivPrestamoExpHistorial> getCivPrestamoExpHistorial(Session session, int idUser) throws Exception {
        String hql = " from CivPrestamoExpHistorial where civUsuarios.usuId =:idUser";
        Query query = session.createQuery(hql);
        query.setParameter("idUser", new BigDecimal(idUser));
        if (query.list().size() > 0) {
            return query.list();
        }
        return null;
    }

}
