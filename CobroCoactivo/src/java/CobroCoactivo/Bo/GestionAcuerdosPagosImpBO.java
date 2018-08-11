/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanAcuerdosPagos;
import CobroCoactivo.Dao.DaoTipoDocumento;
import CobroCoactivo.Dao.ITTipoDocumento;
import CobroCoactivo.Modelo.Financiacion;
import CobroCoactivo.Modelo.Personas;
import CobroCoactivo.Modelo.TipoDocumentos;
import CobroCoactivo.Persistencia.CivTipoDocumentos;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

/**
 *
 * @author jvergara
 */
public class GestionAcuerdosPagosImpBO implements GestionAcuerdosPagosBO {

    private ITTipoDocumento itTipoDocumento;

    public GestionAcuerdosPagosImpBO() {
        this.itTipoDocumento = new DaoTipoDocumento();
    }

    @Override
    public void getListAcuerdosPagos(BeanAcuerdosPagos beanAcuerdosPagos) throws Exception {
        beanAcuerdosPagos.setListFinanciacion(new ArrayList<>());
        WebTarget basetarTarget = beanAcuerdosPagos.getClient().target("http://10.10.2.204:8080/WebServiceContraversiones/api/acuerdosPagos");
        if (basetarTarget.request(MediaType.APPLICATION_JSON).get().getStatus() == 200) {
            String data = basetarTarget.request(MediaType.APPLICATION_JSON).get(String.class);
            JSONArray jSONArray = new JSONArray(data);
            for (int i = 0; i < jSONArray.length(); i++) {
                Financiacion financiacion = new Financiacion();
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                financiacion.setNumero(jSONObject.getString("numero"));
                financiacion.setValor(jSONObject.getDouble("valor"));
                SimpleDateFormat parse = new SimpleDateFormat("yyyy-mm-dd");
                financiacion.setFecha(parse.parse(jSONObject.getString("fecha")));
                beanAcuerdosPagos.getListFinanciacion().add(financiacion);
            }
        }
    }

    /**
     * @return the itTipoDocumento
     */
    public ITTipoDocumento getItTipoDocumento() {
        return itTipoDocumento;
    }

    /**
     * @param itTipoDocumento the itTipoDocumento to set
     */
    public void setItTipoDocumento(ITTipoDocumento itTipoDocumento) {
        this.itTipoDocumento = itTipoDocumento;
    }

}
