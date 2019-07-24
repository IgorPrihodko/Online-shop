package service.basket;

import dao.basket.BasketDao;
import factory.BasketDaoFactory;
import model.Basket;

import java.util.List;
import java.util.Optional;

public class BasketServiceImpl implements BasketService {

    private static final BasketDao basketDao = BasketDaoFactory.getInstance();

    @Override
    public Long createID() {
        return basketDao.createID();
    }

    @Override
    public void addBasket(Basket basket) {
        basketDao.addBasket(basket);
    }

    @Override
    public void removeBasket(Long id) {
        basketDao.removeBasket(id);
    }

    @Override
    public Optional<Basket> getById(Long id) {
        return basketDao.getById(id);
    }

    @Override
    public List<Basket> getAll() {
        return basketDao.getAll();
    }

    @Override
    public List<Basket> getAllByUser(Long userID) {
        return basketDao.getAllByUser(userID);
    }
}
