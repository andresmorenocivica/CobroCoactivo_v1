package CobroCoactivo.Modelo;
// Generated 30/05/2018 02:16:05 PM by Hibernate Tools 4.3.1

import CobroCoactivo.Persistencia.CivDeudas;
import CobroCoactivo.Persistencia.CivEstadoPersonas;
import CobroCoactivo.Persistencia.CivPersonas;
import CobroCoactivo.Persistencia.CivTipoDocumentos;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;

/**
 * Personas generated by hbm2java
 */
public class Personas implements java.io.Serializable {

    private int Id;
    private TipoDocumentos tipoDocumentos = new TipoDocumentos();
    private EstadoPersonas estadoPersonas = new EstadoPersonas();
    @NotNull(message = "El numero de documento no puede estar vacio")
    private String Documento;
    @NotNull(message = "El primer nombre no puede estar vacio")
    private String Nombre1 = new String();
    private String Nombre2;
    @NotNull(message = "El primer apellido no puede estar vacio")
    private String Apellido1;
    private String Apellido2;
    @NotNull(message = "El sexo no puede estar vacio")
    private String Sexo;
    private Date Fechaproceso;
    private List<Deudas> Listdeuda = new ArrayList<>();
    private List<DatosPersonas> listDatosPersonas = new ArrayList<>();
    private boolean editable = false;

    public Personas() {
    }

    public Personas(CivPersonas civPersonas) {
        this.Id = civPersonas.getPerId().intValue();
        this.Documento = civPersonas.getPerDocumento();
        this.Nombre1 = civPersonas.getPerNombre1();
        this.Nombre2 = civPersonas.getPerNombre2();
        this.Apellido1 = civPersonas.getPerApellido1();
        this.Apellido2 = civPersonas.getPerApellido2();
        this.Sexo = civPersonas.getPerSexo();
        this.Fechaproceso = civPersonas.getPerFechaproceso();
    }

    public Personas(CivPersonas civPersonas, CivTipoDocumentos civTipoDocumentos) {
        this.Id = civPersonas.getPerId().intValue();
        this.Documento = civPersonas.getPerDocumento();
        this.Nombre1 = civPersonas.getPerNombre1();
        this.Nombre2 = civPersonas.getPerNombre2();
        this.Apellido1 = civPersonas.getPerApellido1();
        this.Apellido2 = civPersonas.getPerApellido2();
        this.Sexo = civPersonas.getPerSexo();
        this.Fechaproceso = civPersonas.getPerFechaproceso();
        this.tipoDocumentos = new TipoDocumentos(civTipoDocumentos);
    }

    public Personas(CivPersonas civPersonas, CivEstadoPersonas civEstadoPersonas, CivTipoDocumentos civTipoDocumentos) {
        this.Id = civPersonas.getPerId().intValue();
        this.Documento = civPersonas.getPerDocumento();
        this.Nombre1 = civPersonas.getPerNombre1();
        this.Nombre2 = civPersonas.getPerNombre2();
        this.Apellido1 = civPersonas.getPerApellido1();
        this.Apellido2 = civPersonas.getPerApellido2();
        this.Sexo = civPersonas.getPerSexo();
        this.Fechaproceso = civPersonas.getPerFechaproceso();
        this.estadoPersonas = new EstadoPersonas(civEstadoPersonas);
        this.tipoDocumentos = new TipoDocumentos(civTipoDocumentos);
    }

    public Personas(CivPersonas civPersonas, CivEstadoPersonas civEstadoPersonas, CivTipoDocumentos civTipoDocumentos, List<CivDeudas> listCivDeudas) {
        this.Id = civPersonas.getPerId().intValue();
        this.Documento = civPersonas.getPerDocumento();
        this.Nombre1 = civPersonas.getPerNombre1();
        this.Nombre2 = civPersonas.getPerNombre2();
        this.Apellido1 = civPersonas.getPerApellido1();
        this.Apellido2 = civPersonas.getPerApellido2();
        this.Sexo = civPersonas.getPerSexo();
        this.Fechaproceso = civPersonas.getPerFechaproceso();
        this.estadoPersonas = new EstadoPersonas(civEstadoPersonas);
        this.tipoDocumentos = new TipoDocumentos(civTipoDocumentos);
        for (CivDeudas civDeudas : listCivDeudas) {
            Deudas deuda = new Deudas(civDeudas, civDeudas.getCivEstadoDeudas(), civDeudas.getCivTipoDeudas(),civPersonas);
            this.Listdeuda.add(deuda);
        }
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
     * @return the tipoDocumentos
     */
    public TipoDocumentos getTipoDocumentos() {
        return tipoDocumentos;
    }

    /**
     * @param tipoDocumentos the tipoDocumentos to set
     */
    public void setTipoDocumentos(TipoDocumentos tipoDocumentos) {
        this.tipoDocumentos = tipoDocumentos;
    }

    /**
     * @return the estadoPersonas
     */
    public EstadoPersonas getEstadoPersonas() {
        return estadoPersonas;
    }

    /**
     * @param estadoPersonas the estadoPersonas to set
     */
    public void setEstadoPersonas(EstadoPersonas estadoPersonas) {
        this.estadoPersonas = estadoPersonas;
    }

    /**
     * @return the Documento
     */
    public String getDocumento() {
        return Documento;
    }

    /**
     * @param Documento the Documento to set
     */
    public void setDocumento(String Documento) {
        this.Documento = Documento;
    }

    /**
     * @return the Nombre1
     */
    public String getNombre1() {
        return Nombre1;
    }

    /**
     * @param Nombre1 the Nombre1 to set
     */
    public void setNombre1(String Nombre1) {
        this.Nombre1 = Nombre1;
    }

    /**
     * @return the Nombre2
     */
    public String getNombre2() {
        return Nombre2;
    }

    /**
     * @param Nombre2 the Nombre2 to set
     */
    public void setNombre2(String Nombre2) {
        this.Nombre2 = Nombre2;
    }

    /**
     * @return the Apellido1
     */
    public String getApellido1() {
        return Apellido1;
    }

    /**
     * @param Apellido1 the Apellido1 to set
     */
    public void setApellido1(String Apellido1) {
        this.Apellido1 = Apellido1;
    }

    /**
     * @return the Apellido2
     */
    public String getApellido2() {
        return Apellido2;
    }

    /**
     * @param Apellido2 the Apellido2 to set
     */
    public void setApellido2(String Apellido2) {
        this.Apellido2 = Apellido2;
    }

    /**
     * @return the Sexo
     */
    public String getSexo() {
        return Sexo;
    }

    /**
     * @param Sexo the Sexo to set
     */
    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
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

    /**
     * @return the Listdeuda
     */
    public List<Deudas> getListdeuda() {
        return Listdeuda;
    }

    /**
     * @param Listdeuda the Listdeuda to set
     */
    public void setListdeuda(List<Deudas> Listdeuda) {
        this.Listdeuda = Listdeuda;
    }

    /**
     * @return the editable
     */
    public boolean isEditable() {
        return editable;
    }

    /**
     * @param editable the editable to set
     */
    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    /**
     * @return the listDatosPersonas
     */
    public List<DatosPersonas> getListDatosPersonas() {
        return listDatosPersonas;
    }

    /**
     * @param listDatosPersonas the listDatosPersonas to set
     */
    public void setListDatosPersonas(List<DatosPersonas> listDatosPersonas) {
        this.listDatosPersonas = listDatosPersonas;
    }

}
