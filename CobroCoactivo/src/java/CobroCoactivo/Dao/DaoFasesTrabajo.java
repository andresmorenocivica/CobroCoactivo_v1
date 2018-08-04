/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivFasesTrabajos;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author AMORENO
 */
public class DaoFasesTrabajo extends ImpGeneryHibernateDao<CivFasesTrabajos, Integer> implements ITFasesTrabajo {

    @Override
    public List<CivFasesTrabajos> listarFasesTrabajo(Session session, int idEtapaTrabajo) throws Exception {
        String sql = "FROM CivFasesTrabajos WHERE civEtapasTrabajos.etatraId =:idEtapaTrabajo order by fastraDianim,fastraDiamax asc";
        Query query = session.createQuery(sql);
        query.setParameter("idEtapaTrabajo", new BigDecimal(idEtapaTrabajo));
        if (query.list().size() > 0) {
            return query.list();
        }
        return null;
    }

    @Override
    public CivFasesTrabajos getFasesTrabajos(Session session, int idFasesTrabajos) throws Exception {
        String hql = "from CivFasesTrabajos where fastraId=:idFasesTrabajos";
        Query query = session.createQuery(hql);
        query.setInteger("idFasesTrabajos", idFasesTrabajos);
        if (query.list().size() > 0) {
            CivFasesTrabajos civFasesTrabajos = (CivFasesTrabajos) query.list().get(0);
            return civFasesTrabajos;
        }
        return null;

    }

}
