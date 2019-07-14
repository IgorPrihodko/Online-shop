package factory;

import service.user.UserService;
import service.user.UserServiceImpl;

public class UserServiceFactory {

    private static UserService instance;

    public static synchronized UserService getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }
}
