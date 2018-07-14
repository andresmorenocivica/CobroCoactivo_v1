package CobroCoactivo.Persistencia;
// Generated 13/07/2018 10:45:24 AM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CivPlanTrabajos generated by hbm2java
 */
public class CivPlanTrabajos implements java.io.Serializable {

    private BigDecimal platraId;
    private CivEstadoPlanTrabajos civEstadoPlanTrabajos;
    private String platraDescripcion;
    private Date platraFechaproceso;
    private String platraColor;
    private String platraNumeroactoadm;
    private Date platraFechaanctoadm;
    private Set civDeudases = new HashSet(0);
    private Set civEtapasTrabajoses = new HashSet(0);

    public CivPlanTrabajos() {
    }

    public CivPlanTrabajos(BigDecimal platraId, CivEstadoPlanTrabajos civEstadoPlanTrabajos, String platraDescripcion) {
        this.platraId = platraId;
        this.civEstadoPlanTrabajos = civEstadoPlanTrabajos;
        this.platraDescripcion = platraDescripcion;
    }

    public CivPlanTrabajos(BigDecimal platraId, CivEstadoPlanTrabajos civEstadoPlanTrabajos, String platraDescripcion, Date platraFechaproceso, String platraColor, String platraNumeroactoadm, Date platraFechaanctoadm, Set civDeudases, Set civEtapasTrabajoses) {
        this.platraId = platraId;
        this.civEstadoPlanTrabajos = civEstadoPlanTrabajos;
        this.platraDescripcion = platraDescripcion;
        this.platraFechaproceso = platraFechaproceso;
        this.platraColor = platraColor;
        this.platraNumeroactoadm = platraNumeroactoadm;
        this.platraFechaanctoadm = platraFechaanctoadm;
        this.civDeudases = civDeudases;
        this.civEtapasTrabajoses = civEtapasTrabajoses;
    }

    public BigDecimal getPlatraId() {
        return this.platraId;
    }

    public void setPlatraId(BigDecimal platraId) {
        this.platraId = platraId;
    }

    public CivEstadoPlanTrabajos getCivEstadoPlanTrabajos() {
        return this.civEstadoPlanTrabajos;
    }

    public void setCivEstadoPlanTrabajos(CivEstadoPlanTrabajos civEstadoPlanTrabajos) {
        this.civEstadoPlanTrabajos = civEstadoPlanTrabajos;
    }

    public String getPlatraDescripcion() {
        return this.platraDescripcion;
    }

    public void setPlatraDescripcion(String platraDescripcion) {
        this.platraDescripcion = platraDescripcion;
    }

    public Date getPlatraFechaproceso() {
        return this.platraFechaproceso;
    }

    public void setPlatraFechaproceso(Date platraFechaproceso) {
        this.platraFechaproceso = platraFechaproceso;
    }

    public String getPlatraColor() {
        return this.platraColor;
    }

    public void setPlatraColor(String platraColor) {
        this.platraColor = platraColor;
    }

    public String getPlatraNumeroactoadm() {
        return this.platraNumeroactoadm;
    }

    public void setPlatraNumeroactoadm(String platraNumeroactoadm) {
        this.platraNumeroactoadm = platraNumeroactoadm;
    }

    public Date getPlatraFechaanctoadm() {
        return this.platraFechaanctoadm;
    }

    public void setPlatraFechaanctoadm(Date platraFechaanctoadm) {
        this.platraFechaanctoadm = platraFechaanctoadm;
    }

    public Set getCivDeudases() {
        return this.civDeudases;
    }

    public void setCivDeudases(Set civDeudases) {
        this.civDeudases = civDeudases;
    }

    public Set getCivEtapasTrabajoses() {
        return this.civEtapasTrabajoses;
    }

    public void setCivEtapasTrabajoses(Set civEtapasTrabajoses) {
        this.civEtapasTrabajoses = civEtapasTrabajoses;
    }

}
