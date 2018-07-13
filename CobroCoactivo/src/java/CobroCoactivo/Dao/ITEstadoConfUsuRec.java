/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivEstadoConfusurec;
import java.math.BigDecimal;

/**
 *
 * @author emadrid
 */
public interface ITEstadoConfUsuRec extends ITGeneryHibernateDao<CivEstadoConfusurec, Integer> {

    public CivEstadoConfusurec getEstadoConfUsuRec(BigDecimal idEstado) throws Exception;

}
