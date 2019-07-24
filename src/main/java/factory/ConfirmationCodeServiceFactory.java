package factory;

import service.code.ConfirmationCodeService;
import service.code.ConfirmationCodeServiceImpl;

public class ConfirmationCodeServiceFactory {

    private static ConfirmationCodeService instance;

    public static synchronized ConfirmationCodeService getInstance() {
        if (instance == null) {
            instance = new ConfirmationCodeServiceImpl();
        }
        return instance;
    }
}
