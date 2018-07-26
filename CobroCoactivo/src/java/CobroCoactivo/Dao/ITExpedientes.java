/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivExpedientes;

/**
 *
 * @author emadrid
 */
public interface ITExpedientes extends ITGeneryHibernateDao<CivExpedientes, Integer> {

    public CivExpedientes getCivExpedientes(String referencia) throws Exception;

}
