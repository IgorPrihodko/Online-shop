package service.user;

import dao.user.UserDao;
import factory.UserServiceFactory;
import model.User;

import java.util.Map;

public class UserServiceImpl implements UserService {
    private  static UserDao userDao = UserServiceFactory.getInstance();

    @Override
    public Long createId() {
        return userDao.createID();
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public Map<Long, User> getAll() {
        return userDao.getAll();
    }
}
