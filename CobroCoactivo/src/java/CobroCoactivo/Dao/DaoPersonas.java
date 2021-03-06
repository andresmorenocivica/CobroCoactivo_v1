/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivPersonas;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author JefreySistemas
 */
public class DaoPersonas extends ImpGeneryHibernateDao<CivPersonas, Integer> implements ITPersonas {

    @Override
    public CivPersonas consultarPersonasById(Session session, int per_id) throws Exception {
        String hql = "from CivPersonas where perId =:perId";
        Query query = session.createQuery(hql);
        query.setParameter("perId", new BigDecimal(per_id));
        if (query.list().size() > 0) {
            return (CivPersonas) query.list().get(0);
        }
        return null;
    }

    @Override

    public CivPersonas consultarPersonasByDocumento(Session session, int tipo, String nro_documento) throws Exception {

        String hql = "from CivPersonas where civTipoDocumentos.tipdocCodigo =:tipo and perDocumento=:nro_documento";
        Query query = session.createQuery(hql);
        query.setParameter("tipo", new BigDecimal(tipo));
        query.setParameter("nro_documento", nro_documento);
        if (query.list().size() > 0) {
            return (CivPersonas) query.list().get(0);
        }

        return null;
    }

    @Override
    public List<CivPersonas> listarPersonas(Session session, String persona) throws Exception {
        String hql = "from CivPersonas where Per_Nombre1 like :persona or Per_Nombre2 like :persona or Per_Apellido1 like :persona or Per_Apellido2 like :persona ORDER BY 1 asc";
        Query query = session.createQuery(hql);
        query.setString("persona", persona);
        if (query.list().size() > 0) {
            return query.list();
        }
        return null;
    }

    @Override
    public List<CivPersonas> listarPersonasFecha(Session session, String fecha) throws Exception {
        String hql = "from CivPersonas where per_fechainicial = :fecha order by 1 asc";
        Query query = session.createQuery(hql);
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
}
