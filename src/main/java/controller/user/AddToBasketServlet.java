package controller.user;

import factory.ProductServiceFactory;
import model.Basket;
import model.Product;
import model.User;
import service.product.ProductService;
import utils.TotalPriceCounter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/user/basket")
public class AddToBasketServlet extends HttpServlet {

    private static final ProductService productService = ProductServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User userFromSession = (User) req.getSession().getAttribute("user");
        Basket basketFromSession = (Basket) req.getSession().getAttribute("basket");
        basketFromSession.setUser(userFromSession);
        basketFromSession.setTotalPrice(TotalPriceCounter.count(basketFromSession));
        req.setAttribute("totalPrice", basketFromSession.getTotalPrice());
        req.setAttribute("basket", basketFromSession.getProductsInBasket());
        req.getRequestDispatcher("/basket.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        Product product = productService.getById(id).get();
        req.setAttribute("id", id);
        User userFromSession = (User) req.getSession().getAttribute("user");
        Basket basketFromSession = (Basket) req.getSession().getAttribute("basket");
        basketFromSession.setUser(userFromSession);
        basketFromSession.addProductToBasket(product);
        basketFromSession.setTotalPrice(TotalPriceCounter.count(basketFromSession));
        req.setAttribute("allProducts", productService.getAll());
        req.getRequestDispatcher("/products_for_users.jsp").forward(req, resp);
        resp.sendRedirect("/user/products");
    }
}
