/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Beans;

import CobroCoactivo.Bo.GestionUsuariosBO;
import CobroCoactivo.Bo.GestionUsuariosImplBO;
import CobroCoactivo.Crypto.DigestHandler;
import CobroCoactivo.Modelo.ConfUsuRec;
import CobroCoactivo.Modelo.EstadoPersonas;
import CobroCoactivo.Modelo.Modulos;
import CobroCoactivo.Modelo.Personas;
import CobroCoactivo.Modelo.Recursos;
import CobroCoactivo.Modelo.TipoDocumentos;
import CobroCoactivo.Modelo.Usuarios;
import CobroCoactivo.Utility.Log_Handler;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author emadrid
 */
@ManagedBean(name = "gestionUsuariosBean")
@ViewScoped
public class BeanGestionUsuarios {

    private GestionUsuariosBO gestionUsuariosBO;
    private String NombreUsuario; // Variable que es usada para busqueda de user
    private String nombreUsuarioNuevo; // Usuario genereado 
    private List<Usuarios> listadoUsuarios = new ArrayList<>();
    private int tipoBusqueda;
    private List<Personas> listPersonas = new ArrayList<>();
    private Personas detallePersonaModal = new Personas(); //Objeto que se utilizara para mostrar la persona del usuario
    private List<TipoDocumentos> listTipoDocumento = new ArrayList<>();
    private List<EstadoPersonas> listEstadoPersonas = new ArrayList<>();
    private List<Modulos> listModulos = new ArrayList<>();
    private List<Modulos> listTodosModulos = new ArrayList<>();
    private List<Recursos> listRecursos = new ArrayList<>();
    private List<ConfUsuRec> listConfUsuRec = new ArrayList<>();
    private Usuarios detalleUsuario;
    private String encabezadoDetalleUsuario;
    private String contraseñaActual;
    private String contraseñaNueva;
    private String contraseñaConfirmacion;
    private int idModuloSelecionado;
    private String documentoPersona;
    private int tipoDocumentoPersona;
    private BigDecimal idRecurso;
    private boolean selecionado;

    @PostConstruct
    public void init() {
        try {
            setGestionUsuariosBO(new GestionUsuariosImplBO());
            FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            BeanRequest obj_ = (BeanRequest) session.getAttribute("requestBean");
            getGestionUsuariosBO().cargarTipoDocumento(this);
            if (obj_ != null) {
                setDetalleUsuario(obj_.getUsuario());
                getGestionUsuariosBO().cargarModulosByUsuario(this);
                getGestionUsuariosBO().cargarTodosModulos(this);
                if (obj_.getPersonas() != null) {
                    setEncabezadoDetalleUsuario(obj_.getRuta());
                    setDetallePersonaModal(obj_.getPersonas());
                    getGestionUsuariosBO().cargarDatosPersonas(this);
//                    if (obj_.getPersonas().getApellido1() != null) {
//                        RequestContext requestContext = RequestContext.getCurrentInstance();
//                        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("pnlModalDetallePersona");
//                        requestContext.execute("$('#modalDetallePersona').modal('show')");
//                    }
                }
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    public void cargarModulosByUsuario() {
        try {
            getGestionUsuariosBO().cargarModulosByUsuario(this);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    public void consultarUsuarios(int tipo) {
        try {
            setTipoBusqueda(tipo);
            setListadoUsuarios(new ArrayList<>());
            getGestionUsuariosBO().consultarUsuario(this);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    public void actualizarContraseña() {
        try {
            if (getDetalleUsuario() != null) {
                if (!getDetalleUsuario().getPass().equals(DigestHandler.encryptSHA2(contraseñaActual))) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La contraseña actual no es correcta", null));
                }
                if (getDetalleUsuario().getPass().equals(DigestHandler.encryptSHA2(contraseñaActual)) && !DigestHandler.encryptSHA2(getContraseñaNueva()).equals(DigestHandler.encryptSHA2(contraseñaConfirmacion))) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La contraseña nueva no coincide", null));
                }

                if (getDetalleUsuario().getPass().equals(DigestHandler.encryptSHA2(contraseñaActual)) && DigestHandler.encryptSHA2(getContraseñaNueva()).equals(DigestHandler.encryptSHA2(contraseñaConfirmacion))) {
                    if (DigestHandler.encryptSHA2(getContraseñaNueva()).equals(DigestHandler.encryptSHA2(contraseñaActual))) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La contraseña nueva no puede ser igual a la actual", null));
                    } else {
                        getGestionUsuariosBO().actualizarContraseña(this);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "La contraseña actualizada correctamente", null));
                    }
                }
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    public void cargarRecursoByModulo(int idModulos) {
        try {
            setIdModuloSelecionado(idModulos);
            getGestionUsuariosBO().cargarRecursoByModulo(this);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    public void cargarConfUsuRec(int idModulo) {
        try {
            setIdModuloSelecionado(idModulo);
            getGestionUsuariosBO().cargarConfUsuRec(this);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    public void guardarRecursoUsuario() {
        try {
            getGestionUsuariosBO().guardarRecursoUsuario(this);
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("$('#modalRecursoModulos').modal('hide')");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    public void updateRecursoUsuario() {
        try {
            getGestionUsuariosBO().updateRecursoUsuario(this);
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("$('#modalQuitarRecursoModulos').modal('hide')");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    public void registrarUser() {
        try {
            getGestionUsuariosBO().registrarUsuario(this);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    public void buscarPersona(int tipo) {
        try {
            // casos para los tipos de busqueda
            switch (tipo) {
                // busqueda por tipo documento y documento
                case 1:
                    getGestionUsuariosBO().consultarPersona(this);
                    break;
                case 2:
                    break;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    /**
     * @return the gestionUsuariosBO
     */
    public GestionUsuariosBO getGestionUsuariosBO() {
        return gestionUsuariosBO;
    }

    /**
     * @param gestionUsuariosBO the gestionUsuariosBO to set
     */
    public void setGestionUsuariosBO(GestionUsuariosBO gestionUsuariosBO) {
        this.gestionUsuariosBO = gestionUsuariosBO;
    }

    /**
     * @return the NombreUsuario
     */
    public String getNombreUsuario() {
        return NombreUsuario;
    }

    /**
     * @param NombreUsuario the NombreUsuario to set
     */
    public void setNombreUsuario(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }

    /**
     * @return the listadoUsuarios
     */
    public List<Usuarios> getListadoUsuarios() {
        return listadoUsuarios;
    }

    /**
     * @param listadoUsuarios the listadoUsuarios to set
     */
    public void setListadoUsuarios(List<Usuarios> listadoUsuarios) {
        this.listadoUsuarios = listadoUsuarios;
    }

    /**
     * @return the tipoBusqueda
     */
    public int getTipoBusqueda() {
        return tipoBusqueda;
    }

    /**
     * @param tipoBusqueda the tipoBusqueda to set
     */
    public void setTipoBusqueda(int tipoBusqueda) {
        this.tipoBusqueda = tipoBusqueda;
    }

    /**
     * @return the detalleUsuario
     */
    public Usuarios getDetalleUsuario() {
        return detalleUsuario;
    }

    /**
     * @param detalleUsuario the detalleUsuario to set
     */
    public void setDetalleUsuario(Usuarios detalleUsuario) {
        this.detalleUsuario = detalleUsuario;
    }

    /**
     * @return the encabezadoDetalleUsuario
     */
    public String getEncabezadoDetalleUsuario() {
        return encabezadoDetalleUsuario;
    }

    /**
     * @param encabezadoDetalleUsuario the encabezadoDetalleUsuario to set
     */
    public void setEncabezadoDetalleUsuario(String encabezadoDetalleUsuario) {
        this.encabezadoDetalleUsuario = encabezadoDetalleUsuario;
    }

    /**
     * @return the contraseñaActual
     */
    public String getContraseñaActual() {
        return contraseñaActual;
    }

    /**
     * @param contraseñaActual the contraseñaActual to set
     */
    public void setContraseñaActual(String contraseñaActual) {
        this.contraseñaActual = contraseñaActual;
    }

    /**
     * @return the contraseñaNueva
     */
    public String getContraseñaNueva() {
        return contraseñaNueva;
    }

    /**
     * @param contraseñaNueva the contraseñaNueva to set
     */
    public void setContraseñaNueva(String contraseñaNueva) {
        this.contraseñaNueva = contraseñaNueva;
    }

    /**
     * @return the contraseñaConfirmacion
     */
    public String getContraseñaConfirmacion() {
        return contraseñaConfirmacion;
    }

    /**
     * @param contraseñaConfirmacion the contraseñaConfirmacion to set
     */
    public void setContraseñaConfirmacion(String contraseñaConfirmacion) {
        this.contraseñaConfirmacion = contraseñaConfirmacion;
    }

    /**
     * @return the detallePersonaModal
     */
    public Personas getDetallePersonaModal() {
        return detallePersonaModal;
    }

    /**
     * @param detallePersonaModal the detallePersonaModal to set
     */
    public void setDetallePersonaModal(Personas detallePersonaModal) {
        this.detallePersonaModal = detallePersonaModal;
    }

    /**
     * @return the listTipoDocumento
     */
    public List<TipoDocumentos> getListTipoDocumento() {
        return listTipoDocumento;
    }

    /**
     * @param listTipoDocumento the listTipoDocumento to set
     */
    public void setListTipoDocumento(List<TipoDocumentos> listTipoDocumento) {
        this.listTipoDocumento = listTipoDocumento;
    }

    /**
     * @return the listEstadoPersonas
     */
    public List<EstadoPersonas> getListEstadoPersonas() {
        return listEstadoPersonas;
    }

    /**
     * @param listEstadoPersonas the listEstadoPersonas to set
     */
    public void setListEstadoPersonas(List<EstadoPersonas> listEstadoPersonas) {
        this.listEstadoPersonas = listEstadoPersonas;
    }

    /**
     * @return the listModulos
     */
    public List<Modulos> getListModulos() {
        return listModulos;
    }

    /**
     * @param listModulos the listModulos to set
     */
    public void setListModulos(List<Modulos> listModulos) {
        this.listModulos = listModulos;
    }

    /**
     * @return the listTodosModulos
     */
    public List<Modulos> getListTodosModulos() {
        return listTodosModulos;
    }

    /**
     * @param listTodosModulos the listTodosModulos to set
     */
    public void setListTodosModulos(List<Modulos> listTodosModulos) {
        this.listTodosModulos = listTodosModulos;
    }

    /**
     * @return the idModuloSelecionado
     */
    public int getIdModuloSelecionado() {
        return idModuloSelecionado;
    }

    /**
     * @param idModuloSelecionado the idModuloSelecionado to set
     */
    public void setIdModuloSelecionado(int idModuloSelecionado) {
        this.idModuloSelecionado = idModuloSelecionado;
    }

    /**
     * @return the listRecursos
     */
    public List<Recursos> getListRecursos() {
        return listRecursos;
    }

    /**
     * @param listRecursos the listRecursos to set
     */
    public void setListRecursos(List<Recursos> listRecursos) {
        this.listRecursos = listRecursos;
    }

    /**
     * @return the documentoPersona
     */
    public String getDocumentoPersona() {
        return documentoPersona;
    }

    /**
     * @param documentoPersona the documentoPersona to set
     */
    public void setDocumentoPersona(String documentoPersona) {
        this.documentoPersona = documentoPersona;
    }

    /**
     * @return the tipoDocumentoPersona
     */
    public int getTipoDocumentoPersona() {
        return tipoDocumentoPersona;
    }

    /**
     * @param tipoDocumentoPersona the tipoDocumentoPersona to set
     */
    public void setTipoDocumentoPersona(int tipoDocumentoPersona) {
        this.tipoDocumentoPersona = tipoDocumentoPersona;
    }

    /**
     * @return the nombreUsuarioNuevo
     */
    public String getNombreUsuarioNuevo() {
        return nombreUsuarioNuevo;
    }

    /**
     * @param nombreUsuarioNuevo the nombreUsuarioNuevo to set
     */
    public void setNombreUsuarioNuevo(String nombreUsuarioNuevo) {
        this.nombreUsuarioNuevo = nombreUsuarioNuevo;
    }

    /**
     * @return the listPersonas
     */
    public List<Personas> getListPersonas() {
        return listPersonas;
    }

    /**
     * @param listPersonas the listPersonas to set
     */
    public void setListPersonas(List<Personas> listPersonas) {
        this.listPersonas = listPersonas;
    }

    /**
     * @return the idRecurso
     */
    public BigDecimal getIdRecurso() {
        return idRecurso;
    }

    /**
     * @param idRecurso the idRecurso to set
     */
    public void setIdRecurso(BigDecimal idRecurso) {
        this.idRecurso = idRecurso;
    }

    /**
     * @return the listConfUsuRec
     */
    public List<ConfUsuRec> getListConfUsuRec() {
        return listConfUsuRec;
    }

    /**
     * @param listConfUsuRec the listConfUsuRec to set
     */
    public void setListConfUsuRec(List<ConfUsuRec> listConfUsuRec) {
        this.listConfUsuRec = listConfUsuRec;
    }

    /**
     * @return the selecionado
     */
    public boolean isSelecionado() {
        return selecionado;
    }

    /**
     * @param selecionado the selecionado to set
     */
    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }
}
