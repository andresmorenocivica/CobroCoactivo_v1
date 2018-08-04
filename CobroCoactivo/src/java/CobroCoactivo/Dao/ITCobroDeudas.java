/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivCobroDeudas;
import org.hibernate.Session;

/**
 *
 * @author emadrid
 */
public interface ITCobroDeudas extends ITGeneryHibernateDao<CivCobroDeudas, Integer> {

    public CivCobroDeudas getCobroDeudas(Session session , int idCobroDeuda) throws Exception;

}
