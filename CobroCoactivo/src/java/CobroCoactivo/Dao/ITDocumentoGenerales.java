/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivDocumenGenerales;
import java.io.Serializable;
import org.hibernate.Session;

/**
 *
 * @author jvergara
 */
public interface ITDocumentoGenerales extends ITGeneryHibernateDao<CivDocumenGenerales, Integer>{
    
    CivDocumenGenerales getCivDocumentoGeneral(Session session , int idDocumentoGeneral) throws Exception ;
}
