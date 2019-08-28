package dao.product;

import model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    void addProduct(Product product);

    void removeProduct(Long id);

    void updateProduct(Long id, Product product);

    Optional<Product> getById (Long id);

    List<Product> getAll();
}
