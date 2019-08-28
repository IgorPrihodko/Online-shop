package dao.order;

import model.StockOnOrder;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class OrderDaoHibernate implements OrderDao {

    private static final Logger logger = Logger.getLogger(OrderDaoHibernate.class);

    @Override
    public void addOrder(StockOnOrder stockOnOrder) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(stockOnOrder);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Can not add new stockOnOrder " + stockOnOrder, e);
            if (transaction != null) transaction.rollback();
        }
    }

    @Override
    public void removeOrder(Long id) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            StockOnOrder stockOnOrder = session.get(StockOnOrder.class, id);
            if (stockOnOrder != null) {
                String hql = "DELETE FROM StockOnOrder stockOnOrderId WHERE id = :stockOnOrderId";
                Query query = session.createQuery(hql);
                query.setParameter("stockOnOrderId", id);
                query.executeUpdate();
            }
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Can not remove stockOnOrder with id " + id, e);
            if (transaction != null) transaction.rollback();
        }
    }

    @Override
    public Optional<StockOnOrder> getById(Long id) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = " FROM StockOnOrder stockOnOrder WHERE id = :stockOnOrderId";
            Query query = session.createQuery(hql);
            query.setParameter("stockOnOrderId", id);
            Optional<StockOnOrder> optionalStockOnOrder = query.uniqueResultOptional();
            transaction.commit();
            return optionalStockOnOrder;
        } catch (HibernateException e) {
            logger.error("Can not get stockOnOrder with id " + id, e);
            if (transaction != null) transaction.rollback();
        }
        return Optional.empty();
    }

    @Override
    public List<StockOnOrder> getAll() {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<StockOnOrder> stockOnOrders = session.createQuery("FROM StockOnOrder",
                    StockOnOrder.class).list();
            transaction.commit();
            return stockOnOrders;
        } catch (HibernateException e) {
            logger.error("Can not get all stockOnOrders", e);
            if (transaction != null) transaction.rollback();
        }
        return Collections.emptyList();
    }
}
