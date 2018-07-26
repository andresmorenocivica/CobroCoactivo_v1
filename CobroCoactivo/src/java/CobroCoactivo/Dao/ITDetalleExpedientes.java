/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivDetalleExpedientes;
import java.util.List;

/**
 *
 * @author emadrid
 */
public interface ITDetalleExpedientes extends ITGeneryHibernateDao<CivDetalleExpedientes, Integer> {

    public List<CivDetalleExpedientes> getListCivDetalleExpediente(int idExpediente) throws Exception;
}
