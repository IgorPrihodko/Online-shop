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
import java.util.List;

@WebServlet(value = "/products")
public class AllProductServlet extends HttpServlet {

    private static final ProductService productService = ProductServiceFactory.getInstance();
    private List<Product> allProducts = productService.getAll();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("allProducts", allProducts);
        req.getRequestDispatcher("products.jsp").forward(req, resp);
    }
}
