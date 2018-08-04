/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanCargueArchivoDeudas;
import CobroCoactivo.Dao.DaoArchivosPlanos;
import CobroCoactivo.Dao.DaoConceptos;
import CobroCoactivo.Dao.DaoDetalleDeudas;
import CobroCoactivo.Dao.DaoDetalleExpedientes;
import CobroCoactivo.Dao.DaoDeudas;
import CobroCoactivo.Dao.DaoDeudasComparendo;
import CobroCoactivo.Dao.DaoDeudasImpuesto;
import CobroCoactivo.Dao.DaoEstadoDetalleDeudas;
import CobroCoactivo.Dao.DaoEstadoDeudas;
import CobroCoactivo.Dao.DaoEstadoPersonas;
import CobroCoactivo.Dao.DaoEstructuraPlanos;
import CobroCoactivo.Dao.DaoExpedientes;
import CobroCoactivo.Dao.DaoPersonas;
import CobroCoactivo.Dao.DaoPlanTrabajo;
import CobroCoactivo.Dao.DaoTipoDeudas;
import CobroCoactivo.Dao.DaoTipoDocumento;
import CobroCoactivo.Dao.DaoUsuarios;
import CobroCoactivo.Dao.ITArchivosPlanos;
import CobroCoactivo.Dao.ITConceptos;
import CobroCoactivo.Dao.ITDetalleDeudas;
import CobroCoactivo.Dao.ITDetalleExpedientes;
import CobroCoactivo.Dao.ITDeudaComparendo;
import CobroCoactivo.Dao.ITDeudas;
import CobroCoactivo.Dao.ITDeudasImpuesto;
import CobroCoactivo.Dao.ITEstadoDetalleDeudas;
import CobroCoactivo.Dao.ITEstadoDeudas;
import CobroCoactivo.Dao.ITEstadoPersonas;
import CobroCoactivo.Dao.ITEstructuraPlanos;
import CobroCoactivo.Dao.ITExpedientes;
import CobroCoactivo.Dao.ITPersonas;
import CobroCoactivo.Dao.ITPlanTrabajo;
import CobroCoactivo.Dao.ITTipoDeudas;
import CobroCoactivo.Dao.ITTipoDocumento;
import CobroCoactivo.Dao.ITUsuarios;
import CobroCoactivo.Modelo.CargueDeudasComparendo;
import CobroCoactivo.Modelo.CargueDeudasImpuesto;
import CobroCoactivo.Modelo.Deudas;
import CobroCoactivo.Modelo.Expedientes;
import CobroCoactivo.Modelo.Personas;
import CobroCoactivo.Modelo.TipoDocumentos;
import CobroCoactivo.Persistencia.CivArchivosPlanos;
import CobroCoactivo.Persistencia.CivDetalleDeudas;
import CobroCoactivo.Persistencia.CivDetalleExpedientes;
import CobroCoactivo.Persistencia.CivDeudas;
import CobroCoactivo.Persistencia.CivDeudasComparendo;
import CobroCoactivo.Persistencia.CivDeudasImpuesto;
import CobroCoactivo.Persistencia.CivEstadoDetalleExpedientes;
import CobroCoactivo.Persistencia.CivExpedientes;
import CobroCoactivo.Persistencia.CivPersonas;
import CobroCoactivo.Persistencia.CivPlanTrabajos;
import CobroCoactivo.Persistencia.CivTipoDetalleExpedientes;
import CobroCoactivo.Persistencia.CivTipoDocumentos;
import CobroCoactivo.Persistencia.CivUsuarios;
import CobroCoactivo.Utility.HibernateUtil;
import CobroCoactivo.Utility.Utility;
import CobroCoactivo.Utility.UtilityCargues;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Session;

/**
 *
 * @author AMORENO
 */
public class CargueArchivoDeudasImpBO implements CargueArchivoDeudasBO {

    private ITArchivosPlanos archivosPlanosDAO;
    private ITUsuarios usuarioDAO;
    private ITEstructuraPlanos estructuraPlanosDAO;
    private ITDeudasImpuesto deudasImpuestoDAO;
    private ITPersonas personaDAO;
    private ITTipoDocumento tipoDocumentosDAO;
    private ITEstadoPersonas estadoPersonaDAO;
    private ITDeudas deudasDAO;
    private ITEstadoDeudas estadoDeudasDAO;
    private ITTipoDeudas tipoDeudasDAO;
    private ITPlanTrabajo planTranajoDAO;
    private ITConceptos conceptosDAO;
    private ITEstadoDetalleDeudas estadoDetalleDeudasDAO;
    private ITDetalleDeudas detalleDeudasDAO;
    private ITDeudaComparendo deudaComparendoDAO;
    private ITExpedientes expedientesDAO;
    private ITDetalleExpedientes detalleExpedientesDAO;

    public CargueArchivoDeudasImpBO() {
        archivosPlanosDAO = new DaoArchivosPlanos();
        usuarioDAO = new DaoUsuarios();
        estructuraPlanosDAO = new DaoEstructuraPlanos();
        deudasImpuestoDAO = new DaoDeudasImpuesto();
        personaDAO = new DaoPersonas();
        tipoDocumentosDAO = new DaoTipoDocumento();
        estadoPersonaDAO = new DaoEstadoPersonas();
        deudasDAO = new DaoDeudas();
        estadoDeudasDAO = new DaoEstadoDeudas();
        tipoDeudasDAO = new DaoTipoDeudas();
        planTranajoDAO = new DaoPlanTrabajo();
        estadoDetalleDeudasDAO = new DaoEstadoDetalleDeudas();
        detalleDeudasDAO = new DaoDetalleDeudas();
        conceptosDAO = new DaoConceptos();
        deudaComparendoDAO = new DaoDeudasComparendo();
        expedientesDAO = new DaoExpedientes();
        detalleExpedientesDAO = new DaoDetalleExpedientes();
    }

    @Override
    public void procesarCargueArchivoDeudas(BeanCargueArchivoDeudas beanCargueArchivoDeudas) throws Exception {
        if (Paths.get(beanCargueArchivoDeudas.getArchivoCague().getSubmittedFileName()).getFileName().toString().endsWith(".txt")) {
            String nombreArchivo = beanCargueArchivoDeudas.getArchivoCague().getSubmittedFileName().replace(".txt", "");
            if (nombreArchivo.endsWith("PlanoDeudasImpuesto") || nombreArchivo.endsWith("PlanoDeudasComparendos")) {
                int tipoPlano = nombreArchivo.equals("PlanoDeudasImpuesto") ? 1 : (nombreArchivo.equals("PlanoDeudasComparendo") ? 2 : 0);
                CivPlanTrabajos civPlanTrabajos = null;
                if (tipoPlano != 0) {
                    civPlanTrabajos = getPlanTranajoDAO().getPlanTrabajo(tipoPlano == 1 ? "DERECHO DE TRANSITO" : (tipoPlano == 2 ? "COMPARENDO" : ""));//plan de trabajo impuesto
                }
                boolean datosGuardados = false;
                if (civPlanTrabajos != null) {
                    String linea;
                    Utility utility = new Utility();
                    org.apache.commons.fileupload.FileItem fileItem = null;
                    String folder = "D:/Archivo/archivos_planos/";
                    File folders = new File(folder);
                    if (!folders.exists()) {
                        folders.mkdirs();
                    }
                    nombreArchivo = folder + beanCargueArchivoDeudas.getArchivoCague().getSubmittedFileName();
                    File saveTo = new File(nombreArchivo);
                    //fileItem.write(saveTo);
                    java.io.FileInputStream fis = new java.io.FileInputStream(saveTo);
                    java.io.BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

//        
//        InputStream stream = beanCargueArchivoDeudas.getArchivoCague().getInputStream();
//        File file = new File(nombreArchivo);
//        file.c
//        Files.copy(stream, new File(folder + Paths.get(beanCargueArchivoDeudas.getArchivoCague().getSubmittedFileName()).getFileName().toString()).toPath(), StandardCopyOption.REPLACE_EXISTING);
//          
                    while ((linea = reader.readLine()) != null) {
                        java.util.List listaCampos = utility.obtenerCampos(linea);
                        UtilityCargues utilityCargues = new UtilityCargues();
                        List<UtilityCargues> listaCamposDeudas = utilityCargues.validarlineaImpuesto(listaCampos, getEstructuraPlanosDAO(), tipoPlano);
                        switch (tipoPlano) {
                            case 1:
                                if (utilityCargues.isError()) {
                                    CargueDeudasImpuesto cargueDeudasImpuesto = new CargueDeudasImpuesto(listaCamposDeudas);
                                    beanCargueArchivoDeudas.getListaDeudasImpuestoCorrectas().add(cargueDeudasImpuesto);
                                    beanCargueArchivoDeudas.setCargados(beanCargueArchivoDeudas.getCargados() + 1);
                                } else {
                                    beanCargueArchivoDeudas.getListaDeudasImpuestoIncorrectas().add(utilityCargues);
                                    beanCargueArchivoDeudas.setSinCargar(beanCargueArchivoDeudas.getSinCargar() + 1);
                                }
                                break;
                            case 2:
                                if (utilityCargues.isError()) {
                                    CargueDeudasComparendo cargueDeudasComparendo = new CargueDeudasComparendo(listaCamposDeudas);
                                    beanCargueArchivoDeudas.getListaDeudasComparendoCorrectas().add(cargueDeudasComparendo);
                                    beanCargueArchivoDeudas.setCargados(beanCargueArchivoDeudas.getCargados() + 1);
                                } else {
                                    beanCargueArchivoDeudas.getListaDeudasComparendoIncorrectas().add(utilityCargues);
                                    beanCargueArchivoDeudas.setSinCargar(beanCargueArchivoDeudas.getSinCargar() + 1);
                                }
                                break;
                        }

                    }
                    if (beanCargueArchivoDeudas.getCargados() > 0) {
                        CivArchivosPlanos civArchivosPlanos = new CivArchivosPlanos();
                        civArchivosPlanos.setArcNombre(beanCargueArchivoDeudas.getArchivoCague().getSubmittedFileName());
                        civArchivosPlanos.setArcFecha(new Date());
                        civArchivosPlanos.setArcEstadoFk(BigDecimal.ONE);
                        CivUsuarios civUsuarios = getUsuarioDAO().find(new BigDecimal(beanCargueArchivoDeudas.getLoginBO().getID_Usuario()));
                        civArchivosPlanos.setCivUsuarios(civUsuarios);
                        getArchivosPlanosDAO().create(civArchivosPlanos);
                        if (tipoPlano == 1) {
                            datosGuardados = guardarCarqueImpuesto(beanCargueArchivoDeudas.getListaDeudasImpuestoCorrectas(), civArchivosPlanos, civPlanTrabajos);
                        } else if (tipoPlano == 2) {
                            datosGuardados = guardarCarqueComparendo(beanCargueArchivoDeudas.getListaDeudasComparendoCorrectas(), civArchivosPlanos, civPlanTrabajos);
                        }

                        if (datosGuardados) {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                                    "Datos CargadosCorrectamente", null));
                        } else {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                                    "Ocurrio un problema al guardar los datos", null));
                        }

                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "El Archivo no pudo ser cargado", null));
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "No se encontro ningun plan de trabajo para el archivo :" + nombreArchivo, null));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Nombre del archivo no corresponde al requerido", null));
            }

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Solo puede cargar archivos .txt", null));
        }

    }

    public boolean cargarDatosDeudas(Personas personas, String valor, String referencia, String motivo, String fecha, CivPlanTrabajos civPlanTrabajos, BigDecimal concepto, int tipoDeuda) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        String nombreExpedientePersona = "";
        CivPersonas civPersonas = getPersonaDAO().consultarPersonasByDocumento(session,personas.getTipoDocumentos().getCodigo(), personas.getDocumento());
        if (civPersonas == null) {
            civPersonas = new CivPersonas();
            civPersonas.setPerNombre1(personas.getNombre1());
            civPersonas.setPerNombre2(personas.getNombre2());
            civPersonas.setPerApellido1(personas.getApellido1());
            civPersonas.setPerApellido2(personas.getApellido1());

            if (personas != null) {
                CivTipoDocumentos civTipoDocumentos = getTipoDocumentosDAO().getTipoDocumento(session,new BigDecimal(personas.getTipoDocumentos().getCodigo()));
                civPersonas.setCivTipoDocumentos(civTipoDocumentos);
                civPersonas.setPerDocumento(personas.getDocumento());
                civPersonas.setPerSexo(personas.getSexo());
                civPersonas.setPerFechaproceso(new Date());
                civPersonas.setCivEstadoPersonas(getEstadoPersonaDAO().getEstadoPersona(BigDecimal.ONE));
                getPersonaDAO().create(civPersonas);
            }
        }
        Expedientes expedientes = new Expedientes();
        nombreExpedientePersona = expedientes.crearExpediente(civPersonas, getExpedientesDAO());
        CivDeudas civDeudas = new CivDeudas();
        civDeudas.setDeuRefencia(referencia);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatterVigencia = new SimpleDateFormat("yyyy");
        civDeudas.setDeuFechadeuda(formatter.parse(fecha));
        civDeudas.setDeuFechaproceso(new Date());
        civDeudas.setDeuMotivo(motivo);
        civDeudas.setDeuValor(new BigDecimal(valor));
        civDeudas.setDeuSaldo(new BigDecimal(valor));
        civDeudas.setCivEstadoDeudas(getEstadoDeudasDAO().getEstadoDeudas(session,BigDecimal.ONE));
        civDeudas.setCivPersonas(civPersonas);
        civDeudas.setCivPlanTrabajos(civPlanTrabajos);
        civDeudas.setCivTipoDeudas(getTipoDeudasDAO().find(new BigDecimal(tipoDeuda)));
        getDeudasDAO().create(civDeudas);
        String folderExpedienteDeuda = "";
        if (tipoDeuda == 1) {
            folderExpedienteDeuda = nombreExpedientePersona + "/" + referencia + "-" + formatterVigencia.format(civDeudas.getDeuFechadeuda());
        } else if (tipoDeuda == 2) {
            folderExpedienteDeuda = nombreExpedientePersona + "/" + referencia;
        }
        File foldersDeuda = new File(folderExpedienteDeuda);
        if (!foldersDeuda.exists()) {
            foldersDeuda.mkdirs();
            CivDetalleExpedientes civDetalleExpedientes = new CivDetalleExpedientes();
            civDetalleExpedientes.setDetexpFechaproceso(new Date());
            civDetalleExpedientes.setCivDeudas(civDeudas);
            civDetalleExpedientes.setDetexpDescripcion(civDeudas.getDeuRefencia());
            CivEstadoDetalleExpedientes civEstadoDetalleExpedientes = new CivEstadoDetalleExpedientes();
            civEstadoDetalleExpedientes.setEstdetexpId(BigDecimal.ONE);
            civDetalleExpedientes.setCivEstadoDetalleExpedientes(civEstadoDetalleExpedientes);
            CivTipoDetalleExpedientes civTipoDetalleExpedientes = new CivTipoDetalleExpedientes();
            civTipoDetalleExpedientes.setTipdetexpId(BigDecimal.ONE);
            civDetalleExpedientes.setCivTipoDetalleExpedientes(civTipoDetalleExpedientes);
            CivExpedientes civExpedientes = getExpedientesDAO().getCivExpedientesByUri(nombreExpedientePersona);
            civDetalleExpedientes.setCivExpedientes(civExpedientes);
            getDetalleExpedientesDAO().create(civDetalleExpedientes);
        }
        CivDetalleDeudas civDetalleDeudas = new CivDetalleDeudas();
        civDetalleDeudas.setCivDeudas(civDeudas);
        civDetalleDeudas.setCivConceptos(getConceptosDAO().find(concepto));
        civDetalleDeudas.setCivEstadoDetalleDeudas(getEstadoDetalleDeudasDAO().find(BigDecimal.ONE));
        civDetalleDeudas.setDeuFechaproceso(new Date());
        civDetalleDeudas.setDeuValor(civDeudas.getDeuValor());
        getDetalleDeudasDAO().create(civDetalleDeudas);
        return true;
        } finally {
            session.flush();
            session.close();
        }
    }

    private boolean guardarCarqueImpuesto(List<CargueDeudasImpuesto> listacargueDeudasImpuesto, CivArchivosPlanos civArchivosPlanos, CivPlanTrabajos civPlanTrabajos) throws Exception {
        boolean sw = false;
        for (CargueDeudasImpuesto cargueDeudasImpuesto : listacargueDeudasImpuesto) {
            CivDeudasImpuesto civDeudasImpuesto = new CivDeudasImpuesto();
            civDeudasImpuesto.setDeuimpConsecutivo(cargueDeudasImpuesto.getConsecutivo());
            civDeudasImpuesto.setDeuimpFecha(cargueDeudasImpuesto.getFecha());
            civDeudasImpuesto.setDeuimpTipo(cargueDeudasImpuesto.getTipo());
            civDeudasImpuesto.setDeuimpValor(cargueDeudasImpuesto.getValor());
            civDeudasImpuesto.setDeuimpMotivo(cargueDeudasImpuesto.getMotivo());
            civDeudasImpuesto.setDeuimpReferencia(cargueDeudasImpuesto.getFererencia());
            civDeudasImpuesto.setDeuimpNombre1(cargueDeudasImpuesto.getNombre1());
            civDeudasImpuesto.setDeuimpNombre2(cargueDeudasImpuesto.getNombre2());
            civDeudasImpuesto.setDeuimpApellido1(cargueDeudasImpuesto.getApellido1());
            civDeudasImpuesto.setDeuimpApellido2(cargueDeudasImpuesto.getApellido2());
            civDeudasImpuesto.setDeuimpTipodocumento(cargueDeudasImpuesto.getTipoDocumento());
            civDeudasImpuesto.setDeuimpDocumento(cargueDeudasImpuesto.getDocumento());
            civDeudasImpuesto.setDeuimpSexo(cargueDeudasImpuesto.getSexo());
            civDeudasImpuesto.setDeuimpDireccion1(cargueDeudasImpuesto.getDireccion1());
            civDeudasImpuesto.setDeuimpDireccion2(cargueDeudasImpuesto.getDireccion2());
            civDeudasImpuesto.setDeuimpCelular(cargueDeudasImpuesto.getCelular());
            civDeudasImpuesto.setDeuimpTelefono(cargueDeudasImpuesto.getTelefono());
            civDeudasImpuesto.setDeuimpCorreoelectronico(cargueDeudasImpuesto.getCorreo());
            civDeudasImpuesto.setDeuimpPlaca(cargueDeudasImpuesto.getPlaca());
            civDeudasImpuesto.setDeuimpServiciovehiculo(cargueDeudasImpuesto.getServicioVehiculo());
            civDeudasImpuesto.setDeuimpClasevehiculo(cargueDeudasImpuesto.getClaseVehiculo());
            civDeudasImpuesto.setDeuimpAvaluo(cargueDeudasImpuesto.getAvaluo());
            civDeudasImpuesto.setDeuimpLiquidacionoficial(cargueDeudasImpuesto.getLiquidacionOficial());
            civDeudasImpuesto.setDeuimpFechaliquidacion(cargueDeudasImpuesto.getFechaLiquidacion());
            civDeudasImpuesto.setDeuimpFechamatricula(cargueDeudasImpuesto.getFechamatricula());
            civDeudasImpuesto.setDeuimpArchivoFk(civArchivosPlanos.getArcId());
            getDeudasImpuestoDAO().create(civDeudasImpuesto);
            Personas persona = new Personas();
            persona.setNombre1(cargueDeudasImpuesto.getNombre1());
            persona.setNombre2(cargueDeudasImpuesto.getNombre2());
            persona.setApellido1(cargueDeudasImpuesto.getApellido1());
            persona.setApellido2(cargueDeudasImpuesto.getApellido2());
            persona.setDocumento(cargueDeudasImpuesto.getDocumento());
            TipoDocumentos tipoDocumento = new TipoDocumentos();
            tipoDocumento.setCodigo(Integer.parseInt(cargueDeudasImpuesto.getTipoDocumento()));
            persona.setTipoDocumentos(tipoDocumento);
            persona.setSexo(cargueDeudasImpuesto.getSexo());
            sw = cargarDatosDeudas(persona, cargueDeudasImpuesto.getValor(), cargueDeudasImpuesto.getPlaca(), cargueDeudasImpuesto.getMotivo(), cargueDeudasImpuesto.getFecha(), civPlanTrabajos, new BigDecimal(1), 1);
        }
        return sw;
    }

    private boolean guardarCarqueComparendo(List<CargueDeudasComparendo> listacargueDeudasComparendos, CivArchivosPlanos civArchivosPlanos, CivPlanTrabajos civPlanTrabajos) throws Exception {
        boolean sw = false;
        for (CargueDeudasComparendo cargueDeudasComparendo : listacargueDeudasComparendos) {
            CivDeudasComparendo civDeudasComparendo = new CivDeudasComparendo();
            civDeudasComparendo.setDeucomConsecutivo(cargueDeudasComparendo.getConsecutivo());
            civDeudasComparendo.setDeucomFecha(cargueDeudasComparendo.getFecha());
            civDeudasComparendo.setDeucomTipo(cargueDeudasComparendo.getTipo());
            civDeudasComparendo.setDeucomValor(cargueDeudasComparendo.getValor());
            civDeudasComparendo.setDeucomMotivo(cargueDeudasComparendo.getMotivo());
            civDeudasComparendo.setDeucomReferencia(cargueDeudasComparendo.getReferencia());
            civDeudasComparendo.setDeucomNombre1(cargueDeudasComparendo.getNombre1());
            civDeudasComparendo.setDeucomNombre2(cargueDeudasComparendo.getNombre2());
            civDeudasComparendo.setDeucomApellido1(cargueDeudasComparendo.getApellido1());
            civDeudasComparendo.setDeucomApellido2(cargueDeudasComparendo.getApellido2());
            civDeudasComparendo.setDeucomTipodocumento(cargueDeudasComparendo.getTipoDocumento());
            civDeudasComparendo.setDeucomDocumento(cargueDeudasComparendo.getDocumento());
            civDeudasComparendo.setDeucomSexo(cargueDeudasComparendo.getSexo());
            civDeudasComparendo.setDeucomDireccion1(cargueDeudasComparendo.getDireccion1());
            civDeudasComparendo.setDeucomDireccion2(cargueDeudasComparendo.getDireccion2());
            civDeudasComparendo.setDeucomCelular(cargueDeudasComparendo.getCelular());
            civDeudasComparendo.setDeucomTelefono(cargueDeudasComparendo.getTelefono());
            civDeudasComparendo.setDeucomCorreoelectronico(cargueDeudasComparendo.getCorreo());
            civDeudasComparendo.setDeucomNumerocomparendo(cargueDeudasComparendo.getNumeroComparendo());
            civDeudasComparendo.setDeucomInfraccion(cargueDeudasComparendo.getInfraccion());
            civDeudasComparendo.setDeucomDescripcioninfraccion(cargueDeudasComparendo.getDescripcionInfraccion());
            civDeudasComparendo.setDeucomFechaComparendo(cargueDeudasComparendo.getFechaComparendo());
            civDeudasComparendo.setDeucomNumerosancion(cargueDeudasComparendo.getNumeroSancion());
            civDeudasComparendo.setDeucomFechasancion(cargueDeudasComparendo.getFechaSancion());
            civDeudasComparendo.setDeucomValorsancion(cargueDeudasComparendo.getValorSancion());
            civDeudasComparendo.setDeucomArchivoFk(civArchivosPlanos.getArcId());
            getDeudaComparendoDAO().create(civDeudasComparendo);
            Personas persona = new Personas();
            persona.setNombre1(cargueDeudasComparendo.getNombre1());
            persona.setNombre2(cargueDeudasComparendo.getNombre2());
            persona.setApellido1(cargueDeudasComparendo.getApellido1());
            persona.setApellido2(cargueDeudasComparendo.getApellido2());
            persona.setDocumento(cargueDeudasComparendo.getDocumento());
            TipoDocumentos tipoDocumento = new TipoDocumentos();
            tipoDocumento.setCodigo(Integer.parseInt(cargueDeudasComparendo.getTipoDocumento()));
            persona.setTipoDocumentos(tipoDocumento);
            persona.setSexo(cargueDeudasComparendo.getSexo());
            sw = cargarDatosDeudas(persona, cargueDeudasComparendo.getValor(), cargueDeudasComparendo.getNumeroComparendo(), cargueDeudasComparendo.getMotivo(), cargueDeudasComparendo.getFecha(), civPlanTrabajos, new BigDecimal(2), 2);
        }
        return sw;
    }

    /**
     * @return the archivosPlanosDAO
     */
    public ITArchivosPlanos getArchivosPlanosDAO() {
        return archivosPlanosDAO;
    }

    /**
     * @param archivosPlanosDAO the archivosPlanosDAO to set
     */
    public void setArchivosPlanosDAO(ITArchivosPlanos archivosPlanosDAO) {
        this.archivosPlanosDAO = archivosPlanosDAO;
    }

    /**
     * @return the usuarioDAO
     */
    public ITUsuarios getUsuarioDAO() {
        return usuarioDAO;
    }

    /**
     * @param usuarioDAO the usuarioDAO to set
     */
    public void setUsuarioDAO(ITUsuarios usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    /**
     * @return the estructuraPlanosDAO
     */
    public ITEstructuraPlanos getEstructuraPlanosDAO() {
        return estructuraPlanosDAO;
    }

    /**
     * @param estructuraPlanosDAO the estructuraPlanosDAO to set
     */
    public void setEstructuraPlanosDAO(ITEstructuraPlanos estructuraPlanosDAO) {
        this.estructuraPlanosDAO = estructuraPlanosDAO;
    }

    /**
     * @return the deudasImpuestoDAO
     */
    public ITDeudasImpuesto getDeudasImpuestoDAO() {
        return deudasImpuestoDAO;
    }

    /**
     * @param deudasImpuestoDAO the deudasImpuestoDAO to set
     */
    public void setDeudasImpuestoDAO(ITDeudasImpuesto deudasImpuestoDAO) {
        this.deudasImpuestoDAO = deudasImpuestoDAO;
    }

    /**
     * @return the personaDAO
     */
    public ITPersonas getPersonaDAO() {
        return personaDAO;
    }

    /**
     * @param personaDAO the personaDAO to set
     */
    public void setPersonaDAO(ITPersonas personaDAO) {
        this.personaDAO = personaDAO;
    }

    /**
     * @return the tipoDocumentosDAO
     */
    public ITTipoDocumento getTipoDocumentosDAO() {
        return tipoDocumentosDAO;
    }

    /**
     * @param tipoDocumentosDAO the tipoDocumentosDAO to set
     */
    public void setTipoDocumentosDAO(ITTipoDocumento tipoDocumentosDAO) {
        this.tipoDocumentosDAO = tipoDocumentosDAO;
    }

    /**
     * @return the estadoPersonaDAO
     */
    public ITEstadoPersonas getEstadoPersonaDAO() {
        return estadoPersonaDAO;
    }

    /**
     * @param estadoPersonaDAO the estadoPersonaDAO to set
     */
    public void setEstadoPersonaDAO(ITEstadoPersonas estadoPersonaDAO) {
        this.estadoPersonaDAO = estadoPersonaDAO;
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
     * @return the estadoDeudasDAO
     */
    public ITEstadoDeudas getEstadoDeudasDAO() {
        return estadoDeudasDAO;
    }

    /**
     * @param estadoDeudasDAO the estadoDeudasDAO to set
     */
    public void setEstadoDeudasDAO(ITEstadoDeudas estadoDeudasDAO) {
        this.estadoDeudasDAO = estadoDeudasDAO;
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
     * @return the planTranajoDAO
     */
    public ITPlanTrabajo getPlanTranajoDAO() {
        return planTranajoDAO;
    }

    /**
     * @param planTranajoDAO the planTranajoDAO to set
     */
    public void setPlanTranajoDAO(ITPlanTrabajo planTranajoDAO) {
        this.planTranajoDAO = planTranajoDAO;
    }

    /**
     * @return the conceptosDAO
     */
    public ITConceptos getConceptosDAO() {
        return conceptosDAO;
    }

    /**
     * @param conceptosDAO the conceptosDAO to set
     */
    public void setConceptosDAO(ITConceptos conceptosDAO) {
        this.conceptosDAO = conceptosDAO;
    }

    /**
     * @return the estadoDetalleDeudasDAO
     */
    public ITEstadoDetalleDeudas getEstadoDetalleDeudasDAO() {
        return estadoDetalleDeudasDAO;
    }

    /**
     * @param estadoDetalleDeudasDAO the estadoDetalleDeudasDAO to set
     */
    public void setEstadoDetalleDeudasDAO(ITEstadoDetalleDeudas estadoDetalleDeudasDAO) {
        this.estadoDetalleDeudasDAO = estadoDetalleDeudasDAO;
    }

    /**
     * @return the detalleDeudasDAO
     */
    public ITDetalleDeudas getDetalleDeudasDAO() {
        return detalleDeudasDAO;
    }

    /**
     * @param detalleDeudasDAO the detalleDeudasDAO to set
     */
    public void setDetalleDeudasDAO(ITDetalleDeudas detalleDeudasDAO) {
        this.detalleDeudasDAO = detalleDeudasDAO;
    }

    /**
     * @return the deudaComparendoDAO
     */
    public ITDeudaComparendo getDeudaComparendoDAO() {
        return deudaComparendoDAO;
    }

    /**
     * @param deudaComparendoDAO the deudaComparendoDAO to set
     */
    public void setDeudaComparendoDAO(ITDeudaComparendo deudaComparendoDAO) {
        this.deudaComparendoDAO = deudaComparendoDAO;
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

}
