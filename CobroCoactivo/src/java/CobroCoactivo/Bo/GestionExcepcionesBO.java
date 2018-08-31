/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanGestionExcepciones;

/**
 *
 * @author emadridp
 */
public interface GestionExcepcionesBO {

    void buscarExcepcion(BeanGestionExcepciones bean) throws Exception;

    void buscarPersona(BeanGestionExcepciones bean) throws Exception;

    void guardarExcepcion(BeanGestionExcepciones bean) throws Exception;

    void llenarListaDeudaSeleccionada(BeanGestionExcepciones bean) throws Exception;
}
