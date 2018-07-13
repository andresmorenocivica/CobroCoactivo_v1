/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivEstructuraPlanos;

/**
 *
 * @author AMORENO
 */
public interface ITEstructuraPlanos extends ITGeneryHibernateDao<CivEstructuraPlanos, Integer>{
    CivEstructuraPlanos getEstructuraPlano(int tipo,int indiceCampo) throws Exception;
}
