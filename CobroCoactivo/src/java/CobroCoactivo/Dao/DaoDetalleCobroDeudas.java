/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivDetalleCobroDeudas;
import CobroCoactivo.Utility.HibernateUtil;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author emadrid
 */
public class DaoDetalleCobroDeudas extends ImpGeneryHibernateDao<CivDetalleCobroDeudas, Integer> implements ITDetalleCobroDeudas {

    @Override
    public List<CivDetalleCobroDeudas> listarDetalleCobroDeudas(int idCobro) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "SELECT * FROM CIV_DETALLE_COBRO_DEUDAS WHERE DETCOBDEU_COBDEU_FK =:idCobro";
        SQLQuery query = session.createSQLQuery(hql);
        query.addEntity(CivDetalleCobroDeudas.class);
        query.setInteger("idCobro", idCobro);
        if (query.list().size() > 0) {
            return query.list();
        }
        session.close();
        return null;
    }

}
