/*
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.Persistencia.CivTipoDocumentos;
import java.math.BigDecimal;
import java.util.List;

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
    public List<CivTipoDocumentos> listAll() throws Exception;

    public CivTipoDocumentos getTipoDocumento(BigDecimal tipoDocumento) throws Exception;

}
