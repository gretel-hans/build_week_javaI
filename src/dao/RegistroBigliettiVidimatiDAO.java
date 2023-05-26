package dao;

import javax.persistence.EntityManager;

import model.Biglietto;
import model.RegistroBigliettiVidimati;
import utils.JpaUtil;

public class RegistroBigliettiVidimatiDAO implements IRegistroBigliettiVidimatiDAO {

    @Override
    public void saveOrUpdateRegistroBigliettoVidimato(RegistroBigliettiVidimati r) {
            EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
            try {
                if(r.getDocumentoEmesso() instanceof Biglietto){
                   Biglietto b= (Biglietto) r.getDocumentoEmesso();
                   b.setValidita(false);
                }
                em.getTransaction().begin();
                em.persist(em.merge(r));
                em.getTransaction().commit();
                System.out.println("Riga del registro dei biglietti vidimati salvato nel DB!");
            } catch (Exception e) {
                em.getTransaction().rollback();
                e.printStackTrace();
                System.out.println("Errore su salvataggio della riga del registro dei biglietti vidimati tratte!" + e);
            } finally {
                em.close();
            }
    }

    @Override
    public RegistroBigliettiVidimati cercaByIdRegistroBigliettoVidimato(long id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			RegistroBigliettiVidimati m = em.find(RegistroBigliettiVidimati.class, id);
			em.getTransaction().commit();
            System.out.println("Ecco la riga del registro bglietti vidimati cercata: "+m);
			return m;
		} catch (Exception e) {
			System.out.println("Errore su lettura della rige del registro biglietti vidimati con id: "+id + e);
		} finally {
			em.close();
		}
		return null;    }

    @Override
    public void deleteRigaRegistroBigliettoVidimato(long id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			RegistroBigliettiVidimati m = em.find(RegistroBigliettiVidimati.class, id);
			em.remove(m);
			em.getTransaction().commit();
			System.out.println("Riga del registro biglietti vidimati con id: " + id + " eliminato dal DB!!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Errore nella cancellazione della riga del registro biglietti vidimati con id: "+id);
			e.printStackTrace();
		} finally {
			em.close();
		} }
    
}
