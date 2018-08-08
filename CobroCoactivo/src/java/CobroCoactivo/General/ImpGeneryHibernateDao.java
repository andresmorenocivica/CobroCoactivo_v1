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
    public void create(Session session, T entity) throws Exception {
        session.save(entity);

    }

    @Override
    public void update(Session session, T entity) {
        session.merge(entity);
    }

    @Override
    public T find(Session session, BigDecimal id) {
        T entity = (T) session.get(getEntityClass(), id);
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
    public List<T> findAll(Session session) {
        Query query = session.createQuery("select e from " + getEntityClass().getName() + " e");
        List<T> entities = query.list();
        return entities;
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
