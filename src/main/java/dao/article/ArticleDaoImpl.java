package dao.article;

import model.Article;

import java.util.HashMap;
import java.util.Map;

public class ArticleDaoImpl implements ArticleDao {
    private static final Map<Long, Article> ARTICLES = new HashMap<Long, Article>();
    private static Long idCounter = 1L;

    @Override
    public synchronized Long createID() {
        return idCounter++;
    }

    @Override
    public void addArticle(Article article) {
        article.setId(createID());
        ARTICLES.put(article.getId(), article);
    }

    @Override
    public Map<Long, Article> getAll() {
        return ARTICLES;
    }
}
