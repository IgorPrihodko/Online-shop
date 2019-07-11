package factory;

import dao.product.ProductDao;
import dao.product.ProductDaoImpl;

public class ProductServiceFactory {

    private static ProductDao instance;

    public static synchronized ProductDao getInstance() {
        if (instance == null) {
            instance = new ProductDaoImpl();
        }
        return instance;
    }
}
