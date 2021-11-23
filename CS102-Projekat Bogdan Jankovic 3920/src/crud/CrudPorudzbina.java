
package crud;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Porudzbina;
import util.HibernateUtil;


public class CrudPorudzbina {
    private static Session session;

    public static void create(Porudzbina p) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(p);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void update(Porudzbina p) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(p);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void delete(Porudzbina p) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(p);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static List<Porudzbina> readAll() {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Porudzbina> porudzbine = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            porudzbine = session.createCriteria(Porudzbina.class).list();
            transaction.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return porudzbine;
    }

    public static Porudzbina readById(Integer id) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Porudzbina p = null;
        try {
            transaction = session.beginTransaction();
            p = (Porudzbina) session.get(Porudzbina.class, id);
            transaction.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return p;
    }
}