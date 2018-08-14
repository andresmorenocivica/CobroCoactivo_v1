/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanActosAdministractivo;
import CobroCoactivo.Dao.DaoActosAdministractivo;
import CobroCoactivo.Dao.DaoDetalleDeudas;
import CobroCoactivo.Dao.DaoDeudas;
import CobroCoactivo.Dao.DaoUsuarios;
import CobroCoactivo.Dao.ITActosAdministractivo;
import CobroCoactivo.Dao.ITDetalleDeudas;
import CobroCoactivo.Dao.ITDeudas;
import CobroCoactivo.Dao.ITUsuarios;
import CobroCoactivo.Modelo.ActosAdministractivos;
import CobroCoactivo.Modelo.Deudas;
import CobroCoactivo.Persistencia.CivActosAdministractivos;
import CobroCoactivo.Persistencia.CivDetalleDeudas;
import CobroCoactivo.Persistencia.CivDeudas;
import CobroCoactivo.Persistencia.CivEstadoDetalleDeudas;
import CobroCoactivo.Persistencia.CivEstadoDeudas;
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
public class GestionActosAdministractivosImpBO implements GestionActosAdministractivosBO {

    private ITDeudas itDeudas;
    private ITDetalleDeudas iTDetalleDeudas;
    private ITActosAdministractivo iTActosAdministractivo;
    private ITUsuarios usuariosDao;

    public GestionActosAdministractivosImpBO() {
        itDeudas = new DaoDeudas();
        iTDetalleDeudas = new DaoDetalleDeudas();
        iTActosAdministractivo = new DaoActosAdministractivo();
        usuariosDao = new DaoUsuarios();
    }

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
                beanActosAdministractivo.setActosAdministractivos(actosAdministractivos);
                getCarteras(beanActosAdministractivo);
                if (beanActosAdministractivo.getListDeudas().size() > 0) {
                    beanActosAdministractivo.getListActosAdministractivo().add(actosAdministractivos);
                }
            }
        }
    }

    @Override
    public void getCarteras(BeanActosAdministractivo beanActosAdministractivo) throws Exception {
        beanActosAdministractivo.setListDeudas(new ArrayList<>());
        Client client = beanActosAdministractivo.getClient();
        WebTarget baseTarget = client.target("http://10.10.2.204:8080/WebServiceContraversiones/api/actosAdministractivos/carteras?noresolucion=" + beanActosAdministractivo.getActosAdministractivos().getActNumero());
        if (baseTarget.request(MediaType.APPLICATION_JSON).get().getStatus() == 200) {
            String data = baseTarget.request(MediaType.APPLICATION_JSON).get(String.class);
            JSONArray jSONArray = new JSONArray(data);
            for (int i = 0; i < jSONArray.length(); i++) {
                Deudas deudas = new Deudas();
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                deudas.setReferenciaUnica(jSONObject.getLong("idCartera"));
                SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
                deudas.setFechaDeudas(format.parse(jSONObject.getString("fechaCreacion")));
                deudas.setReferencia(jSONObject.getString("referencia"));
                beanActosAdministractivo.getListDeudas().add(deudas);

            }

        }

    }

    @Override
    public void sincronizarActosAdministractivo(BeanActosAdministractivo beanActosAdministractivo) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction trasantion = session.beginTransaction();
            for (int i = 0; i < beanActosAdministractivo.getListDeudas().size(); i++) {
                CivDeudas civDeudas = getItDeudas().getDeudaByReferenciaUnica(session, beanActosAdministractivo.getListDeudas().get(i).getReferenciaUnica());
                if (civDeudas != null) {
                    CivEstadoDeudas civEstadoDeudas = new CivEstadoDeudas();
                    civEstadoDeudas.setEstdeuId(BigDecimal.valueOf(4));
                    civDeudas.setCivEstadoDeudas(civEstadoDeudas);
                    getItDeudas().update(session, civDeudas);
                    List<CivDetalleDeudas> civListDetalleDeudas = getiTDetalleDeudas().getListDetallesDeudasByidDeuda(session, civDeudas.getDeuId().longValue());
                    if (civListDetalleDeudas != null) {
                        for (CivDetalleDeudas DetalleDeuda : civListDetalleDeudas) {
                            CivEstadoDetalleDeudas civEstadoDetalleDeudas = new CivEstadoDetalleDeudas();
                            civEstadoDetalleDeudas.setEstdetdeuId(BigDecimal.valueOf(4));
                            DetalleDeuda.setCivEstadoDetalleDeudas(civEstadoDetalleDeudas);
                            getiTDetalleDeudas().update(session, DetalleDeuda);
                        }
                    }
                    CivActosAdministractivos civActosAdministractivos = new CivActosAdministractivos();
                    civActosAdministractivos.setActNumero(beanActosAdministractivo.getActosAdministractivos().getActNumero());
                    civActosAdministractivos.setActFecha(beanActosAdministractivo.getActosAdministractivos().getActFecha());
                    CivUsuarios civUsuarios = getUsuariosDao().find(session, new BigDecimal(beanActosAdministractivo.getIdUser()));
                    civActosAdministractivos.setCivUsuarios(civUsuarios);
                    getiTActosAdministractivo().create(session, civActosAdministractivos);
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
     * @return the iTDetalleDeudas
     */
    public ITDetalleDeudas getiTDetalleDeudas() {
        return iTDetalleDeudas;
    }

    /**
     * @param iTDetalleDeudas the iTDetalleDeudas to set
     */
    public void setiTDetalleDeudas(ITDetalleDeudas iTDetalleDeudas) {
        this.iTDetalleDeudas = iTDetalleDeudas;
    }

    /**
     * @return the iTActosAdministractivo
     */
    public ITActosAdministractivo getiTActosAdministractivo() {
        return iTActosAdministractivo;
    }

    /**
     * @param iTActosAdministractivo the iTActosAdministractivo to set
     */
    public void setiTActosAdministractivo(ITActosAdministractivo iTActosAdministractivo) {
        this.iTActosAdministractivo = iTActosAdministractivo;
    }

    /**
     * @return the usuariosDao
     */
    public ITUsuarios getUsuariosDao() {
        return usuariosDao;
    }

    /**
     * @param usuariosDao the usuariosDao to set
     */
    public void setUsuariosDao(ITUsuarios usuariosDao) {
        this.usuariosDao = usuariosDao;
    }

}
