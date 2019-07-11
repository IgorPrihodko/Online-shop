package service.user;

import model.User;

import java.util.List;

public interface UserService {

    Long createId();
    void addUser(User user);
    void removeUser(Long id);
    User getByEmai(String email);
    User getById(Long id);
    List<User> getAll();
}
