/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivFasesGenerales;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jvergara
 */
public interface ITFasesGenerales extends ITGeneryHibernateDao<CivFasesGenerales, Integer>{
    
    
    List<CivFasesGenerales> AllListByEtapaGeneral(int id) throws Exception;
    
}
