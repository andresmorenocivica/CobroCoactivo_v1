/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanGestionUsuarios;
import CobroCoactivo.Dao.DaoDatosPersonas;
import CobroCoactivo.Dao.DaoModulos;
import CobroCoactivo.Dao.DaoPersonas;
import CobroCoactivo.Dao.DaoRecursos;
import CobroCoactivo.Dao.DaoTipoDocumento;
import CobroCoactivo.Dao.DaoUsuarios;
import CobroCoactivo.Dao.ITDatosPersonas;
import CobroCoactivo.Dao.ITModulos;
import CobroCoactivo.Dao.ITPersonas;
import CobroCoactivo.Dao.ITRecursos;
import CobroCoactivo.Dao.ITTipoDocumento;
import CobroCoactivo.Dao.ITUsuarios;
import CobroCoactivo.Modelo.DatosPersonas;
import CobroCoactivo.Modelo.Modulos;
import CobroCoactivo.Modelo.TipoDocumentos;
import CobroCoactivo.Persistencia.CivUsuarios;
import java.util.ArrayList;
import java.util.List;
import CobroCoactivo.Modelo.Usuarios;
import CobroCoactivo.Persistencia.CivDatosPersonas;
import CobroCoactivo.Persistencia.CivModulos;
import CobroCoactivo.Persistencia.CivRecursos;
import CobroCoactivo.Persistencia.CivTipoDocumentos;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author emadrid
 */
public class GestionUsuariosImplBO implements GestionUsuariosBO, Serializable {

    private ITUsuarios usuariosDAO;
    private ITPersonas personasDAO;
    private ITDatosPersonas datosPersonasDAO;
    private ITTipoDocumento tipoDocumentoDAO;
    private ITModulos modulosDAO;
    private ITRecursos recursosDAO;

    public GestionUsuariosImplBO() {
        usuariosDAO = new DaoUsuarios();
        personasDAO = new DaoPersonas();
        datosPersonasDAO = new DaoDatosPersonas();
        tipoDocumentoDAO = new DaoTipoDocumento();
        modulosDAO = new DaoModulos();
        recursosDAO = new DaoRecursos();

    }

    @Override
    public void consultarUsuario(BeanGestionUsuarios bean) throws Exception {
        //List<CivUsuarios> listarCivUsuario = new ArrayList<CivUsuarios>();
        List<CivUsuarios> listaCivUsuario = new ArrayList<>();
        switch (bean.getTipoBusqueda()) {
            case 1:
                listaCivUsuario = getUsuariosDAO().listarUsuarios(bean.getNombreUsuario().toUpperCase());
                if (listaCivUsuario == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se encontro el usuario", null));
                }
                break;
        }
        if (listaCivUsuario != null && listaCivUsuario.size() > 0) {
            for (CivUsuarios civUsuarios : listaCivUsuario) {
                Usuarios usuarios = new Usuarios(civUsuarios, civUsuarios.getCivEstadoUsuarios(), civUsuarios.getCivPersonas());
                bean.getListadoUsuarios().add(usuarios);
            }
        }
    }

    @Override
    public void actualizarContraseña(BeanGestionUsuarios bean) throws Exception {
        CivUsuarios civUsuarios = getUsuariosDAO().find(new BigDecimal(bean.getDetalleUsuario().getId()));
        civUsuarios.setUsuPass(bean.getContraseñaConfirmacion());
        getUsuariosDAO().update(civUsuarios);
    }

    @Override
    public void cargarDatosPersonas(BeanGestionUsuarios bean) throws Exception {
        if (bean != null) {
            if (bean.getDetallePersonaModal() != null) {
                if (bean.getDetallePersonaModal().getId() != 0) {
                    List<CivDatosPersonas> listCivDatosPersonas = getDatosPersonasDAO().listarDatosPersonas(bean.getDetallePersonaModal().getId());
                    if (listCivDatosPersonas != null) {
                        for (CivDatosPersonas CivDatosPersona : listCivDatosPersonas) {
                            DatosPersonas datosPersonas = new DatosPersonas(CivDatosPersona, CivDatosPersona.getCivTipoDatosPersonas(), CivDatosPersona.getCivEstadoDatosPersonas());
                            bean.getDetallePersonaModal().getListDatosPersonas().add(datosPersonas);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void cargarTipoDocumento(BeanGestionUsuarios bean) throws Exception {
        List<CivTipoDocumentos> listCivTipoDocumento = getTipoDocumentoDAO().listAll();
        for (CivTipoDocumentos civTipoDocumentos : listCivTipoDocumento) {
            TipoDocumentos tipoDocumentos = new TipoDocumentos(civTipoDocumentos);
            bean.getListTipoDocumento().add(tipoDocumentos);
        }
    }

    @Override
    public void cargarModulos(BeanGestionUsuarios bean) throws Exception {
        List<CivModulos> listCivModulos = getModulosDAO().findAll();
        for (CivModulos civModulo : listCivModulos) {
            List<CivRecursos> listCivRecursos = getRecursosDAO().getRecursos(civModulo.getModId().intValue());
            Modulos modulos = new Modulos(civModulo, civModulo.getCivEstadoModulos(), listCivRecursos);
            bean.getListModulos().add(modulos);
        }
    }

    /**
     * @return the usuariosDAO
     */
    public ITUsuarios getUsuariosDAO() {
        return usuariosDAO;
    }

    /**
     * @param usuariosDAO the usuariosDAO to set
     */
    public void setUsuariosDAO(ITUsuarios usuariosDAO) {
        this.usuariosDAO = usuariosDAO;
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

    /**
     * @return the modulosDAO
     */
    public ITModulos getModulosDAO() {
        return modulosDAO;
    }

    /**
     * @param modulosDAO the modulosDAO to set
     */
    public void setModulosDAO(ITModulos modulosDAO) {
        this.modulosDAO = modulosDAO;
    }

    /**
     * @return the recursosDAO
     */
    public ITRecursos getRecursosDAO() {
        return recursosDAO;
    }

    /**
     * @param recursosDAO the recursosDAO to set
     */
    public void setRecursosDAO(ITRecursos recursosDAO) {
        this.recursosDAO = recursosDAO;
    }

}
