package dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Utente;
import utils.JpaUtil;

public class UtenteDAO implements IUtenteDAO {

	public void checkUtentePerId(long id, LocalDate dataOggi) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			System.out.println("Avvio controllo validita' abbonamento...");
			em.getTransaction().begin();
			Utente c = em.find(Utente.class, id);
			LocalDate scadenza = c.getTessera().getData_scadenza();
			int verificaValidita = dataOggi.compareTo(scadenza);
			if (verificaValidita < 0) {
				System.out.println("L'abbonamento e' valido.");
			} else {
				System.out.println("L'abbonamento non e' valido!");
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Errore nella ricerca!");
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public void saveOrUpdateUtente(Utente u) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(em.merge(u));
			em.getTransaction().commit();
			System.out.println("Utente salvato nel DB!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			System.out.println("Errore su salvataggio del Utente!" + e);
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public Utente cercaUtentePerId(long id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			Utente u = em.find(Utente.class, id);
			em.getTransaction().commit();
			return u;
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Errore nella ricerca del Utente!");
			e.printStackTrace();
		} finally {
			em.close();
		}
		return null;
	}

	@Override
	public void eliminaUtente(Utente u) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.remove(em.merge(u));
			em.getTransaction().commit();
			System.out.println("Utente eliminato nel DB!!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("ERRORE! Utente NON eliminato nel DB!!" + e);
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public List<Utente> showAllUtenti() {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			List<Utente> rs = new ArrayList<Utente>();
			Query q = em.createQuery("SELECT u FROM Utente u");
			rs = q.getResultList();
			return rs;
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("ERRORE impossibile recuperare la lista degli Utenti dal DB!");
		} finally {
			em.close();
		}
		return null;
	}
}
