package controller.user;

import factory.OrderServiceFactory;
import model.ConfirmationCode;
import model.Order;
import model.User;
import service.order.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/user/order")
public class OrderServlet extends HttpServlet {

    private static final OrderService orderService = OrderServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User userFromSession = (User) req.getSession().getAttribute("user");
        Order order = new Order(userFromSession.getId(), userFromSession.getBasket(),
                userFromSession.getEmail());
        orderService.addOrder(order);
        req.setAttribute("userID", userFromSession.getId());
        req.setAttribute("userEmail", userFromSession.getEmail());
        req.setAttribute("totalPrice", userFromSession.getBasket().getTotalPrice());
        req.getRequestDispatcher("/add_order.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User userFromSession = (User) req.getSession().getAttribute("user");
        req.setAttribute("userID", userFromSession.getId());
        req.setAttribute("userEmail", userFromSession.getEmail());
        req.setAttribute("totalPrice", userFromSession.getBasket().getTotalPrice());
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String address = req.getParameter("address");
        String code = req.getParameter("confirmationCode");
        if (name.isEmpty() || surname.isEmpty() || address.isEmpty() || code.isEmpty()) {
            if (name.isEmpty()) {
                req.setAttribute("error", "name can not be empty! Try another.");
            } else {
                req.setAttribute("name", name);
            }
            if (surname.isEmpty()) {
                req.setAttribute("error", "surname can not be empty! Try another.");
            } else {
                req.setAttribute("surname", surname);
            }
            if (address.isEmpty()) {
                req.setAttribute("error", "address can not be empty! Try another.");
            } else {
                req.setAttribute("address", address);
            }
            if (code.isEmpty()) {
                req.setAttribute("error", "code can not be empty! Try another.");
            } else {
                req.setAttribute("code", code);
            }
            req.getRequestDispatcher("/add_order.jsp").forward(req, resp);
            resp.sendRedirect("/user/order");
        }

        ConfirmationCode confirmationCode = userFromSession.getBasket().getConfirmationCode();
        if (code.equals(confirmationCode.getCode())) {
            Order order = new Order(userFromSession.getId(), userFromSession.getBasket(),
                    userFromSession.getEmail());
            order.setName(name);
            order.setSurname(surname);
            order.setAddress(address);
            orderService.addOrder(order);
            userFromSession.getOrders().add(order);
            req.setAttribute("success", "Successfull purchase!");
            req.getRequestDispatcher("/account.jsp").forward(req, resp);
            resp.sendRedirect("/signIn");
        } else {
            req.setAttribute("error", "Wrong confirmation code! Try another");
            req.getRequestDispatcher("/add_order.jsp").forward(req, resp);
            resp.sendRedirect("/user/order");
        }
    }
}
