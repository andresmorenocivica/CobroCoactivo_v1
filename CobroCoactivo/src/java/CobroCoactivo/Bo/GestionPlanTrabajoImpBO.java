/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanGestionPlanTrabajo;
import CobroCoactivo.Dao.DaoEstapaGeneral;
import CobroCoactivo.Dao.DaoEtapasTrabajo;
import CobroCoactivo.Dao.DaoFasesGenerales;
import CobroCoactivo.Dao.DaoFasesTrabajo;
import CobroCoactivo.Dao.DaoPlanGeneral;
import CobroCoactivo.Dao.DaoPlanTrabajo;
import CobroCoactivo.Dao.DaoReporteTrabajos;
import CobroCoactivo.Dao.ITEstapaGeneral;
import CobroCoactivo.Dao.ITEtapasTrabajo;
import CobroCoactivo.Dao.ITFasesGenerales;
import CobroCoactivo.Dao.ITFasesTrabajo;
import CobroCoactivo.Dao.ITPlanGeneral;
import CobroCoactivo.Dao.ITPlanTrabajo;
import CobroCoactivo.Dao.ITReporteTrabajos;
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

    private ITPlanGeneral planGeneral;
    private ITPlanTrabajo iTPlanTrabajo;
    private ITEtapasTrabajo iTEtapasTrabajo;
    private ITEstapaGeneral iTEstapaGeneral;
    private ITFasesGenerales iTFasesGenerales;
    private ITFasesTrabajo iTFasesTrabajo;
    private ITReporteTrabajos iTReporteTrabajos;

    /**
     * contructor e inicializador Dao
     *
     */
    public GestionPlanTrabajoImpBO() {
        planGeneral = new DaoPlanGeneral();
        iTPlanTrabajo = new DaoPlanTrabajo();
        iTEtapasTrabajo = new DaoEtapasTrabajo();
        iTEstapaGeneral = new DaoEstapaGeneral();
        iTFasesGenerales = new DaoFasesGenerales();
        iTFasesTrabajo = new DaoFasesTrabajo();
        iTReporteTrabajos = new DaoReporteTrabajos();

    }

    @Override
    public void getListPlanGenaral(BeanGestionPlanTrabajo beanGestionPlanTrabajo) throws Exception {
        beanGestionPlanTrabajo.setListPlanGenerales(new ArrayList<>());
        List<CivPlanGenerales> listCivPlanGenerales = getPlanGeneral().findAll();
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
        List<CivPlanTrabajos> listCivPlanTrabajos = getiTPlanTrabajo().findAll();
        for (CivPlanTrabajos civPlanTrabajo : listCivPlanTrabajos) {
            PlanTrabajos planTrabajos = new PlanTrabajos(civPlanTrabajo, civPlanTrabajo.getCivEstadoPlanTrabajos());
            beanGestionPlanTrabajo.getListPlanTrabajos().add(planTrabajos);
        }

    }

    @Override
    public void getListEtapaTrabajo(BeanGestionPlanTrabajo beanGestionPlanTrabajo) throws Exception {
        beanGestionPlanTrabajo.setListEtapaTrabajos(new ArrayList<>());
        List<CivEtapasTrabajos> listCivEtapasTrabajos = getiTEtapasTrabajo().listarEtapasTrabajoByPlantrabajo(beanGestionPlanTrabajo.getPlanTrabajos().getId());
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
                getiTPlanTrabajo().create(civPlanTrabajos);
                List<CivEtapasGenerales> listCivEtapasGenerales = getiTEstapaGeneral().findAllEtapaByIdPlanGeneral(session, civPlanTrabajos.getPlatraId().intValue());
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
                        getiTEtapasTrabajo().create(civEtapasTrabajos);
                        List<CivFasesGenerales> listFasesGenerales = getiTFasesGenerales().AllListByEtapaGeneral(session, civEtapasTrabajos.getEtatraId().intValue());
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
                            getiTReporteTrabajos().create(civReporteTrabajos);
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
                                getiTFasesTrabajo().create(civFasesTrabajos);
                            }
                        }

                    }
                }

                FacesContext.getCurrentInstance().addMessage("planMensajeGeneral", new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Plan general creado exitosamente", "Plan de trabajo exitosamente"));
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
    public void getFases(BeanGestionPlanTrabajo beanGestionPlanTrabajo) throws Exception {
        beanGestionPlanTrabajo.setListFasesTrabajoses(new ArrayList<>());
        List<CivFasesTrabajos> listFasesTrabajose = getiTFasesTrabajo().listarFasesTrabajo(beanGestionPlanTrabajo.getEtapasTrabajos().getId());
        for (CivFasesTrabajos civFasesTrabajos : listFasesTrabajose) {
            FasesTrabajos fasesTrabajos = new FasesTrabajos(civFasesTrabajos, civFasesTrabajos.getCivEstadoFasesTrabajos(),civFasesTrabajos.getCivReporteTrabajos());
            beanGestionPlanTrabajo.getListFasesTrabajoses().add(fasesTrabajos);

        }
    }

    /**
     * @return the planGeneral
     */
    public ITPlanGeneral getPlanGeneral() {
        return planGeneral;
    }

    /**
     * @param planGeneral the planGeneral to set
     */
    public void setPlanGeneral(ITPlanGeneral planGeneral) {
        this.planGeneral = planGeneral;
    }

    /**
     * @return the iTPlanTrabajo
     */
    public ITPlanTrabajo getiTPlanTrabajo() {
        return iTPlanTrabajo;
    }

    /**
     * @param iTPlanTrabajo the iTPlanTrabajo to set
     */
    public void setiTPlanTrabajo(ITPlanTrabajo iTPlanTrabajo) {
        this.iTPlanTrabajo = iTPlanTrabajo;
    }

    /**
     * @return the iTEtapasTrabajo
     */
    public ITEtapasTrabajo getiTEtapasTrabajo() {
        return iTEtapasTrabajo;
    }

    /**
     * @param iTEtapasTrabajo the iTEtapasTrabajo to set
     */
    public void setiTEtapasTrabajo(ITEtapasTrabajo iTEtapasTrabajo) {
        this.iTEtapasTrabajo = iTEtapasTrabajo;
    }

    /**
     * @return the iTEstapaGeneral
     */
    public ITEstapaGeneral getiTEstapaGeneral() {
        return iTEstapaGeneral;
    }

    /**
     * @param iTEstapaGeneral the iTEstapaGeneral to set
     */
    public void setiTEstapaGeneral(ITEstapaGeneral iTEstapaGeneral) {
        this.iTEstapaGeneral = iTEstapaGeneral;
    }

    /**
     * @return the iTFasesGenerales
     */
    public ITFasesGenerales getiTFasesGenerales() {
        return iTFasesGenerales;
    }

    /**
     * @param iTFasesGenerales the iTFasesGenerales to set
     */
    public void setiTFasesGenerales(ITFasesGenerales iTFasesGenerales) {
        this.iTFasesGenerales = iTFasesGenerales;
    }

    /**
     * @return the iTFasesTrabajo
     */
    public ITFasesTrabajo getiTFasesTrabajo() {
        return iTFasesTrabajo;
    }

    /**
     * @param iTFasesTrabajo the iTFasesTrabajo to set
     */
    public void setiTFasesTrabajo(ITFasesTrabajo iTFasesTrabajo) {
        this.iTFasesTrabajo = iTFasesTrabajo;
    }

    /**
     * @return the iTReporteTrabajos
     */
    public ITReporteTrabajos getiTReporteTrabajos() {
        return iTReporteTrabajos;
    }

    /**
     * @param iTReporteTrabajos the iTReporteTrabajos to set
     */
    public void setiTReporteTrabajos(ITReporteTrabajos iTReporteTrabajos) {
        this.iTReporteTrabajos = iTReporteTrabajos;
    }

}
