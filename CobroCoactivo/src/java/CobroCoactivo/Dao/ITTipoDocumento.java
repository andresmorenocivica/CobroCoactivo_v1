/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.Persistencia.CivTipoDocumentos;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Roymer Camacho
 */
public interface ITTipoDocumento {

    /**
     *
     *
     * @param civTipodocumentos El Objeto a insertar.
     * @return ID único del elemento insertado.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public long insert(CivTipoDocumentos civTipodocumentos) throws Exception;

    /**
     *
     *
     * @param civTipodocumentos El Objeto a actualizar.
     * @return Retorna verdadero si la actualización fue correcta.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public boolean update(CivTipoDocumentos civTipodocumentos) throws Exception;

    /**
     *
     *
     * @return Retorna verdadero si la actualización fue correcta.
     * @throws Exception La Excepción lanzada en caso de error.
     */
    public List<CivTipoDocumentos> listAll(Session session) throws Exception;

    public CivTipoDocumentos getTipoDocumento(Session session,BigDecimal tipoDocumento) throws Exception;
    
    
    CivTipoDocumentos find(BigDecimal id) throws Exception;
}
