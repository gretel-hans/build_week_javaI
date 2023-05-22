package model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;

@Entity
@DiscriminatorColumn(name = "rivenditore_autorizzato")
public class RivenditoreAutorizzato extends PuntoEmissione {
    public RivenditoreAutorizzato() {};
    public RivenditoreAutorizzato(String indirizzo) {
        super(indirizzo);
    }
}
