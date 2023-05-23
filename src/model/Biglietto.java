package model;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "biglietti")
public class Biglietto extends DocumentoEmesso {
	
	private Boolean validita;
	
	@ManyToOne
	private Mezzo mezzo;

	public Biglietto(PuntoEmissione puntoEmissione, LocalDate dataEmissione) {
		super(puntoEmissione, dataEmissione);
		this.validita = true;
	}

	public Biglietto() {
	}

	public Boolean getValidita() {
		return validita;
	}

	public void setValidita(Boolean validita) {
		this.validita = validita;
	}


	@Override
	public String toString() {
		return super.toString() + "Biglietto [validita=" + validita + "]";
	}
	
	
}
