package controller;

import factory.UserServiceFactory;
import model.Basket;
import model.User;
import org.apache.log4j.Logger;
import service.user.UserService;
import utils.HashUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

@WebServlet(value = "/signIn")
public class SignInServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(SignInServlet.class);
    private static final UserService userService = UserServiceFactory.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (email.isEmpty() || password.isEmpty()) {
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
        }

        Optional<User> optionalUser = userService.getByEmail(email);
        if (optionalUser.isPresent()) {
            try {
                Boolean isValid = HashUtil.validatePassword(password,
                        optionalUser.get().getPassword());
                if (isValid) {
                    HttpSession session = req.getSession();
                    session.setAttribute("user", optionalUser.get());
                    logger.info("Sign in user " + optionalUser.get());
                    if (optionalUser.get().getRole().equals("admin")) {
                        req.setAttribute("allUsers", userService.getAll());
                        req.getRequestDispatcher("/users.jsp").forward(req, resp);
                    } else {
                        Basket basket = new Basket(optionalUser.get().getId());
                        session.setAttribute("basket", basket);
                        req.getRequestDispatcher("/account.jsp").forward(req, resp);
                    }
                } else {
                    req.setAttribute("error", "Wrong email or password");
                    req.getRequestDispatcher("index.jsp").forward(req, resp);
                    resp.sendRedirect("/");
                }
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                logger.error("Can not validate password");
                req.setAttribute("error", "Wrong email or password");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
                resp.sendRedirect("/");
            }
        }
    }
}
