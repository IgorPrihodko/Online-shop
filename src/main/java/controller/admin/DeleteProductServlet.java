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

@WebServlet(value = "/admin/deleteProduct")
public class DeleteProductServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(DeleteProductServlet.class);
    private static final ProductService productService = ProductServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        Product product = productService.getById(id).get();
        req.setAttribute("id", product.getId());
        req.setAttribute("title", product.getTitle());
        req.setAttribute("description", product.getDescription());
        req.setAttribute("price", product.getPrice());
        req.getRequestDispatcher("/deleteProduct.jsp").forward(req, resp);
        resp.sendRedirect("/admin/deleteProduct");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        Product product = productService.getById(id).get();
        req.setAttribute("id", id);
        productService.removeProduct(id);
        logger.warn("Delete product " + product + " from db");
        resp.sendRedirect("/admin/products");
    }
}
