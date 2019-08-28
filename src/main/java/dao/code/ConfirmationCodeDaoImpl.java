package dao.code;

import model.ConfirmationCode;
import model.User;
import org.apache.log4j.Logger;
import utils.IDCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ConfirmationCodeDaoImpl implements ConfirmationCodeDao {

    private static final Logger logger = Logger.getLogger(ConfirmationCodeDaoImpl.class);
    private static final List<ConfirmationCode> confirmationCodes = new ArrayList<>();
    private static Long idCounter = 0L;

    @Override
    public void addConfirmationCode(ConfirmationCode confirmationCode) {
        confirmationCode.setId(IDCreator.create(idCounter));
        idCounter++;
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
    public List<ConfirmationCode> getByUserEmail(String userEmail) {
        return confirmationCodes
                .stream()
                .filter(confirmationCode -> confirmationCode.getUser().getEmail().equals(userEmail))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ConfirmationCode> getLastConfirmationCodeForUser(User user) {
        return confirmationCodes.stream()
                .filter(confirmationCode -> confirmationCode.getUser().equals(user))
                .min((o1, o2) -> (int) (o2.getId() - o1.getId()));
    }
}
