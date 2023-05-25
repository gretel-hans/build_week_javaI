package dao;

import java.time.LocalDate;

import javax.persistence.EntityManager;

import model.Tessera;
import utils.JpaUtil;

public class TesseraDAO implements ITesseraDAO {

    @Override
    public void salvaTessera(Tessera t) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(em.merge(t));
            em.getTransaction().commit();
            System.out.println(t + " salvato nel DB!!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Errore nel salvataggio di " + t);
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public Tessera trovaTessera(long id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            Tessera t = em.find(Tessera.class, id);
            em.getTransaction().commit();
            return t;
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Errore nella  ricerca con id: " + id);
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public void deleteTessera(long id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            Tessera t = em.find(Tessera.class, id);
            em.remove(t);
            em.getTransaction().commit();
            System.out.println("Elememento " + t + " eliminato dal DB!!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Errore nella cancellazione!!");
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void updateTessera(Tessera t) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(t);
            em.getTransaction().commit();
            System.out.println("Aggiornamento " + t + "salvato nel DB!!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Errore nella modifica del " + t + " nel DB!!");
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void rinnovaTessera(Tessera t) {
        LocalDate dataScadenza = t.getData_scadenza();
        if (LocalDate.now().isAfter(dataScadenza)) {
            t.setData_inizio(LocalDate.now());
            LocalDate nuovaDataScadenza = LocalDate.now().plusDays(365);
            t.setData_scadenza(nuovaDataScadenza);
            System.out.println("Abonamento rinnovato! Data scadenza: " + nuovaDataScadenza);
        } else {
            System.out.println("Il tuo abbonamento è ancora valido! Scade il: " + dataScadenza);
        }
    }

}
