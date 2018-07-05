package CobroCoactivo.Dao;

import CobroCoactivo.Persistencia.CivRecursos;
import CobroCoactivo.Persistencia.CivUsuarios;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Roymer Camacho
 */
public interface ITLogin {

    /**
     *
     * @param obj
     * @return
     * @throws java.lang.Exception
     */
    CivUsuarios validarLogin(Session session,CivUsuarios obj) throws Exception;

    /**
     *
     * @param usu_id
     * @return
     * @throws java.lang.Exception
     */
    List<CivRecursos> listarRecursos(Session session,int usu_id) throws Exception;

    /**
     *
     * @param nombre_usu
     * @return
     * @throws Exception
     */
    public CivUsuarios getUsuario(Session session,String nombre_usu) throws Exception;
    
}
