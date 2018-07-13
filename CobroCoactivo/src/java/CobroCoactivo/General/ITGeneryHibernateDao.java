/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.General;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author root
 */
public interface ITGeneryHibernateDao<T, ID extends Serializable> {

    public void create(T entity) throws Exception;
    
    public void update(T entity) throws Exception;

    public T find(BigDecimal id) throws Exception;

    public void remove(ID id) throws Exception;

    public List<T> findAll() throws Exception;

    public List<T> findByIdList() throws Exception;

}
