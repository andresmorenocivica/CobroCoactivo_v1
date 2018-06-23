/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivDeudas;
import CobroCoactivo.Utility.HibernateUtil;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author emadrid
 */
public class DaoDeudas extends ImpGeneryHibernateDao<CivDeudas, Integer> implements ITDeudas{

    @Override
    public List<CivDeudas> listarDeudas(int Id_personas) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "SELECT * FROM CIV_DEUDAS WHERE DEU_PER_FK =:idPersona";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(CivDeudas.class);
        query.setBigDecimal("idPersona", new BigDecimal(Id_personas));
        if (query.list().size() > 0) {
            return query.list();
        }
        session.close();
        return null;
    }

    @Override
    public List<CivDeudas> listarDeudasByRefencia(String referencia) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "SELECT * FROM CIV_DEUDAS WHERE DEU_REFENCIA =:referencia";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(CivDeudas.class);
        query.setString("referencia", referencia);
        if (query.list().size() > 0) {
            return query.list();
        }
        session.close();
        return null;
    }

    @Override
    public List<CivDeudas> listarDeudasByTipo(int tipoDeudas) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "SELECT * FROM CIV_DEUDAS WHERE DEU_TIPDEU_FK=:tipoDeuda";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(CivDeudas.class);
        query.setBigDecimal("tipoDeuda", new BigDecimal(tipoDeudas));
        if (query.list().size() > 0) {
            return query.list();
        }
        session.close();
        return null;
    }
    
    @Override
    public List<CivDeudas> listarDeudasByFechaAdquisicion(String fechaInicial, String fechaFinal) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        //String sql = "from CivDeudas where to_char(deuFechaproceso,'DD/MM/YYYY') between '01/01/2018' and '01/06/2018'";
        String sql = "SELECT * FROM CIV_DEUDAS WHERE TO_DATE(TO_CHAR(DEU_FECHAPROCESO,'DD/MM/YYYY'),'DD/MM/YYYY') BETWEEN '01/01/2018' AND '31/12/2018'";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(CivDeudas.class);
        //query.setDate("fechaInicial", fechaInicial);
        //query.setDate("fechaFinal", fechaFinal);
        if (query.list().size() > 0) {
            return query.list();
        }   
        session.close();
        return null;
    }

}
