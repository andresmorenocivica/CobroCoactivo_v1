/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivModulos;
import java.util.List;

/**
 *
 * @author emadrid
 */
public interface ITModulos extends ITGeneryHibernateDao<CivModulos, Integer> {
    
    public List<CivModulos> getModulosByUsuario(int idUsuario) throws Exception;
}
