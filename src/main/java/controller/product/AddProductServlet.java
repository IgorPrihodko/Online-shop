package controller.product;

import factory.ProductServiceFactory;
import model.Product;
import service.product.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/addProduct")
public class AddProductServlet extends HttpServlet {

    private static final ProductService productService = ProductServiceFactory.getInstance();

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
        productService.addProduct(product);
        resp.sendRedirect("/products");
    }
}
