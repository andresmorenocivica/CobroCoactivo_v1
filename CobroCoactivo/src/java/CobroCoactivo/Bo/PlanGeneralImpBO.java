package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanPlanGeneral;
import CobroCoactivo.Dao.DaoDocumentosGenerales;
import CobroCoactivo.Dao.DaoEstadoEtapaGeneral;
import CobroCoactivo.Dao.DaoEstadoFasesGenerales;
import CobroCoactivo.Dao.DaoEstadoPlanGeneral;
import CobroCoactivo.Dao.DaoEstapaGeneral;
import CobroCoactivo.Dao.DaoFasesGenerales;
import CobroCoactivo.Dao.DaoPlanGeneral;
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
import CobroCoactivo.Dao.ITFasesGenerales;
import CobroCoactivo.Modelo.DocumenGenerales;
import CobroCoactivo.Modelo.EstadoDocumengenerales;
import CobroCoactivo.Modelo.EstadoEtapasGenerales;
import CobroCoactivo.Modelo.EstadoFasesGenerales;
import CobroCoactivo.Modelo.EstadoPlanGenerales;
import CobroCoactivo.Modelo.EtapasGenerales;
import CobroCoactivo.Modelo.FasesGenerales;
import CobroCoactivo.Persistencia.CivDocumenGenerales;
import CobroCoactivo.Persistencia.CivEstadoDocumengenerales;
import CobroCoactivo.Persistencia.CivEstadoEtapasGenerales;
import CobroCoactivo.Persistencia.CivEstadoFasesGenerales;
import CobroCoactivo.Persistencia.CivEtapasGenerales;
import CobroCoactivo.Persistencia.CivFasesGenerales;
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

    private ITPlanGeneral planGeneralDAO;
    private ITEstadoPlanGeneral estadoPlanGeneralDAO;
    private ITEstapaGeneral etapaGeneralDAO;
    private ITEstadoEtapageneral estadoEtapaGeneralDAO;
    private ITFasesGenerales faseGeneralDAO;
    private ITEstadoFasesGenerales estadoFaseGeneralesDAO;
    private ITDocumentoGenerales documentosGeneralesDAO;

    public PlanGeneralImpBO() {
        planGeneralDAO = new DaoPlanGeneral();
        estadoPlanGeneralDAO = new DaoEstadoPlanGeneral();
        etapaGeneralDAO = new DaoEstapaGeneral();
        estadoEtapaGeneralDAO = new DaoEstadoEtapaGeneral();
        faseGeneralDAO = new DaoFasesGenerales();
        estadoFaseGeneralesDAO = new DaoEstadoFasesGenerales();
        documentosGeneralesDAO = new DaoDocumentosGenerales();
    }

    @Override
    public void GuardarEtapaGeneral(BeanPlanGeneral bean) throws Exception {
        CivEtapasGenerales civEtapasGenerales = new CivEtapasGenerales();
        CivEstadoEtapasGenerales civEstadoEtapasGenerales = new CivEstadoEtapasGenerales();
        CivPlanGenerales civPlanGenerales = new CivPlanGenerales();
        civPlanGenerales.setPlagenId(new BigDecimal(bean.getPlanGenerales().getId()));
        civEstadoEtapasGenerales.setEstetagenId(new BigDecimal(bean.getIdEstapaGeneral()));

        civEtapasGenerales.setCivEstadoEtapasGenerales(civEstadoEtapasGenerales);
        civEtapasGenerales.setCivPlanGenerales(civPlanGenerales);
        civEtapasGenerales.setEtagenDescripcion(bean.getEtapasGenerales().getDescripcion());
        civEtapasGenerales.setEtagenFechaproceso(new Date());
        civEtapasGenerales.setEtagenObligatorio(bean.getObligatorio());
        civEtapasGenerales.setEtagenPrioridad(new BigDecimal(bean.getEtapasGenerales().getPrioridad()));
        getItEstapaGeneral().create(civEtapasGenerales);

        bean.init();
        bean.ListarEtapaGeneralesPorIdPlanGeneral(bean.getPlanGenerales());
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
    public void ActualizarPlanGeneral(BeanPlanGeneral bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        boolean flag = false;
        CivPlanGenerales civPlanGenerales = new CivPlanGenerales();
        CivEstadoPlanGenerales civEstadoPlanGenerales = new CivEstadoPlanGenerales();
        civEstadoPlanGenerales.setEstplagenId(new BigDecimal(bean.getIdEstadoGeneral()));
        civPlanGenerales.setPlagenId(new BigDecimal(bean.getPlanGenerales().getId()));
        civPlanGenerales.setPlagenDescripcion(bean.getPlanGenerales().getDescripcion());
        civPlanGenerales.setPlagenFechaproceso(new Date());
        civPlanGenerales.setCivEstadoPlanGenerales(civEstadoPlanGenerales);
        if (bean.getIdEstadoGeneral() != 1) {
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

                } else {
                    getiTPlanGeneral().update(civPlanGenerales);
                    bean.init();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Plan actualizado correctamnete", null));
                }
            } else {
                getiTPlanGeneral().update(civPlanGenerales);
                bean.init();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Plan actualizado correctamente", null));
            }

        } else {
            getiTPlanGeneral().update(civPlanGenerales);
            bean.init();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Plan actualizado correctamente", null));
        }

        session.close();
    }

    @Override
    public void ActualizarEtapaGeneral(BeanPlanGeneral bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        boolean validador = false;
        CivEtapasGenerales civEtapasGenerales = new CivEtapasGenerales();
        CivEstadoEtapasGenerales civEstadoEtapasGenerales = new CivEstadoEtapasGenerales();
        civEstadoEtapasGenerales.setEstetagenId(new BigDecimal(bean.getIdEstapaGeneral()));
        CivPlanGenerales civPlanGenerales = new CivPlanGenerales();
        civPlanGenerales.setPlagenId(new BigDecimal(bean.getPlanGenerales().getId()));

        civEtapasGenerales.setEtagenId(new BigDecimal(bean.getEtapasGenerales().getId()));
        civEtapasGenerales.setCivEstadoEtapasGenerales(civEstadoEtapasGenerales);
        civEtapasGenerales.setCivPlanGenerales(civPlanGenerales);
        civEtapasGenerales.setEtagenDescripcion(bean.getEtapasGenerales().getDescripcion());
        civEtapasGenerales.setEtagenObligatorio(bean.getObligatorio());
        civEtapasGenerales.setEtagenPrioridad(new BigDecimal(bean.getEtapasGenerales().getPrioridad()));
        civEtapasGenerales.setEtagenFechaproceso(new Date());

        if (bean.getIdEstapaGeneral() != 1) {
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
                            "Etapa actualizado Correctamente", null));
                }

            } else {
                getItEstapaGeneral().update(civEtapasGenerales);
                bean.init();
                bean.ListarEtapaGeneralesPorIdPlanGeneral(bean.getPlanGenerales());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Etapa actualizado correctamente", null));
            }

        } else {
            getItEstapaGeneral().update(civEtapasGenerales);
            bean.init();
            bean.ListarEtapaGeneralesPorIdPlanGeneral(bean.getPlanGenerales());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "etapa Actualizado Correctamente", null));
        }

        session.close();
    }

    @Override
    public void ListarPlanesGenerales(BeanPlanGeneral bean) throws Exception {
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

    @Override
    public void ListarEstadoGenerales(BeanPlanGeneral bean) throws Exception {
        List<CivEstadoPlanGenerales> listEstadoPlanGeneraleses = getiTEstado().findAll();
        for (CivEstadoPlanGenerales listEstadoPlanGeneralese : listEstadoPlanGeneraleses) {
            EstadoPlanGenerales estadoPlanGenerales = new EstadoPlanGenerales(listEstadoPlanGeneralese);
            bean.getListadoEStadoPlanesGenerales().add(estadoPlanGenerales);
        }
    }

    @Override
    public void ListarEstadoEtapasGenerales(BeanPlanGeneral bean) throws Exception {
        List<CivEstadoEtapasGenerales> listEstadoEtapageneral = getEstadoEtapageneral().findAll();
        for (CivEstadoEtapasGenerales EstadoEtapasGenerales : listEstadoEtapageneral) {
            EstadoEtapasGenerales estadoEtapasGenerales = new EstadoEtapasGenerales(EstadoEtapasGenerales);
            bean.getEstadoEtapasGenerales().add(estadoEtapasGenerales);
        }
    }

    @Override
    public void GuardarPlanGeneral(BeanPlanGeneral bean) throws Exception {
        CivPlanGenerales civPlanGenerales = new CivPlanGenerales();
        CivEstadoPlanGenerales civEstadoPlanGenerales = new CivEstadoPlanGenerales();
        civEstadoPlanGenerales.setEstplagenId(new BigDecimal(bean.getIdEstadoGeneral()));
        civPlanGenerales.setPlagenDescripcion(bean.getPlanGenerales().getDescripcion().toUpperCase());
        civPlanGenerales.setCivEstadoPlanGenerales(civEstadoPlanGenerales);
        civPlanGenerales.setPlagenFechaproceso(new Date());
        getiTPlanGeneral().create(civPlanGenerales);
        List<CivEtapasGenerales> civEtapaGeneralesPorDefecto = new ArrayList<>();
        CivEtapasGenerales persuasiva = new CivEtapasGenerales();
        persuasiva.setCivPlanGenerales(civPlanGenerales);
        persuasiva.setCivEstadoEtapasGenerales(new CivEstadoEtapasGenerales(new BigDecimal(1)));
        persuasiva.setEtagenDescripcion("Persuasiva");
        persuasiva.setEtagenFechaproceso(new Date());
        persuasiva.setEtagenObligatorio("FALSE");
        persuasiva.setEtagenPrioridad(new BigDecimal(1));
        CivEtapasGenerales juridica = new CivEtapasGenerales();
        juridica.setCivPlanGenerales(civPlanGenerales);
        juridica.setCivEstadoEtapasGenerales(new CivEstadoEtapasGenerales(new BigDecimal(1)));
        juridica.setEtagenDescripcion("Juridica");
        juridica.setEtagenFechaproceso(new Date());
        juridica.setEtagenObligatorio("TRUE");
        juridica.setEtagenPrioridad(new BigDecimal(2));
        CivEtapasGenerales embargo = new CivEtapasGenerales();
        embargo.setCivPlanGenerales(civPlanGenerales);
        embargo.setCivEstadoEtapasGenerales(new CivEstadoEtapasGenerales(new BigDecimal(1)));
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
    public void guardarFasesGeneral(BeanPlanGeneral bean) throws Exception {
        if (bean.getFasesGenerales().getDianim()< bean.getFasesGenerales().getDiamax()) {
            CivFasesGenerales civFasesGenerales = new CivFasesGenerales();
            CivEstadoFasesGenerales civEstadoFasesGenerales = new CivEstadoFasesGenerales();
            civEstadoFasesGenerales.setEstfasgenId(BigDecimal.valueOf(bean.getIdEStadoFasesGeneral()));
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
            InputStream stream = bean.getFile().getInputStream();
            Files.copy(stream, new File("D:\\Archivo\\" + Paths.get(bean.getFile().getSubmittedFileName()).getFileName().toString()).toPath(), StandardCopyOption.REPLACE_EXISTING);
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("$('#faseGeneral').modal('hide')");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Fase agregada correctamente", null));
            listarFasesGeneralesPorEtapa(bean);

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Dia minimo debe ser menor que dia maximo", null));
        }
    }

    @Override
    public void listarFasesGeneralesPorEtapa(BeanPlanGeneral bean) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        bean.setListFasesGenerales(new ArrayList<>());
        List<CivFasesGenerales> listCivFasesGenerales = getiTFasesGenerales().AllListByEtapaGeneral(session, bean.getEtapasGenerales().getId());
        for (CivFasesGenerales civFasesGenerale : listCivFasesGenerales) {
            CivEtapasGenerales civEtapasGenerales = getItEstapaGeneral().getCivEtapaGeneral(civFasesGenerale.getCivEtapasGenerales().getEtagenId().intValue());
            CivDocumenGenerales civDocumenGenerales = getiTDocumentoGenerales().getCivDocumentoGeneral(civFasesGenerale.getCivDocumenGenerales().getDocgenId().intValue());
            FasesGenerales fasesGenerales = new FasesGenerales(civFasesGenerale,civFasesGenerale.getCivEstadoFasesGenerales(),civEtapasGenerales,civDocumenGenerales);
            bean.getListFasesGenerales().add(fasesGenerales);
        }
        session.close();
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
        return faseGeneralDAO;
    }

    /**
     * @param iTFasesGenerales the faseGeneralDAO to set
     */
    public void setiTFasesGenerales(ITFasesGenerales iTFasesGenerales) {
        this.faseGeneralDAO = iTFasesGenerales;
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

}
