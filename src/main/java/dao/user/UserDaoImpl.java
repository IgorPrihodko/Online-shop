package dao.user;

import model.User;

import java.util.HashMap;
import java.util.Map;

public class UserDaoImpl implements UserDao {
    private static final Map<Long, User> USERS = new HashMap<>();
    private static Long idCounter = 1L;

    @Override
    public synchronized Long createID() {
        return idCounter++;
    }

    @Override
    public void addUser(User user) {
        user.setId(createID());
        USERS.put(user.getId(), user);
    }

    @Override
    public Map<Long, User> getAll() {
        return USERS;
    }
}
