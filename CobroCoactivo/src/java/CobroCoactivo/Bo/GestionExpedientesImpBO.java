/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanGestionExpedientes;
import CobroCoactivo.Dao.DaoArchivoDetExpedientes;
import CobroCoactivo.Dao.DaoDetalleExpedientes;
import CobroCoactivo.Dao.DaoExpedientes;
import CobroCoactivo.Dao.DaoUsuarios;
import CobroCoactivo.Dao.ITArchivoDetExpedientes;
import CobroCoactivo.Dao.ITDetalleExpedientes;
import CobroCoactivo.Dao.ITExpedientes;
import CobroCoactivo.Dao.ITUsuarios;
import CobroCoactivo.Modelo.ArchivoDetExpedientes;
import CobroCoactivo.Modelo.DetalleExpedientes;
import CobroCoactivo.Modelo.Expedientes;
import CobroCoactivo.Persistencia.CivArchivoDetExpedientes;
import CobroCoactivo.Persistencia.CivDetalleExpedientes;
import CobroCoactivo.Persistencia.CivEstadoArchDetExp;
import CobroCoactivo.Persistencia.CivEstadoSolicitudes;
import CobroCoactivo.Persistencia.CivExpedientes;
import CobroCoactivo.Persistencia.CivSolicitudes;
import CobroCoactivo.Persistencia.CivUsuarios;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author emadrid
 */
public class GestionExpedientesImpBO implements GestionExpedientesBO, Serializable {

    private ITExpedientes expedientesDAO;
    private ITDetalleExpedientes detalleExpedientesDAO;
    private ITArchivoDetExpedientes archivoDetExpedientesDAO;
    private ITUsuarios usuariosDAO;

    public GestionExpedientesImpBO() {
        expedientesDAO = new DaoExpedientes();
        detalleExpedientesDAO = new DaoDetalleExpedientes();
        archivoDetExpedientesDAO = new DaoArchivoDetExpedientes();
        usuariosDAO = new DaoUsuarios();
    }

    @Override
    public void buscarExpediente(BeanGestionExpedientes bean) throws Exception {
        List<CivExpedientes> listCivExpedientes = new ArrayList<>();
        bean.setListExpedientes(new ArrayList<>());
        switch (bean.getTipoBusqueda()) {
            case 1:
                listCivExpedientes = getExpedientesDAO().getCivExpedientes(bean.getReferenciaExpediente());
                break;
        }
        if (listCivExpedientes != null) {
            for (CivExpedientes civExpedientes : listCivExpedientes) {
                Expedientes expedientes = new Expedientes(civExpedientes, civExpedientes.getCivTipoExpedientes(), civExpedientes.getCivEstadoExpedientes());
                bean.getListExpedientes().add(expedientes);
            }
        }
    }

    @Override
    public void abrirExpediente(BeanGestionExpedientes bean) throws Exception {
        bean.setListDetalleExpedientes(new ArrayList<>());
        List<CivDetalleExpedientes> listCivDetalleExpedientes = getDetalleExpedientesDAO().getListCivDetalleExpediente(bean.getIdExpediente());
        if (listCivDetalleExpedientes != null) {
            for (CivDetalleExpedientes civDetalleExpediente : listCivDetalleExpedientes) {
                DetalleExpedientes detalleExpedientes = new DetalleExpedientes(civDetalleExpediente, civDetalleExpediente.getCivExpedientes(), civDetalleExpediente.getCivEstadoDetalleExpedientes(), civDetalleExpediente.getCivTipoDetalleExpedientes());
                bean.getListDetalleExpedientes().add(detalleExpedientes);
            }
        }
    }

    @Override
    public void buscarArchivo(BeanGestionExpedientes bean) throws Exception {
        bean.setListArchivoDetExpedientes(new ArrayList<>());
        List<CivArchivoDetExpedientes> listCivArchivoDetExpedientes = getArchivoDetExpedientesDAO().getCivArchivoDetExpedientes(bean.getIdDetExpediente());
        if (listCivArchivoDetExpedientes != null) {
            for (CivArchivoDetExpedientes civArchivoDetExpediente : listCivArchivoDetExpedientes) {
                ArchivoDetExpedientes archivoDetExpedientes = new ArchivoDetExpedientes(civArchivoDetExpediente, civArchivoDetExpediente.getCivEstadoArchDetExp(), civArchivoDetExpediente.getCivDetalleExpedientes());
                bean.getListArchivoDetExpedientes().add(archivoDetExpedientes);
            }
        }
    }

    @Override
    public void guardarArchivo(BeanGestionExpedientes bean) throws Exception {
        if (Paths.get(bean.getFile().getSubmittedFileName()).getFileName().toString().endsWith(".pdf")) {
            CivArchivoDetExpedientes archivoDetExpedientes = new CivArchivoDetExpedientes();
            CivEstadoArchDetExp estadoArchDetExp = new CivEstadoArchDetExp();
            estadoArchDetExp.setEstarcdetexpId(BigDecimal.ONE);
            CivDetalleExpedientes civDetalleExpedientes = getDetalleExpedientesDAO().find(new BigDecimal(bean.getIdDetExpediente()));
            archivoDetExpedientes.setArcdetexpNombre(Paths.get(bean.getFile().getSubmittedFileName()).getFileName().toString());
            archivoDetExpedientes.setArcdetexpFechaproceso(new Date());
            archivoDetExpedientes.setCivDetalleExpedientes(civDetalleExpedientes);
            archivoDetExpedientes.setArcdetexpUbicacion(civDetalleExpedientes.getDetexpUbicacion() + "/" + Paths.get(bean.getFile().getSubmittedFileName()).getFileName().toString());
            archivoDetExpedientes.setCivEstadoArchDetExp(estadoArchDetExp);
            InputStream stream = bean.getFile().getInputStream();
            Files.copy(stream, new File(civDetalleExpedientes.getDetexpUbicacion() + "/" + Paths.get(bean.getFile().getSubmittedFileName()).getFileName().toString()).toPath(), StandardCopyOption.REPLACE_EXISTING);
            getArchivoDetExpedientesDAO().create(archivoDetExpedientes);
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("$('#addArchivo').modal('hide')");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se agregado el archivo correctamente.", null));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Solo se puede cargar archivo pdf", null));
        }
    }

    @Override
    public void mostrarExpedienteSelect(BeanGestionExpedientes bean) throws Exception {
        bean.setListDetalleExpdientesSelect(new ArrayList<>());
        int increment = -1;
        for (int i = 0; i < bean.getListDetalleExpedientes().size(); i++) {
            increment++;
            DetalleExpedientes detExp = bean.getListDetalleExpedientes().get(i);
            if (detExp.isSelecionado() == true) {
                if (increment == i) {
                    CivDetalleExpedientes civDetalleExpedientes = getDetalleExpedientesDAO().find(BigDecimal.valueOf(detExp.getDetexpId()));
                    DetalleExpedientes detalleExpedientes = new DetalleExpedientes(civDetalleExpedientes);
                    bean.getListDetalleExpdientesSelect().add(detalleExpedientes);
                }
            }
        }
        if (bean.getListDetalleExpdientesSelect().size() > 0) {
            bean.setPnlExpSelect(true);
        } else {
            bean.setPnlExpSelect(false);
        }
    }

    @Override
    public void enviarSolicitud(BeanGestionExpedientes bean) throws Exception {
        if (bean.getListDetalleExpdientesSelect().size() > 0) {
            CivSolicitudes civSolicitudes = new CivSolicitudes();
            CivEstadoSolicitudes civEstadoSolicitudes = new CivEstadoSolicitudes();
            civEstadoSolicitudes.setEstsolId(BigDecimal.ONE);
           // CivUsuarios civUsuarios = getUsuariosDAO().find(BigDecimal.valueOf());
        }
    }

    public String generarMatricula() {
        String referencia = "";
        int a;
        for (int i = 0; i < 7; i++) {
            if (i < 4) {    // 0,1,2,3 posiciones de numeros
                referencia = (int) (Math.random() * 9) + "" + referencia;
            } else {       // 4,5,6 posiciones de letras
                do {
                    a = (int) (Math.random() * 26 + 65);///
                } while (a == 65 || a == 69 || a == 73 || a == 79 || a == 85);
                char letra = (char) a;
                if (i == 4) {
                    referencia = referencia + "-" + letra;
                } else {
                    referencia = referencia + "" + letra;
                }
            }
        }
        return referencia;
    }

    /**
     * @return the expedientesDAO
     */
    public ITExpedientes getExpedientesDAO() {
        return expedientesDAO;
    }

    /**
     * @param expedientesDAO the expedientesDAO to set
     */
    public void setExpedientesDAO(ITExpedientes expedientesDAO) {
        this.expedientesDAO = expedientesDAO;
    }

    /**
     * @return the detalleExpedientesDAO
     */
    public ITDetalleExpedientes getDetalleExpedientesDAO() {
        return detalleExpedientesDAO;
    }

    /**
     * @param detalleExpedientesDAO the detalleExpedientesDAO to set
     */
    public void setDetalleExpedientesDAO(ITDetalleExpedientes detalleExpedientesDAO) {
        this.detalleExpedientesDAO = detalleExpedientesDAO;
    }

    /**
     * @return the archivoDetExpedientesDAO
     */
    public ITArchivoDetExpedientes getArchivoDetExpedientesDAO() {
        return archivoDetExpedientesDAO;
    }

    /**
     * @param archivoDetExpedientesDAO the archivoDetExpedientesDAO to set
     */
    public void setArchivoDetExpedientesDAO(ITArchivoDetExpedientes archivoDetExpedientesDAO) {
        this.archivoDetExpedientesDAO = archivoDetExpedientesDAO;
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

}
