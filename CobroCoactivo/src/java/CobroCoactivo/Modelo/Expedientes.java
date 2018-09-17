package CobroCoactivo.Modelo;
// Generated 30/05/2018 02:16:05 PM by Hibernate Tools 4.3.1

import CobroCoactivo.Dao.ITExpedientes;
import CobroCoactivo.Persistencia.CivEstadoExpedientes;
import CobroCoactivo.Persistencia.CivExpedientes;
import CobroCoactivo.Persistencia.CivPersonas;
import CobroCoactivo.Persistencia.CivTipoExpedientes;
import CobroCoactivo.Utility.HibernateUtil;
import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Expedientes generated by hbm2java
 */
public class Expedientes implements java.io.Serializable {

    private int Id;
    private TipoExpedientes tipoExpedientes;
    private EstadoExpedientes estadoExpedientes;
    private String Referencia;
    private Date Fechaproceso;
    private String Ubicacion;
    private Set detalleExpedienteses = new HashSet(0);

    public Expedientes() {
    }

    public String crearExpediente(CivPersonas civPersonas, ITExpedientes expedienteDAO) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            String nombreExpedientePersona = civPersonas.getPerDocumento();
            String folderExpedientePersona = "D:/Archivo/Expedientes/" + nombreExpedientePersona;
            File foldersPersona = new File(folderExpedientePersona);
            if (!foldersPersona.exists()) {
                foldersPersona.mkdirs();
                CivExpedientes civExpedientes = new CivExpedientes();
                civExpedientes.setExpFechaproceso(new Date());
                civExpedientes.setExpReferencia(nombreExpedientePersona);
                civExpedientes.setExpUbicacion(folderExpedientePersona);
                CivEstadoExpedientes civEstadoExpedientes = new CivEstadoExpedientes();
                civEstadoExpedientes.setEstexpId(BigDecimal.ONE);
                civExpedientes.setCivEstadoExpedientes(civEstadoExpedientes);
                CivTipoExpedientes civTipoExpedientes = new CivTipoExpedientes();
                civTipoExpedientes.setTipexpId(BigDecimal.ONE);
                civExpedientes.setCivTipoExpedientes(civTipoExpedientes);
                expedienteDAO.create(session, civExpedientes);
                transaction.commit();
            } else {
                return folderExpedientePersona;
            }
            return folderExpedientePersona;
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.flush();
            session.close();
        }
        return null;
    }

    public Expedientes(CivExpedientes civExpedientes) {
        this.Id = civExpedientes.getExpId().intValue();
        this.Referencia = civExpedientes.getExpReferencia();
        this.Fechaproceso = civExpedientes.getExpFechaproceso();
        this.Ubicacion = civExpedientes.getExpUbicacion();
    }

    public Expedientes(CivExpedientes civExpedientes, CivTipoExpedientes civTipoExpedientes) {
        this.Id = civExpedientes.getExpId().intValue();
        this.Referencia = civExpedientes.getExpReferencia();
        this.Fechaproceso = civExpedientes.getExpFechaproceso();
        this.Ubicacion = civExpedientes.getExpUbicacion();
        this.tipoExpedientes = new TipoExpedientes(civTipoExpedientes);
    }

    public Expedientes(CivExpedientes civExpedientes, CivTipoExpedientes civTipoExpedientes, CivEstadoExpedientes civEstadoExpedientes) {
        this.Id = civExpedientes.getExpId().intValue();
        this.Referencia = civExpedientes.getExpReferencia();
        this.Fechaproceso = civExpedientes.getExpFechaproceso();
        this.Ubicacion = civExpedientes.getExpUbicacion();
        this.tipoExpedientes = new TipoExpedientes(civTipoExpedientes);
        this.estadoExpedientes = new EstadoExpedientes(civEstadoExpedientes);
    }

    public TipoExpedientes getTipoExpedientes() {
        return this.tipoExpedientes;
    }

    public void setTipoExpedientes(TipoExpedientes tipoExpedientes) {
        this.tipoExpedientes = tipoExpedientes;
    }

    public EstadoExpedientes getEstadoExpedientes() {
        return this.estadoExpedientes;
    }

    public void setEstadoExpedientes(EstadoExpedientes estadoExpedientes) {
        this.estadoExpedientes = estadoExpedientes;
    }

    public Set getDetalleExpedienteses() {
        return this.detalleExpedienteses;
    }

    public void setDetalleExpedienteses(Set detalleExpedienteses) {
        this.detalleExpedienteses = detalleExpedienteses;
    }

    /**
     * @return the Ubicacion
     */
    public String getUbicacion() {
        return Ubicacion;
    }

    /**
     * @param Ubicacion the Ubicacion to set
     */
    public void setUbicacion(String Ubicacion) {
        this.Ubicacion = Ubicacion;
    }

    /**
     * @return the Id
     */
    public int getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(int Id) {
        this.Id = Id;
    }

    /**
     * @return the Referencia
     */
    public String getReferencia() {
        return Referencia;
    }

    /**
     * @param Referencia the Refencia to set
     */
    public void setReferencia(String Referencia) {
        this.Referencia = Referencia;
    }

    /**
     * @return the Fechaproceso
     */
    public Date getFechaproceso() {
        return Fechaproceso;
    }

    /**
     * @param Fechaproceso the Fechaproceso to set
     */
    public void setFechaproceso(Date Fechaproceso) {
        this.Fechaproceso = Fechaproceso;
    }

}
