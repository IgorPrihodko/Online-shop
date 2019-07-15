package dao.user;

import model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    Long createID();
    void addUser(User user);
    void removeUser(Long id);
    Optional<User> getByEmail(String email);
    Optional<User> getById (Long id);
    List<User> getAll();
}
