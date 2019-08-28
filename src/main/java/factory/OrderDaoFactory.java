package factory;

import dao.order.OrderDao;
import dao.order.OrderDaoImpl;

public class OrderDaoFactory {

    private static OrderDao instance;

    public static synchronized OrderDao getInstance() {
        if (instance == null) {
            instance = new OrderDaoImpl();
        }
        return instance;
    }
}
