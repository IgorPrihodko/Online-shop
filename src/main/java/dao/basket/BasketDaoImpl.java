/*
package dao.basket;

import model.Basket;
import model.User;
import org.apache.log4j.Logger;
import utils.IDCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BasketDaoImpl implements BasketDao {

    private static final Logger logger = Logger.getLogger(BasketDaoImpl.class);
    private static final List<Basket> baskets = new ArrayList<>();
    private static Long idCounter = 0L;

    @Override
    public void addBasket(Basket basket) {
        basket.setId(IDCreator.create(idCounter));
        idCounter++;
        baskets.add(basket);
        logger.info("Add basket " + basket + " to db");
    }

    @Override
    public void removeBasket(Long id) {
        baskets.remove(baskets.stream().filter(basket -> basket.getId().equals(id)).findFirst().get());
        logger.info("Remove basket from db");
    }

    @Override
    public void addProductsToBasket(Basket basket) {
        addBasket(basket);
    }

    @Override
    public Optional<Basket> getLastBasketForUser(User user) {
        return baskets.stream()
                .filter(basket -> basket.getUserID().equals(user.getId()))
                .min((o1, o2) -> (int) (o2.getId() - o1.getId()));
    }

    @Override
    public List<Basket> getAllByUser(User user) {
        return baskets
                .stream()
                .filter(basket -> basket.getUserID().equals(user.getId()))
                .collect(Collectors.toList());
    }
}
*/
