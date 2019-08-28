package dao.order;

import model.StockOnOrder;

import java.util.List;
import java.util.Optional;

public interface OrderDao {

    void addOrder(StockOnOrder stockOnOrder);

    void removeOrder(Long id);

    Optional<StockOnOrder> getById(Long id);

    List<StockOnOrder> getAll();
}
