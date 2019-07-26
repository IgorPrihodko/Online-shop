package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Basket {

    private Long id;
    private Long userID;
    private BigDecimal totalPrice;
    private ConfirmationCode confirmationCode;

    private List<Product> productsInBasket = new ArrayList<>();

    public Basket(Long userID) {
        this.userID = userID;
    }

    public Basket(Long id, Long userID, BigDecimal totalPrice) {
        this.id = id;
        this.userID = userID;
        this.totalPrice = totalPrice;
    }

    public Basket(Long id, Long userID, BigDecimal totalPrice, ConfirmationCode confirmationCode) {
        this.id = id;
        this.userID = userID;
        this.totalPrice = totalPrice;
        this.confirmationCode = confirmationCode;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Basket basket = (Basket) o;

        if (id != null ? !id.equals(basket.id) : basket.id != null) return false;
        if (userID != null ? !userID.equals(basket.userID) : basket.userID != null) return false;
        if (totalPrice != null ? !totalPrice.equals(basket.totalPrice) : basket.totalPrice != null)
            return false;
        if (confirmationCode != null ? !confirmationCode.equals(basket.confirmationCode) : basket.confirmationCode != null)
            return false;
        return productsInBasket != null ? productsInBasket.equals(basket.productsInBasket) : basket.productsInBasket == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userID != null ? userID.hashCode() : 0);
        result = 31 * result + (totalPrice != null ? totalPrice.hashCode() : 0);
        result = 31 * result + (confirmationCode != null ? confirmationCode.hashCode() : 0);
        result = 31 * result + (productsInBasket != null ? productsInBasket.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "id=" + id +
                ", userID=" + userID +
                ", totalPrice=" + totalPrice +
                ", confirmationCode=" + confirmationCode +
                ", productsInBasket=" + productsInBasket +
                '}';
    }
}
