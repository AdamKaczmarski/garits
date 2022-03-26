package com.garits.customer.discounts;

import com.garits.service.Service;
import org.hibernate.annotations.Formula;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
@Entity
@Table(name="customer_variable_discounts_services")
public class VariableDiscount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_var_discount")
    private Integer idVarDiscount;
    @Column(name="customer_id")
    private Integer customerId;
    @Column(name="service_id")
    private Integer serviceId;
    @Column(name="discount")
    private int discount;

    public VariableDiscount(){

    }

    public VariableDiscount(Integer customerId, Integer serviceId, int discount) {
        this.customerId = customerId;
        this.serviceId = serviceId;
        this.discount = discount;
    }

    public VariableDiscount(Integer serviceId, int discount) {
        this.serviceId = serviceId;
        this.discount = discount;
    }

    public VariableDiscount(Integer idVarDiscount, Integer customerId, Integer serviceId, int discount) {
        this.idVarDiscount = idVarDiscount;
        this.customerId = customerId;
        this.serviceId = serviceId;
        this.discount = discount;
    }


    public Integer getIdVarDiscount() {
        return idVarDiscount;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }
}
