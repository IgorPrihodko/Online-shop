package dao;

import model.Article;
import model.User;

import java.util.HashMap;
import java.util.Map;

public class DataBase {
    public static final Map<Long, User> USERS = new HashMap<Long, User>();
    public static final Map<Long, Article> ARTICLES = new HashMap<Long, Article>();
}
