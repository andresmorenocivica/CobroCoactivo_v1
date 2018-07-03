/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivDocumenGenerales;
import CobroCoactivo.Utility.HibernateUtil;
import java.io.Serializable;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author jvergara
 */
public class DaoDocumentosGenerales extends ImpGeneryHibernateDao<CivDocumenGenerales, Integer> implements ITDocumentoGenerales{

    @Override
    public CivDocumenGenerales getCivDocumentoGeneral(int idDocumentoGeneral) throws Exception {
       Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "SELECT * FROM CIV_DOCUMEN_GENERALES WHERE DOCGEN_ID = :idDocumentoGeneral";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(CivDocumenGenerales.class);
        query.setInteger("idDocumentoGeneral", idDocumentoGeneral);
        if (query.list().size() > 0) {
            return (CivDocumenGenerales) query.list().get(0);
        }   
        session.close();
        return null;
    }
    
}
