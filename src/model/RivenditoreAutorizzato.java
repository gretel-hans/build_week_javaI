package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "rivenditore_autorizzato")
public class RivenditoreAutorizzato extends PuntoEmissione {
	public RivenditoreAutorizzato() {
		super();
	};

	public RivenditoreAutorizzato(String indirizzo) {
		super(indirizzo);
	}

	
	@Override
	public String toString() {
		return  super.toString() +  "RivenditoreAutorizzato";
	}

	
	

}
