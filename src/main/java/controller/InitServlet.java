package controller;

import factory.ProductServiceFactory;
import factory.UserServiceFactory;
import model.Product;
import model.User;
import service.product.ProductService;
import service.user.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(value = "/", loadOnStartup = 1)
public class InitServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getInstance();
    private static final ProductService productService = ProductServiceFactory.getInstance();

    @Override
    public void init() throws ServletException {
        userService.addUser(new User("test@test", "test"));
        productService.addProduct(new Product("test", "test", 0.0));
    }
}
