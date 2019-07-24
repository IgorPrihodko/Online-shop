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
        User userFirstTest = new User("test@test", "test", "admin");
        User userSecondTest = new User("igorprihodkoemail@gmail.com",
                "111", "user");
        userService.addUser(userFirstTest);
        logger.warn("Add initial user with ADMIN role " + userFirstTest + " to DB");
        userService.addUser(userSecondTest);
        logger.warn("Add initial user with USER role " + userSecondTest + " to DB");

        Product testProductFirst= new Product("testProductFirst", "test",
                0.1);
        Product testProductSecond = new Product("testProductSecond", "test",
                0.4);
        productService.addProduct(testProductFirst);
        logger.warn("Add new product " + testProductFirst + " to DB");
        productService.addProduct(testProductSecond);
        logger.warn("Add new product " + testProductSecond + " to DB");
    }
}
