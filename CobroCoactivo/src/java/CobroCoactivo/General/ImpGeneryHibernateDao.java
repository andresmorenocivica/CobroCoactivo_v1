/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.General;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivDeudas;
import CobroCoactivo.Persistencia.CivEtapasGenerales;
import CobroCoactivo.Persistencia.CivModulos;
import CobroCoactivo.Persistencia.CivPlanGenerales;
import CobroCoactivo.Persistencia.CivPlanTrabajos;
import CobroCoactivo.Persistencia.CivRecursos;
import CobroCoactivo.Persistencia.CivTipoDatosPersonas;
import CobroCoactivo.Utility.HibernateUtil;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jvergara
 * @param <T>
 */
public class ImpGeneryHibernateDao<T, ID extends Serializable> implements ITGeneryHibernateDao<T, ID> {

    private SessionFactory sessionFactory;

    public ImpGeneryHibernateDao() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(T entity) throws Exception {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        session.close();

    }
    
    @Override
    public void update(Session session,T entity) {
        session.merge(entity);
    }

    @Override
    public T find(BigDecimal id) {
        Session session = session = getSessionFactory().openSession();
        T entity = (T) session.get(getEntityClass(), id);
        session.close();
        return entity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(ID id) {
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        T entity = (T) session.get(getEntityClass(), id);
        session.delete(entity);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public List<T> findAll() {
        Session session = null;
        try {
            session = getSessionFactory().openSession();
            Query query = session.createQuery("select e from " + getEntityClass().getName() + " e");
            List<T> entities = query.list();

            if (getEntityClass().getName().contains("CivPlanGeneral")) {
                for (CivPlanGenerales entity : (List<CivPlanGenerales>) entities) {
                    Hibernate.initialize(entity.getCivEstadoPlanGenerales());
                }
            }
            if (getEntityClass().getName().contains("CivTipoDatosPersonas")) {
                for (CivTipoDatosPersonas entity : (List<CivTipoDatosPersonas>) entities) {
                    Hibernate.initialize(entity.getCivEstadoTipoDatosPersonas());
                }
            }

            if (getEntityClass().getName().contains("CivPlanTrabajos")) {
                for (CivPlanTrabajos entity : (List<CivPlanTrabajos>) entities) {
                    Hibernate.initialize(entity.getCivEstadoPlanTrabajos());
                }
            }
            if (getEntityClass().getName().contains("CivPlanTrabajos")) {
                for (CivPlanTrabajos entity : (List<CivPlanTrabajos>) entities) {
                    Hibernate.initialize(entity.getCivEstadoPlanTrabajos());
                }
            }
            if (getEntityClass().getName().contains("CivDeudas")) {
                for (CivDeudas entity : (List<CivDeudas>) entities) {
                    Hibernate.initialize(entity.getCivEstadoDeudas());
                    Hibernate.initialize(entity.getCivTipoDeudas());
                    Hibernate.initialize(entity.getCivPlanTrabajos());
                }
            }
            if (getEntityClass().getName().contains("CivEtapasGenerales")) {
                for (CivEtapasGenerales entity : (List<CivEtapasGenerales>) entities) {
                    Hibernate.initialize(entity.getCivEstadoEtapasGenerales());
                }
            }

            if (getEntityClass().getName().contains("CivRecursos")) {
                for (CivRecursos entity : (List<CivRecursos>) entities) {
                    Hibernate.initialize(entity.getCivEstadoRecursos());
                    Hibernate.initialize(entity.getCivTipoRecursos());
                    Hibernate.initialize(entity.getCivModulos());
                    Hibernate.initialize(entity.getCivPerfiles());
                }
            }
            if (getEntityClass().getName().contains("CivModulos")) {
                for (CivModulos entity : (List<CivModulos>) entities) {
                    Hibernate.initialize(entity.getCivEstadoModulos());
                }
            }
            return entities;

        } finally {
            if (session != null && session.isOpen()) {
                session.flush();
                session.close();
            }
        }
    }

    @Override
    public List<T> findByIdList() {
        return null;
    }

    private Class<T> getEntityClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    

}
