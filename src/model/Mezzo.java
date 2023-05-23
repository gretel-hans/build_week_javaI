package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import dao.MezzoDAO;
import enums.StatoMezzo;

@Entity
@Table(name = "mezzi")
public class Mezzo extends MezzoDAO{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private long id;

	private Integer capienza;
	
	@Enumerated(EnumType.STRING)
	private StatoMezzo status;
	
	@OneToMany(mappedBy = "mezzo")
	private List<Biglietto> biglietti = new ArrayList<Biglietto>();

	public Mezzo() {
		super();
	}
	

	public Mezzo(Integer capienza, StatoMezzo status, List<Biglietto> bigliettiVidimati) {
		super();
		this.capienza = capienza;
		this.status = status;
		this.biglietti = bigliettiVidimati;
	}

	public Integer getCapienza() {
		return capienza;
	}

	public void setCapienza(Integer capienza) {
		this.capienza = capienza;
	}

	public StatoMezzo getStatus() {
		return status;
	}

	public void setStatus(StatoMezzo status) {
		this.status = status;
	}

	public List<Biglietto> getBigliettiVidimati() {
		return biglietti;
	}

	public void setBigliettiVidimati(List<Biglietto> bigliettiVidimati) {
		this.biglietti = bigliettiVidimati;
	}


	@Override
	public String toString() {
		return "Mezzi [capienza=" + capienza + ", status=" + status + ", bigliettiVidimati=" + biglietti + "]";
	}
	
	
}
