package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.RegistroTratte;
import utils.JpaUtil;

public class RegistroTratteDAO implements IRegistroTratteDAO {

	@Override
	public void saveOrUpdateRegistroTratte(RegistroTratte r) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(em.merge(r));
			em.getTransaction().commit();
			System.out.println("Riga del Registro Tratte salvato nel DB!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			System.out.println("Errore su salvataggio del Registro Tratte su DB!" + e);
		} finally {
			em.close();
		}
	}

	@Override
	public void deleteRegistroTratte(long id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			Query q = em.createQuery("SELECT rt FROM RegistroTratte rt WHERE rt.id_tratta_percorsa = :id_param");
			List<RegistroTratte> rt = q.setParameter("id_param", id).getResultList();
			em.getTransaction().begin();
			em.remove(rt.get(0));
			em.getTransaction().commit();
			System.out.println("Riga di Registro Tratte con id: " + id + " eliminato dal DB!!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("ERRORE! Riga di Registro Tratte NON eliminata dal DB!!" + e);
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public RegistroTratte trovaRegistroTratta(long id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			Query q = em.createQuery("SELECT rt FROM RegistroTratte rt WHERE rt.id = :parametro_id");
			List<RegistroTratte> rt = q.setParameter("parametro_id", id).getResultList();
			return rt.get(0);
		} catch (Exception e) {
			System.out.println("Errore su lettura del Registro Tratte!" + e);
		} finally {
			em.close();
		}
		return null;
	}

	@Override
	public void showAllRegistroTratte() {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			List<RegistroTratte> rs = new ArrayList<RegistroTratte>();
			Query q = em.createQuery("SELECT rt FROM RegistroTratte rt");
			rs = q.getResultList();
			rs.forEach(r -> System.out.println(r));
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("ERRORE! Impossibile recuperare la lista del Registo Tratte dal DB!");
		} finally {
			em.close();
		}
	}

}
