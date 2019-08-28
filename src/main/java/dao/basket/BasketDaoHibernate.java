package dao.basket;

import model.Basket;
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

public class BasketDaoHibernate implements BasketDao {

    private static final Logger logger = Logger.getLogger(BasketDaoHibernate.class);

    @Override
    public void addBasket(Basket basket) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(basket);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Can not add new basket " + basket, e);
            if (transaction != null) transaction.rollback();
        }
    }

    @Override
    public void removeBasket(Long id) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Basket basket = session.get(Basket.class, id);
            if (basket != null) {
                String hql = "DELETE FROM Basket basket WHERE id = :basketId";
                Query query = session.createQuery(hql);
                query.setParameter("basketId", id);
                query.executeUpdate();
            }
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Can not remove basket with id " + id, e);
            if (transaction != null) transaction.rollback();
        }
    }

    @Override
    public void addProductsToBasket(Basket basket) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(basket);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Can not add products to basket " + basket, e);
            if (transaction != null) transaction.rollback();
        }
    }

    @Override
    public Optional<Basket> getLastBasketForUser(User user) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM Basket basket WHERE user.id = :userId ";
            Query query = session.createQuery(hql);
            query.setParameter("userId", user.getId());
            List<Basket> baskets = query.list();
            Optional<Basket> optionalBasket =
                    baskets
                            .stream()
                            .filter(basket -> basket.getUser().equals(user))
                            .min((o1, o2) -> (int) (o2.getId() - o1.getId()));
            transaction.commit();
            return optionalBasket;
        } catch (HibernateException e) {
            logger.error("Can not get last basket for user " + user, e);
            if (transaction != null) transaction.rollback();
        }
        return Optional.empty();
    }

    @Override
    public List<Basket> getAllByUser(User user) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = " FROM Basket basket WHERE user.id = :userId";
            Query query = session.createQuery(hql);
            query.setParameter("userId", user.getId());
            List<Basket> baskets = query.list();
            transaction.commit();
            return baskets;
        } catch (HibernateException e) {
            logger.error("Can not get baskets for user " + user, e);
            if (transaction != null) transaction.rollback();
        }
        return Collections.emptyList();
    }
}
