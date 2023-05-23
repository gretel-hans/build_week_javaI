package dao;

import java.time.LocalDate;

public interface IUtenteDAO {
	
	public void checkPerId(long id, LocalDate dataOggi);
	
}
