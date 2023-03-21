package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.session();) {
            session.beginTransaction();
            try {
                session.createSQLQuery("CREATE TABLE IF NOT EXISTS User (id INT PRIMARY KEY AUTO_INCREMENT," +
                                " name VARCHAR(50), lastName VARCHAR(50), age TINYINT)").addEntity(User.class).executeUpdate();
                session.getTransaction().commit();
            } catch (Throwable e) {
                session.getTransaction().rollback();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.session();) {
            session.beginTransaction();
            try {
                session.createSQLQuery("DROP TABLE IF EXISTS User").addEntity(User.class).executeUpdate();
                session.getTransaction().commit();
            } catch (Throwable e) {
                session.getTransaction().rollback();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.session();) {
            session.beginTransaction();
            try {
                session.save(new User(name, lastName, age));
                session.getTransaction().commit();
            } catch (Throwable e) {
                session.getTransaction().rollback();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.session();) {
            session.beginTransaction();
            try {
                session.delete(session.get(User.class, id));
                session.getTransaction().commit();
            } catch (Throwable e) {
                session.getTransaction().rollback();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
       try(Session session = Util.session();){
           session.beginTransaction();
           users = session.createQuery("from User").getResultList();
       } catch (Throwable e){
           e.printStackTrace();
       }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.session();) {
            session.beginTransaction();
            try {
                session.createQuery("delete User").executeUpdate();
                session.getTransaction().commit();
            } catch (Throwable e) {
                session.getTransaction().rollback();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
