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

	@OneToMany (mappedBy = "id")
	private List<DocumentoEmesso> biglietti = new ArrayList<DocumentoEmesso>();

	public Mezzo() {
		super();
	}

	public Mezzo(List<DocumentoEmesso> biglietti) {
		super();
		this.biglietti = biglietti;
	}

	public void percorriTratta(Tratta t) {
		RegistroTratteDAO rtd = new RegistroTratteDAO();
		RegistroTratte rt = new RegistroTratte(t, this);
		rtd.saveOrUpdateRegistroTratte(rt);
	}

	public List<DocumentoEmesso> getBigliettiVidimati() {
		return biglietti;
	}

	public void setBigliettiVidimati(List<DocumentoEmesso> biglietti) {
		this.biglietti = biglietti;
	}

	public void getNumeroBiglietti() {
		this.biglietti.forEach(b -> System.out.println(b));
	}

	@Override
	public String toString() {
		return "Mezzo";
	}

	public long getId() {
		return id;
	}

	public List<DocumentoEmesso> getBiglietti() {
		return biglietti;
	}

	public void setBiglietti(List<DocumentoEmesso> biglietti) {
		this.biglietti = biglietti;
	}

}
