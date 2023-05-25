package model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import enums.StatoMezzo;

@Entity
@Table(name = "registro_stato_mezzi")
public class RegistroStatoMezzi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Mezzo mezzo;

    @Column(name = "stato_mezzo")
    @Enumerated(EnumType.STRING)
    private StatoMezzo statoMezzo;

    @Column(name = "data_inizio_stato")
    private LocalDate dataInizioStato;

    public RegistroStatoMezzi() {
    }

    public RegistroStatoMezzi(Mezzo mezzo, StatoMezzo statoMezzo, LocalDate dataInizioStato) {
        this.mezzo = mezzo;
        this.statoMezzo = statoMezzo;
        this.dataInizioStato = dataInizioStato;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzo mezzo) {
        this.mezzo = mezzo;
    }

    public StatoMezzo getStatoMezzo() {
        return statoMezzo;
    }

    public void setStatoMezzo(StatoMezzo statoMezzo) {
        this.statoMezzo = statoMezzo;
    }

    public LocalDate getDataInizioStato() {
        return dataInizioStato;
    }

    public void setDataInizioStato(LocalDate dataInizioStato) {
        this.dataInizioStato = dataInizioStato;
    }

    @Override
    public String toString() {
        return "RegistroStatoMezzi [id=" + id + ", mezzo=" + mezzo + ", statoMezzo=" + statoMezzo + ", dataInizioStato="
                + dataInizioStato + "]";
    }

}
