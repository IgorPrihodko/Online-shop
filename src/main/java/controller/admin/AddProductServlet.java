package controller.admin;

import factory.ProductServiceFactory;
import model.Product;
import org.apache.log4j.Logger;
import service.product.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(value = "/admin/addProduct")
public class AddProductServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AddProductServlet.class);
    private static final ProductService productService = ProductServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/addProduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(req.getParameter("price")));
        if (title.isEmpty() || description.isEmpty() || price.compareTo(BigDecimal.ZERO) <= 0) {
            req.setAttribute("error", "One or more fields are empty! Try better.");
            req.getRequestDispatcher("/addProduct.jsp").forward(req, resp);
        }

        Product product = new Product(title, description, price);
        productService.addProduct(product);
        logger.warn("Add new product " + product + " to db");
        resp.sendRedirect("/admin/products");
    }
}
