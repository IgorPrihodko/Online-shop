package dao.basket;

import model.Basket;

import java.util.List;
import java.util.Optional;

public interface BasketDao {

    Long createID();

    void addBasket(Basket basket);

    void removeBasket(Long id);

    Optional<Basket> getById(Long id);

    List<Basket> getAll();

    List<Basket> getAllByUser(Long userID);
}
