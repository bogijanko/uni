
package crud;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Lek;
import util.HibernateUtil;


public class CrudLek {
    private static Session session;

    public static boolean create(Lek lek) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(lek);
            transaction.commit();
            System.out.println("Uspesno ste dodali lek!");
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public static boolean update(Lek lek) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(lek);
            transaction.commit();
            System.out.println("Uspesno ste izmenili lek!");
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public static boolean delete(Lek lek) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(lek);
            transaction.commit();
            System.out.println("Uspesno ste obrisali lek!");
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public static List<Lek> readAll() {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Lek> lekovi = new ArrayList<>();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            lekovi = session.createQuery("from Lek").list();
            transaction.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }

        return lekovi;
    }
        public static Lek readById(Integer id) {
        session = HibernateUtil.getSessionFactory().openSession();
        Lek lek = null;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            lek = (Lek) session.get(Lek.class, id);
            transaction.commit();

        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return lek;
    }
    
}
