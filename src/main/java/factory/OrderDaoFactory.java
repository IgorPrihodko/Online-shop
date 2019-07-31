package factory;

import dao.order.OrderDao;
import dao.order.OrderDaoHibernate;

public class OrderDaoFactory {

    private static OrderDao instance;

    public static synchronized OrderDao getInstance() {
        if (instance == null) {
            instance = new OrderDaoHibernate();
        }
        return instance;
    }
}
