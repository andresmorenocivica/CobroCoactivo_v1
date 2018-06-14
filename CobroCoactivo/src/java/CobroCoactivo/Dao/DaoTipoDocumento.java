/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package CobroCoactivo.Dao;

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
public class DaoTipoDocumento implements ITTipoDocumento {

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
    public List<CivTipoDocumentos> listAll() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from CivTipoDocumentos";
        Query query = session.createQuery(hql);
        if (query.list().size() > 0) {
            return query.list();
        }
        session.close();
        return null;
    }

    @Override
    public CivTipoDocumentos getTipoDocumento(BigDecimal codigoDocumento) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from CivTipoDocumentos where tipdocId=:codigoDocumento";
        Query query = session.createQuery(hql);
        query.setParameter("codigoDocumento",codigoDocumento);
        if (query.list().size() > 0) {
            return (CivTipoDocumentos) query.list().get(0);
        }
        session.close();
        return null;
    }
}
