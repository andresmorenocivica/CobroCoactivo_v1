/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivDeudas;
import java.util.Date;
import java.util.List;

/**
 *
 * @author emadrid
 */
public interface ITDeudas extends ITGeneryHibernateDao<CivDeudas, Integer>{

    public List<CivDeudas> listarDeudas(int Id_personas) throws Exception;

    public List<CivDeudas> listarDeudasByRefencia(String refencia) throws Exception;

    public List<CivDeudas> listarDeudasByTipo(int tipoDeudas) throws Exception;
    
    public List<CivDeudas> listarDeudasByFechaAdquisicion(Date fechaInicial,Date fechaFinal) throws Exception ;
    public List<CivDeudas> listarDeudasByFechaDeuda(Date fechaInicial,Date fechaFinal) throws Exception ;
    
    public long countDeudas(long idPlanTrabajo) throws Exception;
    
    public List<CivDeudas> listarDeudasByPlanTrabajo(int idPlanTrabajo) throws Exception;
    
    public long countDeudasEtapa(int idPlanTrabajo,int idEtapaTrabajo) throws Exception;
    
    public long countDeudasEtapaFases(int idPlanTrabajo,int idEtapaTrabajo,int idFaseTrabajo) throws Exception;

}