package service.code;

import dao.code.ConfirmationCodeDao;
import factory.ConfirmationCodeDaoFactory;
import model.ConfirmationCode;
import model.User;

import java.util.List;
import java.util.Optional;

public class ConfirmationCodeServiceImpl implements ConfirmationCodeService {

    private static final ConfirmationCodeDao confirmationCodeDao =
            ConfirmationCodeDaoFactory.getInstance();

    @Override
    public void addConfirmationCode(ConfirmationCode confirmationCode) {
        confirmationCodeDao.addConfirmationCode(confirmationCode);
    }

    @Override
    public void removeConfirmationCode(Long id) {
        confirmationCodeDao.removeConfirmationCode(id);
    }

    @Override
    public List<ConfirmationCode> getByUserEmail(String userEmail) {
        return confirmationCodeDao.getByUserEmail(userEmail);
    }

    @Override
    public Optional<ConfirmationCode> getLastConfirmationCodeForUser(User user) {
        return confirmationCodeDao.getLastConfirmationCodeForUser(user);
    }
}
