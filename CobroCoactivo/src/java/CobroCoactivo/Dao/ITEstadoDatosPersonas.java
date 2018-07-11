/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivEstadoDatosPersonas;
import java.math.BigDecimal;

/**
 *
 * @author emadrid
 */
public interface ITEstadoDatosPersonas extends ITGeneryHibernateDao<CivEstadoDatosPersonas, Integer>{
    
    public CivEstadoDatosPersonas getEstadoDatosPersonas(BigDecimal idEstado) throws Exception;
}
