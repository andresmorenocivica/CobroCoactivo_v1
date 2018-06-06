package CobroCoactivo.Bo;

import CobroCoactivo.Beans.BeanGestionPersonas;
import java.io.Serializable;
import CobroCoactivo.Dao.*;
import CobroCoactivo.Modelo.Personas;
import CobroCoactivo.Modelo.TipoDocumentos;
import CobroCoactivo.Persistencia.CivPersonas;
import CobroCoactivo.Persistencia.CivTipoDocumentos;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author jvergara
 */
public class GestionPersonasImpBO implements GestionPersonasBO, Serializable {

    private ITTipoDocumento tipoDocumentoDAO;
    private ITPersonas personasDAO;

    public GestionPersonasImpBO() {
        tipoDocumentoDAO = new DaoTipoDocumento();
        personasDAO = new DaoPersonas();
    }

    @Override
    public void cargarTipoDocumento(BeanGestionPersonas beanGestionPersonas) throws Exception {
        List<CivTipoDocumentos> listCivTipoDocumento = getTipoDocumentoDAO().listAll();
        for (CivTipoDocumentos civTipoDocumentos : listCivTipoDocumento) {
            TipoDocumentos tipoDocumentos = new TipoDocumentos(civTipoDocumentos);
            beanGestionPersonas.getListTipoDocumento().add(tipoDocumentos);
        }
    }

    @Override
    public void consultarByDocumentoByTipoDocumento(BeanGestionPersonas bean) throws Exception {
        bean.setListPersonas(new ArrayList<>());
        CivPersonas civPersonas = getPersonasDAO().consultarPersonasByDocumento(bean.getTipoDocumentoPersona(), bean.getDocumentoPersona());
        if (civPersonas == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"no se encontro la persona en el sistema", null));
        }
        Personas persona = new Personas(civPersonas);
        bean.getListPersonas().add(persona);
    }

    /**
     * @return the tipoDocumentoDAO
     */
    public ITTipoDocumento getTipoDocumentoDAO() {
        return tipoDocumentoDAO;
    }

    /**
     * @param tipoDocumentoDAO the tipoDocumentoDAO to set
     */
    public void setTipoDocumentoDAO(ITTipoDocumento tipoDocumentoDAO) {
        this.tipoDocumentoDAO = tipoDocumentoDAO;
    }

    /**
     * @return the personasDAO
     */
    public ITPersonas getPersonasDAO() {
        return personasDAO;
    }

    /**
     * @param personasDAO the personasDAO to set
     */
    public void setPersonasDAO(ITPersonas personasDAO) {
        this.personasDAO = personasDAO;
    }

}
