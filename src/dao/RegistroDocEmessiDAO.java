package dao;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.PuntoEmissione;
import model.RegistroDocEmessi;
import utils.JpaUtil;

public class RegistroDocEmessiDAO implements IRegistoDocEmessiDAO{

    @Override
	public void saveOrUpdateRDE( RegistroDocEmessi b) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(em.merge(b));
			em.getTransaction().commit();
			System.out.println("Riga registro documenti emessi salvato nel DB!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			System.out.println("Errore su salvataggio della riga del registro documenti emessi!" + e);
		}finally {
			em.close();
		}
	}

    @Override
    public RegistroDocEmessi trovaRDE(long id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			RegistroDocEmessi c = em.find(RegistroDocEmessi.class, id);
			em.getTransaction().commit();
			return c;
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Errore nella ricerca della riga del registro documenti emessi!" + e);
		} finally {
			em.close();
		}
		return null;
	}


    // @Override
    // public void deleteRDE(RegistroDocEmessi m) {
	// 	EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
	// 	try {
	// 		em.getTransaction().begin();
	// 		em.remove(m);
	// 		em.getTransaction().commit();
	// 		System.out.println("RegistroDocEmessi eliminato nel DB!!");
	// 	} catch (Exception e) {
	// 		em.getTransaction().rollback();
	// 		System.out.println("ERRORE RegistroDocEmessi NON eliminato nel DB!!");
	// 	} finally {
	// 		em.close();
	// 	}
	// }
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
