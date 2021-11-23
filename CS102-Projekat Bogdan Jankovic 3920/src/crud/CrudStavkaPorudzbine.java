
package crud;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.StavkaPorudzbine;
import util.HibernateUtil;

public class CrudStavkaPorudzbine {
       private static Session session;

    public static void create(StavkaPorudzbine sp) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(sp);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void update(StavkaPorudzbine sp) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.update(sp);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void delete(StavkaPorudzbine sp) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.delete(sp);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static List<StavkaPorudzbine> readAll() {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<StavkaPorudzbine> stavke = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            stavke = session.createCriteria(StavkaPorudzbine.class).list();
            transaction.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return stavke;
    }
      public static StavkaPorudzbine readById(Integer id) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        StavkaPorudzbine sp = null;
        try {
            transaction = session.beginTransaction();
            sp = (StavkaPorudzbine) session.get(StavkaPorudzbine.class, id);
            transaction.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return sp;
    }
}
