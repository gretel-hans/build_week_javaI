package model;

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
}


