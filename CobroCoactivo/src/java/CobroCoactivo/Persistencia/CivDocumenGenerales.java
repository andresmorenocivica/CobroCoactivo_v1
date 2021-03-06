package CobroCoactivo.Persistencia;
// Generated 30/08/2018 09:56:13 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivDocumenGenerales generated by hbm2java
 */
public class CivDocumenGenerales  implements java.io.Serializable {


     private BigDecimal docgenId;
     private CivEstadoDocumengenerales civEstadoDocumengenerales;
     private String docgenDescripcion;
     private Date docgenFechaproceso;
     private String docgenArchivo;
     private Set civFasesGeneraleses = new HashSet(0);

    public CivDocumenGenerales() {
    }

	
    public CivDocumenGenerales(BigDecimal docgenId, CivEstadoDocumengenerales civEstadoDocumengenerales, String docgenDescripcion) {
        this.docgenId = docgenId;
        this.civEstadoDocumengenerales = civEstadoDocumengenerales;
        this.docgenDescripcion = docgenDescripcion;
    }
    public CivDocumenGenerales(BigDecimal docgenId, CivEstadoDocumengenerales civEstadoDocumengenerales, String docgenDescripcion, Date docgenFechaproceso, String docgenArchivo, Set civFasesGeneraleses) {
       this.docgenId = docgenId;
       this.civEstadoDocumengenerales = civEstadoDocumengenerales;
       this.docgenDescripcion = docgenDescripcion;
       this.docgenFechaproceso = docgenFechaproceso;
       this.docgenArchivo = docgenArchivo;
       this.civFasesGeneraleses = civFasesGeneraleses;
    }
   
    public BigDecimal getDocgenId() {
        return this.docgenId;
    }
    
    public void setDocgenId(BigDecimal docgenId) {
        this.docgenId = docgenId;
    }
    public CivEstadoDocumengenerales getCivEstadoDocumengenerales() {
        return this.civEstadoDocumengenerales;
    }
    
    public void setCivEstadoDocumengenerales(CivEstadoDocumengenerales civEstadoDocumengenerales) {
        this.civEstadoDocumengenerales = civEstadoDocumengenerales;
    }
    public String getDocgenDescripcion() {
        return this.docgenDescripcion;
    }
    
    public void setDocgenDescripcion(String docgenDescripcion) {
        this.docgenDescripcion = docgenDescripcion;
    }
    public Date getDocgenFechaproceso() {
        return this.docgenFechaproceso;
    }
    
    public void setDocgenFechaproceso(Date docgenFechaproceso) {
        this.docgenFechaproceso = docgenFechaproceso;
    }
    public String getDocgenArchivo() {
        return this.docgenArchivo;
    }
    
    public void setDocgenArchivo(String docgenArchivo) {
        this.docgenArchivo = docgenArchivo;
    }
    public Set getCivFasesGeneraleses() {
        return this.civFasesGeneraleses;
    }
    
    public void setCivFasesGeneraleses(Set civFasesGeneraleses) {
        this.civFasesGeneraleses = civFasesGeneraleses;
    }




}


