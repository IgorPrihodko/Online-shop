package controller.user;

import dao.user.UserDao;
import factory.UserServiceFactory;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/users", loadOnStartup = 2)
public class AllUsersServlet extends HttpServlet {

    private static final UserDao userDao = UserServiceFactory.getInstance();
    private List<User> allusers = userDao.getAll();

    public void initialUser() {
        if (allusers.isEmpty()) {
            User initialUser = new User("test@test", "test");
            userDao.addUser(initialUser);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("allUsers", allusers);
        req.getRequestDispatcher("users.jsp").forward(req, resp);
    }
}
