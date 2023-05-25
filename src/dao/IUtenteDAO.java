package dao;

import java.time.LocalDate;
import java.util.List;

import model.Utente;

public interface IUtenteDAO {

	public void saveOrUpdateUtente(Utente u);

	public Utente cercaUtentePerId(long id);

	public void eliminaUtente(Utente u);

	public void checkUtentePerId(long id, LocalDate dataOggi);

	public List<Utente> showAllUtenti();

}
