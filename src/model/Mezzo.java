package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import dao.MezzoDAO;
import dao.RegistroTratteDAO;

@Entity
@DiscriminatorColumn(name = "tipo_mezzo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "mezzi")
public class Mezzo extends MezzoDAO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToMany(mappedBy = "mezzo")
	private List<Biglietto> biglietti = new ArrayList<Biglietto>();

	public Mezzo() {
		super();
	}

	public Mezzo(List<Biglietto> biglietti) {
		super();
		this.biglietti = biglietti;
	}

	public void percorriTratta(Tratta t) {
		RegistroTratteDAO rtd = new RegistroTratteDAO();
		RegistroTratte rt = new RegistroTratte(t, this);
		rtd.saveRegistro(rt);
	}

	public List<Biglietto> getBigliettiVidimati() {
		return biglietti;
	}

	public void setBigliettiVidimati(List<Biglietto> biglietti) {
		this.biglietti = biglietti;
	}

	@Override
	public String toString() {
		return "Mezzi [bigliettiVidimati=" + "]";
	}

	public long getId() {
		return id;
	}

	public List<Biglietto> getBiglietti() {
		return biglietti;
	}

	public void setBiglietti(List<Biglietto> biglietti) {
		this.biglietti = biglietti;
	}

}
