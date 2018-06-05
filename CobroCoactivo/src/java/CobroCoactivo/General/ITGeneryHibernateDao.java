/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.General;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author root
 */
public interface ITGeneryHibernateDao<T, ID extends Serializable> {
    
    
     void create(T entity) throws Exception;
     
     void update(T entity) throws Exception;
     
     T find(ID id) throws Exception;
     
     void remove(ID id) throws Exception ;
     
     List<T> findAll() throws Exception;
     
     List<T> findByIdList() throws Exception;
    
}
