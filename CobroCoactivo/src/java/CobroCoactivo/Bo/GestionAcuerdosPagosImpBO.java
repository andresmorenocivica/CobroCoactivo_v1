/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanAcuerdosPagos;
import CobroCoactivo.Dao.DaoDetalleDeudas;
import CobroCoactivo.Dao.DaoDeudas;
import CobroCoactivo.Dao.DaoFinanciacion;
import CobroCoactivo.Dao.DaoTipoDocumento;
import CobroCoactivo.Dao.DaoUsuarios;
import CobroCoactivo.Dao.ITDetalleDeudas;
import CobroCoactivo.Dao.ITDeudas;
import CobroCoactivo.Dao.ITFinanciacion;
import CobroCoactivo.Dao.ITTipoDocumento;
import CobroCoactivo.Dao.ITUsuarios;
import CobroCoactivo.Modelo.Deudas;
import CobroCoactivo.Modelo.Financiacion;
import CobroCoactivo.Persistencia.CivDetalleDeudas;
import CobroCoactivo.Persistencia.CivDeudas;
import CobroCoactivo.Persistencia.CivEstadoDetalleDeudas;
import CobroCoactivo.Persistencia.CivEstadoDeudas;
import CobroCoactivo.Persistencia.CivEstadoFinanciacion;
import CobroCoactivo.Persistencia.CivFinanciacion;
import CobroCoactivo.Persistencia.CivUsuarios;
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
public class GestionAcuerdosPagosImpBO implements GestionAcuerdosPagosBO {

    private ITTipoDocumento itTipoDocumento;
    private ITDeudas itDeudas;
    private ITDetalleDeudas itDetalleDeudas;
    private ITUsuarios itUsuarios;
    private ITFinanciacion itFinanciacion;

    public GestionAcuerdosPagosImpBO() {
        this.itTipoDocumento = new DaoTipoDocumento();
        itDeudas = new DaoDeudas();
        itDetalleDeudas = new DaoDetalleDeudas();
        itUsuarios = new DaoUsuarios();
        itFinanciacion = new DaoFinanciacion();
    }

    @Override
    public void sincronizar(BeanAcuerdosPagos beanAcuerdosPagos) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction trasantion = session.beginTransaction();
            for (int i = 0; i < beanAcuerdosPagos.getListDeudas().size(); i++) {
                CivDeudas civDeudas = getItDeudas().getDeudaByReferenciaUnica(session, beanAcuerdosPagos.getListDeudas().get(i).getReferenciaUnica());
                if (civDeudas != null) {
                    CivEstadoDeudas civEstadoDeudas = new CivEstadoDeudas();
                    civEstadoDeudas.setEstdeuId(BigDecimal.valueOf(5));
                    civDeudas.setCivEstadoDeudas(civEstadoDeudas);
                    getItDeudas().update(session, civDeudas);
                    List<CivDetalleDeudas> civListDetalleDeudas = getItDetalleDeudas().getListDetallesDeudasByidDeuda(session, civDeudas.getDeuId().longValue());
                    if (civListDetalleDeudas != null) {
                        for (CivDetalleDeudas DetalleDeuda : civListDetalleDeudas) {
                            CivEstadoDetalleDeudas civEstadoDetalleDeudas = new CivEstadoDetalleDeudas();
                            civEstadoDetalleDeudas.setEstdetdeuId(BigDecimal.valueOf(5));
                            DetalleDeuda.setCivEstadoDetalleDeudas(civEstadoDetalleDeudas);
                            getItDetalleDeudas().update(session, DetalleDeuda);
                        }
                    }
                    CivFinanciacion civFinanciacion = new CivFinanciacion();
                    civFinanciacion.setFinNumero(beanAcuerdosPagos.getFinanciacion().getNumero());
                    civFinanciacion.setFinValor(new BigDecimal(beanAcuerdosPagos.getFinanciacion().getValor()));
                    civFinanciacion.setFinFecha(beanAcuerdosPagos.getFinanciacion().getFecha());
                    CivUsuarios civUsuarios = getItUsuarios().find(session, new BigDecimal(beanAcuerdosPagos.getIdUser()));
                    civFinanciacion.setCivUsuarios(civUsuarios);
                    CivEstadoFinanciacion civEstadoFinanciacion = new CivEstadoFinanciacion();
                    civEstadoFinanciacion.setEstfinId(new BigDecimal(1));
                    civFinanciacion.setCivEstadoFinanciacion(civEstadoFinanciacion);
                    civFinanciacion.setCivUsuarios(civUsuarios);
                    getItFinanciacion().create(session, civFinanciacion);
                    trasantion.commit();

                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "no se ha encontrado cartera asociada en CobroCoactivo", "Etapa De trabajo exitosamente"));
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.execute("$('#modalActosAdministractivo').modal('hide')");
                    return;
                }
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Se ha actualizado la cartera mediante acto administractivo", "Etapa De trabajo exitosamente"));
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("$('#modalActosAdministractivo').modal('hide')");

        } finally {
            session.flush();
            session.close();

        }
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
                beanAcuerdosPagos.setFinanciacion(financiacion);
                getListCarteras(beanAcuerdosPagos);
                if (beanAcuerdosPagos.getListDeudas().size() > 0) {
                    beanAcuerdosPagos.getListFinanciacion().add(financiacion);
                }

            }
        }
    }

    @Override
    public void getListCarteras(BeanAcuerdosPagos beanAcuerdosPagos) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            beanAcuerdosPagos.setListDeudas(new ArrayList<>());
            Client client = beanAcuerdosPagos.getClient();
            WebTarget baseTarget = client.target("http://10.10.2.204:8080/WebServiceContraversiones/api/acuerdosPagos/carteras?nofinanciacion=" + beanAcuerdosPagos.getFinanciacion().getNumero());
            if (baseTarget.request(MediaType.APPLICATION_JSON).get().getStatus() == 200) {
                String data = baseTarget.request(MediaType.APPLICATION_JSON).get(String.class);
                JSONArray jSONArray = new JSONArray(data);
                for (int i = 0; i < jSONArray.length(); i++) {
                    Deudas deudas = new Deudas();
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    deudas.setReferenciaUnica(jSONObject.getLong("referenciaUnica"));
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
                    deudas.setFechaDeudas(format.parse(jSONObject.getString("fecha")));
                    deudas.setReferencia(jSONObject.getString("referencia"));
                    deudas.setValor(jSONObject.getInt("valorCartera"));
                    CivDeudas civDeudas = getItDeudas().getDeudaByReferenciaUnica(session, deudas.getReferenciaUnica());
                    if (civDeudas != null) {
                        beanAcuerdosPagos.getListDeudas().add(deudas);
                    }

                }

            }
        } finally {
            session.flush();
            session.close();
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

    /**
     * @return the itDeudas
     */
    public ITDeudas getItDeudas() {
        return itDeudas;
    }

    /**
     * @param itDeudas the itDeudas to set
     */
    public void setItDeudas(ITDeudas itDeudas) {
        this.itDeudas = itDeudas;
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

    /**
     * @return the itUsuarios
     */
    public ITUsuarios getItUsuarios() {
        return itUsuarios;
    }

    /**
     * @param itUsuarios the itUsuarios to set
     */
    public void setItUsuarios(ITUsuarios itUsuarios) {
        this.itUsuarios = itUsuarios;
    }

    /**
     * @return the itFinanciacion
     */
    public ITFinanciacion getItFinanciacion() {
        return itFinanciacion;
    }

    /**
     * @param itFinanciacion the itFinanciacion to set
     */
    public void setItFinanciacion(ITFinanciacion itFinanciacion) {
        this.itFinanciacion = itFinanciacion;
    }

}
