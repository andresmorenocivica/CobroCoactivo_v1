/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanActosAdministractivo;
import CobroCoactivo.Modelo.ActosAdministractivos;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

/**
 *
 * @author jvergara
 */
public class GestionActosAdministractivosImpBO implements GestionActosAdministractivosBO {

    @Override
    public void getTodosActosAdministractivos(BeanActosAdministractivo beanActosAdministractivo) throws Exception {
        beanActosAdministractivo.setListActosAdministractivo(new ArrayList<>());
        Client client = beanActosAdministractivo.getClient();
        WebTarget baseTarget = client.target("http://10.10.2.204:8080/WebServiceContraversiones/api/actosAdministractivos");
        if (baseTarget.request(MediaType.APPLICATION_JSON).get().getStatus() == 200) {
            String data = baseTarget.request(MediaType.APPLICATION_JSON).get(String.class);
            JSONArray jSONArray = new JSONArray(data);
            for (int i = 0; i < jSONArray.length(); i++) {
                ActosAdministractivos actosAdministractivos = new ActosAdministractivos();
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                SimpleDateFormat parse = new SimpleDateFormat("yyyy-mm-dd");
                actosAdministractivos.setActFecha(parse.parse(jSONObject.getString("fechaProceso")));
                actosAdministractivos.setActNumero(jSONObject.getString("noresolucion"));
                beanActosAdministractivo.getListActosAdministractivo().add(actosAdministractivos);
            }
        }
    }

}
