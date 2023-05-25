package model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "abbonamenti")
public class Abbonamento extends DocumentoEmesso {
    @OneToOne(cascade = CascadeType.PERSIST)
    private Utente utente;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private durata durata;
    @Column(nullable = false)
    private LocalDate scadenza;
    
    public enum durata {SETTIMANALE,MENSILE}

    public Abbonamento() {}

    public Abbonamento(PuntoEmissione puntoEmissione, LocalDate dataEmissione, Utente utente, durata durata) {
        super(puntoEmissione, dataEmissione);
        this.utente = utente;
        this.durata = durata;
        if (this.durata == durata.SETTIMANALE) {
        	this.scadenza = super.getDataEmissione().plusDays(7);
        } else {
        	this.scadenza = super.getDataEmissione().plusDays(31);
        }
    }

    public Abbonamento(PuntoEmissione puntoEmissione, LocalDate dataEmissione) {
        super(puntoEmissione, dataEmissione);
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public durata getDurata() {
        return durata;
    }

    public void setDurata(durata durata) {
        this.durata = durata;
    }

	@Override
	public String toString() {
		return super.toString() +  "Abbonamento [utente=" + utente + ", durata=" + durata + "]";
	}
    
    
}
