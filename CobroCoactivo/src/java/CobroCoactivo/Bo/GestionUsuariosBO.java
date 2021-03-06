/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanGestionUsuarios;

/**
 *
 * @author emadrid
 */
public interface GestionUsuariosBO {

    /**
     *
     * @param bean
     * @throws Exception
     */
    void consultarUsuario(BeanGestionUsuarios bean) throws Exception;

    void consultarPersona(BeanGestionUsuarios bean) throws Exception;

    void actualizarContraseña(BeanGestionUsuarios bean) throws Exception;

    void cargarDatosPersonas(BeanGestionUsuarios bean) throws Exception;

    void cargarTipoDocumento(BeanGestionUsuarios bean) throws Exception;

    void cargarModulosByUsuario(BeanGestionUsuarios bean) throws Exception;

    void cargarTodosModulos(BeanGestionUsuarios bean) throws Exception;

    void cargarRecursoByModulo(BeanGestionUsuarios bean) throws Exception;

    void guardarRecursoUsuario(BeanGestionUsuarios bean) throws Exception;

    void registrarUsuario(BeanGestionUsuarios bean) throws Exception;

    void cargarConfUsuRec(BeanGestionUsuarios bean) throws Exception;

    void updateRecursoUsuario(BeanGestionUsuarios bean) throws Exception;

    void cargarHistorialConfUsuRec(BeanGestionUsuarios bean) throws Exception;

    void cargarMovimientoByUser(BeanGestionUsuarios bean) throws Exception;

    void cargarEstadoUsuario(BeanGestionUsuarios bean) throws Exception;

    void generarContrasena(BeanGestionUsuarios bean) throws Exception;

    void actualizarUser(BeanGestionUsuarios bean) throws Exception;

    void cargarHistorialExpPrestado(BeanGestionUsuarios bean) throws Exception; // metodo que se utiliza para los historiales de los expediente por user
}
