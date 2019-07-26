package dao.user;

import model.User;
import org.apache.log4j.Logger;
import utils.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoJDBC implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoJDBC.class);
    private static final String ADD_USER = "INSERT INTO users (email, password, role) " +
            "VALUES (?, ?, ?)";
    private static final String REMOVE_USER = "DELETE FROM users WHERE id = ?";
    private static final String UPDATE_USER = "UPDATE users SET email = ?, password = ?, " +
            "role = ? WHERE id = ?";
    private static final String GET_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    private static final String GET_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String GET_ALL = "SELECT * FROM users";

    @Override
    public void addUser(User user) {
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.execute();
            logger.info("User " + user + " was added to DB");
        } catch (SQLException e) {
            logger.error("Adding user " + user + " was failed", e);
        }
    }

    @Override
    public void removeUser(Long id) {
        try(Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_USER);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            logger.info("User with id = " + id + " was removed from db");
        } catch (SQLException e) {
            logger.error("Removing user with id = " + id + " from db was failed", e);
        }
    }

    @Override
    public void updateUser(Long id, User user) {
        try(Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.setLong(4, user.getId());
            preparedStatement.execute();
            logger.info("User " + user + " with id = " + id + " was updated");
        } catch (SQLException e) {
            logger.error("Updating user " + user + " with id = " + id + " was failed", e);
        }
    }

    @Override
    public Optional<User> getByEmail(String email) {
        try(Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role")
                );
                return Optional.of(user);
            }
        } catch (SQLException e) {
            logger.error("No user with such email " + email + " in db", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getById(Long id) {
        try(Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role")
                );
                return Optional.of(user);
            }
        } catch (SQLException e) {
            logger.error("No user with such id " + id + " in db", e);
        }
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        List<User> allUsers = new ArrayList<>();
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role"));
                allUsers.add(user);
            }
        } catch (SQLException e) {
            logger.error("Getting all users was failed", e);
        }
        return allUsers;
    }
}
