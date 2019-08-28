package service.code;

import model.ConfirmationCode;
import model.User;

import java.util.List;
import java.util.Optional;

public interface ConfirmationCodeService {

    void addConfirmationCode(ConfirmationCode confirmationCode);

    void removeConfirmationCode(Long id);

    List<ConfirmationCode> getByUserEmail(String userEmail);

    Optional<ConfirmationCode> getLastConfirmationCodeForUser(User user);
}
