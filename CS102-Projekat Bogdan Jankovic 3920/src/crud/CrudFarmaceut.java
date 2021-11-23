package crud;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Farmaceut;
import util.HibernateUtil;

public class CrudFarmaceut {

    private static Session session;

    public static void create(Farmaceut f) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(f);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void update(Farmaceut f) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(f);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void delete(Farmaceut f) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(f);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static List<Farmaceut> readAll() {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Farmaceut> farmaceuti = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            farmaceuti = session.createCriteria(Farmaceut.class).list();
            transaction.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return farmaceuti;
    }

    public static Farmaceut readById(Integer id) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Farmaceut f = null;
        try {
            transaction = session.beginTransaction();
            f = (Farmaceut) session.get(Farmaceut.class, id);
            transaction.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return f;
    }

    public static Farmaceut get(String username) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Farmaceut where username = :username");
        query.setParameter("username", username);
        Farmaceut f = (Farmaceut) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return f;
    }
        public static boolean check(String username) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Farmaceut where username = :username");
        query.setParameter("username", username);
        Farmaceut temp = (Farmaceut) query.uniqueResult();
        session.getTransaction().commit();
        return temp != null;
    }

    public static boolean checkLogin(String username, String password) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Farmaceut where username = :username "
                + "and password = :password");
        query.setParameter("password", password);
        query.setParameter("username", username);
        Farmaceut temp = (Farmaceut) query.uniqueResult();
        session.getTransaction().commit();
        boolean result;
        return result = temp != null ? true : false;
    }
}
