package model;

public class ConfirmationCode {

    private Long id;
    private String code;
    private String userEmail;

    public ConfirmationCode(String userEmail) {
        this.userEmail = userEmail;
    }

    public ConfirmationCode(Long id, String userEmail) {
        this.id = id;
        this.userEmail = userEmail;
    }

    public ConfirmationCode(Long id, String code, String userEmail) {
        this.id = id;
        this.code = code;
        this.userEmail = userEmail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        return userEmail != null ? userEmail.equals(that.userEmail) : that.userEmail == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (userEmail != null ? userEmail.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ConfirmationCode{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
