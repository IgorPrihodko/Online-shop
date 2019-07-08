package service.article;

import model.Article;

import java.util.Map;

public interface ArticleService {
    void addArticle(Article article);
    Map<Long, Article> getAll();
}
