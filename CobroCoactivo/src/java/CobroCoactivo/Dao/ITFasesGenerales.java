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
import org.hibernate.Session;

/**
 *
 * @author jvergara
 */
public interface ITFasesGenerales extends ITGeneryHibernateDao<CivFasesGenerales, Integer> {

    /**
     * devuelve una lista de la etapa general
     *
     * @param session
     * @param id civfasesGenerales
     * @return una lista de etapa general
     * @throws Exception
     */
    List<CivFasesGenerales> AllListByEtapaGeneral(Session session, int id) throws Exception;

}
