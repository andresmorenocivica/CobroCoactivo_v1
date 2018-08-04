/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivConfUsuRec;
import CobroCoactivo.Utility.HibernateUtil;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ing_jefreypadilla
 */
public class DaoConfUsuRec extends ImpGeneryHibernateDao<CivConfUsuRec, Integer> implements ITConfUsuRec {

    @Override

    @Transactional(rollbackFor = Exception.class)
    public int insert(Session session, CivConfUsuRec civConfUsuRec) throws Exception {
        return Integer.parseInt(session.save(civConfUsuRec).toString());
    }

   

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Session session, CivConfUsuRec civConfUsuRec){
        session.update(civConfUsuRec);
       
    }

    @Override

    public List<CivConfUsuRec> listPerfilRecursoByIDUsuario(Session session, long idusuario) throws Exception {
        String hql = "from CivConfUsuRec where civUsuarios.usuId =:id_usuario";
        Query query = session.createQuery(hql);
        query.setParameter("id_usuario", new BigDecimal(idusuario));
        if (query.list().size() > 0) {
            return query.list();
        }
        return null;
    }

    @Override

    public List<CivConfUsuRec> listPerfilRecurso(Session session) throws Exception {
        return session.createCriteria(CivConfUsuRec.class).list();
    }

    @Override
    public List<CivConfUsuRec> getConfUsuRec(int id) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "SELECT * FROM CIV_CONF_USU_REC WHERE CONFUSUREC_RECURSO_FK = :id AND CONFUSUREC_ESTADO_FK = 1";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(CivConfUsuRec.class);
        query.setInteger("id", id);
        if (query.list().size() > 0) {
            return query.list();
        }
        session.close();
        return null;
    }

    @Override
    public List<CivConfUsuRec> getConfUsuRec(int idRecurso, int idUsuario) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "SELECT * FROM CIV_CONF_USU_REC WHERE CONFUSUREC_RECURSO_FK =:idRecurso AND CONFUSUREC_USUARIOS_FK =:idUsuario";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(CivConfUsuRec.class);
        query.setBigDecimal("idRecurso", new BigDecimal(idRecurso));
        query.setBigDecimal("idUsuario", new BigDecimal(idUsuario));
        if (query.list().size() > 0) {
            return query.list();
        }
        session.close();
        return null;
    }

    @Override
    public List<CivConfUsuRec> getConfUsuRecByUser(int idUsuario) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "SELECT * FROM CIV_CONF_USU_REC WHERE CONFUSUREC_USUARIOS_FK =:idUsuario";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(CivConfUsuRec.class);
        query.setBigDecimal("idUsuario", new BigDecimal(idUsuario));
        if (query.list().size() > 0) {
            return query.list();
        }
        session.close();
        return null;
    }
}
