package controller;

import factory.ProductServiceFactory;
import factory.UserServiceFactory;
import model.Product;
import model.User;
import org.apache.log4j.Logger;
import service.product.ProductService;
import service.user.UserService;
import utils.HashUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@WebServlet(value = "/", loadOnStartup = 0)
public class InitServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(InitServlet.class);
    private static final UserService userService = UserServiceFactory.getInstance();
    private static final ProductService productService = ProductServiceFactory.getInstance();

    @Override
    public void init() throws ServletException {
        if (userService.getAll().isEmpty()) {
            try {
                String hashedPasswordFirst = HashUtil.generateHashPassword("test");
                User firstUserTest = new User("test@test", hashedPasswordFirst, "admin");
                userService.addUser(firstUserTest);
                logger.info("Add initial user with ADMIN role " + firstUserTest + " to DB");

                String hashedPasswordSecond = HashUtil.generateHashPassword("111");
                User secondUserTest = new User("igorprihodkoemail@gmail.com",
                        hashedPasswordSecond, "user");
                userService.addUser(secondUserTest);
                logger.info("Add initial user with USER role " + secondUserTest + " to DB");
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                logger.error("Can not hashing password", e);
            }
        }
        if (productService.getAll().isEmpty()) {
            Product firstProductTest = new Product("firstProductTest", "test",
                    BigDecimal.valueOf(0.1));
            Product secondProductTest = new Product("secondProductTest", "test",
                    BigDecimal.valueOf(0.4));
            productService.addProduct(firstProductTest);
            logger.info("Add new product " + firstProductTest + " to DB");
            productService.addProduct(secondProductTest);
            logger.info("Add new product " + secondProductTest + " to DB");
        }
    }
}
