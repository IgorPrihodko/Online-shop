package dao.product;

import model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    Long createID();
    void addProduct(Product product);
    void removeProduct(Long id);
    Optional<Product> getById (Long id);
    List<Product> getAll();
}
