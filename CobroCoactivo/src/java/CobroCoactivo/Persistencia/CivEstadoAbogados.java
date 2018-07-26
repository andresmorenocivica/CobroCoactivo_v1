package CobroCoactivo.Persistencia;
// Generated 25/07/2018 05:27:39 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivEstadoAbogados generated by hbm2java
 */
public class CivEstadoAbogados  implements java.io.Serializable {


     private BigDecimal estaboId;
     private String estaboDescripcion;
     private Date estaboFechainicial;
     private Date estaboFechafinal;
     private Date estaboFechaproceso;
     private Set civAbogadoses = new HashSet(0);

    public CivEstadoAbogados() {
    }

	
    public CivEstadoAbogados(BigDecimal estaboId, String estaboDescripcion) {
        this.estaboId = estaboId;
        this.estaboDescripcion = estaboDescripcion;
    }
    public CivEstadoAbogados(BigDecimal estaboId, String estaboDescripcion, Date estaboFechainicial, Date estaboFechafinal, Date estaboFechaproceso, Set civAbogadoses) {
       this.estaboId = estaboId;
       this.estaboDescripcion = estaboDescripcion;
       this.estaboFechainicial = estaboFechainicial;
       this.estaboFechafinal = estaboFechafinal;
       this.estaboFechaproceso = estaboFechaproceso;
       this.civAbogadoses = civAbogadoses;
    }
   
    public BigDecimal getEstaboId() {
        return this.estaboId;
    }
    
    public void setEstaboId(BigDecimal estaboId) {
        this.estaboId = estaboId;
    }
    public String getEstaboDescripcion() {
        return this.estaboDescripcion;
    }
    
    public void setEstaboDescripcion(String estaboDescripcion) {
        this.estaboDescripcion = estaboDescripcion;
    }
    public Date getEstaboFechainicial() {
        return this.estaboFechainicial;
    }
    
    public void setEstaboFechainicial(Date estaboFechainicial) {
        this.estaboFechainicial = estaboFechainicial;
    }
    public Date getEstaboFechafinal() {
        return this.estaboFechafinal;
    }
    
    public void setEstaboFechafinal(Date estaboFechafinal) {
        this.estaboFechafinal = estaboFechafinal;
    }
    public Date getEstaboFechaproceso() {
        return this.estaboFechaproceso;
    }
    
    public void setEstaboFechaproceso(Date estaboFechaproceso) {
        this.estaboFechaproceso = estaboFechaproceso;
    }
    public Set getCivAbogadoses() {
        return this.civAbogadoses;
    }
    
    public void setCivAbogadoses(Set civAbogadoses) {
        this.civAbogadoses = civAbogadoses;
    }




}


