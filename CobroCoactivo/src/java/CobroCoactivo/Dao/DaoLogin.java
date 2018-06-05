package CobroCoactivo.Dao;

import CobroCoactivo.Crypto.DigestHandler;
import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivRecursos;
import CobroCoactivo.Persistencia.CivUsuarios;
import java.io.Serializable;
import java.util.List;
import static jdk.nashorn.internal.runtime.Debug.id;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Roymer Camacho
 */
public class DaoLogin extends ImpGeneryHibernateDao<Object, Serializable> implements ITLogin, Serializable {

    private static final long serialVersionUID = 7526472295622776147L;

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public CivUsuarios validarLogin(Session session, CivUsuarios obj) throws Exception {
        return (CivUsuarios) session.createCriteria(CivUsuarios.class)
                .add(Restrictions.eq("usuPass", obj.getUsuPass()))
                .uniqueResult();
    }

    /**
     *
     * @param usu_id
     * @return
     */
    @Override
    public List<CivRecursos> listarRecursos(Session session, int usu_id) throws Exception {
        String hql = "SELECT R.* \n"
                + "FROM CIV_RECURSOS R\n"
                + "INNER JOIN CIV_CONF_USU_REC UR ON UR.CONFUSUREC_RECURSO_FK = R.REC_ID\n"
                + "INNER JOIN CIV_ESTADO_CONFUSUREC EC ON EC.ESTCONFUSUREC_ID = UR.CONFUSUREC_ESTADO_FK\n"
                + "INNER JOIN CIV_USUARIOS U ON U.USU_ID = UR.CONFUSUREC_USUARIOS_FK\n"
                + "WHERE U.USU_ID =:usu_id AND EC.ESTCONFUSUREC_ID = 1";
        SQLQuery query = session.createSQLQuery(hql);
        query.addEntity(CivRecursos.class);
        query.setInteger("usu_id", usu_id);
        if (query.list().size() > 0) {
            return query.list();
        }
        return null;
    }

    /**
     *
     * @param nombre_usu
     * @return
     * @throws Exception
     */
    @Override
    public CivUsuarios getUsuario(Session session, String usuNombre) throws Exception {
        
        String  hql = "from CivUsuarios where usuNombre = :usuNombre";
        Query query = session.createQuery(hql);
        query.setParameter("usuNombre", usuNombre);
        CivUsuarios civUsuarios =(CivUsuarios) query.uniqueResult();
        return civUsuarios;
//        return (CivUsuarios) session.createCriteria(CivUsuarios.class)
//                .add(Restrictions.eq("usuNombre", usuNombre))
//                .uniqueResult();
    }
}
