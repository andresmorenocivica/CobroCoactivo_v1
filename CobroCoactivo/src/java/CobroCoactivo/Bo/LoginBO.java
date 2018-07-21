/*
 * To change this template, choose Tools | Templates
 * Copyright 2016.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanLogin;
import CobroCoactivo.Modelo.Modulos;
import java.util.List;

/**
 *
 * @author Jefrey Padilla
 */
public interface LoginBO {

    /**
     *
     * @param obj
     * @throws java.lang.Exception
     */
    void iniciarSesion(BeanLogin obj) throws Exception;

    /**
     *
     * @param obj
     * @return
     * @throws java.lang.Exception
     */
    List<Modulos> listarModulos(BeanLogin obj) throws Exception;

    /**
     *
     * @return @throws Exception
     */
    public String generarContrasena() throws Exception;

    /**
     *
     * @param obj
     * @return
     * @throws java.lang.Exception
     */
    void listarPerfilRecursos(BeanLogin obj) throws Exception;

    /**
     *
     * @param obj
     * @param tipo
     * @return
     * @throws java.lang.Exception
     */
    /**
     *
     * @param obj
     * @return
     * @throws Exception
     */
    List<String> listarRecursos(BeanLogin obj) throws Exception;

    public List<Modulos> listarModulos(BeanLogin obj, int tipo) throws Exception;

    void consultarDatosUsuario(BeanLogin obj) throws Exception;

    void actualizarContraseña(BeanLogin obj) throws Exception;

}
