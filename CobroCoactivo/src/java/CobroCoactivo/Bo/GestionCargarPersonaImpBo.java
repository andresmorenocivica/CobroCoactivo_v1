/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanGestionCargarPersonas;
import CobroCoactivo.Dao.DaoDeudas;
import CobroCoactivo.Dao.DaoExpedientes;
import CobroCoactivo.Dao.DaoPersonas;
import CobroCoactivo.Dao.DaoPlanTrabajo;
import CobroCoactivo.Dao.DaoTipoDocumento;
import CobroCoactivo.Dao.ITDeudas;
import CobroCoactivo.Dao.ITExpedientes;
import CobroCoactivo.Dao.ITPersonas;
import CobroCoactivo.Dao.ITPlanTrabajo;
import CobroCoactivo.Dao.ITTipoDocumento;
import CobroCoactivo.Modelo.Deudas;
import CobroCoactivo.Modelo.Expedientes;
import CobroCoactivo.Modelo.Personas;
import CobroCoactivo.Modelo.TipoDeudas;
import CobroCoactivo.Modelo.TipoDocumentos;
import CobroCoactivo.Persistencia.CivDeudas;
import CobroCoactivo.Persistencia.CivEstadoDeudas;
import CobroCoactivo.Persistencia.CivEstadoPersonas;
import CobroCoactivo.Persistencia.CivPersonas;
import CobroCoactivo.Persistencia.CivPlanTrabajos;
import CobroCoactivo.Persistencia.CivTipoDeudas;
import CobroCoactivo.Persistencia.CivTipoDocumentos;
import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.primefaces.context.RequestContext;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

/**
 *
 * @author jvergara
 */
public class GestionCargarPersonaImpBo implements GestionCargarPersonaBO {

    private ITPersonas itPersonas;
    private ITDeudas itDeudas;
    private ITPlanTrabajo itPlanTrabajo;
    private ITTipoDocumento itTipoDocumento;
    private ITExpedientes expedienteDAO;

    public GestionCargarPersonaImpBo() {
        itPersonas = new DaoPersonas();
        itDeudas = new DaoDeudas();
        itPlanTrabajo = new DaoPlanTrabajo();
        itTipoDocumento = new DaoTipoDocumento();
        expedienteDAO = new DaoExpedientes();
    }

    @Override
    public void buscarPersona(BeanGestionCargarPersonas beanGestionCargarPersonas) throws Exception {
        WebTarget baseTarge = beanGestionCargarPersonas.getClient().target("http://localhost:8080/WebServiceContraversiones/api/cartera");
        if (baseTarge.request(MediaType.APPLICATION_JSON).get().getStatus() == 200) {
            String data = baseTarge.request(MediaType.APPLICATION_JSON).get(String.class);
            JSONArray jSONArray = new JSONArray(data);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (!jSONObject.isNull("nombre1") && !jSONObject.isNull("apellido1") && !jSONObject.isNull("nombreDocumento") && !jSONObject.isNull("tipoDocumento")) {
                    Personas personas = new Personas();
                    personas.setNombre1(jSONObject.getString("nombre1"));
                    if (!jSONObject.isNull("nombre2")) {
                        personas.setNombre2(jSONObject.getString("nombre2"));
                    }
                    personas.setApellido1(jSONObject.getString("apellido1"));
                    if (!jSONObject.isNull("apellido2")) {
                        personas.setApellido2(jSONObject.getString("apellido2"));
                    }
                    personas.setDocumento(jSONObject.getString("nombreDocumento"));
                    TipoDocumentos tipoDocumentos = new TipoDocumentos();
                    tipoDocumentos.setId(jSONObject.getInt("tipoDocumento"));
                    personas.setTipoDocumentos(tipoDocumentos);
                    if (!jSONObject.isNull("sexo")) {
                        personas.setSexo(jSONObject.getString("sexo"));
                    }
                    beanGestionCargarPersonas.getListPersonas().add(personas);

                }
            }

        } else {
            if (baseTarge.request(MediaType.APPLICATION_JSON).get().getStatus() == 400) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "No se encontro la persona", null));
            }
//            beanGestionCargarPersonas.setPersonas(new Personas());

        }

    }

    @Override
    public void verDeuda(BeanGestionCargarPersonas beanGestionCargarPersonas) throws Exception {
        beanGestionCargarPersonas.setListDeudas(new ArrayList<>());
        String documento = beanGestionCargarPersonas.getPersonas().getDocumento();
        int tipo = beanGestionCargarPersonas.getPersonas().getTipoDocumentos().getId();
        WebTarget baseTarget = beanGestionCargarPersonas.getClient().target("http://localhost:8080/WebServiceContraversiones/api/cartera/deudas?numero=" + documento + "&&tipo=" + tipo);
        if (baseTarget.request(MediaType.APPLICATION_JSON).get().getStatus() == 200) {
            String data = baseTarget.request(MediaType.APPLICATION_JSON).get(String.class);
            JSONArray jSONArray = new JSONArray(data);

            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                Deudas deudas = new Deudas();
                deudas.setValor(jSONObject.getInt("valor"));
                TipoDeudas tipoDeudas = new TipoDeudas();
                tipoDeudas.setCodigo(jSONObject.getInt("tipo"));
                deudas.setTipoDeudas(tipoDeudas);
                deudas.setReferencia(jSONObject.getString("referencia"));
                SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
                deudas.setFechaDeudas(format.parse(jSONObject.getString("fecha")));
                beanGestionCargarPersonas.getListDeudas().add(deudas);
            }

        }
    }

    @Override
    public void sincronizarDeuda(BeanGestionCargarPersonas beanGestionCargarPersonas) throws Exception {
        int count = 0;
        int tipo = beanGestionCargarPersonas.getPersonas().getTipoDocumentos().getId();
        String documento = beanGestionCargarPersonas.getPersonas().getDocumento();
        CivPersonas civPersonas = getItPersonas().consultarPersonasByDocumento(tipo, documento);
        if (civPersonas != null) {
            for (int i = 0; i < beanGestionCargarPersonas.getListDeudas().size(); i++) {
                List<CivDeudas> listDeudas = getItDeudas().listarDeudasByRefencia(beanGestionCargarPersonas.getListDeudas().get(i).getReferencia());
                if (listDeudas == null) {
                    count++;
                    CivDeudas civDeudas = new CivDeudas();
                    CivTipoDeudas civTipoDeudas = new CivTipoDeudas();
                    civTipoDeudas.setTipdeuId(new BigDecimal(2));
                    civDeudas.setCivTipoDeudas(civTipoDeudas);
                    CivEstadoDeudas civEstadoDeudas = new CivEstadoDeudas();
                    civEstadoDeudas.setEstdeuId(new BigDecimal(1));
                    civDeudas.setCivEstadoDeudas(civEstadoDeudas);
                    civDeudas.setCivPersonas(civPersonas);
                    civDeudas.setDeuFechadeuda(beanGestionCargarPersonas.getListDeudas().get(i).getFechaDeudas());
                    civDeudas.setDeuValor(new BigDecimal(beanGestionCargarPersonas.getListDeudas().get(i).getSaldo()));
                    civDeudas.setDeuSaldo(BigDecimal.ZERO);
                    civDeudas.setDeuFechaproceso(new Date());
                    civDeudas.setDeuRefencia(beanGestionCargarPersonas.getListDeudas().get(i).getReferencia());
                    CivPlanTrabajos civPlanTrabajos = new CivPlanTrabajos();
                    civPlanTrabajos.setPlatraId(new BigDecimal(2));
                    civDeudas.setCivPlanTrabajos(civPlanTrabajos);
                    getItDeudas().create(civDeudas);
                }

            }
            if (count != 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "se han agregado al aplicativo COBRO COACTIVO " + count + " deudas", "Etapa De trabajo exitosamente"));
                RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("$('#modalDeuda').modal('hide')");
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "la persona " + civPersonas.getPerNombre1() + " " + civPersonas.getPerApellido1() + " ya tiene las deudas actualizada", "Etapa De trabajo exitosamente"));
                RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("$('#modalDeuda').modal('hide')");
            }
        } else {
            civPersonas = new CivPersonas();
            civPersonas.setPerDocumento(documento);
            civPersonas.setPerNombre1(beanGestionCargarPersonas.getPersonas().getNombre1());
            civPersonas.setPerNombre2(beanGestionCargarPersonas.getPersonas().getNombre2());
            civPersonas.setPerApellido1(beanGestionCargarPersonas.getPersonas().getApellido1());
            civPersonas.setPerApellido2(beanGestionCargarPersonas.getPersonas().getApellido2());
            civPersonas.setPerSexo(beanGestionCargarPersonas.getPersonas().getSexo());
            CivEstadoPersonas civEstadoPersonas = new CivEstadoPersonas();
            civEstadoPersonas.setEstperId(new BigDecimal(1));
            civPersonas.setCivEstadoPersonas(civEstadoPersonas);

            CivTipoDocumentos civTipoDocumentos = getItTipoDocumento().find(new BigDecimal(tipo));
            if (civTipoDocumentos != null) {
                civPersonas.setCivTipoDocumentos(civTipoDocumentos);

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "El tipo de documento no coincide con los parametros establecido en el diccionario de datos", "Etapa De trabajo exitosamente"));
                return;
            }

            civPersonas.setPerFechaproceso(new Date());
            getItPersonas().create(civPersonas);
            Expedientes expedientes = new Expedientes();
            expedientes.crearExpediente(civPersonas,getExpedienteDAO());

            for (int i = 0; i < beanGestionCargarPersonas.getListDeudas().size(); i++) {
                CivDeudas civDeudas = new CivDeudas();
                CivTipoDeudas civTipoDeudas = new CivTipoDeudas();
                civTipoDeudas.setTipdeuId(new BigDecimal(2));
                civDeudas.setCivTipoDeudas(civTipoDeudas);
                CivEstadoDeudas civEstadoDeudas = new CivEstadoDeudas();
                civEstadoDeudas.setEstdeuId(new BigDecimal(1));
                civDeudas.setCivEstadoDeudas(civEstadoDeudas);
                civDeudas.setCivPersonas(civPersonas);
                civDeudas.setDeuFechadeuda(beanGestionCargarPersonas.getListDeudas().get(i).getFechaDeudas());
                civDeudas.setDeuValor(new BigDecimal(beanGestionCargarPersonas.getListDeudas().get(i).getSaldo()));
                civDeudas.setDeuSaldo(BigDecimal.ZERO);
                civDeudas.setDeuFechaproceso(new Date());
                civDeudas.setDeuRefencia(beanGestionCargarPersonas.getListDeudas().get(i).getReferencia());
                CivPlanTrabajos civPlanTrabajos = new CivPlanTrabajos();
                civPlanTrabajos.setPlatraId(new BigDecimal(2));
                civDeudas.setCivPlanTrabajos(civPlanTrabajos);
                getItDeudas().create(civDeudas);
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "se ha creado la persona " + civPersonas.getPerNombre1() + " " + civPersonas.getPerApellido1() + " con deudas:" + beanGestionCargarPersonas.getListDeudas().size(), "Etapa De trabajo exitosamente"));
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("$('#modalDeuda').modal('hide')");

        }
        beanGestionCargarPersonas.setListDeudas(new ArrayList<>());
        beanGestionCargarPersonas.setPersonas(new Personas());
    }

    /**
     * @return the itPersonas
     */
    public ITPersonas getItPersonas() {
        return itPersonas;
    }

    /**
     * @param itPersonas the itPersonas to set
     */
    public void setItPersonas(ITPersonas itPersonas) {
        this.itPersonas = itPersonas;
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
     * @return the itPlanTrabajo
     */
    public ITPlanTrabajo getItPlanTrabajo() {
        return itPlanTrabajo;
    }

    /**
     * @param itPlanTrabajo the itPlanTrabajo to set
     */
    public void setItPlanTrabajo(ITPlanTrabajo itPlanTrabajo) {
        this.itPlanTrabajo = itPlanTrabajo;
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
     * @return the expedienteDAO
     */
    public ITExpedientes getExpedienteDAO() {
        return expedienteDAO;
    }

    /**
     * @param expedienteDAO the expedienteDAO to set
     */
    public void setExpedienteDAO(ITExpedientes expedienteDAO) {
        this.expedienteDAO = expedienteDAO;
    }

}
