/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivRecursos;
import java.util.List;

/**
 *
 * @author emadrid
 */
public interface ITRecursos extends ITGeneryHibernateDao<CivRecursos, Integer>{
    
    public List<CivRecursos> getRecursos(int idRecursos) throws Exception;
    
}
