package dao;

import java.time.LocalDate;

import model.Biglietto;
import model.Mezzo;

public interface IMezzoDAO {

	public void salvaMezzo(Mezzo m);

	public Mezzo trovaMezzo(long id);

	public void updateMezzo(Mezzo m);

	public void deleteMezzo(long id);

	public void getNumeriBigliettiVidimatiSuUnMezzo(long id);
	public void getNumeriBigliettiVidimatiTraDueDate(LocalDate inizio, LocalDate fine);

}
