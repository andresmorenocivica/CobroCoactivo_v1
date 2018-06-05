/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.General;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Utility.HibernateUtil;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jvergara
 */
public class ImpGeneryHibernateDao<T, ID extends Serializable> implements ITGeneryHibernateDao<T, ID> {

    SessionFactory sessionFactory;

    public ImpGeneryHibernateDao() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public  void create(T entity) throws Exception {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(T entity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
    }

    @Override
    public T find(ID id) {
        T entity = null;
        return  entity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(ID id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        T entity = (T) session.get(getEntityClass(), id);
        session.delete(entity);
        session.getTransaction().commit();

    }

    @Override
    public List<T> findAll() {
        Session session = sessionFactory.openSession();
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

}