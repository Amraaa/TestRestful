package helper;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojos.UserTbl;
import utility.HibernateUtil;

public class UserHelper {

    public UserHelper() {

    }

    public void saveDataUser(int idUser, String name, String gender, String adress) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        UserTbl ut = new UserTbl();
        if (idUser != 0) {
            ut.setIdUser(idUser);
        }
        ut.setGender(gender);
        ut.setName(name);
        ut.setAdress(adress);

        session.saveOrUpdate(ut);
        tx.commit();
        session.close();
    }

    public List<UserTbl> userList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        List<UserTbl> l = null;

        Query q = session.createQuery("from UserTbl");
        l = q.list();
        tx.commit();
        session.close();
        return l;
    }

    public void updateName(int idUser, String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Query q = session.createQuery("update UserTbl u set u.name'" + name + "'where u.idUser= " + idUser);
        int i = q.executeUpdate();
        tx.commit();
        session.close();

    }

    public void deleteUser(int idUser) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Query q = session.createQuery("delete from UserTbl u where u.idUser" + idUser);
        int i = q.executeUpdate();
        tx.commit();
        session.close();

    }

    public List<UserTbl> getUserbyId(int idUser) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        List<UserTbl> l = null;
        Query q = session.createQuery("from UserTbl u where u.idUser" + idUser);
        l = q.list();
        tx.commit();
        session.close();
        return l;

    }

}
