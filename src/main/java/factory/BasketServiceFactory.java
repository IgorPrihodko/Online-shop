package factory;

import service.basket.BasketService;
import service.basket.BasketServiceImpl;

public class BasketServiceFactory {

    private static BasketService instance;

    public static synchronized BasketService getInstance() {
        if (instance == null) {
            instance = new BasketServiceImpl();
        }
        return instance;
    }
}
