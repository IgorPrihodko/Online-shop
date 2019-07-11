package service.product;

import model.Product;

import java.util.List;

public interface ProductService {

    Long createId();
    void addProduct(Product product);
    void removeProduct(Long id);
    Product getById (Long id);
    List<Product> getAll();
}
