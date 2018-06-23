/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivDeudas;
import CobroCoactivo.Persistencia.CivEtapasTrabajos;
import CobroCoactivo.Utility.HibernateUtil;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author AMORENO
 */
public class DaoEtapasTrabajo extends ImpGeneryHibernateDao<CivEtapasTrabajos, Integer> implements ITEtapasTrabajo{

    @Override
    public List<CivEtapasTrabajos> listarEtapasTrabajoByPlantrabajo(int idPlanTrabajo) throws Exception {
       Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "SELECT * FROM CIV_ETAPAS_TRABAJOS WHERE ETATRA_PLATRA_FK =:idPlanTrabajo";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(CivEtapasTrabajos.class);
        query.setInteger("idPlanTrabajo", idPlanTrabajo);
        if (query.list().size() > 0) {
            return query.list();
        }   
        session.close();
        return null;
    }
    
}
