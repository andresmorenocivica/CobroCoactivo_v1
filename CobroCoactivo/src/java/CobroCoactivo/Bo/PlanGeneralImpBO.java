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
import CobroCoactivo.Modelo.EstadoAbogados;
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
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class PlanGeneralImpBO implements PlanGeneralBO {

    private ITPlanGeneral iTPlanGeneral;
    private ITEstadoPlanGeneral iTEstado;
    private ITEstapaGeneral itEstapaGeneral;
    private ITEstadoEtapageneral estadoEtapageneral;
    private ITFasesGenerales iTFasesGenerales;
    private ITEstadoFasesGenerales iTEstadoFasesGenerales;
    private ITDocumentoGenerales iTDocumentoGenerales;

    public PlanGeneralImpBO() {
        iTPlanGeneral = new DaoPlanGeneral();
        iTEstado = new DaoEstadoPlanGeneral();
        itEstapaGeneral = new DaoEstapaGeneral();
        estadoEtapageneral = new DaoEstadoEtapaGeneral();
        iTFasesGenerales = new DaoFasesGenerales();
        iTEstadoFasesGenerales = new DaoEstadoFasesGenerales();
        iTDocumentoGenerales = new DaoDocumentosGenerales();
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
        civEtapasGenerales.setEtagenDescripcion(bean.getEtapasGenerales().getEtagenDescripcion());
        civEtapasGenerales.setEtagenFechaproceso(new Date());
        civEtapasGenerales.setEtagenObligatorio(bean.getObligatorio());
        civEtapasGenerales.setPrioridad(bean.getEtapasGenerales().getPrioridad());
        getItEstapaGeneral().create(civEtapasGenerales);
        bean.init();
        bean.ListarEtadoGeneralesPorIdPlanGeneral(bean.getPlanGenerales());
    }

    @Override
    public void listarEtadoGeneralesPorIdPlanGeneral(BeanPlanGeneral bean) throws Exception {
        List<CivEtapasGenerales> listCivEtapasGenerales = getItEstapaGeneral().findAllEtapaByIdPlanGeneral(bean.getPlanGenerales().getId());
        if (listCivEtapasGenerales != null) {
            bean.setListadoEtapasGeneraleses(new ArrayList<>());
            for (CivEtapasGenerales listCivEtapasGenerale : listCivEtapasGenerales) {
                EtapasGenerales etapasGenerales = new EtapasGenerales();
                PlanGenerales planGenerales = new PlanGenerales(listCivEtapasGenerale.getCivPlanGenerales());
                EstadoEtapasGenerales estadoEtapasGenerales = new EstadoEtapasGenerales(listCivEtapasGenerale.getCivEstadoEtapasGenerales());
                etapasGenerales.setEtagenId(listCivEtapasGenerale.getEtagenId());
                etapasGenerales.setEtagenDescripcion(listCivEtapasGenerale.getEtagenDescripcion());
                etapasGenerales.setEstadoEtapasGenerales(estadoEtapasGenerales);
                etapasGenerales.setEtagenFechaproceso(listCivEtapasGenerale.getEtagenFechaproceso());
                etapasGenerales.setEtagenObligatorio(listCivEtapasGenerale.getEtagenObligatorio());
                etapasGenerales.setPlanGenerales(planGenerales);
                etapasGenerales.setPrioridad(listCivEtapasGenerale.getPrioridad());
                if (etapasGenerales.getEstadoEtapasGenerales().getId() == 1) {
                    bean.getListadoEtapasGeneraleses().add(etapasGenerales);
                }
            }

        } else {
            bean.setListadoEtapasGeneraleses(new ArrayList<>());
        }
    }

    @Override
    public void ActualizarPlanGeneral(BeanPlanGeneral bean) throws Exception {
        boolean flag = false;
        CivPlanGenerales civPlanGenerales = new CivPlanGenerales();
        CivEstadoPlanGenerales civEstadoPlanGenerales = new CivEstadoPlanGenerales();
        civEstadoPlanGenerales.setEstplagenId(new BigDecimal(bean.getIdEstadoGeneral()));
        civPlanGenerales.setPlagenId(new BigDecimal(bean.getPlanGenerales().getId()));
        civPlanGenerales.setPlagenDescripcion(bean.getPlanGenerales().getDescripcion());
        civPlanGenerales.setPlagenFechaproceso(new Date());
        civPlanGenerales.setCivEstadoPlanGenerales(civEstadoPlanGenerales);
        if (bean.getIdEstadoGeneral() != 1) {
            List<CivEtapasGenerales> listadoPlangeneral = getItEstapaGeneral().findAllEtapaByIdPlanGeneral(civPlanGenerales.getPlagenId().intValue());
            if (listadoPlangeneral != null && listadoPlangeneral.size() > 0) {
                for (CivEtapasGenerales civEtapasGenerales : listadoPlangeneral) {
                    if (civEtapasGenerales.getCivEstadoEtapasGenerales().getEstetagenId().intValue() == 1) {
                        flag = true;
                    }
                }
                if (flag) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "debe desactivar las estapa generales", null));

                } else {
                    getiTPlanGeneral().update(civPlanGenerales);
                    bean.init();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Plan Actualizado Correctamnete", null));
                }
            } else {
                getiTPlanGeneral().update(civPlanGenerales);
                bean.init();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Plan Actualizado Correctamnete", null));
            }

        } else {
            getiTPlanGeneral().update(civPlanGenerales);
            bean.init();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Plan Actualizado Correctamente", null));
        }
    }

    @Override
    public void ActualizarEtapaGeneral(BeanPlanGeneral bean) throws Exception {
        boolean validador = false;
        CivEtapasGenerales civEtapasGenerales = new CivEtapasGenerales();
        CivEstadoEtapasGenerales civEstadoEtapasGenerales = new CivEstadoEtapasGenerales();
        civEstadoEtapasGenerales.setEstetagenId(new BigDecimal(bean.getIdEstapaGeneral()));
        CivPlanGenerales civPlanGenerales = new CivPlanGenerales();
        civPlanGenerales.setPlagenId(new BigDecimal(bean.getPlanGenerales().getId()));

        civEtapasGenerales.setEtagenId(bean.getEtapasGenerales().getEtagenId());
        civEtapasGenerales.setCivEstadoEtapasGenerales(civEstadoEtapasGenerales);
        civEtapasGenerales.setCivPlanGenerales(civPlanGenerales);
        civEtapasGenerales.setEtagenDescripcion(bean.getEtapasGenerales().getEtagenDescripcion());
        civEtapasGenerales.setEtagenObligatorio(bean.getObligatorio());
         civEtapasGenerales.setPrioridad(bean.getEtapasGenerales().getPrioridad());
        civEtapasGenerales.setEtagenFechaproceso(new Date());

        if (bean.getIdEstapaGeneral() != 1) {
            List<CivFasesGenerales> listCivFasesGenerales = getiTFasesGenerales().AllListByEtapaGeneral(bean.getEtapasGenerales().getEtagenId().intValue());
            if (listCivFasesGenerales != null && listCivFasesGenerales.size() > 0) {
                for (CivFasesGenerales civFasesGenerales : listCivFasesGenerales) {
                    if (civFasesGenerales.getCivEstadoFasesGenerales().getEstfasgenId().intValue() == 1) {
                        validador = true;
                    }
                }

                if (validador) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "no se puede desactivar la etapa por que tiene fases activas", null));
                } else {
                    getItEstapaGeneral().update(civEtapasGenerales);
                    bean.init();
                    bean.ListarEtadoGeneralesPorIdPlanGeneral(bean.getPlanGenerales());
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "etapa Actualizado Correctamente", null));
                }

            } else {
                getItEstapaGeneral().update(civEtapasGenerales);
                bean.init();
                bean.ListarEtadoGeneralesPorIdPlanGeneral(bean.getPlanGenerales());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "etapa Actualizado Correctamente", null));
            }

        } else {
            getItEstapaGeneral().update(civEtapasGenerales);
            bean.init();
            bean.ListarEtadoGeneralesPorIdPlanGeneral(bean.getPlanGenerales());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "etapa Actualizado Correctamente", null));
        }
    }

    @Override
    public void ListarPlanesGenerales(BeanPlanGeneral bean) throws Exception {
        bean.setListadoPlanGeneraleses(new ArrayList<>());
        List<CivPlanGenerales> listcivPlanGeneral = getiTPlanGeneral().findAll();
        if (listcivPlanGeneral != null && listcivPlanGeneral.size() > 0) {
            for (CivPlanGenerales civPlanGenerales : listcivPlanGeneral) {
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
        civPlanGenerales.setPlagenDescripcion(bean.getPlanGenerales().getDescripcion());
        civPlanGenerales.setCivEstadoPlanGenerales(civEstadoPlanGenerales);
        civPlanGenerales.setPlagenFechaproceso(new Date());
        getiTPlanGeneral().create(civPlanGenerales);
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
        CivFasesGenerales civFasesGenerales = new CivFasesGenerales();
        CivEstadoFasesGenerales civEstadoFasesGenerales = new CivEstadoFasesGenerales();
        civEstadoFasesGenerales.setEstfasgenId(BigDecimal.valueOf(bean.getIdEStadoFasesGeneral()));
        CivEtapasGenerales civEtapasGenerales = new CivEtapasGenerales();
        civEtapasGenerales.setEtagenId(bean.getEtapasGenerales().getEtagenId());

        CivDocumenGenerales civDocumenGenerales = new CivDocumenGenerales();
        CivEstadoDocumengenerales civEstadoDocumengenerales = new CivEstadoDocumengenerales();
        civEstadoDocumengenerales.setEstdocgenId(BigDecimal.valueOf(1));
        civDocumenGenerales.setDocgenDescripcion(Paths.get(bean.getFile().getSubmittedFileName()).getFileName().toString());
        civDocumenGenerales.setCivEstadoDocumengenerales(civEstadoDocumengenerales);
        civDocumenGenerales.setDocgenFechaproceso(new Date());
        civDocumenGenerales.setDocgenArchivo("D:\\Archivo\\" + Paths.get(bean.getFile().getSubmittedFileName()).getFileName().toString());
        getiTDocumentoGenerales().create(civDocumenGenerales);
        civFasesGenerales.setFasgenDescripcion(bean.getFasesGenerales().getFasgenDescripcion());
        civFasesGenerales.setCivEstadoFasesGenerales(civEstadoFasesGenerales);
        civFasesGenerales.setFasgenFechaproceso(new Date());
        civFasesGenerales.setCivEtapasGenerales(civEtapasGenerales);
        civFasesGenerales.setFasgenDianim(bean.getFasesGenerales().getFasgenDianim());
        civFasesGenerales.setFasgenDiamax(bean.getFasesGenerales().getFasgenDiamax());
        civFasesGenerales.setFasgenObligatorio(bean.getFasesGenerales().getFasgenObligatorio());
        civFasesGenerales.setCivDocumenGenerales(civDocumenGenerales);
        getiTFasesGenerales().create(civFasesGenerales);
        InputStream stream = bean.getFile().getInputStream();
        Files.copy(stream, new File("D:\\Archivo\\" + Paths.get(bean.getFile().getSubmittedFileName()).getFileName().toString()).toPath());

    }

    @Override
    public void listarFasesGeneralesPorEtapa(BeanPlanGeneral bean) throws Exception {
        bean.setListFasesGenerales(new ArrayList<>());
           List<CivFasesGenerales> listCivFasesGenerales = getiTFasesGenerales().AllListByEtapaGeneral(bean.getEtapasGenerales().getEtagenId().intValue());
           for (CivFasesGenerales civFasesGenerale : listCivFasesGenerales) {
               DocumenGenerales documenGenerales = new DocumenGenerales();
               documenGenerales.setDocgenId(civFasesGenerale.getCivDocumenGenerales().getDocgenId());
               documenGenerales.setDocgenDescripcion(civFasesGenerale.getCivDocumenGenerales().getDocgenDescripcion());
               documenGenerales.setDocgenFechaproceso(civFasesGenerale.getCivDocumenGenerales().getDocgenFechaproceso());
               documenGenerales.setEstadoDocumengenerales(new EstadoDocumengenerales(civFasesGenerale.getCivDocumenGenerales().getCivEstadoDocumengenerales()));
               documenGenerales.setDocgenArchivo(civFasesGenerale.getCivDocumenGenerales().getDocgenArchivo());
               FasesGenerales fasesGenerales = new FasesGenerales();
               fasesGenerales.setFasgenId(civFasesGenerale.getFasgenId());
               fasesGenerales.setFasgenDescripcion(civFasesGenerale.getFasgenDescripcion());
               fasesGenerales.setEstadoFasesGenerales(new EstadoFasesGenerales(civFasesGenerale.getCivEstadoFasesGenerales()));
               fasesGenerales.setFasgenFechaproceso(civFasesGenerale.getFasgenFechaproceso());
               EtapasGenerales estaGenerales = new EtapasGenerales();
               estaGenerales.setEtagenId(civFasesGenerale.getCivEtapasGenerales().getEtagenId());
               estaGenerales.setEtagenDescripcion(civFasesGenerale.getCivEtapasGenerales().getEtagenDescripcion());
               fasesGenerales.setEtapasGenerales(estaGenerales);
               fasesGenerales.setFasgenDianim(civFasesGenerale.getFasgenDianim());
               fasesGenerales.setFasgenDiamax(civFasesGenerale.getFasgenDiamax());
               fasesGenerales.setFasgenObligatorio(civFasesGenerale.getFasgenObligatorio());
               fasesGenerales.setDocumenGenerales(documenGenerales);
               bean.getListFasesGenerales().add(fasesGenerales);
                       
            
        }
           
           
    }

    /**
     * @return the iTPlanGeneral
     */
    public ITPlanGeneral getiTPlanGeneral() {
        return iTPlanGeneral;
    }

    /**
     * @param iTPlanGeneral the iTPlanGeneral to set
     */
    public void setiTPlanGeneral(ITPlanGeneral iTPlanGeneral) {
        this.iTPlanGeneral = iTPlanGeneral;
    }

    /**
     * @return the iTEstado
     */
    public ITEstadoPlanGeneral getiTEstado() {
        return iTEstado;
    }

    /**
     * @param iTEstado the iTEstado to set
     */
    public void setiTEstado(ITEstadoPlanGeneral iTEstado) {
        this.iTEstado = iTEstado;
    }

    /**
     * @return the itEstapaGeneral
     */
    public ITEstapaGeneral getItEstapaGeneral() {
        return itEstapaGeneral;
    }

    /**
     * @param itEstapaGeneral the itEstapaGeneral to set
     */
    public void setItEstapaGeneral(ITEstapaGeneral itEstapaGeneral) {
        this.itEstapaGeneral = itEstapaGeneral;
    }

    /**
     * @return the estadoEtapageneral
     */
    public ITEstadoEtapageneral getEstadoEtapageneral() {
        return estadoEtapageneral;
    }

    /**
     * @param estadoEtapageneral the estadoEtapageneral to set
     */
    public void setEstadoEtapageneral(ITEstadoEtapageneral estadoEtapageneral) {
        this.estadoEtapageneral = estadoEtapageneral;
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
     * @return the iTEstadoFasesGenerales
     */
    public ITEstadoFasesGenerales getiTEstadoFasesGenerales() {
        return iTEstadoFasesGenerales;
    }

    /**
     * @param iTEstadoFasesGenerales the iTEstadoFasesGenerales to set
     */
    public void setiTEstadoFasesGenerales(ITEstadoFasesGenerales iTEstadoFasesGenerales) {
        this.iTEstadoFasesGenerales = iTEstadoFasesGenerales;
    }

    /**
     * @return the iTDocumentoGenerales
     */
    public ITDocumentoGenerales getiTDocumentoGenerales() {
        return iTDocumentoGenerales;
    }

    /**
     * @param iTDocumentoGenerales the iTDocumentoGenerales to set
     */
    public void setiTDocumentoGenerales(ITDocumentoGenerales iTDocumentoGenerales) {
        this.iTDocumentoGenerales = iTDocumentoGenerales;
    }

}
