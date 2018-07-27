package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanPlanGeneral;
import CobroCoactivo.Dao.DaoDocumentosGenerales;
import CobroCoactivo.Dao.DaoEstadoEtapaGeneral;
import CobroCoactivo.Dao.DaoEstadoFasesGenerales;
import CobroCoactivo.Dao.DaoEstadoPlanGeneral;
import CobroCoactivo.Dao.DaoEstapaGeneral;
import CobroCoactivo.Dao.DaoEtapasTrabajo;
import CobroCoactivo.Dao.DaoFasesGenerales;
import CobroCoactivo.Dao.DaoFasesTrabajo;
import CobroCoactivo.Dao.DaoMovimientos;
import CobroCoactivo.Dao.DaoPlanGeneral;
import CobroCoactivo.Dao.DaoPlanTrabajo;
import CobroCoactivo.Dao.DaoReporteTrabajos;
import CobroCoactivo.Dao.ITDocumentoGenerales;
import CobroCoactivo.Dao.ITEstadoEtapageneral;
import CobroCoactivo.Dao.ITEstadoFasesGenerales;
import CobroCoactivo.Dao.ITPlanGeneral;
import CobroCoactivo.Modelo.PlanGenerales;
import CobroCoactivo.Persistencia.CivEstadoPlanGenerales;
import CobroCoactivo.Persistencia.CivPlanGenerales;
import java.math.BigDecimal;
import java.util.List;
import CobroCoactivo.Dao.ITEstadoPlanGeneral;
import CobroCoactivo.Dao.ITEstapaGeneral;
import CobroCoactivo.Dao.ITEtapasTrabajo;
import CobroCoactivo.Dao.ITFasesGenerales;
import CobroCoactivo.Dao.ITFasesTrabajo;
import CobroCoactivo.Dao.ITMovimientos;
import CobroCoactivo.Dao.ITPlanTrabajo;
import CobroCoactivo.Dao.ITReporteTrabajos;
import CobroCoactivo.Modelo.EstadoEtapasGenerales;
import CobroCoactivo.Modelo.EstadoFasesGenerales;
import CobroCoactivo.Modelo.EstadoPlanGenerales;
import CobroCoactivo.Modelo.EtapasGenerales;
import CobroCoactivo.Modelo.FasesGenerales;
import CobroCoactivo.Persistencia.CivDocumenGenerales;
import CobroCoactivo.Persistencia.CivEstadoDocumengenerales;
import CobroCoactivo.Persistencia.CivEstadoEtapaTrabajos;
import CobroCoactivo.Persistencia.CivEstadoEtapasGenerales;
import CobroCoactivo.Persistencia.CivEstadoFasesGenerales;
import CobroCoactivo.Persistencia.CivEstadoFasesTrabajos;
import CobroCoactivo.Persistencia.CivEstadoReporteTrabajos;
import CobroCoactivo.Persistencia.CivEtapasGenerales;
import CobroCoactivo.Persistencia.CivEtapasTrabajos;
import CobroCoactivo.Persistencia.CivFasesGenerales;
import CobroCoactivo.Persistencia.CivFasesTrabajos;
import CobroCoactivo.Persistencia.CivMovimientos;
import CobroCoactivo.Persistencia.CivPlanTrabajos;
import CobroCoactivo.Persistencia.CivReporteTrabajos;
import CobroCoactivo.Utility.HibernateUtil;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.primefaces.context.RequestContext;

public class PlanGeneralImpBO implements PlanGeneralBO {

    private ITDocumentoGenerales documentosGeneralesDAO;
    private ITEstapaGeneral etapaGeneralDAO;
    private ITEtapasTrabajo etapaTrabajoDao;
    private ITEstadoEtapageneral estadoEtapaGeneralDAO;
    private ITEstadoFasesGenerales estadoFaseGeneralesDAO;
    private ITEstadoPlanGeneral estadoPlanGeneralDAO;
    private ITFasesGenerales faseGeneralDAO;
    private ITFasesTrabajo fasesTrabajoDao;
    private ITMovimientos movimientosDao;
    private ITPlanGeneral planGeneralDAO;
    private ITPlanTrabajo planTrabajoDao;
    private ITReporteTrabajos reporteTrabajoDao;

    public PlanGeneralImpBO() {
        documentosGeneralesDAO = new DaoDocumentosGenerales();
        etapaGeneralDAO = new DaoEstapaGeneral();
        etapaTrabajoDao = new DaoEtapasTrabajo();
        estadoEtapaGeneralDAO = new DaoEstadoEtapaGeneral();
        estadoFaseGeneralesDAO = new DaoEstadoFasesGenerales();
        estadoPlanGeneralDAO = new DaoEstadoPlanGeneral();
        faseGeneralDAO = new DaoFasesGenerales();
        fasesTrabajoDao = new DaoFasesTrabajo();
        movimientosDao = new DaoMovimientos();
        planGeneralDAO = new DaoPlanGeneral();
        planTrabajoDao = new DaoPlanTrabajo();
        reporteTrabajoDao = new DaoReporteTrabajos();

    }

    @Override
    public void actualizarFase(BeanPlanGeneral bean) throws Exception {
        if (bean.getFasesGenerales().getDianim() < bean.getFasesGenerales().getDiamax()) {

            CivDocumenGenerales civDocumenGenerales = new CivDocumenGenerales();
            CivFasesGenerales civFasesGenerales = new CivFasesGenerales();
            CivEstadoFasesGenerales civEstadoFasesGenerales = new CivEstadoFasesGenerales();
            CivEtapasGenerales civEtapasGenerales = new CivEtapasGenerales();

            civEtapasGenerales.setEtagenId(BigDecimal.valueOf(bean.getEtapasGenerales().getId()));
            civEstadoFasesGenerales.setEstfasgenId(new BigDecimal(bean.getIdEStadoFasesGeneral()));
            civFasesGenerales.setFasgenId(new BigDecimal(bean.getFasesGenerales().getId()));
            civFasesGenerales.setFasgenDescripcion(bean.getFasesGenerales().getDescripcion());
            civFasesGenerales.setCivEstadoFasesGenerales(civEstadoFasesGenerales);
            civFasesGenerales.setFasgenFechaproceso(bean.getFasesGenerales().getFechaproceso());
            civFasesGenerales.setCivEtapasGenerales(civEtapasGenerales);
            civFasesGenerales.setFasgenDianim(BigDecimal.valueOf(bean.getFasesGenerales().getDianim()));
            civFasesGenerales.setFasgenDiamax(BigDecimal.valueOf(bean.getFasesGenerales().getDiamax()));
            civFasesGenerales.setFasgenObligatorio(bean.getFasesGenerales().getObligatorio());
            List<CivMovimientos> listCivMovimientos = getMovimientosDao().listMovimiento(civFasesGenerales.getFasgenId().intValue());
            if (listCivMovimientos != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "No se puede desactivar la fase tiene movimientos", null));
                return;

            }
            if (bean.getFile() == null) {
                civDocumenGenerales.setDocgenId(new BigDecimal(bean.getFasesGenerales().getDocumenGenerales().getId()));
            } else {
                if (!Paths.get(bean.getFile().getSubmittedFileName()).getFileName().toString().endsWith(".pdf")) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Solo se puede cargar archivo pdf", null));
                    return;
                } else {

                    CivEstadoDocumengenerales civEstadoDocumengenerales = new CivEstadoDocumengenerales();
                    civDocumenGenerales.setDocgenId(new BigDecimal(bean.getFasesGenerales().getDocumenGenerales().getId()));
                    civEstadoDocumengenerales.setEstdocgenId(BigDecimal.valueOf(1));
                    civDocumenGenerales.setDocgenDescripcion(Paths.get(bean.getFile().getSubmittedFileName()).getFileName().toString());
                    civDocumenGenerales.setCivEstadoDocumengenerales(civEstadoDocumengenerales);
                    civDocumenGenerales.setDocgenFechaproceso(new Date());
                    civDocumenGenerales.setDocgenArchivo("D:\\Archivo\\" + Paths.get(bean.getFile().getSubmittedFileName()).getFileName().toString());
                    getiTDocumentoGenerales().update(civDocumenGenerales);
                }
            }

            civFasesGenerales.setCivDocumenGenerales(civDocumenGenerales);
            getFaseGeneralDAO().update(civFasesGenerales);
            CivFasesTrabajos civFasesTrabajos = getFasesTrabajoDao().getFasesTrabajos(civFasesGenerales.getFasgenId().intValue());
            if (civFasesTrabajos != null) {
                civFasesTrabajos.setFastraDescripcion(civFasesGenerales.getFasgenDescripcion());
                civFasesTrabajos.setFastraDianim(civFasesGenerales.getFasgenDianim());
                civFasesTrabajos.setFastraDiamax(civFasesGenerales.getFasgenDiamax());
                civFasesTrabajos.setFastraFechaproceso(civFasesGenerales.getFasgenFechaproceso());
                civFasesTrabajos.setFastraObligatorio(civFasesGenerales.getFasgenObligatorio());
                CivEstadoFasesTrabajos civEstadoFasesTrabajos = new CivEstadoFasesTrabajos();
                civEstadoFasesTrabajos.setEstfastraId(new BigDecimal(bean.getIdEStadoFasesGeneral()));
                civFasesTrabajos.setCivEstadoFasesTrabajos(civEstadoFasesTrabajos);
                CivEtapasTrabajos civEtapasTrabajos = new CivEtapasTrabajos();
                civEtapasTrabajos.setEtatraId(BigDecimal.valueOf(bean.getEtapasGenerales().getId()));
                civFasesTrabajos.setCivEtapasTrabajos(civEtapasTrabajos);
                CivReporteTrabajos civReporteTrabajos = new CivReporteTrabajos();
                if (bean.getFile() == null) {
                    civReporteTrabajos.setReptraId(new BigDecimal(bean.getFasesGenerales().getDocumenGenerales().getId()));
                } else {
                    CivEstadoReporteTrabajos civEstadoReporteTrabajos = new CivEstadoReporteTrabajos();
                    civEstadoReporteTrabajos.setEstreptraId(BigDecimal.ONE);
                    civReporteTrabajos.setReptraId(new BigDecimal(bean.getFasesGenerales().getDocumenGenerales().getId()));
                    civReporteTrabajos.setReptraDescripcion(civDocumenGenerales.getDocgenDescripcion());
                    civReporteTrabajos.setReptraArchivo(civDocumenGenerales.getDocgenArchivo());
                    civReporteTrabajos.setReptraFechaproceso(civDocumenGenerales.getDocgenFechaproceso());
                    civReporteTrabajos.setCivEstadoReporteTrabajos(civEstadoReporteTrabajos);
                    getReporteTrabajoDao().update(civReporteTrabajos);
                }
                civFasesTrabajos.setCivReporteTrabajos(civReporteTrabajos);
                getFasesTrabajoDao().update(civFasesTrabajos);
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Fase general actualizada correctamente", "Plan General Creado exitosamente"));
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("$('#faseGeneral').modal('hide')");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Dia minimo debe ser menor que dia maximo", null));
        }

    }

    @Override
    public void actualizarEtapaGeneral(BeanPlanGeneral bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        boolean validador = false;
        CivEtapasGenerales civEtapasGenerales = new CivEtapasGenerales();
        CivEstadoEtapasGenerales civEstadoEtapasGenerales = new CivEstadoEtapasGenerales();
        civEstadoEtapasGenerales.setEstetagenId(new BigDecimal(bean.getEtapasGenerales().getEstadoEtapasGenerales().getId()));
        CivPlanGenerales civPlanGenerales = new CivPlanGenerales();
        civPlanGenerales.setPlagenId(new BigDecimal(bean.getPlanGenerales().getId()));

        civEtapasGenerales.setEtagenId(new BigDecimal(bean.getEtapasGenerales().getId()));
        civEtapasGenerales.setCivEstadoEtapasGenerales(civEstadoEtapasGenerales);
        civEtapasGenerales.setCivPlanGenerales(civPlanGenerales);
        civEtapasGenerales.setEtagenDescripcion(bean.getEtapasGenerales().getDescripcion());
        civEtapasGenerales.setEtagenObligatorio(bean.getEtapasGenerales().getObligatorio());
        civEtapasGenerales.setEtagenPrioridad(new BigDecimal(bean.getEtapasGenerales().getPrioridad()));
        civEtapasGenerales.setEtagenFechaproceso(new Date());

        if (bean.getEtapasGenerales().getEstadoEtapasGenerales().getId() != 1) {
            List<CivFasesGenerales> listCivFasesGenerales = getiTFasesGenerales().AllListByEtapaGeneral(session, bean.getEtapasGenerales().getId());
            if (listCivFasesGenerales != null && listCivFasesGenerales.size() > 0) {
                for (CivFasesGenerales civFasesGenerales : listCivFasesGenerales) {
                    if (civFasesGenerales.getCivEstadoFasesGenerales().getEstfasgenId().intValue() == 1) {
                        validador = true;
                    }
                }

                if (validador) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede desactivar la etapa por que tiene fases activas", null));
                } else {
                    getItEstapaGeneral().update(civEtapasGenerales);
                    bean.init();
                    bean.ListarEtapaGeneralesPorIdPlanGeneral(bean.getPlanGenerales());
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Etapa desactivada correctamente", null));
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.execute("$('#etapaGeneral').modal('hide')");
                }

            } else {
                CivEtapasTrabajos civEtapasTrabajos = getEtapaTrabajoDao().find(civEtapasGenerales.getEtagenId().intValue());
                if (civEtapasTrabajos != null) {
                    CivEstadoEtapaTrabajos civEstadoEtapaTrabajos = new CivEstadoEtapaTrabajos();
                    civEstadoEtapaTrabajos.setEstetatraId(new BigDecimal(bean.getEtapasGenerales().getEstadoEtapasGenerales().getId()));
                    civEtapasTrabajos.setCivEstadoEtapaTrabajos(civEstadoEtapaTrabajos);
                    getEtapaTrabajoDao().update(civEtapasTrabajos);
                }
                getItEstapaGeneral().update(civEtapasGenerales);
                bean.init();
                bean.ListarEtapaGeneralesPorIdPlanGeneral(bean.getPlanGenerales());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Etapa desahabilitada correctamente", null));
                RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("$('#etapaGeneral').modal('hide')");
            }

        } else {
            CivEtapasTrabajos civEtapasTrabajos = getEtapaTrabajoDao().find(civEtapasGenerales.getEtagenId().intValue());
            if (civEtapasTrabajos != null) {
                civEtapasTrabajos.setEtatraObligatorio(civEtapasGenerales.getEtagenObligatorio());
                civEtapasTrabajos.setEtatraDescricion(civEtapasGenerales.getEtagenDescripcion());
                civEtapasTrabajos.setEtatraPrioridad(civEtapasGenerales.getEtagenPrioridad());
                getEtapaTrabajoDao().update(civEtapasTrabajos);

            }
            getItEstapaGeneral().update(civEtapasGenerales);
            bean.init();
            bean.ListarEtapaGeneralesPorIdPlanGeneral(bean.getPlanGenerales());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "etapa Actualizado Correctamente", null));
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("$('#etapaGeneral').modal('hide')");
        }

        session.close();
    }

    @Override
    public void actualizarPlanGeneral(BeanPlanGeneral bean) throws Exception {
        boolean flag = false;
        CivPlanGenerales civPlanGenerales = new CivPlanGenerales();
        CivEstadoPlanGenerales civEstadoPlanGenerales = new CivEstadoPlanGenerales();
        civEstadoPlanGenerales.setEstplagenId(new BigDecimal(bean.getPlanGenerales().getEstadoPlanGenerales().getId()));
        civPlanGenerales.setPlagenId(new BigDecimal(bean.getPlanGenerales().getId()));
        civPlanGenerales.setPlagenDescripcion(bean.getPlanGenerales().getDescripcion().toUpperCase());
        civPlanGenerales.setPlagenColor(bean.getPlanGenerales().getColor());
        civPlanGenerales.setPlagenFechaproceso(new Date());
        civPlanGenerales.setCivEstadoPlanGenerales(civEstadoPlanGenerales);

        if (bean.getPlanGenerales().getEstadoPlanGenerales().getId() != 1) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            List<CivEtapasGenerales> listadoPlangeneral = getItEstapaGeneral().findAllEtapaByIdPlanGeneral(session, civPlanGenerales.getPlagenId().intValue());
            if (listadoPlangeneral != null && listadoPlangeneral.size() > 0) {
                for (CivEtapasGenerales civEtapasGenerales : listadoPlangeneral) {
                    if (civEtapasGenerales.getCivEstadoEtapasGenerales().getEstetagenId().intValue() == 1) {
                        flag = true;
                    }
                }
                if (flag) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Debe desactivar las etapas generales", null));
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.execute("$('#planGeneral').modal('hide')");

                } else {
                    getiTPlanGeneral().update(civPlanGenerales);
                    bean.init();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Plan actualizado correctamente", null));
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.execute("$('#planGeneral').modal('hide')");

                }
            } else {
                getiTPlanGeneral().update(civPlanGenerales);
                bean.init();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Plan actualizado correctamente", null));
                RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("$('#planGeneral').modal('hide')");
            }
            session.close();
        } else {
            CivPlanTrabajos civPlanTrabajos = getPlanTrabajoDao().getPlanTrabajo(civPlanGenerales.getPlagenId().intValue());
            CivPlanGenerales civPlanGeneralesColor = getiTPlanGeneral().getCivPlanGeneralByColor(civPlanGenerales.getPlagenColor());
            if (civPlanGeneralesColor == null || civPlanGeneralesColor.getPlagenId().intValue() == civPlanGenerales.getPlagenId().intValue()) {
                if (civPlanTrabajos != null) {
                    civPlanTrabajos.setPlatraDescripcion(civPlanGenerales.getPlagenDescripcion());
                    civPlanTrabajos.setPlatraColor(civPlanGenerales.getPlagenColor());
                    getPlanTrabajoDao().update(civPlanTrabajos);
                }
                getiTPlanGeneral().update(civPlanGenerales);
                bean.init();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Plan actualizado correctamente", null));
                RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("$('#planGeneral').modal('hide')");
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "El color ya existe en el sistema", null));
            }

        }

    }

    @Override
    public void guardarEtapaGeneral(BeanPlanGeneral bean) throws Exception {
        CivEtapasGenerales civEtapasGenerales = new CivEtapasGenerales();
        CivEstadoEtapasGenerales civEstadoEtapasGenerales = new CivEstadoEtapasGenerales();
        CivPlanGenerales civPlanGenerales = new CivPlanGenerales();
        civPlanGenerales.setPlagenId(new BigDecimal(bean.getPlanGenerales().getId()));
        civEstadoEtapasGenerales.setEstetagenId(new BigDecimal(1));
        civEtapasGenerales.setCivEstadoEtapasGenerales(civEstadoEtapasGenerales);
        civEtapasGenerales.setCivPlanGenerales(civPlanGenerales);
        civEtapasGenerales.setEtagenDescripcion(bean.getEtapasGenerales().getDescripcion());
        civEtapasGenerales.setEtagenFechaproceso(new Date());
        civEtapasGenerales.setEtagenObligatorio(bean.getEtapasGenerales().getObligatorio());
        civEtapasGenerales.setEtagenPrioridad(new BigDecimal(bean.getEtapasGenerales().getPrioridad()));
        getItEstapaGeneral().create(civEtapasGenerales);
        CivPlanTrabajos civPlanTrabajos = getPlanTrabajoDao().find(bean.getPlanGenerales().getId());
        if (civPlanTrabajos != null) {
            if (civEtapasGenerales.getEtagenObligatorio().equals("TRUE")) {
                CivEstadoEtapaTrabajos civEstadoEtapaTrabajos = new CivEstadoEtapaTrabajos();
                civEstadoEtapaTrabajos.setEstetatraId(new BigDecimal(1));
                CivEtapasTrabajos civEtapasTrabajos = new CivEtapasTrabajos();
                civEtapasTrabajos.setEtatraId(civEtapasGenerales.getEtagenId());
                civEtapasTrabajos.setCivEstadoEtapaTrabajos(civEstadoEtapaTrabajos);
                civEtapasTrabajos.setCivPlanTrabajos(civPlanTrabajos);
                civEtapasTrabajos.setEtatraDescricion(civEtapasGenerales.getEtagenDescripcion());
                civEtapasTrabajos.setEtatraFechaproceso(civEtapasGenerales.getEtagenFechaproceso());
                civEtapasTrabajos.setEtatraObligatorio(civEtapasGenerales.getEtagenObligatorio());
                civEtapasTrabajos.setEtatraPrioridad(civEtapasGenerales.getEtagenPrioridad());
                getEtapaTrabajoDao().create(civEtapasTrabajos);
            }
        }
        bean.init();
        bean.ListarEtapaGeneralesPorIdPlanGeneral(bean.getPlanGenerales());
    }

    @Override
    public void guardarFasesGeneral(BeanPlanGeneral bean) throws Exception {
        if (bean.getFasesGenerales().getDianim() < bean.getFasesGenerales().getDiamax()) {
            if (Paths.get(bean.getFile().getSubmittedFileName()).getFileName().toString().endsWith(".pdf")) {
                CivFasesGenerales civFasesGenerales = new CivFasesGenerales();
                CivEstadoFasesGenerales civEstadoFasesGenerales = new CivEstadoFasesGenerales();
                civEstadoFasesGenerales.setEstfasgenId(BigDecimal.valueOf(1));
                CivEtapasGenerales civEtapasGenerales = new CivEtapasGenerales();
                civEtapasGenerales.setEtagenId(new BigDecimal(bean.getEtapasGenerales().getId()));
                CivDocumenGenerales civDocumenGenerales = new CivDocumenGenerales();
                CivEstadoDocumengenerales civEstadoDocumengenerales = new CivEstadoDocumengenerales();
                civEstadoDocumengenerales.setEstdocgenId(BigDecimal.valueOf(1));
                civDocumenGenerales.setDocgenDescripcion(Paths.get(bean.getFile().getSubmittedFileName()).getFileName().toString());
                civDocumenGenerales.setCivEstadoDocumengenerales(civEstadoDocumengenerales);
                civDocumenGenerales.setDocgenFechaproceso(new Date());
                civDocumenGenerales.setDocgenArchivo("D:\\Archivo\\" + Paths.get(bean.getFile().getSubmittedFileName()).getFileName().toString());
                getiTDocumentoGenerales().create(civDocumenGenerales);
                civFasesGenerales.setFasgenDescripcion(bean.getFasesGenerales().getDescripcion());
                civFasesGenerales.setCivEstadoFasesGenerales(civEstadoFasesGenerales);
                civFasesGenerales.setFasgenFechaproceso(new Date());
                civFasesGenerales.setCivEtapasGenerales(civEtapasGenerales);
                civFasesGenerales.setFasgenDianim(BigDecimal.valueOf(bean.getFasesGenerales().getDianim()));
                civFasesGenerales.setFasgenDiamax(BigDecimal.valueOf(bean.getFasesGenerales().getDiamax()));
                civFasesGenerales.setFasgenObligatorio(bean.getFasesGenerales().getObligatorio());
                civFasesGenerales.setCivDocumenGenerales(civDocumenGenerales);
                getiTFasesGenerales().create(civFasesGenerales);

                CivEtapasTrabajos civEtapasTrabajos = getEtapaTrabajoDao().find(bean.getEtapasGenerales().getId());
                if (civEtapasTrabajos != null) {
                    if (bean.getFasesGenerales().getObligatorio().equals("TRUE")) {
                        CivFasesTrabajos civFasesTrabajos = new CivFasesTrabajos();
                        CivEstadoReporteTrabajos civEstadoReporteTrabajos = new CivEstadoReporteTrabajos();
                        civEstadoReporteTrabajos.setEstreptraId(BigDecimal.ONE);
                        CivReporteTrabajos civReporteTrabajos = new CivReporteTrabajos();
                        civReporteTrabajos.setReptraId(civDocumenGenerales.getDocgenId());
                        civReporteTrabajos.setReptraArchivo(civDocumenGenerales.getDocgenArchivo());
                        civReporteTrabajos.setReptraDescripcion(civDocumenGenerales.getDocgenDescripcion());
                        civReporteTrabajos.setReptraFechaproceso(civDocumenGenerales.getDocgenFechaproceso());
                        civReporteTrabajos.setCivEstadoReporteTrabajos(civEstadoReporteTrabajos);
                        getReporteTrabajoDao().create(civReporteTrabajos);
                        CivEstadoFasesTrabajos civEstadoFasesTrabajos = new CivEstadoFasesTrabajos();
                        civEstadoFasesTrabajos.setEstfastraId(BigDecimal.valueOf(1));
                        civFasesTrabajos.setFastraId(civFasesGenerales.getFasgenId());
                        civFasesTrabajos.setFastraDescripcion(civFasesGenerales.getFasgenDescripcion());
                        civFasesTrabajos.setCivEstadoFasesTrabajos(civEstadoFasesTrabajos);
                        civFasesTrabajos.setFastraFechaproceso(civFasesGenerales.getFasgenFechaproceso());
                        civFasesTrabajos.setFastraDianim(civFasesGenerales.getFasgenDianim());
                        civFasesTrabajos.setFastraDiamax(civFasesGenerales.getFasgenDiamax());
                        civFasesTrabajos.setFastraObligatorio(civFasesGenerales.getFasgenObligatorio());
                        civFasesTrabajos.setCivEtapasTrabajos(civEtapasTrabajos);
                        civFasesTrabajos.setCivReporteTrabajos(civReporteTrabajos);
                        getFasesTrabajoDao().create(civFasesTrabajos);
                    }
                }
                InputStream stream = bean.getFile().getInputStream();
                Files.copy(stream, new File("D:\\Archivo\\" + Paths.get(bean.getFile().getSubmittedFileName()).getFileName().toString()).toPath(), StandardCopyOption.REPLACE_EXISTING);
                RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("$('#faseGeneral').modal('hide')");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Fase agregada correctamente", null));
                listarFasesGeneralesPorEtapa(bean);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Solo se puede cargar archivo pdf", null));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Dia minimo debe ser menor que dia maximo", null));
        }
    }

    @Override
    public void guardarPlanGeneral(BeanPlanGeneral bean) throws Exception {
        CivPlanGenerales civPlanGenerales = new CivPlanGenerales();
        CivPlanGenerales civPlanGeneralesColor = getiTPlanGeneral().getCivPlanGeneralByColor(bean.getPlanGenerales().getColor());
        CivPlanGenerales civPlanGeneralesDescripcion = getiTPlanGeneral().getCivPlanGeneralByDescripcion(bean.getPlanGenerales().getDescripcion().toUpperCase());
        if (civPlanGeneralesColor != null || civPlanGeneralesDescripcion != null) {
            if (civPlanGeneralesColor != null) {
                FacesContext.getCurrentInstance().addMessage("planMensajeGeneral", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "El color ya existe en el sistema", "Plan General Creado exitosamente"));
            }
            if (civPlanGeneralesDescripcion != null) {
                FacesContext.getCurrentInstance().addMessage("planMensajeGeneral", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "La descripcion ya existe en el sistema", "Plan General Creado exitosamente"));
            }

        } else {
            CivEstadoPlanGenerales civEstadoPlanGenerales = new CivEstadoPlanGenerales();
            civEstadoPlanGenerales.setEstplagenId(new BigDecimal(1));
            civPlanGenerales.setPlagenDescripcion(bean.getPlanGenerales().getDescripcion().toUpperCase());
            civPlanGenerales.setCivEstadoPlanGenerales(civEstadoPlanGenerales);
            civPlanGenerales.setPlagenFechaproceso(new Date());
            civPlanGenerales.setPlagenColor(bean.getPlanGenerales().getColor());
            getiTPlanGeneral().create(civPlanGenerales);
            List<CivEtapasGenerales> civEtapaGeneralesPorDefecto = new ArrayList<>();
            CivEtapasGenerales persuasiva = new CivEtapasGenerales();
            persuasiva.setCivPlanGenerales(civPlanGenerales);
            persuasiva.setCivEstadoEtapasGenerales(getEstadoEtapageneral().find(BigDecimal.ONE));
            persuasiva.setEtagenDescripcion("Persuasiva");
            persuasiva.setEtagenFechaproceso(new Date());
            persuasiva.setEtagenObligatorio("FALSE");
            persuasiva.setEtagenPrioridad(new BigDecimal(1));
            CivEtapasGenerales juridica = new CivEtapasGenerales();
            juridica.setCivPlanGenerales(civPlanGenerales);
            juridica.setCivEstadoEtapasGenerales(getEstadoEtapageneral().find(BigDecimal.ONE));
            juridica.setEtagenDescripcion("Juridica");
            juridica.setEtagenFechaproceso(new Date());
            juridica.setEtagenObligatorio("TRUE");
            juridica.setEtagenPrioridad(new BigDecimal(2));
            CivEtapasGenerales embargo = new CivEtapasGenerales();
            embargo.setCivPlanGenerales(civPlanGenerales);
            embargo.setCivEstadoEtapasGenerales(getEstadoEtapageneral().find(BigDecimal.ONE));
            embargo.setEtagenDescripcion("Embargo");
            embargo.setEtagenFechaproceso(new Date());
            embargo.setEtagenObligatorio("TRUE");
            embargo.setEtagenPrioridad(new BigDecimal(3));
            civEtapaGeneralesPorDefecto.add(persuasiva);
            civEtapaGeneralesPorDefecto.add(juridica);
            civEtapaGeneralesPorDefecto.add(embargo);
            for (int i = 0; i < civEtapaGeneralesPorDefecto.size(); i++) {
                getItEstapaGeneral().create(civEtapaGeneralesPorDefecto.get(i));
            }
            bean.init();
            FacesContext.getCurrentInstance().addMessage("planMensajeGeneral", new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Plan general creado exitosamente", "Plan General Creado exitosamente"));
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("$('#planGeneral').modal('hide')");

        }
    }

    @Override
    public void listarEtapaGeneralesPorIdPlanGeneral(BeanPlanGeneral bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<CivEtapasGenerales> listCivEtapasGenerales = getItEstapaGeneral().findAllEtapaByIdPlanGeneral(session, bean.getPlanGenerales().getId());
        if (listCivEtapasGenerales != null) {
            bean.setListadoEtapasGeneraleses(new ArrayList<>());
            for (CivEtapasGenerales civEtapasGeneral : listCivEtapasGenerales) {
                CivPlanGenerales civPlanGenerales = getiTPlanGeneral().getCivPlanGeneral(civEtapasGeneral.getCivPlanGenerales().getPlagenId().intValue());
                EtapasGenerales etapasGenerales = new EtapasGenerales(civEtapasGeneral, civEtapasGeneral.getCivEstadoEtapasGenerales(), civPlanGenerales);
                if (etapasGenerales.getEstadoEtapasGenerales().getId() == 1) {
                    bean.getListadoEtapasGeneraleses().add(etapasGenerales);
                }
            }

        } else {
            bean.setListadoEtapasGeneraleses(new ArrayList<>());
        }

        session.close();
    }

    @Override
    public void listarEstadoEtapasGenerales(BeanPlanGeneral bean) throws Exception {
        List<CivEstadoEtapasGenerales> listEstadoEtapageneral = getEstadoEtapageneral().findAll();
        for (CivEstadoEtapasGenerales EstadoEtapasGenerales : listEstadoEtapageneral) {
            EstadoEtapasGenerales estadoEtapasGenerales = new EstadoEtapasGenerales(EstadoEtapasGenerales);
            bean.getEstadoEtapasGenerales().add(estadoEtapasGenerales);
        }
    }

    @Override
    public void listarEstadoFasesGenerales(BeanPlanGeneral bean) throws Exception {
        List<CivEstadoFasesGenerales> listCivEstadoFasesGeneraleses = getiTEstadoFasesGenerales().findAll();
        for (CivEstadoFasesGenerales civEstadoFasesGeneralese : listCivEstadoFasesGeneraleses) {
            EstadoFasesGenerales estadoFasesGenerales = new EstadoFasesGenerales(civEstadoFasesGeneralese);
            bean.getListCivEstadoFasesGeneraleses().add(civEstadoFasesGeneralese);
        }
    }

    @Override
    public void listarEstadoGenerales(BeanPlanGeneral bean) throws Exception {
        List<CivEstadoPlanGenerales> listEstadoPlanGeneraleses = getiTEstado().findAll();
        for (CivEstadoPlanGenerales listEstadoPlanGeneralese : listEstadoPlanGeneraleses) {
            EstadoPlanGenerales estadoPlanGenerales = new EstadoPlanGenerales(listEstadoPlanGeneralese);
            bean.getListadoEStadoPlanesGenerales().add(estadoPlanGenerales);
        }
    }

    @Override
    public void listarFasesGeneralesPorEtapa(BeanPlanGeneral bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        boolean faseObligatoria = false;
        bean.setListFasesGenerales(new ArrayList<>());
        List<CivFasesGenerales> listCivFasesGenerales = getiTFasesGenerales().AllListByEtapaGeneral(session, bean.getEtapasGenerales().getId());
        for (CivFasesGenerales civFasesGenerale : listCivFasesGenerales) {
            if (civFasesGenerale.getFasgenObligatorio().equals("TRUE")) {
                faseObligatoria = true;
            }
            //  faseObligatoria =  civFasesGenerale.getFasgenObligatorio().equals("TRUE") ? true : false;
            CivEtapasGenerales civEtapasGenerales = getItEstapaGeneral().getCivEtapaGeneral(civFasesGenerale.getCivEtapasGenerales().getEtagenId().intValue());
            CivDocumenGenerales civDocumenGenerales = getiTDocumentoGenerales().getCivDocumentoGeneral(civFasesGenerale.getCivDocumenGenerales().getDocgenId().intValue());
            FasesGenerales fasesGenerales = new FasesGenerales(civFasesGenerale, civFasesGenerale.getCivEstadoFasesGenerales(), civEtapasGenerales, civDocumenGenerales);
            if (fasesGenerales.getObligatorio().equals("TRUE")) {
                fasesGenerales.setObligatorio("Si");
            } else {
                fasesGenerales.setObligatorio("No");
            }
            bean.getListFasesGenerales().add(fasesGenerales);
        }

        if (!faseObligatoria) {
            FacesContext.getCurrentInstance().addMessage("planMensajeGeneral", new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "La etapa " + bean.getEtapasGenerales().getDescripcion() + " debe contener por lo menos una fases obligatoria" , "Plan General Creado exitosamente"));
        }

        session.close();
    }

    @Override
    public void listarPlanesGenerales(BeanPlanGeneral bean) throws Exception {
        bean.setListadoPlanGeneraleses(new ArrayList<>());
        List<CivPlanGenerales> listcivPlanGeneral = getiTPlanGeneral().findAll();
        if (listcivPlanGeneral != null && listcivPlanGeneral.size() > 0) {
            for (CivPlanGenerales civPlanGenerales : listcivPlanGeneral) {
                if (!Hibernate.isInitialized(civPlanGenerales.getCivEstadoPlanGenerales())) {
                    Hibernate.initialize(civPlanGenerales.getCivEstadoPlanGenerales());
                }
                if (civPlanGenerales.getCivEstadoPlanGenerales().getEstplagenId().intValue() == 1) {
                    PlanGenerales planGenerales = new PlanGenerales(civPlanGenerales, civPlanGenerales.getCivEstadoPlanGenerales());
                    bean.getListadoPlanGeneraleses().add(planGenerales);
                }

            }
        }
    }

    /**
     * @return the planGeneralDAO
     */
    public ITPlanGeneral getiTPlanGeneral() {
        return planGeneralDAO;
    }

    /**
     * @param iTPlanGeneral the planGeneralDAO to set
     */
    public void setiTPlanGeneral(ITPlanGeneral iTPlanGeneral) {
        this.planGeneralDAO = iTPlanGeneral;
    }

    /**
     * @return the estadoPlanGeneralDAO
     */
    public ITEstadoPlanGeneral getiTEstado() {
        return estadoPlanGeneralDAO;
    }

    /**
     * @param iTEstado the estadoPlanGeneralDAO to set
     */
    public void setiTEstado(ITEstadoPlanGeneral iTEstado) {
        this.estadoPlanGeneralDAO = iTEstado;
    }

    /**
     * @return the etapaGeneralDAO
     */
    public ITEstapaGeneral getItEstapaGeneral() {
        return etapaGeneralDAO;
    }

    /**
     * @param itEstapaGeneral the etapaGeneralDAO to set
     */
    public void setItEstapaGeneral(ITEstapaGeneral itEstapaGeneral) {
        this.etapaGeneralDAO = itEstapaGeneral;
    }

    /**
     * @return the estadoEtapaGeneralDAO
     */
    public ITEstadoEtapageneral getEstadoEtapageneral() {
        return estadoEtapaGeneralDAO;
    }

    /**
     * @param estadoEtapageneral the estadoEtapaGeneralDAO to set
     */
    public void setEstadoEtapageneral(ITEstadoEtapageneral estadoEtapageneral) {
        this.estadoEtapaGeneralDAO = estadoEtapageneral;
    }

    /**
     * @return the faseGeneralDAO
     */
    public ITFasesGenerales getiTFasesGenerales() {
        return getFaseGeneralDAO();
    }

    /**
     * @param iTFasesGenerales the faseGeneralDAO to set
     */
    public void setiTFasesGenerales(ITFasesGenerales iTFasesGenerales) {
        this.setFaseGeneralDAO(iTFasesGenerales);
    }

    /**
     * @return the estadoFaseGeneralesDAO
     */
    public ITEstadoFasesGenerales getiTEstadoFasesGenerales() {
        return estadoFaseGeneralesDAO;
    }

    /**
     * @param iTEstadoFasesGenerales the estadoFaseGeneralesDAO to set
     */
    public void setiTEstadoFasesGenerales(ITEstadoFasesGenerales iTEstadoFasesGenerales) {
        this.estadoFaseGeneralesDAO = iTEstadoFasesGenerales;
    }

    /**
     * @return the documentosGeneralesDAO
     */
    public ITDocumentoGenerales getiTDocumentoGenerales() {
        return documentosGeneralesDAO;
    }

    /**
     * @param iTDocumentoGenerales the documentosGeneralesDAO to set
     */
    public void setiTDocumentoGenerales(ITDocumentoGenerales iTDocumentoGenerales) {
        this.documentosGeneralesDAO = iTDocumentoGenerales;
    }

    /**
     * @return the faseGeneralDAO
     */
    public ITFasesGenerales getFaseGeneralDAO() {
        return faseGeneralDAO;
    }

    /**
     * @param faseGeneralDAO the faseGeneralDAO to set
     */
    public void setFaseGeneralDAO(ITFasesGenerales faseGeneralDAO) {
        this.faseGeneralDAO = faseGeneralDAO;
    }

    /**
     * @return the planTrabajoDao
     */
    public ITPlanTrabajo getPlanTrabajoDao() {
        return planTrabajoDao;
    }

    /**
     * @param planTrabajoDao the planTrabajoDao to set
     */
    public void setPlanTrabajoDao(ITPlanTrabajo planTrabajoDao) {
        this.planTrabajoDao = planTrabajoDao;
    }

    /**
     * @return the etapaTrabajoDao
     */
    public ITEtapasTrabajo getEtapaTrabajoDao() {
        return etapaTrabajoDao;
    }

    /**
     * @param etapaTrabajoDao the etapaTrabajoDao to set
     */
    public void setEtapaTrabajoDao(ITEtapasTrabajo etapaTrabajoDao) {
        this.etapaTrabajoDao = etapaTrabajoDao;
    }

    /**
     * @return the fasesTrabajoDao
     */
    public ITFasesTrabajo getFasesTrabajoDao() {
        return fasesTrabajoDao;
    }

    /**
     * @param fasesTrabajoDao the fasesTrabajoDao to set
     */
    public void setFasesTrabajoDao(ITFasesTrabajo fasesTrabajoDao) {
        this.fasesTrabajoDao = fasesTrabajoDao;
    }

    /**
     * @return the reporteTrabajoDao
     */
    public ITReporteTrabajos getReporteTrabajoDao() {
        return reporteTrabajoDao;
    }

    /**
     * @param reporteTrabajoDao the reporteTrabajoDao to set
     */
    public void setReporteTrabajoDao(ITReporteTrabajos reporteTrabajoDao) {
        this.reporteTrabajoDao = reporteTrabajoDao;
    }

    /**
     * @return the movimientosDao
     */
    public ITMovimientos getMovimientosDao() {
        return movimientosDao;
    }

    /**
     * @param movimientosDao the movimientosDao to set
     */
    public void setMovimientosDao(ITMovimientos movimientosDao) {
        this.movimientosDao = movimientosDao;
    }

}
