package factory;

import dao.user.UserDao;
import dao.user.UserDaoJDBC;

public class UserDaoFactory {

    private static UserDao instance;

    public static synchronized UserDao getInstance() {
        if (instance == null) {
            instance = new UserDaoJDBC();
        }
        return instance;
    }

}
