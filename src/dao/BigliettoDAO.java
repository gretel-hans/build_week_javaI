package dao;

import javax.persistence.EntityManager;

import model.Biglietto;
import utils.JpaUtil;

public class BigliettoDAO implements IBigliettoDAO{

	@Override
	public void salvaBiglietto(Biglietto b) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(b);
			em.getTransaction().commit();
			System.out.println("Biglietto salvato nel DB!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			System.out.println("Errore su salvataggio del Biglietto!" + e);
		}finally {
			em.close();
		}
	}

}
