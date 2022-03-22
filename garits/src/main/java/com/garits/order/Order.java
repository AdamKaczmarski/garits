package com.garits.order;

import javax.persistence.*;
import java.time.LocalDate;

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
    private LocalDate orderDate;
    @Column(name = "order_arrival")
    private LocalDate orderArrival;
    @Column(name="order_amount")
    private double orderAmount;
    @Column(name="description")
    private String description;
    //Getters and Setters
    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getOrderArrival() {
        return orderArrival;
    }

    public void setOrderArrival(LocalDate orderArrival) {
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
}


