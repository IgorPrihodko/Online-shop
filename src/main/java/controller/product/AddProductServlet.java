package controller.product;

import dao.product.ProductDao;
import factory.ProductServiceFactory;
import model.Product;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/addProduct")
public class AddProductServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AddProductServlet.class);
    private ProductDao productDao = ProductServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("addProduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        Double price = Double.parseDouble(req.getParameter("price"));
        if (title.isEmpty() || description.isEmpty() || price.isNaN()) {
            req.setAttribute("error", "One or more fields are empty! Try better.");
            req.getRequestDispatcher("addProduct.jsp").forward(req, resp);
        }

        Product product = new Product(title, description, price);
        productDao.addProduct(product);
        logger.info("Add new product " + product + " to db");
        resp.sendRedirect("/products");
    }
}