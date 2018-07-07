/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanGestionMovimientos;
import CobroCoactivo.Dao.DaoDeudas;
import CobroCoactivo.Dao.DaoEstadoMovimientos;
import CobroCoactivo.Dao.DaoEtapasTrabajo;
import CobroCoactivo.Dao.DaoFasesTrabajo;
import CobroCoactivo.Dao.DaoMovimientos;
import CobroCoactivo.Dao.DaoPersonas;
import CobroCoactivo.Dao.DaoPlanTrabajo;
import CobroCoactivo.Dao.DaoUsuarios;
import CobroCoactivo.Dao.ITDeudas;
import CobroCoactivo.Dao.ITEstadoMovimientos;
import CobroCoactivo.Dao.ITEtapasTrabajo;
import CobroCoactivo.Dao.ITFasesTrabajo;
import CobroCoactivo.Dao.ITMovimientos;
import CobroCoactivo.Dao.ITPersonas;
import CobroCoactivo.Dao.ITPlanTrabajo;
import CobroCoactivo.Dao.ITUsuarios;
import CobroCoactivo.Modelo.Deudas;
import CobroCoactivo.Modelo.EtapasTrabajos;
import CobroCoactivo.Modelo.FasesTrabajos;
import CobroCoactivo.Modelo.PlanTrabajos;
import CobroCoactivo.Persistencia.CivDeudas;
import CobroCoactivo.Persistencia.CivEstadoMovimientos;
import CobroCoactivo.Persistencia.CivEtapasTrabajos;
import CobroCoactivo.Persistencia.CivFasesTrabajos;
import CobroCoactivo.Persistencia.CivMovimientos;
import CobroCoactivo.Persistencia.CivPersonas;
import CobroCoactivo.Persistencia.CivPlanTrabajos;
import CobroCoactivo.Persistencia.CivUsuarios;
import CobroCoactivo.Utility.DateUtility;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

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
    private ITMovimientos movimientoDAO;
    private ITEstadoMovimientos estadoMovimientosDAO;
    private ITUsuarios usuarioDAO;

    public GestionMovimientosImpBO() {
        planTrabajoDAO = new DaoPlanTrabajo();
        deudasDAO = new DaoDeudas();
        personaDAO = new DaoPersonas();
        etapasTrabajoDAO = new DaoEtapasTrabajo();
        fasesTrabajoDAO = new DaoFasesTrabajo();
        movimientoDAO = new DaoMovimientos();
        estadoMovimientosDAO = new DaoEstadoMovimientos();
        usuarioDAO = new DaoUsuarios();
    }

    @Override
    public void cargarListadoPlanesTrabajo(BeanGestionMovimientos beanGestionMovimientos) throws Exception {
        List<Deudas> listDeudas = beanGestionMovimientos.getListaDeudas();
        List<CivPlanTrabajos> listaCivPlanTrabajo = getPlanTrabajoDAO().getAllPlanTrabajo();
        for (CivPlanTrabajos civPlanTrabajos : listaCivPlanTrabajo) {
            PlanTrabajos planTrabajos = new PlanTrabajos(civPlanTrabajos, civPlanTrabajos.getCivEstadoPlanTrabajos());
            List<CivEtapasTrabajos> listCivEtapasTrabajos = getEtapasTrabajoDAO().listarEtapasTrabajoByPlantrabajo(planTrabajos.getId());
            if (listCivEtapasTrabajos != null) {
                if (listCivEtapasTrabajos != null) {
                    for (CivEtapasTrabajos civEtapasTrabajo : listCivEtapasTrabajos) {
                        EtapasTrabajos etapasTrabajos = new EtapasTrabajos(civEtapasTrabajo, civEtapasTrabajo.getCivEstadoEtapaTrabajos());
                        planTrabajos.getListaEtapasTrabajo().add(etapasTrabajos);
                    }
                }
            }
            List<Deudas> listaDeudarRemovidas = new ArrayList<>();
            if (listCivEtapasTrabajos != null) {
                for (CivEtapasTrabajos civEtapasTrabajo : listCivEtapasTrabajos) {
                    List<CivFasesTrabajos> listcivCivFasesTrabajos = getFasesTrabajoDAO().listarFasesTrabajo(civEtapasTrabajo.getEtatraId().intValue());
                    if (listcivCivFasesTrabajos != null) {
                        for (CivFasesTrabajos civCivFasesTrabajo : listcivCivFasesTrabajos) {
                            if (listDeudas != null) {
                                for (Deudas deudas : listDeudas) {
                                    CivMovimientos civMovimientos = getMovimientoDAO().getMovimientoByDeudaByFaseTrabajo(deudas.getId(), civCivFasesTrabajo.getFastraId().intValue());
                                    if (civMovimientos == null) {
                                        if (deudas.getPlanTrabajoDeuda().getId() == civPlanTrabajos.getPlatraId().intValue()) {
                                            planTrabajos.getListaDeudas().add(deudas);
                                            listaDeudarRemovidas.add(deudas);
                                        }
                                    }
                                }
                                for (Deudas deudarRemovida : listaDeudarRemovidas) {
                                    listDeudas.remove(deudarRemovida);
                                }
                            }
                        }
                    }

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
        beanGestionMovimientos.getPlanTrabajoSeleccionado().setListaEtapasTrabajo(new ArrayList<>());
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
        beanGestionMovimientos.getEtapaTrabajoSeleccionada().setListaFasesTrabajo(new ArrayList<>());
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
    public void generarMovimiento(BeanGestionMovimientos beanGestionMovimientos) throws Exception {
        List<Deudas> listaDeudasRealizadas = new ArrayList<>();
        int i = 0;
        for (Deudas deudas : beanGestionMovimientos.getListaDeudasTabla()) {
            if (deudas.isSeleccionado()) {
                CivMovimientos civMovimientos = new CivMovimientos();
                CivDeudas civDeudas = getDeudasDAO().find(new BigDecimal(deudas.getId()));
                CivEstadoMovimientos civEstadomovimiento = getEstadoMovimientosDAO().find(BigDecimal.ONE);
                CivFasesTrabajos civFasesTrabajos = getFasesTrabajoDAO().find(new BigDecimal(beanGestionMovimientos.getFaseTrabajoSeleccionada().getId()));
                CivPersonas civPersonas = getPersonaDAO().find(new BigDecimal(deudas.getPersonas().getId()));
                CivUsuarios civUsuarios = getUsuarioDAO().find(new BigDecimal(beanGestionMovimientos.getLoginBO().getID_Usuario()));
                civMovimientos.setCivDeudas(civDeudas);
                civMovimientos.setCivEstadoMovimientos(civEstadomovimiento);
                civMovimientos.setCivFasesTrabajos(civFasesTrabajos);
                civMovimientos.setCivPersonas(civPersonas);
                civMovimientos.setCivUsuarios(civUsuarios);
                civMovimientos.setMovFechaproceso(new Date());
                getMovimientoDAO().create(civMovimientos);
                listaDeudasRealizadas.add(deudas);
                i++;
            }

        }
        for (Deudas deudasRealizada : listaDeudasRealizadas) {
            beanGestionMovimientos.getListaDeudasTabla().remove(deudasRealizada);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                i + " Movimiento realizado exitoxamente", null));

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
    public ITMovimientos getMovimientoDAO() {
        return movimientoDAO;
    }

    /**
     * @param movimientoDAO the movimientoDAO to set
     */
    public void setMovimientoDAO(ITMovimientos movimientoDAO) {
        this.movimientoDAO = movimientoDAO;
    }

    /**
     * @return the estadoMovimientosDAO
     */
    public ITEstadoMovimientos getEstadoMovimientosDAO() {
        return estadoMovimientosDAO;
    }

    /**
     * @param estadoMovimientosDAO the estadoMovimientosDAO to set
     */
    public void setEstadoMovimientosDAO(ITEstadoMovimientos estadoMovimientosDAO) {
        this.estadoMovimientosDAO = estadoMovimientosDAO;
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

}
