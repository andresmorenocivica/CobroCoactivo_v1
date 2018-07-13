/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivModulos;
import CobroCoactivo.Utility.HibernateUtil;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author emadrid
 */
public class DaoModulos extends ImpGeneryHibernateDao<CivModulos, Integer> implements ITModulos {

    @Override
    public List<CivModulos> getModulosByUsuario(int idUsuario) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "SELECT DISTINCT M.*\n"
                + "FROM CIV_CONF_USU_REC CF\n"
                + "INNER JOIN CIV_RECURSOS R ON R.REC_ID = CF.CONFUSUREC_RECURSO_FK AND R.REC_ESTADO_FK=1\n"
                + "INNER JOIN CIV_MODULOS M ON M.MOD_ID = R.REC_MODULOS_FK AND M.MOD_ESTADO_FK = 1\n"
                + "WHERE CF.CONFUSUREC_ESTADO_FK = 1 AND CF.CONFUSUREC_USUARIOS_FK = :idUsuario";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(CivModulos.class);
        query.setInteger("idUsuario", (idUsuario));
        if (query.list().size() > 0) {
            return query.list();
        }
        session.close();
        return null;
    }

}
