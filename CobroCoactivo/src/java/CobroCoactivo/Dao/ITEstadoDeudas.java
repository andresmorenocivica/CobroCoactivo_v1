/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivEstadoDeudas;
import java.math.BigDecimal;
import org.hibernate.Session;

/**
 *
 * @author emadrid
 */
public interface ITEstadoDeudas extends ITGeneryHibernateDao<CivEstadoDeudas, Integer> {
    
    public CivEstadoDeudas getEstadoDeudas (Session session , BigDecimal estadoDeudas) throws Exception;
    
}
