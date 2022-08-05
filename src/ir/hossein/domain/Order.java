package ir.hossein.domain;

import ir.hossein.util.Databaseutil;

import java.util.Date;

public class Order {
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", book_id=" + book_id +
                ", create_date=" + create_date +
                ", status=" + status +
                ", return_status=" + return_status +
                ", tariff_id=" + tariff_id +
                '}';
    }

    public Order(Integer id, Integer user_id, Integer book_id, Date create_date, boolean status, boolean return_status, Integer tariff_id) {
        this.id = id;
        this.user_id = user_id;
        this.book_id = book_id;
        this.create_date = create_date;
        this.status = status;
        this.return_status = return_status;
        this.tariff_id = tariff_id;
    }

    public Integer getTariff_id() {
        return tariff_id;
    }

    public void setTariff_id(Integer tariff_id) {
        this.tariff_id = tariff_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isReturn_status() {
        return return_status;
    }

    public void setReturn_status(boolean return_status) {
        this.return_status = return_status;
    }

    private Integer id;
    private Integer user_id;
    private Integer book_id;
    private Date create_date;
    private  boolean status;
    private  boolean return_status;
    private Integer tariff_id;
}
