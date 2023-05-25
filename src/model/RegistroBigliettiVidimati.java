package model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

@Entity
@Table(name="registro_biglietti_vidimati")
public class RegistroBigliettiVidimati {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Mezzo mezzo;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Biglietto biglietto;

    @Column(name="data_vidimazione")
    private LocalDate dataVidimazione;

    public RegistroBigliettiVidimati() {
    }

    public RegistroBigliettiVidimati(Mezzo mezzo, Biglietto biglietto, LocalDate data) {
        this.mezzo = mezzo;
        this.biglietto = biglietto;
        this.dataVidimazione = data;
    }

    public long getId() {
        return id;
    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzo mezzo) {
        this.mezzo = mezzo;
    }

    public Biglietto getBiglietto() {
        return biglietto;
    }

    public void setBiglietto(Biglietto biglietto) {
        this.biglietto = biglietto;
    }

    @Override
    public String toString() {
        return "RegistroBigliettiVidimati [id=" + id + ", mezzo=" + mezzo + ", biglietto=" + biglietto + "]";
    }
    
    
}
