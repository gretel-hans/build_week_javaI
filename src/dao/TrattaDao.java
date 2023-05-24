package dao;

import javax.persistence.EntityManager;

import model.Tratta;
import utils.JpaUtil;

public class TrattaDao implements ITrattaDAO {

    @Override
    public void saveTratta(Tratta t) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(t);
			em.getTransaction().commit();
			System.out.println("Riga del registro salvato nel DB!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			System.out.println("Errore su salvataggio del registro tratte!" + e);
		}finally {
			em.close();
		}
    }
}
