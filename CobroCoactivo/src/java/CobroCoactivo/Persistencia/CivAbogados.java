package CobroCoactivo.Persistencia;
// Generated 7/06/2018 08:54:17 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;

/**
 * CivAbogados generated by hbm2java
 */
public class CivAbogados  implements java.io.Serializable {


     private BigDecimal aboId;
     private CivPersonas civPersonas;
     private CivEstadoAbogados civEstadoAbogados;
     private CivEntidades civEntidades;
     private BigDecimal aboNumlicencia;
     private Date aboFechainicial;
     private Date aboFechafinal;
     private String aboFirma;

    public CivAbogados() {
    }

	
    public CivAbogados(BigDecimal aboId, CivPersonas civPersonas, CivEstadoAbogados civEstadoAbogados, CivEntidades civEntidades, BigDecimal aboNumlicencia, Date aboFechainicial) {
        this.aboId = aboId;
        this.civPersonas = civPersonas;
        this.civEstadoAbogados = civEstadoAbogados;
        this.civEntidades = civEntidades;
        this.aboNumlicencia = aboNumlicencia;
        this.aboFechainicial = aboFechainicial;
    }
    public CivAbogados(BigDecimal aboId, CivPersonas civPersonas, CivEstadoAbogados civEstadoAbogados, CivEntidades civEntidades, BigDecimal aboNumlicencia, Date aboFechainicial, Date aboFechafinal, String aboFirma) {
       this.aboId = aboId;
       this.civPersonas = civPersonas;
       this.civEstadoAbogados = civEstadoAbogados;
       this.civEntidades = civEntidades;
       this.aboNumlicencia = aboNumlicencia;
       this.aboFechainicial = aboFechainicial;
       this.aboFechafinal = aboFechafinal;
       this.aboFirma = aboFirma;
    }
   
    public BigDecimal getAboId() {
        return this.aboId;
    }
    
    public void setAboId(BigDecimal aboId) {
        this.aboId = aboId;
    }
    public CivPersonas getCivPersonas() {
        return this.civPersonas;
    }
    
    public void setCivPersonas(CivPersonas civPersonas) {
        this.civPersonas = civPersonas;
    }
    public CivEstadoAbogados getCivEstadoAbogados() {
        return this.civEstadoAbogados;
    }
    
    public void setCivEstadoAbogados(CivEstadoAbogados civEstadoAbogados) {
        this.civEstadoAbogados = civEstadoAbogados;
    }
    public CivEntidades getCivEntidades() {
        return this.civEntidades;
    }
    
    public void setCivEntidades(CivEntidades civEntidades) {
        this.civEntidades = civEntidades;
    }
    public BigDecimal getAboNumlicencia() {
        return this.aboNumlicencia;
    }
    
    public void setAboNumlicencia(BigDecimal aboNumlicencia) {
        this.aboNumlicencia = aboNumlicencia;
    }
    public Date getAboFechainicial() {
        return this.aboFechainicial;
    }
    
    public void setAboFechainicial(Date aboFechainicial) {
        this.aboFechainicial = aboFechainicial;
    }
    public Date getAboFechafinal() {
        return this.aboFechafinal;
    }
    
    public void setAboFechafinal(Date aboFechafinal) {
        this.aboFechafinal = aboFechafinal;
    }
    public String getAboFirma() {
        return this.aboFirma;
    }
    
    public void setAboFirma(String aboFirma) {
        this.aboFirma = aboFirma;
    }




}


