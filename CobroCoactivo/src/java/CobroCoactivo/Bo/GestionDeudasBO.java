/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanGestionDeudas;
import CobroCoactivo.Modelo.Deudas;

/**
 *
 * @author emadrid
 */
public interface GestionDeudasBO {

    void buscarDeudas(BeanGestionDeudas bean) throws Exception;

    void cargarListaTipoDeudas(BeanGestionDeudas bean) throws Exception;

    void cargarListaCobroDeudas(BeanGestionDeudas bean) throws Exception;

    Deudas cargarProcentajeCobro(Deudas deudas) throws Exception;

    void actualizarDeudaCargada(BeanGestionDeudas bean) throws Exception;

}
