/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivTipoDeudas;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author emadrid
 */
public interface ITTipoDeudas extends ITGeneryHibernateDao<CivTipoDeudas,Integer>{
    
    public List<CivTipoDeudas> listAll() throws Exception;
    
    
    public CivTipoDeudas getTipoDeuda(BigDecimal tipdeuId) throws Exception ;
      
    
}
