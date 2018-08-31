/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import CobroCoactivo.Persistencia.CivEstadoUsuarios;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author amoreno
 */
public class DaoEstadoUsuarios extends ImpGeneryHibernateDao<CivEstadoUsuarios, Integer> implements ITEstadoUsuarios {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public long insert(Session session, CivEstadoUsuarios estadousuarios) throws Exception {
        return Integer.parseInt(session.save(estadousuarios).toString());
    }


    @Override
    public CivEstadoUsuarios consultarModuloById(Session session, int id) throws Exception {
        return (CivEstadoUsuarios) session.createCriteria(CivEstadoUsuarios.class)
                .add(Restrictions.eq("estusuId", BigDecimal.valueOf(id)))
                .uniqueResult();
    }

}
