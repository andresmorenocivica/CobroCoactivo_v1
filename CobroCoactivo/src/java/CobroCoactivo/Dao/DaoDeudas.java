/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CobroCoactivo.Dao;

import CobroCoactivo.General.ImpGeneryHibernateDao;
import CobroCoactivo.Persistencia.CivDeudas;
import CobroCoactivo.Utility.HibernateUtil;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author emadrid
 */
public class DaoDeudas extends ImpGeneryHibernateDao<CivDeudas, Integer> implements ITDeudas {

    @Override
    public List<CivDeudas> listarDeudas(int Id_personas) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "SELECT * FROM CIV_DEUDAS WHERE DEU_PER_FK =:idPersona";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(CivDeudas.class);
        query.setBigDecimal("idPersona", new BigDecimal(Id_personas));
        if (query.list().size() > 0) {
            return query.list();
        }
        session.close();
        return null;
    }

    @Override
    public List<CivDeudas> listarDeudasByRefenciaUnica(Session session, Long referenciaUnica) throws Exception {
        String sql = "SELECT * FROM CIV_DEUDAS WHERE DEU_REF_UNICA =:referenciaUnica";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(CivDeudas.class);
        query.setParameter("referenciaUnica", new BigDecimal(referenciaUnica));
        if (query.list().size() > 0) {
            return query.list();
        }
        return null;
    }

    @Override
    public List<CivDeudas> listarDeudasByRefencia(Session session, String referencia) throws Exception {
        String sql = "SELECT * FROM CIV_DEUDAS WHERE DEU_REFENCIA =:referencia";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(CivDeudas.class);
        query.setString("referencia", referencia);
        if (query.list().size() > 0) {
            return query.list();
        }
        return null;

    }

    @Override
    public List<CivDeudas> listarDeudasByTipo(Session session , int tipoDeudas) throws Exception {
        String sql = "SELECT * FROM CIV_DEUDAS WHERE DEU_TIPDEU_FK=:tipoDeuda";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(CivDeudas.class);
        query.setBigDecimal("tipoDeuda", new BigDecimal(tipoDeudas));
        if (query.list().size() > 0) {
            return query.list();
        }
        return null;
    }

    @Override
    public List<CivDeudas> listarDeudasByFechaAdquisicion(Session session, Date fechaInicial, Date fechaFinal) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String sql = "SELECT * FROM CIV_DEUDAS WHERE TO_DATE(TO_CHAR(DEU_FECHAPROCESO,'DD/MM/YYYY'),'DD/MM/YYYY') BETWEEN :fechaInicial AND :fechaFinal";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(CivDeudas.class);
        query.setDate("fechaInicial", format.parse(format.format(fechaInicial)));
        query.setDate("fechaFinal", format.parse(format.format(fechaFinal)));
        if (query.list().size() > 0) {
            return query.list();
        }
        return null;
    }

    @Override
    public long countDeudas(long idPlanTrabajo) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "Select count(*) from CivDeudas where civPlanTrabajos.platraId = :idPlanTrabajo and civEstadoDeudas.estdeuId = 1";
        Query query = session.createQuery(hql);
        query.setParameter("idPlanTrabajo", new BigDecimal(idPlanTrabajo));
        long cantidad = (long) query.list().get(0);
        session.close();
        return cantidad;
    }

    @Override
    public List<CivDeudas> listarDeudasByPlanTrabajo(Session session, int idPlanTrabajo) throws Exception {
        String hql = "FROM CivDeudas WHERE civPlanTrabajos.platraId =:idPlanTrabajo";
        Query query = session.createQuery(hql);
        query.setParameter("idPlanTrabajo", new BigDecimal(idPlanTrabajo));
        if (query.list().size() > 0) {
            return query.list();
        }
        return null;
    }

    @Override
    public long countDeudasEtapa(int idPlanTrabajo, int idEtapaTrabajo) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        long cantidad = 0;
        String sql = "SELECT COUNT(M.MOV_DEUDA_FK)\n"
                + "FROM CIV_MOVIMIENTOS M\n"
                + "INNER JOIN CIV_FASES_TRABAJOS FT ON FT.FASTRA_ID = M.MOV_FASTRA_FK\n"
                + "INNER JOIN CIV_ETAPAS_TRABAJOS ET ON ET.ETATRA_ID = FT.FASTRA_ETATRA_FK AND ET.ETATRA_ID = :idEtapaTrabajo\n"
                + "INNER JOIN CIV_PLAN_TRABAJOS PT ON PT.PLATRA_ID = ET.ETATRA_PLATRA_FK AND PT.PLATRA_ID = :idPlanTrabajo";
        SQLQuery query = session.createSQLQuery(sql);
        query.setInteger("idEtapaTrabajo", idEtapaTrabajo);
        query.setInteger("idPlanTrabajo", idPlanTrabajo);
        if (query.list().size() > 0) {
            cantidad = Long.parseLong(query.list().get(0).toString());
        }
        session.close();
        return cantidad;
    }

    @Override
    public long countDeudasEtapaFases(int idPlanTrabajo, int idEtapaTrabajo, int idFaseTrabajo) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        long cantidad = 0;
        String sql = "SELECT COUNT(M.MOV_DEUDA_FK)\n"
                + "FROM CIV_MOVIMIENTOS M\n"
                + "INNER JOIN CIV_FASES_TRABAJOS FT ON FT.FASTRA_ID = M.MOV_FASTRA_FK AND FT.FASTRA_ID = :idFaseTrabajo\n"
                + "INNER JOIN CIV_ETAPAS_TRABAJOS ET ON ET.ETATRA_ID = FT.FASTRA_ETATRA_FK AND ET.ETATRA_ID = :idEtapaTrabajo\n"
                + "INNER JOIN CIV_PLAN_TRABAJOS PT ON PT.PLATRA_ID = ET.ETATRA_PLATRA_FK AND PT.PLATRA_ID = :idPlanTrabajo";
        SQLQuery query = session.createSQLQuery(sql);
        query.setInteger("idFaseTrabajo", idFaseTrabajo);
        query.setInteger("idEtapaTrabajo", idEtapaTrabajo);
        query.setInteger("idPlanTrabajo", idPlanTrabajo);
        if (query.list().size() > 0) {
            cantidad = Long.parseLong(query.list().get(0).toString());
        }
        session.close();
        return cantidad;
    }

    @Override
    public List<CivDeudas> listarDeudasByFechaDeuda(Session session, Date fechaInicial, Date fechaFinal) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String sql = "SELECT * FROM CIV_DEUDAS WHERE TO_DATE(TO_CHAR(DEU_FECHADEUDA,'DD/MM/YYYY'),'DD/MM/YYYY') BETWEEN :fechaInicial AND :fechaFinal";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(CivDeudas.class);
        query.setDate("fechaInicial", format.parse(format.format(fechaInicial)));
        query.setDate("fechaFinal", format.parse(format.format(fechaFinal)));
        if (query.list().size() > 0) {
            return query.list();
        }
        return null;
    }

    @Override
    public CivDeudas getDeudaByReferenciaUnica(Session session, long idReferencia) throws Exception {
        String hql = "from CivDeudas where deuRefUnica = :idReferenciaUnica";
        Query query = session.createQuery(hql);
        query.setParameter("idReferenciaUnica", BigDecimal.valueOf(idReferencia));
        if (query.uniqueResult() != null) {
            return (CivDeudas) query.uniqueResult();
        }

        return null;

    }

}
