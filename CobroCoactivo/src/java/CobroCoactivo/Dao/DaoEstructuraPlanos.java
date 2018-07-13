/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivEstructuraPlanos;
import CobroCoactivo.Persistencia.CivPlanGenerales;
import CobroCoactivo.Utility.HibernateUtil;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author AMORENO
 */
public class DaoEstructuraPlanos extends ImpGeneryHibernateDao<CivEstructuraPlanos, Integer> implements ITEstructuraPlanos{

    @Override
    public CivEstructuraPlanos getEstructuraPlano(int tipo,int indiceCampo) throws Exception {
         Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "SELECT * FROM CIV_ESTRUCTURA_PLANOS WHERE ESTPLA_TIPO = :tipo AND ESTPLA_INDICE = :indiceCampo";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(CivEstructuraPlanos.class);
        query.setInteger("tipo", tipo);
        query.setInteger("indiceCampo", indiceCampo);
        if (query.list().size() > 0) {
            return (CivEstructuraPlanos) query.list().get(0);
        }
        session.close();
        return null;
    }
    
}
