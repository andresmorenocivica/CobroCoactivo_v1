/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivExpedientes;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author emadrid
 */
public interface ITExpedientes extends ITGeneryHibernateDao<CivExpedientes, Integer> {

    public CivExpedientes getCivExpedientes(Session session, String referencia) throws Exception;

    public CivExpedientes getCivExpedientesByUri(Session session, String ubicacion) throws Exception;

}
