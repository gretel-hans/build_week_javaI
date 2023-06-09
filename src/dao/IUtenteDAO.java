package dao;

import java.time.LocalDate;

import model.Utente;

public interface IUtenteDAO {

	public void saveOrUpdateUtente(Utente u);

	public Utente cercaUtentePerId(long id);

	public void eliminaUtente(Utente u);

	public void checkUtentePerId(long id, LocalDate dataOggi);

	public void showAllUtenti();

}
