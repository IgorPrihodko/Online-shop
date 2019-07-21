package factory;

import service.order.OrderService;
import service.order.OrderServiceImpl;

public class OrderServiceFactory {

    private static OrderService instance;

    public static synchronized OrderService getInstance() {
        if (instance == null) {
            instance = new OrderServiceImpl();
        }
        return instance;
    }
}
