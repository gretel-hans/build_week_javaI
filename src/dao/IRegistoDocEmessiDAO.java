package dao;

import java.time.LocalDate;

import model.PuntoEmissione;
import model.RegistroDocEmessi;

public interface IRegistoDocEmessiDAO {
    public void saveOrUpdateRDE(RegistroDocEmessi m);
	public RegistroDocEmessi trovaRDE(long id);
	//public void deleteRDE(RegistroDocEmessi m);
	public Long cercaBigliettiInPerioDiTempoDaPuntoVendita(LocalDate inizio, LocalDate fine, PuntoEmissione pe);
}
