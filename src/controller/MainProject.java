package controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import ch.qos.logback.core.status.Status;
import dao.BigliettoDAO;
import dao.MezzoDAO;
import enums.StatoMezzo;
import model.Abbonamento;
import model.DistributoreAutomatico;
import model.Mezzo;
import model.PuntoEmissione;
import model.RivenditoreAutorizzato;
import model.Tessera;
import model.Utente;
import model.Abbonamento.durata;
import model.Biglietto;
import utils.JpaUtil;


public class MainProject {
	
	static EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
	public static void main(String[] args) {
		
		PuntoEmissione disAut = new DistributoreAutomatico("Via Verdi 51");
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
		em.getTransaction().begin();
		em.persist(disAut);
		em.persist(rivAut);
		em.persist(tessera);
		em.persist(mario);
		em.persist(abbonamento);
		em.getTransaction().commit();
		em.close();
	}
	
	static public void timbraBiglieto(Biglietto b) {
		EntityManager em= JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			b.setValidita(false);
			em.merge(b);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Errore nella vidimazione del biglietto!" + e);
		}finally {
	    em.close();
		}
	}
	
	static public Biglietto cercaPerId(long id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			Biglietto c =  em.find(Biglietto.class, id);
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
			PuntoEmissione c =  em.find(PuntoEmissione.class, id);
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

}
