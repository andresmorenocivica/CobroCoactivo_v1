/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanGestionPlanTrabajo;
import CobroCoactivo.Dao.DaoEstadoEtapasTrabajo;
import CobroCoactivo.Dao.DaoEstapaGeneral;
import CobroCoactivo.Dao.DaoEtapasTrabajo;
import CobroCoactivo.Dao.DaoFasesGenerales;
import CobroCoactivo.Dao.DaoFasesTrabajo;
import CobroCoactivo.Dao.DaoPlanGeneral;
import CobroCoactivo.Dao.DaoPlanTrabajo;
import CobroCoactivo.Dao.DaoReporteTrabajos;
import CobroCoactivo.Dao.ITEstadoEtapasTrabajo;
import CobroCoactivo.Dao.ITEstapaGeneral;
import CobroCoactivo.Dao.ITEtapasTrabajo;
import CobroCoactivo.Dao.ITFasesGenerales;
import CobroCoactivo.Dao.ITFasesTrabajo;
import CobroCoactivo.Dao.ITPlanGeneral;
import CobroCoactivo.Dao.ITPlanTrabajo;
import CobroCoactivo.Dao.ITReporteTrabajos;
import CobroCoactivo.Modelo.EtapasGenerales;
import CobroCoactivo.Modelo.EtapasTrabajos;
import CobroCoactivo.Modelo.FasesGenerales;
import CobroCoactivo.Modelo.FasesTrabajos;
import CobroCoactivo.Modelo.PlanGenerales;
import CobroCoactivo.Modelo.PlanTrabajos;
import CobroCoactivo.Persistencia.CivEstadoEtapaTrabajos;
import CobroCoactivo.Persistencia.CivEstadoFasesTrabajos;
import CobroCoactivo.Persistencia.CivEstadoPlanTrabajos;
import CobroCoactivo.Persistencia.CivEstadoReporteTrabajos;
import CobroCoactivo.Persistencia.CivEtapasGenerales;
import CobroCoactivo.Persistencia.CivEtapasTrabajos;
import CobroCoactivo.Persistencia.CivFasesGenerales;
import CobroCoactivo.Persistencia.CivFasesTrabajos;
import CobroCoactivo.Persistencia.CivPlanGenerales;
import CobroCoactivo.Persistencia.CivPlanTrabajos;
import CobroCoactivo.Persistencia.CivReporteTrabajos;
import CobroCoactivo.Utility.HibernateUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;

/**
 *
 * @author jvergara
 * @version 1.0, 27/06/2008
 */
public class GestionPlanTrabajoImpBO implements GestionPlanTrabajoBO {

    private ITEstapaGeneral EtapaGeneralDAO;
    private ITEtapasTrabajo EtapasTrabajoDAO;
    private ITEstadoEtapasTrabajo estadoEtapasTrabajoDAO;
    private ITFasesGenerales FasesGeneralesDAO;
    private ITFasesTrabajo FasesTrabajoDAO;
    private ITPlanGeneral planGeneralDAO;
    private ITPlanTrabajo PlanTrabajoDAO;

    private ITReporteTrabajos ReporteTrabajosDAO;

    /**
     * contructor e inicializador Dao
     *
     */
    public GestionPlanTrabajoImpBO() {
        EtapaGeneralDAO = new DaoEstapaGeneral();
        EtapasTrabajoDAO = new DaoEtapasTrabajo();
        estadoEtapasTrabajoDAO = new DaoEstadoEtapasTrabajo();
        FasesGeneralesDAO = new DaoFasesGenerales();
        FasesTrabajoDAO = new DaoFasesTrabajo();
        planGeneralDAO = new DaoPlanGeneral();
        PlanTrabajoDAO = new DaoPlanTrabajo();
        ReporteTrabajosDAO = new DaoReporteTrabajos();

    }

    @Override
    public void actualizarFases(BeanGestionPlanTrabajo beanGestionPlanTrabajo) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            for (FasesTrabajos fasesTrabajos : beanGestionPlanTrabajo.getListFasesTrabajoses()) {
                CivFasesGenerales civFasesGenerales = getFasesGeneralesDAO().find(session, BigDecimal.valueOf(fasesTrabajos.getId()));
                if (fasesTrabajos.isUpdateFase()) {
                    if (civFasesGenerales.getFasgenDiamax().intValue() >= fasesTrabajos.getDiamax()) {
                        CivFasesTrabajos civFasesTrabajos = getFasesTrabajoDAO().find(session, BigDecimal.valueOf(fasesTrabajos.getId()));
                        civFasesTrabajos.setFastraDianim(BigDecimal.valueOf(fasesTrabajos.getDianim()));
                        civFasesTrabajos.setFastraDiamax(BigDecimal.valueOf(fasesTrabajos.getDiamax()));
                        getFasesTrabajoDAO().update(session, civFasesTrabajos);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Fase de trabajo actualizada correctamente", "Etapa De trabajo exitosamente"));
                        fasesTrabajos.setUpdateFase(false);

                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Fase de trabajo el dia maximo no puede ser mayor a " + civFasesGenerales.getFasgenDiamax(), "Etapa De trabajo exitosamente"));
                    }
                }
            }

        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void getListEtapaGenerales(BeanGestionPlanTrabajo beanGestionPlanTrabajo) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            beanGestionPlanTrabajo.setListEtapaGenerales(new ArrayList<>());
            List<CivEtapasGenerales> listEtapasGenerales = getEtapaGeneralDAO().findAllEtapaByIdPlanGeneral(session, beanGestionPlanTrabajo.getPlanTrabajos().getId());
            for (CivEtapasGenerales civEtapasGeneral : listEtapasGenerales) {
                boolean sw = true;
                for (EtapasTrabajos etapasTrabajo : beanGestionPlanTrabajo.getListEtapaTrabajos()) {
                    if (etapasTrabajo.getDescricion().equals(civEtapasGeneral.getEtagenDescripcion())) {
                        sw = false;
                    }
                }
                if (sw) {
                    EtapasGenerales etapasGenerales = new EtapasGenerales(civEtapasGeneral, civEtapasGeneral.getCivEstadoEtapasGenerales());
                    beanGestionPlanTrabajo.getListEtapaGenerales().add(etapasGenerales);
                }
            }

        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void getListPlanGenaral(BeanGestionPlanTrabajo beanGestionPlanTrabajo) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            beanGestionPlanTrabajo.setListPlanGenerales(new ArrayList<>());
            List<CivPlanGenerales> listCivPlanGenerales = getPlanGeneralDAO().getListPlanGenerales(session);
            for (CivPlanGenerales civPlanGenerale : listCivPlanGenerales) {
                boolean flag = true;
                for (PlanTrabajos planTrabajo : beanGestionPlanTrabajo.getListPlanTrabajos()) {
                    if (planTrabajo.getDescripcion().equals(civPlanGenerale.getPlagenDescripcion())) {
                        flag = false;
                    }
                }
                if (flag) {
                    PlanGenerales planGenerales = new PlanGenerales(civPlanGenerale, civPlanGenerale.getCivEstadoPlanGenerales());
                    beanGestionPlanTrabajo.getListPlanGenerales().add(planGenerales);
                }
            }

        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void getListaTrabajo(BeanGestionPlanTrabajo beanGestionPlanTrabajo) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            beanGestionPlanTrabajo.setListPlanTrabajos(new ArrayList<>());
            List<CivPlanTrabajos> listCivPlanTrabajos = getPlanTrabajoDAO().findAll(session);
            for (CivPlanTrabajos civPlanTrabajo : listCivPlanTrabajos) {
                PlanTrabajos planTrabajos = new PlanTrabajos(civPlanTrabajo, civPlanTrabajo.getCivEstadoPlanTrabajos());
                beanGestionPlanTrabajo.getListPlanTrabajos().add(planTrabajos);
            }
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void getListEtapaTrabajo(BeanGestionPlanTrabajo beanGestionPlanTrabajo) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            beanGestionPlanTrabajo.setListEtapaTrabajos(new ArrayList<>());
            List<CivEtapasTrabajos> listCivEtapasTrabajos = getEtapasTrabajoDAO().listarEtapasTrabajoByPlantrabajo(session, beanGestionPlanTrabajo.getPlanTrabajos().getId());
            for (CivEtapasTrabajos civEtapasTrabajo : listCivEtapasTrabajos) {
                EtapasTrabajos etapasTrabajos = new EtapasTrabajos(civEtapasTrabajo, civEtapasTrabajo.getCivEstadoEtapaTrabajos());
                beanGestionPlanTrabajo.getListEtapaTrabajos().add(etapasTrabajos);

            }
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void guardarPlanTrabajo(BeanGestionPlanTrabajo beanGestionPlanTrabajo) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            for (int i = 0; i < beanGestionPlanTrabajo.getListPlanGenerales().size(); i++) {
                if (beanGestionPlanTrabajo.getListPlanGenerales().get(i).isSelecionado() == true) {
                    CivPlanTrabajos civPlanTrabajos = new CivPlanTrabajos();
                    CivEstadoPlanTrabajos civEstadoPlanTrabajos = new CivEstadoPlanTrabajos();

                    civEstadoPlanTrabajos.setEstplatraId(new BigDecimal(beanGestionPlanTrabajo.getListPlanGenerales().get(i).getEstadoPlanGenerales().getId()));
                    civPlanTrabajos.setPlatraId(new BigDecimal(beanGestionPlanTrabajo.getListPlanGenerales().get(i).getId()));
                    civPlanTrabajos.setPlatraDescripcion(beanGestionPlanTrabajo.getListPlanGenerales().get(i).getDescripcion());
                    civPlanTrabajos.setCivEstadoPlanTrabajos(civEstadoPlanTrabajos);
                    civPlanTrabajos.setPlatraFechaproceso(beanGestionPlanTrabajo.getListPlanGenerales().get(i).getFechaproceso());
                    civPlanTrabajos.setPlatraColor(beanGestionPlanTrabajo.getListPlanGenerales().get(i).getColor());
                    civPlanTrabajos.setPlatraNumeroactoadm(beanGestionPlanTrabajo.getListPlanGenerales().get(i).getPlanTrabajoNumeroActo());
                    civPlanTrabajos.setPlatraFechaanctoadm(beanGestionPlanTrabajo.getListPlanGenerales().get(i).getPlanTrabajoFechaActo());

                    getPlanTrabajoDAO().create(session, civPlanTrabajos);
                    List<CivEtapasGenerales> listCivEtapasGenerales = getEtapaGeneralDAO().findAllEtapaByIdPlanGeneral(session, civPlanTrabajos.getPlatraId().intValue());
                    for (CivEtapasGenerales etapaGeneral : listCivEtapasGenerales) {
                        if (etapaGeneral.getEtagenObligatorio().equals("TRUE")) {
                            //creamos un civEstado y le pasamos sus valores
                            CivEstadoEtapaTrabajos civEstadoEtapaTrabajos = new CivEstadoEtapaTrabajos();
                            civEstadoEtapaTrabajos.setEstetatraId(etapaGeneral.getCivEstadoEtapasGenerales().getEstetagenId());
                            civEstadoEtapaTrabajos.setEstetatraDescripcion(etapaGeneral.getCivEstadoEtapasGenerales().getEstetagenDescripcion());
                            civEstadoEtapaTrabajos.setEstetatraFechainicial(etapaGeneral.getCivEstadoEtapasGenerales().getEstetagenFechainicial());
                            civEstadoEtapaTrabajos.setEstetatraFechafinal(etapaGeneral.getCivEstadoEtapasGenerales().getEstetagenFechafinal());
                            civEstadoEtapaTrabajos.setEstetatraFechaproceso(etapaGeneral.getCivEstadoEtapasGenerales().getEstetagenFechaproceso());
                            //termina la craecion del objeto civEstado
                            //creamos la CivEtapasTrabajo a guardar
                            CivEtapasTrabajos civEtapasTrabajos = new CivEtapasTrabajos();
                            civEtapasTrabajos.setEtatraId(etapaGeneral.getEtagenId());
                            civEtapasTrabajos.setEtatraDescricion(etapaGeneral.getEtagenDescripcion());
                            civEtapasTrabajos.setCivEstadoEtapaTrabajos(civEstadoEtapaTrabajos);
                            civEtapasTrabajos.setEtatraFechaproceso(etapaGeneral.getEtagenFechaproceso());
                            civEtapasTrabajos.setCivPlanTrabajos(civPlanTrabajos);
                            civEtapasTrabajos.setEtatraObligatorio(etapaGeneral.getEtagenObligatorio());
                            civEtapasTrabajos.setEtatraPrioridad(etapaGeneral.getEtagenPrioridad());
                            //finaliza  la CivEtapasTrabajo a guardar
                            getEtapasTrabajoDAO().create(session, civEtapasTrabajos);
                            List<CivFasesGenerales> listFasesGenerales = getFasesGeneralesDAO().AllListByEtapaGeneral(session, civEtapasTrabajos.getEtatraId().intValue());
                            for (CivFasesGenerales fasesGenerales : listFasesGenerales) {
                                CivEstadoFasesTrabajos civEstadoFasesTrabajos = new CivEstadoFasesTrabajos();
                                civEstadoFasesTrabajos.setEstfastraId(fasesGenerales.getCivEstadoFasesGenerales().getEstfasgenId());
                                civEstadoFasesTrabajos.setEstfastraDescripcion(fasesGenerales.getCivEstadoFasesGenerales().getEstfasgenDescripcion());
                                civEstadoFasesTrabajos.setEstfastraFechainicial(fasesGenerales.getCivEstadoFasesGenerales().getEstfasgenFechainicial());
                                civEstadoFasesTrabajos.setEstfastraFechafinal(fasesGenerales.getCivEstadoFasesGenerales().getEstfasgenFechafinal());
                                civEstadoFasesTrabajos.setEstfastraFechaproceso(fasesGenerales.getCivEstadoFasesGenerales().getEstfasgenFechaproceso());
                                CivEstadoReporteTrabajos civEstadoReporteTrabajos = new CivEstadoReporteTrabajos();
                                civEstadoReporteTrabajos.setEstreptraId(fasesGenerales.getCivDocumenGenerales().getCivEstadoDocumengenerales().getEstdocgenId());
                                civEstadoReporteTrabajos.setEstreptraDescripcion(fasesGenerales.getCivDocumenGenerales().getCivEstadoDocumengenerales().getEstdocgenDescripcion());
                                civEstadoReporteTrabajos.setEstreptraFechainicial(fasesGenerales.getCivDocumenGenerales().getCivEstadoDocumengenerales().getEstdocgenFechainicial());
                                civEstadoReporteTrabajos.setEstreptraFechafinal(fasesGenerales.getCivDocumenGenerales().getCivEstadoDocumengenerales().getEstdocgenFechafinal());
                                civEstadoReporteTrabajos.setEstreptraFechaproceso(fasesGenerales.getCivDocumenGenerales().getCivEstadoDocumengenerales().getEstdocgenFechaproceso());
                                CivReporteTrabajos civReporteTrabajos = new CivReporteTrabajos();
                                civReporteTrabajos.setReptraId(fasesGenerales.getCivDocumenGenerales().getDocgenId());
                                civReporteTrabajos.setReptraDescripcion(fasesGenerales.getCivDocumenGenerales().getDocgenDescripcion());
                                civReporteTrabajos.setReptraFechaproceso(fasesGenerales.getCivDocumenGenerales().getDocgenFechaproceso());
                                civReporteTrabajos.setReptraArchivo(fasesGenerales.getCivDocumenGenerales().getDocgenArchivo());
                                civReporteTrabajos.setCivEstadoReporteTrabajos(civEstadoReporteTrabajos);
                                getReporteTrabajosDAO().create(session, civReporteTrabajos);
                                //Objeto civFasesTrabajos
                                CivFasesTrabajos civFasesTrabajos = new CivFasesTrabajos();
                                civFasesTrabajos.setFastraId(fasesGenerales.getFasgenId());
                                civFasesTrabajos.setFastraDescripcion(fasesGenerales.getFasgenDescripcion());
                                civFasesTrabajos.setCivEstadoFasesTrabajos(civEstadoFasesTrabajos);
                                civFasesTrabajos.setFastraFechaproceso(fasesGenerales.getFasgenFechaproceso());
                                civFasesTrabajos.setFastraDianim(fasesGenerales.getFasgenDianim());
                                civFasesTrabajos.setFastraDiamax(fasesGenerales.getFasgenDiamax());
                                civFasesTrabajos.setCivReporteTrabajos(civReporteTrabajos);
                                civFasesTrabajos.setFastraObligatorio(fasesGenerales.getFasgenObligatorio());
                                civFasesTrabajos.setCivEtapasTrabajos(civEtapasTrabajos);
                                if (civFasesTrabajos.getFastraObligatorio().equals("TRUE")) {
                                    getFasesTrabajoDAO().create(session, civFasesTrabajos);
                                }
                            }

                        }
                    }
                }
            }
            beanGestionPlanTrabajo.init();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Plan de trabajo creado exitosamente", "Plan de trabajo exitosamente"));
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("$('#planTrabajo').modal('hide')");
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void guardarFaseTrabajo(BeanGestionPlanTrabajo beanGestionPlanTrabajo) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            for (int i = 0; i < beanGestionPlanTrabajo.getListFasesGeneral().size(); i++) {
                if (beanGestionPlanTrabajo.getListFasesGeneral().get(i).isSeleccionado()) {
                    CivFasesTrabajos civFasesTrabajos = new CivFasesTrabajos();
                    CivEtapasTrabajos civEtapasTrabajos = new CivEtapasTrabajos();
                    civEtapasTrabajos.setEtatraId(new BigDecimal(beanGestionPlanTrabajo.getEtapasTrabajos().getId()));
                    CivEstadoReporteTrabajos civEstadoReporteTrabajos = new CivEstadoReporteTrabajos();
                    civEstadoReporteTrabajos.setEstreptraId(new BigDecimal(beanGestionPlanTrabajo.getListFasesGeneral().get(i).getDocumenGenerales().getEstadoDocumengenerales().getId()));
                    CivReporteTrabajos civReporteTrabajos = new CivReporteTrabajos();
                    civReporteTrabajos.setReptraId(new BigDecimal(beanGestionPlanTrabajo.getListFasesGeneral().get(i).getDocumenGenerales().getId()));
                    civReporteTrabajos.setReptraArchivo(beanGestionPlanTrabajo.getListFasesGeneral().get(i).getDocumenGenerales().getArchivo());
                    civReporteTrabajos.setReptraDescripcion(beanGestionPlanTrabajo.getListFasesGeneral().get(i).getDocumenGenerales().getDescripcion());
                    civReporteTrabajos.setReptraFechaproceso(beanGestionPlanTrabajo.getListFasesGeneral().get(i).getDocumenGenerales().getFechaproceso());
                    civReporteTrabajos.setCivEstadoReporteTrabajos(civEstadoReporteTrabajos);
                    getReporteTrabajosDAO().create(session, civReporteTrabajos);
                    CivEstadoFasesTrabajos civEstadoFasesTrabajos = new CivEstadoFasesTrabajos();
                    civEstadoFasesTrabajos.setEstfastraId(new BigDecimal(beanGestionPlanTrabajo.getListFasesGeneral().get(i).getEstadoFasesGenerales().getId()));
                    civFasesTrabajos.setFastraId(new BigDecimal(beanGestionPlanTrabajo.getListFasesGeneral().get(i).getId()));
                    civFasesTrabajos.setFastraDescripcion(beanGestionPlanTrabajo.getListFasesGeneral().get(i).getDescripcion());
                    civFasesTrabajos.setCivEstadoFasesTrabajos(civEstadoFasesTrabajos);
                    civFasesTrabajos.setFastraFechaproceso(beanGestionPlanTrabajo.getListFasesGeneral().get(i).getFechaproceso());
                    civFasesTrabajos.setFastraDianim(new BigDecimal(beanGestionPlanTrabajo.getListFasesGeneral().get(i).getDianim()));
                    civFasesTrabajos.setFastraDiamax(new BigDecimal(beanGestionPlanTrabajo.getListFasesGeneral().get(i).getDiamax()));
                    civFasesTrabajos.setFastraObligatorio(beanGestionPlanTrabajo.getListFasesGeneral().get(i).getObligatorio());
                    civFasesTrabajos.setCivEtapasTrabajos(civEtapasTrabajos);
                    civFasesTrabajos.setCivReporteTrabajos(civReporteTrabajos);
                    getFasesTrabajoDAO().create(session, civFasesTrabajos);

                }
            }
            transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "fase de trabajo creada exitosamente", "Etapa De trabajo exitosamente"));
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("$('#fasesTrabajos').modal('hide')");
            getFases(beanGestionPlanTrabajo);
            getfasesGenerales(beanGestionPlanTrabajo);
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void guardarEtapaTrabajo(BeanGestionPlanTrabajo beanGestionPlanTrabajo) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            for (int i = 0; i < beanGestionPlanTrabajo.getListEtapaGenerales().size(); i++) {
                if (beanGestionPlanTrabajo.getListEtapaGenerales().get(i).isSeleccionado() == true) {
                    CivEtapasTrabajos civEtapasTrabajos = new CivEtapasTrabajos();
                    civEtapasTrabajos.setEtatraId(new BigDecimal(beanGestionPlanTrabajo.getListEtapaGenerales().get(i).getId()));
                    civEtapasTrabajos.setEtatraDescricion(beanGestionPlanTrabajo.getListEtapaGenerales().get(i).getDescripcion());
                    civEtapasTrabajos.setEtatraFechaproceso(new java.util.Date());
                    civEtapasTrabajos.setEtatraObligatorio(beanGestionPlanTrabajo.getListEtapaGenerales().get(i).getObligatorio());
                    civEtapasTrabajos.setEtatraPrioridad(new BigDecimal(beanGestionPlanTrabajo.getListEtapaGenerales().get(i).getPrioridad()));
                    CivEstadoEtapaTrabajos civEstadoEtapaTrabajos = getEstadoEtapasTrabajoDAO().find(session, BigDecimal.ONE);
                    civEtapasTrabajos.setCivEstadoEtapaTrabajos(civEstadoEtapaTrabajos);
                    CivPlanTrabajos civPlanTrabajos = getPlanTrabajoDAO().getPlanTrabajo(session, beanGestionPlanTrabajo.getPlanTrabajos().getId());
                    civEtapasTrabajos.setCivPlanTrabajos(civPlanTrabajos);
                    getEtapasTrabajoDAO().create(session, civEtapasTrabajos);
                }
            }
            beanGestionPlanTrabajo.listaEtapaTrabajo(beanGestionPlanTrabajo.getPlanTrabajos());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Etapa De trabajo creada exitosamente", "Etapa De trabajo exitosamente"));
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("$('#EtapasTrabajo').modal('hide')");
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void getFases(BeanGestionPlanTrabajo beanGestionPlanTrabajo) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            beanGestionPlanTrabajo.setListFasesTrabajoses(new ArrayList<>());
            List<CivFasesTrabajos> listFasesTrabajose = getFasesTrabajoDAO().listarFasesTrabajo(session, beanGestionPlanTrabajo.getEtapasTrabajos().getId());
            if (listFasesTrabajose != null) {
                for (CivFasesTrabajos civFasesTrabajos : listFasesTrabajose) {
                    FasesTrabajos fasesTrabajos = new FasesTrabajos(civFasesTrabajos, civFasesTrabajos.getCivEstadoFasesTrabajos(), civFasesTrabajos.getCivReporteTrabajos());
                    beanGestionPlanTrabajo.getListFasesTrabajoses().add(fasesTrabajos);

                }
            }
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void getfasesGenerales(BeanGestionPlanTrabajo beanGestionPlanTrabajo) throws Exception {
        beanGestionPlanTrabajo.setListFasesGeneral(new ArrayList<>());
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<CivFasesGenerales> listFasesGenerales = FasesGeneralesDAO.AllListByEtapaGeneral(session, beanGestionPlanTrabajo.getEtapasTrabajos().getId());
        for (CivFasesGenerales civFasesGenerale : listFasesGenerales) {
            FasesGenerales fasesGenerales = new FasesGenerales(civFasesGenerale, civFasesGenerale.getCivEstadoFasesGenerales(), civFasesGenerale.getCivEtapasGenerales(), civFasesGenerale.getCivDocumenGenerales());
            boolean flag = true;
            for (FasesTrabajos fasesTrabajos : beanGestionPlanTrabajo.getListFasesTrabajoses()) {
                if (fasesTrabajos.getId() == fasesGenerales.getId()) {
                    flag = false;
                }
            }
            if (flag) {
                beanGestionPlanTrabajo.getListFasesGeneral().add(fasesGenerales);
            }

        }
        session.close();
    }

    /**
     * @return the planGeneralDAO
     */
    public ITPlanGeneral getPlanGeneralDAO() {
        return planGeneralDAO;
    }

    /**
     * @param planGeneralDAO the planGeneralDAO to set
     */
    public void setPlanGeneralDAO(ITPlanGeneral planGeneralDAO) {
        this.planGeneralDAO = planGeneralDAO;
    }

    /**
     * @return the PlanTrabajoDAO
     */
    public ITPlanTrabajo getPlanTrabajoDAO() {
        return PlanTrabajoDAO;
    }

    /**
     * @param PlanTrabajoDAO the PlanTrabajoDAO to set
     */
    public void setPlanTrabajoDAO(ITPlanTrabajo PlanTrabajoDAO) {
        this.PlanTrabajoDAO = PlanTrabajoDAO;
    }

    /**
     * @return the EtapasTrabajoDAO
     */
    public ITEtapasTrabajo getEtapasTrabajoDAO() {
        return EtapasTrabajoDAO;
    }

    /**
     * @param EtapasTrabajoDAO the EtapasTrabajoDAO to set
     */
    public void setEtapasTrabajoDAO(ITEtapasTrabajo EtapasTrabajoDAO) {
        this.EtapasTrabajoDAO = EtapasTrabajoDAO;
    }

    /**
     * @return the FasesGeneralesDAO
     */
    public ITFasesGenerales getFasesGeneralesDAO() {
        return FasesGeneralesDAO;
    }

    /**
     * @param FasesGeneralesDAO the FasesGeneralesDAO to set
     */
    public void setFasesGeneralesDAO(ITFasesGenerales FasesGeneralesDAO) {
        this.FasesGeneralesDAO = FasesGeneralesDAO;
    }

    /**
     * @return the FasesTrabajoDAO
     */
    public ITFasesTrabajo getFasesTrabajoDAO() {
        return FasesTrabajoDAO;
    }

    /**
     * @param FasesTrabajoDAO the FasesTrabajoDAO to set
     */
    public void setFasesTrabajoDAO(ITFasesTrabajo FasesTrabajoDAO) {
        this.FasesTrabajoDAO = FasesTrabajoDAO;
    }

    /**
     * @return the ReporteTrabajosDAO
     */
    public ITReporteTrabajos getReporteTrabajosDAO() {
        return ReporteTrabajosDAO;
    }

    /**
     * @param ReporteTrabajosDAO the ReporteTrabajosDAO to set
     */
    public void setReporteTrabajosDAO(ITReporteTrabajos ReporteTrabajosDAO) {
        this.ReporteTrabajosDAO = ReporteTrabajosDAO;
    }

    /**
     * @return the EtapaGeneralDAO
     */
    public ITEstapaGeneral getEtapaGeneralDAO() {
        return EtapaGeneralDAO;
    }

    /**
     * @param EtapaGeneralDAO the EtapaGeneralDAO to set
     */
    public void setEtapaGeneralDAO(ITEstapaGeneral EtapaGeneralDAO) {
        this.EtapaGeneralDAO = EtapaGeneralDAO;
    }

    /**
     * @return the estadoEtapasTrabajoDAO
     */
    public ITEstadoEtapasTrabajo getEstadoEtapasTrabajoDAO() {
        return estadoEtapasTrabajoDAO;
    }

    /**
     * @param estadoEtapasTrabajoDAO the estadoEtapasTrabajoDAO to set
     */
    public void setEstadoEtapasTrabajoDAO(ITEstadoEtapasTrabajo estadoEtapasTrabajoDAO) {
        this.estadoEtapasTrabajoDAO = estadoEtapasTrabajoDAO;
    }
}
