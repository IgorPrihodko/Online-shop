package controller.user;

import factory.ProductServiceFactory;
import model.Basket;
import model.Product;
import model.User;
import service.product.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/user/deleteFromBasket")
public class DeleteFromBasketServlet extends HttpServlet {

    private static final ProductService productService = ProductServiceFactory.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        Product product = productService.getById(id).get();
        req.setAttribute("id", id);
        User userFromSession = (User) req.getSession().getAttribute("user");
        Basket basket = userFromSession.getBasket();
        basket.deleteProductFromBasket(product);
        basket.setTotalPrice(basket.countTotalPrice());
        userFromSession.setBasket(basket);
        req.setAttribute("totalPrice", basket.getTotalPrice());
        req.setAttribute("basket", basket.getProductsInBasket());
        req.getRequestDispatcher("/basket.jsp").forward(req, resp);
    }
}
