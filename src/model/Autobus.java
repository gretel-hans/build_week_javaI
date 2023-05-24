package model;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class Autobus extends Mezzo {

	private Integer capienza = 50;

	public Autobus() {
		super();
	}

	public Autobus( List<Biglietto> biglietti) {
		super(biglietti);
		this.capienza = capienza;
	}

	public Integer getCapienza() {
		return capienza;
	}

	@Override
	public String toString() {
		return super.toString() + "Autobus [capienza=" + capienza + "]";
	}

}
