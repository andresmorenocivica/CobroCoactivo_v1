/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanGestionPagos;
import CobroCoactivo.Dao.DaoDetalleDeudas;
import CobroCoactivo.Dao.DaoDeudas;
import CobroCoactivo.Dao.DaoTipoDocumento;
import CobroCoactivo.Dao.ITDetalleDeudas;
import CobroCoactivo.Dao.ITDeudas;
import CobroCoactivo.Dao.ITTipoDocumento;
import CobroCoactivo.Modelo.Pagos;
import CobroCoactivo.Modelo.Personas;
import CobroCoactivo.Modelo.TipoDocumentos;
import CobroCoactivo.Persistencia.CivDetalleDeudas;
import CobroCoactivo.Persistencia.CivDeudas;
import CobroCoactivo.Persistencia.CivEstadoDetalleDeudas;
import CobroCoactivo.Persistencia.CivEstadoDeudas;
import CobroCoactivo.Persistencia.CivTipoDocumentos;
import CobroCoactivo.Utility.HibernateUtil;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

/**
 *
 * @author jvergara
 */
public class GestionPagosImpBO implements GestionPagosBo {

    private ITTipoDocumento iTTipoDocumento;
    private ITDeudas iTDeudas;
    private ITDetalleDeudas itDetalleDeudas;

    public GestionPagosImpBO() {

        iTTipoDocumento = new DaoTipoDocumento();
        iTDeudas = new DaoDeudas();
        itDetalleDeudas = new DaoDetalleDeudas();
    }

    @Override
    public void listarPersonas(BeanGestionPagos pagos) throws Exception {
        pagos.setListPersonas(new ArrayList<>());
        Client client = pagos.getClient();
        WebTarget baseTarget = client.target("http://10.10.2.204:8080/WebServiceContraversiones/api/pagos/personas");
        if (baseTarget.request(MediaType.APPLICATION_JSON).get().getStatus() == 200) {
            String data = baseTarget.request(MediaType.APPLICATION_JSON).get(String.class);
            JSONArray jSONArray = new JSONArray(data);
            for (int i = 0; i < jSONArray.length(); i++) {
                Personas personas = new Personas();
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                personas.setNombre1(jSONObject.getString("nombre1"));
                personas.setApellido1(jSONObject.getString("apellido1"));
                CivTipoDocumentos civTipoDocumentos = getiTTipoDocumento().find(BigDecimal.valueOf(jSONObject.getInt("tipoDocumento")));
                if (civTipoDocumentos != null) {
                    TipoDocumentos tipoDocumentos = new TipoDocumentos(civTipoDocumentos);
                    personas.setTipoDocumentos(tipoDocumentos);
                }
                personas.setDocumento(jSONObject.getString("nombreDocumento"));
                if (!jSONObject.isNull("nombre2")) {
                    personas.setNombre2("nombre2");

                }
                if (!jSONObject.isNull("apellido2")) {
                    personas.setApellido2("apellido2");
                }
                if (!jSONObject.isNull("sexo")) {
                    personas.setSexo(jSONObject.getString("sexo"));
                }

                pagos.getListPersonas().add(personas);
            }
        }
    }

    @Override
    public void verPagos(BeanGestionPagos beanGestionPagos) throws Exception {
        beanGestionPagos.setListPagos(new ArrayList<>());
        Client client = beanGestionPagos.getClient();
        WebTarget baseTarget = client.target("http://10.10.2.204:8080/WebServiceContraversiones/api/pagos?tipo=" + beanGestionPagos.getPersonas().getTipoDocumentos().getId() + "&&numero=" + beanGestionPagos.getPersonas().getDocumento());
        if (baseTarget.request(MediaType.APPLICATION_JSON).get().getStatus() == 200) {
            String data = baseTarget.request(MediaType.APPLICATION_JSON).get(String.class);
            JSONArray jSONArray = new JSONArray(data);
            for (int i = 0; i < jSONArray.length(); i++) {
                Pagos pagos = new Pagos();
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                pagos.setNumero(jSONObject.getString("numeroFactura"));
                SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
                pagos.setFecha(format.parse(jSONObject.getString("fecha")));
                pagos.setValor(jSONObject.getDouble("total"));
                pagos.setIdCartera(jSONObject.getLong("idCartera"));
                beanGestionPagos.getListPagos().add(pagos);

            }

        }

    }

    @Override
    public void sincronizarPagos(BeanGestionPagos beanGestionPagos) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction trasantion = session.beginTransaction();
            for (int i = 0; i < beanGestionPagos.getListPagos().size(); i++) {
                CivDeudas civDeudas = getiTDeudas().getDeudaByReferenciaUnica(session, beanGestionPagos.getListPagos().get(i).getIdCartera());
                if (civDeudas != null) {
                    CivEstadoDeudas civEstadoDeudas = new CivEstadoDeudas();
                    civEstadoDeudas.setEstdeuId(BigDecimal.valueOf(3));
                    civDeudas.setCivEstadoDeudas(civEstadoDeudas);
                    getiTDeudas().update(session, civDeudas);
                    List<CivDetalleDeudas> civListDetalleDeudas = getItDetalleDeudas().getListDetallesDeudasByidDeuda(session, civDeudas.getDeuId().longValue());
                    if (civListDetalleDeudas != null) {
                        for (CivDetalleDeudas DetalleDeuda : civListDetalleDeudas) {
                            CivEstadoDetalleDeudas civEstadoDetalleDeudas = new CivEstadoDetalleDeudas();
                            civEstadoDetalleDeudas.setEstdetdeuId(BigDecimal.valueOf(3));
                            DetalleDeuda.setCivEstadoDetalleDeudas(civEstadoDetalleDeudas);
                            getItDetalleDeudas().update(session, DetalleDeuda);
                        }
                    }

                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "no se ha encontrado cartera asociada en CobroCoactivo", "Etapa De trabajo exitosamente"));
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.execute("$('#modalPagos').modal('hide')");
                    return;
                }
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "se han agregado ", "Etapa De trabajo exitosamente"));
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("$('#modalPagos').modal('hide')");
            trasantion.commit();

        } finally {
            session.flush();
            session.close();
        }
    }

    /**
     * @return the iTTipoDocumento
     */
    public ITTipoDocumento getiTTipoDocumento() {
        return iTTipoDocumento;
    }

    /**
     * @param iTTipoDocumento the iTTipoDocumento to set
     */
    public void setiTTipoDocumento(ITTipoDocumento iTTipoDocumento) {
        this.iTTipoDocumento = iTTipoDocumento;
    }

    /**
     * @return the iTDeudas
     */
    public ITDeudas getiTDeudas() {
        return iTDeudas;
    }

    /**
     * @param iTDeudas the iTDeudas to set
     */
    public void setiTDeudas(ITDeudas iTDeudas) {
        this.iTDeudas = iTDeudas;
    }

    /**
     * @return the itDetalleDeudas
     */
    public ITDetalleDeudas getItDetalleDeudas() {
        return itDetalleDeudas;
    }

    /**
     * @param itDetalleDeudas the itDetalleDeudas to set
     */
    public void setItDetalleDeudas(ITDetalleDeudas itDetalleDeudas) {
        this.itDetalleDeudas = itDetalleDeudas;
    }

}
