package factory;

import dao.user.UserDao;
import dao.user.UserDaoHibernate;

public class UserDaoFactory {

    private static UserDao instance;

    public static synchronized UserDao getInstance() {
        if (instance == null) {
            instance = new UserDaoHibernate();
        }
        return instance;
    }

}
