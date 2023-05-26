package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import dao.DocumentoDAO;
import dao.MezzoDAO;
import dao.RegistroDocEmessiDAO;
import dao.RegistroStatoMezziDAO;
import dao.TrattaDAO;
import model.Abbonamento.durata;
import model.Autobus;
import model.DistributoreAutomatico;
import model.Mezzo;
import model.PuntoEmissione;
import model.RivenditoreAutorizzato;
import model.Tessera;
import model.Tram;
import model.Tratta;
import model.Utente;
import utils.JpaUtil;

public class MainProject {

	static EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

	public static void main(String[] args) {

		//Istanziamento delle entità 
		
		//Utenti
		List<Utente> listaUtenti = new ArrayList<Utente>();
		
		listaUtenti.add(new Utente(new Tessera(LocalDate.now()), "Vincenzo", "De Vito"));
		listaUtenti.add(new Utente(new Tessera(LocalDate.now()), "Hansel", "Gretel"));
		listaUtenti.add(new Utente(new Tessera(LocalDate.now()), "Gabriele", "Pagliaricci"));
		listaUtenti.add(new Utente(new Tessera(LocalDate.now()), "Tiberiu", "Cujba"));
		listaUtenti.add(new Utente(new Tessera(LocalDate.now()), "Chiara", "Andria"));
		listaUtenti.add(new Utente(new Tessera(LocalDate.now()), "Dekanis", "Hani"));
		//foreach di saveUpdate
		listaUtenti.forEach(u -> u.saveOrUpdateUtente(u));

		//Punti di emissione
		List<PuntoEmissione> listaPuntiEmissione = new ArrayList<PuntoEmissione>();
		//Distributori automatici
		listaPuntiEmissione.add(new DistributoreAutomatico("Via Verdi 14",false));
		listaPuntiEmissione.add(new DistributoreAutomatico("Via Gialli 14"));
		listaPuntiEmissione.add(new DistributoreAutomatico("Via Bianchi 14"));
		listaPuntiEmissione.add(new DistributoreAutomatico("Via Rossi 14"));
		//Rivenditori autorizzati
		listaPuntiEmissione.add(new RivenditoreAutorizzato("Via Giacomino 14"));
		listaPuntiEmissione.add(new RivenditoreAutorizzato("Via Neri 14"));
		//foreach di saveUpdate
		listaPuntiEmissione.forEach(l -> l.saveOrUpdatePuntoVendita(l));
	
		//Emissione biglietti ed abbonamenti
		listaPuntiEmissione.get(0).cercaPEperId(1).emettiBiglietto();
		listaPuntiEmissione.get(0).cercaPEperId(1).emettiAbbonamento(listaUtenti.get(0).cercaUtentePerId(1), durata.SETTIMANALE);

		listaPuntiEmissione.get(1).cercaPEperId(2).emettiBiglietto(LocalDate.of(2023, 5,1));
		listaPuntiEmissione.get(1).cercaPEperId(2).emettiBiglietto(LocalDate.of(2023, 5,2));
		listaPuntiEmissione.get(1).cercaPEperId(2).emettiBiglietto(LocalDate.of(2023, 5,3));
		listaPuntiEmissione.get(1).cercaPEperId(2).emettiBiglietto(LocalDate.of(2023, 5,4));
		listaPuntiEmissione.get(1).cercaPEperId(2).emettiBiglietto(LocalDate.of(2023, 5,5));
		listaPuntiEmissione.get(1).cercaPEperId(2).emettiBiglietto(LocalDate.of(2023, 5,6));

		RegistroDocEmessiDAO ped = new RegistroDocEmessiDAO();
		ped.cercaBigliettiInPerioDiTempoDaPuntoVendita(LocalDate.of(2023, 5,2), LocalDate.of(2023, 5,5), listaPuntiEmissione.get(1).cercaPEperId(2));

		//Tratte
		List<Tratta> listaTratte = new ArrayList<Tratta>();
		listaTratte.add(new Tratta("Via Bianchi 14", "Via Neri 14", 1.20));
		listaTratte.add(new Tratta("Via Gialli 14", "Via Giacomino 14", 0.30));
		listaTratte.add(new Tratta("Via Verdi 14", "Via Rossi 14", 1.00));
		//foreach di saveUpdate		
		listaTratte.forEach(t->t.saveOrUpdateTratta(t));

		//Mezzi
		List<Mezzo> listaMezzi=new ArrayList<Mezzo>();
		//Autobus
		listaMezzi.add(new Autobus("Mercedes"));
		listaMezzi.add(new Autobus("Autobus 2"));
		listaMezzi.add(new Autobus("Fiat"));
		// Tram
		listaMezzi.add(new Tram("Tram tram"));
		listaMezzi.add(new Tram("Iveco"));
		listaMezzi.add(new Tram("Tram mercedes"));
		//foreach di saveUpdate		
		listaMezzi.forEach(m->m.saveOrUpdateMezzo(m));

		//Vidimazione biglietti/abbonamenti
		MezzoDAO md = new MezzoDAO() ;
		DocumentoDAO dd = new DocumentoDAO();
		Autobus autobus = (Autobus) md.trovaMezzo(1);
		autobus.timbraDocumentoEmesso(dd.cercaBigliettoPerId(3), LocalDate.of(2023, 5,1));
		autobus.timbraDocumentoEmesso(dd.cercaBigliettoPerId(4), LocalDate.of(2023, 5,2));
		autobus.timbraDocumentoEmesso(dd.cercaBigliettoPerId(5), LocalDate.of(2023, 5,3));
		autobus.timbraDocumentoEmesso(dd.cercaBigliettoPerId(6), LocalDate.of(2023, 5,4));
		autobus.timbraDocumentoEmesso(dd.cercaBigliettoPerId(7), LocalDate.of(2023, 5,5));
		autobus.timbraDocumentoEmesso(dd.cercaBigliettoPerId(8), LocalDate.of(2023, 5,6));
		md.getNumeriBigliettiVidimatiSuUnMezzo(autobus.getId());
		md.getNumeriBigliettiVidimatiTraDueDate(LocalDate.of(2023,5,2), LocalDate.of(2023,5,5));

		//I mezzi percorrono le tratte
		TrattaDAO td = new TrattaDAO();
		Tram tram1 = (Tram) md.trovaMezzo(4);
		Tram tram2 = (Tram) md.trovaMezzo(5);
		Tram tram3 = (Tram) md.trovaMezzo(6);
		tram1.percorriTratta(td.showTratta(1));
		tram2.percorriTratta(td.showTratta(2));
		tram3.percorriTratta(td.showTratta(3));

		//registro stato mezzi
		autobus.vaiInManutenzione(LocalDate.of(2023, 6, 1));
		tram1.vaiInManutenzione(LocalDate.of(2023, 6, 2));
		autobus.tornaInServizio(LocalDate.of(2023, 6, 8));
		tram1.tornaInServizio(LocalDate.of(2023, 6, 9));
		
		RegistroStatoMezziDAO rstmd = new RegistroStatoMezziDAO();
		rstmd.showAllRegistroStato();


	}
}
