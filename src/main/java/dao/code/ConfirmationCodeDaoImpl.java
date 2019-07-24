package dao.code;

import model.ConfirmationCode;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ConfirmationCodeDaoImpl implements ConfirmationCodeDao {

    private static final Logger logger = Logger.getLogger(ConfirmationCodeDaoImpl.class);
    private static final List<ConfirmationCode> confirmationCodes = new ArrayList<>();
    private static Long idCounter = 1L;

    @Override
    public synchronized Long createID() {
        logger.info("Increment id counter for confirmation code");
        return idCounter++;
    }

    @Override
    public void addConfirmationCode(ConfirmationCode confirmationCode) {
        confirmationCode.setId(createID());
        confirmationCodes.add(confirmationCode);
        logger.info("Add confirmation code " + confirmationCode + " to db");
    }

    @Override
    public void removeConfirmationCode(Long id) {
        confirmationCodes
                .remove(confirmationCodes
                        .stream()
                        .filter(confirmationCode -> confirmationCode.getId().equals(id))
                        .findFirst()
                        .get());
        logger.info("Remove confirmation code from db");
    }

    @Override
    public Optional<ConfirmationCode> getById(Long id) {
        return confirmationCodes
                .stream()
                .filter(confirmationCode -> confirmationCode.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<ConfirmationCode> getByBasketID(Long id) {
        return confirmationCodes
                .stream()
                .filter(confirmationCode -> confirmationCode.getBasketID().equals(id))
                .findFirst();
    }

    @Override
    public List<ConfirmationCode> getAll() {
        return confirmationCodes;
    }

    @Override
    public List<ConfirmationCode> getAllByUserEmail(String userEmail) {
        return confirmationCodes
                .stream()
                .filter(confirmationCode -> confirmationCode.getUserEmail().equals(userEmail))
                .collect(Collectors.toList());
    }
}
