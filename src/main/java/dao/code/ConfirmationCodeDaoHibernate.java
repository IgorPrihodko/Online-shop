package dao.code;

import model.ConfirmationCode;
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

public class ConfirmationCodeDaoHibernate implements ConfirmationCodeDao {

    private static final Logger logger = Logger.getLogger(ConfirmationCodeDaoHibernate.class);

    @Override
    public void addConfirmationCode(ConfirmationCode confirmationCode) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(confirmationCode);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Can not add new confirmation code " + confirmationCode, e);
            if (transaction != null) transaction.rollback();
        }
    }

    @Override
    public void removeConfirmationCode(Long id) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            ConfirmationCode confirmationCode = session.get(ConfirmationCode.class, id);
            if (confirmationCode != null) {
                String hql =
                        "DELETE FROM ConfirmationCode confirmationCode WHERE id = :confirmationCodeId";
                Query query = session.createQuery(hql);
                query.setParameter("confirmationCodeId", id);
                query.executeUpdate();
            }
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Can not remove confirmation code with id " + id, e);
            if (transaction != null) transaction.rollback();
        }
    }

    @Override
    public List<ConfirmationCode> getByUserEmail(String userEmail) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = " FROM ConfirmationCode confirmationCode WHERE user.email = :userEmail";
            Query query = session.createQuery(hql);
            query.setParameter("userEmail", userEmail);
            List<ConfirmationCode> confirmationCodes = query.list();
            transaction.commit();
            return confirmationCodes;
        } catch (HibernateException e) {
            logger.error("Can not get user with email " + userEmail, e);
            if (transaction != null) transaction.rollback();
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<ConfirmationCode> getLastConfirmationCodeForUser(User user) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM ConfirmationCode confirmationCode WHERE user.id = :userId ";
            Query query = session.createQuery(hql);
            query.setParameter("userId", user.getId());
            List<ConfirmationCode> confirmationCodes = query.list();
            Optional<ConfirmationCode> optionalConfirmationCode =
                    confirmationCodes
                            .stream()
                            .filter(confirmationCode -> confirmationCode.getUser().equals(user))
                            .min((o1, o2) -> (int) (o2.getId() - o1.getId()));
            transaction.commit();
            return optionalConfirmationCode;
        } catch (HibernateException e) {
            logger.error("Can not get last confirmation code for user " + user, e);
            if (transaction != null) transaction.rollback();
        }
        return Optional.empty();
    }
}
