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

import dao.RegistroBigliettiVidimatiDAO;

@Entity
@Table(name="registro_biglietti_vidimati")
public class RegistroBigliettiVidimati extends RegistroBigliettiVidimatiDAO {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne(cascade = CascadeType.MERGE)
    private Mezzo mezzo;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private DocumentoEmesso documentoEmesso;

    @Column(name="data_vidimazione")
    private LocalDate dataVidimazione;

    public RegistroBigliettiVidimati() {
    }

    public RegistroBigliettiVidimati(Mezzo mezzo, DocumentoEmesso documentoEmesso, LocalDate data) {
        this.mezzo = mezzo;
        this.documentoEmesso = documentoEmesso;
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

    public void setId(long id) {
        this.id = id;
    }

    public DocumentoEmesso getDocumentoEmesso() {
        return documentoEmesso;
    }

    public void setDocumentoEmesso(DocumentoEmesso documentoEmesso) {
        this.documentoEmesso = documentoEmesso;
    }

    public LocalDate getDataVidimazione() {
        return dataVidimazione;
    }

    public void setDataVidimazione(LocalDate dataVidimazione) {
        this.dataVidimazione = dataVidimazione;
    }

    @Override
    public String toString() {
        return "RegistroBigliettiVidimati [id=" + id + ", mezzo=" + mezzo + ", documentoEmesso=" + documentoEmesso
                + ", dataVidimazione=" + dataVidimazione + "]";
    }


    
    
}
