package CobroCoactivo.Persistencia;
// Generated 30/07/2018 04:15:15 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivEstadoDocumengenerales generated by hbm2java
 */
public class CivEstadoDocumengenerales  implements java.io.Serializable {


     private BigDecimal estdocgenId;
     private String estdocgenDescripcion;
     private Date estdocgenFechainicial;
     private Date estdocgenFechafinal;
     private Date estdocgenFechaproceso;
     private Set civDocumenGeneraleses = new HashSet(0);

    public CivEstadoDocumengenerales() {
    }

	
    public CivEstadoDocumengenerales(BigDecimal estdocgenId, String estdocgenDescripcion, Date estdocgenFechainicial) {
        this.estdocgenId = estdocgenId;
        this.estdocgenDescripcion = estdocgenDescripcion;
        this.estdocgenFechainicial = estdocgenFechainicial;
    }
    public CivEstadoDocumengenerales(BigDecimal estdocgenId, String estdocgenDescripcion, Date estdocgenFechainicial, Date estdocgenFechafinal, Date estdocgenFechaproceso, Set civDocumenGeneraleses) {
       this.estdocgenId = estdocgenId;
       this.estdocgenDescripcion = estdocgenDescripcion;
       this.estdocgenFechainicial = estdocgenFechainicial;
       this.estdocgenFechafinal = estdocgenFechafinal;
       this.estdocgenFechaproceso = estdocgenFechaproceso;
       this.civDocumenGeneraleses = civDocumenGeneraleses;
    }
   
    public BigDecimal getEstdocgenId() {
        return this.estdocgenId;
    }
    
    public void setEstdocgenId(BigDecimal estdocgenId) {
        this.estdocgenId = estdocgenId;
    }
    public String getEstdocgenDescripcion() {
        return this.estdocgenDescripcion;
    }
    
    public void setEstdocgenDescripcion(String estdocgenDescripcion) {
        this.estdocgenDescripcion = estdocgenDescripcion;
    }
    public Date getEstdocgenFechainicial() {
        return this.estdocgenFechainicial;
    }
    
    public void setEstdocgenFechainicial(Date estdocgenFechainicial) {
        this.estdocgenFechainicial = estdocgenFechainicial;
    }
    public Date getEstdocgenFechafinal() {
        return this.estdocgenFechafinal;
    }
    
    public void setEstdocgenFechafinal(Date estdocgenFechafinal) {
        this.estdocgenFechafinal = estdocgenFechafinal;
    }
    public Date getEstdocgenFechaproceso() {
        return this.estdocgenFechaproceso;
    }
    
    public void setEstdocgenFechaproceso(Date estdocgenFechaproceso) {
        this.estdocgenFechaproceso = estdocgenFechaproceso;
    }
    public Set getCivDocumenGeneraleses() {
        return this.civDocumenGeneraleses;
    }
    
    public void setCivDocumenGeneraleses(Set civDocumenGeneraleses) {
        this.civDocumenGeneraleses = civDocumenGeneraleses;
    }




}


