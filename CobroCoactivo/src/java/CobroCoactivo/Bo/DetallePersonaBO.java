/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanDetallePersonas;

/**
 *
 * @author emadrid
 */
public interface DetallePersonaBO {

    void cargarTipoDocumento(BeanDetallePersonas bean) throws Exception;

    void cargarDatosPersonas(BeanDetallePersonas bean) throws Exception;
}
