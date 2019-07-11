package controller.user;

import dao.user.UserDao;
import factory.UserServiceFactory;
import model.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/register")
public class UserRegistrationServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(UserRegistrationServlet.class);
    private UserDao userDao = UserServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String repeatPassword = req.getParameter("repeatPassword");

        if (email.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) {
            if (email.isEmpty()) {
                req.setAttribute("error", "Email can not be empty! Try another.");
            } else {
                req.setAttribute("email", email);
            }
            if (password.isEmpty()) {
                req.setAttribute("error", "Password can not be empty! Try another.");
            } else {
                req.setAttribute("password", password);
            }
            if (repeatPassword.isEmpty()) {
                req.setAttribute("error", "Password can not be empty! Try another.");
            } else {
                req.setAttribute("repeatPassword", repeatPassword);
            }
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            resp.sendRedirect("/register");
        }

        if (userDao.getByEmai(email) != null) {
            req.setAttribute("error", "Email already registered! Try another.");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            resp.sendRedirect("/register");
        }

        if (!repeatPassword.equals(password)) {
            req.setAttribute("error", "Your passwords are not equals! Try better.");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            resp.sendRedirect("/register");
        } else {
            User user = new User(email, password);
            userDao.addUser(user);
            logger.info("Add new user " + user + " to db");
            resp.sendRedirect("/users");
        }
    }
}
