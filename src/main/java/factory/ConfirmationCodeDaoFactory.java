package factory;

import dao.code.ConfirmationCodeDao;
import dao.code.ConfirmationCodeDaoJDBC;

public class ConfirmationCodeDaoFactory {

    private static ConfirmationCodeDao instance;

    public static synchronized ConfirmationCodeDao getInstance() {
        if (instance == null) {
            instance = new ConfirmationCodeDaoJDBC();
        }
        return instance;
    }
}
