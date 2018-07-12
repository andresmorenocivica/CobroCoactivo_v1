/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivEtapasTrabajos;
import java.util.List;

/**
 *
 * @author AMORENO
 */
public interface ITEtapasTrabajo extends ITGeneryHibernateDao<CivEtapasTrabajos,Integer>{
 
     public List<CivEtapasTrabajos> listarEtapasTrabajoByPlantrabajo(int idPlanTrabajo) throws Exception;
     
     CivEtapasTrabajos find(int id) throws Exception;
}
