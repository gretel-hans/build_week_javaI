package dao;

import model.Abbonamento;
import model.Biglietto;

public interface IDocumentoDAO {
	
	public void saveOrUpdateBiglietto(Biglietto b);
	public Biglietto cercaBigliettoPerId(long id);
	public void eliminaBiglietto(Biglietto b);
	public void saveOrUpdateAbbonamento(Abbonamento a);
}
