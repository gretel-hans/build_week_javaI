package controller;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.EntityManager;

import model.Abbonamento;
import model.DistributoreAutomatico;
import model.PuntoEmissione;
import model.RivenditoreAutorizzato;
import model.Tessera;
import model.Utente;
import model.Abbonamento.durata;
import utils.JpaUtil;


public class MainProject {
	
	static EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
	public static void main(String[] args) {
		PuntoEmissione disAut = new DistributoreAutomatico("Via Verdi 51");
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

}
