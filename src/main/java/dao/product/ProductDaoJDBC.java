package dao.product;

import model.Product;
import org.apache.log4j.Logger;
import utils.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDaoJDBC implements ProductDao {

    private static final Logger logger = Logger.getLogger(ProductDaoJDBC.class);
    private static final String ADD_PRODUCT = "INSERT INTO products (title, description, price)" +
            " VALUES (?, ?, ?)";
    private static final String REMOVE_PRODUCT = "DELETE FROM products WHERE id = ?";
    private static final String UPDATE_PRODUCT = "UPDATE products SET title = ?, " +
            "description = ?, price = ? WHERE id = ?";
    private static final String GET_BY_ID = "SELECT * FROM products WHERE id = ?";
    private static final String GET_ALL = "SELECT * FROM products";

    @Override
    public void addProduct(Product product) {
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUCT);
            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setBigDecimal(3, product.getPrice());
            preparedStatement.execute();
            logger.info("Product " + product + " was added to DB");
        } catch (SQLException e) {
            logger.error("Adding prpoduct " + product + " was failed", e);
        }
    }

    @Override
    public void removeProduct(Long id) {
        try(Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_PRODUCT);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            logger.info("Product with id = " + id + " was removed from db");
        } catch (SQLException e) {
            logger.error("Removing product with id = " + id + " from db was failed", e);
        }
    }

    @Override
    public void updateProduct(Long id, Product product) {
        try(Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT);
            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setBigDecimal(3, product.getPrice());
            preparedStatement.setLong(4, product.getId());
            preparedStatement.execute();
            logger.info("Product " + product + " with id = " + id + " was updated");
        } catch (SQLException e) {
            logger.error("Updating product " + product + " with id = " + id + " was failed", e);
        }
    }

    @Override
    public Optional<Product> getById(Long id) {
        try(Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Product product = new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getBigDecimal("price")
                );
                return Optional.of(product);
            }
        } catch (SQLException e) {
            logger.error("No product with such id " + id + " in db", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Product> getAll() {
        List<Product> allProducts = new ArrayList<>();
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getBigDecimal("price"));
                allProducts.add(product);
            }
        } catch (SQLException e) {
            logger.error("Getting all products was failed", e);
        }
        return allProducts;
    }
}
