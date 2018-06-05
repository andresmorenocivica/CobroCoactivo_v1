/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import java.util.List;
import CobroCoactivo.Persistencia.CivEstadoUsuarios;
import org.hibernate.Session;

/**
 *
 * @author amoreno
 */
public interface ITEstadoUsuarios {
    
    public long insert(Session session,CivEstadoUsuarios estadousuarios)throws Exception;
    
    public boolean update(Session session,CivEstadoUsuarios estadousuarios)throws Exception;
    
    public List<CivEstadoUsuarios> listAll(Session session)throws Exception;
    
    public CivEstadoUsuarios consultarModuloById(Session session,int id) throws Exception;
}