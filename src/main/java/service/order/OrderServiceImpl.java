package service.order;

import dao.order.OrderDao;
import factory.OrderDaoFactory;
import model.StockOnOrder;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    private static final OrderDao orderDao = OrderDaoFactory.getInstance();

    @Override
    public void addOrder(StockOnOrder stockOnOrder) {
        orderDao.addOrder(stockOnOrder);
    }

    @Override
    public void removeOrder(Long id) {
        orderDao.removeOrder(id);
    }

    @Override
    public Optional<StockOnOrder> getById(Long id) {
        return orderDao.getById(id);
    }

    @Override
    public List<StockOnOrder> getAll() {
        return orderDao.getAll();
    }
}
