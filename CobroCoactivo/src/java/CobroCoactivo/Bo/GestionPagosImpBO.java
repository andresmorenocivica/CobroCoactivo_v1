/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanGestionPagos;
import CobroCoactivo.Dao.DaoDetalleDeudas;
import CobroCoactivo.Dao.DaoDetallesPagos;
import CobroCoactivo.Dao.DaoDeudas;
import CobroCoactivo.Dao.DaoPagos;
import CobroCoactivo.Dao.DaoTipoDocumento;
import CobroCoactivo.Dao.DaoUsuarios;
import CobroCoactivo.Dao.ITDetalleDeudas;
import CobroCoactivo.Dao.ITDetallePagos;
import CobroCoactivo.Dao.ITDeudas;
import CobroCoactivo.Dao.ITPagos;
import CobroCoactivo.Dao.ITTipoDocumento;
import CobroCoactivo.Dao.ITUsuarios;
import CobroCoactivo.Modelo.Deudas;
import CobroCoactivo.Modelo.Pagos;
import CobroCoactivo.Persistencia.CivDetalleDeudas;
import CobroCoactivo.Persistencia.CivDetallePagos;
import CobroCoactivo.Persistencia.CivDeudas;
import CobroCoactivo.Persistencia.CivEstadoDetalleDeudas;
import CobroCoactivo.Persistencia.CivEstadoDetallePago;
import CobroCoactivo.Persistencia.CivEstadoDeudas;
import CobroCoactivo.Persistencia.CivEstadoPagos;
import CobroCoactivo.Persistencia.CivPagos;
import CobroCoactivo.Persistencia.CivUsuarios;
import CobroCoactivo.Utility.HibernateUtil;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.scene.chart.PieChart;
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
    private ITPagos iTPagos;
    private ITDetallePagos iTDetallePagos;
    private ITUsuarios usuariosDAO;

    public GestionPagosImpBO() {

        iTTipoDocumento = new DaoTipoDocumento();
        iTDeudas = new DaoDeudas();
        itDetalleDeudas = new DaoDetalleDeudas();
        iTPagos = new DaoPagos();
        iTDetallePagos = new DaoDetallesPagos();
        usuariosDAO = new DaoUsuarios();
    }

    @Override
    public void listarPagos(BeanGestionPagos pagos) throws Exception {
        pagos.setListPagos(new ArrayList<>());
        Client client = pagos.getClient();
        WebTarget baseTarget = client.target("http://10.10.2.204:8080/WebServiceContraversiones/api/pagos");
        if (baseTarget.request(MediaType.APPLICATION_JSON).get().getStatus() == 200) {
            String data = baseTarget.request(MediaType.APPLICATION_JSON).get(String.class);
            JSONArray jSONArray = new JSONArray(data);
            for (int i = 0; i < jSONArray.length(); i++) {
                Pagos pago = new Pagos();
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                pago.setNumero(jSONObject.getString("numeroFactura"));
                pago.setValor(Double.valueOf(jSONObject.getString("total")));
                SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
                pago.setFecha(format.parse(jSONObject.getString("fecha")));
                pagos.getListPagos().add(pago);
            }
        }
    }

    @Override
    public void verCarteras(BeanGestionPagos beanGestionPagos) throws Exception {
        beanGestionPagos.setListDeudas(new ArrayList<>());
        Client client = beanGestionPagos.getClient();
        WebTarget baseTarget = client.target("http://10.10.2.204:8080/WebServiceContraversiones/api/pagos/carteras?numeroFactura=" + beanGestionPagos.getNumeroFactura());
        if (baseTarget.request(MediaType.APPLICATION_JSON).get().getStatus() == 200) {
            String data = baseTarget.request(MediaType.APPLICATION_JSON).get(String.class);
            JSONArray jSONArray = new JSONArray(data);
            for (int i = 0; i < jSONArray.length(); i++) {
                Deudas deudas = new Deudas();
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                deudas.setReferenciaUnica(jSONObject.getLong("idCartera"));
                SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
                deudas.setFechaDeudas(format.parse(jSONObject.getString("fechaCreacion")));
                deudas.setValor(jSONObject.getInt("dtValorTotal"));
                deudas.setReferencia(jSONObject.getString("referencia"));
                beanGestionPagos.getListDeudas().add(deudas);

            }

        }

    }

    @Override
    public void sincronizarPagos(BeanGestionPagos beanGestionPagos) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction trasantion = session.beginTransaction();
            for (int i = 0; i < beanGestionPagos.getListDeudas().size(); i++) {
                CivDeudas civDeudas = getiTDeudas().getDeudaByReferenciaUnica(session, beanGestionPagos.getListDeudas().get(i).getReferenciaUnica());
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

                    CivPagos civPagos = new CivPagos();
                    civPagos.setPagNumero(beanGestionPagos.getPagos().getNumero());
                    civPagos.setPagValor(new BigDecimal(beanGestionPagos.getPagos().getValor()));
                    civPagos.setPagFecha(beanGestionPagos.getPagos().getFecha());
                    CivEstadoPagos civEstadoPagos = new CivEstadoPagos();
                    civEstadoPagos.setEstpagId(BigDecimal.ONE);
                    civPagos.setCivEstadoPagos(civEstadoPagos);
                    civPagos.setPagBanco(beanGestionPagos.getPagos().getBanco());
                    civPagos.setPagOservacion(beanGestionPagos.getPagos().getOservacion());
                    CivUsuarios civUsuarios = getUsuariosDAO().find(session, new BigDecimal(beanGestionPagos.getIdUser()));
                    civPagos.setCivUsuarios(civUsuarios);
                    getiTPagos().create(session, civPagos);
                    CivDetallePagos civDetallePagos = new CivDetallePagos();
                    civDetallePagos.setDetpagValor(civPagos.getPagValor());
                    civDetallePagos.setDetpagFechaproceso(new Date());
                    civDetallePagos.setCivDeudas(civDeudas);
                    civDetallePagos.setCivPagos(civPagos);
                    CivEstadoDetallePago civEstadoDetallePago = new CivEstadoDetallePago();
                    civEstadoDetallePago.setEstdetpagId(new BigDecimal(1));
                    civDetallePagos.setCivEstadoDetallePago(civEstadoDetallePago);
                    getiTDetallePagos().create(session, civDetallePagos);

                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "no se ha encontrado cartera asociada en CobroCoactivo", "Etapa De trabajo exitosamente"));
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.execute("$('#modalPagos').modal('hide')");
                    return;
                }
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "se ha Actualizado el pago", "Etapa De trabajo exitosamente"));
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

    /**
     * @return the iTPagos
     */
    public ITPagos getiTPagos() {
        return iTPagos;
    }

    /**
     * @param iTPagos the iTPagos to set
     */
    public void setiTPagos(ITPagos iTPagos) {
        this.iTPagos = iTPagos;
    }

    /**
     * @return the iTDetallePagos
     */
    public ITDetallePagos getiTDetallePagos() {
        return iTDetallePagos;
    }

    /**
     * @param iTDetallePagos the iTDetallePagos to set
     */
    public void setiTDetallePagos(ITDetallePagos iTDetallePagos) {
        this.iTDetallePagos = iTDetallePagos;
    }

    /**
     * @return the usuariosDAO
     */
    public ITUsuarios getUsuariosDAO() {
        return usuariosDAO;
    }

    /**
     * @param usuariosDAO the usuariosDAO to set
     */
    public void setUsuariosDAO(ITUsuarios usuariosDAO) {
        this.usuariosDAO = usuariosDAO;
    }

}
