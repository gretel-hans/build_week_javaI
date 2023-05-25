package model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "registro_doc_emessi")
public class RegistroDocEmessi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private DocumentoEmesso doc_emesso;
    @Column
    private String punto_emissione;
    @Column
    private LocalDate data_emissione;
    // @Column
    // private String tipo_documento;

    public RegistroDocEmessi() {
    }

    public RegistroDocEmessi(DocumentoEmesso docEmesso) {
        this.doc_emesso = docEmesso;
        this.punto_emissione = docEmesso.getPuntoEmissione().getIndirizzo();
        this.data_emissione = docEmesso.getDataEmissione();
    }
    
    public DocumentoEmesso getDocEmesso() {
        return doc_emesso;
    }
    
    
    public void setDocEmesso(DocumentoEmesso docEmesso) {
        this.doc_emesso = docEmesso;
    }
    public String getPuntoEmissione() {
        return punto_emissione;
    }
    public void setPuntoEmissione(String punto_emissione) {
        this.punto_emissione = punto_emissione;
    }
    public LocalDate getData_emissione() {
        return data_emissione;
    }
    public void setData_emissione(LocalDate data_emissione) {
        this.data_emissione = data_emissione;
    }

    @Override
    public String toString() {
        return "RegistroDocEmessi [docEmesso=" + doc_emesso + ", puntoEmissione=" + punto_emissione + ", data_emissione="
                + data_emissione + "]";
    }

}
