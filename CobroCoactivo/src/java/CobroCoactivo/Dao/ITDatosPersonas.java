/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivDatosPersonas;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author emadrid
 */
public interface ITDatosPersonas extends ITGeneryHibernateDao<CivDatosPersonas, Integer>{
    
     public List<CivDatosPersonas> listarDatosPersonas (Session session , int  Id_personas) throws Exception;
    
}
