/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivSolicitudes;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author emadridp
 */
public interface ITSolicitudes extends ITGeneryHibernateDao<CivSolicitudes, Integer> {

    public List<CivSolicitudes> getCivSolicitudesPendientes(Session session) throws Exception;
}
