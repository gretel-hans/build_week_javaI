package dao;

import model.Biglietto;
import model.Mezzo;

public interface IMezzoDAO {

	public void salvaMezzo(Mezzo m);

	public Mezzo trovaMezzo(long id);

	public void updateMezzo(Mezzo m);

	public void deleteMezzo(long id);

	public void timbraBiglietto(Biglietto b);
}
