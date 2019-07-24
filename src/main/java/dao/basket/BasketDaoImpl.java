package dao.basket;

import model.Basket;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BasketDaoImpl implements BasketDao {

    private static final Logger logger = Logger.getLogger(BasketDaoImpl.class);
    private static final List<Basket> baskets = new ArrayList<>();
    private static Long idCounter = 1L;

    @Override
    public synchronized Long createID() {
        logger.info("Increment id counter for basket");
        return idCounter++;
    }

    @Override
    public void addBasket(Basket basket) {
        basket.setId(createID());
        baskets.add(basket);
        logger.info("Add basket " + basket + " to db");
    }

    @Override
    public void removeBasket(Long id) {
        baskets.remove(baskets.stream().filter(basket -> basket.getId().equals(id)).findFirst().get());
        logger.info("Remove basket from db");
    }

    @Override
    public Optional<Basket> getById(Long id) {
        return baskets.stream().filter(basket -> basket.getId().equals(id)).findFirst();
    }

    @Override
    public List<Basket> getAll() {
        return baskets;
    }

    @Override
    public List<Basket> getAllByUser(Long userID) {
        return baskets
                .stream()
                .filter(basket -> basket.getUserID().equals(userID))
                .collect(Collectors.toList());
    }
}
