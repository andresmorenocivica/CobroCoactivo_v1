/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivTipoDocumentos;
import CobroCoactivo.Utility.HibernateUtil;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Roymer Camacho
 */
public class DaoTipoDocumento extends ImpGeneryHibernateDao<CivTipoDocumentos, Integer> implements ITTipoDocumento {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public long insert(CivTipoDocumentos civTipodocumentos) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        int id = Integer.parseInt(session.save(civTipodocumentos).toString());
        session.close();
        return id;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(CivTipoDocumentos civTipodocumentos) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.update(civTipodocumentos);
        session.close();
        return true;
    }

    @Override
    @Transactional
    public List<CivTipoDocumentos> listAll(Session session) throws Exception {
        String hql = "from CivTipoDocumentos";
        Query query = session.createQuery(hql);
        if (query.list().size() > 0) {
            return query.list();
        }
        return null;
    }

    @Override
    public CivTipoDocumentos getTipoDocumento(Session session, BigDecimal codigoDocumento) throws Exception {
        String hql = "from CivTipoDocumentos where tipdocCodigo=:codigoDocumento";
        Query query = session.createQuery(hql);
        query.setParameter("codigoDocumento", codigoDocumento);
        if (query.list().size() > 0) {
            return (CivTipoDocumentos) query.list().get(0);
        }
        return null;
    }

}
