package com.lisanbian.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private String order_id;
    private Date create_time;
    private BigDecimal total_money;
    private Integer status = 0;//默认未发货
    private Integer user_id;

    public Order(String order_id, Date create_time, BigDecimal total_money, Integer status, Integer user_id) {
        this.order_id = order_id;
        this.create_time = create_time;
        this.total_money = total_money;
        this.status = status;
        this.user_id = user_id;
    }

    public Order() {
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public BigDecimal getTotal_money() {
        return total_money;
    }

    public void setTotal_money(BigDecimal total_money) {
        this.total_money = total_money;
    }



    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id='" + order_id + '\'' +
                ", create_time=" + create_time +
                ", total_money=" + total_money +
                ", status=" + status +
                ", user_id=" + user_id +
                '}';
    }
}
