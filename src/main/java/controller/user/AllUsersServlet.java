package controller.user;

import factory.UserServiceFactory;
import model.User;
import service.user.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/users", loadOnStartup = 2)
public class AllUsersServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getInstance();
    private List<User> allUsers = userService.getAll();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("allUsers", allUsers);
        req.getRequestDispatcher("users.jsp").forward(req, resp);
    }
}
