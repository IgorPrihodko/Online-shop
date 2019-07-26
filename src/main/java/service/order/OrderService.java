package service.order;

import model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    void addOrder(Order order);

    void removeOrder(Long id);

    Optional<Order> getById(Long id);

    List<Order> getAll();
}
