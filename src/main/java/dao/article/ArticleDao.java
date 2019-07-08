package dao.article;

import model.Article;

import java.util.Map;

public interface ArticleDao {
    Long createID();
    void addArticle(Article article);
    Map<Long, Article> getAll();
}
