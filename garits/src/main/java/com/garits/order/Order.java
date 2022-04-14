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
    @Column(name="company")
    private String company;
    @Column(name="address")
    private String address;
    @Column(name="phone_no")
    private String phoneNo;
    @Column(name="fax")
    private String fax;
    public Order() {
    }

    public Order(Integer idOrder, String status, Date orderDate, Date orderArrival, double orderAmount, String description, String company, String address, String phoneNo, String fax) {
        this.idOrder = idOrder;
        this.status = status;
        this.orderDate = orderDate;
        this.orderArrival = orderArrival;
        this.orderAmount = orderAmount;
        this.description = description;
        this.company = company;
        this.address = address;
        this.phoneNo = phoneNo;
        this.fax = fax;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }
}


