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
        User user = new User("test@test", "test", "admin");
        User user1 = new User("111@111", "111", "user");
        User user2 = new User("igorprihodkoemail@gmail.com", "111", "user");
        userService.addUser(user);
        userService.addUser(user1);
        userService.addUser(user2);
        logger.warn("Add initial user " + user + " to DB");

        Product product = new Product("test", "test", 0.0);
        Product product1 = new Product("test1", "test1", 0.1);
        Product product2 = new Product("test2", "test2", 0.4);
        productService.addProduct(product);
        productService.addProduct(product1);
        productService.addProduct(product2);
        logger.warn("Add new product " + product + " to DB");
    }
}
