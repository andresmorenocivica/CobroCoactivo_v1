/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivValorTipoDetalleCobro;
import org.hibernate.Session;

/**
 *
 * @author emadrid
 */
public interface ITValorTipoDetalleCobro extends ITGeneryHibernateDao<CivValorTipoDetalleCobro, Integer> {

    public CivValorTipoDetalleCobro cargarValorDetalle(Session session, int ValorTipo) throws Exception;

}
