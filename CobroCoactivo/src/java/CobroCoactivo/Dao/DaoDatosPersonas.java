/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivDatosPersonas;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author emadrid
 */
public class DaoDatosPersonas extends ImpGeneryHibernateDao<CivDatosPersonas, Integer> implements ITDatosPersonas {

    @Override
    public List<CivDatosPersonas> listarDatosPersonas(Session session, int Id_personas) throws Exception {
        String hql ="from CivDatosPersonas where civPersonas.perId =:idPersona";
        Query query = session.createQuery(hql);
        query.setBigDecimal("idPersona", new BigDecimal(Id_personas));
        if (query.list().size() > 0) {
            return query.list();
        }
        return null;
    }

}
