package model;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "biglietti")
public class Biglietto extends DocumentoEmesso {

    public Biglietto(PuntoEmissione puntoEmissione, LocalDate dataEmissione) {
        super(puntoEmissione, dataEmissione);
    }
    
}
