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
import CobroCoactivo.Dao.ITEstadoEtapageneral;
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
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.primefaces.context.RequestContext;

/**
 *
 * @author jvergara
 * @version 1.0, 27/06/2008
 */
public class GestionPlanTrabajoImpBO implements GestionPlanTrabajoBO {

    private ITPlanGeneral planGeneralDAO;
    private ITPlanTrabajo PlanTrabajoDAO;
    private ITEtapasTrabajo EtapasTrabajoDAO;
    private ITEstapaGeneral EtapaGeneralDAO;
    private ITFasesGenerales FasesGeneralesDAO;
    private ITFasesTrabajo FasesTrabajoDAO;
    private ITReporteTrabajos ReporteTrabajosDAO;
    private ITEstadoEtapasTrabajo estadoEtapasTrabajoDAO;

    /**
     * contructor e inicializador Dao
     *
     */
    public GestionPlanTrabajoImpBO() {
        planGeneralDAO = new DaoPlanGeneral();
        PlanTrabajoDAO = new DaoPlanTrabajo();
        EtapasTrabajoDAO = new DaoEtapasTrabajo();
        EtapaGeneralDAO = new DaoEstapaGeneral();
        FasesGeneralesDAO = new DaoFasesGenerales();
        FasesTrabajoDAO = new DaoFasesTrabajo();
        ReporteTrabajosDAO = new DaoReporteTrabajos();
        estadoEtapasTrabajoDAO = new DaoEstadoEtapasTrabajo();
    }

    @Override
    public void getListPlanGenaral(BeanGestionPlanTrabajo beanGestionPlanTrabajo) throws Exception {
        beanGestionPlanTrabajo.setListPlanGenerales(new ArrayList<>());
        List<CivPlanGenerales> listCivPlanGenerales = getPlanGeneralDAO().findAll();
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
    }

    @Override
    public void getListaTrabajo(BeanGestionPlanTrabajo beanGestionPlanTrabajo) throws Exception {
        beanGestionPlanTrabajo.setListPlanTrabajos(new ArrayList<>());
        List<CivPlanTrabajos> listCivPlanTrabajos = getPlanTrabajoDAO().findAll();
        for (CivPlanTrabajos civPlanTrabajo : listCivPlanTrabajos) {
            PlanTrabajos planTrabajos = new PlanTrabajos(civPlanTrabajo, civPlanTrabajo.getCivEstadoPlanTrabajos());
            beanGestionPlanTrabajo.getListPlanTrabajos().add(planTrabajos);
        }

    }

    @Override
    public void getListEtapaGenerales(BeanGestionPlanTrabajo beanGestionPlanTrabajo) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
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
        session.close();
    }

    @Override
    public void getListEtapaTrabajo(BeanGestionPlanTrabajo beanGestionPlanTrabajo) throws Exception {
        beanGestionPlanTrabajo.setListEtapaTrabajos(new ArrayList<>());
        List<CivEtapasTrabajos> listCivEtapasTrabajos = getEtapasTrabajoDAO().listarEtapasTrabajoByPlantrabajo(beanGestionPlanTrabajo.getPlanTrabajos().getId());
        for (CivEtapasTrabajos civEtapasTrabajo : listCivEtapasTrabajos) {
            EtapasTrabajos etapasTrabajos = new EtapasTrabajos(civEtapasTrabajo, civEtapasTrabajo.getCivEstadoEtapaTrabajos());
            beanGestionPlanTrabajo.getListEtapaTrabajos().add(etapasTrabajos);

        }

    }

    @Override
    public void guardarPlanTrabajo(BeanGestionPlanTrabajo beanGestionPlanTrabajo) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
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
                getPlanTrabajoDAO().create(civPlanTrabajos);
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
                        getEtapasTrabajoDAO().create(civEtapasTrabajos);
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
                            getReporteTrabajosDAO().create(civReporteTrabajos);
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
                                getFasesTrabajoDAO().create(civFasesTrabajos);
                            }
                        }

                    }
                }

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Plan de trabajo creado exitosamente", "Plan de trabajo exitosamente"));
                RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("$('#planTrabajo').modal('hide')");
            }
        }
        beanGestionPlanTrabajo.init();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("$('#planTrabajo').modal('hide')");
        session.close();
    }

    @Override
    public void guardarEtapaTrabajo(BeanGestionPlanTrabajo beanGestionPlanTrabajo) throws Exception {
        for (int i = 0; i < beanGestionPlanTrabajo.getListEtapaGenerales().size(); i++) {
            if (beanGestionPlanTrabajo.getListEtapaGenerales().get(i).isSeleccionado() == true) {
                CivEtapasTrabajos civEtapasTrabajos = new CivEtapasTrabajos();
                civEtapasTrabajos.setEtatraId(new BigDecimal(beanGestionPlanTrabajo.getListEtapaGenerales().get(i).getId()));
                civEtapasTrabajos.setEtatraDescricion(beanGestionPlanTrabajo.getListEtapaGenerales().get(i).getDescripcion());
                civEtapasTrabajos.setEtatraFechaproceso(new java.util.Date());
                civEtapasTrabajos.setEtatraObligatorio(beanGestionPlanTrabajo.getListEtapaGenerales().get(i).getObligatorio());
                civEtapasTrabajos.setEtatraPrioridad(new BigDecimal(beanGestionPlanTrabajo.getListEtapaGenerales().get(i).getPrioridad()));
                CivEstadoEtapaTrabajos civEstadoEtapaTrabajos = getEstadoEtapasTrabajoDAO().find(BigDecimal.ONE);
                civEtapasTrabajos.setCivEstadoEtapaTrabajos(civEstadoEtapaTrabajos);
                CivPlanTrabajos civPlanTrabajos = getPlanTrabajoDAO().getPlanTrabajo(beanGestionPlanTrabajo.getPlanTrabajos().getId());
                civEtapasTrabajos.setCivPlanTrabajos(civPlanTrabajos);
                getEtapasTrabajoDAO().create(civEtapasTrabajos);
            }
        }
        beanGestionPlanTrabajo.listaEtapaTrabajo(beanGestionPlanTrabajo.getPlanTrabajos());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Etapa De trabajo creada exitosamente", "Etapa De trabajo exitosamente"));
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("$('#EtapasTrabajo').modal('hide')");

    }

    @Override
    public void getFases(BeanGestionPlanTrabajo beanGestionPlanTrabajo) throws Exception {
        beanGestionPlanTrabajo.setListFasesTrabajoses(new ArrayList<>());
        List<CivFasesTrabajos> listFasesTrabajose = getFasesTrabajoDAO().listarFasesTrabajo(beanGestionPlanTrabajo.getEtapasTrabajos().getId());
        if (listFasesTrabajose != null) {
            for (CivFasesTrabajos civFasesTrabajos : listFasesTrabajose) {
                FasesTrabajos fasesTrabajos = new FasesTrabajos(civFasesTrabajos, civFasesTrabajos.getCivEstadoFasesTrabajos(), civFasesTrabajos.getCivReporteTrabajos());
                beanGestionPlanTrabajo.getListFasesTrabajoses().add(fasesTrabajos);

            }
        }
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
