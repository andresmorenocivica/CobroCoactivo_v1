/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivUspHistoria;
import CobroCoactivo.Persistencia.CivUsuarios;
import CobroCoactivo.Utility.HibernateUtil;
import java.math.BigDecimal;
import java.util.*;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author JefreySistemas
 */
public class DaoUsuarios extends ImpGeneryHibernateDao<CivUsuarios, Integer> implements ITUsuarios  {

//    @Override
//    public CivUsuarios consultarUsuario(String usuario, String password) throws Exception {
//
//        String hql = "from CivUsuarios where usu_nombre =? and usu_password =?";
//        List list = getHibernateTemplate().findByNamedParam(hql, usuario, password);
//        if (list.size() > 0) {
//            return (CivUsuarios) list.get(0);
//        }
//        return null;
//    }
    @Override

    public List<CivUsuarios> getAll(Session session) throws Exception {
        return session.createCriteria(CivUsuarios.class).list();
    }

    @Override

    public CivUsuarios consultarUsuarioBy(Session session, int id_usuario) throws Exception {
        return (CivUsuarios) session.createCriteria(CivUsuarios.class)
                .add(Restrictions.eq("usuId", new BigDecimal(id_usuario)))
                .uniqueResult();
    }

    @Override
    public CivUsuarios consultarIdPer(Session session, int id) throws Exception {
        return (CivUsuarios) session.createCriteria(CivUsuarios.class)
                .add(Restrictions.eq("id", new BigDecimal(id)))
                .uniqueResult();
    }

    @Override

    public CivUsuarios consultarUsuarioByNombre(Session session, String nombre_usuario) throws Exception {
        return (CivUsuarios) session.createCriteria(CivUsuarios.class)
                .add(Restrictions.eq("usuNombre", nombre_usuario))
                .uniqueResult();
    }

    @Override

    public List<CivUsuarios> listarUsuarios(String nombre_usuario) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "SELECT * FROM CIV_USUARIOS WHERE USU_NOMBRE LIKE '%"+nombre_usuario+"%' ORDER BY 1 asc";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(CivUsuarios.class);
        if (query.list().size() > 0) {
            return query.list();
        }
        session.close();
        return null;
    }

    /**
     *
     * @param id_usuario
     * @return
     * @throws Exception
     */
    @Override
    public ArrayList<String> consultar_HPAS(Session session, int id_usuario) throws Exception {
        String sql = "select P_DATA from CIV_USP_HISTORIA where USU_ID =:usu_id";
        SQLQuery query = session.createSQLQuery(sql);
        query.setInteger("usu_id", id_usuario);

        if (query.list().size() > 0) {
            if (query.list() instanceof java.util.ArrayList) {
                return (ArrayList<String>) query.list();
            }
        }
        return new ArrayList<>();

    }

    @Override
    public List<CivUspHistoria> consultarEstado_HPAS(Session session, int id_usuario) throws Exception {

        String hql = "from CivUspHistoria where USU_ID =:id_usuario";
        SQLQuery query = session.createSQLQuery(hql);
        query.addEntity(CivUsuarios.class);
        query.setInteger("id_usuario", id_usuario);
        if (query.list().size() > 0) {
            return query.list();
        }
        return null;
    }

    @Override
    public Date consultarFechaUltimoPassword(Session session, int id_usuario) throws Exception {
        Date fecha = null;
        String sql = "select FECHA_PROCESO from CIV_USP_HISTORIA where USU_ID =:usu_id and ESTUSP_ID=1 order by FECHA_PROCESO DESC";
        SQLQuery query = session.createSQLQuery(sql);
        query.setInteger("usu_id", id_usuario);

        if (query.list().size() > 0) {
            fecha = (Date) query.list().get(0);
        }
        /*
        if (session.isOpen()) {
            session.flush();
            session.close();
        }*/
        if (fecha != null) {
            return fecha;
        } else {
            return null;
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public long insert(Session session, CivUsuarios civUsuario) throws Exception {
        return Integer.parseInt(session.save(civUsuario).toString());
    }

    /**
     *
     * @param uspHistoria
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public long insertHisPass(Session session, CivUspHistoria uspHistoria) throws Exception {
        return Integer.parseInt(session.save(uspHistoria).toString());

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateHisPass(Session session, CivUspHistoria uspHistoria) throws Exception {
        session.update(uspHistoria);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Session session, CivUsuarios civUsuario) throws Exception {
        session.update(civUsuario);
        return true;
    }

}
