package controller;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String repeatPassword = req.getParameter("repeatPassword");

        if (!repeatPassword.equals(password)) {
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
            System.out.println("Passwords are not equals");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }

        User user = new User(email, password);
        DataBase.USERS.put(user.getId(), user);
        resp.setStatus(HttpServletResponse.SC_OK);
        System.out.println(user);
        resp.sendRedirect("/addArticle");
    }
}
