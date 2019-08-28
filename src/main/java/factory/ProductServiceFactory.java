package factory;

import service.product.ProductService;
import service.product.ProductServiceImpl;

public class ProductServiceFactory {

    private static ProductService instance;

    public static synchronized ProductService getInstance() {
        if (instance == null) {
            instance = new ProductServiceImpl();
        }
        return instance;
    }
}
