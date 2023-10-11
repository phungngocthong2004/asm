package DTO;

public class TaiKhoan_DTO {
    private String username, enail, password, fullnam;

    public TaiKhoan_DTO(String username, String enail, String password, String fullnam) {

        this.username = username;
        this.enail = enail;
        this.password = password;
        this.fullnam = fullnam;
    }

    public TaiKhoan_DTO() {
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEnail() {
        return enail;
    }

    public void setEnail(String enail) {
        this.enail = enail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullnam() {
        return fullnam;
    }

    public void setFullnam(String fullnam) {
        this.fullnam = fullnam;
    }
}
