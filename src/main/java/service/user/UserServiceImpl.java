package service.user;

import dao.user.UserDao;
import factory.UserServiceFactory;
import model.User;

import java.util.List;

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
    public void removeUser(Long id) {
        userDao.removeUser(id);
    }

    @Override
    public User getByEmai(String email) {
        return userDao.getByEmai(email);
    }

    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }
}
