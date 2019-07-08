package service.article;

import dao.article.ArticleDao;
import factory.ArticleServiceFactory;
import model.Article;

import java.util.Map;

public class ArticleServiceImpl implements ArticleService {
    private static ArticleDao articleDao = ArticleServiceFactory.getInstance();

    @Override
    public Long createId() {
        return articleDao.createID();
    }

    @Override
    public void addArticle(Article article) {
        articleDao.addArticle(article);
    }

    @Override
    public Map<Long, Article> getAll() {
        return articleDao.getAll();
    }
}
