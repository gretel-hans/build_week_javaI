package dao;

import javax.persistence.EntityManager;

import model.RegistroStatoMezzi;
import utils.JpaUtil;

public class RegistroDAO implements IRegistroStatoMezziDAO {

    @Override
    public void saveRegistro(RegistroStatoMezzi r) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(r);
			em.getTransaction().commit();
			System.out.println("Riga del registro salvato nel DB!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			System.out.println("Errore su salvataggio della riga del registro" + e);
		}finally {
			em.close();
		}
    }
    
}
