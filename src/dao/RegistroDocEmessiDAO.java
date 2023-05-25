package dao;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.PuntoEmissione;
import model.RegistroDocEmessi;
import utils.JpaUtil;

public class RegistroDocEmessiDAO implements IRegistoDocEmessiDAO{

    @Override
	public void salvaRDE( RegistroDocEmessi b) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(b);
			em.getTransaction().commit();
			System.out.println("Registro salvato nel DB!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			System.out.println("Errore su salvataggio del registro!" + e);
		}finally {
			em.close();
		}
	}

    @Override
    public void trovaRDE(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'trovaRDE'");
    }

    @Override
    public void updateRDE(RegistroDocEmessi m) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateRDE'");
    }

    @Override
    public void deleteRDE(RegistroDocEmessi m) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteRDE'");
    }
    @Override
    public Long cercaBigliettiInPerioDiTempoDaPuntoVendita(LocalDate inizio, LocalDate fine, PuntoEmissione pe) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			Long rs;
			Query q = em.createQuery("SELECT COUNT (r) FROM RegistroDocEmessi r WHERE r.doc_emesso.dataEmissione >= :inizio AND  r.doc_emesso.dataEmissione <= :fine");
            q.setParameter("inizio", inizio);
            q.setParameter("fine", fine);
			rs = (Long) q.getSingleResult();
			System.out.println("Il punto di emissione " + pe.getIndirizzo() + " ha stampato " + rs + " biglietti dal " + inizio + " al " + fine + ".");
			return rs;
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("ERRORE impossibile recuperare la lista dal db!" + e);
		} finally {
			em.close();
		}
		return null;
    };

}
