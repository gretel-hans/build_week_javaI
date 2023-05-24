package dao;

import javax.persistence.EntityManager;
import model.RegistroTratte;
import utils.JpaUtil;

public class RegistroTratteDAO implements IRegistroTratteDAO{
    
    @Override
    public void saveRegistro(RegistroTratte r) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(r);
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
