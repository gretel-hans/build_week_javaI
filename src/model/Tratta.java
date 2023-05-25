package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tratte")
public class Tratta {
    @Id
    @SequenceGenerator(name = "seq_tratte", sequenceName = "seq_tratte", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tratte")
    private long id_tratta;
    @Column(nullable = false)
    private String zona_partenza;
    @Column(nullable = false)
    private String capolinea;
    @Column(nullable = false)
    private double tempo_medio_percorrenza;
    @Column(nullable = false)
    private boolean agibile;

    
    public Tratta() {}
    
    public Tratta(String zona_partenza, String capolinea, double tempo_medio_percorrenza) {
        this.zona_partenza = zona_partenza;
        this.capolinea = capolinea;
        this.tempo_medio_percorrenza = tempo_medio_percorrenza;
        this.agibile = true;
    }

    public long getId_tratta() {
        return id_tratta;
    }
    public void setId_tratta(long id_tratta) {
        this.id_tratta = id_tratta;
    }
    public String getZona_partenza() {
        return zona_partenza;
    }
    public void setZona_partenza(String zona_partenza) {
        this.zona_partenza = zona_partenza;
    }
    public String getCapolinea() {
        return capolinea;
    }
    public void setCapolinea(String capolinea) {
        this.capolinea = capolinea;
    }
    public double getTempo_medio_percorrenza() {
        return tempo_medio_percorrenza;
    }
    public void setTempo_medio_percorrenza(double tempo_medio_percorrenza) {
        this.tempo_medio_percorrenza = tempo_medio_percorrenza;
    }

    public boolean isAgibile() {
        return agibile;
    }

    public void setAgibile(boolean agibile) {
        this.agibile = agibile;
    }
}
