package controller.product;

import dao.product.ProductDao;
import factory.ProductServiceFactory;
import model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/products")
public class AllProductServlet extends HttpServlet {

    private static final ProductDao productDao = ProductServiceFactory.getInstance();
    private List<Product> allProducts = productDao.getAll();

    public void initialProduct() {
        if (allProducts.isEmpty()) {
            Product initialProduct = new Product("test", "test", 0.0);
            productDao.addProduct(initialProduct);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Product> allProducts = productDao.getAll();
        req.setAttribute("allProducts", allProducts);
        req.getRequestDispatcher("products.jsp").forward(req, resp);
    }
}
