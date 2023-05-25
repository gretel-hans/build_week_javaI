package dao;

import javax.persistence.EntityManager;

import model.Abbonamento;
import model.Biglietto;
import utils.JpaUtil;

public class DocumentoDAO implements IDocumentoDAO{

	@Override
	public void salvaBiglietto(Biglietto b) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(b);
			em.getTransaction().commit();
			System.out.println("Biglietto salvato nel DB!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			System.out.println("Errore su salvataggio del Biglietto!" + e);
		}finally {
			em.close();
		}
	}

	@Override
	public Biglietto cercaBigliettoPerId(long id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			Biglietto c = em.find(Biglietto.class, id);
			em.getTransaction().commit();
			return c;
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Errore nella ricerca del biglietto!");
		} finally {
			em.close();
		}
		return null;
	}

	@Override
	public void modificaBiglietto(Biglietto b) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(b);
			em.getTransaction().commit();
			System.out.println("Biglietto modificato nel DB!!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("ERRORE biglietto NON modificato nel DB!!");
		} finally {
			em.close();
		}
	}

	@Override
	public void eliminaBiglietto(Biglietto b) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.remove(b);
			em.getTransaction().commit();
			System.out.println("Biglietto eliminato nel DB!!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("ERRORE biglietto NON eliminato nel DB!!");
		} finally {
			em.close();
		}
	}

	@Override
	public void salvaAbbonamento(Abbonamento a) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(a);
			em.getTransaction().commit();
			System.out.println("Abbonamento salvato nel DB!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			System.out.println("Errore su salvataggio del Abbonamento!" + e);
		}finally {
			em.close();
		}
	}

}
