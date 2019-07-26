package dao.basket;

import factory.UserServiceFactory;
import model.Basket;
import model.ConfirmationCode;
import model.Product;
import model.User;
import org.apache.log4j.Logger;
import service.user.UserService;
import utils.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BasketDaoJDBC implements BasketDao {

    private static final UserService userService = UserServiceFactory.getInstance();
    private static final Logger logger = Logger.getLogger(BasketDaoJDBC.class);
    private static final String ADD_PRODUCT_TO_BASKET = "INSERT INTO product_in_basket " +
            "(basket_id, product_id) VALUES (?, ?)";
    private static final String ADD_BASKET = "INSERT INTO baskets (user_id, confirmation_code_id)" +
            " VALUES (?, ?)";
    private static final String REMOVE_BASKET = "DELETE FROM baskets WHERE id = ?";
    private static final String GET_LAST_BASKET_FOR_USER = "SELECT baskets.id, baskets.user_id, " +
            "baskets.confirmation_code_id FROM baskets WHERE " +
            "baskets.user_id = ? ORDER BY baskets.id DESC LIMIT 1";
    private static final String GET_ALL_BY_USER = "SELECT * FROM baskets WHERE user_id = ?";

    @Override
    public void addBasket(Basket basket) {
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_BASKET);
            preparedStatement.setLong(1, basket.getUserID());
            preparedStatement.setLong(2, basket.getConfirmationCode().getId());
            preparedStatement.execute();

            logger.info("Basket " + basket + " was added to DB");
        } catch (SQLException e) {
            logger.error("Adding basket " + basket + " was failed", e);
        }
    }

    @Override
    public void addProductsToBasket(Basket basket) {
        try (Connection connection = DBConnector.connect()) {
            List<Product> productList = basket.getProductsInBasket();
            for (Product product : productList) {
                PreparedStatement statement = connection.prepareStatement(ADD_PRODUCT_TO_BASKET);
                statement.setLong(1, basket.getId());
                statement.setLong(2, product.getId());
                statement.execute();
                logger.info("Added products to basket with id = " + basket.getId());
            }
        } catch (SQLException e) {
            logger.error("Try to add product in basket failed!", e);
        }
    }

    @Override
    public void removeBasket(Long id) {
        try(Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_BASKET);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            logger.info("Basket with id = " + id + " was removed from db");
        } catch (SQLException e) {
            logger.error("Removing basket with id = " + id + " from db was failed", e);
        }
    }

    @Override
    public Optional<Basket> getLastBasketForUser(User user) {
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(GET_LAST_BASKET_FOR_USER);
            preparedStatement.setLong(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                ConfirmationCode confirmationCode = new ConfirmationCode(
                        resultSet.getLong("confirmation_code_id"),
                        user.getEmail());
                Basket basket = new Basket(
                        resultSet.getLong("id"),
                        resultSet.getLong("user_id"),
                        confirmationCode
                );

                return Optional.of(basket);
            }
        } catch (SQLException e) {
            logger.error("Getting basket was failed", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Basket> getAllByUser(User user) {
        List<Basket> basketList = new ArrayList<>();
        try(Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_BY_USER);
            preparedStatement.setLong(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ConfirmationCode confirmationCode = new ConfirmationCode(
                        resultSet.getLong("confirmation_code_id"),
                        user.getEmail());
                Basket basket = new Basket(
                        resultSet.getLong("id"),
                        user.getId(),
                        confirmationCode
                );
                basketList.add(basket);
            }
        } catch (SQLException e) {
            logger.error("Getting all baskets for user was failed", e);
        }
        return basketList;
    }
}
