package service.code;

import dao.code.ConfirmationCodeDao;
import factory.ConfirmationCodeDaoFactory;
import model.ConfirmationCode;

import java.util.List;
import java.util.Optional;

public class ConfirmationCodeServiceImpl implements ConfirmationCodeService {

    private static final ConfirmationCodeDao confirmationCodeDao = ConfirmationCodeDaoFactory.getInstance();

    @Override
    public Long createID() {
        return confirmationCodeDao.createID();
    }

    @Override
    public void addConfirmationCode(ConfirmationCode confirmationCode) {
        confirmationCodeDao.addConfirmationCode(confirmationCode);
    }

    @Override
    public void removeConfirmationCode(Long id) {
        confirmationCodeDao.removeConfirmationCode(id);
    }

    @Override
    public Optional<ConfirmationCode> getById(Long id) {
        return confirmationCodeDao.getById(id);
    }

    @Override
    public Optional<ConfirmationCode> getByBasketID(Long id) {
        return confirmationCodeDao.getByBasketID(id);
    }

    @Override
    public List<ConfirmationCode> getAll() {
        return confirmationCodeDao.getAll();
    }

    @Override
    public List<ConfirmationCode> getAllByUserEmail(String userEmail) {
        return confirmationCodeDao.getAllByUserEmail(userEmail);
    }
}
