/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Beans;

import CobroCoactivo.Bo.GestionExpedientesBO;
import CobroCoactivo.Bo.GestionExpedientesImpBO;
import CobroCoactivo.Modelo.ArchivoDetExpedientes;
import CobroCoactivo.Modelo.DetalleExpedientes;
import CobroCoactivo.Modelo.DetalleSolicitudes;
import CobroCoactivo.Modelo.Expedientes;
import CobroCoactivo.Modelo.Solicitudes;
import CobroCoactivo.Utility.Log_Handler;
import java.io.File;
import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author emadrid
 *
 */
@ManagedBean(name = "gestionExpedientesBean")
@ViewScoped
public class BeanGestionExpedientes {

    private int idExpediente;
    private int idDetExpediente;
    private int tipoBusqueda;
    private int idSolicitud;
    private int idDetSolicitud;
    private int tipoDocumentoPersona;
    private boolean pnlCarpeta = false;
    private boolean pnlSubcarpetas = false;
    private boolean pnlArchivo = false;
    private boolean pnlBtnAddArchivo = false;
    private boolean pnlExpSelect = false;
    private boolean pnlBtnActualizarFile = false;
    private boolean pnlBtnGuardarFile = true;
    private boolean pnlBtnAddExpediente = false;
    private boolean pnlPersonaEncontrada = false;
    private boolean pnlBtnAddFolder = false;
    private String referenciaExpediente;
    private String idUser;
    private String titleModal = "Agregar Archivo";
    private String nombrePersona;
    private String documentoPersona;
    private List<Expedientes> listExpedientes = new ArrayList<>();
    private List<ArchivoDetExpedientes> listArchivoDetExpedientes = new ArrayList<>();
    private List<DetalleExpedientes> listDetalleExpedientes = new ArrayList<>();
    private List<DetalleExpedientes> listDetalleExpdientesSelect = new ArrayList<>();
    private List<Solicitudes> listSolicitudes = new ArrayList<>();
    private List<DetalleSolicitudes> listDetalleSolicitudes = new ArrayList<>();
    private Part file;
    private ArchivoDetExpedientes archivoDetExpedientes = new ArchivoDetExpedientes();
    private GestionExpedientesBO gestionExpedientesBO;
    private BeanLogin loginBO;

    @PostConstruct
    public void init() {
        try {
            setGestionExpedientesBO(new GestionExpedientesImpBO());
            getGestionExpedientesBO().cargarSolicitudes(this);
            FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            BeanLogin obj_ = ((BeanLogin) session.getAttribute("loginBean"));
            setPnlBtnAddExpediente(true);
            if (obj_.getID_Usuario() != null) {
                setIdUser(obj_.getID_Usuario());
            }
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    public void crearExpedientes() {
        try {
            getGestionExpedientesBO().crearExpediente(this);
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    public void buscarPersona() {
        try {
            getGestionExpedientesBO().buscarPersona(this);
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    public void buscarExpedientes(int tipobusqueda) {
        try {
            setTipoBusqueda(tipobusqueda);
            getGestionExpedientesBO().buscarExpediente(this);
            if (listExpedientes.size() > 0) {
                setPnlCarpeta(true);
                setPnlArchivo(false);
                setPnlSubcarpetas(false);
                setPnlExpSelect(false);
                setPnlBtnAddExpediente(false);
                setPnlBtnAddArchivo(false);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se encontro el expediente, por favor verifique la referencia.", null));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    public void abrirCarpeta(int idExpediente) {
        try {
            setIdExpediente(idExpediente);
            getGestionExpedientesBO().abrirExpediente(this);
            if (listDetalleExpedientes.size() > 0) {
                setPnlCarpeta(false);
                setPnlSubcarpetas(true);
                setPnlBtnAddFolder(true);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se encontro deudas en el expediente de la persona.", null));
                setPnlBtnAddFolder(true);
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    public void buscarArchivo(int idDetExpediente) {
        try {
            setIdDetExpediente(idDetExpediente);
            getGestionExpedientesBO().buscarArchivo(this);
            setPnlBtnAddArchivo(true);
            if (listArchivoDetExpedientes.size() > 0) {
                setPnlSubcarpetas(false);
                setPnlArchivo(true);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No hay archivo en esta carpeta, agrege archivo por favor.", null));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    public void mostrarPdf(String data1) {
        try {

            File ficheroXLS = new File(data1);
            FacesContext ctx = FacesContext.getCurrentInstance();
            FileInputStream fis = new FileInputStream(ficheroXLS);
            byte[] bytes = new byte[1000];
            int read = 0;
            if (!ctx.getResponseComplete()) {
                String fileName = ficheroXLS.getName();
                String contentType = "application/pdf";
                HttpServletResponse response
                        = (HttpServletResponse) ctx.getExternalContext().getResponse();
                response.setContentType(contentType);
                response.setHeader("Content-Disposition",
                        "attachment;filename=\"" + fileName + "\"");
                ServletOutputStream out = response.getOutputStream();
                while ((read = fis.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                out.flush();
                out.close();
                ctx.responseComplete();
            }

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    public void guardarArchivo() {
        try {
            getGestionExpedientesBO().guardarArchivo(this);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    public void mostrarExpedienteSelect() {
        try {
            getGestionExpedientesBO().mostrarExpedienteSelect(this);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    public void enviarSolicitudes() {
        try {
            getGestionExpedientesBO().enviarSolicitud(this);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    public void buscarDetSolicitudes(int idSolicitud) {
        try {
            setIdSolicitud(idSolicitud);
            getGestionExpedientesBO().buscarDetSolicitudes(this);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    public void aceptarSolicitudExpedientes() {
        try {
            getGestionExpedientesBO().aceptarSolicitudExpediente(this);
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    public void modalActualizarArchivo(ArchivoDetExpedientes archivoDetExpedientes) {
        try {
            setPnlBtnGuardarFile(false);
            setFile(null);
            String title = "Actualizar archivo";
            setPnlBtnActualizarFile(true);
            setTitleModal(title);
            setArchivoDetExpedientes(archivoDetExpedientes);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }

    }

    public void updateArchivo() {
        try {
            getGestionExpedientesBO().updateArchivo(this);
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Log_Handler.solucionError(e)));
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gestionParametros" + "messageGeneral");
        }
    }

    /**
     * @return the gestionExpedientesBO
     */
    public GestionExpedientesBO getGestionExpedientesBO() {
        return gestionExpedientesBO;
    }

    /**
     * @param gestionExpedientesBO the gestionExpedientesBO to set
     */
    public void setGestionExpedientesBO(GestionExpedientesBO gestionExpedientesBO) {
        this.gestionExpedientesBO = gestionExpedientesBO;
    }

    /**
     * @return the listExpedientes
     */
    public List<Expedientes> getListExpedientes() {
        return listExpedientes;
    }

    /**
     * @param listExpedientes the listExpedientes to set
     */
    public void setListExpedientes(List<Expedientes> listExpedientes) {
        this.listExpedientes = listExpedientes;
    }

    /**
     * @return the referenciaExpediente
     */
    public String getReferenciaExpediente() {
        return referenciaExpediente;
    }

    /**
     * @param referenciaExpediente the referenciaExpediente to set
     */
    public void setReferenciaExpediente(String referenciaExpediente) {
        this.referenciaExpediente = referenciaExpediente;
    }

    /**
     * @return the listDetalleExpedientes
     */
    public List<DetalleExpedientes> getListDetalleExpedientes() {
        return listDetalleExpedientes;
    }

    /**
     * @param listDetalleExpedientes the listDetalleExpedientes to set
     */
    public void setListDetalleExpedientes(List<DetalleExpedientes> listDetalleExpedientes) {
        this.listDetalleExpedientes = listDetalleExpedientes;
    }

    /**
     * @return the idExpediente
     */
    public int getIdExpediente() {
        return idExpediente;
    }

    /**
     * @param idExpediente the idExpediente to set
     */
    public void setIdExpediente(int idExpediente) {
        this.idExpediente = idExpediente;
    }

    /**
     * @return the pnlCarpeta
     */
    public boolean isPnlCarpeta() {
        return pnlCarpeta;
    }

    /**
     * @param pnlCarpeta the pnlCarpeta to set
     */
    public void setPnlCarpeta(boolean pnlCarpeta) {
        this.pnlCarpeta = pnlCarpeta;
    }

    /**
     * @return the pnlSubcarpetas
     */
    public boolean isPnlSubcarpetas() {
        return pnlSubcarpetas;
    }

    /**
     * @param pnlSubcarpetas the pnlSubcarpetas to set
     */
    public void setPnlSubcarpetas(boolean pnlSubcarpetas) {
        this.pnlSubcarpetas = pnlSubcarpetas;
    }

    /**
     * @return the idDetExpediente
     */
    public int getIdDetExpediente() {
        return idDetExpediente;
    }

    /**
     * @param idDetExpediente the idDetExpediente to set
     */
    public void setIdDetExpediente(int idDetExpediente) {
        this.idDetExpediente = idDetExpediente;
    }

    /**
     * @return the listArchivoDetExpedientes
     */
    public List<ArchivoDetExpedientes> getListArchivoDetExpedientes() {
        return listArchivoDetExpedientes;
    }

    /**
     * @param listArchivoDetExpedientes the listArchivoDetExpedientes to set
     */
    public void setListArchivoDetExpedientes(List<ArchivoDetExpedientes> listArchivoDetExpedientes) {
        this.listArchivoDetExpedientes = listArchivoDetExpedientes;
    }

    /**
     * @return the pnlArchivo
     */
    public boolean isPnlArchivo() {
        return pnlArchivo;
    }

    /**
     * @param pnlArchivo the pnlArchivo to set
     */
    public void setPnlArchivo(boolean pnlArchivo) {
        this.pnlArchivo = pnlArchivo;
    }

    /**
     * @return the file
     */
    public Part getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(Part file) {
        this.file = file;
    }

    /**
     * @return the pnlBtnAddArchivo
     */
    public boolean isPnlBtnAddArchivo() {
        return pnlBtnAddArchivo;
    }

    /**
     * @param pnlBtnAddArchivo the pnlBtnAddArchivo to set
     */
    public void setPnlBtnAddArchivo(boolean pnlBtnAddArchivo) {
        this.pnlBtnAddArchivo = pnlBtnAddArchivo;
    }

    /**
     * @return the listDetalleExpdientesSelect
     */
    public List<DetalleExpedientes> getListDetalleExpdientesSelect() {
        return listDetalleExpdientesSelect;
    }

    /**
     * @param listDetalleExpdientesSelect the listDetalleExpdientesSelect to set
     */
    public void setListDetalleExpdientesSelect(List<DetalleExpedientes> listDetalleExpdientesSelect) {
        this.listDetalleExpdientesSelect = listDetalleExpdientesSelect;
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
     * @return the pnlExpSelect
     */
    public boolean isPnlExpSelect() {
        return pnlExpSelect;
    }

    /**
     * @param pnlExpSelect the pnlExpSelect to set
     */
    public void setPnlExpSelect(boolean pnlExpSelect) {
        this.pnlExpSelect = pnlExpSelect;
    }

    /**
     * @return the loginBO
     */
    public BeanLogin getLoginBO() {
        return loginBO;
    }

    /**
     * @param loginBO the loginBO to set
     */
    public void setLoginBO(BeanLogin loginBO) {
        this.loginBO = loginBO;
    }

    /**
     * @return the idUser
     */
    public String getIdUser() {
        return idUser;
    }

    /**
     * @param idUser the idUser to set
     */
    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    /**
     * @return the listSolicitudes
     */
    public List<Solicitudes> getListSolicitudes() {
        return listSolicitudes;
    }

    /**
     * @param listSolicitudes the listSolicitudes to set
     */
    public void setListSolicitudes(List<Solicitudes> listSolicitudes) {
        this.listSolicitudes = listSolicitudes;
    }

    /**
     * @return the idSolicitud
     */
    public int getIdSolicitud() {
        return idSolicitud;
    }

    /**
     * @param idSolicitud the idSolicitud to set
     */
    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    /**
     * @return the listDetalleSolicitudes
     */
    public List<DetalleSolicitudes> getListDetalleSolicitudes() {
        return listDetalleSolicitudes;
    }

    /**
     * @param listDetalleSolicitudes the listDetalleSolicitudes to set
     */
    public void setListDetalleSolicitudes(List<DetalleSolicitudes> listDetalleSolicitudes) {
        this.listDetalleSolicitudes = listDetalleSolicitudes;
    }

    /**
     * @return the idDetSolicitud
     */
    public int getIdDetSolicitud() {
        return idDetSolicitud;
    }

    /**
     * @param idDetSolicitud the idDetSolicitud to set
     */
    public void setIdDetSolicitud(int idDetSolicitud) {
        this.idDetSolicitud = idDetSolicitud;
    }

    /**
     * @return the titleModal
     */
    public String getTitleModal() {
        return titleModal;
    }

    /**
     * @param titleModal the titleModal to set
     */
    public void setTitleModal(String titleModal) {
        this.titleModal = titleModal;
    }

    /**
     * @return the archivoDetExpedientes
     */
    public ArchivoDetExpedientes getArchivoDetExpedientes() {
        return archivoDetExpedientes;
    }

    /**
     * @param archivoDetExpedientes the archivoDetExpedientes to set
     */
    public void setArchivoDetExpedientes(ArchivoDetExpedientes archivoDetExpedientes) {
        this.archivoDetExpedientes = archivoDetExpedientes;
    }

    /**
     * @return the pnlBtnActualizarFile
     */
    public boolean isPnlBtnActualizarFile() {
        return pnlBtnActualizarFile;
    }

    /**
     * @param pnlBtnActualizarFile the pnlBtnActualizarFile to set
     */
    public void setPnlBtnActualizarFile(boolean pnlBtnActualizarFile) {
        this.pnlBtnActualizarFile = pnlBtnActualizarFile;
    }

    /**
     * @return the pnlBtnGuardarFile
     */
    public boolean isPnlBtnGuardarFile() {
        return pnlBtnGuardarFile;
    }

    /**
     * @param pnlBtnGuardarFile the pnlBtnGuardarFile to set
     */
    public void setPnlBtnGuardarFile(boolean pnlBtnGuardarFile) {
        this.pnlBtnGuardarFile = pnlBtnGuardarFile;
    }

    /**
     * @return the pnlBtnAddExpediente
     */
    public boolean isPnlBtnAddExpediente() {
        return pnlBtnAddExpediente;
    }

    /**
     * @param pnlBtnAddExpediente the pnlBtnAddExpediente to set
     */
    public void setPnlBtnAddExpediente(boolean pnlBtnAddExpediente) {
        this.pnlBtnAddExpediente = pnlBtnAddExpediente;
    }

    /**
     * @return the nombrePersona
     */
    public String getNombrePersona() {
        return nombrePersona;
    }

    /**
     * @param nombrePersona the nombrePersona to set
     */
    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
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
     * @return the pnlPersonaEncontrada
     */
    public boolean isPnlPersonaEncontrada() {
        return pnlPersonaEncontrada;
    }

    /**
     * @param pnlPersonaEncontrada the pnlPersonaEncontrada to set
     */
    public void setPnlPersonaEncontrada(boolean pnlPersonaEncontrada) {
        this.pnlPersonaEncontrada = pnlPersonaEncontrada;
    }

    /**
     * @return the pnlBtnAddFolder
     */
    public boolean isPnlBtnAddFolder() {
        return pnlBtnAddFolder;
    }

    /**
     * @param pnlBtnAddFolder the pnlBtnAddFolder to set
     */
    public void setPnlBtnAddFolder(boolean pnlBtnAddFolder) {
        this.pnlBtnAddFolder = pnlBtnAddFolder;
    }

}
