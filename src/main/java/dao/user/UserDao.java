package dao.user;

import model.User;

import java.util.Map;

public interface UserDao {
    Long createID();
    void addUser(User user);
    Map<Long, User> getAll();
}
