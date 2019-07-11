package controller.product;

import dao.product.ProductDao;
import factory.ProductDaoFactory;
import model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/deleteProduct")
public class DeleteProductServlet extends HttpServlet {

    private ProductDao productDao = ProductDaoFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        Product product = productDao.getById(id);
        req.setAttribute("id", product.getId());
        req.setAttribute("title", product.getTitle());
        req.setAttribute("description", product.getDescription());
        req.setAttribute("price", product.getPrice());
        req.getRequestDispatcher("deleteProduct.jsp").forward(req, resp);
        resp.sendRedirect("/deleteProduct");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        req.setAttribute("id", id);
        productDao.removeProduct(id);
        resp.sendRedirect("/products");
    }
}
