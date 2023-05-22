package model;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "documenti_emessi")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class DocumentoEmesso {
    @Id
    @SequenceGenerator(name = "seq_documenti_emessi", sequenceName = "seq_documenti_emessi", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_documenti_emessi")
    private long id;
    @OneToOne(cascade = CascadeType.PERSIST)      
    private  PuntoEmissione puntoEmissione;
    @Column(name = "data_emissione", nullable = false)                 
    private  LocalDate dataEmissione;

    public DocumentoEmesso() {}
    public DocumentoEmesso(PuntoEmissione puntoEmissione, LocalDate dataEmissione) {
        this.puntoEmissione = puntoEmissione;
        this.dataEmissione = dataEmissione;
    }
    
    public PuntoEmissione getPuntoEmissione() {
        return puntoEmissione;
    }
    public void setPuntoEmissione(PuntoEmissione puntoEmissione) {
        this.puntoEmissione = puntoEmissione;
    }
    public LocalDate getDataEmissione() {
        return dataEmissione;
    }
    public void setDataEmissione(LocalDate dataEmissione) {
        this.dataEmissione = dataEmissione;
    }

}
