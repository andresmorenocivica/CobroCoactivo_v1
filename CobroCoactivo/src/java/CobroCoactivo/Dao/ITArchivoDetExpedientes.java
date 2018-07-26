/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivArchivoDetExpedientes;
import java.util.List;

/**
 *
 * @author emadrid
 */
public interface ITArchivoDetExpedientes extends ITGeneryHibernateDao<CivArchivoDetExpedientes, Integer> {

    public List<CivArchivoDetExpedientes> getCivArchivoDetExpedientes(int idDetExpediente) throws Exception;
}
