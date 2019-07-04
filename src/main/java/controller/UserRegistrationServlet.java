package controller;

import com.sun.istack.internal.NotNull;
import dao.DataBase;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/register", loadOnStartup = 1)
public class UserRegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    @NotNull
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (req.getParameter("email").isEmpty() ||
                req.getParameter("password").isEmpty() ||
                req.getParameter("repeatPassword").isEmpty()) {
            req.setAttribute("isEmpty", "One or more fields are empty! Try better.");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            resp.sendRedirect("/register");
        }
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String repeatPassword = req.getParameter("repeatPassword");

        if (!repeatPassword.equals(password)) {
            req.setAttribute("notEquals", "Your passwords are not equals! Try better.");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        } else {
            User user = new User(email, password);
            DataBase.USERS.put(user.getId(), user);
            System.out.println(user);
            resp.sendRedirect("/addArticle");
        }
    }
}
