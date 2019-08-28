package dao.order;

import model.Order;
import org.apache.log4j.Logger;
import utils.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDaoJDBC implements OrderDao {

    private static final Logger logger = Logger.getLogger(OrderDaoJDBC.class);
    private static final String ADD_ORDER = "INSERT INTO orders (user_id, user_email, " +
            "name, surname, address, basket_id) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String REMOVE_ORDER = "DELETE FROM orders WHERE id = ?";
    private static final String GET_BY_ID = "SELECT * FROM orders WHERE id = ?";
    private static final String GET_ALL = "SELECT * FROM orders";

    @Override
    public void addOrder(Order order) {
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDER);
            preparedStatement.setLong(1, order.getUserID());
            preparedStatement.setString(2, order.getUserEmail());
            preparedStatement.setString(3, order.getName());
            preparedStatement.setString(4, order.getSurname());
            preparedStatement.setString(5, order.getAddress());
            preparedStatement.setLong(6, order.getBasketID());
            preparedStatement.execute();
            logger.info("Order " + order + " was added to DB");
        } catch (SQLException e) {
            logger.error("Adding order " + order + " was failed", e);
        }
    }

    @Override
    public void removeOrder(Long id) {
        try(Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_ORDER);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            logger.info("Order with id = " + id + " was removed from db");
        } catch (SQLException e) {
            logger.error("Removing order with id = " + id + " from db was failed", e);
        }
    }

    @Override
    public Optional<Order> getById(Long id) {
        try(Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Order order = new Order(
                        resultSet.getLong("id"),
                        resultSet.getLong("user_id"),
                        resultSet.getString("user_email"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("address"),
                        resultSet.getLong("basket_id")
                );
                return Optional.of(order);
            }
        } catch (SQLException e) {
            logger.error("No order with such id " + id + " in db", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Order> getAll() {
        List<Order> allOrders = new ArrayList<>();
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Order order = new Order(
                        resultSet.getLong("id"),
                        resultSet.getLong("user_id"),
                        resultSet.getString("user_email"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("address"),
                        resultSet.getLong("basket_id"));
                allOrders.add(order);
            }
        } catch (SQLException e) {
            logger.error("Getting all orders was failed", e);
        }
        return allOrders;
    }
}
