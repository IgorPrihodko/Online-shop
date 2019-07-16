package controller.user;

import factory.UserServiceFactory;
import model.User;
import org.apache.log4j.Logger;
import service.user.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/editUser")
public class EditUserServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(EditUserServlet.class);
    private static final UserService userService = UserServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        User user = userService.getById(id).get();
        req.setAttribute("id", user.getId());
        req.setAttribute("email", user.getEmail());
        req.setAttribute("password", user.getPassword());
        req.setAttribute("repeatPassword", user.getPassword());
        req.getRequestDispatcher("editUser.jsp").forward(req, resp);
        resp.sendRedirect("/editUser");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        User user = userService.getById(id).get();
        req.setAttribute("id", id);
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
            req.getRequestDispatcher("editUser.jsp").forward(req, resp);
            resp.sendRedirect("/editUser");
        }

        if (userService.getByEmail(email).isPresent()
                && !user.getEmail().equals(email)) {
            req.setAttribute("error", "Email already registered! Try another.");
            req.getRequestDispatcher("editUser.jsp").forward(req, resp);
            resp.sendRedirect("/editUser");
        }
        if (!repeatPassword.equals(password)) {
            req.setAttribute("error", "Your passwords are not equals! Try better.");
            req.setAttribute("email", req.getParameter("email"));
            req.getRequestDispatcher("editUser.jsp").forward(req, resp);
            resp.sendRedirect("/editUser");
        } else {
            user.setEmail(email);
            user.setPassword(password);
            logger.info("Edit user data " + user + " in db");
        }
        resp.sendRedirect("/users");
    }
}
