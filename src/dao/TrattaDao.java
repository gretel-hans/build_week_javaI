package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Tratta;
import utils.JpaUtil;

public class TrattaDao implements ITrattaDAO {

	@Override
	public void saveOrUpdateTratta(Tratta t) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(em.merge(t));
			em.getTransaction().commit();
			System.out.println("Riga del registro salvato nel DB!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			System.out.println("Errore su salvataggio del registro tratte!" + e);
		} finally {
			em.close();
		}
	}

	@Override // MERGE cancella correttamente solo se la tratta non e' stata percorsa,
				// RIVEDERE
	public void deleteTratta(long id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			Query q = em.createQuery("SELECT r FROM Tratta r WHERE r.id = :parametro_id");
			List<Tratta> rs = q.setParameter("parametro_id", id).getResultList();
			em.getTransaction().begin();
			em.remove(rs.get(0));
			em.getTransaction().commit();
			System.out.println("Tratta eliminata nel DB!!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("ERRORE! Tratta NON eliminata nel DB!!" + e);
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public List<Tratta> showAllTratta() {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			List<Tratta> rs = new ArrayList<Tratta>();
			Query q = em.createQuery("SELECT s FROM Tratta s");
			rs = q.getResultList();
			rs.forEach(l -> System.out
					.println(l));
			return rs;
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("ERRORE impossibile recuperare la lista dal db!" + e);
			e.printStackTrace();
		} finally {
			em.close();
		}
		return null;
	}

	@Override
	public Tratta showTratta(long id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			Query q = em.createQuery("SELECT t FROM Tratta t WHERE t.id = :parametro_id");
			List<Tratta> rt = q.setParameter("parametro_id", id).getResultList();
			return rt.get(0);
		} catch (Exception e) {
			System.out.println("Errore su lettura delle Tratte!" + e);
			e.printStackTrace();
		} finally {
			em.close();
		}
		return null;
	}

}
