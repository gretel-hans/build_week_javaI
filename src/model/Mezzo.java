package model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import dao.MezzoDAO;
import dao.RegistroBigliettiVidimatiDAO;
import dao.RegistroStatoMezziDAO;
import dao.RegistroTratteDAO;
import enums.StatoMezzo;

@Entity
@DiscriminatorColumn(name = "tipo_mezzo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "mezzi")
public class Mezzo extends MezzoDAO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name="nome_mezzo")
	private String nomeMezzo;


	public Mezzo() {
		super();
	}

	public Mezzo(String nomeMezzo) {
		super();
		this.nomeMezzo = nomeMezzo;
	}


	public void percorriTratta(Tratta t) {
		RegistroTratteDAO rtd = new RegistroTratteDAO();
		RegistroTratte rt = new RegistroTratte(t, this);
		rtd.saveOrUpdateRegistroTratte(rt);
	}

	public void timbraDocumentoEmesso( DocumentoEmesso b,LocalDate data ){
      RegistroBigliettiVidimatiDAO rbvd= new RegistroBigliettiVidimatiDAO();
	  rbvd.saveOrUpdateRegistroBigliettoVidimato(new RegistroBigliettiVidimati(this, b,data));
	}

	public void vaiInManutenzione(LocalDate data){
		RegistroStatoMezziDAO rsmd = new RegistroStatoMezziDAO();
		RegistroStatoMezzi rsm = new RegistroStatoMezzi(this, StatoMezzo.MANUTENZIONE, data);
		rsmd.saveOrUpdateRegistroStato(rsm);
	}
	public void tornaInServizio(LocalDate data){
		RegistroStatoMezziDAO rsmd = new RegistroStatoMezziDAO();
		RegistroStatoMezzi rsm = new RegistroStatoMezzi(this, StatoMezzo.IN_SERVIZIO, data);
		rsmd.saveOrUpdateRegistroStato(rsm);
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getNomeMezzo() {
		return nomeMezzo;
	}
	
	public void setNomeMezzo(String nomeMezzo) {
		this.nomeMezzo = nomeMezzo;
	}

	@Override
	public String toString() {
		return "Mezzo [id=" + id + ", nomeMezzo=" + nomeMezzo + "]";
	}
	

	
}
