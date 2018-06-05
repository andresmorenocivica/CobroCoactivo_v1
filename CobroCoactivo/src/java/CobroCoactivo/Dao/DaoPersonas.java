/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.Persistencia.CivPersonas;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author JefreySistemas
 */
public class DaoPersonas implements ITPersonas {

    @Override
    public CivPersonas consultarPersonasById(Session session, int per_id) throws Exception {
        return (CivPersonas) session.createCriteria(CivPersonas.class)
                .add(Restrictions.eq("perId", new BigDecimal(per_id)))
                .uniqueResult();
    }

    @Override
    public CivPersonas consultarPersonasByDocumento(Session session, int tipo, String nro_documento) throws Exception {
        String hql = "from CivPersonas where civTipodocumentos.tipdocCodigo =:tipo and perDocumento=:nro_documento";
        Query query = session.createQuery(hql);
        query.setParameter("tipo", tipo);
        query.setParameter("nro_documento", nro_documento);
        return (CivPersonas) query.uniqueResult();
    }

    @Override

    public List<CivPersonas> listarPersonas(Session session, String persona) throws Exception {
        String hql = "from CivPersonas where Per_Nombre1 like :persona or Per_Nombre2 like :persona or Per_Apellido1 like :persona or Per_Apellido2 like :persona ORDER BY 1 asc";
        SQLQuery query = session.createSQLQuery(hql);
        query.addEntity(CivPersonas.class);
        query.setString("persona", persona);
        if (query.list().size() > 0) {
            return query.list();
        }
        return null;
    }

    @Override

    public List<CivPersonas> listarPersonasFecha(Session session, String fecha) throws Exception {

        String hql = "from CivPersonas where per_fechainicial = :fecha order by 1 asc";
        SQLQuery query = session.createSQLQuery(hql);
        query.addEntity(CivPersonas.class);
        query.setString("fecha", fecha);
        if (query.list().size() > 0) {
            return query.list();
        }
        return null;

    }

    @Override
    public CivPersonas consultarPersonasDocumento(Session session, String nro_documento) throws Exception {
        return (CivPersonas) session.createCriteria(CivPersonas.class)
                .add(Restrictions.eq("perDocumento", nro_documento))
                .uniqueResult();
    }

    /**
     *
     * @param per
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public long insert(Session session, CivPersonas civPersonas) throws Exception {
         return Integer.parseInt(session.save(civPersonas).toString());
    }

    /**
     *
     * @param per
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Session session, CivPersonas civPersonas) throws Exception {
        session.update(civPersonas);
        return true;
    }

}
