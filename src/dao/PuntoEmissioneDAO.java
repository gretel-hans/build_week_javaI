package dao;

import javax.persistence.EntityManager;

import model.PuntoEmissione;
import utils.JpaUtil;

public class PuntoEmissioneDAO implements IPuntoEmissioneDAO{
    
    
    public PuntoEmissione cercaPEperId(long id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			PuntoEmissione c = em.find(PuntoEmissione.class, id);
			em.getTransaction().commit();
			return c;
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Errore nella ricerca!");
		} finally {
			em.close();
		}
		return null;
	}

	public void saveOrUpdatePuntoVendita(PuntoEmissione pe) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(em.merge(pe));
			em.getTransaction().commit();
			System.out.println("Elemento " + pe + " salvato nel DB!!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Errore su salvataggio!!");
		} finally {
			em.close();
		}
	}
}
