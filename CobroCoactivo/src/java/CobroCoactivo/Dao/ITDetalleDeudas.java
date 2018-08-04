/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivDetalleDeudas;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author AMORENO
 */
public interface ITDetalleDeudas extends ITGeneryHibernateDao<CivDetalleDeudas,Integer>{
    
    List<CivDetalleDeudas> getListDetallesDeudasByidDeuda(Session session, long id) throws Exception;
    
}
