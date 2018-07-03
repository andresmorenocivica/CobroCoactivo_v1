/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanGestionMovimientos;
import CobroCoactivo.Dao.DaoDeudas;
import CobroCoactivo.Dao.DaoEtapasTrabajo;
import CobroCoactivo.Dao.DaoFasesTrabajo;
import CobroCoactivo.Dao.DaoMovimiento;
import CobroCoactivo.Dao.DaoPersonas;
import CobroCoactivo.Dao.DaoPlanTrabajo;
import CobroCoactivo.Dao.ITDeudas;
import CobroCoactivo.Dao.ITEtapasTrabajo;
import CobroCoactivo.Dao.ITFasesTrabajo;
import CobroCoactivo.Dao.ITMovimiento;
import CobroCoactivo.Dao.ITPersonas;
import CobroCoactivo.Dao.ITPlanTrabajo;
import CobroCoactivo.Modelo.Deudas;
import CobroCoactivo.Modelo.EtapasTrabajos;
import CobroCoactivo.Modelo.FasesTrabajos;
import CobroCoactivo.Modelo.PlanTrabajos;
import CobroCoactivo.Persistencia.CivDeudas;
import CobroCoactivo.Persistencia.CivEtapasTrabajos;
import CobroCoactivo.Persistencia.CivFasesTrabajos;
import CobroCoactivo.Persistencia.CivMovimientos;
import CobroCoactivo.Persistencia.CivPersonas;
import CobroCoactivo.Persistencia.CivPlanTrabajos;
import CobroCoactivo.Utility.DateUtility;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author AMORENO
 */
public class GestionMovimientosImpBO implements GestionMovimientosBO, Serializable {

    private ITPlanTrabajo planTrabajoDAO;
    private ITEtapasTrabajo etapasTrabajoDAO;
    private ITDeudas deudasDAO;
    private ITPersonas personaDAO;
    private ITFasesTrabajo fasesTrabajoDAO;
    private ITMovimiento movimientoDAO;

    public GestionMovimientosImpBO() {
        planTrabajoDAO = new DaoPlanTrabajo();
        deudasDAO = new DaoDeudas();
        personaDAO = new DaoPersonas();
        etapasTrabajoDAO = new DaoEtapasTrabajo();
        fasesTrabajoDAO = new DaoFasesTrabajo();
        movimientoDAO = new DaoMovimiento();
    }

    @Override
    public void cargarListadoPlanesTrabajo(BeanGestionMovimientos beanGestionMovimientos) throws Exception {
        List<Deudas> listDeudas = beanGestionMovimientos.getListaDeudas();
        List<CivPlanTrabajos> listaCivPlanTrabajo = getPlanTrabajoDAO().getAllPlanTrabajo();
        for (CivPlanTrabajos civPlanTrabajos : listaCivPlanTrabajo) {
            PlanTrabajos planTrabajos = new PlanTrabajos(civPlanTrabajos, civPlanTrabajos.getCivEstadoPlanTrabajos());
            List<CivEtapasTrabajos> listCivEtapasTrabajos = getEtapasTrabajoDAO().listarEtapasTrabajoByPlantrabajo(planTrabajos.getId());
            if (listCivEtapasTrabajos != null) {
                for (CivEtapasTrabajos civEtapasTrabajo : listCivEtapasTrabajos) {
                    EtapasTrabajos etapasTrabajos = new EtapasTrabajos(civEtapasTrabajo, civEtapasTrabajo.getCivEstadoEtapaTrabajos());
                    planTrabajos.getListaEtapasTrabajo().add(etapasTrabajos);
                }
            }
            List<Deudas> listaDeudarRemovidas = new ArrayList<>();
            if (listDeudas != null) {
                for (Deudas deudas : listDeudas) {
                    if (deudas.getPlanTrabajoDeuda().getId() == civPlanTrabajos.getPlatraId().intValue()) {
                        planTrabajos.getListaDeudas().add(deudas);
                        listaDeudarRemovidas.add(deudas);
                    }
                }
                for (Deudas deudarRemovida : listaDeudarRemovidas) {
                    listDeudas.remove(deudarRemovida);
                }
            }
            beanGestionMovimientos.getListaPlanTrabajo().add(planTrabajos);
        }
    }

    @Override
    public void cargarListadoDeudas(BeanGestionMovimientos beanGestionMovimientos) throws Exception {
        List<CivDeudas> listaCivDeudas = new ArrayList<>();
        listaCivDeudas = getDeudasDAO().findAll();
        if (listaCivDeudas != null) {
            for (CivDeudas civDeuda : listaCivDeudas) {

                CivPersonas civPersonas = getPersonaDAO().consultarPersonasById(civDeuda.getCivPersonas().getPerId().intValue());
                CivPlanTrabajos civPlanTrabajos = getPlanTrabajoDAO().getPlanTrabajo(civDeuda.getCivPlanTrabajos().getPlatraId().intValue());
                Deudas deudas = new Deudas(civDeuda, civDeuda.getCivEstadoDeudas(), civPlanTrabajos, civDeuda.getCivTipoDeudas(), civPersonas);
                int diasDeuda = DateUtility.fechasDiferenciaEnDias(deudas.getFechaproceso(), new Date());
                deudas.setDiasHabilesDeuda(diasDeuda);
                beanGestionMovimientos.getListaDeudas().add(deudas);
            }

        }
    }

    @Override
    public void cargarEtapasPlanTrabajo(BeanGestionMovimientos beanGestionMovimientos) throws Exception {
        List<Deudas> listaDeudarRemovidas = new ArrayList<>();
        List<Deudas> listDeudas = beanGestionMovimientos.getPlanTrabajoSeleccionado().getListaDeudas();
        List listDeudasEtapas = new ArrayList<>();
        List<CivEtapasTrabajos> listCivEtapasTrabajos = getEtapasTrabajoDAO().listarEtapasTrabajoByPlantrabajo(beanGestionMovimientos.getPlanTrabajoSeleccionado().getId());
        if (listCivEtapasTrabajos != null) {
            for (CivEtapasTrabajos civEtapasTrabajo : listCivEtapasTrabajos) {
                EtapasTrabajos etapasTrabajos = new EtapasTrabajos(civEtapasTrabajo, civEtapasTrabajo.getCivEstadoEtapaTrabajos());
                List<CivFasesTrabajos> listcivCivFasesTrabajos = getFasesTrabajoDAO().listarFasesTrabajo(etapasTrabajos.getId());
                if (listcivCivFasesTrabajos != null) {
                    for (CivFasesTrabajos civCivFasesTrabajo : listcivCivFasesTrabajos) {
                        FasesTrabajos fasesTrabajos = new FasesTrabajos(civCivFasesTrabajo, civCivFasesTrabajo.getCivEstadoFasesTrabajos());
                        if (listDeudas != null) {
                            listaDeudarRemovidas = new ArrayList<>();
                            for (Deudas deuda : listDeudas) {
                                CivMovimientos civMovimientos = getMovimientoDAO().getMovimientoByDeudaByFaseTrabajo(deuda.getId(), fasesTrabajos.getId());
                                if (civMovimientos == null) {
                                    etapasTrabajos.getListDeudas().add(deuda);
                                    listaDeudarRemovidas.add(deuda);
                                }
                            }
                            for (Deudas deudarRemovida : listaDeudarRemovidas) {
                                listDeudas.remove(deudarRemovida);
                                listDeudasEtapas.add(deudarRemovida);
                            }
                        }
                        etapasTrabajos.getListaFasesTrabajo().add(fasesTrabajos);
                    }
                } else {
                    if (listDeudas != null) {
                        listaDeudarRemovidas = new ArrayList<>();
                        for (Deudas deuda : listDeudas) {
                            etapasTrabajos.getListDeudas().add(deuda);
                            listaDeudarRemovidas.add(deuda);
                        }
                        for (Deudas deudarRemovida : listaDeudarRemovidas) {
                            listDeudas.remove(deudarRemovida);
                            listDeudasEtapas.add(deudarRemovida);
                        }
                    }
                }
                beanGestionMovimientos.getPlanTrabajoSeleccionado().setListaDeudas(listDeudasEtapas);
                beanGestionMovimientos.getPlanTrabajoSeleccionado().getListaEtapasTrabajo().add(etapasTrabajos);
            }
        }
    }

    @Override
    public void cargarFasesTrabajo(BeanGestionMovimientos beanGestionMovimientos) throws Exception {
        List<Deudas> listDeudas = beanGestionMovimientos.getEtapaTrabajoSeleccionada().getListDeudas();
        List listDeudasFases = new ArrayList<>();
        List<CivFasesTrabajos> listaCivFasesTrabajoses = getFasesTrabajoDAO().listarFasesTrabajo(beanGestionMovimientos.getEtapaTrabajoSeleccionada().getId());
        if (listaCivFasesTrabajoses != null && listaCivFasesTrabajoses.size() > 0) {
            for (CivFasesTrabajos civFasesTrabajose : listaCivFasesTrabajoses) {
                FasesTrabajos fasesTrabajos = new FasesTrabajos(civFasesTrabajose, civFasesTrabajose.getCivEstadoFasesTrabajos());
                if (listDeudas != null) {
                    List<Deudas> listaDeudarRemovidas = new ArrayList<>();
                    for (Deudas deuda : listDeudas) {
                        CivMovimientos civMovimientos = getMovimientoDAO().getMovimientoByDeudaByFaseTrabajo(deuda.getId(), fasesTrabajos.getId());
                        if (civMovimientos == null) {
                            if (deuda.getDiasHabilesDeuda() >= fasesTrabajos.getDianim() && deuda.getDiasHabilesDeuda() > fasesTrabajos.getDiamax()) {
                                fasesTrabajos.getListaDeudas().add(deuda);
                                listaDeudarRemovidas.add(deuda);
                            } else if (deuda.getDiasHabilesDeuda() >= fasesTrabajos.getDianim() && deuda.getDiasHabilesDeuda() <= fasesTrabajos.getDiamax()) {
                                fasesTrabajos.getListaDeudas().add(deuda);
                                listaDeudarRemovidas.add(deuda);
                            }
                        }
                    }
                    for (Deudas deudarRemovida : listaDeudarRemovidas) {
                        listDeudas.remove(deudarRemovida);
                        listDeudasFases.add(deudarRemovida);
                    }
                }
                beanGestionMovimientos.getEtapaTrabajoSeleccionada().setListDeudas(listDeudasFases);
                beanGestionMovimientos.getEtapaTrabajoSeleccionada().getListaFasesTrabajo().add(fasesTrabajos);
            }
        }

    }

    @Override
    public void filtarListaDeudaTabla(BeanGestionMovimientos beanGestionMovimientos) throws Exception {
//        List<Deudas> listaDeudas = new ArrayList<>();
//        for(Deudas deuda : beanGestionMovimientos.getListaDeudas()){
//            if(beanGestionMovimientos.getPlanTrabajoSeleccionado().getId() == deuda.getPlanTrabajoDeuda().getId()){
//                listaDeudas.add(deuda);
//            }
//        }
//        beanGestionMovimientos.setListaDeudasTabla(new ArrayList<>());
//        beanGestionMovimientos.setListaDeudasTabla(listaDeudas);
//        

    }

    /**
     * @return the planTrabajoDAO
     */
    public ITPlanTrabajo getPlanTrabajoDAO() {
        return planTrabajoDAO;
    }

    /**
     * @param planTrabajoDAO the planTrabajoDAO to set
     */
    public void setPlanTrabajoDAO(ITPlanTrabajo planTrabajoDAO) {
        this.planTrabajoDAO = planTrabajoDAO;
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
     * @return the etapasTrabajoDAO
     */
    public ITEtapasTrabajo getEtapasTrabajoDAO() {
        return etapasTrabajoDAO;
    }

    /**
     * @param etapasTrabajoDAO the etapasTrabajoDAO to set
     */
    public void setEtapasTrabajoDAO(ITEtapasTrabajo etapasTrabajoDAO) {
        this.etapasTrabajoDAO = etapasTrabajoDAO;
    }

    /**
     * @return the fasesTrabajoDAO
     */
    public ITFasesTrabajo getFasesTrabajoDAO() {
        return fasesTrabajoDAO;
    }

    /**
     * @param fasesTrabajoDAO the fasesTrabajoDAO to set
     */
    public void setFasesTrabajoDAO(ITFasesTrabajo fasesTrabajoDAO) {
        this.fasesTrabajoDAO = fasesTrabajoDAO;
    }

    /**
     * @return the movimientoDAO
     */
    public ITMovimiento getMovimientoDAO() {
        return movimientoDAO;
    }

    /**
     * @param movimientoDAO the movimientoDAO to set
     */
    public void setMovimientoDAO(ITMovimiento movimientoDAO) {
        this.movimientoDAO = movimientoDAO;
    }

}
