package service.basket;

import dao.basket.BasketDao;
import factory.BasketDaoFactory;
import model.Basket;
import model.User;

import java.util.List;
import java.util.Optional;

public class BasketServiceImpl implements BasketService {

    private static final BasketDao basketDao = BasketDaoFactory.getInstance();

    @Override
    public void addBasket(Basket basket) {
        basketDao.addBasket(basket);
    }

    @Override
    public void removeBasket(Long id) {
        basketDao.removeBasket(id);
    }

    @Override
    public void addProductsToBasket(Basket basket) {
        basketDao.addProductsToBasket(basket);
    }

    @Override
    public Optional<Basket> getLastBasketForUser(User user) {
        return basketDao.getLastBasketForUser(user);
    }

    @Override
    public List<Basket> getAllByUser(User user) {
        return basketDao.getAllByUser(user);
    }
}
