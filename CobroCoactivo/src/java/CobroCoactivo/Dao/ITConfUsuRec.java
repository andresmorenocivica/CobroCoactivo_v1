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
     * Retorna el listado de los Perfieles de Recursos correspondientes a un
     * formulario de la base de datos.
     *
     * @param session
     * @param idusuario
     * @return Lista con los Perfil Recursos resultantes de la consulta. Retorna
     * NULL en caso de no encontrarse resultados.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public List<CivConfUsuRec> listPerfilRecursoByIDUsuario(Session session, long idusuario) throws Exception;

    public List<CivConfUsuRec> listPerfilRecurso(Session session) throws Exception;

    public List<CivConfUsuRec> getConfUsuRec(Session session, int id) throws Exception;

    public List<CivConfUsuRec> getConfUsuRec(Session session, int idRecurso, int idUsuario) throws Exception;

    public List<CivConfUsuRec> getConfUsuRecByUser(Session session, int idUsuario) throws Exception;
}
