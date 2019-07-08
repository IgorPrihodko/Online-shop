package controller.article;

import model.Article;
import service.article.ArticleService;
import service.article.ArticleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(value = "/articles")
public class AllArticlesServlet extends HttpServlet {

    private static final ArticleService articleService = new ArticleServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<Long, Article> allArticles = articleService.getAll();
        req.setAttribute("allArticles", allArticles);
        req.getRequestDispatcher("articles.jsp").forward(req, resp);
    }
}
