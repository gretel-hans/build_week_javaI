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
@Table(name = "utenti")
public class Utente {
    @Id
    @SequenceGenerator(name = "seq_utenti", sequenceName = "seq_utenti", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_utenti")
    private long id_utente;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Tessera tessera;
    @Column
    private String nome;
    @Column
    private String cognome;
    @Column
    private double saldo;

    public Utente() {
    }

    public Utente(Tessera tessera, String nome, String cognome) {
        this.tessera = tessera;
        this.nome = nome;
        this.cognome = cognome;
    }

    public long getId_utente() {
        return id_utente;
    }

    public void setId_utente(long id_utente) {
        this.id_utente = id_utente;
    }

    public Tessera getTessera() {
        return tessera;
    }

    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

}
