/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivRecursos;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author emadrid
 */
public interface ITRecursos extends ITGeneryHibernateDao<CivRecursos, Integer> {

    public List<CivRecursos> getRecursos(Session session, int idModulo, int idUsuario) throws Exception;

    public List<CivRecursos> getListCivRecursos(Session session, int idModulo, int idUsuario) throws Exception;

    public CivRecursos getRecursos(Session session,int idRecurso) throws Exception;

}
