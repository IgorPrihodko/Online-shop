package dao.code;

import model.ConfirmationCode;
import model.User;

import java.util.Optional;

public interface ConfirmationCodeDao {

    void addConfirmationCode(ConfirmationCode confirmationCode);

    void removeConfirmationCode(Long id);

    Optional<ConfirmationCode> getByUserEmail(String userEmail);

    Optional<ConfirmationCode> getLastConfirmationCodeForUser(User user);
}
