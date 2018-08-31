/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivExcepciones;
import org.hibernate.Session;

/**
 *
 * @author emadridp
 */
public interface ITExcepciones extends ITGeneryHibernateDao<CivExcepciones, Integer> {

    public CivExcepciones getExcepciones(Session session, String numRadicado) throws Exception;

}
