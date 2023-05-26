package dao;

import java.time.LocalDate;

import model.Mezzo;

public interface IMezzoDAO {

	public void saveOrUpdateMezzo(Mezzo m);

	public Mezzo trovaMezzo(long id);

	public void getNumeriBigliettiVidimatiSuUnMezzo(long id);
	public void getNumeriBigliettiVidimatiTraDueDate(LocalDate inizio, LocalDate fine);

}
