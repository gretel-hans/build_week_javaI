package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import dao.DocumentoDAO;
import dao.MezzoDAO;
import dao.RegistroStatoMezziDAO;
import dao.RegistroStatoMezziDAO;
import dao.RegistroTratteDAO;
import dao.TesseraDAO;
import dao.TrattaDAO;
import enums.StatoMezzo;
import model.Abbonamento.durata;
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
import model.Utente;
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
		DocumentoDAO bd = new DocumentoDAO();
		bd.salvaBiglietto(b1);
		bd.salvaBiglietto(b2);
		Biglietto br = bd.cercaBigliettoPerId(1);
		Biglietto br2 = bd.cercaBigliettoPerId(2);
		// System.out.println(br);
		// System.out.println(br2);
		List<Biglietto> listaBiglietti = new ArrayList<Biglietto>();
		listaBiglietti.add(br);
		listaBiglietti.add(br2);

		Mezzo m1 = new Autobus(listaBiglietti);
		Mezzo m2 = new Tram(listaBiglietti);
		MezzoDAO md = new MezzoDAO();
		RegistroStatoMezziDAO rd = new RegistroStatoMezziDAO();
		md.salvaMezzo(m1);
		md.salvaMezzo(m2);
		// timbraBiglieto(br);
		Mezzo mezzo = md.trovaMezzo(1);
		Mezzo mezzo1 = md.trovaMezzo(2);

		/* Crud Registro Stato Mezzi */
		RegistroStatoMezzi rsm = new RegistroStatoMezzi(mezzo,
				StatoMezzo.IN_SERVIZIO, LocalDate.now());
		RegistroStatoMezzi rsm1 = new RegistroStatoMezzi(mezzo,
				StatoMezzo.MANUTENZIONE, LocalDate.of(2023, 06, 03));
		rd.saveOrUpdateRegistroStato(rsm);
		rd.saveOrUpdateRegistroStato(rsm1);

		RegistroStatoMezzi rsm2 = new RegistroStatoMezzi(mezzo1,
				StatoMezzo.IN_SERVIZIO, LocalDate.now());
		rd.saveOrUpdateRegistroStato(rsm2);

		RegistroStatoMezzi rsmMod = rd.trovaRegistroStatoMezzi(2);
		rsmMod.setStatoMezzo(StatoMezzo.IN_SERVIZIO);
		rd.saveOrUpdateRegistroStato(rsmMod);

		rd.deleteRegistroStato(1);

		List<RegistroStatoMezzi> listaRegistroStato = rd.showAllRegistroStato();
		listaRegistroStato.forEach(u -> System.out.println(u));

		// rd.saveRegistro(rsm2);
		/* Crud Tratte (senza delete!!) */
		Tratta t1 = new Tratta("Piazza Verdi", "Piazza Rossi", 1.20);
		Tratta t2 = new Tratta("Piazza Marconi", "Piazza Rosalbo", 0.40);
		Tratta t3 = new Tratta("Piazza Martiri", "Piazza Cristo", 1.30);

		TrattaDAO td = new TrattaDAO();
		td.saveOrUpdateTratta(t1);
		td.saveOrUpdateTratta(t2);
		td.saveOrUpdateTratta(t3);

		m1.percorriTratta(t1);

		// td.showAllTratta();
		// td.deleteTratta(2);
		td.showAllTratta();
		// FUNZIONA TUTTO
		// System.out.println(rd.trovaRegistroStatoMezzi(1));
		// rd.deleteRegistro(1);
		// p1.emettiDocumento(true, LocalDate.of(2023, 1, 1));
		// p1.emettiDocumento(true, LocalDate.of(2023, 1, 2));
		// p1.emettiDocumento(true, LocalDate.of(2023, 1, 3));
		// p1.emettiDocumento(true, LocalDate.of(2023, 1,4));
		// p1.emettiDocumento(true, LocalDate.of(2023, 1,5));

		/* Crud Registro Tratte */

		RegistroTratteDAO rtd = new RegistroTratteDAO();
		RegistroTratte rt = new RegistroTratte(t3, mezzo1);
		rtd.saveOrUpdateRegistroTratte(rt);
		rtd.showAllRegistroTratte();
		rtd.deleteRegistroTratte(1);

		Tessera ts1 = new Tessera(LocalDate.now());
		TesseraDAO tsd = new TesseraDAO();
		// tsd.rinnovaTessera(ts1);
		tsd.salvaTessera(ts1);
		Tessera tsr1 = tsd.trovaTessera(1);
		System.out.println(tsr1);
		// tsd.deleteTessera(1);
		tsr1.setData_inizio(LocalDate.of(2023, 5, 25));
		tsd.updateTessera(tsr1);

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
