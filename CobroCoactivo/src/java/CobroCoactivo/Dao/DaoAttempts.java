/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivAttempts;
import java.math.BigDecimal;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Roymer Camacho
 */
public class DaoAttempts extends ImpGeneryHibernateDao<CivAttempts, Integer> implements ITAttempts {

    @Override
    public CivAttempts consultarIntentosByUser(Session session, int idUsuario) throws Exception {
        String hql = "from CivAttempts where civUsuarios.usuId =:idUsuario";
        Query query = session.createQuery(hql);
        query.setParameter("idUsuario", new BigDecimal(idUsuario));
        if (query.list().size() > 0) {
            return (CivAttempts) query.list().get(0);
        }
        return null;
    }

}
