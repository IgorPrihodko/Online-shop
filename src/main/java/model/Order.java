package model;

public class Order {

    private Long id;
    private Long userID;
    private String userEmail;
    private String name;
    private String surname;
    private String address;
    private Long basketID;

    public Order(Long id, Long userID, String userEmail, String name,
                 String surname, String address, Long basketID) {
        this.id = id;
        this.userID = userID;
        this.userEmail = userEmail;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.basketID = basketID;
    }

    public Order(Long userID, String userEmail) {
        this.userID = userID;
        this.userEmail = userEmail;
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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getBasketID() {
        return basketID;
    }

    public void setBasketID(Long basketID) {
        this.basketID = basketID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != null ? !id.equals(order.id) : order.id != null) return false;
        if (userID != null ? !userID.equals(order.userID) : order.userID != null) return false;
        if (userEmail != null ? !userEmail.equals(order.userEmail) : order.userEmail != null)
            return false;
        if (name != null ? !name.equals(order.name) : order.name != null) return false;
        if (surname != null ? !surname.equals(order.surname) : order.surname != null) return false;
        if (address != null ? !address.equals(order.address) : order.address != null) return false;
        return basketID != null ? basketID.equals(order.basketID) : order.basketID == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userID != null ? userID.hashCode() : 0);
        result = 31 * result + (userEmail != null ? userEmail.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (basketID != null ? basketID.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userID=" + userID +
                ", userEmail='" + userEmail + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", address='" + address + '\'' +
                ", basketID=" + basketID +
                '}';
    }
}
