/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanAcuerdosPagos;
import java.util.ArrayList;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jvergara
 */
public class GestionAcuerdosPagosImpBO implements GestionAcuerdosPagosBO{

    @Override
    public void getListPersonasAcuerdosPagos(BeanAcuerdosPagos beanAcuerdosPagos) throws Exception {
        beanAcuerdosPagos.setListPersonas(new ArrayList<>());
        WebTarget basetarTarget = beanAcuerdosPagos.getClient().target("http://10.10.2.204:8080/WebServiceContraversiones/api/pagos/AcuerdosPagos");
        if ( basetarTarget.request(MediaType.APPLICATION_JSON).get().getStatus() == 200) {
            
        }
      
    }
    
}
