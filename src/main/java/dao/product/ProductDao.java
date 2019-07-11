package dao.product;

import model.Product;

import java.util.List;

public interface ProductDao {

    Long createID();
    void addProduct(Product product);
    void removeProduct(Long id);
    Product getById (Long id);
    List<Product> getAll();
}
