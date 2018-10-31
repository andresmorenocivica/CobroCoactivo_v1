package CobroCoactivo.Persistencia;
// Generated 30/08/2018 09:56:13 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivTipoExcepcion generated by hbm2java
 */
public class CivTipoExcepcion  implements java.io.Serializable {


     private BigDecimal tipexcId;
     private String tipexcNombre;
     private Date tipexcFechaproceso;
     private Date tipexcFechainicial;
     private Date tipexcFechafinal;
     private Set civExcepcioneses = new HashSet(0);

    public CivTipoExcepcion() {
    }

	
    public CivTipoExcepcion(BigDecimal tipexcId) {
        this.tipexcId = tipexcId;
    }
    public CivTipoExcepcion(BigDecimal tipexcId, String tipexcNombre, Date tipexcFechaproceso, Date tipexcFechainicial, Date tipexcFechafinal, Set civExcepcioneses) {
       this.tipexcId = tipexcId;
       this.tipexcNombre = tipexcNombre;
       this.tipexcFechaproceso = tipexcFechaproceso;
       this.tipexcFechainicial = tipexcFechainicial;
       this.tipexcFechafinal = tipexcFechafinal;
       this.civExcepcioneses = civExcepcioneses;
    }
   
    public BigDecimal getTipexcId() {
        return this.tipexcId;
    }
    
    public void setTipexcId(BigDecimal tipexcId) {
        this.tipexcId = tipexcId;
    }
    public String getTipexcNombre() {
        return this.tipexcNombre;
    }
    
    public void setTipexcNombre(String tipexcNombre) {
        this.tipexcNombre = tipexcNombre;
    }
    public Date getTipexcFechaproceso() {
        return this.tipexcFechaproceso;
    }
    
    public void setTipexcFechaproceso(Date tipexcFechaproceso) {
        this.tipexcFechaproceso = tipexcFechaproceso;
    }
    public Date getTipexcFechainicial() {
        return this.tipexcFechainicial;
    }
    
    public void setTipexcFechainicial(Date tipexcFechainicial) {
        this.tipexcFechainicial = tipexcFechainicial;
    }
    public Date getTipexcFechafinal() {
        return this.tipexcFechafinal;
    }
    
    public void setTipexcFechafinal(Date tipexcFechafinal) {
        this.tipexcFechafinal = tipexcFechafinal;
    }
    public Set getCivExcepcioneses() {
        return this.civExcepcioneses;
    }
    
    public void setCivExcepcioneses(Set civExcepcioneses) {
        this.civExcepcioneses = civExcepcioneses;
    }




}

