package dao.product;

import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDaoImpl implements ProductDao {

    private static final List<Product> products = new ArrayList<>();
    private static Long idCounter = 0L;

    @Override
    public synchronized Long createID() {
        return idCounter++;
    }

    @Override
    public void addProduct(Product product) {
        product.setId(createID());
        products.add(product);
    }

    @Override
    public void removeProduct(Long id) {
         products.remove(products.stream()
                 .filter(product -> product.getId().equals(id))
                 .findFirst()
                 .get());
    }

    @Override
    public Optional<Product> getById(Long id) {
        return products.stream().filter(product -> product.getId().equals(id)).findFirst();
    }

    @Override
    public List<Product> getAll() {
        return products;
    }
}
