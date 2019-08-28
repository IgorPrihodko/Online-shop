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

@WebServlet(value = "/editProduct")
public class EditProductServlet extends HttpServlet {

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
        req.getRequestDispatcher("editProduct.jsp").forward(req, resp);
        resp.sendRedirect("/editProduct");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        Product product = productService.getById(id).get();
        req.setAttribute("id", id);
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        Double price = Double.valueOf(req.getParameter("price"));

        if (title.isEmpty() || description.isEmpty() || price <= 0) {
            if (title.isEmpty()) {
                req.setAttribute("error", "Title can not be empty! Try another.");
            } else {
                req.setAttribute("title", title);
            }
            if (description.isEmpty()) {
                req.setAttribute("error", "Description can not be empty! Try another.");
            } else {
                req.setAttribute("description", description);
            }
            if (price <= 0.0) {
                req.setAttribute("error", "Price can not be 0 or less! Try another.");
            } else {
                req.setAttribute("price", price);
            }
            req.getRequestDispatcher("editProduct.jsp").forward(req, resp);
            resp.sendRedirect("/editProduct");
        }
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        resp.sendRedirect("/products");
    }
}
