package dao;

import java.time.LocalDate;

import model.PuntoEmissione;
import model.RegistroDocEmessi;

public interface IRegistoDocEmessiDAO {
    public void salvaRDE(RegistroDocEmessi m);
	public void trovaRDE(long id);
	public void updateRDE(RegistroDocEmessi m);
	public void deleteRDE(RegistroDocEmessi m);
	public Long cercaBigliettiInPerioDiTempoDaPuntoVendita(LocalDate inizio, LocalDate fine, PuntoEmissione pe);
}
