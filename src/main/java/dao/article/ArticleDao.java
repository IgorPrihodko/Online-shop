package dao.article;

import model.Article;

import java.util.Map;

public interface ArticleDao {
    void addArticle(Article article);
    Map<Long, Article> getAll();
}
