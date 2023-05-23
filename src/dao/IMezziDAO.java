package dao;

import model.Biglietto;
import model.Mezzo;
import model.Tratta;

public interface IMezziDAO {

	public void salvaMezzo(Mezzo m);
	public void percorriTratta(Tratta tratta, Mezzo mezzo);
}
