package model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;

@Entity
@DiscriminatorColumn(name = "distributore_automatico")
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
    
}
