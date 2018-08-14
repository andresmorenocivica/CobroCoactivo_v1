/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivSolicitudes;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author emadridp
 */
public class DaoSolicitudes extends ImpGeneryHibernateDao<CivSolicitudes, Integer> implements ITSolicitudes {

    @Override
    public List<CivSolicitudes> getCivSolicitudesPendientes(Session session) throws Exception {
        String hql = "SELECT DISTINCT SOL.* FROM CIV_SOLICITUDES SOL  \n"
                + "INNER JOIN CIV_DETALLE_SOLICITUDES DETSOL ON DETSOL.DETSOL_SOL_FK = SOL.SOL_ID WHERE SOL.SOL_ESTSOL_FK = 3";
        SQLQuery query = session.createSQLQuery(hql);
        query.addEntity(CivSolicitudes.class);
        if (query.list().size() > 0) {
            return query.list();
        }
        return null;
    }

}
