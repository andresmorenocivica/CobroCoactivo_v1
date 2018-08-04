/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivConfUsuRec;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author ing_jefreypadilla
 */
public interface ITConfUsuRec extends ITGeneryHibernateDao<CivConfUsuRec, Integer> {

    /**
     * Método para insertar un Perfil Recursos a la base de datos.
     *
     * @param session
     * @param civConfUsuRec
     * @param perfilrecurso El Objeto Perfil Recursos a insertar.
     * @return ID único del elemento insertado.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public int insert(Session session, CivConfUsuRec civConfUsuRec) throws Exception;

    /**
     * Método para actualizar un Perfil Recursos a la base de datos.
     *
     * @param perfilrecurso El Objeto Perfil Recursos a actualizar.
     * @return Retorna verdadero si la actualización fue correcta.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    @Override
    public void update(Session session, CivConfUsuRec civConfUsuRec);

    /**
     * Retorna el listado de los Perfieles de Recursos correspondientes a un
     * formulario de la base de datos.
     *
     * @param session
     * @param idusuario
     * @param perfil Perfil del Recurso.
     * @return Lista con los Perfil Recursos resultantes de la consulta. Retorna
     * NULL en caso de no encontrarse resultados.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public List<CivConfUsuRec> listPerfilRecursoByIDUsuario(Session session, long idusuario) throws Exception;

    public List<CivConfUsuRec> listPerfilRecurso(Session session) throws Exception;

    public List<CivConfUsuRec> getConfUsuRec(int id) throws Exception;

    public List<CivConfUsuRec> getConfUsuRec(int idRecurso, int idUsuario) throws Exception;

    public List<CivConfUsuRec> getConfUsuRecByUser(int idUsuario) throws Exception;
}
