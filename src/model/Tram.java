package model;

import javax.persistence.Entity;

@Entity
public class Tram extends Mezzo{
	
	private Integer capienza = 70;

	public Tram() {
		super();
	}

	public Tram(String nomeMezzo) {
		super(nomeMezzo);
	}

	public Integer getCapienza() {
		return capienza;
	}

	@Override
	public String toString() {
		return super.toString() + "Tram [capienza=" + capienza + "]";
	}
	
	
	
	

}
