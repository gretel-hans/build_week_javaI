package dao;

import javax.persistence.EntityManager;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import model.Biglietto;
import model.Mezzo;
import model.Tratta;
import model.RegistroTratte;
import utils.JpaUtil;

public class MezzoDAO implements IMezzoDAO{

	//static Logger log = LoggerFactory.getLogger(MezzoDAO.class);
	
	
	@Override
	public void salvaMezzo(Mezzo m) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(m);
			em.getTransaction().commit();
			System.out.println("Mezzo salvato nel DB!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Errore su salvataggio del Mezzo!" + e);
		}finally {
			em.close();
		}
		
	}

	@Override
	public Mezzo trovaMezzo(long id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			Mezzo m=em.find(Mezzo.class, id);
			em.getTransaction().commit();
			return m;
		} catch (Exception e) {
			System.out.println("Errore su lettura del Mezzo!" + e);
		}finally {
			em.close();
		}
		return null;
		
	}

	@Override
	public void updateMezzo(Mezzo m) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(m);
			em.getTransaction().commit();
			System.out.println("Mezzo modificato nel DB!!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("ERRORE mezzo NON modificato nel DB!!");
		} finally {
			em.close();
		}
	}

	@Override
	public void deleteMezzo(Mezzo m) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.remove(m);
			em.getTransaction().commit();
			System.out.println("Mezzo eliminato nel DB!!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("ERRORE mezzo NON eliminato nel DB!!");
		} finally {
			em.close();
		}
	}

	@Override
	 public void timbraBiglietto(Biglietto b) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			b.setValidita(false);
			em.merge(b);
			em.getTransaction().commit();
			System.out.println("Biglietto con id " + b.getId() + " timbrato!");
		} catch (Exception e) {
			System.out.println("Errore nella vidimazione del biglietto!" + e);
		} finally {
			em.close();
		}
	}

}
