package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import model.Abbonamento.durata;
import model.Autobus;
import model.Biglietto;
import model.DistributoreAutomatico;
import model.DocumentoEmesso;
import model.Mezzo;
import model.PuntoEmissione;
import model.Tessera;
import model.Utente;
import utils.JpaUtil;

public class MainProject {

	static EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

	public static void main(String[] args) {
		/* 
		List<Utente> lt = new ArrayList<Utente>();
		
		lt.add(new Utente(new Tessera(LocalDate.now()), "Vincenzo", "De Vito"));
		lt.add(new Utente(new Tessera(LocalDate.now()), "Hansel", "Gretel"));
		lt.add(new Utente(new Tessera(LocalDate.now()), "Gabriele", "Pagliaricci"));
		lt.add(new Utente(new Tessera(LocalDate.now()), "Tiberiu", "Cujba"));


		lt.forEach(u -> u.saveOrUpdateUtente(u));


		List<PuntoEmissione> lpe = new ArrayList<PuntoEmissione>();

		lpe.add(new DistributoreAutomatico("Via Verdi 14"));
		lpe.add(new DistributoreAutomatico("Via Gialli 14"));
		lpe.add(new DistributoreAutomatico("Via Io 14"));
		lpe.add(new DistributoreAutomatico("Via Tu 14"));
		lpe.add(new DistributoreAutomatico("Via Noi 14"));
		lpe.add(new DistributoreAutomatico("Via Voi 14"));

		lpe.forEach(l -> l.salvaPuntoVendita(l));
		
		List<DocumentoEmesso> de = new ArrayList<DocumentoEmesso>();
		List<DocumentoEmesso> de2 = new ArrayList<DocumentoEmesso>();

		lpe.get(0).emettiBiglietto(LocalDate.of(2023, 5, 1));
		lpe.get(0).emettiBiglietto(LocalDate.of(2023, 5, 2));
		lpe.get(0).emettiBiglietto(LocalDate.of(2023, 5, 3));
		
		de.add(null);
		lpe.get(1).emettiBiglietto(LocalDate.of(2023, 5,4));
		lpe.get(1).emettiBiglietto(LocalDate.of(2023, 5,5));
		lpe.get(1).emettiBiglietto(LocalDate.of(2023, 5,6));

		lpe.get(1).emettiAbbonamento(lt.get(0), durata.SETTIMANALE);
		lpe.get(1).emettiAbbonamento(lt.get(2), durata.MENSILE);
		lpe.get(1).emettiAbbonamento(lt.get(3), durata.SETTIMANALE);

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
		lm.add(new Autobus("Mercedes bus"));
		lm.get(0).timbraBiglietto( new Biglietto(new DistributoreAutomatico("Via Io 14"), LocalDate.now()),LocalDate.now());
		System.out.println();
		lm.get(0).getNumeriBigliettiVidimatiSuUnMezzo(1);
		lm.get(0).getNumeriBigliettiVidimatiTraDueDate(LocalDate.of(2023,05,26), LocalDate.of(2023,05,27));

	}

	

}
