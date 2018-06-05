/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.Persistencia.CivConfUsuRec;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ing_jefreypadilla
 */
public class DaoConfUsuRec implements ITConfUsuRec {

    @Override

    @Transactional(rollbackFor = Exception.class)
    public int insert(Session session, CivConfUsuRec civConfUsuRec) throws Exception {
        return Integer.parseInt(session.save(civConfUsuRec).toString());
    }

    @Override

    @Transactional(rollbackFor = Exception.class)
    public boolean update(Session session, CivConfUsuRec civConfUsuRec) throws Exception {
        session.update(civConfUsuRec);
        return true;
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

}
