package dao;

import java.time.LocalDate;

import javax.persistence.EntityManager;

import model.Utente;
import utils.JpaUtil;

public class UtenteDAO implements IUtenteDAO{
	
	public void checkPerId(long id, LocalDate dataOggi) {
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
		} finally {
			em.close();
		}
	}
}
