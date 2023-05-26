package model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "distributore_automatico")
public class DistributoreAutomatico extends PuntoEmissione{
    @Column(name = "in_servizio")
    Boolean inServizio = true;
    
    public DistributoreAutomatico(String indirizzo, Boolean inServizio) {
        super(indirizzo);
        this.inServizio = inServizio;
    }
    public DistributoreAutomatico() {}


    public DistributoreAutomatico(String indirizzo) {
        super(indirizzo);
    }
    public Boolean getInServizio() {
        return inServizio;
    }

    public void setInServizio(Boolean inServizio) {
        this.inServizio = inServizio;
    }
	@Override
	public String toString() {
		return super.toString() + "DistributoreAutomatico [inServizio=" + inServizio + "]";
	}
    
    
}
