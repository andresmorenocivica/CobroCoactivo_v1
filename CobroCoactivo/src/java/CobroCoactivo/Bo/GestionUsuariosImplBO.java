/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanGestionUsuarios;
import CobroCoactivo.Dao.DaoConfUsuRec;
import CobroCoactivo.Dao.DaoDatosPersonas;
import CobroCoactivo.Dao.DaoEstadoConfUsuRec;
import CobroCoactivo.Dao.DaoModulos;
import CobroCoactivo.Dao.DaoPersonas;
import CobroCoactivo.Dao.DaoRecursos;
import CobroCoactivo.Dao.DaoTipoDocumento;
import CobroCoactivo.Dao.DaoUsuarios;
import CobroCoactivo.Dao.ITConfUsuRec;
import CobroCoactivo.Dao.ITDatosPersonas;
import CobroCoactivo.Dao.ITEstadoConfUsuRec;
import CobroCoactivo.Dao.ITModulos;
import CobroCoactivo.Dao.ITPersonas;
import CobroCoactivo.Dao.ITRecursos;
import CobroCoactivo.Dao.ITTipoDocumento;
import CobroCoactivo.Dao.ITUsuarios;
import CobroCoactivo.Modelo.ConfUsuRec;
import CobroCoactivo.Modelo.DatosPersonas;
import CobroCoactivo.Modelo.Modulos;
import CobroCoactivo.Modelo.Recursos;
import CobroCoactivo.Modelo.TipoDocumentos;
import CobroCoactivo.Persistencia.CivUsuarios;
import java.util.ArrayList;
import java.util.List;
import CobroCoactivo.Modelo.Usuarios;
import CobroCoactivo.Persistencia.CivConfUsuRec;
import CobroCoactivo.Persistencia.CivDatosPersonas;
import CobroCoactivo.Persistencia.CivEstadoConfusurec;
import CobroCoactivo.Persistencia.CivModulos;
import CobroCoactivo.Persistencia.CivRecursos;
import CobroCoactivo.Persistencia.CivTipoDocumentos;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
    private ITConfUsuRec confUsuRecDAO;
    private ITEstadoConfUsuRec estadoConfUsuRecDAO;

    public GestionUsuariosImplBO() {
        usuariosDAO = new DaoUsuarios();
        personasDAO = new DaoPersonas();
        datosPersonasDAO = new DaoDatosPersonas();
        tipoDocumentoDAO = new DaoTipoDocumento();
        modulosDAO = new DaoModulos();
        recursosDAO = new DaoRecursos();
        confUsuRecDAO = new DaoConfUsuRec();
        estadoConfUsuRecDAO = new DaoEstadoConfUsuRec();

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
    public void cargarModulosByUsuario(BeanGestionUsuarios bean) throws Exception {
        List<CivModulos> listCivModulos = getModulosDAO().getModulosByUsuario(bean.getDetalleUsuario().getId());
        if (listCivModulos != null) {
            for (CivModulos civModulo : listCivModulos) {
                List<CivRecursos> listCivRecursos = getRecursosDAO().getRecursos(civModulo.getModId().intValue(), bean.getDetalleUsuario().getId());
                Modulos modulos = new Modulos(civModulo, civModulo.getCivEstadoModulos(), listCivRecursos);
                bean.getListModulos().add(modulos);
            }
        }
    }

    @Override
    public void cargarTodosModulos(BeanGestionUsuarios bean) throws Exception {
        List<CivModulos> listCivModulos = getModulosDAO().findAll();
        if (listCivModulos != null) {
            for (CivModulos civModulo : listCivModulos) {
                Modulos modulos = new Modulos(civModulo, civModulo.getCivEstadoModulos());
                List<CivRecursos> listCivRecursos = getRecursosDAO().getRecursos(civModulo.getModId().intValue(), bean.getDetalleUsuario().getId());
                if (listCivRecursos != null) {
                    for (CivRecursos CivRecurso : listCivRecursos) {
                        List<CivConfUsuRec> civConfUsuRec = getConfUsuRecDAO().getConfUsuRec(CivRecurso.getRecId().intValue());

                        Recursos recurso = new Recursos(CivRecurso);
                        recurso.setSelecionado(civConfUsuRec != null ? true : false);
                        modulos.getListRecurso().add(recurso);
                    }
                }
                bean.getListTodosModulos().add(modulos);
            }
        }
    }

    @Override
    public void cargarRecursoByModulo(BeanGestionUsuarios bean) throws Exception {
        List<CivRecursos> listRecursos = getRecursosDAO().getListCivRecursos(bean.getIdModuloSelecionado(), bean.getDetalleUsuario().getId());
        if (listRecursos != null) {
            for (CivRecursos civRecurso : listRecursos) {
                Recursos recursos = new Recursos(civRecurso);
                bean.getListRecursos().add(recursos);
            }
        }
    }

    @Override
    public void guardarRecursoUsuario(BeanGestionUsuarios bean) throws Exception {
        int increment = -1;
        for (int i = 0; i < bean.getListRecursos().size(); i++) {
            increment++;
            if (bean.getListRecursos().get(i).isSelecionado() == true) {
                if (increment == i) {
                    CivConfUsuRec civConfUsuRec = new CivConfUsuRec();
                    CivEstadoConfusurec civEstadoConfusurec = getEstadoConfUsuRecDAO().getEstadoConfUsuRec(BigDecimal.valueOf(1));
                    CivRecursos civRecursos = getRecursosDAO().getRecursos(bean.getListRecursos().get(i).getRecId().intValue());
                    CivUsuarios civUsuarios = getUsuariosDAO().find(BigDecimal.valueOf(bean.getDetalleUsuario().getId()));

                    civConfUsuRec.setCivUsuarios(civUsuarios);
                    civConfUsuRec.setConfusurecFechaproceso(new Date());
                    civConfUsuRec.setCivEstadoConfusurec(civEstadoConfusurec);
                    civConfUsuRec.setCivRecursos(civRecursos);
                    getConfUsuRecDAO().create(civConfUsuRec);
                }
            }
        }
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se agrego el recurso exitosamente", null));
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

    /**
     * @return the confUsuRecDAO
     */
    public ITConfUsuRec getConfUsuRecDAO() {
        return confUsuRecDAO;
    }

    /**
     * @param confUsuRecDAO the confUsuRecDAO to set
     */
    public void setConfUsuRecDAO(ITConfUsuRec confUsuRecDAO) {
        this.confUsuRecDAO = confUsuRecDAO;
    }

    /**
     * @return the estadoConfUsuRecDAO
     */
    public ITEstadoConfUsuRec getEstadoConfUsuRecDAO() {
        return estadoConfUsuRecDAO;
    }

    /**
     * @param estadoConfUsuRecDAO the estadoConfUsuRecDAO to set
     */
    public void setEstadoConfUsuRecDAO(ITEstadoConfUsuRec estadoConfUsuRecDAO) {
        this.estadoConfUsuRecDAO = estadoConfUsuRecDAO;
    }

}
