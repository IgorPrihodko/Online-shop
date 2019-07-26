package service.user;

import model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void addUser(User user);

    void removeUser(Long id);

    void updateUser(Long id, User user);

    Optional<User> getByEmail(String email);

    Optional<User> getById(Long id);

    List<User> getAll();
}
