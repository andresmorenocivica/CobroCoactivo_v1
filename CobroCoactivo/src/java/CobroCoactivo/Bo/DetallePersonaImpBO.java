/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanDetallePersonas;
import CobroCoactivo.Dao.DaoDatosPersonas;
import CobroCoactivo.Dao.DaoTipoDocumento;
import CobroCoactivo.Dao.ITDatosPersonas;
import CobroCoactivo.Dao.ITTipoDocumento;
import CobroCoactivo.Modelo.DatosPersonas;
import CobroCoactivo.Modelo.TipoDocumentos;
import CobroCoactivo.Persistencia.CivDatosPersonas;
import CobroCoactivo.Persistencia.CivTipoDocumentos;
import CobroCoactivo.Utility.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author emadrid
 */
public class DetallePersonaImpBO implements DetallePersonaBO {

    private ITDatosPersonas datosPersonasDAO;
    private ITTipoDocumento tipoDocumentoDAO;

    public DetallePersonaImpBO() {
        datosPersonasDAO = new DaoDatosPersonas();
        tipoDocumentoDAO = new DaoTipoDocumento();
    }

    @Override
    public void cargarTipoDocumento(BeanDetallePersonas bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            List<CivTipoDocumentos> listCivTipoDocumento = getTipoDocumentoDAO().listAll(session);
            for (CivTipoDocumentos civTipoDocumentos : listCivTipoDocumento) {
                TipoDocumentos tipoDocumentos = new TipoDocumentos(civTipoDocumentos);
                bean.getListTipoDocumento().add(tipoDocumentos);
            }
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void cargarDatosPersonas(BeanDetallePersonas bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            if (bean != null) {
                if (bean.getDetallePersonasModal() != null) {
                    if (bean.getDetallePersonasModal().getId() != 0) {
                        bean.getDetallePersonasModal().setListDatosPersonas(new ArrayList<>());
                        List<CivDatosPersonas> listCivDatosPersonas = getDatosPersonasDAO().listarDatosPersonas(session , bean.getDetallePersonasModal().getId());
                        if (listCivDatosPersonas != null) {
                            for (CivDatosPersonas CivDatosPersona : listCivDatosPersonas) {
                                DatosPersonas datosPersonas = new DatosPersonas(CivDatosPersona, CivDatosPersona.getCivTipoDatosPersonas(), CivDatosPersona.getCivEstadoDatosPersonas());
                                bean.getDetallePersonasModal().getListDatosPersonas().add(datosPersonas);
                            }
                        }
                    }
                }
            }
        } finally {
            session.flush();
            session.close();
        }
    }

    /**
     * @return the datosPersonasDAO
     */
    public ITDatosPersonas getDatosPersonasDAO() {
        return datosPersonasDAO;
    }

    /**
     * @param datosPersonasDAO the datosPersonasDAO to set
     */
    public void setDatosPersonasDAO(ITDatosPersonas datosPersonasDAO) {
        this.datosPersonasDAO = datosPersonasDAO;
    }

    /**
     * @return the tipoDocumentoDAO
     */
    public ITTipoDocumento getTipoDocumentoDAO() {
        return tipoDocumentoDAO;
    }

    /**
     * @param tipoDocumentoDAO the tipoDocumentoDAO to set
     */
    public void setTipoDocumentoDAO(ITTipoDocumento tipoDocumentoDAO) {
        this.tipoDocumentoDAO = tipoDocumentoDAO;
    }

}
