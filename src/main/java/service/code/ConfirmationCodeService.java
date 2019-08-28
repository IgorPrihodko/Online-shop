package service.code;

import model.ConfirmationCode;

import java.util.List;
import java.util.Optional;

public interface ConfirmationCodeService {

    Long createID();

    void addConfirmationCode(ConfirmationCode confirmationCode);

    void removeConfirmationCode(Long id);

    Optional<ConfirmationCode> getById(Long id);

    Optional<ConfirmationCode> getByBasketID(Long id);

    List<ConfirmationCode> getAll();

    List<ConfirmationCode> getAllByUserEmail(String userEmail);
}
