package controller;

import factory.ProductServiceFactory;
import factory.UserServiceFactory;
import model.Product;
import model.User;
import org.apache.log4j.Logger;
import service.product.ProductService;
import service.user.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(value = "/", loadOnStartup = 0)
public class InitServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(InitServlet.class);
    private static final UserService userService = UserServiceFactory.getInstance();
    private static final ProductService productService = ProductServiceFactory.getInstance();

    @Override
    public void init() throws ServletException {
        User user = new User("test@test", "test");
        userService.addUser(user);
        logger.warn("Add initial user " + user + " to DB");

        Product product = new Product("test", "test", 0.0);
        productService.addProduct(product);
        logger.warn("Add new product " + product + " to DB");
    }
}
