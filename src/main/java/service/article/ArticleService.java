package service.article;

import model.Article;

import java.util.Map;

public interface ArticleService {
    Long createId();
    void addArticle(Article article);
    Map<Long, Article> getAll();
}
