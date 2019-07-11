package dao.product;

import model.Product;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private static final Logger logger = Logger.getLogger(ProductDaoImpl.class);
    private static final List<Product> PRODUCTS = new ArrayList<>();
    private static Long idCounter = 0L;

    @Override
    public synchronized Long createID() {
        logger.info("Increment id counter for product");
        return idCounter++;
    }

    @Override
    public void addProduct(Product product) {
        product.setId(createID());
        PRODUCTS.add(product);
        logger.info("Add product " + product + " to db");
    }

    @Override
    public void removeProduct(Long id) {
        for (Product product : PRODUCTS) {
            if (product.getId().equals(id)) {
                PRODUCTS.remove(product);
                logger.info("Remove product " + product + " from db");
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
