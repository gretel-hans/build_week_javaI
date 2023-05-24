package dao;

import model.Biglietto;

public interface IBigliettoDAO {
	
	public void salvaBiglietto(Biglietto b);
	public Biglietto cercaBigliettoPerId(long id);
	public void modificaBiglietto(Biglietto b);
	public void eliminaBiglietto(Biglietto b);
}
