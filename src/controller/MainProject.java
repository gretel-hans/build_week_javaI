package controller;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import dao.BigliettoDAO;
import dao.MezzoDAO;
import enums.StatoMezzo;
import model.Biglietto;
import model.DistributoreAutomatico;
import model.Mezzo;
import model.PuntoEmissione;
import model.RivenditoreAutorizzato;
import utils.JpaUtil;

public class MainProject {

	static EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

	public static void main(String[] args) {
		
		/*PuntoEmissione disAut = new DistributoreAutomatico("Via Verdi 51");
		Biglietto b1 = new Biglietto(disAut, LocalDate.of(2023, 5, 23));
		BigliettoDAO bd = new BigliettoDAO();
		bd.salvaBiglietto(b1);
		List<Biglietto> bv = new ArrayList<Biglietto>();
		 bv.add(b1);
		
		 //cercaPEperId(1);
		 
		 Biglietto b2 = new Biglietto(disAut, LocalDate.of(2023, 5, 23));
		 bd.salvaBiglietto(b2);
		
		Mezzo m1 = new Mezzo(30, StatoMezzo.SERVIZIO, bv);
		MezzoDAO md = new MezzoDAO();
		//md.salvaMezzo(m1);
		//timbraBiglieto(b1);
	
	PuntoEmissione rivAut = new RivenditoreAutorizzato("Via Rossi 15");
	Tessera tessera = new Tessera(LocalDate.of(2020, 1, 1));
		Utente mario = new Utente(tessera, "Mario", "Rossi");
		Abbonamento abbonamento = new Abbonamento(rivAut, LocalDate.of(2021, 4,6), mario, durata.MENSILE);
		*/
		
		PuntoEmissione disAut = new DistributoreAutomatico("Via Verdi 51");
		PuntoEmissione rivAut = new RivenditoreAutorizzato("Via Rossi 15");
		salvaPuntoVendita(disAut);
		salvaPuntoVendita(rivAut);
		PuntoEmissione p1 = cercaPEperId(1);
		PuntoEmissione p2 = cercaPEperId(2);
		System.out.println(p1);
		System.out.println(p2);
		
		Biglietto b1 = new Biglietto(p1, LocalDate.of(2023, 5, 23));
		Biglietto b2 = new Biglietto(p2, LocalDate.of(2023, 5, 24));
		BigliettoDAO bd = new BigliettoDAO();
		bd.salvaBiglietto(b1);
		bd.salvaBiglietto(b2);
		Biglietto br = cercaPerId(1);
		Biglietto br2 = cercaPerId(2);
		System.out.println(br);
		System.out.println(br2);
		List<Biglietto> listaBiglietti = new ArrayList<Biglietto>();
		listaBiglietti.add(br);
		listaBiglietti.add(br2);
		
		
		Mezzo m1 = new Mezzo(30, StatoMezzo.SERVIZIO, listaBiglietti);
		MezzoDAO md = new MezzoDAO();
		md.salvaMezzo(m1);
		timbraBiglieto(br);
	}

	static public void timbraBiglieto(Biglietto b) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			b.setValidita(false);
			em.merge(b);
			em.getTransaction().commit();
			System.out.println("Biglietto con id " + b.getId() + " timbrato!");
		} catch (Exception e) {
			System.out.println("Errore nella vidimazione del biglietto!" + e);
		} finally {
			em.close();
		}
	}

	static public Biglietto cercaPerId(long id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			Biglietto c = em.find(Biglietto.class, id);
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

	static public PuntoEmissione cercaPEperId(long id) {
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
	
	static public void salvaPuntoVendita(PuntoEmissione pe) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(pe);
			em.getTransaction().commit();
			System.out.println("Elemento " + pe + " salvato nel DB!!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Errore su salvataggio!!");
		}finally {
			em.close();
		}
	}

}
