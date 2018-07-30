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
import CobroCoactivo.Dao.ITArchivoDetExpedientes;
import CobroCoactivo.Dao.ITDetalleExpedientes;
import CobroCoactivo.Dao.ITExpedientes;
import CobroCoactivo.Modelo.ArchivoDetExpedientes;
import CobroCoactivo.Modelo.DetalleExpedientes;
import CobroCoactivo.Modelo.Expedientes;
import CobroCoactivo.Persistencia.CivArchivoDetExpedientes;
import CobroCoactivo.Persistencia.CivDetalleExpedientes;
import CobroCoactivo.Persistencia.CivEstadoArchDetExp;
import CobroCoactivo.Persistencia.CivExpedientes;
import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author emadrid
 */
public class GestionExpedientesImpBO implements GestionExpedientesBO, Serializable {

    private ITExpedientes expedientesDAO;
    private ITDetalleExpedientes detalleExpedientesDAO;
    private ITArchivoDetExpedientes archivoDetExpedientesDAO;

    public GestionExpedientesImpBO() {
        expedientesDAO = new DaoExpedientes();
        detalleExpedientesDAO = new DaoDetalleExpedientes();
        archivoDetExpedientesDAO = new DaoArchivoDetExpedientes();
    }

    @Override
    public void buscarExpediente(BeanGestionExpedientes bean) throws Exception {
        bean.setListExpedientes(new ArrayList<>());
        CivExpedientes civExpedientes = getExpedientesDAO().getCivExpedientes(bean.getReferenciaExpediente());
        if (civExpedientes != null) {
            Expedientes expedientes = new Expedientes(civExpedientes, civExpedientes.getCivTipoExpedientes(), civExpedientes.getCivEstadoExpedientes());
            bean.getListExpedientes().add(expedientes);
            /*String sDirectorio = expedientes.getUbicacion();
            String files;
            File folder = new File(sDirectorio);
            File[] listOfFiles = folder.listFiles();
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    files = listOfFiles[i].getName();
                    if(files.endsWith(".pdf")  || files.endsWith(".PDF") ){
                    
                    }
                }
            }*/

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
            archivoDetExpedientes.setArcdetexpFechaproceso(new Date());
            archivoDetExpedientes.setCivDetalleExpedientes(civDetalleExpedientes);
            archivoDetExpedientes.setCivEstadoArchDetExp(estadoArchDetExp);
         //   archivoDetExpedientes.setArcdetexpUbicacion(arcdetexpUbicacion);
            archivoDetExpedientes.setArcdetexpNombre(Paths.get(bean.getFile().getSubmittedFileName()).getFileName().toString());
        }
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

}
