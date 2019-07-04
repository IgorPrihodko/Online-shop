package controller;

import com.sun.istack.internal.NotNull;
import dao.DataBase;
import model.Article;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/addArticle")
public class AddArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("addArticle.jsp").forward(req, resp);
    }

    @Override
    @NotNull
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (req.getParameter("title").isEmpty() ||
                req.getParameter("description").isEmpty() ||
                req.getParameter("price").isEmpty()) {
            req.setAttribute("isEmpty", "One or more fields are empty! Try better.");
            req.getRequestDispatcher("addArticle.jsp").forward(req, resp);
        }
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        Double price = Double.parseDouble(req.getParameter("price"));

        Article article = new Article(title, description, price);
        DataBase.ARTICLES.put(article.getId(), article);
        System.out.println(article);
        resp.sendRedirect("/addArticle");
    }
}
