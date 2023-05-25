package model;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class Tram extends Mezzo{
	
	private Integer capienza = 70;

	public Tram() {
		super();
	}

	public Tram(List<Biglietto> biglietti) {
		super(biglietti);
		this.capienza = capienza;
	}

	public Integer getCapienza() {
		return capienza;
	}

	@Override
	public String toString() {
		return super.toString() + "Tram [capienza=" + capienza + "]";
	}
	
	
	
	

}
