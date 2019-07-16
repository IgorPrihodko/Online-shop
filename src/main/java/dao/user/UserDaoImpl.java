package dao.user;

import model.User;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);
    private static final List<User> users = new ArrayList<>();
    private static Long idCounter = 0L;

    @Override
    public synchronized Long createID() {
        logger.info("Increment id counter for user");
        return idCounter++;
    }

    @Override
    public void addUser(User user) {
        user.setId(createID());
        users.add(user);
        logger.info("Add user " + user + " to db");
    }

    @Override
    public void removeUser(Long id) {
        users.remove(users.stream().filter(user -> user.getId().equals(id)).findFirst().get());
        logger.info("Remove user from db");
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
