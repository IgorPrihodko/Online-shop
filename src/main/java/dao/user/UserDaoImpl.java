package dao.user;

import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private static final List<User> users = new ArrayList<>();
    private static Long idCounter = 0L;

    @Override
    public synchronized Long createID() {
        return idCounter++;
    }

    @Override
    public void addUser(User user) {
        user.setId(createID());
        users.add(user);
    }

    @Override
    public void removeUser(Long id) {
        users.remove(users.stream().filter(user -> user.getId().equals(id)).findFirst().get());
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return users.stream().filter(user -> user.getEmail().equals(email)).findFirst();
    }

    @Override
    public Optional<User> getById(Long id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    @Override
    public List<User> getAll() {
        return users;
    }
}
