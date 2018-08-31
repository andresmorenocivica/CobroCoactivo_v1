/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ITGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivDeudas;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author emadrid
 */
public interface ITDeudas extends ITGeneryHibernateDao<CivDeudas, Integer> {

    public List<CivDeudas> listarDeudas(Session session, int Id_personas) throws Exception;

    public List<CivDeudas> listarDeudasByRefenciaUnica(Session session, Long refenciaUnica) throws Exception;

    public List<CivDeudas> listarDeudasByReferencia(Session session, String referencia) throws Exception;

    public List<CivDeudas> listarDeudasByTipo(Session session, int tipoDeudas) throws Exception;

    public List<CivDeudas> listarDeudasByFechaAdquisicion(Session session, Date fechaInicial, Date fechaFinal) throws Exception;

    public List<CivDeudas> listarDeudasByFechaDeuda(Session session, Date fechaInicial, Date fechaFinal) throws Exception;

    public long countDeudas(Session session, long idPlanTrabajo) throws Exception;

    public List<CivDeudas> listarDeudasByPlanTrabajo(Session session, int idPlanTrabajo) throws Exception;

    public long countDeudasEtapa(Session session, int idPlanTrabajo, int idEtapaTrabajo) throws Exception;

    public long countDeudasEtapaFases(Session session, int idPlanTrabajo, int idEtapaTrabajo, int idFaseTrabajo) throws Exception;

    CivDeudas getDeudaByReferenciaUnica(Session session, long idReferencia) throws Exception;

}
