package factory;

import dao.article.ArticleDao;
import dao.article.ArticleDaoImpl;

public class ArticleServiceFactory {
    private static ArticleDao instance;

    public static synchronized ArticleDao getInstance() {
        if (instance == null) {
            instance = new ArticleDaoImpl();
        }
        return instance;
    }
}
