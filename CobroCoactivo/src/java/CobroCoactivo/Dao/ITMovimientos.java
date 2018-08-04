/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivMovimientos;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author emadrid
 */
public interface ITMovimientos extends ITGeneryHibernateDao<CivMovimientos, Integer> {

    public List<CivMovimientos> listMovimientos(Session session , int idMovimientos) throws Exception;

    public CivMovimientos getMovimientoByDeudaByFaseTrabajo(Session session,int deuId, int faseTrabajo) throws Exception;

    /**
     *
     * @param fase
     * @return lista movimiento por fase
     * @throws Exception
     */
    List<CivMovimientos> listMovimiento(Session session,int fase) throws Exception;

    public List<CivMovimientos> getMovimientoByUser(int idUsuario) throws Exception;

}
