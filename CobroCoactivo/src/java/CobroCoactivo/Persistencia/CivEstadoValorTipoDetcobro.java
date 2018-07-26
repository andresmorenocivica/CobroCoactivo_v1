package CobroCoactivo.Persistencia;
// Generated 26/07/2018 02:21:37 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivEstadoValorTipoDetcobro generated by hbm2java
 */
public class CivEstadoValorTipoDetcobro  implements java.io.Serializable {


     private BigDecimal estvaltipdetcobId;
     private String estvaltipdetcobDescripcion;
     private Date estvaltipdetcobFechainicial;
     private Date estvaltipdetcobFechafinal;
     private Date estvaltipdetcobFechaproceso;
     private Set civValorTipoDetalleCobros = new HashSet(0);

    public CivEstadoValorTipoDetcobro() {
    }

	
    public CivEstadoValorTipoDetcobro(BigDecimal estvaltipdetcobId, String estvaltipdetcobDescripcion) {
        this.estvaltipdetcobId = estvaltipdetcobId;
        this.estvaltipdetcobDescripcion = estvaltipdetcobDescripcion;
    }
    public CivEstadoValorTipoDetcobro(BigDecimal estvaltipdetcobId, String estvaltipdetcobDescripcion, Date estvaltipdetcobFechainicial, Date estvaltipdetcobFechafinal, Date estvaltipdetcobFechaproceso, Set civValorTipoDetalleCobros) {
       this.estvaltipdetcobId = estvaltipdetcobId;
       this.estvaltipdetcobDescripcion = estvaltipdetcobDescripcion;
       this.estvaltipdetcobFechainicial = estvaltipdetcobFechainicial;
       this.estvaltipdetcobFechafinal = estvaltipdetcobFechafinal;
       this.estvaltipdetcobFechaproceso = estvaltipdetcobFechaproceso;
       this.civValorTipoDetalleCobros = civValorTipoDetalleCobros;
    }
   
    public BigDecimal getEstvaltipdetcobId() {
        return this.estvaltipdetcobId;
    }
    
    public void setEstvaltipdetcobId(BigDecimal estvaltipdetcobId) {
        this.estvaltipdetcobId = estvaltipdetcobId;
    }
    public String getEstvaltipdetcobDescripcion() {
        return this.estvaltipdetcobDescripcion;
    }
    
    public void setEstvaltipdetcobDescripcion(String estvaltipdetcobDescripcion) {
        this.estvaltipdetcobDescripcion = estvaltipdetcobDescripcion;
    }
    public Date getEstvaltipdetcobFechainicial() {
        return this.estvaltipdetcobFechainicial;
    }
    
    public void setEstvaltipdetcobFechainicial(Date estvaltipdetcobFechainicial) {
        this.estvaltipdetcobFechainicial = estvaltipdetcobFechainicial;
    }
    public Date getEstvaltipdetcobFechafinal() {
        return this.estvaltipdetcobFechafinal;
    }
    
    public void setEstvaltipdetcobFechafinal(Date estvaltipdetcobFechafinal) {
        this.estvaltipdetcobFechafinal = estvaltipdetcobFechafinal;
    }
    public Date getEstvaltipdetcobFechaproceso() {
        return this.estvaltipdetcobFechaproceso;
    }
    
    public void setEstvaltipdetcobFechaproceso(Date estvaltipdetcobFechaproceso) {
        this.estvaltipdetcobFechaproceso = estvaltipdetcobFechaproceso;
    }
    public Set getCivValorTipoDetalleCobros() {
        return this.civValorTipoDetalleCobros;
    }
    
    public void setCivValorTipoDetalleCobros(Set civValorTipoDetalleCobros) {
        this.civValorTipoDetalleCobros = civValorTipoDetalleCobros;
    }




}


