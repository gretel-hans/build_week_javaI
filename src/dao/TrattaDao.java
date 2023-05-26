package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Tratta;
import utils.JpaUtil;

public class TrattaDAO implements ITrattaDAO {

	@Override
	public void saveOrUpdateTratta(Tratta t) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(em.merge(t));
			em.getTransaction().commit();
			System.out.println("Tratta salvata nel DB!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			System.out.println("Errore su salvataggio della tratta!" + e);
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
			rs.forEach(l -> System.out.println(l));
			return rs;
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("ERRORE impossibile recuperare la lista delle tratte dal db!" + e);
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
			System.out.println("Errore su lettura della Tratta!" + e);
			e.printStackTrace();
		} finally {
			em.close();
		}
		return null;
	}

}
