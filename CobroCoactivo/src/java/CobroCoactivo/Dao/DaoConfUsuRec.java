/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivConfUsuRec;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author ing_jefreypadilla
 */
public class DaoConfUsuRec extends ImpGeneryHibernateDao<CivConfUsuRec, Integer> implements ITConfUsuRec {

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
    public List<CivConfUsuRec> getConfUsuRec(Session session, int id) throws Exception {
        String hql = "from CivConfUsuRec where civRecursos.recId=:id and civEstadoConfusurec.estconfusurecId = 1";
        Query query = session.createQuery(hql);
        query.setInteger("id", id);
        if (query.list().size() > 0) {
            return query.list();
        }
        return null;
    }

    @Override
    public List<CivConfUsuRec> getConfUsuRec(Session session, int idRecurso, int idUsuario) throws Exception {
        String hql = "from CivConfUsuRec where civRecursos.recId=:idRecurso and civUsuarios.usuId =:idUsuario and civEstadoConfusurec.estconfusurecId = 1";
        Query query = session.createQuery(hql);
        query.setBigDecimal("idRecurso", new BigDecimal(idRecurso));
        query.setBigDecimal("idUsuario", new BigDecimal(idUsuario));
        if (query.list().size() > 0) {
            return query.list();
        }
        return null;
    }

    @Override
    public List<CivConfUsuRec> getConfUsuRecByUser(Session session, int idUsuario) throws Exception {
        String hql = "from CivConfUsuRec where civUsuarios.usuId =:idUsuario";
        Query query = session.createQuery(hql);
        query.setBigDecimal("idUsuario", new BigDecimal(idUsuario));
        if (query.list().size() > 0) {
            return query.list();
        }
        return null;
    }
}
