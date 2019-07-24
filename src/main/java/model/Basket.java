package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Basket {

    private Long id;
    private Long userID;
    private Long orderID;
    private List<Product> productsInBasket = new ArrayList<>();
    private BigDecimal totalPrice;
    private ConfirmationCode confirmationCode;

    public Basket(Long userID) {
        this.userID = userID;
    }

    public void addProductToBasket(Product product) {
        productsInBasket.add(product);
    }

    public void deleteProductFromBasket(Product product) {
        productsInBasket.remove(product);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
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

    @Override
    public String toString() {
        return "Basket{" +
                "id=" + id +
                ", userID=" + userID +
                ", orderID=" + orderID +
                ", productsInBasket=" + productsInBasket +
                ", totalPrice=" + totalPrice +
                ", confirmationCode=" + confirmationCode +
                '}';
    }
}
