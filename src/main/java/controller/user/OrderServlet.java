package controller.user;

import factory.BasketServiceFactory;
import factory.ConfirmationCodeServiceFactory;
import factory.OrderServiceFactory;
import model.Basket;
import model.ConfirmationCode;
import model.StockOnOrder;
import model.User;
import service.basket.BasketService;
import service.code.ConfirmationCodeService;
import service.order.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(value = "/user/order")
public class OrderServlet extends HttpServlet {

    private static final OrderService orderService = OrderServiceFactory.getInstance();
    private static final BasketService basketService = BasketServiceFactory.getInstance();
    private static final ConfirmationCodeService confirmationCodeService =
            ConfirmationCodeServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User userFromSession = (User) req.getSession().getAttribute("user");
        Basket basketFromSession = (Basket) req.getSession().getAttribute("basket");
        if (basketFromSession == null || basketFromSession.getProductsInBasket().isEmpty()) {
            req.setAttribute("totalPrice", BigDecimal.ZERO);
            req.setAttribute("error", "Your basket is empty! Nothing to buy.");
        }
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String address = req.getParameter("address");

        req.setAttribute("name", name);
        req.setAttribute("surname", surname);
        req.setAttribute("address", address);
        req.setAttribute("userID", userFromSession.getId());
        req.setAttribute("userEmail", userFromSession.getEmail());
        req.setAttribute("totalPrice", basketFromSession.getTotalPrice());
        req.getRequestDispatcher("/add_order.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User userFromSession = (User) req.getSession().getAttribute("user");
        Basket basketFromSession = (Basket) req.getSession().getAttribute("basket");
        if (basketFromSession == null || basketFromSession.getProductsInBasket().isEmpty()) {
            req.setAttribute("totalPrice", BigDecimal.ZERO);
            req.setAttribute("error", "Your basket is empty! Nothing to buy.");
            req.getRequestDispatcher("/add_order.jsp").forward(req, resp);
            resp.sendRedirect("/user/order");
        }
        req.setAttribute("userID", userFromSession.getId());
        req.setAttribute("userEmail", userFromSession.getEmail());
        req.setAttribute("totalPrice", basketFromSession.getTotalPrice());
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String address = req.getParameter("address");
        String code = req.getParameter("confirmationCode");

        if (name.isEmpty() || surname.isEmpty() || address.isEmpty() || code.isEmpty()) {
            if (name.isEmpty()) {
                req.setAttribute("error", "Name can not be empty! Try another.");
            } else {
                req.setAttribute("name", name);
            }
            if (surname.isEmpty()) {
                req.setAttribute("error", "Surname can not be empty! Try another.");
            } else {
                req.setAttribute("surname", surname);
            }
            if (address.isEmpty()) {
                req.setAttribute("error", "Address can not be empty! Try another.");
            } else {
                req.setAttribute("address", address);
            }
            if (code.isEmpty()) {
                req.setAttribute("error", "Confirmation code can not be empty!");
            } else {
                req.setAttribute("code", code);
            }
            req.getRequestDispatcher("/add_order.jsp").forward(req, resp);
            resp.sendRedirect("/user/order");
        }
        req.setAttribute("name", name);
        req.setAttribute("surname", surname);
        req.setAttribute("address", address);

        ConfirmationCode confirmationCode = basketFromSession.getConfirmationCode();
        if (confirmationCode == null || !code.equals(confirmationCode.getCode())) {
            req.setAttribute("error", "Wrong confirmation code! Try another");
            req.getRequestDispatcher("/add_order.jsp").forward(req, resp);
            resp.sendRedirect("/user/order");
        } else {
            confirmationCode.getUser().setEmail(userFromSession.getEmail());
            confirmationCodeService.addConfirmationCode(confirmationCode);
            ConfirmationCode codeFromDB =
                    confirmationCodeService.getLastConfirmationCodeForUser(userFromSession).get();

            basketFromSession.setConfirmationCode(codeFromDB);
            basketService.addBasket(basketFromSession);
            Basket basketFromDB = basketService.getLastBasketForUser(userFromSession).get();

            StockOnOrder stockOnOrder = new StockOnOrder(userFromSession, userFromSession.getEmail());
            stockOnOrder.setName(name);
            stockOnOrder.setSurname(surname);
            stockOnOrder.setAddress(address);
            stockOnOrder.setBasket(basketFromDB);
            orderService.addOrder(stockOnOrder);

            req.setAttribute("success", "Successfull purchase!");
            HttpSession session = req.getSession();
            Basket basket = new Basket(userFromSession);
            session.setAttribute("basket", basket);
            req.getRequestDispatcher("/account.jsp").forward(req, resp);
            resp.sendRedirect("/signIn");
        }
    }
}
