package dao;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Biglietto;
import model.Mezzo;
import utils.JpaUtil;

public class MezzoDAO implements IMezziDAO{

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

}
