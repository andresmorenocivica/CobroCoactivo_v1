/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivEtapasGenerales;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jvergara
 */
public interface ITEstapaGeneral extends ITGeneryHibernateDao<CivEtapasGenerales, Integer>{
    
        List<CivEtapasGenerales> findAllEtapaByIdPlanGeneral(int id) throws Exception ;
    
}
