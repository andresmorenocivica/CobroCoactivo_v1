/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivDatosPersonas;
import CobroCoactivo.Utility.HibernateUtil;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author emadrid
 */
public class DaoDatosPersonas extends ImpGeneryHibernateDao<CivDatosPersonas, Integer> implements ITDatosPersonas {

    @Override
    public List<CivDatosPersonas> listarDatosPersonas(int Id_personas) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "SELECT * FROM CIV_DATOS_PERSONAS WHERE DATPER_PERSONA_FK =:idPersona";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(CivDatosPersonas.class);
        query.setBigDecimal("idPersona", new BigDecimal(Id_personas));
        if (query.list().size() > 0) {
            return query.list();
        }
        session.close();
        return null;
    }

}
