package dao.order;

import model.Order;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderDaoImpl implements OrderDao {

    private static final Logger logger = Logger.getLogger(OrderDaoImpl.class);
    private static final List<Order> orders = new ArrayList<>();
    private static Long idCounter = 1L;

    @Override
    public synchronized Long createID() {
        logger.info("Increment id counter for order");
        return idCounter++;
    }

    @Override
    public void addOrder(Order order) {
        order.setId(createID());
        orders.add(order);
        logger.info("Add order " + order + " to db");
    }

    @Override
    public void removeOrder(Long id) {
        orders.remove(orders.stream().filter(order -> order.getId().equals(id)).findFirst().get());
        logger.info("Remove order from db");
    }

    @Override
    public Optional<Order> getById(Long id) {
        return orders.stream().filter(order -> order.getId().equals(id)).findFirst();
    }

    @Override
    public List<Order> getAll() {
        return orders;
    }

    @Override
    public List<Order> getAllByUser(Long userID) {
        return orders
                .stream()
                .filter(order -> order.getUserID().equals(userID))
                .collect(Collectors.toList());
    }
}
