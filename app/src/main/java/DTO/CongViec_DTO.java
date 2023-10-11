package DTO;

public class CongViec_DTO {
    private int idcv;
    private String name,content;
    private int status;


    private String star,and;

    public CongViec_DTO(int idcv, String name, String content, int status, String star, String and) {
        this.idcv = idcv;
        this.name = name;
        this.content = content;
        this.status = status;
        this.star = star;
        this.and = and;
    }

    public CongViec_DTO() {
    }

    public int getIdcv() {
        return idcv;
    }

    public void setIdcv(int idcv) {
        this.idcv = idcv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getAnd() {
        return and;
    }

    public void setAnd(String and) {
        this.and = and;
    }


}
