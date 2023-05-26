package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import dao.DocumentoDAO;
import dao.MezzoDAO;
import dao.RegistroBigliettiVidimatiDAO;
import dao.RegistroDocEmessiDAO;
import dao.RegistroStatoMezziDAO;
import dao.RegistroTratteDAO;
import dao.TesseraDAO;
import dao.UtenteDAO;
import enums.StatoMezzo;
import model.Abbonamento.durata;
import model.Abbonamento;
import model.Autobus;
import model.Biglietto;
import model.DistributoreAutomatico;
import model.DocumentoEmesso;
import model.Mezzo;
import model.PuntoEmissione;
import model.RegistroStatoMezzi;
import model.RivenditoreAutorizzato;
import model.Tessera;
import model.Tram;
import model.Tratta;
import model.Utente;
import utils.JpaUtil;

public class MainProject {

	static EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

	public static void main(String[] args) {
		
		List<Utente> lt = new ArrayList<Utente>();
		
		lt.add(new Utente(new Tessera(LocalDate.now()), "Vincenzo", "De Vito"));
		lt.add(new Utente(new Tessera(LocalDate.now()), "Hansel", "Gretel"));
		lt.add(new Utente(new Tessera(LocalDate.now()), "Gabriele", "Pagliaricci"));
		lt.add(new Utente(new Tessera(LocalDate.now()), "Tiberiu", "Cujba"));


		lt.forEach(u -> u.saveOrUpdateUtente(u));

		TesseraDAO tesserad=new TesseraDAO();
		//System.out.println(tesserad.trovaTessera(3));
		//tesserad.rinnovaTessera(tesserad.trovaTessera(1));

		UtenteDAO utented=new UtenteDAO();
		//System.out.println(utented.cercaUtentePerId(2));
		//utented.checkUtentePerId(1, LocalDate.of(2025,05,18));
		//utented.eliminaUtente(utented.cercaUtentePerId(1));
        //utented.showAllUtenti();

		List<PuntoEmissione> lpe = new ArrayList<PuntoEmissione>();

		lpe.add(new DistributoreAutomatico("Via Verdi 14",false));
		lpe.add(new DistributoreAutomatico("Via Gialli 14"));
		lpe.add(new DistributoreAutomatico("Via Io 14"));
		lpe.add(new DistributoreAutomatico("Via Tu 14"));
		lpe.add(new RivenditoreAutorizzato("Via Giacomino 14"));
		lpe.add(new RivenditoreAutorizzato("Via Neri 14"));

		//lpe.forEach(l -> l.saveOrUpdatePuntoVendita(l));
		//System.out.println(lpe.get(0).cercaPEperId(2));
		
		
		List<DocumentoEmesso> de = new ArrayList<DocumentoEmesso>();
		//List<DocumentoEmesso> de2 = new ArrayList<DocumentoEmesso>();

		//lpe.get(0).emettiBiglietto(LocalDate.of(2023, 5, 1));
		//lpe.get(0).emettiBiglietto(LocalDate.of(2023, 5, 2));
		//lpe.get(0).emettiBiglietto(LocalDate.of(2023, 5, 3));
		//lpe.get(0).emettiAbbonamento(utented.cercaUtentePerId(1), durata.SETTIMANALE);
		//lpe.get(0).emettiAbbonamento(utented.cercaUtentePerId(2), durata.SETTIMANALE);
		
		// de.add(null);
		// lpe.get(1).emettiBiglietto(LocalDate.of(2023, 5,4));
		// lpe.get(1).emettiBiglietto(LocalDate.of(2023, 5,5));
		// lpe.get(1).emettiBiglietto(LocalDate.of(2023, 5,6));

		// lpe.get(1).emettiAbbonamento(lt.get(0), durata.SETTIMANALE);
		// lpe.get(1).emettiAbbonamento(lt.get(2), durata.MENSILE);
		// lpe.get(1).emettiAbbonamento(lt.get(3), durata.SETTIMANALE);
/* 
		List<Mezzo> lm = new ArrayList<Mezzo>();

		lm.add(new Autobus());*/

		// List<PuntoEmissione> lpe = new ArrayList<PuntoEmissione>();

		// lpe.add(new DistributoreAutomatico("Via Verdi 14"));
		// lpe.add(new DistributoreAutomatico("Via Gialli 14"));
		// lpe.add(new DistributoreAutomatico("Via Io 14"));
		// lpe.add(new DistributoreAutomatico("Via Tu 14"));
		// lpe.add(new DistributoreAutomatico("Via Noi 14"));
		// lpe.add(new DistributoreAutomatico("Via Voi 14"));

	//	lpe.forEach(l -> l.salvaPuntoVendita(l));


		List<Mezzo> lm = new ArrayList<Mezzo>();
		
		Mezzo mezzoAutobus= new Autobus("Mercedes bus");
		mezzoAutobus.saveOrUpdateMezzo(mezzoAutobus);
		Mezzo autobus=mezzoAutobus.trovaMezzo(1);

		PuntoEmissione puntoEmi=lpe.get(0);
		
		autobus.timbraDocumentoEmesso(puntoEmi.emettiBiglietto(),LocalDate.now());
		autobus.timbraDocumentoEmesso(puntoEmi.emettiBiglietto(),LocalDate.of(2023,04,21));
		autobus.timbraDocumentoEmesso(puntoEmi.emettiAbbonamento( new Utente(new Tessera(LocalDate.now()), "Mario", "Luigi"), durata.SETTIMANALE), LocalDate.of(2023,05,27));

		DocumentoDAO documentoDao=new DocumentoDAO();
		//System.out.println(documentoDao.cercaBigliettoPerId(1));

		RegistroDocEmessiDAO regdocED= new RegistroDocEmessiDAO();
		//regdocED.cercaBigliettiInPerioDiTempoDaPuntoVendita(LocalDate.of(2023,05,23), LocalDate.of(2023,05,29), puntoEmi.cercaPEperId(1));
		//System.out.println(regdocED.trovaRDE(1)); 
		
		//lm.get(0).getNumeriBigliettiVidimatiSuUnMezzo(1);
		//lm.get(0).getNumeriBigliettiVidimatiTraDueDate(LocalDate.of(2023,05,26), LocalDate.of(2023,05,27));
        RegistroBigliettiVidimatiDAO registrobvDao= new RegistroBigliettiVidimatiDAO();

//registrobvDao.cercaByIdRegistroBigliettoVidimato(2);
//registrobvDao.deleteRigaRegistroBigliettoVidimato(1);

		List<Mezzo> listaMezzi=new ArrayList<Mezzo>();
		listaMezzi.add(new Autobus("Mercedes"));
		listaMezzi.add(new Autobus("Autobus 2"));
		listaMezzi.add(new Autobus("Fiat"));
		listaMezzi.add(new Tram("Tram tram"));
		listaMezzi.add(new Tram("Iveco"));
		listaMezzi.add(new Tram("Tram mercedes"));

		listaMezzi.forEach(m->m.saveOrUpdateMezzo(m));
		System.out.println(listaMezzi.get(0).trovaMezzo(1));
		listaMezzi.get(0).getNumeriBigliettiVidimatiSuUnMezzo(1);
		listaMezzi.get(0).getNumeriBigliettiVidimatiTraDueDate(LocalDate.of(2023,03,27), LocalDate.of(2023,07,28));


		List<RegistroStatoMezzi> listaRegistroStatoMezzi= new ArrayList<RegistroStatoMezzi>();
		RegistroStatoMezziDAO registStatoMeDao = new RegistroStatoMezziDAO();
		listaRegistroStatoMezzi.add (new RegistroStatoMezzi(listaMezzi.get(0).trovaMezzo(1), StatoMezzo.IN_SERVIZIO, LocalDate.now()));
		listaRegistroStatoMezzi.add (new RegistroStatoMezzi(listaMezzi.get(0).trovaMezzo(4), StatoMezzo.MANUTENZIONE, LocalDate.of(2023, 05, 10)));
		listaRegistroStatoMezzi.add (new RegistroStatoMezzi(listaMezzi.get(0).trovaMezzo(2), StatoMezzo.IN_SERVIZIO, LocalDate.of(2023, 07, 18)));
		listaRegistroStatoMezzi.add (new RegistroStatoMezzi(listaMezzi.get(0).trovaMezzo(3), StatoMezzo.DISMESSO, LocalDate.of(2023, 05, 12)));
		listaRegistroStatoMezzi.forEach(r->registStatoMeDao.saveOrUpdateRegistroStato(r));
		//registStatoMeDao.showAllRegistroStato();
		//System.out.println(registStatoMeDao.trovaRegistroStatoMezzi(1));
		//registStatoMeDao.deleteRegistroStato(1);

		List <Tratta> listaTratte=new ArrayList<Tratta>();

		listaTratte.add(new Tratta("Via roma", "Via verdi", 1.35));
		listaTratte.add(new Tratta("Via neri", "Via gian", 0.53));
		listaTratte.add(new Tratta("Via silvio", "Via cassala", 1.10));
		listaTratte.add(new Tratta("Via panciroli", "Via marco", 1.27));
		listaTratte.forEach(t->t.saveOrUpdateTratta(t));

		listaMezzi.get(0).trovaMezzo(1).percorriTratta(listaTratte.get(0));
		listaMezzi.get(0).trovaMezzo(3).percorriTratta(listaTratte.get(2));
		listaMezzi.get(0).trovaMezzo(4).percorriTratta(listaTratte.get(3));

		RegistroTratteDAO registDao= new RegistroTratteDAO();
		System.out.println(registDao.trovaRegistroTratta(1));
		registDao.showAllRegistroTratte();

		//listaTratte.get(0).showAllTratta();
		//System.out.println(listaTratte.get(0).showTratta(3));




	}

	

}
