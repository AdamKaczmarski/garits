package com.garits.customer.discounts;

import javax.persistence.*;

@Entity
@Table(name="customer_flex_discounts")
public class FlexDiscount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_flex_discount")
    private Integer idFlexDiscount;
    @Column(name="customer_id")
    private Integer customerId;
    @Column(name="range_from")
    private int rangeFrom;
    @Column(name="discount")
    private int discount;

    public FlexDiscount (Integer idFlexDiscount, Integer customerId, int rangeFrom, int discount){
        this.idFlexDiscount=idFlexDiscount;
        this.customerId=customerId;
        this.rangeFrom=rangeFrom;
        this.discount=discount;
    }

    public FlexDiscount() {

    }


    public Integer getIdFlexDiscount() {
        return idFlexDiscount;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public int getRangeFrom() {
        return rangeFrom;
    }

    public void setRangeFrom(int rangeFrom) {
        this.rangeFrom = rangeFrom;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
