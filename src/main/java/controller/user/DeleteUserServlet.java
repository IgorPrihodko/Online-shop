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

@WebServlet(value = "/deleteUser")
public class DeleteUserServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(DeleteUserServlet.class);
    private static final UserService userService = UserServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        User user = userService.getById(id).get();
        req.setAttribute("id", user.getId());
        req.setAttribute("email", user.getEmail());
        req.setAttribute("password", user.getPassword());
        req.getRequestDispatcher("deleteUser.jsp").forward(req, resp);
        resp.sendRedirect("/deleteUser");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        User user = userService.getById(id).get();
        req.setAttribute("id", id);
        userService.removeUser(id);
        logger.info("Delete user " + user + " from db");
        resp.sendRedirect("/users");
    }
}
