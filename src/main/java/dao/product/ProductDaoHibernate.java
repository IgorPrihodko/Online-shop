package dao.product;

import model.Product;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ProductDaoHibernate implements ProductDao {

    private static final Logger logger = Logger.getLogger(ProductDaoHibernate.class);

    @Override
    public void addProduct(Product product) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Can not add new product " + product, e);
            if (transaction != null) transaction.rollback();
        }
    }

    @Override
    public void removeProduct(Long id) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Product product = session.get(Product.class, id);
            if (product != null) {
                String hql = "DELETE FROM Product product WHERE id = :productId";
                Query query = session.createQuery(hql);
                query.setParameter("productId", id);
                query.executeUpdate();
            }
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Can not remove product with id " + id, e);
            if (transaction != null) transaction.rollback();
        }
    }

    @Override
    public void updateProduct(Long id, Product product) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "UPDATE Product product set title = :title, description =:description," +
                    " price =:price WHERE id = :productId";
            Query query = session.createQuery(hql);
            query.setParameter("title", product.getTitle());
            query.setParameter("description", product.getDescription());
            query.setParameter("price", product.getPrice());
            query.setParameter("productId", id);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Can not update product " + product + " with id = " + id, e);
            if (transaction!=null) transaction.rollback();
        }
    }

    @Override
    public Optional<Product> getById(Long id) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = " FROM Product product WHERE id = :productId";
            Query query = session.createQuery(hql);
            query.setParameter("productId", id);
            Optional<Product> optionalProduct = query.uniqueResultOptional();
            transaction.commit();
            return optionalProduct;
        } catch (HibernateException e) {
            logger.error("Can not get product with id " + id, e);
            if (transaction != null) transaction.rollback();
        }
        return Optional.empty();
    }

    @Override
    public List<Product> getAll() {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<Product> products =
                    session.createQuery("FROM Product", Product.class).list();
            transaction.commit();
            return products;
        } catch (HibernateException e) {
            logger.error("Can not get all products ", e);
            if (transaction != null) transaction.rollback();
        }
        return Collections.emptyList();
    }
}
