/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanGestionMovimientos;
import java.util.List;

/**
 *
 * @author AMORENO
 */
public interface GestionMovimientosBO {
    void cargarListadoPlanesTrabajo(BeanGestionMovimientos beanGestionMovimientos)throws Exception;
    //void cargarListadoDeudas(BeanGestionMovimientos beanGestionMovimientos) throws Exception;
    void cargarEtapasPlanTrabajo(BeanGestionMovimientos beanGestionMovimientos)throws Exception;
    void cargarFasesTrabajo(BeanGestionMovimientos beanGestionMovimientos)throws Exception;
    void generarMovimiento(BeanGestionMovimientos beanGestionMovimientos)throws Exception;
}
