package model;

public class ConfirmationCode {

    private Long id;
    private Long basketID;
    private String code;
    private String userEmail;

    public ConfirmationCode(String userEmail) {
        this.userEmail = userEmail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBasketID() {
        return basketID;
    }

    public void setBasketID(Long basketID) {
        this.basketID = basketID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConfirmationCode that = (ConfirmationCode) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (basketID != null ? !basketID.equals(that.basketID) : that.basketID != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        return userEmail != null ? userEmail.equals(that.userEmail) : that.userEmail == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (basketID != null ? basketID.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (userEmail != null ? userEmail.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ConfirmationCode{" +
                "id=" + id +
                ", basketID=" + basketID +
                ", code='" + code + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
