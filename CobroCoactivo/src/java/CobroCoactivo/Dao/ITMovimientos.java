/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivMovimientos;
import java.util.List;

/**
 *
 * @author emadrid
 */
public interface ITMovimientos extends ITGeneryHibernateDao<CivMovimientos, Integer> {
    
    public List<CivMovimientos> listMovimientos (int idMovimientos) throws Exception;

}
