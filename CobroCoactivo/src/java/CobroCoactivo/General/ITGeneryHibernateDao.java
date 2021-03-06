/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.General;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author root
 */
public interface ITGeneryHibernateDao<T, ID extends Serializable> {

    public void create(Session session, T entity) throws Exception;

    public void update(Session session, T entity) throws Exception;

    public T find(Session session, BigDecimal id) throws Exception;

    public List<T> findAll(Session session) throws Exception;

    public List<T> findByIdList() throws Exception;

}
