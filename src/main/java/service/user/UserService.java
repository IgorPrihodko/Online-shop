package service.user;

import model.User;

import java.util.Map;

public interface UserService {
    Long createId();
    void addUser(User user);
    Map<Long, User> getAll();
}
