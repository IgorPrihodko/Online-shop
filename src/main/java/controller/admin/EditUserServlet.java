package controller.admin;

import factory.UserServiceFactory;
import model.User;
import org.apache.log4j.Logger;
import service.user.UserService;
import utils.HashUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@WebServlet(value = "/admin/editUser")
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
        req.setAttribute("role", user.getRole());
        req.getRequestDispatcher("/editUser.jsp").forward(req, resp);
        resp.sendRedirect("/admin/editUser");
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
        String role = req.getParameter("role");

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
            req.getRequestDispatcher("/editUser.jsp").forward(req, resp);
            resp.sendRedirect("/admin/editUser");
        }

        if (userService.getByEmail(email).isPresent() && !user.getEmail().equals(email)) {
            req.setAttribute("error", "Email already registered! Try another.");
            req.getRequestDispatcher("/editUser.jsp").forward(req, resp);
            resp.sendRedirect("/admin/editUser");
        }
        if (!repeatPassword.equals(password)) {
            req.setAttribute("error", "Your passwords are not equals! Try better.");
            req.setAttribute("email", req.getParameter("email"));
            req.getRequestDispatcher("/editUser.jsp").forward(req, resp);
            resp.sendRedirect("/admin/editUser");
        } else {
            if (user.getPassword().equals(password)) {
                user.setEmail(email);
                user.setPassword(password);
                user.setRole(role);
            } else {
                try {
                    String hashPassword = HashUtil.generateHashPassword(password);
                    user.setEmail(email);
                    user.setPassword(hashPassword);
                    user.setRole(role);
                } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                    logger.error("Can not hashing password", e);
                    req.setAttribute("email", email);
                    req.setAttribute("error", "Bad type of password! Try another.");
                    req.getRequestDispatcher("/editUser.jsp").forward(req, resp);
                    resp.sendRedirect("/admin/editUser");
                }
            }
            userService.updateUser(id, user);
            logger.warn("Edit user data " + user + " in db");
        }
        resp.sendRedirect("/admin/users");
    }
}
