package service.basket;

import model.Basket;
import model.User;

import java.util.List;
import java.util.Optional;

public interface BasketService {

    void addBasket(Basket basket);

    void removeBasket(Long id);

    void addProductsToBasket(Basket basket);

    Optional<Basket> getLastBasketForUser(User user);

    List<Basket> getAllByUser(User user);
}
