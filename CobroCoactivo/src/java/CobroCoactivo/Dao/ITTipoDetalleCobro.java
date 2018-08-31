/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivTipoDetalleCobro;
import org.hibernate.Session;

/**
 *
 * @author emadrid
 */
public interface ITTipoDetalleCobro extends ITGeneryHibernateDao<CivTipoDetalleCobro, Integer> {

    public CivTipoDetalleCobro cargarTipoDetalleCobro(Session session, int tipoDetalle) throws Exception;

}
