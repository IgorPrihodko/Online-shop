package factory;

import dao.basket.BasketDao;
import dao.basket.BasketDaoHibernate;

public class BasketDaoFactory {

    private static BasketDao instance;

    public static synchronized BasketDao getInstance() {
        if (instance == null) {
            instance = new BasketDaoHibernate();
        }
        return instance;
    }
}
