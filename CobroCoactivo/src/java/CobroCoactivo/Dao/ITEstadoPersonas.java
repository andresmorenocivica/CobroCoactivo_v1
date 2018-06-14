/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.Persistencia.CivEstadoPersonas;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author emadrid
 */
public interface ITEstadoPersonas {

    public CivEstadoPersonas getEstadoPersona(BigDecimal tipoDocumento) throws Exception;

    public List<CivEstadoPersonas> listAll() throws Exception;

}
