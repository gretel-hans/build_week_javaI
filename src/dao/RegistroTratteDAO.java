package dao;

import java.util.ArrayList;
import java.util.List;

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
	public void deleteRegistroTratte(long id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			Query q = em.createQuery("SELECT r FROM RegistroTratte r WHERE r.id_tratta_percorsa = :id_param");
			List<RegistroTratte> rs =q.setParameter("id_param", id).getResultList();
			em.getTransaction().begin();
			em.remove(rs.get(0));
			em.getTransaction().commit();
			System.out.println("Riga RegistroTratta con id: "+id+ " eliminato dal DB!!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("ERRORE! Riga RegistroTratta NON eliminata dal DB!!" + e);
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public List<RegistroTratte> showAllRegistroTratte() {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			List<RegistroTratte> rs = new ArrayList<RegistroTratte>();
			Query q = em.createQuery("SELECT s FROM RegistroTratte s");
			rs = q.getResultList();
			rs.forEach(l -> System.out.println(l.getId_tratta_percorsa() + " = mezzo "+l.getMezzo().getId() +", da " + l.getTratta().getZona_partenza() +", in "+ l.getTempo_effettivo_percorrenza()+"h"));
			return rs;
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("ERRORE impossibile recuperare la lista dal db!" + e);
		} finally {
			em.close();
		}
		return null;
	}
    
}
