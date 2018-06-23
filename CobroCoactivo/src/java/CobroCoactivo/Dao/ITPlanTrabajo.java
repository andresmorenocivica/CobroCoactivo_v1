/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivPlanGenerales;
import CobroCoactivo.Persistencia.CivPlanTrabajos;

/**
 *
 * @author AMORENO
 */
public interface ITPlanTrabajo extends ITGeneryHibernateDao<CivPlanTrabajos,Integer>{
    public CivPlanTrabajos getPlanTrabajo(int idPlanTrabajo)throws Exception;
}
