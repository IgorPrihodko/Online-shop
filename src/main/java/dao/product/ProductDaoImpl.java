package dao.product;

import model.Product;
import org.apache.log4j.Logger;
import utils.IDCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDaoImpl implements ProductDao {

    private static final Logger logger = Logger.getLogger(ProductDaoImpl.class);
    private static final List<Product> products = new ArrayList<>();
    private static Long idCounter = 0L;

    @Override
    public void addProduct(Product product) {
        product.setId(IDCreator.create(idCounter));
        idCounter++;
        products.add(product);
        logger.info("Add product " + product + " to db");
    }

    @Override
    public void removeProduct(Long id) {
         products.remove(products.stream()
                 .filter(product -> product.getId().equals(id))
                 .findFirst()
                 .get());
        logger.info("Remove product from db");
    }

    @Override
    public void updateProduct(Long id, Product updatedProduct) {
        products.set(products.indexOf(products
                .stream()
                .filter(product -> product.getId().equals(id)).findFirst().get()), updatedProduct);
        logger.info("Update product from db");
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
