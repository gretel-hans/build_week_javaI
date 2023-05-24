package dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.RegistroTratte;
import utils.JpaUtil;

public class RegistroTratteDAO implements IRegistroTratteDAO{
    
    @Override
    public void saveRegistroTratte(RegistroTratte r) {
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

	@Override
	public void updateRegistroTratte(RegistroTratte r) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(r);
			em.getTransaction().commit();
			System.out.println("registro modificato nel DB!!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("ERRORE registro NON modificato nel DB!!");
		} finally {
			em.close();
		}
	}

	@Override
	public void deleteRegistroTratte(RegistroTratte r) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			Query q = em.createQuery("SELECT r FROM RegistroStatoMezzi r");
			em.getTransaction().begin();
			em.remove(rs.get(0));
			em.getTransaction().commit();
			System.out.println("Registro eliminato nel DB!!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("ERRORE! Registro NON eliminato nel DB!!" + e);
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public void showAllRegistroTratte() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'showAllRegistro'");
	}
    
}
