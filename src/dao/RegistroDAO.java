package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.RegistroStatoMezzi;
import utils.JpaUtil;

public class RegistroDAO implements IRegistroStatoMezziDAO {

	@Override
	public void saveRegistro(RegistroStatoMezzi r) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(r);
			em.getTransaction().commit();
			System.out.println("Riga del registro salvato nel DB!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			System.out.println("Errore su salvataggio della riga del registro" + e);
		} finally {
			em.close();
		}
	}

	@Override
	public void updateRegistro(RegistroStatoMezzi r) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(r);
			em.getTransaction().commit();
			System.out.println("registro modificato nel DB!!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("ERRORE registro NON modificato nel DB!!");
		} finally {
			em.close();
		}
	}

	@Override
	public void deleteRegistro(long id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			Query q = em.createQuery("SELECT r FROM RegistroStatoMezzi r WHERE r.id = :parametro_id");
			List <RegistroStatoMezzi> rs = q.setParameter("parametro_id", id).getResultList();
			em.getTransaction().begin();
			em.remove(rs.get(0));
			em.getTransaction().commit();
			System.out.println("Registro eliminato nel DB!!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("ERRORE! Registro NON eliminato nel DB!!" + e);
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public List<RegistroStatoMezzi> showAllRegistro() {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			List<RegistroStatoMezzi> rs = new ArrayList<RegistroStatoMezzi>();
			Query q = em.createQuery("SELECT s FROM RegistroStatoMezzi s");
			rs = q.getResultList();
			return rs;
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("ERRORE impossibile recuperare la lista dal db!");
		} finally {
			em.close();
		}
		return null;
	}
	
	@Override
	public RegistroStatoMezzi trovaRegistroStatoMezzi(long id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			Query q = em.createQuery("SELECT s FROM RegistroStatoMezzi s WHERE s.id = :parametro_id");
			//RegistroStatoMezzi rs = (RegistroStatoMezzi) q.setParameter("parametro_id", id).getSingleResult();
			List <RegistroStatoMezzi> rs = q.setParameter("parametro_id", id).getResultList();
			return rs.get(0);
		} catch (Exception e) {
			System.out.println("Errore su lettura del Registro!" + e);
		} finally {
			em.close();
		}
		return null;
	}
}
