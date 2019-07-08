package controller.user;

import com.sun.istack.internal.NotNull;
import dao.user.UserDao;
import factory.UserDaoFactory;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/register", loadOnStartup = 1)
public class UserRegistrationServlet extends HttpServlet {

    private UserDao userDao = UserDaoFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    @NotNull
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String email = "";
        String password = "";
        String repeatPassword = "";
        if (req.getParameter("email").isEmpty() ||
                req.getParameter("password").isEmpty() ||
                req.getParameter("repeatPassword").isEmpty()) {
            if (req.getParameter("email").isEmpty()) {
                req.setAttribute("isEmpty", "Email can not be empty! Try another.");
            } else {
                req.setAttribute("email", req.getParameter("email"));
            }
            if (req.getParameter("password").isEmpty()) {
                req.setAttribute("isEmpty", "Password can not be empty! Try another.");
            } else {
                req.setAttribute("password", req.getParameter("password"));
            }
            if (req.getParameter("repeatPassword").isEmpty()) {
                req.setAttribute("isEmpty", "Password can not be empty! Try another.");
            } else {
                req.setAttribute("repeatPassword", req.getParameter("repeatPassword"));
            }
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            resp.sendRedirect("/register");
        }
        email = req.getParameter("email");
        password = req.getParameter("password");
        repeatPassword = req.getParameter("repeatPassword");

        if (!repeatPassword.equals(password)) {
            req.setAttribute("notEquals", "Your passwords are not equals! Try better.");
            req.setAttribute("email", req.getParameter("email"));
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            resp.sendRedirect("/register");
        } else {
            User user = new User(email, password);
            userDao.addUser(user);
            System.out.println(user);
            resp.sendRedirect("/users");
        }
    }
}
