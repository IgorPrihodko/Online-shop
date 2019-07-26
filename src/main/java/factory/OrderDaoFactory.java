package factory;

import dao.order.OrderDao;
import dao.order.OrderDaoJDBC;

public class OrderDaoFactory {

    private static OrderDao instance;

    public static synchronized OrderDao getInstance() {
        if (instance == null) {
            instance = new OrderDaoJDBC();
        }
        return instance;
    }
}
