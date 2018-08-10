/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivPrestamoExpHistorial;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author emadridp
 */
public interface ITPrestamoExpHistorial extends ITGeneryHibernateDao<CivPrestamoExpHistorial, Integer> {

    public List<CivPrestamoExpHistorial> getCivPrestamoExpHistorial(Session session,int idUser) throws Exception;

}
