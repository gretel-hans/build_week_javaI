package dao;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.Query;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import model.Mezzo;
import utils.JpaUtil;

public class MezzoDAO implements IMezzoDAO {

	// static Logger log = LoggerFactory.getLogger(MezzoDAO.class);

	@Override
	public void saveOrUpdateMezzo(Mezzo m) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(em.merge(m));
			em.getTransaction().commit();
			System.out.println("Mezzo salvato nel DB!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Errore su salvataggio del Mezzo!" + e);
		} finally {
			em.close();
		}

	}

	@Override
	public Mezzo trovaMezzo(long id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			Mezzo m = em.find(Mezzo.class, id);
			em.getTransaction().commit();
			return m;
		} catch (Exception e) {
			System.out.println("Errore su lettura del Mezzo!" + e);
		} finally {
			em.close();
		}
		return null;

	}

	@Override
	public void getNumeriBigliettiVidimatiSuUnMezzo(long id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			Query q=em.createQuery("SELECT COUNT(r) FROM RegistroBigliettiVidimati r WHERE r.mezzo.id = :parametro_id");
			long conteggio= (long)q.setParameter("parametro_id", id).getSingleResult();
			System.out.println("Il mezzo con id: "+id +" ha vidimato "+conteggio +" biglietti");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Errore nel conteggio dei biglietti vidimati dal mezzo con id: !!"+id+e);
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public void getNumeriBigliettiVidimatiTraDueDate(LocalDate inizio, LocalDate fine) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			//select count(*) from registro_biglietti_vidimati where registro_biglietti_vidimati.data_vidimazione between '2023-05-24' AND '2023-05-29'

			Query q=em.createQuery("SELECT COUNT(r) FROM RegistroBigliettiVidimati r WHERE r.dataVidimazione BETWEEN :data_inizio AND :data_fine");
			long conteggio= (long)q.setParameter("data_inizio", inizio).setParameter("data_fine", fine).getSingleResult();
			System.out.println("Sono stati vidimati "+conteggio +" biglietti tra il "+inizio +" e "+fine);
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Errore nel conteggio dei biglietti vidimati tra"+inizio +" e "+fine+e);
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

    

}
