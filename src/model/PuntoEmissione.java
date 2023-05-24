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

import dao.BigliettoDAO;
import dao.RegistroDocEmessiDAO;

@Entity
@Table(name = "punti_emissione")
@DiscriminatorColumn(name = "tipo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class PuntoEmissione {
    @Id
    @SequenceGenerator(name = "seq_punti_emissione", sequenceName = "seq_punti_emissione", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_punti_emissione")
    private long id_punto_emissione;
    @Column(nullable = true)
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
    
    public void emettiDocumento(boolean b) {
        if (b) {
            Biglietto bg = new Biglietto(this, LocalDate.now());
            BigliettoDAO bd = new BigliettoDAO();
		    bd.salvaBiglietto(bg);
            RegistroDocEmessiDAO rded = new RegistroDocEmessiDAO();
            RegistroDocEmessi rde = new RegistroDocEmessi(bg);
            rded.salvaRDE(rde);
        } else {
            Abbonamento a = new Abbonamento(this, LocalDate.now());
            BigliettoDAO bd = new BigliettoDAO();
		    bd.salvaAbbonamento(a);
            RegistroDocEmessiDAO rded = new RegistroDocEmessiDAO();
            RegistroDocEmessi rde = new RegistroDocEmessi(a);
            rded.salvaRDE(rde);
        }
    }

    public void emettiDocumento(boolean b, LocalDate dataEmissione) {
        if (b) {
            Biglietto bg = new Biglietto(this, dataEmissione);
            BigliettoDAO bd = new BigliettoDAO();
		    bd.salvaBiglietto(bg);
            RegistroDocEmessiDAO rded = new RegistroDocEmessiDAO();
            RegistroDocEmessi rde = new RegistroDocEmessi(bg);
            rded.salvaRDE(rde);
        } else {
            Abbonamento a = new Abbonamento(this, dataEmissione);
            BigliettoDAO bd = new BigliettoDAO();
		    bd.salvaAbbonamento(a);
            RegistroDocEmessiDAO rded = new RegistroDocEmessiDAO();
            RegistroDocEmessi rde = new RegistroDocEmessi(a);
            rded.salvaRDE(rde);
        }
    }
}

