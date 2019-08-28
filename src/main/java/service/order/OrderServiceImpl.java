package service.order;

import dao.order.OrderDao;
import factory.OrderDaoFactory;
import model.Order;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    private static final OrderDao orderDao = OrderDaoFactory.getInstance();

    @Override
    public Long createID() {
        return orderDao.createID();
    }

    @Override
    public void addOrder(Order order) {
        orderDao.addOrder(order);
    }

    @Override
    public void removeOrder(Long id) {
        orderDao.removeOrder(id);
    }

    @Override
    public Optional<Order> getById(Long id) {
        return orderDao.getById(id);
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public List<Order> getAllByUser(Long userID) {
        return orderDao.getAllByUser(userID);
    }
}
