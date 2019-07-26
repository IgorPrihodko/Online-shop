package service.product;

import dao.product.ProductDao;
import factory.ProductDaoFactory;
import model.Product;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    private static final ProductDao productDao = ProductDaoFactory.getInstance();

    @Override
    public void addProduct(Product product) {
        productDao.addProduct(product);
    }

    @Override
    public void removeProduct(Long id) {
        productDao.removeProduct(id);
    }

    @Override
    public void updateProduct(Long id, Product product) {
        productDao.updateProduct(id, product);
    }

    @Override
    public Optional<Product> getById(Long id) {
        return productDao.getById(id);
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }
}
