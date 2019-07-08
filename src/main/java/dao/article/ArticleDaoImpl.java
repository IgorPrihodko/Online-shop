package dao.article;

import model.Article;

import java.util.HashMap;
import java.util.Map;

public class ArticleDaoImpl implements ArticleDao {
    public static final Map<Long, Article> ARTICLES = new HashMap<Long, Article>();

    @Override
    public void addArticle(Article article) {
        ARTICLES.put(article.getId(), article);
    }

    @Override
    public Map<Long, Article> getAll() {
        return ARTICLES;
    }
}
