package service.product;

import dao.product.ProductDao;
import factory.ProductServiceFactory;
import model.Product;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private static ProductDao productDao = ProductServiceFactory.getInstance();

    @Override
    public Long createId() {
        return productDao.createID();
    }

    @Override
    public void addProduct(Product product) {
        productDao.addProduct(product);
    }

    @Override
    public void removeProduct(Long id) {
        productDao.removeProduct(id);
    }

    @Override
    public Product getById(Long id) {
        return productDao.getById(id);
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }
}
