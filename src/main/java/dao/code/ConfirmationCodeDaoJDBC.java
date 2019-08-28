/*
package dao.code;

import model.ConfirmationCode;
import model.User;
import org.apache.log4j.Logger;
import utils.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ConfirmationCodeDaoJDBC implements ConfirmationCodeDao {

    private static final Logger logger = Logger.getLogger(ConfirmationCodeDaoJDBC.class);
    private static final String ADD_CONFIRMATION_CODE = "INSERT INTO confirmation_codes " +
            "(code, user_email) VALUES (?, ?)";
    private static final String REMOVE_CONFIRMATION_CODE = "DELETE FROM confirmation_codes " +
            "WHERE id = ?";
    private static final String GET_BY_USER_EMAIL = "SELECT * FROM confirmation_codes " +
            "WHERE user_email = ?";
    private static final String GET_LAST_CONFIRMATION_CODE_FOR_USER = "SELECT * FROM " +
            "confirmation_codes WHERE user_email = ? ORDER BY id DESC LIMIT 1";

    @Override
    public void addConfirmationCode(ConfirmationCode confirmationCode) {
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_CONFIRMATION_CODE);
            preparedStatement.setString(1, confirmationCode.getCode());
            preparedStatement.setString(2, confirmationCode.getUser().getEmail());
            preparedStatement.execute();
            logger.info("Confirmation code " + confirmationCode + " was added to DB");
        } catch (SQLException e) {
            logger.error("Adding confirmation code " + confirmationCode + " was failed", e);
        }
    }

    @Override
    public void removeConfirmationCode(Long id) {
        try(Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(REMOVE_CONFIRMATION_CODE);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            logger.info("Confirmation code with id = " + id + " was removed from db");
        } catch (SQLException e) {
            logger.error("Removing confirmation code with id = " + id +
                    " from db was failed", e);
        }
    }

    @Override
    public Optional<ConfirmationCode> getByUserEmail(String userEmail) {
        try(Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_USER_EMAIL);
            preparedStatement.setString(1, userEmail);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                ConfirmationCode confirmationCode = new ConfirmationCode(
                        resultSet.getString("code"),
                        resultSet.getString("user")
                );
                return Optional.of(confirmationCode);
            }
        } catch (SQLException e) {
            logger.error("No confirmation code with such user email " + userEmail +
                    " in db", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<ConfirmationCode> getLastConfirmationCodeForUser(User user) {
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(GET_LAST_CONFIRMATION_CODE_FOR_USER);
            preparedStatement.setString(1, user.getEmail());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                ConfirmationCode confirmationCode = new ConfirmationCode(
                        resultSet.getLong("id"),
                        resultSet.getString("code"),
                        resultSet.getString("user_email")
                );
                return Optional.of(confirmationCode);
            }
        } catch (SQLException e) {
            logger.error("Getting confirmation code was failed", e);
        }
        return Optional.empty();
    }
}
*/
