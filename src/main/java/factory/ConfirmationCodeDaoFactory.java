package factory;

import dao.code.ConfirmationCodeDao;
import dao.code.ConfirmationCodeDaoImpl;

public class ConfirmationCodeDaoFactory {

    private static ConfirmationCodeDao instance;

    public static synchronized ConfirmationCodeDao getInstance() {
        if (instance == null) {
            instance = new ConfirmationCodeDaoImpl();
        }
        return instance;
    }
}
