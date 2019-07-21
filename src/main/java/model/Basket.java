package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Basket {

    private List<Product> productsInBasket = new ArrayList<>();
    private BigDecimal totalPrice;
    private ConfirmationCode confirmationCode;

    public void addProductToBusket(Product product) {
        productsInBasket.add(product);
    }

    public BigDecimal countTotalPrice() {
        totalPrice = BigDecimal.valueOf(0.0);
        for (Product product : productsInBasket) {
            totalPrice = totalPrice.add(product.getPrice());
        }
        return totalPrice;
    }

    public void deleteProductFromBasket(Product product) {
        productsInBasket.remove(product);
    }

    public List<Product> getProductsInBasket() {
        return productsInBasket;
    }

    public void setProductsInBasket(List<Product> productsInBasket) {
        this.productsInBasket = productsInBasket;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ConfirmationCode getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(ConfirmationCode confirmationCode) {
        this.confirmationCode = confirmationCode;
    }
}
