package model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import dao.TesseraDAO;

@Entity
@Table(name = "tessere")
public class Tessera extends TesseraDAO {
    @Id
    @SequenceGenerator(name = "seq_tessere", sequenceName = "seq_tessere", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tessere")
    private long id_tessera;
    @Column(nullable = false)
    private LocalDate data_inizio;
    @Column
    private LocalDate data_scadenza;

    public Tessera() {
    }

    public Tessera(LocalDate data_inizio) {
        this.data_inizio = data_inizio;
        this.data_scadenza = this.data_inizio.plusDays(365);
    }

    public long getId_tessera() {
        return id_tessera;
    }

    public void setId_tessera(long id_tessera) {
        this.id_tessera = id_tessera;
    }

    public LocalDate getData_inizio() {
        return data_inizio;
    }

    public void setData_inizio(LocalDate data_inizio) {
        this.data_inizio = data_inizio;
    }

    public LocalDate getData_scadenza() {
        return data_scadenza;
    }

    public void setData_scadenza(LocalDate data_scadenza) {
        this.data_scadenza = data_scadenza;
    }

    @Override
    public String toString() {
        return "Tessera [id_tessera=" + id_tessera + ", data_inizio=" + data_inizio + ", data_scadenza=" + data_scadenza
                + "]";
    }

}
