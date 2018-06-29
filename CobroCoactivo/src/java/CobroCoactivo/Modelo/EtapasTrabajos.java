package CobroCoactivo.Modelo;
// Generated 30/05/2018 02:16:05 PM by Hibernate Tools 4.3.1


import CobroCoactivo.Persistencia.CivEstadoEtapaTrabajos;
import CobroCoactivo.Persistencia.CivEtapasTrabajos;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * EtapasTrabajos generated by hbm2java
 */
public class EtapasTrabajos  implements java.io.Serializable {


     private int Id;
     private EstadoEtapaTrabajos estadoEtapaTrabajos;
     private String Descricion;
     private Date Fechaproceso;
     private List<FasesTrabajos> listaFasesTrabajo = new ArrayList<>();
     private int cantidadDeudas;
     private List<Deudas> listDeudas = new ArrayList<>();

    public EtapasTrabajos() {
    }

	
   
    public EtapasTrabajos(CivEtapasTrabajos civEtapasTrabajos,CivEstadoEtapaTrabajos civEstadoEtapaTrabajos) {
       this.Id = civEtapasTrabajos.getEtatraId().intValue();
       this.estadoEtapaTrabajos = new EstadoEtapaTrabajos(civEstadoEtapaTrabajos);
       this.Descricion = civEtapasTrabajos.getEtatraDescricion();
       this.Fechaproceso = civEtapasTrabajos.getEtatraFechaproceso();
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
     * @return the estadoEtapaTrabajos
     */
    public EstadoEtapaTrabajos getEstadoEtapaTrabajos() {
        return estadoEtapaTrabajos;
    }

    /**
     * @param estadoEtapaTrabajos the estadoEtapaTrabajos to set
     */
    public void setEstadoEtapaTrabajos(EstadoEtapaTrabajos estadoEtapaTrabajos) {
        this.estadoEtapaTrabajos = estadoEtapaTrabajos;
    }

    /**
     * @return the Descricion
     */
    public String getDescricion() {
        return Descricion;
    }

    /**
     * @param Descricion the Descricion to set
     */
    public void setDescricion(String Descricion) {
        this.Descricion = Descricion;
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
     * @return the listaFasesTrabajo
     */
    public List<FasesTrabajos> getListaFasesTrabajo() {
        return listaFasesTrabajo;
    }

    /**
     * @param listaFasesTrabajo the listaFasesTrabajo to set
     */
    public void setListaFasesTrabajo(List<FasesTrabajos> listaFasesTrabajo) {
        this.listaFasesTrabajo = listaFasesTrabajo;
    }

    /**
     * @return the cantidadDeudas
     */
    public int getCantidadDeudas() {
        return cantidadDeudas;
    }

    /**
     * @param cantidadDeudas the cantidadDeudas to set
     */
    public void setCantidadDeudas(int cantidadDeudas) {
        this.cantidadDeudas = cantidadDeudas;
    }

    /**
     * @return the listDeudas
     */
    public List<Deudas> getListDeudas() {
        return listDeudas;
    }

    /**
     * @param listDeudas the listDeudas to set
     */
    public void setListDeudas(List<Deudas> listDeudas) {
        this.listDeudas = listDeudas;
    }

    

    
   


}


