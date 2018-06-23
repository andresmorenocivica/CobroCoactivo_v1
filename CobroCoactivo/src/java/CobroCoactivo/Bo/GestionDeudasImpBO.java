/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanGestionDeudas;
import CobroCoactivo.Dao.DaoCobroDeudas;
import CobroCoactivo.Dao.DaoDetalleCobroDeudas;
import CobroCoactivo.Dao.DaoDeudas;
import CobroCoactivo.Dao.DaoPersonas;
import CobroCoactivo.Dao.DaoTipoDetalleCobro;
import CobroCoactivo.Dao.DaoTipoDeudas;
import CobroCoactivo.Dao.DaoValorTipoDetalleCobro;
import CobroCoactivo.Dao.ITCobroDeudas;
import CobroCoactivo.Dao.ITDetalleCobroDeudas;
import CobroCoactivo.Dao.ITDeudas;
import CobroCoactivo.Dao.ITPersonas;
import CobroCoactivo.Dao.ITTipoDetalleCobro;
import CobroCoactivo.Dao.ITTipoDeudas;
import CobroCoactivo.Dao.ITValorTipoDetalleCobro;
import CobroCoactivo.Modelo.CobroDeudas;
import CobroCoactivo.Persistencia.CivDeudas;
import CobroCoactivo.Modelo.Deudas;
import CobroCoactivo.Modelo.TipoDeudas;
import CobroCoactivo.Persistencia.CivCobroDeudas;
import CobroCoactivo.Persistencia.CivDetalleCobroDeudas;
import CobroCoactivo.Persistencia.CivPersonas;
import CobroCoactivo.Persistencia.CivTipoDetalleCobro;
import CobroCoactivo.Persistencia.CivTipoDeudas;
import CobroCoactivo.Persistencia.CivValorTipoDetalleCobro;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author emadrid
 */
public class GestionDeudasImpBO implements GestionDeudasBO, Serializable {

    private ITDeudas deudasDAO;
    private ITPersonas personasDAO;
    private ITTipoDeudas tipoDeudasDAO;
    private ITCobroDeudas cobroDeudasDAO;
    private ITDetalleCobroDeudas detalleCobroDeudasDAO;
    private ITTipoDetalleCobro tipoDetalleCobroDAO;
    private ITValorTipoDetalleCobro valorTipoDetalleCobroDAO;

    public GestionDeudasImpBO() {
        deudasDAO = new DaoDeudas();
        personasDAO = new DaoPersonas();
        tipoDeudasDAO = new DaoTipoDeudas();
        cobroDeudasDAO = new DaoCobroDeudas();
        tipoDetalleCobroDAO = new DaoTipoDetalleCobro();
        detalleCobroDeudasDAO = new DaoDetalleCobroDeudas();
        valorTipoDetalleCobroDAO = new DaoValorTipoDetalleCobro();

    }

    @Override
    public void cargarListaTipoDeudas(BeanGestionDeudas bean) throws Exception {
        List<CivTipoDeudas> listCivTipoDeudas = getTipoDeudasDAO().listAll();
        for (CivTipoDeudas CivTipoDeuda : listCivTipoDeudas) {
            TipoDeudas tipoDeudas = new TipoDeudas(CivTipoDeuda);
            bean.getListTipoDeudas().add(tipoDeudas);
        }
    }

    @Override
    public void cargarListaCobroDeudas(BeanGestionDeudas bean) throws Exception {
        List<CivCobroDeudas> listCivCobroDeudas = getCobroDeudasDAO().findAll();
        for (CivCobroDeudas CivCobroDeuda : listCivCobroDeudas) {
            CobroDeudas cobroDeudas = new CobroDeudas(CivCobroDeuda);
            switch (cobroDeudas.getDescripcion()) {
                case "DIFICIL":
                    cobroDeudas.setColorDificultad("btn btn-danger");
                    break;
                case "MEDIANO":
                    cobroDeudas.setColorDificultad("btn btn-warning");
                    break;
                case "FACIL":
                    cobroDeudas.setColorDificultad("btn btn-success");
                    break;
            }
            bean.getListCobroDeudas().add(cobroDeudas);
        }
    }

    @Override
    public void buscarDeudas(BeanGestionDeudas bean) throws Exception {

        List<CivDeudas> listCivDeudas = new ArrayList<>();
        bean.setListDeudas(new ArrayList<>());
        switch (bean.getTipoBusqueda()) {
            case 1:
                listCivDeudas = getDeudasDAO().listarDeudasByRefencia(bean.getReferenciaDeuda().toUpperCase());
                break;
            case 2:
                listCivDeudas = getDeudasDAO().listarDeudasByTipo(bean.getTipoDeudas());
                break;
        }
        if (listCivDeudas != null) {
            if (listCivDeudas.size() > 0) {
                int i = 0;
                for (CivDeudas civDeuda : listCivDeudas) {
                    CivPersonas civPersonas = getPersonasDAO().consultarPersonasById(civDeuda.getCivPersonas().getPerId().intValue());
                    Deudas deudas = new Deudas(civDeuda, civDeuda.getCivEstadoDeudas(), civDeuda.getCivTipoDeudas(), civPersonas);
                    bean.getListDeudas().add(deudas);
                    i++;
                }
            }
        }
    }

    @Override
    public void actualizarDeudaCargada(BeanGestionDeudas bean) throws Exception {
        CivDeudas civDeudas = new CivDeudas();

    }

    @Override
    public Deudas cargarProcentajeCobro(Deudas deudas) throws Exception {
        List<CivCobroDeudas> listaCivCobroDeudas = getCobroDeudasDAO().findAll();
        for (CivCobroDeudas civCobroDeudas : listaCivCobroDeudas) {
            List<CivDetalleCobroDeudas> listCivDetalleCobroDeudas = getDetalleCobroDeudasDAO().listarDetalleCobroDeudas(civCobroDeudas.getCobdeuId().intValue());
            for (CivDetalleCobroDeudas civDetalleCobroDeudas : listCivDetalleCobroDeudas) {
                CivValorTipoDetalleCobro civValorTipoDetalleCobro = getValorTipoDetalleCobroDAO().cargarValorDetalle(civDetalleCobroDeudas.getCivValorTipoDetalleCobro().getValtipdetcobId().intValue());
                CivTipoDetalleCobro civTipoDetalleCobro = getTipoDetalleCobroDAO().cargarTipoDetalleCobro(civValorTipoDetalleCobro.getCivTipoDetalleCobro().getTipdetcobId().intValue());

            }
        }

        return deudas;
    }

    /**
     * @return the deudasDAO
     */
    public ITDeudas getDeudasDAO() {
        return deudasDAO;
    }

    /**
     * @param deudasDAO the deudasDAO to set
     */
    public void setDeudasDAO(ITDeudas deudasDAO) {
        this.deudasDAO = deudasDAO;
    }

    /**
     * @return the personasDAO
     */
    public ITPersonas getPersonasDAO() {
        return personasDAO;
    }

    /**
     * @param personasDAO the personasDAO to set
     */
    public void setPersonasDAO(ITPersonas personasDAO) {
        this.personasDAO = personasDAO;
    }

    /**
     * @return the tipoDeudasDAO
     */
    public ITTipoDeudas getTipoDeudasDAO() {
        return tipoDeudasDAO;
    }

    /**
     * @param tipoDeudasDAO the tipoDeudasDAO to set
     */
    public void setTipoDeudasDAO(ITTipoDeudas tipoDeudasDAO) {
        this.tipoDeudasDAO = tipoDeudasDAO;
    }

    /**
     * @return the cobroDeudasDAO
     */
    public ITCobroDeudas getCobroDeudasDAO() {
        return cobroDeudasDAO;
    }

    /**
     * @param cobroDeudasDAO the cobroDeudasDAO to set
     */
    public void setCobroDeudasDAO(ITCobroDeudas cobroDeudasDAO) {
        this.cobroDeudasDAO = cobroDeudasDAO;
    }

    /**
     * @return the detalleCobroDeudasDAO
     */
    public ITDetalleCobroDeudas getDetalleCobroDeudasDAO() {
        return detalleCobroDeudasDAO;
    }

    /**
     * @param detalleCobroDeudasDAO the detalleCobroDeudasDAO to set
     */
    public void setDetalleCobroDeudasDAO(ITDetalleCobroDeudas detalleCobroDeudasDAO) {
        this.detalleCobroDeudasDAO = detalleCobroDeudasDAO;
    }

    /**
     * @return the tipoDetalleCobroDAO
     */
    public ITTipoDetalleCobro getTipoDetalleCobroDAO() {
        return tipoDetalleCobroDAO;
    }

    /**
     * @param tipoDetalleCobroDAO the tipoDetalleCobroDAO to set
     */
    public void setTipoDetalleCobroDAO(ITTipoDetalleCobro tipoDetalleCobroDAO) {
        this.tipoDetalleCobroDAO = tipoDetalleCobroDAO;
    }

    /**
     * @return the valorTipoDetalleCobroDAO
     */
    public ITValorTipoDetalleCobro getValorTipoDetalleCobroDAO() {
        return valorTipoDetalleCobroDAO;
    }

    /**
     * @param valorTipoDetalleCobroDAO the valorTipoDetalleCobroDAO to set
     */
    public void setValorTipoDetalleCobroDAO(ITValorTipoDetalleCobro valorTipoDetalleCobroDAO) {
        this.valorTipoDetalleCobroDAO = valorTipoDetalleCobroDAO;
    }
}
