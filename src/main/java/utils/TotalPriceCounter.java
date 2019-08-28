package utils;

import factory.ProductServiceFactory;
import model.Basket;
import model.Product;
import service.product.ProductService;

import java.math.BigDecimal;

public class TotalPriceCounter {

    private static ProductService productService = ProductServiceFactory.getInstance();

    public static BigDecimal count(Basket basket) {
        Product productFromDB;
        BigDecimal totalPrice = BigDecimal.valueOf(0.0);
        for (Product product : basket.getProductsInBasket()) {
            productFromDB = productService.getById(product.getId()).get();
            totalPrice = totalPrice.add(productFromDB.getPrice());
        }
        return totalPrice;
    }
}
