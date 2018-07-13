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
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author emadrid
 */
public class DaoRecursos extends ImpGeneryHibernateDao<CivRecursos, Integer> implements ITRecursos {

    @Override
    public List<CivRecursos> getRecursos(int idModulo, int idUsuario) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        //String sql = "SELECT * FROM CIV_RECURSOS WHERE REC_MODULOS_FK =:idModulo";
        String sql = "SELECT * \n"
                + "FROM CIV_RECURSOS \n"
                + "WHERE REC_MODULOS_FK= :idModulo AND REC_ID IN (SELECT CONFUSUREC_RECURSO_FK FROM CIV_CONF_USU_REC WHERE CONFUSUREC_USUARIOS_FK = :idUsuario AND CONFUSUREC_ESTADO_FK = 1)";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(CivRecursos.class);
        query.setBigDecimal("idModulo", new BigDecimal(idModulo));
        query.setBigDecimal("idUsuario", new BigDecimal(idUsuario));
        if (query.list().size() > 0) {
            return query.list();
        }
        session.close();
        return null;
    }

    @Override
    public List<CivRecursos> getListCivRecursos(int idModulo, int idUsuario) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "SELECT r.* \n"
                + "FROM CIV_RECURSOS r\n"
                + "WHERE REC_MODULOS_FK= :idModulo\n"
                + "AND REC_ID NOT IN (SELECT CONFUSUREC_RECURSO_FK FROM CIV_CONF_USU_REC WHERE CONFUSUREC_USUARIOS_FK = :idUsuario AND CONFUSUREC_ESTADO_FK = 1)\n"
                + "AND REC_ESTADO_FK = 1";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(CivRecursos.class);
        query.setBigDecimal("idModulo", new BigDecimal(idModulo));
        query.setBigDecimal("idUsuario", new BigDecimal(idUsuario));
        if (query.list().size() > 0) {
            return query.list();
        }
        session.close();
        return null;
    }

    @Override
    public CivRecursos getRecursos(int idRecurso) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from CivRecursos where recId=:idRecurso";
        Query query = session.createQuery(hql);
        query.setInteger("idRecurso", idRecurso);
        if (query.list().size() > 0) {
            return (CivRecursos) query.list().get(0);
        }
        session.close();
        return null;
    }

}
