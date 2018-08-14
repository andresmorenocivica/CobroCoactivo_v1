/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivDetalleSolicitudes;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author emadridp
 */
public interface ITDetalleSolicitudes extends ITGeneryHibernateDao<CivDetalleSolicitudes, Integer> {

    public List<CivDetalleSolicitudes> getCivDetalleSolicitudes(Session session,int idSolicitud) throws Exception;
    
    public List<CivDetalleSolicitudes> getCivDetalleSolicitudes(Session session) throws Exception;

}
