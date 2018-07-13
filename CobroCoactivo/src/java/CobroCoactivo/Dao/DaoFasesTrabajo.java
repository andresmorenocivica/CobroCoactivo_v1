/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivFasesTrabajos;
import CobroCoactivo.Utility.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author AMORENO
 */
public class DaoFasesTrabajo extends ImpGeneryHibernateDao<CivFasesTrabajos, Integer> implements ITFasesTrabajo {

    @Override
    public List<CivFasesTrabajos> listarFasesTrabajo(int idEtapaTrabajo) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "SELECT * FROM CIV_FASES_TRABAJOS WHERE FASTRA_ETATRA_FK =:idEtapaTrabajo order by FASTRA_DIANIM,FASTRA_DIAMAX asc";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(CivFasesTrabajos.class);
        query.setInteger("idEtapaTrabajo", idEtapaTrabajo);
        if (query.list().size() > 0) {
            return query.list();
        }
        session.close();
        return null;
    }

    @Override
    public CivFasesTrabajos getFasesTrabajos(int idFasesTrabajos) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "from CivFasesTrabajos where fastraId=:idFasesTrabajos";
            Query query = session.createQuery(hql);
            query.setInteger("idFasesTrabajos", idFasesTrabajos);
            if (query.list().size() > 0) {
                CivFasesTrabajos civFasesTrabajos = (CivFasesTrabajos) query.list().get(0);
                System.out.println(civFasesTrabajos.getCivEstadoFasesTrabajos());
                System.out.println(civFasesTrabajos.getCivEtapasTrabajos());
                System.out.println(civFasesTrabajos.getCivReporteTrabajos());
                return civFasesTrabajos;
            }
            return null;
        } finally {
            session.close();
        }

    }

}
