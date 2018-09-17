package CobroCoactivo.Beans;

import CobroCoactivo.Modelo.Modulos;
import CobroCoactivo.Exception.LoginException;
import CobroCoactivo.Bo.LoginBO;
import CobroCoactivo.Bo.LoginImplBO;
import CobroCoactivo.Crypto.DigestHandler;
import CobroCoactivo.Exception.PasswordException;
import CobroCoactivo.Modelo.Usuarios;
import CobroCoactivo.Persistencia.CivConfUsuRec;
import CobroCoactivo.Utility.Log_Handler;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

/**
 *
 * @author Roymer Camacho
 */
@ManagedBean(name = "loginBean")
@SessionScoped
public class BeanLogin implements Serializable {

    private static final String REGEX = "^[a-zA-Z0-9/-@ ]*$";
    private static final String REGEXNUMERO = "^[0-9]*$";
    private static final String REGEXPASSWORD = "^[a-zA-Z0-9]*$";
//    private static final String REGEX_MAIL = "^[a-zA-Z0-9/-@. ]*$";
    private static final String REGEX_MAIL = "^$|^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
    private static final String REGEX_DIRECCION = "^[a-zA-Z0-9-@.#_ ]*$";
    private static final String VALIDATOR_MENSAJE = "inválido/a. Revise que el campo no tenga caracteres especiales como tildes, puntos o numerales.";

    private String id_usuario;
    private String user = "";
    private String password;
    private String nombre;
    private char tipoDocumentoRUNT;
    private List<BeanLogin> listaUsuarios;
    private List<Modulos> listModulos;
    private LoginBO loginBO;
    private int sede;
    private long dias_vencimiento = -1;
    private boolean aut_RUNT;
    private Date fecha_;
    private String root;
    private Map<Integer, String> notificationMap;
    private List<String> listRecursos;
    private int userEstado;
    private boolean hasAccess;
    private Usuarios usuarios;
    private int idPersonaUsuario;
    private String nombrePersonaUsuario;
    private String cedulaPersonaUsuario;
    private Date fechaInicioPersonaUsuario;
    private String contraseñaConfirmacion;
    private String contraseñaNueva;

    private boolean Ad = false;
    private boolean Op = false;
    private int tipo;
    private String plantilla;
    private Client client;

    private List<CivConfUsuRec> listUsuarioRecursos;
    private List<CivConfUsuRec> listRedireccion;

    /**
     *
     * @return
     */
    public String autenticarUsuario() {
        try {
            setLoginBO(new LoginImplBO());
//            Log_Handler.registrarEvento("Tiempo fuera de lugar del reloj: " + NTPClient.retrasoReloj(), null, Log_Handler.INFO, getClass(),Integer.parseInt(loginBean.getID_Usuario()));
            setNotificationMap(new LinkedHashMap<>());
            getLoginBO().iniciarSesion(this); //Se cargan datos y ejecutan validaciones de usuario.
            //setUsuarios();
//            getLoginBO().listarPerfilRecursos(this);
            setListModulos(getLoginBO().listarModulos(this)); //Se carga el menu correspondiente al usuario
            setListRecursos(getLoginBO().listarRecursos(this)); // Se cargan los recursos correspondientes al usuario
            //         AuthSingleton.getInstancia().reesstablecerFuncionario(Integer.parseInt(id_usuario)); //Se reestablecen credenciales de RUNT
            setNombre(getNombre().toUpperCase(Locale.ROOT));
            validarAcceso(); //Revisar Estado del Usuario
            getLoginBO().consultarDatosUsuario(this);

            // setRoot("/resources/dist/img/" + (getID_Usuario().equals("5") ? "avatar3.png" : "user2-160x160.jpg"));
            // setRoot("/resources/images/transito_avatar.png");
            return ejecutarDestino();
        } catch (LoginException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", e.getMessage()));
            return "";
        } catch (Exception e) {
            Log_Handler.registrarEvento("Error iniciando sesion: ", e, Log_Handler.ERROR, getClass(), (getID_Usuario() != null) ? Integer.parseInt(getID_Usuario()) : 0);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            return "";
        }
    }

    private String ejecutarDestino() throws IOException {
        if (getUserEstado() == 3) { //Usuario Por reestablecer credenciales
            return "/restablecer?faces-redirect=true";
        } else {
            return "/inicio?faces-redirect=true"; //Redirect=true obligatorio para validaciones de filtro
        }
    }

    public void validarAcceso() {

        switch (getUserEstado()) {
            case 0: //Desactivada
            case 3: //Por reestablecer
            case 4: //Bloqueado
            case 2: //Deshabilitado
                this.hasAccess = false;
                break;
            case 1: //Normal
                this.hasAccess = true;
                break;
            default:
                this.hasAccess = false;
                break;
        }
    }

    public String actualizarContraseña() {
        try {
            if (!getUsuarios().getPass().equals(DigestHandler.encryptSHA2(password))) {
                throw new PasswordException("La contraseña actual no es correcta", 3);
            }
            if (getUsuarios().getPass().equals(DigestHandler.encryptSHA2(password)) && !DigestHandler.encryptSHA2(getContraseñaNueva()).equals(DigestHandler.encryptSHA2(contraseñaConfirmacion))) {
                throw new PasswordException("La contraseña nueva no coincide.", 3);
            }
            if (getUsuarios().getPass().equals(DigestHandler.encryptSHA2(password)) && DigestHandler.encryptSHA2(getContraseñaNueva()).equals(DigestHandler.encryptSHA2(getContraseñaConfirmacion()))) {
                if (DigestHandler.encryptSHA2(getContraseñaNueva()).equals(DigestHandler.encryptSHA2(password))) {
                    throw new PasswordException("La contraseña nueva no puede ser igual a la actual.", 3);
                } else {
                    if (contraseñaConfirmacion.length() < 6) {
                        throw new PasswordException("La contraseña tiene que tener min 7 caracteres.", 3);
                    } else {
                        getLoginBO().actualizarContraseña(this);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "La contraseña actualizada correctamente", null));
                        validarAcceso();
                        return "/inicio?faces-redirect=true";
                    }
                }
            }
        } catch (PasswordException pe) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", pe.getMessage()));
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            Log_Handler.registrarEvento("Error al actualizar contraseña: ", e, Log_Handler.ERROR, getClass(), (getID_Usuario() != null) ? Integer.parseInt(getID_Usuario()) : 0);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
        return "";
    }

    public Long numeroDeudas() throws JSONException {
        client = ClientBuilder.newClient();

        WebTarget baTarget = client.target("http://localhost:8080/WebServiceContraversiones/api/cartera/numero");
        if (baTarget.request(MediaType.APPLICATION_JSON).get().getStatus() == 200) {
            String data = baTarget.request(MediaType.APPLICATION_JSON).get(String.class);
            JSONObject jSONObject = new JSONObject(data);
            if (!jSONObject.isNull("numero")) {
                return jSONObject.getLong("numero");
            }
        }
        return null;
    }

    public Long numeroPagos() {
        try {
            client = ClientBuilder.newClient();
            WebTarget baseTarget = client.target("http://10.10.2.204:8080/WebServiceContraversiones/api/pagos/numero");
            if (baseTarget.request(MediaType.APPLICATION_JSON).get().getStatus() == 200) {
                String data = baseTarget.request(MediaType.APPLICATION_JSON).get(String.class);
                JSONObject jSONObject = new JSONObject(data);
                if (!jSONObject.isNull("numero")) {
                    return jSONObject.getLong("numero");
                }
            }

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
            return null;
        }
    }

    public String getCedulaPersonaUsuario() {
        return cedulaPersonaUsuario;
    }

    /**
     * @param cedulaPersonaUsuario
     */
    public void setCedulaPersonaUsuario(String cedulaPersonaUsuario) {
        this.cedulaPersonaUsuario = cedulaPersonaUsuario;
    }

    public String getNombrePersonaUsuario() {
        return nombrePersonaUsuario;
    }

    /**
     * @param user the user to set
     */
    public void setNombrePersonaUsuario(String user) {
        this.nombrePersonaUsuario = user;
    }

    public Date getFechaInicioPersonaUsuario() {
        return fechaInicioPersonaUsuario;
    }

    public void setFechaInicioPersonaUsuario(Date fechaInicioPersonaUsuario) {
        this.fechaInicioPersonaUsuario = fechaInicioPersonaUsuario;
    }

    public int getIdPersonaUsuario() {
        return idPersonaUsuario;
    }

    /**
     * @param user the user to set
     */
    public void setIdPersonaUsuario(int user) {
        this.idPersonaUsuario = user;
    }

    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the listaUsuarios
     */
    public List<BeanLogin> getListaUsuarios() {
        return listaUsuarios;
    }

    /**
     * @param listaUsuarios the listaUsuarios to set
     */
    public void setListaUsuarios(List<BeanLogin> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    /**
     * @return the loginBO
     */
    public LoginBO getLoginBO() {
        return loginBO;
    }

    /**
     * @param loginBO the loginBO to set
     */
    public void setLoginBO(LoginBO loginBO) {
        this.loginBO = loginBO;
    }

    /**
     * @return the id_usuario
     */
    public String getID_Usuario() {
        return id_usuario;
    }

    /**
     * @param id_usuario
     */
    public void setID_Usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    /**
     * @return the sede
     */
    public int getSede() {
        return sede;
    }

    /**
     * @param sede the sede to set
     */
    public void setSede(int sede) {
        this.sede = sede;
    }

    /**
     * @return the TipoDocumentoRUNT
     */
    public char getTipo_documento_runt() {
        return tipoDocumentoRUNT;
    }

    /**
     * @param tipo_documento_runt the TipoDocumentoRUNT to set
     */
    public void setTipo_documento_runt(char tipo_documento_runt) {
        this.tipoDocumentoRUNT = tipo_documento_runt;
    }

    /**
     * @return the aut_RUNT
     */
    public boolean isAut_RUNT() {
        return aut_RUNT;
    }

    /**
     * @param aut_RUNT the aut_RUNT to set
     */
    public void setAut_RUNT(boolean aut_RUNT) {
        this.aut_RUNT = aut_RUNT;
    }

    /**
     *
     * @return
     */
    public String getRegex() {
        return REGEX;
    }

    /**
     * @return the fecha_
     */
    public Date getFecha_() {
        return fecha_;
    }

    /**
     * @param fecha_ the fecha_ to set
     */
    public void setFecha_(Date fecha_) {
        this.fecha_ = fecha_;
    }

    /**
     * @return the dias_vencimiento
     */
    public long getDias_vencimiento() {
        return dias_vencimiento;
    }

    /**
     * @param dias_vencimiento the dias_vencimiento to set
     */
    public void setDias_vencimiento(long dias_vencimiento) {
        this.dias_vencimiento = dias_vencimiento;
    }

    /**
     * @return the root
     */
    public String getRoot() {
        return root;
    }

    /**
     * @param root the root to set
     */
    public void setRoot(String root) {
        this.root = root;
    }

    /**
     * @return the notificationMap
     */
    public Map<Integer, String> getNotificationMap() {
        return notificationMap;
    }

    /**
     * @param notificationMap the notificationMap to set
     */
    public void setNotificationMap(Map<Integer, String> notificationMap) {
        this.notificationMap = notificationMap;
    }

    /**
     * @return the listRecursos
     */
    public List<String> getListRecursos() {
        return listRecursos;
    }

    /**
     * @param listRecursos the listRecursos to set
     */
    public void setListRecursos(List<String> listRecursos) {
        this.listRecursos = listRecursos;
    }

    /**
     * @return the VALIDATOR_MENSAJE
     */
    public String getVALIDATOR_MENSAJE() {
        return VALIDATOR_MENSAJE;
    }

    public String getREGEXDIRECCION() {
        return REGEX_DIRECCION;
    }

    /**
     * @return the REGEXPASSWORD
     */
    public String getREGEXPASSWORD() {
        return REGEXPASSWORD;
    }

    /**
     * @return the userEstado
     */
    public int getUserEstado() {
        return userEstado;
    }

    /**
     * @param userEstado the userEstado to set
     */
    public void setUserEstado(int userEstado) {
        this.userEstado = userEstado;
    }

    /**
     * @return the hasAccess
     */
    public boolean isHasAccess() {
        return hasAccess;
    }

    /**
     * @param hasAccess the hasAccess to set
     */
    public void setHasAccess(boolean hasAccess) {
        this.hasAccess = hasAccess;
    }

    /**
     * @return the REGEX_MAIL
     */
    public String getREGEX_MAIL() {
        return REGEX_MAIL;
    }

    /**
     * @return the REGEXNUMERO
     */
    public String getREGEXNUMERO() {
        return REGEXNUMERO;
    }

    /**
     * @return the plantilla
     */
    public String getPlantilla() {
        return plantilla;
    }

    /**
     * @param plantilla the plantilla to set
     */
    public void setPlantilla(String plantilla) {
        this.plantilla = plantilla;
    }

    /**
     * @return the Ad
     */
    public boolean isAd() {
        return Ad;
    }

    /**
     * @param Ad the Ad to set
     */
    public void setAd(boolean Ad) {
        this.Ad = Ad;
    }

    /**
     * @return the Op
     */
    public boolean isOp() {
        return Op;
    }

    /**
     * @param Op the Op to set
     */
    public void setOp(boolean Op) {
        this.Op = Op;
    }

    /**
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the listUsuarioRecursos
     */
    public List<CivConfUsuRec> getListUsuarioRecursos() {
        return listUsuarioRecursos;
    }

    /**
     * @param listUsuarioRecursos the listUsuarioRecursos to set
     */
    public void setListUsuarioRecursos(List<CivConfUsuRec> listUsuarioRecursos) {
        this.listUsuarioRecursos = listUsuarioRecursos;
    }

    /**
     * @return the listRedireccion
     */
    public List<CivConfUsuRec> getListRedireccion() {
        return listRedireccion;
    }

    /**
     * @param listRedireccion the listRedireccion to set
     */
    public void setListRedireccion(List<CivConfUsuRec> listRedireccion) {
        this.listRedireccion = listRedireccion;
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
     * @return the usuarios
     */
    public Usuarios getUsuarios() {
        return usuarios;
    }

    /**
     * @param usuarios the usuarios to set
     */
    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
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
     * @return the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(Client client) {
        this.client = client;
    }

}
