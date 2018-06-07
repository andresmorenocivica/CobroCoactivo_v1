/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanPlanGeneral;
import CobroCoactivo.Dao.DaoEstadoPlanGeneral;
import CobroCoactivo.Dao.DaoPlanGeneral;
import CobroCoactivo.Dao.ITPlanGeneral;
import CobroCoactivo.Modelo.PlanGenerales;
import CobroCoactivo.Persistencia.CivEstadoPlanGenerales;
import CobroCoactivo.Persistencia.CivPlanGenerales;
import java.math.BigDecimal;
import java.util.List;
import CobroCoactivo.Dao.ITEstadoPlanGeneral;
import CobroCoactivo.Modelo.EstadoPlanGenerales;

/**
 *
 * @author jvergara
 */
public class PlanGeneralImpBO implements PlanGeneralBO {

    private ITPlanGeneral iTPlanGeneral;
    private ITEstadoPlanGeneral iTEstado;

    public PlanGeneralImpBO() {
        iTPlanGeneral = new DaoPlanGeneral();
        iTEstado = new DaoEstadoPlanGeneral();

    }

    @Override
    public void ListarPlanesGenerales(BeanPlanGeneral bean) throws Exception {
        List<CivPlanGenerales> listcivPlanGeneral = getiTPlanGeneral().findAll();
        for (CivPlanGenerales civPlanGenerales : listcivPlanGeneral) {
            PlanGenerales planGenerales = new PlanGenerales(civPlanGenerales,civPlanGenerales.getCivEstadoPlanGenerales());
            bean.getListadoPlanGeneraleses().add(planGenerales);
            
        }
    }
    
     @Override
    public void ListarEstadoGenerales(BeanPlanGeneral bean) throws Exception {
       List<CivEstadoPlanGenerales> listEstadoPlanGeneraleses = getiTEstado().findAll();
         for (CivEstadoPlanGenerales listEstadoPlanGeneralese : listEstadoPlanGeneraleses) {
             EstadoPlanGenerales estadoPlanGenerales =  new EstadoPlanGenerales(listEstadoPlanGeneralese);
             bean.getListadoEStadoPlanesGenerales().add(estadoPlanGenerales);
             
         }
    }

    @Override
    public void GuardarPlanGeneral(BeanPlanGeneral bean) throws Exception {
        CivPlanGenerales civPlanGenerales = new CivPlanGenerales();
        CivEstadoPlanGenerales civEstadoPlanGenerales = new CivEstadoPlanGenerales();
        civEstadoPlanGenerales.setEstplagenId(new BigDecimal(bean.getIdEstadoGeneral()));
        civPlanGenerales.setPlagenDescripcion(bean.getPlanGenerales().getPlagenDescripcion());
        civPlanGenerales.setCivEstadoPlanGenerales(civEstadoPlanGenerales);
        civPlanGenerales.setPlagenFechaproceso(new BigDecimal(15));
        getiTPlanGeneral().create(civPlanGenerales);

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

   

}
