/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivDetalleCobroDeudas;
import java.util.List;

/**
 *
 * @author emadrid
 */
public interface ITDetalleCobroDeudas extends ITGeneryHibernateDao<CivDetalleCobroDeudas, Integer> {
    
    public List<CivDetalleCobroDeudas> listarDetalleCobroDeudas(int idCobro) throws Exception ;
}
