package dao.product;

import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private static final List<Product> PRODUCTS = new ArrayList<>();
    private static Long idCounter = 0L;

    @Override
    public synchronized Long createID() {
        return idCounter++;
    }

    @Override
    public void addProduct(Product product) {
        product.setId(createID());
        PRODUCTS.add(product);
    }

    @Override
    public void removeProduct(Long id) {
        for (Product product : PRODUCTS) {
            if (product.getId().equals(id)) {
                PRODUCTS.remove(product);
            }
        }
    }

    @Override
    public Product getById(Long id) {
        for (Product product : PRODUCTS) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public List<Product> getAll() {
        return PRODUCTS;
    }
}
