package dao.user;

import model.User;

import java.util.HashMap;
import java.util.Map;

public class UserDaoImpl implements UserDao {
    public static final Map<Long, User> USERS = new HashMap<Long, User>();

    @Override
    public void addUser(User user) {
        USERS.put(user.getId(), user);
    }

    @Override
    public Map<Long, User> getAll() {
        return USERS;
    }
}
