/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivAttempts;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Roymer Camacho
 */
public class DaoAttempts extends ImpGeneryHibernateDao<CivAttempts, Integer> implements ITAttempts {

    /**
     *
     * @param attp
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public long insert(Session session, CivAttempts attp) throws Exception {
        return Integer.parseInt(session.save(attp).toString());
    }

    /**
     *
     * @param attp
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Session session, CivAttempts attp) throws Exception {
        session.update(attp);
        return true;
    }

    /**
     *
     * @param id_usuario
     * @return
     * @throws Exception
     */
    @Override
    public CivAttempts consultarIntentos(Session session, int id_usuario) throws Exception {
        return (CivAttempts) session.createCriteria(CivAttempts.class)
                .add(Restrictions.eq("civUsuarios.usuId", new BigDecimal(id_usuario)))
                .uniqueResult();
    }

}
