/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanCargueArchivoDeudas;
import CobroCoactivo.Modelo.Personas;
import CobroCoactivo.Persistencia.CivDeudasImpuesto;
import CobroCoactivo.Persistencia.CivPlanTrabajos;
import java.math.BigDecimal;

/**
 *
 * @author AMORENO
 */
public interface CargueArchivoDeudasBO {
    void procesarCargueArchivoDeudas(BeanCargueArchivoDeudas beanCargueArchivoDeudas)throws Exception;
}
