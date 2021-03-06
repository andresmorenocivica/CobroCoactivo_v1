/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivPersonas;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author JefreySistemas
 */
public interface ITPersonas extends ITGeneryHibernateDao<CivPersonas, Integer> {

    public CivPersonas consultarPersonasById(Session session,int per_id) throws Exception;

    /**
     * Retorna La Persona de la base de datos por medio del documento de
     * identificación.
     *
     * @param tipo Tipo de documento
     * @param Documento Documento de la Persona
     * @return La Persona resultante de la consulta. Retorna NULL en caso de no
     * encontrarse resultados.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public CivPersonas consultarPersonasByDocumento(Session session, int tipo, String Documento) throws Exception;

    /**
     * Retorna el listado de los Datos de Vehículos correspondientes a un
     * vehiculo de la base de datos.
     *
     * @param persona Nombre de usuario. 'ALL' o vacío trae todos los usuarios.
     * @return Lista con los Usuarios resultantes de la consulta. Retorna NULL
     * en caso de no encontrarse resultados.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public List<CivPersonas> listarPersonas(Session session, String persona) throws Exception;

    /**
     * Retorna el listado de los Datos de Vehículos correspondientes a un
     * vehiculo de la base de datos.
     *
     * @param fecha Nombre de usuario. 'ALL' o vacío trae todos los usuarios.
     * @return Lista con los Usuarios resultantes de la consulta. Retorna NULL
     * en caso de no encontrarse resultados.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public List<CivPersonas> listarPersonasFecha(Session session, String fecha) throws Exception;

    /**
     * Retorna La Persona de la base de datos por medio del documento de
     * identificación.
     *
     * @param Documento Documento de la Persona
     * @return La Persona resultante de la consulta. Retorna NULL en caso de no
     * encontrarse resultados.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public CivPersonas consultarPersonasDocumento(Session session, String Documento) throws Exception;

}
