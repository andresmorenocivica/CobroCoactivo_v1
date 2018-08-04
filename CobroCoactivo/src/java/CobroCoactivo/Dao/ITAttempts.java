/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivAttempts;
import org.hibernate.Session;

/**
 *
 * @author Roymer Camacho
 */
public interface ITAttempts extends ITGeneryHibernateDao<CivAttempts, Integer>{

    /**
     *
     * @param attp
     * @return
     * @throws Exception
     */
    public long insert(Session session,CivAttempts attp) throws Exception;

    /**
     *
     * @param attp
     * @return
     * @throws Exception
     */
    void update(Session session,CivAttempts attp) throws Exception;

    /**
     *
     * @param id_usuario
     * @return
     * @throws Exception
     */
    public CivAttempts consultarIntentos(Session session,int id_usuario) throws Exception;
}
