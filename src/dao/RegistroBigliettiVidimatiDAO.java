package dao;

import javax.persistence.EntityManager;

import model.RegistroBigliettiVidimati;
import utils.JpaUtil;

public class RegistroBigliettiVidimatiDAO implements IRegistroBigliettiVidimatiDAO {

    @Override
    public void saveOrUpdateRegistroBigliettoVidimato(RegistroBigliettiVidimati r) {
            EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
            try {
                r.getBiglietto().setValidita(false);
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
    
}
