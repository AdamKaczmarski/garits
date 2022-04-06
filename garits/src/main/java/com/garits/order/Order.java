package com.garits.order;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Integer idOrder;
    @Column(name = "status")
    private String status;
    @Column(name = "order_date")
    private Date orderDate;
    @Column(name = "order_arrival")
    private Date orderArrival;
    @Column(name="order_amount")
    private double orderAmount;
    @Column(name="description")
    private String description;

    public Order() {
    }

    public Order(Integer idOrder, String status, Date orderDate, Date orderArrival, double orderAmount, String description) {
        this.idOrder = idOrder;
        this.status = status;
        this.orderDate = orderDate;
        this.orderArrival = orderArrival;
        this.orderAmount = orderAmount;
        this.description = description;
    }

    //Getters and Setters
    public Integer getIdOrder() {
        return idOrder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getOrderArrival() {
        return orderArrival;
    }

    public void setOrderArrival(Date orderArrival) {
        this.orderArrival = orderArrival;
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*public void setOrderTotalAmount(double amount){this.orderAmount=amount}*/
}


