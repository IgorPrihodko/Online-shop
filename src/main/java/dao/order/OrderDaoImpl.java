package dao.order;

import model.StockOnOrder;
import org.apache.log4j.Logger;
import utils.IDCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao {

    private static final Logger logger = Logger.getLogger(OrderDaoImpl.class);
    private static final List<StockOnOrder> stockOnOrders = new ArrayList<>();
    private static Long idCounter = 1L;

    @Override
    public void addOrder(StockOnOrder stockOnOrder) {
        stockOnOrder.setId(IDCreator.create(idCounter));
        idCounter++;
        stockOnOrders.add(stockOnOrder);
        logger.info("Add stockOnOrder " + stockOnOrder + " to db");
    }

    @Override
    public void removeOrder(Long id) {
        stockOnOrders.remove(stockOnOrders.stream().filter(order -> order.getId().equals(id)).findFirst().get());
        logger.info("Remove order from db");
    }

    @Override
    public Optional<StockOnOrder> getById(Long id) {
        return stockOnOrders.stream().filter(order -> order.getId().equals(id)).findFirst();
    }

    @Override
    public List<StockOnOrder> getAll() {
        return stockOnOrders;
    }
}
