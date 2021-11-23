package crud;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Klijent;
import util.HibernateUtil;

public class CrudKlijent {
    private static Session session;

    public static void create(Klijent k) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(k);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void update(Klijent k) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(k);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void delete(Klijent k) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(k);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static List<Klijent> readAll() {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Klijent> klijenti = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            klijenti = session.createCriteria(Klijent.class).list();
            transaction.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return klijenti;
    }

    public static Klijent readById(Integer id) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Klijent k = null;
        try {
            transaction = session.beginTransaction();
            k = (Klijent) session.get(Klijent.class, id);
            transaction.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return k;
    }
}
