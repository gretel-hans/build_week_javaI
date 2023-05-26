package model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import dao.PuntoEmissioneDAO;
import dao.RegistroDocEmessiDAO;
import model.Abbonamento.durata;

@Entity
@Table(name = "punti_emissione")
@DiscriminatorColumn(name = "tipo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class PuntoEmissione extends PuntoEmissioneDAO{
    @Id
    @SequenceGenerator(name = "seq_punti_emissione", sequenceName = "seq_punti_emissione", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_punti_emissione")
    private long id_punto_emissione;
    @Column(nullable = false)
    private String indirizzo;

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public PuntoEmissione(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public PuntoEmissione() {}

    public long getId() {
        return id_punto_emissione;
    }

    public void setId(long id) {
        this.id_punto_emissione = id;
    }

	@Override
	public String toString() {
		return "PuntoEmissione [id_punto_emissione=" + id_punto_emissione + ", indirizzo=" + indirizzo + "]";
	}
    
    public Biglietto emettiBiglietto() {
            Biglietto bg = new Biglietto(this, LocalDate.now());		  
            RegistroDocEmessiDAO rded = new RegistroDocEmessiDAO();
            RegistroDocEmessi rde = new RegistroDocEmessi(bg);
            rded.saveOrUpdateRDE(rde);
            return bg;
    }

    public Abbonamento emettiAbbonamento(Utente utente, durata durata) {
            Abbonamento a = new Abbonamento(this, LocalDate.now(), utente, durata);
            RegistroDocEmessiDAO rded = new RegistroDocEmessiDAO();
            RegistroDocEmessi rde = new RegistroDocEmessi(a);
            rded.saveOrUpdateRDE(rde);
            return a;
        
    }

    public void emettiBiglietto(LocalDate data) {
        Biglietto bg = new Biglietto(this, data);       
        RegistroDocEmessiDAO rded = new RegistroDocEmessiDAO();
        RegistroDocEmessi rde = new RegistroDocEmessi(bg);
        rded.saveOrUpdateRDE(rde);
}
}

