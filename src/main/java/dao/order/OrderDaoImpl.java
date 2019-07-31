package dao.order;

import model.StockOnOrder;
import org.apache.log4j.Logger;
import utils.IDCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao {

    private static final Logger logger = Logger.getLogger(OrderDaoImpl.class);
    private static final List<StockOnOrder> STOCK_ON_ORDERS = new ArrayList<>();
    private static Long idCounter = 1L;

    @Override
    public void addOrder(StockOnOrder stockOnOrder) {
        stockOnOrder.setId(IDCreator.create(idCounter));
        idCounter++;
        STOCK_ON_ORDERS.add(stockOnOrder);
        logger.info("Add stockOnOrder " + stockOnOrder + " to db");
    }

    @Override
    public void removeOrder(Long id) {
        STOCK_ON_ORDERS.remove(STOCK_ON_ORDERS.stream().filter(order -> order.getId().equals(id)).findFirst().get());
        logger.info("Remove order from db");
    }

    @Override
    public Optional<StockOnOrder> getById(Long id) {
        return STOCK_ON_ORDERS.stream().filter(order -> order.getId().equals(id)).findFirst();
    }

    @Override
    public List<StockOnOrder> getAll() {
        return STOCK_ON_ORDERS;
    }
}
