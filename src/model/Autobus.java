package model;

import javax.persistence.Entity;

@Entity
public class Autobus extends Mezzo {

	private Integer capienza = 50;

	public Autobus() {
		super();
	}

	public Autobus( String nomeMezzo) {
		super(nomeMezzo);
	}

	public Integer getCapienza() {
		return capienza;
	}

	@Override
	public String toString() {
		return super.toString() + "Autobus [capienza=" + capienza + "]";
	}

	

}
