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
public interface ITAttempts extends ITGeneryHibernateDao<CivAttempts, Integer> {

    public CivAttempts consultarIntentosByUser(Session session, int idUsuario) throws Exception;
}
