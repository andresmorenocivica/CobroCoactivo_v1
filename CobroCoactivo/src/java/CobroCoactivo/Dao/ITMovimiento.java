/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivMovimientos;

/**
 *
 * @author AMORENO
 */
public interface ITMovimiento extends ITGeneryHibernateDao<CivMovimientos,Integer>{
    public CivMovimientos getMovimientoByDeudaByFaseTrabajo(int deuId, int faseTrabajo) throws Exception;
}
