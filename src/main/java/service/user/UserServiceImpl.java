package service.user;

import dao.user.UserDao;
import factory.UserDaoFactory;
import model.User;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final UserDao userDao = UserDaoFactory.getInstance();

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
    public Optional<User> getByEmail(String email) {
        return userDao.getByEmail(email);
    }

    @Override
    public Optional<User> getById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }
}
