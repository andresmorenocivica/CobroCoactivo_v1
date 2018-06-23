/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivDeudas;
import java.util.List;

/**
 *
 * @author emadrid
 */
public interface ITDeudas extends ITGeneryHibernateDao<CivDeudas, Integer>{

    public List<CivDeudas> listarDeudas(int Id_personas) throws Exception;

    public List<CivDeudas> listarDeudasByRefencia(String refencia) throws Exception;

    public List<CivDeudas> listarDeudasByTipo(int tipoDeudas) throws Exception;
    
    public List<CivDeudas> listarDeudasByFechaAdquisicion(String fechaInicial,String fechaFinal) throws Exception ;

}