package dao.user;

import model.User;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserDaoHibernate implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoHibernate.class);

    @Override
    public void addUser(User user) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(user);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Can not add new user " + user, e);
            if (transaction != null) transaction.rollback();
        }
    }

    @Override
    public void removeUser(Long id) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                String hql = "DELETE FROM User user WHERE id = :userId";
                Query query = session.createQuery(hql);
                query.setParameter("userId", id);
                query.executeUpdate();
            }
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Can not remove user with id " + id, e);
            if (transaction != null) transaction.rollback();
        }
    }

    @Override
    public void updateUser(Long id, User user) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql =
                    "UPDATE User user set email = :email, password =:password, role =:role" +
                            " WHERE id = :userId";
            Query query = session.createQuery(hql);
            query.setParameter("email", user.getEmail());
            query.setParameter("password", user.getPassword());
            query.setParameter("role", user.getRole());
            query.setParameter("userId", id);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Can not update user " + user + " with id = " + id, e);
            if (transaction!=null) transaction.rollback();
        }
    }

    @Override
    public Optional<User> getByEmail(String email) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = " FROM User user WHERE email = :userEmail";
            Query query = session.createQuery(hql);
            query.setParameter("userEmail", email);
            Optional<User> optionalUser = query.uniqueResultOptional();
            transaction.commit();
            return optionalUser;
        } catch (HibernateException e) {
            logger.error("Can not get user with email " + email, e);
            if (transaction != null) transaction.rollback();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getById(Long id) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = " FROM User user WHERE id = :userId";
            Query query = session.createQuery(hql);
            query.setParameter("userId", id);
            Optional<User> optionalUser = query.uniqueResultOptional();
            transaction.commit();
            return optionalUser;
        } catch (HibernateException e) {
            logger.error("Can not get user with id " + id, e);
            if (transaction != null) transaction.rollback();
        }
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<User> users = session.createQuery("FROM User", User.class).list();
            transaction.commit();
            return users;
        } catch (HibernateException e) {
            logger.error("Can not get all users ", e);
            if (transaction != null) transaction.rollback();
        }
        return Collections.emptyList();
    }
}
