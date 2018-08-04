/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivEstadoPersonas;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author emadrid
 */
public interface ITEstadoPersonas  extends ITGeneryHibernateDao<CivEstadoPersonas,Integer>{

    public CivEstadoPersonas getEstadoPersona(BigDecimal tipoDocumento) throws Exception;

    public List<CivEstadoPersonas> listAll(Session session) throws Exception;

}
