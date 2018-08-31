/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivFasesTrabajos;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author AMORENO
 */
public interface ITFasesTrabajo extends ITGeneryHibernateDao<CivFasesTrabajos, Integer> {

    public List<CivFasesTrabajos> listarFasesTrabajo(Session session , int idEtapaTrabajo) throws Exception;
    
}
