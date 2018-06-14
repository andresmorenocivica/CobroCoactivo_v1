/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.Persistencia.CivDatosPersonas;
import java.util.List;

/**
 *
 * @author emadrid
 */
public interface ITDatosPersonas {
    
     public List<CivDatosPersonas> listarDatosPersonas (int  Id_personas) throws Exception;
    
}
