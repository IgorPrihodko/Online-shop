package dao.user;

import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static final List<User> USERS = new ArrayList<>();
    private static Long idCounter = 0L;

    @Override
    public synchronized Long createID() {
        return idCounter++;
    }

    @Override
    public void addUser(User user) {
        user.setId(createID());
        USERS.add(user);
    }

    @Override
    public void removeUser(Long id) {
        for(User user : USERS) {
            if (user.getId().equals(id)) {
                USERS.remove(user);
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
