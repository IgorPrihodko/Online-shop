package factory;

import dao.user.UserDao;
import dao.user.UserDaoImpl;

public class UserServiceFactory {
    private static UserDao instance;

    public static synchronized UserDao getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
        }
        return instance;
    }
}
