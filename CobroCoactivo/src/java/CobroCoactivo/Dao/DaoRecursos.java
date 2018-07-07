/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivRecursos;
import CobroCoactivo.Utility.HibernateUtil;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author emadrid
 */
public class DaoRecursos extends ImpGeneryHibernateDao<CivRecursos, Integer> implements ITRecursos {

    @Override
    public List<CivRecursos> getRecursos(int idRecursos) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "SELECT * FROM CIV_RECURSOS WHERE REC_MODULOS_FK =:idRecursos";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(CivRecursos.class);
        query.setBigDecimal("idRecursos", new BigDecimal(idRecursos));
        if (query.list().size() > 0) {
            return query.list();
        }
        session.close();
        return null;
    }

}
