package ir.hossein.domain;

import java.util.Date;

public class Tariff {
    @Override
    public String toString() {
        return "Tariff{" +
                "id=" + id +
                ", amount=" + amount +
                ", create_date=" + create_date +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Tariff(Integer id, Integer amount, Date create_date) {
        this.id = id;
        this.amount = amount;
        this.create_date = create_date;
    }

    private Integer id;
    private Integer amount;
    private Date create_date;
}
