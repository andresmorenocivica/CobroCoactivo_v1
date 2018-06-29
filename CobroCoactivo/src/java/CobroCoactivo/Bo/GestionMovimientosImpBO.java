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
import CobroCoactivo.Dao.DaoPersonas;
import CobroCoactivo.Dao.DaoPlanTrabajo;
import CobroCoactivo.Dao.ITDeudas;
import CobroCoactivo.Dao.ITEtapasTrabajo;
import CobroCoactivo.Dao.ITFasesTrabajo;
import CobroCoactivo.Dao.ITPersonas;
import CobroCoactivo.Dao.ITPlanTrabajo;
import CobroCoactivo.Modelo.Deudas;
import CobroCoactivo.Modelo.EtapasTrabajos;
import CobroCoactivo.Modelo.FasesTrabajos;
import CobroCoactivo.Modelo.PlanTrabajos;
import CobroCoactivo.Persistencia.CivDeudas;
import CobroCoactivo.Persistencia.CivEtapasTrabajos;
import CobroCoactivo.Persistencia.CivFasesTrabajos;
import CobroCoactivo.Persistencia.CivPersonas;
import CobroCoactivo.Persistencia.CivPlanTrabajos;
import CobroCoactivo.Utility.DateUtility;
import CobroCoactivo.Utility.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;

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

    public GestionMovimientosImpBO() {
        planTrabajoDAO = new DaoPlanTrabajo();
        deudasDAO = new DaoDeudas();
        personaDAO = new DaoPersonas();
        etapasTrabajoDAO = new DaoEtapasTrabajo();
        fasesTrabajoDAO = new DaoFasesTrabajo();
    }

    @Override
    public void cargarListadoPlanesTrabajo(BeanGestionMovimientos beanGestionMovimientos) throws Exception {
            List<CivPlanTrabajos> listaCivPlanTrabajo = getPlanTrabajoDAO().findAll();
            for (CivPlanTrabajos civPlanTrabajos : listaCivPlanTrabajo) {
                PlanTrabajos planTrabajos = new PlanTrabajos(civPlanTrabajos, civPlanTrabajos.getCivEstadoPlanTrabajos());
                beanGestionMovimientos.getListaPlanTrabajo().add(planTrabajos);
            }
    }

    @Override
    public void buscarDeudasByTipoBusqueda(BeanGestionMovimientos beanGestionMovimientos) throws Exception {
        List<CivDeudas> listaCivDeudas = new ArrayList<>();
        switch (beanGestionMovimientos.getTipoBusqueda()) {
            case 1:
                // tipo busqueda por fecha de adquisicion de la deuda
                listaCivDeudas = getDeudasDAO().listarDeudasByFechaAdquisicion(beanGestionMovimientos.getFechaAdquisicionInicial(), beanGestionMovimientos.getFechaAdquisicionFinal());
                break;
            case 2:
                break;
        }
        if (listaCivDeudas.size() > 0) {
            for (CivDeudas civDeuda : listaCivDeudas) {
                CivPersonas civPersonas = getPersonaDAO().consultarPersonasById(civDeuda.getCivPersonas().getPerId().intValue());
                CivPlanTrabajos civPlanTrabajo = getPlanTrabajoDAO().getPlanTrabajo(civDeuda.getCivPlanTrabajos().getPlatraId().intValue());
                civDeuda.setCivPlanTrabajos(civPlanTrabajo);
                Deudas deuda = new Deudas(civDeuda, civDeuda.getCivEstadoDeudas(), civPlanTrabajo, civDeuda.getCivTipoDeudas(), civPersonas);
                deuda.setDiasHabilesDeuda(DateUtility.fechasDiferenciaEnDias(deuda.getFechaproceso(), new Date()));
                beanGestionMovimientos.getListaDeudas().add(deuda);
                beanGestionMovimientos.getListaDeudasTabla().add(deuda);
            }
        }

    }

    @Override
    public void cargarEtapasPlanTrabajo(BeanGestionMovimientos beanGestionMovimientos) throws Exception {
        List<CivEtapasTrabajos> listCivEtapasTrabajos = getEtapasTrabajoDAO().listarEtapasTrabajoByPlantrabajo(beanGestionMovimientos.getPlanTrabajoSeleccionado().getId());
        for (CivEtapasTrabajos civEtapasTrabajo : listCivEtapasTrabajos) {
            EtapasTrabajos etapasTrabajos = new EtapasTrabajos(civEtapasTrabajo, civEtapasTrabajo.getCivEstadoEtapaTrabajos());
            beanGestionMovimientos.getPlanTrabajoSeleccionado().getListaEtapasTrabajo().add(etapasTrabajos);
        }
    }

    @Override
    public void cargarFasesTrabajo(BeanGestionMovimientos beanGestionMovimientos) throws Exception {
        List<CivFasesTrabajos> listaCivFasesTrabajoses = getFasesTrabajoDAO().listarFasesTrabajo(beanGestionMovimientos.getEtapaTrabajoSeleccionada().getId());
        if (listaCivFasesTrabajoses != null && listaCivFasesTrabajoses.size() > 0) {
            for (CivFasesTrabajos civFasesTrabajose : listaCivFasesTrabajoses) {
                FasesTrabajos fasesTrabajos = new FasesTrabajos(civFasesTrabajose, civFasesTrabajose.getCivEstadoFasesTrabajos());
                beanGestionMovimientos.getEtapaTrabajoSeleccionada().getListaFasesTrabajo().add(fasesTrabajos);
            }
        }

    }

    @Override
    public void filtarListaDeudaTabla(BeanGestionMovimientos beanGestionMovimientos) throws Exception {
        List<Deudas> listaDeudas = new ArrayList<>();
        for (Deudas deuda : beanGestionMovimientos.getListaDeudas()) {
            if (beanGestionMovimientos.getPlanTrabajoSeleccionado().getId() == deuda.getPlanTrabajoDeuda().getId()) {
                listaDeudas.add(deuda);
            }
        }
        beanGestionMovimientos.setListaDeudasTabla(new ArrayList<>());
        beanGestionMovimientos.setListaDeudasTabla(listaDeudas);

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

}
