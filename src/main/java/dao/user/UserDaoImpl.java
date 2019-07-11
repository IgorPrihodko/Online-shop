package dao.user;

import model.User;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);
    private static final List<User> USERS = new ArrayList<>();
    private static Long idCounter = 0L;

    @Override
    public synchronized Long createID() {
        logger.info("Increment id counter for user");
        return idCounter++;
    }

    @Override
    public void addUser(User user) {
        user.setId(createID());
        USERS.add(user);
        logger.info("Add user " + user + " to db");
    }

    @Override
    public void removeUser(Long id) {
        for(User user : USERS) {
            if (user.getId().equals(id)) {
                USERS.remove(user);
                logger.info("Remove user " + user + " from db");
            }
        }
    }

    @Override
    public User getByEmai(String email) {
        for(User user : USERS) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User getById(Long id) {
        for (User user : USERS) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        return USERS;
    }
}
