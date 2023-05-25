package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "registro_tratte")
public class RegistroTratte {
    @Id
    @SequenceGenerator(name = "seq_tratte_percorse", sequenceName = "seq_tratte_percorse", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tratte_percorse")
    private long id_tratta_percorsa;
    @OneToOne
    private Tratta tratta;
    @OneToOne(cascade = CascadeType.MERGE)
    Mezzo mezzo;
    @Column(nullable = true)
    private double tempo_effettivo_percorrenza;

    public RegistroTratte() {
    }

    public RegistroTratte(Tratta tratta, Mezzo mezzo) {
        this.tratta = tratta;
        this.mezzo = mezzo;
        this.tempo_effettivo_percorrenza = this.tratta.getTempo_medio_percorrenza()
                + Math.floor((Math.random() * 0.20) * 100) / 100;
    }

    public long getId_tratta_percorsa() {
        return id_tratta_percorsa;
    }

    public void setId_tratta_percorsa(long id_tratta_percorsa) {
        this.id_tratta_percorsa = id_tratta_percorsa;
    }

    public Tratta getTratta() {
        return tratta;
    }

    public void setTratta(Tratta tratta) {
        this.tratta = tratta;
    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzo mezzo) {
        this.mezzo = mezzo;
    }

    public double getTempo_effettivo_percorrenza() {
        return tempo_effettivo_percorrenza;
    }

    public void setTempo_effettivo_percorrenza(double tempo_effettivo_percorrenza) {
        this.tempo_effettivo_percorrenza = tempo_effettivo_percorrenza;
    }
}
