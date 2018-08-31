/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivTipoDeudas;
import java.math.BigDecimal;
import org.hibernate.Session;

/**
 *
 * @author emadrid
 */
public interface ITTipoDeudas extends ITGeneryHibernateDao<CivTipoDeudas, Integer> {

    public CivTipoDeudas getTipoDeuda(Session session, BigDecimal tipdeuId) throws Exception;

}
