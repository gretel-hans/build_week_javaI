package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import dao.BigliettoDAO;
import dao.MezzoDAO;
import dao.RegistroDAO;
import dao.RegistroTratteDAO;
import dao.TesseraDAO;
import dao.TrattaDao;
import enums.StatoMezzo;
import model.Autobus;
import model.Biglietto;
import model.DistributoreAutomatico;
import model.Mezzo;
import model.PuntoEmissione;
import model.RegistroStatoMezzi;
import model.RegistroTratte;
import model.RivenditoreAutorizzato;
import model.Tessera;
import model.Tram;
import model.Tratta;
import utils.JpaUtil;

public class MainProject {

	static EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

	public static void main(String[] args) {

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
		Biglietto br = bd.cercaBigliettoPerId(1);
		Biglietto br2 = bd.cercaBigliettoPerId(2);
		System.out.println(br);
		System.out.println(br2);
		List<Biglietto> listaBiglietti = new ArrayList<Biglietto>();
		listaBiglietti.add(br);
		listaBiglietti.add(br2);

		Mezzo m1 = new Autobus(listaBiglietti);
		Mezzo m2 = new Tram(listaBiglietti);
		MezzoDAO md = new MezzoDAO();
		RegistroDAO rd = new RegistroDAO();
		md.salvaMezzo(m1);
		md.salvaMezzo(m2);
	    md.timbraBiglietto(br);
		Mezzo mezzo = md.trovaMezzo(1);
		Mezzo mezzo1 = md.trovaMezzo(2);
		System.out.println(mezzo);
		System.out.println(mezzo1);

		RegistroStatoMezzi rsm = new RegistroStatoMezzi(m1, StatoMezzo.SERVIZIO,
				LocalDate.now());
		
		RegistroStatoMezzi rsm1 = new RegistroStatoMezzi(m2,
				StatoMezzo.MANUTENZIONE, LocalDate.of(2023, 06, 03));
		 rd.saveRegistro(rsm);
		 rd.saveRegistro(rsm1);
/*
		RegistroStatoMezzi rsm2 = new RegistroStatoMezzi(new Autobus(listaBiglietti), StatoMezzo.SERVIZIO,
				LocalDate.now());
		// rd.saveRegistro(rsm2);
*/
		Tratta t1 = new Tratta("Piazza Verdi", "Piazza Rossi", 1.20);
		TrattaDao td = new TrattaDao();
		td.saveTratta(t1);

		m1.percorriTratta(t1);

		Tessera ts1 = new Tessera(LocalDate.now());
		TesseraDAO tsd = new TesseraDAO();
		// tsd.rinnovaTessera(ts1);
		tsd.salvaTessera(ts1);
		Tessera tsr1 = tsd.trovaTessera(1);
		System.out.println(tsr1);
		// tsd.deleteTessera(1);
		tsr1.setData_inizio(LocalDate.of(2023, 5, 25));
		tsd.updateTessera(tsr1);

      // md.deleteMezzo(1);
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
		} finally {
			em.close();
		}
	}

}
